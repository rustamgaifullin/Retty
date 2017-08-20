package com.rm.retty.api.service;

import com.rm.retty.api.model.Account;
import com.rm.retty.api.model.User;
import com.rm.retty.api.repository.UserRepository;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    private final UserRepository userRepository = mock(UserRepository.class);

    @Test
    public void should_find_user_by_name() throws Exception {
        //given
        UserService sut = new UserService(userRepository);

        //when
        when(userRepository.get(anyString())).thenReturn(expectedUser());
        User actualUser = sut.findUserByName("Yoda");

        //then
        assertEquals(expectedUser(), actualUser);
    }

    private User expectedUser() {
        User user = new User("Yoda");

        user.addAccount(new Account(user, "AccountIsYodaNumber123", BigDecimal.ZERO));

        return user;
    }
}