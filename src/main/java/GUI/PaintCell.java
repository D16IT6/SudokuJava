package GUI;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.Queue;

public class PaintCell implements Runnable {
    private LableInput[][] lableInput;
    public Queue<Integer[]> queue;
    public Integer[] data;
    private LableInput[][] arrayCoppy;
    private LableInput[][] origin;

    public PaintCell(LableInput[][] lableInput, Queue<Integer[]> queue, LableInput[][] arrayCoppy) {
        this.lableInput = lableInput;
        this.queue = queue;
        this.arrayCoppy = arrayCoppy;
        this.origin = new CoppyArray().getCopyLableInput(lableInput);
    }

    public void setNull(int rowCurrent, int colCurrent, int row, int col) {
        for (int i = 8; i >= row; i--) {
            for (int j = 8; j >= col; j--) {
                if (!lableInput[i][j].getText().equals(origin[i][j].getText())) {
                    lableInput[i][j].setText("");
                    lableInput[i][j].setHover(false);
                    lableInput[i][j].repaint();

                }
            }
        }
    }

    public void panting() {
        data = queue.poll();
        while (data != null) {
            if (true) {
                try {
                    Thread.sleep(5);
                    lableInput[data[0]][data[1]].setText(data[2] + "");
                    lableInput[data[0]][data[1]].setHover(true);
                    lableInput[data[0]][data[1]].repaint();
                    if (queue.peek() != null) {
                        Integer[] temp = queue.peek();
                        if (data[0] > temp[0] || (data[0] == temp[0] && data[1] >= temp[1])) {
                            setNull(data[0], data[1], temp[0], temp[1]);
                        }
                        data = queue.poll();
                    } else {
                        break;
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }

    @Override
    public void run() {
        panting();
    }

    public static void printMatrix(LableInput[][] a) {
        System.out.println();
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("-------------------------------");
            }
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print("|  ");
                }
                System.out.print(a[i][j].getText() + "  ");
            }
            System.out.println();
        }
    }
}
