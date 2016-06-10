package com.github.mykhalechko.web.server;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileReaderFromWeb {



    public PrintWriter prepareResponse(String fileName, PrintWriter out) throws IOException {

        String response = readTextFile(fileName);
        byte[] utf8 = response.getBytes("UTF-8");
        int byteCount = utf8.length;

        String fileType = null;
        Pattern p = Pattern.compile("\\.(.+$)");
        Matcher m = p.matcher(fileName);

        if (m.find()) {
            fileType = (m.group(1));

        }

        System.out.println("fileType = " + fileType );

        out.print("HTTP/1.1 200 OK\r\n");
        out.print("Content-Length: " + byteCount + "\r\n");
        if (fileType.equalsIgnoreCase("css")) {
            out.print("Content-Type: text/css\r\n");
        }
        out.print("Content-Type: text/html\r\n");
        out.print("\r\n");
        out.print(response);
        return out;
    }




    public String readTextFile(String fileName) throws IOException {
        BufferedReader reader = null;

        fileName = checkFileName(fileName);
        fileName = "web/" + fileName;
        FileInputStream file = null;
        try {
            file = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            file = new FileInputStream("web/404.html");
        }

        reader = new BufferedReader(new InputStreamReader(file));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    public String checkFileName(String fileName) {
        if (fileName.equalsIgnoreCase("/")) {
            return "index.html";
        }
        return fileName.substring(1);
    }
}
