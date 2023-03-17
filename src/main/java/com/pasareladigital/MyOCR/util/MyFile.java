package com.pasareladigital.MyOCR.util;

import java.io.*;

public class MyFile {
    public static void StreamToFile(InputStream input, File file) throws IOException {

        FileOutputStream outputStream = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = input.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        input.close();
        outputStream.close();
    }
}
