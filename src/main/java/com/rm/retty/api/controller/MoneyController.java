package com.rm.retty.api.controller;

import com.rm.retty.api.controller.request.TransferRequest;
import com.rm.retty.api.service.MoneyService;
import com.rm.retty.container.Response;
import com.rm.retty.container.annotations.Json;
import com.rm.retty.container.annotations.Method;
import com.rm.retty.container.annotations.MethodType;
import com.rm.retty.container.annotations.Rest;

@Rest("/money")
public class MoneyController {

    private final MoneyService moneyService;

    public MoneyController() {
        moneyService = new MoneyService();
    }

    public MoneyController(MoneyService moneyService) {
        this.moneyService = moneyService;
    }

    @Method(methodType = MethodType.POST, path = "/transfer")
    public Response transfer(@Json TransferRequest transferRequest) {
        return new Response("Success", 200);
    }
}