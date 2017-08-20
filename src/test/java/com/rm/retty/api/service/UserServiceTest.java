package com.rm.retty.api.service;

import com.rm.retty.api.model.Account;
import com.rm.retty.api.model.User;
import com.rm.retty.api.repository.UserRepository;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    private final UserRepository userRepository = mock(UserRepository.class);

    @Test
    public void should_find_user_by_name() throws Exception {
        //given
        UserService sut = new UserService(userRepository);
        User expectedUser = expectedUser();

        //when
        when(userRepository.get(anyString())).thenReturn(expectedUser);
        User actualUser = sut.findUserByName("Yoda");

        //then
        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void should_find_account_by_user_name_and_account_number() {
        //given
        UserService sut = new UserService(userRepository);
        User expectedUser = expectedUser();

        //when
        when(userRepository.get(anyString())).thenReturn(expectedUser);
        Optional<Account> account = sut.getAccount("Yoda", "number0");

        assertTrue(account.isPresent());
    }

    private User expectedUser() {
        User user = new User("Yoda");

        user.addAccount(new Account(user, "number0", BigDecimal.ZERO));

        return user;
    }
}