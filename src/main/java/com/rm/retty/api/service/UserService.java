package com.rm.retty.api.service;

import com.rm.retty.api.model.Account;
import com.rm.retty.api.model.User;
import com.rm.retty.api.repository.UserRepository;

import java.util.Optional;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserByName(String name) {
        return userRepository.get(name);
    }

    public Optional<Account> getAccount(String userName, String accountNumber) {
        return userRepository
                .get(userName)
                .getAccountList()
                .stream()
                .filter(account -> account.getNumber().equals(accountNumber))
                .findFirst();
    }
}