package com.rm.retty.server;

import org.junit.Test;

import static com.rm.retty.server.MethodPath.methodPathFromStringRequest;
import static com.rm.retty.server.MethodType.GET;
import static org.junit.Assert.*;

public class MethodPathTest {

    @Test
    public void should_be_able_to_parse_from_string() {
        //given
        String firstLine = "GET /user/balance?name=Yoda&accountNumber=number0 HTTP/1.1";

        //when
        MethodPath methodPath = methodPathFromStringRequest(firstLine);

        //then
        assertEquals(GET, methodPath.getType());
        assertEquals("/user/balance", methodPath.getPath());
        assertEquals("Yoda", methodPath.getParameters().value("name"));
        assertEquals("number0", methodPath.getParameters().value("accountNumber"));
    }
}