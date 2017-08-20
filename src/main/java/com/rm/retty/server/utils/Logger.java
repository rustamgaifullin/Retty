package com.rm.retty.server.utils;

public class Logger {
    public static void println(String msg) {
        String name = Thread.currentThread().getName();
        System.out.println(String.format("%s: %s", name, msg));
    }

    public static void print(String msg) {
        String name = Thread.currentThread().getName();
        System.out.print(String.format("%s: %s", name, msg));
    }
}