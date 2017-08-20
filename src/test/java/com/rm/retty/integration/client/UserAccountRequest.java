package com.rm.retty.integration.client;

public class UserAccountRequest {
    private final String name;
    private final String accountNumber;

    public UserAccountRequest(String name, String accountNumber) {
        this.name = name;
        this.accountNumber = accountNumber;
    }

    public String getName() {
        return name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserAccountRequest that = (UserAccountRequest) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return accountNumber != null ? accountNumber.equals(that.accountNumber) : that.accountNumber == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (accountNumber != null ? accountNumber.hashCode() : 0);
        return result;
    }
}