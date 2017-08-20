package com.rm.retty.api.controller;

import com.rm.retty.api.SampleData;
import com.rm.retty.api.controller.request.TransferRequest;
import com.rm.retty.api.controller.request.UserRequest;
import com.rm.retty.api.model.Account;
import com.rm.retty.api.repository.AccountRepository;
import com.rm.retty.api.repository.UserRepository;
import com.rm.retty.api.service.MoneyService;
import com.rm.retty.api.service.TransferResult;
import com.rm.retty.api.service.UserService;
import com.rm.retty.server.Request;
import com.rm.retty.server.Response;
import com.rm.retty.server.annotations.Method;
import com.rm.retty.server.annotations.MethodType;
import com.rm.retty.server.annotations.Rest;

import java.util.Optional;

@Rest("/money")
public class MoneyController {

    private final MoneyService moneyService;
    private final UserService userService;
    private final MessageConverter<TransferRequest> messageConverter;

    //TODO: Shouldn't be here until context initializer will be smart enough.
    public MoneyController() {
        moneyService = new MoneyService(new AccountRepository(SampleData.getAccountList()));
        userService = new UserService(new UserRepository(SampleData.getUserList()));
        messageConverter = new MessageConverter<>();
    }

    public MoneyController(MoneyService moneyService, UserService userService, MessageConverter<TransferRequest> messageConverter) {
        this.moneyService = moneyService;
        this.userService = userService;
        this.messageConverter = messageConverter;
    }

    @Method(methodType = MethodType.POST, path = "/transfer")
    public Response transfer(Request request) {
        TransferRequest requestTransfer = messageConverter.fromJson(request.getBody(), TransferRequest.class);

        UserRequest fromUserRequest = requestTransfer.getFrom();
        Optional<Account> from = userService.getAccount(fromUserRequest.getName(), fromUserRequest.getAccountNumber());

        UserRequest toUserRequest = requestTransfer.getFrom();
        Optional<Account> to = userService.getAccount(toUserRequest.getName(), toUserRequest.getAccountNumber());

        if (from.isPresent() && to.isPresent()) {
            TransferResult transferResult = moneyService.transfer(from.get(), to.get(), requestTransfer.getAmount());

            return new Response(transferResult.getResult());
        }

        return new Response("Failed");
    }
}