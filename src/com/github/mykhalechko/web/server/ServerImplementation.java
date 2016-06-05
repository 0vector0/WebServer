package com.github.mykhalechko.web.server;

import com.sun.corba.se.impl.protocol.giopmsgheaders.RequestMessage_1_0;
import com.sun.deploy.nativesandbox.comm.Response;

import java.io.*;
import java.net.Socket;

/**
 * Created by vector on 05.06.2016.
 */
public class ServerImplementation {

    Socket socket = null;


    public ServerImplementation(Socket socket) throws IOException {
        this.socket = socket;
        System.out.println("Connected");
    }

    public void otvet() throws IOException {
        String request = parsingRequestMessage(socket);
        String response = prepareResponse(request);
        serverAnswer(response);
    }


    public void serverAnswer(String response) throws IOException {
        byte[] utf8 = response.getBytes("UTF-8");
        int byteCount = utf8.length;
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        out.print("HTTP/1.1 200 OK\r\n");
        out.print("Content-Length: " + byteCount + "\r\n");
        out.print("Content-Type: text/html\r\n");
        out.print("\r\n");
        out.print(response);
        out.flush();
    }

    public String parsingRequestMessage(Socket socket) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        // String get = "GET /home.html HTTP/1.1";
        String[] getArray = in.readLine().split(" ");
        // /home.html
        return getArray[1];

    }

    public String prepareResponse(String request)  {
        String response = null;

        if (request.equalsIgnoreCase("/index.html")) {
            response = "<html>\n" +
                    "<body>\n" +
                    "<h1>index</h1>\n" +
                    "</body>\n" +
                    "</html>" + "\r\n";
        }
        if (request.equalsIgnoreCase("/home.html")) {
            response = "<html>\n" +
                    "<body>\n" +
                    "<h1>home</h1>\n" +
                    "</body>\n" +
                    "</html>" + "\r\n";
        }



        return response;
    }
}
