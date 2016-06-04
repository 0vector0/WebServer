package com.github.mykhalechko.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by vector on 04.06.2016.
 */
public class Client {


    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost", 48000);
        BufferedReader  consoleInput = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader  in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter  out = new PrintWriter(socket.getOutputStream(), true);

        while (true)
            try {
                out.println("from client " + consoleInput.readLine());

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

    }


}
