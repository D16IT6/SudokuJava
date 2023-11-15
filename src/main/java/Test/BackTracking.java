package Test;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BackTracking {
    public static int x=0;
    public static boolean isNumberInRow(int[][] a, int number, int row) {
        for (int i = 0; i < 9; i++) {
            if (a[row][i] == number) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNumberInCol(int[][] a, int number, int col) {
        for (int i = 0; i < 9; i++) {
            if (a[i][col] == number) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNumberInBox(int[][] a, int number, int col, int row) {
        int localRow = row - row % 3;
        int localCol = col - col % 3;
        for (int i = localRow; i < localRow + 3; i++) {
            for (int j = localCol; j < localCol + 3; j++) {
                if (a[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isNumberPlacementValid(int[][] a, int row, int col, int number) {
        return !isNumberInRow(a, number, row) &&
                !isNumberInCol(a, number, col) &&
                !isNumberInBox(a, number, col, row);
    }

    public static boolean solveSudoku(int[][] a) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (a[i][j] == 0) {
                    for (int k = 1; k <= 9; k++) {
                        if (isNumberPlacementValid(a, i, j, k)) {
                            a[i][j] = k;
                            x++;
                            if (solveSudoku(a)) {
                                return true;
                            } else {
                                a[i][j] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public static void printMatrix(int[][] a) {
        System.out.println();
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("-------------------------------");
            }
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print("|  ");
                }
                System.out.print(a[i][j] + "  ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        String path = "D:\\java\\laptrinhhethong\\soduku.txt";
        int[][] a = new int[9][9];

        try {
            Path filePath = Paths.get(path);
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
                System.out.println("File has been created");
            } else {
                String line;
                int row = 0;
                BufferedReader reader = new BufferedReader(new FileReader(path));
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(" ");
                    for (int i = 0; i < 9; i++) {
                        a[row][i] = Integer.parseInt(data[i]);
                    }
                    row++;
                }
                reader.close();
                System.out.println("Original Sudoku.Sudoku:");
                printMatrix(a);
                solveSudoku(a);
                System.out.println("\nSolved Sudoku.Sudoku:");
                printMatrix(a);
                System.out.println("so lan chay"+x);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
