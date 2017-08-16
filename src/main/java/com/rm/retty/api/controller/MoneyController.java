package com.rm.retty.api.controller;

import com.rm.retty.api.controller.request.UserRequest;
import com.rm.retty.api.service.MoneyService;
import com.rm.retty.container.Response;

import java.math.BigDecimal;

public class MoneyController {

    private final MoneyService moneyService;

    public MoneyController(MoneyService moneyService) {
        this.moneyService = moneyService;
    }

    public Response transfer(UserRequest fromUser, UserRequest toUser, BigDecimal amount) {
        throw new UnsupportedOperationException("not implemented");
    }
}