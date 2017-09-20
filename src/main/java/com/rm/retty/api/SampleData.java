package com.rm.retty.api;

import com.rm.retty.api.model.Account;
import com.rm.retty.api.model.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SampleData {
    private static final List<User> userList = new ArrayList<>();
    private static final List<Account> accountList = new ArrayList<>();

    static {
        initUsers();
        initAccounts();
    }

    private static void initUsers() {
        userList.add(new User("Yoda"));
        userList.add(new User("Luke"));
    }

    private static void initAccounts() {
        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            Account account = new Account(user, String.format("number%d", i), BigDecimal.valueOf(1000));
            accountList.add(account);
            user.addAccount(account);
        }
    }

    public static List<User> getUserList() {
        return userList;
    }

    public static List<Account> getAccountList() {
        return accountList;
    }
}