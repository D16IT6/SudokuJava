package GUI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReadFile {
//    public static LableInput[][] setDataSpace(LableInput[][] lableInput,String _path) {
//        int[][] a = new int[9][9];
//
//        try {
//            Path filePath = Paths.get(_path);
//            if (!Files.exists(filePath)) {
//                Files.createFile(filePath);
//                System.out.println("File has been created");
//            } else {
//                String line;
//                int row = 0;
//                BufferedReader reader = new BufferedReader(new FileReader(_path));
//                while ((line = reader.readLine()) != null) {
//                    String[] data = line.split(" ");
//                    for (int i = 0; i < 9; i++) {
//                        if (data[i].equalsIgnoreCase("0"))
//                            lableInput[row][i].setText("");
//                        else {
//                            lableInput[row][i].setText(data[i]);
//                            lableInput[row][i].setData(true);
//                            lableInput[row][i].repaint();
//                        }
//                    }
//                    row++;
//                }
//                reader.close();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return lableInput;
//    }
    public static LableInput[][] setData(LableInput[][] lableInput,String _path) {
        File f = new File(_path);
        try (BufferedReader br = Files.newBufferedReader(f.toPath(), StandardCharsets.US_ASCII)) {
            String line;
            int row = 0;
            while ((line = br.readLine()) != null && row < 9) {
                char[] values = line.toCharArray();
                for (int column = 0; column < line.length(); column++) {
                    if (values[column]=='0')
                            lableInput[row][column].setText("");
                        else {
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
}
