package com.rm.retty.api.service;

public class TransferResult {
    private final String result;

    public TransferResult(String result) {
        this.result = result;
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

