package com.rm.retty.api.model;

import java.math.BigDecimal;

public class Account {
    private final User user;
    private final String number;
    private final BigDecimal balance;

    public Account(User user, String number, BigDecimal balance) {
        this.user = user;
        this.number = number;
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public String getNumber() {
        return number;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (user != null ? !user.equals(account.user) : account.user != null) return false;
        if (number != null ? !number.equals(account.number) : account.number != null) return false;
        return balance != null ? balance.equals(account.balance) : account.balance == null;
    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Account{" +
                "user=" + user +
                ", number='" + number + '\'' +
                ", balance=" + balance +
                '}';
    }
}