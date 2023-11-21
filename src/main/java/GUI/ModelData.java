package GUI;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class ModelData {
//    (lableInput[i][columns].getText().equals(cur + "")
//                    &&!lableInput[i][columns].getText().equalsIgnoreCase(cur+"") )
//            || (lableInput[row][i].getText().equals(cur + "")
//            &&!lableInput[row][i].getText().equalsIgnoreCase(cur+"")))
    public static boolean isValid(LableInput[][] lableInput, int row, int columns, int cur) {
        for (int i = 0; i < 9; i++) {
            if (i!=row&&i!=columns&&(lableInput[i][columns].getText().equals(cur + "") || lableInput[row][i].getText().equals(cur + "")))
                return false;
        }
        int boxRow = row / 3 * 3;
        int boxColumns = columns / 3 * 3;
        for (int i = boxRow; i < boxRow + 3; i++) {
            for (int j = boxColumns; j < boxColumns + 3; j++) {
                if ((i!=row&&j!=columns)&&lableInput[i][j].getText().equals(cur + "")) {
                    return false;
                }
            }
        }
        return true;
    }
    public static boolean checkValidate(LableInput[][]lableInput){
        for(int i=0;i<9;i++)
            for (int j=0;j<9;j++)
            {
                if(!lableInput[i][j].getText().equalsIgnoreCase(""))
                {
                    if(!isValid(lableInput,i,j,Integer.parseInt(lableInput[i][j].getText())))
                        return false;
                }
            }

        return true;
    }

    public static void ResetData(LableInput[][] lableInput) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                lableInput[i][j].setText("");
                lableInput[i][j].setData(false);
                lableInput[i][j].setSelected(false);
                lableInput[i][j].repaint();
            }
        }
    }

    public static LableInput[][] setData(LableInput[][] lableInput, String _path) {
        File f = new File(_path);
        try (BufferedReader br = Files.newBufferedReader(f.toPath(), StandardCharsets.US_ASCII)) {
            String line;
            int row = 0;
            while ((line = br.readLine()) != null && row < 9) {
                char[] values = line.toCharArray();
                for (int column = 0; column < line.length(); column++) {
                    if (values[column] == '0') {
                        lableInput[row][column].setText("");
                        lableInput[row][column].setData(false);
                        lableInput[row][column].setHover(false);
                        lableInput[row][column].setSelected(false);
                        lableInput[row][column].repaint();
                    } else {
                        lableInput[row][column].setText(Character.getNumericValue(values[column]) + "");
                        lableInput[row][column].setData(true);
                        lableInput[row][column].repaint();
                    }
//                    lableInput[row][column].setText(Character.getNumericValue(values[column]) + "");
                }
                row++;
            }
            return lableInput;
        } catch (IOException e) {
            System.out.printf("loi" + e);
            return null;
        }
    }

    public static void setData(LableInput[][] lableInput, LableInput[][] copyLableInput) {
        try {
            for (int row = 0; row < 9; row++) {
                for (int column = 0; column < 9; column++) {
                    if (copyLableInput[row][column].getText().equalsIgnoreCase("")) {
                        lableInput[row][column].setText("");
                        lableInput[row][column].setData(false);
                        lableInput[row][column].setHover(false);
                        lableInput[row][column].setSelected(false);
                        lableInput[row][column].repaint();
                    } else {
                        lableInput[row][column].setText(copyLableInput[row][column].getText());
                        lableInput[row][column].setData(true);
                        lableInput[row][column].repaint();
                    }
                }
            }
        } catch (Exception e) {
            System.out.printf("loi" + e);
        }
    }

    public static boolean checkData(LableInput[][] lableInput) {
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                if (!lableInput[row][column].getText().equalsIgnoreCase("")) {
                    return true;
                }
            }

        }
        return false;
    }
}
