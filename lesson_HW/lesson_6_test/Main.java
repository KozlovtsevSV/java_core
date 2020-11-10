package ru.geekbrains.test;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Main {

    private static FileOutputStream fos;
    private static FileInputStream fis;
    private static float dataS;
    private static float dataT;

    public static void main(String[] args) {

        dataS = System.nanoTime();
        mergeFiles("Text1", "Text2", "Text3_1");
        dataT =  System.nanoTime() - dataS;
        System.out.println("merge по байтно время: " + dataT * 0.000001f);

        dataS = System.nanoTime();
        saveFile("Text3_2", (readFile("Text1").append(readFile("Text2"))).toString());
        dataT =  System.nanoTime() - dataS;
        System.out.println("merge через переменную время: " + dataT * 0.000001f);

    }

    private static void mergeFiles(String inputFile1, String inputFile2, String outputFile) {
        try {
            fos = new FileOutputStream(outputFile);
            int ch;
            fis = new FileInputStream(inputFile1);
            while ((ch = fis.read()) != -1)
                fos.write(ch);
            fis.close();

            fis = new FileInputStream(inputFile2);
            while ((ch = fis.read()) != -1) {
                fos.write(ch);
            }
            fis.close();

            fos.flush();
            fos.close();
        } catch (IOException e) {
            System.out.println("Something wrong. Reason: " + e.getCause());
        }
    }

    public static void saveFile(String NameFile, String text) {

         try {
        FileOutputStream fos = new FileOutputStream(NameFile);
        PrintStream ps = new PrintStream(fos);
        ps.print(text);
        ps.close();
        }catch (IOException IOe){
             System.out.println(IOe.getMessage());
        }
    }

    public static StringBuilder readFile(String NameFile) {
        StringBuilder result = new StringBuilder();

        try {
            FileInputStream fis = new FileInputStream(NameFile);
            InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
            int b;
            while ((b = isr.read()) != -1){
                result.append((char) b);
            }
            fis.close();

        }catch (IOException IOe){
            System.out.println(IOe.getMessage());
        }
        return result;
    }

    
}



