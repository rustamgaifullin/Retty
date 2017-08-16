package com.rm.retty.api.service;

import com.rm.retty.api.model.Account;
import com.rm.retty.api.model.User;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class UserServiceTest {
    @Test
    public void findUserByName() throws Exception {
        UserService sut = new UserService();

        User actualUser = sut.findUserByName("Yoda");

        assertEquals(expectedUser(), actualUser);
    }

    private User expectedUser() {
        User user = new User("Yoda");

        user.addAccount(new Account(user, "AccountIsYodaNumber123", BigDecimal.ZERO));

        return user;
    }
}