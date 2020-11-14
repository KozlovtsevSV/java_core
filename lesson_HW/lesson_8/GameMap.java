package lesson7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class GameMap extends JPanel {
    public static final int MODE_HVA = 0;
    public static final int MODE_HVH = 1;
    public static final int WINDOW_WIDTH = 300;
    public static final int WINDOW_HEIGHT = 300;
    private static int FIELD_SIZE_X = 3;
    private static int FIELD_SIZE_Y = 3;
    // флаг окончания игры
    // -1, - игра не окончена
    // 0, - ничья
    // 1, - победа Игрока
    // 2, - победа ИИ
    private static int MODE_FINISH_GAME;

    private static int MODE_GAME;
    private static int WINLENGTH;

    private static final char HUMAN_DOT = 'X';
    private static final char PC_DOT = 'O';
    private static final char EMPTY_DOT = '_';

    private static char[][] field;
    private static char ch_DOT = HUMAN_DOT;
    public static final Random RANDOM = new Random(); // [0;1)

    GameMap() {
        setBackground(Color.BLACK);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
      }
    // логика игры
    void startNewGame(int mode, int fieldSizeX, int fieldSizeY, int winLength) {

        this.FIELD_SIZE_X = fieldSizeX;
        this.FIELD_SIZE_Y = fieldSizeY;
        this.MODE_GAME = mode;
        this.WINLENGTH = winLength;
        System.out.println(mode);

        // инициализация игрового поля
        initMap();

        // добавляем обработчик клика на поле
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(MODE_FINISH_GAME != -1){
                    return;
                }
                int x = FIELD_SIZE_X * e.getX() / getWidth();
                int y = FIELD_SIZE_Y * e.getY() /getHeight();

                if (isEmptyCell(x, y)){
                    field[x][y] = ch_DOT;
                }
                else{
                    return;
                }
                if(checkWin(ch_DOT)){
                    MODE_FINISH_GAME = 1;
                    return;
                }

                if (isFullMap()) {
                    MODE_FINISH_GAME = 0;
                    return;
                }

                if(MODE_GAME == 0){
                    pcTurn();
                }
                if(MODE_FINISH_GAME != -1){
                    return;
                }

                if(MODE_GAME == 1 && ch_DOT == HUMAN_DOT){
                    ch_DOT = PC_DOT;
                }
                else{
                    ch_DOT = HUMAN_DOT;
                }
            }
        });
    }

    public static void initMap() {

        MODE_FINISH_GAME = -1;
        ch_DOT = HUMAN_DOT;
        field = new char[FIELD_SIZE_X][FIELD_SIZE_Y];

        for (int x = 0; x < FIELD_SIZE_X; x++) {
            for (int y = 0; y < FIELD_SIZE_Y; y++) {
                field[y][x] = EMPTY_DOT;
            }
        }
    }

    public static boolean isFullMap() {
        for (int x = 0; x < FIELD_SIZE_X; x++) {
            for (int y = 0; y < FIELD_SIZE_Y; y++) {
                if(field[x][y] == EMPTY_DOT) return false;
            }
        }
        return true;
    }

    public static boolean checkWin(char c) {
        boolean result = false;
        for (int x = 0; x < FIELD_SIZE_X; x++) {
            for (int y = 0; y < FIELD_SIZE_Y; y++) {
                if(c == field[x][y]) {
                    int comboLengthD = 1, comboLengthXD = 1, comboLengthX = 1, comboLengthY =1;
                    for (int z = 1; z < WINLENGTH; z++) {
                        int xz = x + z;
                        int yz = y + z;
                        // проверка диагонали
                        if (isValidCell(xz, yz)) {
                            if (c == field[xz][yz]) {
                                comboLengthD++;
                            }
                        }
                        if (comboLengthD == WINLENGTH) {
                            result = true;
                            break;
                        }
                        // проверка  обратной диагонали
                        if (isValidCell(xz, y - z)) {
                            if (c == field[xz][y - z]) {
                                comboLengthXD++;
                            }
                        }
                        if (comboLengthXD == WINLENGTH) {
                            result = true;
                            break;
                        }
                        // проверка  по оси Х
                        if (isValidCell(x, yz)) {
                            if (c == field[x][yz]) {
                                comboLengthX++;
                            }
                        }
                        if (comboLengthX == WINLENGTH) {
                            result = true;
                            break;
                        }
                        // проверка  по оси Y
                        if (isValidCell(xz, y)) {
                            if (c == field[xz][y]) {
                                comboLengthY++;
                            }
                        }
                        if (comboLengthY == WINLENGTH) {
                            result = true;
                            break;
                        }
                    }
                    if (result) {
                        break;
                    }
                }
            }
            if (result) {
                break;
            }
        }
        return result;
    }

    public static void pcTurn() {

        if (pcTurnChekWin()) {
            return;
        }

        int x;
        int y;
        do {
            x = RANDOM.nextInt(FIELD_SIZE_X); // 0...2
            y = RANDOM.nextInt(FIELD_SIZE_Y);
        } while (!isEmptyCell(x, y));
        field[x][y] = PC_DOT;
    }

    public static boolean pcTurnChekWin() {

        for (int x = 0; x < FIELD_SIZE_X; x++) {
            for (int y = 0; y < FIELD_SIZE_Y; y++) {
                if (isEmptyCell(x, y)) {
                    // попытка искуственным интелектом найти выигрышный ход для себя
                    field[x][y] = PC_DOT;
                    if (checkWin(PC_DOT)) {
                        MODE_FINISH_GAME = 2;
                        //Победа компьютера
                        return true;
                    }
                    field[x][y] = EMPTY_DOT;
                }
            }
        }

    // если нет выигрышного хода проверяем нет ли выихрышных ходов для игрока и блокируем его
        for (int x = 0; x < FIELD_SIZE_X; x++) {
            for (int y = 0; y < FIELD_SIZE_Y; y++) {
                if (isEmptyCell(x, y)) {
                    field[x][y] = HUMAN_DOT;
                    if(checkWin(HUMAN_DOT)){
                        field[x][y] = PC_DOT;
                        return true;
                    }
                    field[x][y] = EMPTY_DOT;
                }
            }
        }
        return false;
    }

    // графика
    // переопределяем paintComponent теперь можно самостоятельно рисовать на GameMap
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        onRepaintGameMap(g, getWidth(), getHeight(), FIELD_SIZE_X, FIELD_SIZE_Y);
        repaint();
    }

    public static boolean isEmptyCell(int y, int x) {
        return field[y][x] == EMPTY_DOT;
    }

    public static boolean isValidCell(int y, int x) {
        return x >= 0 && x < FIELD_SIZE_X && y >= 0 && y < FIELD_SIZE_Y;
    }

    // рисуем пока без использования Graphics2D
    private static void onRepaintGameMap(Graphics g, int width, int height, int fieldSizeX, int fieldSizeY){
        Graphics2D g2 = (Graphics2D) g;

        g2.setStroke(new BasicStroke(3.0f));  // толщина равна 3

        g.setColor(Color.YELLOW);

        for (int x = 0; x < fieldSizeX; x ++) {
            for (int y = 0; y < fieldSizeY; y ++) {
                // отрисовываем Х / 0
                if (field[x][y] == HUMAN_DOT){
                    onRepaint_HUMAN_DOT(g, (int) (width/fieldSizeX),(int)(height/fieldSizeY), x, y);
                }
                else if(field[x][y] == PC_DOT){
                   onRepaint_PC_DOT(g, (int) (width/fieldSizeX),(int)(height/fieldSizeY), x, y);
                }
                // рисуем игровое поле
                g.drawLine((width/fieldSizeX) * x,0, (width/fieldSizeX) * x, height);
                g.drawLine(0, (height/fieldSizeY) * x, width, (height/fieldSizeY) * x);
            }
        }
        if(MODE_FINISH_GAME != -1){
            int centerWidth = (int) width / 2;
            int centerHeight = (int) height / 2;

            int WidthRectFinish = 200;
            int HeightRectFinish = 100;

            g.setColor(Color.GRAY);
            g.fillRect(centerWidth - WidthRectFinish / 2 ,centerHeight - HeightRectFinish / 2,WidthRectFinish,HeightRectFinish);

            g.setColor(Color.GREEN);
            int fontSizeText = 15;
            g.setFont(new Font("Serif", Font.BOLD, fontSizeText));
            String text = "GAME OVER";
            g.drawString(text,(centerWidth - (text.length() * fontSizeText) / 2),(centerHeight - HeightRectFinish / 2) + 30);
            // ничья
            if(MODE_FINISH_GAME == 0){
                text = "НИЧЬЯ";
                g.drawString("НИЧЬЯ", (centerWidth - (text.length() * fontSizeText) / 2),(centerHeight - HeightRectFinish / 2) + 50);
                return;
            }
            text = "ПОБЕДА";
            g.drawString("ПОБЕДА", (centerWidth - (text.length() * fontSizeText) / 2),(centerHeight - HeightRectFinish / 2) + 50);
            if(MODE_FINISH_GAME == 2){
                text = "КОМПЬЮТЕРА";
                g.drawString(text, (centerWidth - (text.length() * fontSizeText) / 2),(centerHeight - HeightRectFinish / 2) + 70);
                return;
            }
            text = "ИГРОКА ";
            g.drawString(text + ch_DOT, (centerWidth - (text.length() * fontSizeText) / 2),(centerHeight - HeightRectFinish / 2) + 70);

        }
    }
    private static void onRepaint_HUMAN_DOT(Graphics g, int widthField, int heightField, int fieldX, int fieldY){
        // тут можно и картинку крестика рисовать, тогда будет визуально круче
        g.drawLine(widthField * fieldX, heightField * fieldY, widthField * fieldX + widthField, heightField * fieldY + heightField);
        g.drawLine(widthField * fieldX, heightField * fieldY + heightField, widthField * fieldX + widthField,heightField * fieldY);

    }

    private static void onRepaint_PC_DOT(Graphics g, int widthField, int heightField, int fieldX, int fieldY){
        // тут можно и картинку нолика рисовать, тогда будет визуально круче
        g.drawOval(widthField * fieldX, heightField * fieldY, widthField, heightField);
    }

}
