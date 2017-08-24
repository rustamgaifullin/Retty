package com.rm.retty.server;

import com.rm.retty.server.context.Context;
import com.rm.retty.server.context.ContextHolder;
import com.rm.retty.server.context.MethodInfo;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.rm.retty.server.Header.header;
import static com.rm.retty.server.MethodPath.methodPathFromStringRequest;
import static com.rm.retty.server.utils.Closer.close;
import static com.rm.retty.server.utils.Closer.flush;
import static com.rm.retty.server.utils.Logger.println;

public class RettyApplication {
    private final ExecutorService clientProcessingPool = Executors.newFixedThreadPool(10);
    private Thread threadServer = null;

    private final Config config;
    private final ContextHolder contextHolder;

    private ServerSocket listener;

    public RettyApplication(Config config, Context context) {
        this.config = config;
        this.contextHolder = new ContextHolder(context);
    }

    public void start() throws Exception {
        contextHolder.initialize();

        listener = new ServerSocket(config.getPort(), 1, InetAddress.getByName(config.getHost()));
        println(String.format("Start listener on %s host, %d port", config.getHost(), config.getPort()));

        Runnable runnable = () -> {
            while (!listener.isClosed()) {
                Socket socket = null;
                try {
                    socket = listener.accept();
                    clientProcessingPool.submit(new ClientTask(socket));
                } catch (IOException e) {
                    println("Closing socket");
                    close(socket);
                }
            }
        };

        threadServer = new Thread(runnable);
        threadServer.setName("Thread server");
        threadServer.start();
    }

    public void stop() throws InterruptedException, IOException {
        listener.close();
        clientProcessingPool.shutdown();
        clientProcessingPool.awaitTermination(10, TimeUnit.MINUTES);

        if (threadServer != null) threadServer.join();
        println("Server stopped successfully");
    }

    private class ClientTask implements Runnable {
        private final Headers headers = new Headers();
        private final Socket socket;

        private ClientTask(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            BufferedReader in = null;
            BufferedWriter out = null;
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                Boolean isFirstLine = true;
                String line;
                MethodPath methodPath = null;
                while ((line = in.readLine()) != null) {
                    println(line);
                    if (isFirstLine) {
                        methodPath = methodPathFromStringRequest(line);
                        isFirstLine = false;
                        continue;
                    }

                    if (line.isEmpty()) {
                        break;
                    }

                    headers.put(header(line));
                }

                String body = readBody(in);

                respond(out, methodPath, body);
                println("Everything closed");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                flush(out);
                close(in);
                close(out);
                close(socket);
            }
        }

        private String readBody(BufferedReader in) throws IOException {
            int contentLength = headers.getContentLenght();
            char[] content = new char[contentLength];
            in.read(content);
            return new String(content);
        }

        private void respond(BufferedWriter out, MethodPath methodPath, String body) throws IOException {
            Responder responder = new Responder(out);
            MethodInfo methodInfo = contextHolder.get(methodPath.getTypedPath());

            if (methodInfo == null) {
                responder.methodNotAllowed();
            } else {
                responder.successful(methodInfo, body);
            }
        }
    }
}