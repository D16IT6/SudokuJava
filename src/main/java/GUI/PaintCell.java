package GUI;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PaintCell implements Runnable {
    private static Lock lock = new ReentrantLock();
    private  LableInput[][] lableInput;
    public Queue<Integer[]> queue;
    public Integer[] data;
    private LableInput[][] arrayCoppy;
    private LableInput[][] origin;
    private Boolean isBacktracking;

    private int speed=20;

    public void setSpeed(int _speed) {
        speed = _speed;
    }
    public int getSpeed() {
        return speed;
    }

    public PaintCell(LableInput[][] lableInput, Queue<Integer[]> queue, LableInput[][] arrayCoppy, boolean _isBacktracking) {
        this.lableInput = lableInput;
        this.queue = queue;
        this.arrayCoppy = arrayCoppy;
        this.origin = new CoppyArray().getCopyLableInput(lableInput);
        this.isBacktracking=_isBacktracking;
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

    public void panting(boolean isBackTracking) {
        synchronized (lock) {
            if (isBackTracking) {
                while (!queue.isEmpty()) {
                    data = queue.poll();
                    try {
                        Thread.sleep(220-speed*2);
                        lableInput[data[0]][data[1]].setText(data[2] + "");
                        lableInput[data[0]][data[1]].setHover(true);
                        lableInput[data[0]][data[1]].repaint();
                        if (queue.peek() != null) {
                            Integer[] temp = queue.peek();
                            if (data[0] > temp[0] || (data[0] == temp[0] && data[1] >= temp[1])) {
                                setNull(data[0], data[1], temp[0], temp[1]);
                            }
                        } else {
                            break;
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
            else {
                while (!queue.isEmpty()) {
                    data = queue.poll();
                    try {
                        Thread.sleep(220-speed*2);
                        lableInput[data[0]][data[1]].setText(data[2] + "");
                        lableInput[data[0]][data[1]].setHover(true);
                        lableInput[data[0]][data[1]].repaint();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    @Override
    public void run() {
        lock.lock();
        try {
            // Thực hiện các thao tác trên panel
            panting(isBacktracking);
        } finally {
            // Mở khoá panel sau khi hoàn thành
            lock.unlock();
        }

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
