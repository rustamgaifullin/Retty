package com.rm.retty.api.controller;

import com.rm.retty.api.controller.request.TransferRequest;
import com.rm.retty.api.controller.request.UserRequest;
import com.rm.retty.api.service.MoneyService;
import com.rm.retty.api.service.TransferResult;
import com.rm.retty.server.Request;
import com.rm.retty.server.Response;
import org.junit.Test;
import org.mockito.Mock;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class MoneyControllerTest {

    @Mock
    private MoneyService moneyService;

    @Test
    public void should_be_able_to_respond() {
        //given
        MoneyController moneyController = new MoneyController(moneyService);
        UserRequest fromUserRequest = new UserRequest("Yoda", "AccountIsYodaNumber123");
        UserRequest toUserRequest = new UserRequest("Luke", "theforce123");
        TransferRequest transferRequest = new TransferRequest(fromUserRequest, toUserRequest, BigDecimal.valueOf(500));

        Response expectedResponse = new Response("Success");

        //when
        when(moneyService.transfer(any(), any(), any())).thenReturn(new TransferResult("Success"));

        Response actualResponse = moneyController.transfer(new Request(transferRequest.toString()));

        //then
        assertEquals(expectedResponse, actualResponse);
    }
}