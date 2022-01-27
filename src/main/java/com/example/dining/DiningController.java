package com.example.dining;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.dining.restaurant.Restaurant;
import com.example.dining.restaurant.RestaurantRepository;
import com.example.dining.review.Review;
import com.example.dining.review.ReviewRepository;
import com.example.dining.review.ReviewStatus;
import com.example.dining.user.User;
import com.example.dining.user.UserRepository;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class DiningController {
    private final RestaurantRepository restaurantRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    Logger logger = LoggerFactory.logger(DiningController.class);

    public DiningController(RestaurantRepository restaurantRepository,
        ReviewRepository reviewRepository, UserRepository userRepository) {
        this.restaurantRepository = restaurantRepository;
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/user/add")
    public void createUser(@RequestParam String name) {
        Optional<User> optUser = this.userRepository.findByName(name);
        if (optUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists");
        }
        else {
            User user = new User(name);
            this.userRepository.save(user);
        }
    }

    @PutMapping("/user/{name}/update")
    public void updateUserInfo(@PathVariable String name, @RequestParam Map<String, String> params) {
        logger.trace("Update User Info " + name + " with " + params);
        Optional<User> optUser = this.userRepository.findByName(name);
        if (!optUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username does not exist");
        }
        else {
            User user = optUser.get();
            if (params.containsKey("city"))
                user.setCity(params.get("city"));
            if (params.containsKey("zip"))
            {
                logger.trace("Update Zip" + params.get("zip"));
                user.setZip(params.get("zip"));
            }
            if (params.containsKey("state"))
                user.setState(params.get("state"));
            if (params.containsKey("peanut_allergy"))
            {
                logger.trace("Update Peanut Allergy" + params.get("peanut_allergy"));
                user.setPeanutAllergy(params.get("peanut_allergy").equals("true") ? true : false);
            }
            if (params.containsKey("egg_allergy"))
                user.setEggAllergy(params.get("egg_allergy").equals("true") ? true : false);
            if (params.containsKey("diary_allergy"))
                user.setDiaryAllergy(params.get("diary_allergy").equals("true") ? true : false);
            this.userRepository.save(user);
        } 
    }

    @GetMapping("/user/{name}")
    public User getUserInfo(@PathVariable String name) {
        logger.trace("Get User Info " + name);
        Optional<User> optUser = this.userRepository.findByName(name);
        if (!optUser.isPresent()) {
            logger.info("User " + name + " does not exist.");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User " + name + " does not exist.");
        }
        else {
            return optUser.get();
        }
    }

    @PostMapping("/review")
    public void createReview(@RequestParam String name, @RequestBody Review review) {
        logger.trace("Get User Info " + name);
        Optional<User> optUser = this.userRepository.findByName(name);
        if (!optUser.isPresent()) {
            logger.info("User " + name + " does not exist.");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User " + name + " does not exist.");
        }
        else {
            review.setName(name);
            review.setStatus(ReviewStatus.PENDING);
            this.reviewRepository.save(review);
        }
    }

    @GetMapping("/admin/reviews")
    public List<Review> getPendingReviews() {
        return this.reviewRepository.findByStatus(ReviewStatus.PENDING);
    }

    @PutMapping("/admin")
    public void updateStatus(@RequestParam Long id, @RequestParam String action) {
        Optional<Review> optReview = this.reviewRepository.findById(id);
        if (!optReview.isPresent()) {
            logger.info("Review " + id + " does not exist.");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Review " + id + " does not exist.");
        }
        else {
            Review review = optReview.get();
            switch (action) {
                case "approve":
                    review.setStatus(ReviewStatus.APPROVED);
                    break;
                case "reject":
                    review.setStatus(ReviewStatus.REJECTED);
                    break;
                default:
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Action is not valid.");
            }
            this.reviewRepository.save(review);
        }
    }

    @GetMapping("/restaurant/{id}/reviews")
    public List<Review> getAllReviewsOfRestaurant(@PathVariable Long id) {
        return this.reviewRepository.findByRestIdAndStatus(id, ReviewStatus.APPROVED);
    }

    @PostMapping("restaurant/add")
    public void createNewRestaurant(@RequestParam String name, @RequestParam String zip) {
        Optional<Restaurant> optRest = this.restaurantRepository.findByNameAndZip(name, zip);
        if (optRest.isPresent()) {
            logger.info("Restaurant " + name + " with zip " + zip + " already exists.");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Restaurant " + name + " with zip " + zip + " already exists.");
        }
        else {
            Restaurant rest = new Restaurant(name, zip);
            this.restaurantRepository.save(rest);
        }
    }

    @GetMapping("restaurant/{id}")
    public Restaurant getRestaurantInfo (@PathVariable Long id) {
        Optional<Restaurant> optRest = this.restaurantRepository.findById(id);
        if (!optRest.isPresent()) {
            logger.info("Restaurant " + id + " does not exist.");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Restaurant " + id + " does not exist.");
        }
        else
            return optRest.get();
    }

    @GetMapping("restaurant")
    public List<Restaurant> getRestaurantWithZipAndAllergy(@RequestParam String zip, @RequestParam Boolean allergy) {
        if (allergy)
            return this.restaurantRepository.findByZipWithAllergy(zip);
        else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unavailable request.");
    }
}
