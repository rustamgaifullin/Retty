package com.rm.retty.server.utils;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;

public class Closer {
    public static void close(Closeable closeable) {
        if (closeable == null) return;

        try {
            closeable.close();
        } catch (IOException ignored) {}
    }

    public static void flush(Flushable flushable) {
        if (flushable == null) return;

        try {
            flushable.flush();
        } catch (IOException ignored) {}
    }
}