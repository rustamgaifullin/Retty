package com.rm.retty.api.controller.request;

public class UserRequest {
    private final String name;
    private final String accountNumber;

    public UserRequest(String name, String accountNumber) {
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

        UserRequest userRequest = (UserRequest) o;

        if (name != null ? !name.equals(userRequest.name) : userRequest.name != null) return false;
        return accountNumber != null ? accountNumber.equals(userRequest.accountNumber) : userRequest.accountNumber == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (accountNumber != null ? accountNumber.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserRequest{" +
                "name='" + name + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                '}';
    }
}