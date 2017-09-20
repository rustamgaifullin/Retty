package com.rm.retty.api.service;

public class TransferResult {
    private final String result;

    private TransferResult(String result) {
        this.result = result;
    }

    public static TransferResult SUCCESS() {
        return new TransferResult("Success");
    }

    public static TransferResult ZERO_BALANCE() {
        return new TransferResult("Zero balance on account");
    }

    public static TransferResult NOT_ENOUGH_MONEY() {
        return new TransferResult("Not enough money");
    }

    public String getResult() {
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransferResult that = (TransferResult) o;

        return result != null ? result.equals(that.result) : that.result == null;
    }

    @Override
    public int hashCode() {
        return result != null ? result.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TransferResult{" +
                "result='" + result + '\'' +
                '}';
    }
}

