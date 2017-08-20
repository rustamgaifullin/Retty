package com.rm.retty.api.service;

import com.rm.retty.api.model.Account;
import com.rm.retty.api.repository.AccountRepository;

import java.math.BigDecimal;

import static com.rm.retty.api.service.TransferResult.SUCCESS;

public class MoneyService {
    private final AccountRepository accountRepository;

    public MoneyService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public TransferResult transfer(Account from, Account to, BigDecimal amount) {
        BigDecimal fromBalance = from.getBalance();
        Account newFromAccount = from.accountWithNewBalance(fromBalance.subtract(amount));

        BigDecimal toBalance = to.getBalance();
        Account newToAccount = to.accountWithNewBalance(toBalance.subtract(amount));

        accountRepository.save(newFromAccount);
        accountRepository.save(newToAccount);

        return SUCCESS();
    }
}