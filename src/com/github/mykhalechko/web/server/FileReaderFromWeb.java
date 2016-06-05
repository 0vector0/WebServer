package com.github.mykhalechko.web.server;

import java.io.*;

/**
 * Created by vector on 05.06.2016.
 */
public class FileReaderFromWeb {



    public String readFile(String fileName) throws IOException {
        BufferedReader reader = null;

        fileName = checkFileName(fileName);

        fileName = "web\\" + fileName;
        FileInputStream file = null;
        try {
            file = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
                file = new FileInputStream("web\\404.html");
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
