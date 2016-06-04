package com.github.mykhalechko.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by vector on 04.06.2016.
 */
public class Server {

    public static void main(String[] args) throws IOException {
        Socket client = null;
        Socket client2 = null;
        System.out.println("server start");

        ServerSocket serverSocket = new ServerSocket(48000);


            client = serverSocket.accept();

                    System.out.println("Connected");
                    BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

                    while (true) {
                        String message = in.readLine();
                        System.out.println("message:" + message);
                    }
                }





}