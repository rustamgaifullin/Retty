package com.rm.retty.container;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class RettyApplication {

    private ServerSocket listener;
    private Socket socket;

    private Boolean interrupt = false;

    private final Config config;

    public RettyApplication(Config config) {
        this.config = config;
    }

    public void start() {
        new Thread(() -> {
            try {
                listener = new ServerSocket(config.getPort(), 5, InetAddress.getByName(config.getHost()));
                System.out.println(String.format("Start listener on %s host, %d port", config.getHost(), config.getPort()));
                while (true) {
                    if (interrupt) {
                        break;
                    }

                    socket = listener.accept();
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    String line;
                    while ((line = in.readLine()) != null) {
                        //read request
                    }

                    //write to out response

                    out.close();
                    in.close();
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void stop() {
        interrupt = true;
    }
}