package com.rm.retty.api.service;

import com.rm.retty.api.model.User;
import com.rm.retty.api.repository.UserRepository;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserByName(String name) {
        return userRepository.get(name);
    }
}