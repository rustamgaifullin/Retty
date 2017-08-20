package com.rm.retty.api.service;

import com.rm.retty.api.model.Account;
import com.rm.retty.api.model.User;
import com.rm.retty.api.repository.AccountRepository;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class MoneyServiceTest {

    private AccountRepository accountRepository = mock(AccountRepository.class);

    @Test
    public void should_transfer_successfully() throws Exception {
        //given
        MoneyService sut = new MoneyService(accountRepository);
        Account fromAccount = new Account(new User("Yoda"), "number0", BigDecimal.valueOf(1000));
        Account toAccount = new Account(new User("Luke"), "number1", BigDecimal.valueOf(0));

        //when
        TransferResult actualResult = sut.transfer(fromAccount, toAccount, BigDecimal.valueOf(50));

        //then
        assertEquals(new TransferResult("Success"), actualResult);
    }
}