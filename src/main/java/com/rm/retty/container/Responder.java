package com.rm.retty.container;

import com.google.gson.Gson;
import com.rm.retty.container.annotations.Json;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class Responder {
    public static final String statusLine = "HTTP/1.1 %d %s\r\n";

    private final Class claszz;
    private final Method method;
    private final Object object;

    public Responder(Class claszz, Method method, Object object) {
        this.claszz = claszz;
        this.method = method;
        this.object = object;
    }

    public void respond(BufferedWriter out, String body) throws IOException {
        try {
            Object methodParameter = null;
            for (Parameter parameter : method.getParameters()) {
                if (parameter.isAnnotationPresent(Json.class)) {
                    Class<?> type = parameter.getType();

                    Gson gson = new Gson();
                    methodParameter = gson.fromJson(body, type);
                }
            }

            Response response = (Response) method.invoke(object, methodParameter);

            System.out.println("Got response " + response.getBody());

            System.out.println("writing to output stream");
            out.write(String.format(statusLine, response.getCode(), "OK"));
            out.write(String.format("Content-Length: %d\r\n", response.getBody().getBytes().length));
            out.write(String.format("Content-Type: %s\r\n", "application/json"));
            out.write("\r\n");
            out.write(response.getBody());
            System.out.println("done writing to output stream");

        } catch (IllegalAccessException | InvocationTargetException e) {
            out.write(String.format(statusLine, 500, "Internal Server Error"));
            e.printStackTrace();
        }
    }
}
