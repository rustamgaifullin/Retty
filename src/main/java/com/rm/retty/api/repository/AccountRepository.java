package com.rm.retty.api.repository;

import com.rm.retty.api.model.Account;

import java.util.List;

public class AccountRepository extends AbstractRepository<String, Account> {
    public AccountRepository(List<Account> accounts) {
        super(accounts);
    }
}