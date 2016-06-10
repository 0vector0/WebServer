package com.github.mykhalechko.web.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculatorWeb {

    public PrintWriter calculation(String request, PrintWriter out) throws IOException {



        System.out.println(request);

//        String[] operationArray = request.substring(6,request.length()).split("&");
//        String operation = operationArray[0].substring(10, operationArray[0].length());
//        String first = operationArray[1].substring(6, operationArray[1].length());
//        String second = operationArray[2].substring(7, operationArray[2].length());

        Pattern p = Pattern.compile("first-number=(.*)&second-number=(.*)&");
        Matcher m = p.matcher(request);
        String first = null;
        String second = null;

        if (m.find()) {
            first = m.group(1);
            second = m.group(2);
        }
        String resultString = "";
        if (first!= null && second!= null) {
            int result = Integer.parseInt(first) + Integer.parseInt(second);
            resultString = first + " + " + second + " = " + result;
        }

        String  response = new FileReaderFromWeb().readTextFile("/calculation.html");
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
