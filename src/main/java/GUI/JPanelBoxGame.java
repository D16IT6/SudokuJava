package GUI;

import javax.swing.*;
import java.awt.*;

public class JPanelBoxGame extends JPanel{
    private LableInput[][] sudokuBox;
    public JPanelBoxGame() {
        this.setSize(new Dimension(720,720));
        this.setLayout(new GridLayout(9,9));
        sudokuBox=new LableInput[9][9];

        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                sudokuBox[i][j]=new LableInput();
                this.add(sudokuBox[i][j]);
            }
        }


    }

    public LableInput[][] getSudokuBox() {
        return sudokuBox;
    }

    public void setSudokuBox(LableInput[][] sudokuBox) {
        this.sudokuBox = sudokuBox;
    }
}
