package com.github.mykhalechko.web.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by vector on 04.06.2016.
 */
public class ServerWeb {

    public static void main(String[] args) throws IOException {
        Socket client = null;
        System.out.println("server start");
        ServerSocket serverSocket = new ServerSocket(48000);

        while (true) {
            client = serverSocket.accept();
            ServerImplementation serverImplementation = new ServerImplementation(client);
            serverImplementation.answer();
        }
    }
}