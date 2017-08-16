package com.rm.retty.api.service;

import com.rm.retty.api.model.Account;
import com.rm.retty.api.model.User;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class MoneyServiceTest {
    @Test
    public void transfer() throws Exception {
        MoneyService sut = new MoneyService();
        Account fromAccount = new Account(new User("from"), "123Number", BigDecimal.valueOf(1000));
        Account toAccount = new Account(new User("to"), "123456Number", BigDecimal.valueOf(0));

        TransferResult actualResult = sut.transfer(fromAccount, toAccount);

        assertEquals(new TransferResult("Success"), actualResult);
    }
}