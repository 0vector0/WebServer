package com.github.mykhalechko.web.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
        if (response.equalsIgnoreCase("")) {
            out.print("HTTP/1.1 404 Not Found\r\n");
            out.print("Content-Length: " + 40 + "\r\n");
            out.print("Content-Type: text/html\r\n");
            out.print("\r\n");
            out.print("HTTP/1.1 404 Not Found\r\n");
            out.flush();
        } else {
            out.print("HTTP/1.1 200 OK\r\n");
            out.print("Content-Length: " + byteCount + "\r\n");
            out.print("Content-Type: text/html\r\n");
            out.print("\r\n");
            out.print(response);
            out.flush();
        }


    }

    public String parsingRequestMessage(Socket socket) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        // String get = "GET /home.html HTTP/1.1";
        String requestMessage = in.readLine();
        String[] getArray = {"GET", "/", "HTTP/1.1"};
        if (requestMessage != null && requestMessage.contains(" ")) {
            getArray = requestMessage.split(" ");
        }

        // /home.html
        return getArray[1];

    }

    public String prepareResponse(String request) throws IOException {
        String response = "";
        FileReaderFromWeb fileReader = new FileReaderFromWeb();
        if (request.equalsIgnoreCase("/")) {
            response = fileReader.readFile("/index.html");
        }

        if (request.equalsIgnoreCase("/index.html")) {
            response = fileReader.readFile("/index.html");
        }
        if (request.equalsIgnoreCase("/home.html")) {
            response = "<html>\n" +
                    "<body>\n" +
                    "<h1>home</h1>\n" +
                    "</body>\n" +
                    "</html>" + "\r\n";
        }

        if (request.length() > 5 && request.substring(0, 5).equalsIgnoreCase("/calc")) {
            response = new CalculatorWeb().calculation(request);
        }


        return response;
    }


}
