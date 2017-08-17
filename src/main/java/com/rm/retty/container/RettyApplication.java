package com.rm.retty.container;

import com.rm.retty.container.annotations.MethodType;
import com.rm.retty.container.annotations.Rest;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RettyApplication {
    final ExecutorService clientProcessingPool = Executors.newFixedThreadPool(10);

    private HashMap<String, Responder> responderMap = new HashMap<>();
    private Thread threadServer = null;


    private final Config config;
    private final Context context;

    public RettyApplication(Config config, Context context) {
        this.config = config;
        this.context = context;
    }

    public void start() {
        initializeContext();

        Runnable runnable = () -> {
            try (ServerSocket listener = new ServerSocket(config.getPort(), 1, InetAddress.getByName(config.getHost()))) {
                System.out.println(String.format("Start listener on %s host, %d port", config.getHost(), config.getPort()));
                while (!listener.isClosed()) {
                    Socket socket = listener.accept();
                    clientProcessingPool.submit(new SocketTask(socket));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        threadServer = new Thread(runnable);
        threadServer.start();
    }

    private void initializeContext() {
        System.out.println("Initializing context");

        context.getClassList().forEach(clazz -> {
            try {
                Object newObject = clazz.newInstance();
                Annotation restAnnotation = clazz.getAnnotation(Rest.class);
                Method getValueMethod = restAnnotation.annotationType().getDeclaredMethod("value");
                String controllerPath = (String) getValueMethod.invoke(restAnnotation);

                for (Method method : clazz.getDeclaredMethods()) {
                    if (method.isAnnotationPresent(com.rm.retty.container.annotations.Method.class)) {
                        Annotation methodAnnotation = method.getAnnotation(com.rm.retty.container.annotations.Method.class);
                        Method getType = methodAnnotation.annotationType().getDeclaredMethod("methodType");
                        MethodType methodType = (MethodType) getType.invoke(methodAnnotation);

                        Method getPath = methodAnnotation.annotationType().getDeclaredMethod("path");
                        String methodPath = (String) getPath.invoke(methodAnnotation);

                        Responder responder = new Responder(clazz, method, newObject);
                        String fullPath = String.format("%s %s%s HTTP/1.1", methodType.name(), controllerPath, methodPath);
                        responderMap.put(fullPath, responder);
                    }
                }

            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException("Could not initialize context", e);
            } catch (NoSuchMethodException | InvocationTargetException e) {
                System.out.println("Could not find or invoke method");
            }
        });
    }

    public void stop() throws InterruptedException {
        if (threadServer != null) threadServer.interrupt();
    }

    private class SocketTask implements Runnable {
        private final Socket socket;

        private SocketTask(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                Boolean isFirstLine = true;
                Boolean isContent = false;
                String line;
                String fullPath = "";
                String body = "";
                while ((line = in.readLine()) != null) {

                    System.out.println(line);
                    if (isFirstLine) {
                        fullPath = line;
                        isFirstLine = false;
                        continue;
                    }

                    if (line.isEmpty()) {
                        //Somehow I should send some status before reading request body.
                        out.write(String.format(Responder.statusLine, 200, "Continue"));
                        isContent = true;
                        continue;
                    }

                    if (isContent) {
                        body = line;
                    }
                }

                System.out.println(fullPath);
                System.out.println(body);

                respond(out, fullPath, body);

                out.close();
                in.close();
                socket.close();

                System.out.println("Everything closed");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void respond(BufferedWriter out, String fullPath, String body) throws IOException {
            Responder responder = responderMap.get(fullPath);

            if (responder == null) {
                System.out.println("400 Bad request");
                out.write(String.format(Responder.statusLine, 400, "Bad request"));
            } else {
                responder.respond(out, body);
            }
        }
    }
}