package Algorithm;
import GUI.LableInput;

import java.util.LinkedList;

import java.util.Queue;
public class Backtracking {
    private Queue<Integer[]> queue;

    private int count;
    private long executionTime;
    public int getCount() {
        return count;
    }
    public long getEcutionTime(){
        return executionTime;
    }
    public Queue<Integer[]> getQueue() {
        return queue;
    }

    public Backtracking() {
        queue=new LinkedList<>();
    }
    public  boolean isValid(LableInput[][] lableInput,int row,int columns,int cur)
    {
        for (int i=0;i<9;i++)
        {
            if(lableInput[i][columns].getText().equals(cur+"")||lableInput[row][i].getText().equals(cur+""))
                return false;
        }
        int boxRow= row/3 *3;
        int boxColumns=columns/3*3;
        for(int i=boxRow;i<boxRow+3;i++)
        {
            for(int j=boxColumns;j<boxColumns+3;j++){
                if(lableInput[i][j].getText().equals(cur+""))
                {
                    return false;
                }
            }
        }
        return true;
    }
    public boolean solveSudoku(LableInput[][] lableInput) {
        if (backtracking(lableInput)) {
            return true;
        }
        return false;
    }
    public boolean backtracking(LableInput[][] lableInput)
    {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (lableInput[i][j].getText().equals("")) {
                    for (int k = 1; k <= 9; k++) {
                        if (isValid(lableInput, i, j, k)) {
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
        return true;
    }
}
