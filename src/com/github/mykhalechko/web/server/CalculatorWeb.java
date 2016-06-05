package com.github.mykhalechko.web.server;

public class CalculatorWeb {

    public String calculation(String request) {

        String[] operationArray = request.substring(6,request.length()).split("&");
        String operation = operationArray[0].substring(10, operationArray[0].length());
        String first = operationArray[1].substring(6, operationArray[1].length());
        String second = operationArray[2].substring(7, operationArray[2].length());

        int result = Integer.parseInt(first) + Integer.parseInt(second);
        String resultString = first + " + " + second + " = " + result;
        String  response = "<html>\n" +
                "<body>\n" +
                "<h1>"+ resultString + "</h1>\n" +
                "</body>\n" +
                "</html>" + "\r\n";
        return response;
    }
}
