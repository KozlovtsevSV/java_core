package ru.geekbrains.lesson_1;

public class Main {

    public static void main(String[] args) {

        byte valueByte = 127;
        short valueShort = 32767;

        char valueCh = 'd';

        int valueInt = 2147483647;
        long valueLong = 9223372036854775807L;

        float valueFloat = 3.4e+38f;
        double valueDouble = 1.7e+308;

        String stringValue= "Hello!";

        Boolean valueBoolean = true;

        System.out.println("3. Вычислить по формуле: " + calculation(1,2,3,4));

        System.out.println("4. Число в пределах: " + chekSum(18,2));

        reversNumber(597);
        reversNumber_2(102);

        leepYear(2100);
        leepYear(2000);
        leepYear(2020);
        leepYear(2021);
        
    }

    public static float calculation(float a, float b, float c, float d) {

        return a * (b + (c / d));
    }

    public static boolean chekSum (float a, float b) {

        float sum = a + b;

        return (sum >= 10 && sum <= 20);
    }
// 5. мы же не знаем циклов поэтому наверное так но он не универсален если поменять пределы на разряд все сломаем
    public static void reversNumber(int number) {

        if (number >= 100 && number <= 999){
            int result;
            result = (number % 10) * 100;
            number /= 10;
            result += (number % 10) * 10;
            number /= 10;
            result += (number % 10);
            System.out.println("5. Первый вариант отзеркалить число: " + result);
        }
    }
// или так
    public static void reversNumber_2(int number) {

        if (number >= 100 && number <= 999){
            String result = "";
            String strNumber = Integer.toString(number);
            for(int i = strNumber.length()-1; i >= 0; i--){
                result += strNumber.charAt(i);
            }
            System.out.println("5. Второй вариант отзеркалить число: " + result);
        }
    }

// 6. Написать метод, который определяет является ли год високосным, и выводит сообщение в консоль.
// Каждый 4-й год является високосным, кроме каждого 100-го, при этом каждый 400-й – високосный.
    public static void leepYear(int year) {
        if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) {
            System.out.println(year + " год високосный!");
        }
        else {
            System.out.println(year + " год не високосный!");
        }
    }
}
