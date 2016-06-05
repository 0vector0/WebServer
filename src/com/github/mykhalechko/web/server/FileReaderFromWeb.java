package com.github.mykhalechko.web.server;

import java.io.*;

/**
 * Created by vector on 05.06.2016.
 */
public class FileReaderFromWeb {



    public String readFile(String fileName) throws IOException {
        BufferedReader reader = null;
        fileName = fileName.substring(1);
        fileName = "web\\" + fileName;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

}
