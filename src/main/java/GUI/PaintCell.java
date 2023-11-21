package GUI;

import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PaintCell implements Runnable {
    private static final Lock lock = new ReentrantLock();
    private final LableInput[][] lableInput;
    public Queue<Integer[]> queue;
    public Integer[] data;
    private final LableInput[][] arrayCoppy;
    private final LableInput[][] origin;
    private final Stack<Integer[]> stack;

    public LableInput[][] getOrigin() {
        return origin;
    }

    private final Boolean isBacktracking;

    public PaintCell(LableInput[][] lableInput, Queue<Integer[]> queue, LableInput[][] arrayCoppy, boolean _isBacktracking) {
        this.lableInput = lableInput;
        this.queue = queue;
        this.arrayCoppy = arrayCoppy;
        this.origin = new CoppyArray().getCopyLableInput(lableInput);
        this.isBacktracking = _isBacktracking;
        stack = new Stack<>();
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

    public void setNull( int row, int col, Stack<Integer[]> stack) {
        Integer[] data = stack.pop();
        while (true) {
            if (data[0] == row && data[1] == col)
                break;
            lableInput[data[0]][data[1]].setText("");
            lableInput[data[0]][data[1]].setHover(false);
            lableInput[data[0]][data[1]].repaint();
            if (stack.peek() != null) {
                data = stack.pop();
            } else
                break;
        }
    }


    public void panting(boolean isBackTracking) {
        synchronized (lock) {
            if (isBackTracking) {
                while (!queue.isEmpty()) {
                    data = queue.poll();
                    stack.add(data);
                    try {
                        Thread.sleep(50);
                        lableInput[data[0]][data[1]].setText(String.valueOf(data[2]));
                        lableInput[data[0]][data[1]].setHover(true);
                        lableInput[data[0]][data[1]].repaint();
                        if (queue.peek() != null) {
                            Integer[] temp = queue.peek();
                            if (data[0] > temp[0] || (data[0] == temp[0] && data[1] >= temp[1])) {
                                setNull(temp[0], temp[1], stack);
                            }
                        } else {
                            break;
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

            } else {
                while (!queue.isEmpty()) {
                    data = queue.poll();
                    try {
                        Thread.sleep(50);
                        lableInput[data[0]][data[1]].setText(String.valueOf(data[2]));
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
