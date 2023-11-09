package com.finalproject.engineerapp.service;

import com.finalproject.engineerapp.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();

    void save(User user);

    void delete(User user);

    Optional<User> findById(Long id);

    User findByUsername(String username);
}
