package com.rm.retty.api.service;

import com.rm.retty.api.model.Account;
import com.rm.retty.api.repository.AccountRepository;

import java.math.BigDecimal;

import static com.rm.retty.api.service.TransferResult.NOT_ENOUGH_MONEY;
import static com.rm.retty.api.service.TransferResult.SUCCESS;
import static com.rm.retty.api.service.TransferResult.ZERO_BALANCE;
import static java.math.BigDecimal.ZERO;

public class MoneyService {
    private final AccountRepository accountRepository;

    public MoneyService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public TransferResult transfer(Account from, Account to, BigDecimal amount) {
        if (from.getBalance().equals(ZERO)) return ZERO_BALANCE();
        if (from.getBalance().compareTo(amount) < 0) return  NOT_ENOUGH_MONEY();

        makeTransfer(from, to, amount);

        return SUCCESS();
    }

    private void makeTransfer(Account from, Account to, BigDecimal amount) {
        BigDecimal fromBalance = from.getBalance();
        Account newFromAccount = from.accountWithNewBalance(fromBalance.subtract(amount));

        BigDecimal toBalance = to.getBalance();
        Account newToAccount = to.accountWithNewBalance(toBalance.subtract(amount));

        accountRepository.save(newFromAccount);
        accountRepository.save(newToAccount);
    }
}