package com.rm.retty.server;

import com.rm.retty.server.context.MethodInfo;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static com.rm.retty.server.utils.Logger.print;
import static com.rm.retty.server.utils.Logger.println;

public class Responder {
    private static final String statusLine = "HTTP/1.1 %d %s\r\n";

    private final BufferedWriter out;

    public Responder(BufferedWriter out) {
        this.out = out;
    }

    public void successful(MethodInfo methodInfo, String body) throws IOException {
        try {
            Method method = methodInfo.getMethod();
            Object object = methodInfo.getObject();

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
            error(500, "Internal server error");
            e.printStackTrace();
        }
    }

    public void methodNotAllowed() throws IOException {
        error(405, "Method not allowed");
    }

    private void error(int code, String message) throws IOException {
        writeAndLog(out, String.format(statusLine, code, message));
        writeAndLog(out, "\r\n");
    }

    private void writeAndLog(BufferedWriter out, String string) throws IOException {
        out.write(string);
        print(string);
    }
}