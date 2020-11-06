package ru.geekbrains.lesson_6;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.io.File;

public class Main {

    // флаг коректности чтения записи всех файлов
    private static boolean flagResult = true;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String findString;
        String nameFile = "Text3";
        // получение рабочей директории
        String workingDir = System.getProperty("user.dir");

        // проба передать обработку исключения из своего метода выше.
        try {
            saveFile("Text1", "Мама мыла раму! ");
            saveFile("Text2", "Раму мыла мама! ");
          }
        catch (IOException IOe){
            System.out.println(IOe.getMessage());
            flagResult = false;
        }

        // если входные файлы записались коректно можно продолжать
        if (flagResult) {
            try {
                saveFile(nameFile, (readFile("Text1").append(readFile("Text2"))).toString());
            }
            catch (IOException IOe){
                System.out.println(IOe.getMessage());
                flagResult = false;
            }
            // если файлы склеились можно проболжать
            if (flagResult) {
                System.out.println("Содержимое склееного файла: " + nameFile + "\n" + readFile(nameFile) + "\n ---------------------------------------------------");

                System.out.print("Введите строку поиска в файле: ");
                findString = scanner.nextLine();

                System.out.println("Найдена " + findString + " строка в файле " + nameFile + "? " + findStringToFile(findString, nameFile));
            }
        }

        System.out.print("Введите строку поиска в файлах каталога "+ workingDir + ": ");
        findString = scanner.nextLine();
        System.out.println("Поищем строку в фалах каталога "+ workingDir + ": включаяя вложенные каталоги");
        findStringToFilesDirectory(workingDir, findString, true);

        System.out.print("Введите строку поиска в строке каталога "+ workingDir + ": ");
        findString = scanner.nextLine();
        System.out.println("Строка найденна? " + (workingDir.contains(findString)));



        scanner.close();
    }

    public static void findStringToFilesDirectory(String directories, String findString, boolean nestedDirectories){

        File findDirectories = new File(directories);
        File[] files = findDirectories.listFiles();
        // т.к. метод listFiles может возвращать null обработаем такую ситуацию
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory() && nestedDirectories) {
                    // волшебство рекурсии
                    findStringToFilesDirectory(files[i].getAbsolutePath(), findString, nestedDirectories);
                } else {
                    if (findStringToFile(findString, files[i].getAbsolutePath())) {
                        System.out.println("Строка найденна в файле: " + files[i].getAbsolutePath());
                    }
                }
            }
        }

    }

    public static void saveFile(String NameFile, String text) throws IOException{
       // try {
            FileOutputStream fos = new FileOutputStream(NameFile);
            PrintStream ps = new PrintStream(fos);
            ps.print(text);
            ps.close();
       //}catch (IOException IOe){
       //     System.out.println(IOe.getMessage());
        //}
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

    public static boolean findStringToFile(String findString, String NameFile) {
        return readFile(NameFile).indexOf(findString) != -1;
    }

}
//Создать 2 текстовых файла, примерно по 50-100 символов в каждом (особого значения не имеет);
//        Написать программу, «склеивающую» эти файлы, то есть вначале идет текст из первого файла, потом текст из второго.
//        * Написать программу, которая проверяет присутствует ли указанное пользователем слово в файле.
//        ** Написать метод, проверяющий, есть ли указанное слово в папке, внутри есть файлы, в которых может содержатся это слово
//        (данная тема преднамеренно не рассказывалась, поэтому надо погуглить (Гуглить - тоже надо уметь правильно).
//        При чем не просто найти решение и списать, а именно сформулировать проблему. Пока вы будете формулировать проблему - вы найдете 50% решения)
