package com.example.dining.review;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {
    Optional<Review> findByName(String name);
    Optional<Review> findById(String name);
    List<Review> findByStatus(Status status);
    Optional<Review> findByRestIdAndStatus(Long restId, Status status);
}
