package com.rm.retty.api.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final String name;
    private final List<Account> accountList = new ArrayList<>();

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void addAccount(Account newAccount) {
        accountList.add(newAccount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        return accountList != null ? accountList.equals(user.accountList) : user.accountList == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (accountList != null ? accountList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", accountList=" + accountList +
                '}';
    }
}

