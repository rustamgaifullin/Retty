package com.rm.retty.integration.client;

public class UserAccountRequest {
    private final String userName;
    private final String accountNumber;

    public UserAccountRequest(String userName, String accountNumber) {
        this.userName = userName;
        this.accountNumber = accountNumber;
    }

    public String getUserName() {
        return userName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserAccountRequest that = (UserAccountRequest) o;

        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        return accountNumber != null ? accountNumber.equals(that.accountNumber) : that.accountNumber == null;
    }

    @Override
    public int hashCode() {
        int result = userName != null ? userName.hashCode() : 0;
        result = 31 * result + (accountNumber != null ? accountNumber.hashCode() : 0);
        return result;
    }
}
