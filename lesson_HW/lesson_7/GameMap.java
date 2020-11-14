package lesson7;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

/**
 * Created by Aleksandr Gladkov [Anticisco]
 * Date of creation: 09.11.2020
 */

public class GameMap extends JPanel {
    public static final int MODE_HVA = 0;
    public static final int MODE_HVH = 1;
    public static final int WINDOW_WIDTH = 300;
    public static final int WINDOW_HEIGHT = 300;
    private static int FIELD_SIZE_X = 3;
    private static int FIELD_SIZE_Y = 3;

    private static final char HUMAN_DOT = 'X';
    private static final char PC_DOT = 'O';
    private static final char EMPTY_DOT = '_';

    private static char[][] field;
    // переменная для тестирования
    private static char sumb_test = HUMAN_DOT;

    GameMap() {
        setBackground(Color.BLACK);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
      }

    void startNewGame(int mode, int fieldSizeX, int fieldSizeY, int winLength) {

        this.FIELD_SIZE_X = fieldSizeX;
        this.FIELD_SIZE_Y = fieldSizeY;
        // графика готова осталось только наложить все то что мы делали в консольных крестиках ноликах
        // инициализация игрового поля
        field = new char[fieldSizeY][fieldSizeX];
        // добавляем обработчик клика на поле
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                // это все для теста отрисовки
                System.out.println("Координата Х:" + x + " Координата Y:" + y);
                System.out.println("Элемент массива Х:" + (int)(FIELD_SIZE_X * x / getWidth()) + " ЭлементМассива Y:" + (int)(FIELD_SIZE_Y * y /getHeight()));

                if(sumb_test == HUMAN_DOT){
                    sumb_test = PC_DOT;
                }
                else{
                   sumb_test = HUMAN_DOT;
                }
                field[(int)(FIELD_SIZE_X * x / getWidth())][(int)(FIELD_SIZE_Y * y /getHeight())] = sumb_test;

            }
        });

    }

    // переопределяем paintComponent теперь можно самостоятельно рисовать на GameMap
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        onRepaintGameMap(g, getWidth(), getHeight(), FIELD_SIZE_X, FIELD_SIZE_Y);
        repaint();
    }

    // рисуем пока без использования Graphics2D
    private static void onRepaintGameMap(Graphics g, int width, int height, int fieldSizeX, int fieldSizeY){

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
