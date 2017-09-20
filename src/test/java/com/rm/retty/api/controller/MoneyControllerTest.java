package com.rm.retty.api.controller;

import com.rm.retty.api.controller.request.TransferRequest;
import com.rm.retty.api.controller.request.UserRequest;
import com.rm.retty.api.model.Account;
import com.rm.retty.api.service.MoneyService;
import com.rm.retty.api.service.TransferResult;
import com.rm.retty.api.service.UserService;
import com.rm.retty.server.Request;
import com.rm.retty.server.Response;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static com.rm.retty.api.service.TransferResult.SUCCESS;
import static java.math.BigDecimal.ZERO;
import static java.util.Optional.of;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MoneyControllerTest {

    private MoneyService moneyService = mock(MoneyService.class);
    private UserService userService = mock(UserService.class);
    private MessageConverter<TransferRequest> messageConverter = mock(MessageConverter.class);

    private UserRequest emptyRequest = new UserRequest("", "");
    private TransferRequest transferRequest = new TransferRequest(emptyRequest, emptyRequest, ZERO);

    @Before
    public void setup() {
        when(messageConverter.fromJson(anyString(), any())).thenReturn(transferRequest);
    }

    @Test
    public void should_respond_with_successful_result() {
        //given
        MoneyController moneyController = new MoneyController(moneyService, userService, messageConverter);
        Optional<Account> account = of(new Account(null, null, null));

        //when
        when(userService.getAccount(anyString(), anyString())).thenReturn(account);
        when(moneyService.transfer(any(), any(), any())).thenReturn(SUCCESS());

        Response actualResponse = moneyController.transfer(new Request(transferRequest.toString()));

        //then
        assertEquals(successfullResponse(), actualResponse);
    }

    @Test
    public void should_respond_with_failed_result() {
        //given
        MoneyController moneyController = new MoneyController(moneyService, userService, messageConverter);
        Optional<Account> account = Optional.empty();

        //when
        when(userService.getAccount(anyString(), anyString())).thenReturn(account);
        when(moneyService.transfer(any(), any(), any())).thenReturn(SUCCESS());

        Response actualResponse = moneyController.transfer(new Request(transferRequest.toString()));

        //then
        assertEquals(failedResponse(), actualResponse);
    }

    private Response successfullResponse() {
        return new Response("Success");
    }

    private Response failedResponse() {
        return new Response("Failed");
    }
}