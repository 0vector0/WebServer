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
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            if (request.length() > 5 && request.substring(0, 5).equalsIgnoreCase("/calc")) {
                out = new CalculatorWeb().calculation(request, out);
                out.flush();
            } else {
                out = new FileReaderFromWeb().prepareResponse(request, out);
                out.flush();
            }
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
        return "/";
    }
}
