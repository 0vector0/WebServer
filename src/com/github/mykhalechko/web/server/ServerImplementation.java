package com.github.mykhalechko.web.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServerImplementation implements Runnable {

    Socket socket = null;

    public ServerImplementation(Socket socket) throws IOException {
        if (this.socket == null) {
        this.socket = socket;
        System.out.println("Connected");
        }

    }

    @Override
    public void run() {
        try {
            String request = parsingRequestMessage(socket);

            String response = prepareResponse(request);
            serverAnswer(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String parsingRequestMessage(Socket socket) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        // String get = "GET /home.html HTTP/1.1";
        String requestMessage = in.readLine();
        Pattern p = Pattern.compile("GET\\s(.*?)\\sHTTP");
        Matcher m = p.matcher(requestMessage);
        if (m.find()) {
            System.out.println(m.group(1));
            return m.group(1);
        }
        // /home.html
        return "/web/404.html";
    }

    public String prepareResponse(String request) throws IOException {

        if (request.length() > 5 && request.substring(0, 5).equalsIgnoreCase("/calc")) {
            return new CalculatorWeb().calculation(request);
        }

        return  new FileReaderFromWeb().readFile(request);
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


}
