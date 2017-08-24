package com.rm.retty.server;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class MethodParameters {
    private final Map<String, String> parametersMap = new HashMap<>();

    private MethodParameters() {
    }

    public static MethodParameters noParameters() {
        return new MethodParameters();
    }

    public static MethodParameters parseFromString(String parameters) {
        MethodParameters methodParameters = new MethodParameters();

        StringTokenizer stringTokenizer = new StringTokenizer(parameters, "&");

        while (stringTokenizer.hasMoreTokens()) {
            String pair = stringTokenizer.nextToken();

            StringTokenizer pairTokenizer = new StringTokenizer(pair, "=");
            String key = pairTokenizer.nextToken();
            String value = pairTokenizer.nextToken();

            methodParameters.add(key, value);
        }

        return methodParameters;
    }

    private void add(String key, String value) {
        parametersMap.put(key, value);
    }

    public String value(String key) {
        return parametersMap.get(key);
    }
}