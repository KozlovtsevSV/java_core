package ru.geekbrains.lesson_2.lession_3;
import java.util.Random;
import java.util.Scanner;
public class game {

    private static final char HUMAN_DOT = 'X';
    private static final char PC_DOT = 'O';
    private static final char EMPTY_DOT = '_';

    public static final Scanner SCANNER = new Scanner(System.in);
    public static final Random RANDOM = new Random(); // [0;1)

    public static int fieldSizeX;
    public static int fieldSizeY;

    public static int fieldNextWinX;
    public static int fieldNextWinY;

    public static char[][] field;

    public static int fieldWinSize;

    public static boolean artificialIntelligence;


    public static void initMap() {

        // разбираться с кодом нет необходимости т.к. все понятно поэтому решил не переписывать самостоятельно все с нуля
        // добавил искуственного интелекта который не только блокирует выигрышные ходы человека но и сам стремиться выиграть если видит что победа близка
        // поддерживаются любые квадрантрые размеры поля и любое количество на победу естественно колво на победу не должно быть больше размеров поля
        fieldSizeX = 5;
        fieldSizeY = 5;
        fieldWinSize = 4;
        // подключение искуственного интелекта
        artificialIntelligence = true;

        fieldNextWinX = 0;
        fieldNextWinY = 0;
        field = new char[fieldSizeY][fieldSizeX];

        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                field[y][x] = EMPTY_DOT;
            }
        }
    }

    public static void printMap() {
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                System.out.print(field[y][x] + "|");
            }
            System.out.println();
        }
    }

    public static void humanTurn() {
        int x;
        int y;
        do {
            System.out.println("Введите координаты через пробел: ");
            x = SCANNER.nextInt() - 1;
            y = SCANNER.nextInt() - 1;
        } while (!isValidCell(y, x) || !isEmptyCell(y, x)); //&& - and / || - or
        field[y][x] = HUMAN_DOT;
    }

    public static void pcTurn() {

        if (artificialIntelligence && pcTurnChekWin()) {
            return;
        }

        int x;
        int y;
        do {
            x = RANDOM.nextInt(fieldSizeX); // 0...2
            y = RANDOM.nextInt(fieldSizeY);
        } while (!isEmptyCell(y, x));
        field[y][x] = PC_DOT;
    }

    public static boolean pcTurnChekWin() {

        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                 if (isEmptyCell(y, x)){
                     // попытка искуственным интелектом найти выигрышный ход для себя
                     field[y][x] = PC_DOT;
                     if(checkWin(PC_DOT)){
                         return true;
                     }
                    // если нет выигрышного хода проверяем нет ли выихрышных ходов для игрока и блокируем его
                    field[y][x] = HUMAN_DOT;
                    if(checkWin(HUMAN_DOT)){
                        field[y][x] = PC_DOT;
                        return true;
                    }
                    // если ничего не подходит возвращаем все как было
                    field[y][x] = EMPTY_DOT;
                }
            }
        }
        return false;
    }

    public static boolean isValidCell(int y, int x) {
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }

    public static boolean isEmptyCell(int y, int x) {
        return field[y][x] == EMPTY_DOT;
    }

    public static boolean checkWin(char c) {
        boolean result = false;

        for (int y_ = 0; y_ < fieldSizeY - (fieldWinSize - 1); y_++) {
            for (int x_ = 0; x_ < fieldSizeX - (fieldWinSize - 1); x_++) {
                result = checkWinSizeWin(y_, x_ ,c);
                if (result) break;
            }
            if (result) break;
        }

        return result;
    }

    public static boolean checkWinSizeWin(int y_, int x_, char c) {

        boolean[] result = {true, true, true, true};

        for (int y = y_; y < fieldWinSize + y_; y++) {
            result[1] = true; result[2] = true;
            for (int x = x_; x < fieldWinSize + x_; x++) {
                if(field[y][x] != c) {
                    result[1] = false;
                }
                if(field[x][y] != c) {
                    result[2] = false;
                }
            }
            if (result[1] || result[2]) {
                break;
            }
            if(field[y][(x_-y_)+ y] != c) {
                result[3] = false;
            }
            int yy = (x_ + y_ + fieldWinSize -1) - y;
            if(field[y][yy] != c) {
                result[0] = false;
            }
        }

        return result[0] || result[1] || result[2] || result[3];
    }

    public static boolean isFullMap() {
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if(field[y][x] == EMPTY_DOT) return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        initMap();
        printMap();
        while (true) {
            humanTurn();
            printMap();
            if (checkWin(HUMAN_DOT)) {
                System.out.println("Человек выиграл!!!");
                break;
            }
            if (isFullMap()) {
                System.out.println("Ничья");
                break;
            }
            System.out.println();
            pcTurn();
            printMap();
            if (checkWin(PC_DOT)) {
                System.out.println("Компьютер выиграл!!! $(((");
                break;
            }
            if (isFullMap()) {
                System.out.println("Ничья");
                break;
            }
            System.out.println();
        }
    }

    /*
     * Полностью разобраться с кодом, попробовать переписать с нуля;
     * ^ Усовершенствовать метод проверки победы (через циклы);
     * ^ Расширить поле до 5х5 и в качестве условий победы установить серию равной 4.
     * ^^ Проработать базовый искусственный интеллект, чтобы он мог блокировать ходы игрока.
     */
}
