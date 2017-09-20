package com.rm.retty.api.service;

import com.rm.retty.api.model.Account;
import com.rm.retty.api.model.User;
import com.rm.retty.api.repository.AccountRepository;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static com.rm.retty.api.service.TransferResult.NOT_ENOUGH_MONEY;
import static com.rm.retty.api.service.TransferResult.SUCCESS;
import static com.rm.retty.api.service.TransferResult.ZERO_BALANCE;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
        verify(accountRepository, times(2)).save(any(Account.class));
        assertEquals(SUCCESS(), actualResult);
    }

    @Test
    public void should_not_transfer_money_with_zero_balance() throws Exception {
        //given
        MoneyService sut = new MoneyService(accountRepository);
        Account fromAccount = new Account(new User("Yoda"), "number0", BigDecimal.valueOf(0));
        Account toAccount = new Account(new User("Luke"), "number1", BigDecimal.valueOf(50));

        //when
        TransferResult actualResult = sut.transfer(fromAccount, toAccount, BigDecimal.valueOf(50));

        //then
        verifyZeroInteractions(accountRepository);
        assertEquals(ZERO_BALANCE(), actualResult);
    }

    @Test
    public void should_not_transfer_money_with_not_enough_money_on_account() throws Exception {
        //given
        MoneyService sut = new MoneyService(accountRepository);
        Account fromAccount = new Account(new User("Yoda"), "number0", BigDecimal.valueOf(30));
        Account toAccount = new Account(new User("Luke"), "number1", BigDecimal.valueOf(50));

        //when
        TransferResult actualResult = sut.transfer(fromAccount, toAccount, BigDecimal.valueOf(50));

        //then
        verifyZeroInteractions(accountRepository);
        assertEquals(NOT_ENOUGH_MONEY(), actualResult);
    }

    @Test
    public void should_transfer_successfully_when_balance_equal_to_transfer_amount() throws Exception {
        //given
        MoneyService sut = new MoneyService(accountRepository);
        Account fromAccount = new Account(new User("Yoda"), "number0", BigDecimal.valueOf(50));
        Account toAccount = new Account(new User("Luke"), "number1", BigDecimal.valueOf(0));

        //when
        TransferResult actualResult = sut.transfer(fromAccount, toAccount, BigDecimal.valueOf(50));

        //then
        verify(accountRepository, times(2)).save(any(Account.class));
        assertEquals(SUCCESS(), actualResult);
    }
}