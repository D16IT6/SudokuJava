package GUI;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PaintCell implements Runnable {
    private static final Lock lock = new ReentrantLock();
    private  LableInput[][] lableInput;
    public Queue<Integer[]> queue;
    public Integer[] data;
    public Stack<Integer[]> tempStack;
    private LableInput[][] arrayCoppy;
    private  LableInput[][] origin;
    private  Stack<Integer[]> stack;
    private boolean isSoloved=false;

    private  boolean isBacktracking;

    public LableInput[][] getOrigin() {
        return origin;
    }

    private int speed=40;

    public void setSpeed(int _speed) {
        speed = _speed;
    }
    public int getSpeed() {
        return speed;
    }

    public PaintCell() {
    }

    public PaintCell(LableInput[][] lableInput, Queue<Integer[]> queue, LableInput[][] arrayCoppy, boolean _isBacktracking) {
        this.lableInput = lableInput;
        this.queue = queue;
        this.arrayCoppy = arrayCoppy;
        this.origin = new CoppyArray().getCopyLableInput(lableInput);
        this.isBacktracking = _isBacktracking;
        stack = new Stack<>();
        tempStack = new Stack<>();
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

    public void setNull(int row, int col, Stack<Integer[]> stack) {
        Integer[] data = stack.pop();
        while (true) {
            if (data[0] == row && data[1] == col)
                break;
            lableInput[data[0]][data[1]].setText("");
            lableInput[data[0]][data[1]].setHover(false);
            lableInput[data[0]][data[1]].repaint();
            if (stack.size() > 0 && stack.peek() != null) {
                data = stack.pop();
            } else
                break;
        }
    }


    public void panting(boolean isBackTracking) {
        synchronized (lock) {
            Integer[] copy = null;
            while (!queue.isEmpty()) {
                data = queue.poll();
                stack.add(data);
                try {
                    Thread.sleep(240-speed);
                    lableInput[data[0]][data[1]].setText(String.valueOf(data[2]));
                    lableInput[data[0]][data[1]].setHover(true);
                    if (copy != null) {
                        lableInput[copy[0]][copy[1]].setHover(false);
                        lableInput[copy[0]][copy[1]].repaint();
                    }
                    lableInput[data[0]][data[1]].repaint();
                    copy = data;
                    if (queue.peek() != null) {
                        Integer[] temp = queue.peek();
                        if (isBackTracking) {
                            if (data[0] > temp[0] || (data[0] == temp[0] && data[1] >= temp[1])) {
                                setNull(temp[0], temp[1], stack);
                            }
                        } else {
                            tempStack.addAll(stack);
                            if (checkMRV(temp[0], temp[1])) {
                                setNull(temp[0], temp[1], stack);
                            }
                        }
                    } else {
                        lableInput[data[0]][data[1]].setHover(false);
                        lableInput[data[0]][data[1]].repaint();
                        break;
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }

    public boolean checkMRV(int row, int col) {
        Integer[] tempData;
        while (tempStack.size() > 0 && tempStack.peek() != null) {
            tempData = tempStack.pop();
            if (tempData[0] == row && tempData[1] == col) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void run() {
        lock.lock();
        try {
            // Thực hiện các thao tác trên panel
            panting(isBacktracking);
            isSoloved=true;
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

    public boolean isSoloved() {
        return isSoloved;
    }

    public void setSoloved(boolean soloved) {
        isSoloved = soloved;
    }
}

