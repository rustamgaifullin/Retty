package com.rm.retty.server;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static com.rm.retty.server.utils.Logger.print;
import static com.rm.retty.server.utils.Logger.println;

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
            Request request = new Request(body);
            Response response = (Response) method.invoke(object, request);

            println("Got response from a controller " + response.getBody());
            println("writing to output stream");

            writeAndLog(out, String.format(statusLine, 200, "OK"));
            writeAndLog(out,String.format("Content-Length: %d\n", response.getBody().getBytes().length));
            writeAndLog(out,String.format("Content-Type: %s\n", "text/plain"));
            writeAndLog(out,"\r\n");
            writeAndLog(out,response.getBody());
            writeAndLog(out,"\n");

            println("done writing to output stream");

        } catch (IllegalAccessException | InvocationTargetException e) {
            out.write(String.format(statusLine, 500, "Internal Server Error"));
            e.printStackTrace();
        }
    }

    private void writeAndLog(BufferedWriter out, String string) throws IOException {
        out.write(string);
        print(string);
    }
}