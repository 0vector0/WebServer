package com.github.mykhalechko.web.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculatorWeb {

    public PrintWriter calculation(String request, PrintWriter out) throws IOException {

        System.out.println(request);

        Pattern p = Pattern.compile("first-number=(.*)&second-number=(.*)&");
        Matcher m = p.matcher(request);
        String first = null;
        String second = null;

        if (m.find()) {
            first = m.group(1);
            second = m.group(2);
        }
        String resultString = "";
        if (first != null && second != null) {
            int result = Integer.parseInt(first) + Integer.parseInt(second);
            resultString = first + " + " + second + " = " + result;
        }

        String response = new FileReaderFromWeb().readTextFile("/calculation.html");
        response = response.replaceAll("<!--CALCULATION-->", resultString);
        byte[] utf8 = response.getBytes("UTF-8");
        int byteCount = utf8.length;
        out.print("HTTP/1.1 200 OK\r\n");
        out.print("Content-Length: " + byteCount + "\r\n");
        out.print("Content-Type: text/html\r\n");
        out.print("\r\n");
        out.print(response);
        return out;
    }
}
