package com.rm.retty.server;

import java.util.HashMap;
import java.util.Map;

public class Headers {
    private final Map<String, Header> headerMap = new HashMap<>();

    public void put(Header header) {
        headerMap.put(header.getName(), header);
    }

    public int getContentLenght() {
        Header header = headerMap.get("Content-Length");

        int length = 0;

        if (header != null) {
            length = Integer.parseInt(header.getValue());
        }

        return  length;
    }
}