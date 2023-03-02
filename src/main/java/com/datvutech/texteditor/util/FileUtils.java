package com.datvutech.texteditor.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {
    public static boolean saveNewOrOverrideTextFile(String fileName, String textData) {
        Path path = Paths.get(fileName);
        try {
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
            File file = path.toFile();
            FileWriter fWriter = new FileWriter(file);
            fWriter.write(textData);
            fWriter.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static String getTextData(String fileName) {
        File file = new File(fileName);
        try (FileReader fReader = new FileReader(file);
                BufferedReader bReader = new BufferedReader(fReader);) {
            StringBuilder textData = new StringBuilder();
            String data = "";
            while ((data = bReader.readLine()) != null) {
                textData.append(data + "\n");
            }
            return textData.toString();
        } catch (IOException e) {
            return "";
        }
    }

    public static float getFileSizeKb(String fileName) {
        float sizeKb = 0F;
        Path path = Paths.get(fileName);
        if (Files.exists(path)) {
            try {
                sizeKb = Files.size(path) / 1024F;
            } catch (IOException e) {
                return sizeKb;
            }
        }
        return sizeKb;
    }

    public static String getEncoding(String fileName) {
        String encoding = "";
        try {
            Path path = Paths.get(fileName);
            if (Files.exists(path)) {
                InputStreamReader iSReader = new FileReader(fileName);
                encoding = iSReader.getEncoding();
                iSReader.close();
            }
            return encoding;
        } catch (FileNotFoundException e) {
            return encoding;
        } catch (IOException e) {
            return encoding;
        }
    }
}
