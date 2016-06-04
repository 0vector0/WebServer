package com.github.mykhalechko.examples;

public class Main {

    public static void main(String[] args) {

        String get = "GET /calc?operation=ADD&first=5&second=10 HTTP/1.1";
        String[] getArray = get.split(" ");
//        for (int i = 0; i < getArray.length ; i++) {
//            System.out.println(getArray[i]);
//        }
        
        System.out.println(getArray[1]);

        System.out.println( getArray[1].substring(0,5));
        System.out.println( getArray[1].substring(6,getArray[1].length()));
        String[] operationArray = getArray[1].substring(6,getArray[1].length()).split("&");

        String operation = operationArray[0].substring(10, operationArray[0].length());
        String first = operationArray[1].substring(6, operationArray[1].length());
        String second = operationArray[2].substring(7, operationArray[2].length());
        System.out.println(operation);
        System.out.println(first);
        System.out.println(second);



    }
}
