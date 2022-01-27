package com.example.dining.restaurant;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, RestaurantId> {
    Optional<Restaurant> findById(Long id);
    Optional<Restaurant> findByNameAndZip(String name, String zip);

    @Query("SELECT r FROM restaurant r WHERE zip = ?1 AND (peanut IS NOT NULL OR egg IS NOT NULL OR diary IS NOT NULL) ORDER BY zip DESC")
    List<Restaurant> findByZipWithAllergy(String zip);
}
