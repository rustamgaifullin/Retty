package com.rm.retty.server;

import org.junit.Test;

import static org.junit.Assert.*;

public class MethodParametersTest {

    @Test
    public void should_be_able_to_parse_from_string() {
        //given
        String url = "key0=value0&key1=value1&key2=value2";

        //when
        MethodParameters methodParameters = MethodParameters.parseFromString(url);

        //then

        assertEquals("value0", methodParameters.value("key0"));
        assertEquals("value1", methodParameters.value("key1"));
        assertEquals("value2", methodParameters.value("key2"));
    }
}