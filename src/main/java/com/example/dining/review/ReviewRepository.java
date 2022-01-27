package com.example.dining.review;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {
    Optional<Review> findByName(String name);
    Optional<Review> findById(Long id);
    List<Review> findByStatus(ReviewStatus status);
    List<Review> findByRestIdAndStatus(Long restId, ReviewStatus status);
}
