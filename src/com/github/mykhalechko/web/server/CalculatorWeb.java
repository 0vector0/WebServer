package com.github.mykhalechko.web.server;

/**
 * Created by vector on 05.06.2016.
 */
public class CalculatorWeb {

    public String calculation(String request) {
        System.out.println(request);
        String  response = "<html>\n" +
                "<body>\n" +
                "<h1>calc 111111111111111</h1>\n" +
                "</body>\n" +
                "</html>" + "\r\n";
        return response;
    }
}
