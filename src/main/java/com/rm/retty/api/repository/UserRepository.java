package com.rm.retty.api.repository;

import com.rm.retty.api.model.User;

import java.util.List;

public class UserRepository extends AbstractRepository<String, User> {

    public UserRepository(List<User> users) {
        super(users);
    }
}