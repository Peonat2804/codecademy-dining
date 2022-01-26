package com.example.dining.user;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
    Optional<User> findByName(String name);
    Optional<User> findById(Long id);
}
