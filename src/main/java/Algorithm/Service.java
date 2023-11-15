package Algorithm;
import GUI.LableInput;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Service {
    private Queue<Integer[]> queue;

    private int count;
    public int getCount() {
        return count;
    }
    public Queue<Integer[]> getQueue() {
        return queue;
    }

    public Service() {
        queue=new LinkedList<>();
    }
    public static boolean isNumberInRow(LableInput[][] lableInput, int number, int row) {
        for (int i = 0; i < 9; i++) {
            if (lableInput[row][i].getText().equals(number + "")) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNumberInCol(LableInput[][] lableInput, int number, int col) {
        for (int i = 0; i < 9; i++) {
            if (lableInput[i][col].getText().equals(number + "")) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNumberInBox(LableInput[][] lableInput, int number, int col, int row) {
        int localRow = row - row % 3;
        int localCol = col - col % 3;
        for (int i = localRow; i < localRow + 3; i++) {
            for (int j = localCol; j < localCol + 3; j++) {
                if (lableInput[i][j].getText().equals(number + "")) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isNumberPlacementValid(LableInput[][] lableInput, int row, int col, int number) {
        return !isNumberInRow(lableInput, number, row) &&
                !isNumberInCol(lableInput, number, col) &&
                !isNumberInBox(lableInput, number, col, row);
    }

    public boolean solveSudoku(LableInput[][] lableInput) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (lableInput[i][j].getText().equals("")) {
                    for (int k = 1; k <= 9; k++) {
                        if (isNumberPlacementValid(lableInput, i, j, k)) {
                            Integer[] cell={i,j,k};
                            queue.add(cell);
                            lableInput[i][j].setText(k+"");
                            lableInput[i][j].repaint();
                            count++;
                            if (solveSudoku(lableInput)) {
                                return true;
                            } else {
                                lableInput[i][j].setText("");
                                lableInput[i][j].repaint();
                            }
                        }
                    }
                    return false;
                }
            }
        }
        printMatrix(lableInput);
        return true;
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
