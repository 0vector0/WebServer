package com.github.mykhalechko.web.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerWeb {

    public static void main(String[] args) throws IOException {
        Socket client = null;
        System.out.println("server start");
        ServerSocket serverSocket = new ServerSocket(48000);

        while (true) {
            if (client == null) {
            client = serverSocket.accept();

            }
            if (client !=null) {
                new ServerImplementation(client).run();
            }
        }
    }
}