package com.rm.retty.api.controller;

import com.rm.retty.api.SampleData;
import com.rm.retty.api.repository.AccountRepository;
import com.rm.retty.api.service.MoneyService;
import com.rm.retty.server.Request;
import com.rm.retty.server.Response;
import com.rm.retty.server.annotations.Method;
import com.rm.retty.server.annotations.MethodType;
import com.rm.retty.server.annotations.Rest;

@Rest("/money")
public class MoneyController {

    private final MoneyService moneyService;

    public MoneyController() {
        moneyService = new MoneyService(new AccountRepository(SampleData.getAccountList()));
    }

    public MoneyController(MoneyService moneyService) {
        this.moneyService = moneyService;
    }

    @Method(methodType = MethodType.POST, path = "/transfer")
    public Response transfer(Request request) {
        return new Response("Success");
    }
}