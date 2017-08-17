package com.rm.retty.api.controller.request;

import java.math.BigDecimal;

public class TransferRequest {
    private final UserRequest from;
    private final UserRequest to;
    private final BigDecimal amount;

    public TransferRequest(UserRequest from, UserRequest to, BigDecimal amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    public UserRequest getFrom() {
        return from;
    }

    public UserRequest getTo() {
        return to;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransferRequest that = (TransferRequest) o;

        if (from != null ? !from.equals(that.from) : that.from != null) return false;
        if (to != null ? !to.equals(that.to) : that.to != null) return false;
        return amount != null ? amount.equals(that.amount) : that.amount == null;
    }

    @Override
    public int hashCode() {
        int result = from != null ? from.hashCode() : 0;
        result = 31 * result + (to != null ? to.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        return result;
    }
}
