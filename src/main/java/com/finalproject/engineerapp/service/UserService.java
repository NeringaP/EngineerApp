package com.finalproject.engineerapp.service;

import com.finalproject.engineerapp.model.User;
import com.finalproject.engineerapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public void addUser(User user) {
        user.setEnabled(true);
        userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public Optional<User> findUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user;
    }
}
