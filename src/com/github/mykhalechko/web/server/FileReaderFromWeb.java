package com.github.mykhalechko.web.server;

import java.io.*;

public class FileReaderFromWeb {

    public String readFile(String fileName) throws IOException {
        BufferedReader reader = null;

        fileName = checkFileName(fileName);

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
            return "web/index.html";
        }
        return fileName.substring(1);
    }
}
