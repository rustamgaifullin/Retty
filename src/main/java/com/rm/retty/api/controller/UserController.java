package com.rm.retty.api.controller;

import com.rm.retty.server.Request;
import com.rm.retty.server.Response;
import com.rm.retty.server.annotations.Method;
import com.rm.retty.server.annotations.Rest;

import static com.rm.retty.server.annotations.MethodType.GET;

@Rest("/user")
public class UserController {

    @Method(methodType = GET, path = "/balance")
    public Response userBalance(Request request) {
        return new Response("1000.00");
    }
}