package com.github.mykhalechko.web.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
        int count = 0;


        while (true) {
            client = serverSocket.accept();

            System.out.println("Connected");
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            // String get = "GET /home.html HTTP/1.1";
            String message = null;
            String[] getArray = in.readLine().split(" ");

            if (getArray[1].equalsIgnoreCase("/index.html")) {
                message = "<html>\n" +
                        "<body>\n" +
                        "<h1>index.html</h1>\n" +
                        "<p>" + count + "\n" +
                        "</body>\n" +
                        "</html>" + "\r\n";
            }
            if (getArray[1].equalsIgnoreCase("/home.html")) {
                message = "<html>\n" +
                        "<body>\n" +
                        "<h1>home.html</h1>\n" +
                        "<p>" + count + "\n" +
                        "</body>\n" +
                        "</html>" + "\r\n";
            }

            byte[] utf8 = message.getBytes("UTF-8");
            int byteCount = utf8.length;

            PrintWriter out = new PrintWriter(client.getOutputStream());
            out.print("HTTP/1.1 200 OK\r\n");
            out.print("Content-Length: " + byteCount + "\r\n");
            out.print("Content-Type: text/html\r\n");
            out.print("\r\n");
            out.print(message);
            out.flush();
            count++;
//                        String message = in.readLine();
//                        System.out.println("message:" + message);

        }
    }


}