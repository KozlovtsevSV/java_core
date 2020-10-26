package ru.geekbrains.lesson_2;
//        1 Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ]. Написать метод, заменяющий в принятом массиве 0 на 1, 1 на 0;
//        2 Задать пустой целочисленный массив размером 8. Написать метод, который c помощью цикла заполнит его значениями 1 4 7 10 13 16 19 22;
//        3 Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ], написать метод, принимающий на вход массив и умножающий числа меньше 6 на 2;
//        4 Задать одномерный массив. Написать методы поиска в нём минимального и максимального элемента;
//        5 ^ Создать квадратный целочисленный массив (количество строк и столбцов одинаковое), заполнить его диагональные элементы единицами, используя цикл(ы);
//        6 ^^ Написать метод, в который передается не пустой одномерный целочисленный массив, метод должен вернуть true если в массиве есть место, в котором сумма левой и правой части массива равны. Примеры:
//        checkBalance([1, 1, 1, || 2, 1]) → true,
//        checkBalance ([2, 1, 1, 2, 1]) → false,
//        checkBalance ([10, || 1, 2, 3, 4]) → true.
//        Абстрактная граница показана символами ||, эти символы в массив не входят.
//        7 ^^^ Написать метод, которому на вход подаётся одномерный массив и число n (может быть положительным, или отрицательным), при этом метод должен циклически сместить все элементы массива на n позиций.
//        [1,2,3,4,5], -2 => [3,4,5,1,2]
//        [1,2,3,4,5], 2 => [4,5,1,2,3]
//        8 ^^^^ Не пользоваться вспомогательным массивом при решении задачи 7.

//        Задание по хардкору. Нужен метод, который получает в параметры 2 массива (разной длины) int-чисел. Он (метод) должен вернуть массив значений, которые есть в 1 массиве, но их нет во втором
//        Задание комбо-хардкор: необходимо проверить, что первый двухмерный массив является подмассивом второго двухмерного массива, которые подаются в метод//

import java.util.Arrays;

public class Main {


    public static void main(String[] args) {

//1
        System.out.println("Задание № 1");
        int[] array = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        printArray(array,"Массив исходный: ");
        printArray(reversArray(array),"Измененный массив: ");

//2
        System.out.println("Задание № 2");
        int[] array2 = new int[8];
        printArray(fillArray(array2,1,3),"Заполнить массив: ");

//3
        System.out.println("Задание № 3");
        int[] array3 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        printArray(array3,"Массив исходный: ");
        printArray(multiplication(array3,2,6),"Измененный массив: ");

//4
        System.out.println("Задание № 4");
        int[] array4 = new int[15];
        array4 = fillArrayRandom(array4,50);
        printArray(array4,"Массив исходный: ");
        System.out.println("Максимальное значение: " + maxValueArray(array4));
        System.out.println("Минимальное значение: " + minValueArray(array4));

//5
        System.out.println("Задание № 5");
        int sizeArray = 5;
        int[][] array5 = new int[sizeArray][sizeArray];
        printArraySqr(fillArrayCross(array5),"");
//6
        System.out.println("Задание № 6");
        int[] array6 = new int[4];
        array6 = fillArrayRandom(array6,5);
        printArray(array6,"Массив исходный: ");
        System.out.println("Массив сбалансирован? " + checkBalance(array6));
//7
        System.out.println("Задание № 7");
        int[] array7 = {1, 2, 3, 4, 5};
        int shift = -6;
        printArray(array7,"Массив исходный: \t");
        printArray(shiftArray(array7, shift),"Сместили массив на "+ shift +" результат: ");
//8
        System.out.println("Задание № 8");
        int[] array8 = {1, 2, 3, 4, 5, 6, 7, 8, 3};
        int[] array9 = {2, 4, 8, 5};
        printArray(array8,"Первый массив ");
        printArray(array9,"Второй массив: ");
        printArray(checkArray(array8, array9),"Есть в первом массиве, но нет во втором массиве: ");

   }
    //1
    public static int[] reversArray(int[] array){
        for(int i = 0; i < array.length; i++ ){
            array[i] = (array[i] == 0)? 1: 0;
        }
        return array;

    }

    //2
    public static int[] fillArray(int[] array, int firstValue, int step){
        for (int i = 0; i < array.length; i++ ){
            array[i] = firstValue;
            firstValue += step;
        }
        return array;
    }

    //3
    public static int[] multiplication(int[] array,int coefficient, int checkValue){
        for (int i = 0; i < array.length; i++ ) {
            if(array[i] < checkValue){
                array[i] *= coefficient;
            }
        }
        return array;
    }

    //4
    public static int maxValueArray(int[] array){
        if (array.length == 0){
            System.out.println("Массив пустой.");
            return 0;
        }

        int result = array[0];
        for (int i = 1; i < array.length; i++ ) {
            if (array[i] > result){
                result = array[i];
            }
        }
        return result;
    }

    public static int minValueArray(int[] array){
        if (array.length == 0){
            System.out.println("Массив пустой.");
            return 0;
        }

        int result = array[0];
        for (int i = 1; i < array.length; i++ ) {
            if (array[i] < result){
                result = array[i];
            }
        }
        return result;
    }

    //5
    public static int[][] fillArrayCross(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if(i == j || (i + j) == array.length - 1){
                    array[i][j] = 1;
                }
            }
        }
        return array;
    }

    //6
    public static boolean checkBalance(int[] array) {

        boolean result = false;
        int sumLeft = 0;
        int sumRight = 0;

        for (int i = 0; i < array.length; i++) {
            sumLeft += array[i];
            sumRight = 0;
            for (int j = i + 1; j <array.length; j++) {
                sumRight += array[j];
            }
            if (sumLeft == sumRight){
                result = true;
                break;
            }
        }
        return result;
    }

    //7
    public static int[] shiftArray(int[] array, int n){

        int memo1 = 0;
        int memo2 = 0;
        int step_index = 0;
        memo1 = array[step_index];
        for(int step = 0; step < array.length; step++ ){
            // расчет шага с учетом отрицательных шагов и если шаг более длинны массива
            step_index = Math.abs(step_index + + (array.length * (Math.abs(n) / array.length + 1)) + n) % array.length;
            memo2 = array[step_index];
            array[step_index] = memo1;
            memo1 = memo2;
        }
        return array;
    }

    //8
    public static int[] checkArray(int[] array, int[] array1){

        int[] memo_array = new int[array.length];
        int resultArrayIndex = 0;
        Boolean flagFind;

        for (int i = 0; i < array.length; i++) {
            flagFind = false;
            for (int j = 0; j < array1.length; j++) {
                if(array[i] == array1[j]){
                    flagFind = true;
                }
            }
            if (!flagFind){
                // проверим что в результирующем массиве уже нет числа
                if(!findNumberInArray(memo_array,array[i])) {
                    memo_array[resultArrayIndex] = array[i];
                    resultArrayIndex++;
                }
            }
        }

        int[] result = new int[resultArrayIndex];
        for (int i = 0; i < resultArrayIndex; i++) {
            result[i] = memo_array[i];
        }

        return result;
    }


// методы для следующих заданий
    public static int[] fillArrayRandom(int[] array, int maxValueRandom){
        for (int i = 0; i < array.length; i++) {
            array[i] = getRandomIntNumber(maxValueRandom);
        }
        return array;
    }

    public static int getRandomIntNumber(int maxValueRandom){
        return (int) (Math.random()* maxValueRandom + 1);
    }

    public static Boolean findNumberInArray(int[] array, int number){
        Boolean result = false;
        for (int i = 0; i < array.length; i++) {
            if(array[i] == number){
                result = true;
                break;
            }
        }
        return result;
    }

    public static void printArray(int[] array, String massage){
        System.out.print(massage);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " | ");
        }
        System.out.println();
    }

    public static void printArraySqr(int[][] array, String massage){
        System.out.print(massage);
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " | \t");
            }
            System.out.println();
        }
        System.out.println();
    }

}
