package GUI;

public class CoppyArray {
    private LableInput[][] copyLableInput;

    public CoppyArray() {
        this.copyLableInput=new LableInput[9][9];
        for (int i=0;i<9;i++){
            for (int j=0;j<9;j++){
                copyLableInput[i][j]=new LableInput();
            }
        }
    }

    public LableInput[][] getCopyLableInput() {
        return copyLableInput;
    }

    public LableInput[][] getCopyLableInput(LableInput[][]lableInput) {
        for (int i=0;i<9;i++){
            for (int j=0;j<9;j++){
                copyLableInput[i][j].setText(lableInput[i][j].getText());
            }
        }
        return copyLableInput;
    }
}
