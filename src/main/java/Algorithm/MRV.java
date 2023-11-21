package Algorithm;

import GUI.LableInput;

import java.util.LinkedList;
import java.util.Queue;

public class MRV {
        private Queue<Integer[]> queue;

        public Queue<Integer[]> getQueue() {
            return queue;
        }
        private int count=0;
    private long executionTime = 0;

    public long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }

    public MRV()
        {
            queue=new LinkedList<>();
        }

        public boolean sloveSudokuWithMRV(LableInput[][] lableInput)
        {
            long startTime = System.currentTimeMillis();
            if (MRVheurictic(lableInput)) {
                long endTime = System.currentTimeMillis();
                executionTime = endTime - startTime;
                return true;
            }
            return false;

        }
        public  boolean MRVheurictic(LableInput[][] lableInput){
            int [] emptyCell=findEmptyCellMRV(lableInput);
            int row,col;
            if(emptyCell==null)
                return true;
            row = emptyCell[0];
            col = emptyCell[1];

            for(int i=1;i<=9;i++)
            {
                if(isValid(lableInput,row,col,i))
                {
                    Integer[] cell={row,col,i};
                    queue.add(cell);
                    lableInput[row][col].setText(i+"");
                    lableInput[row][col].repaint();
                    count++;
                    if(MRVheurictic(lableInput))
                        return true;
                    else {
                        lableInput[row][col].setText("");
                        lableInput[row][col].repaint();
                    }

                }
            }
            return false;
        }
        public  int[] findEmptyCellMRV(LableInput[][] lableInput) {
            int[] emptyCell= new int[2];
            int minRemainingValues =Integer.MAX_VALUE;
            for(int row=0;row<9;row++)
            {
                for (int col =0;col<9;col++)
                {
                    if(lableInput[row][col].getText().equals(""))
                    {
                        int remainingValue=countRemainingValues(lableInput,row,col);
                        if(remainingValue<minRemainingValues)
                        {
                            minRemainingValues=remainingValue;
                            emptyCell[0]=row;
                            emptyCell[1]=col;
                        }
                    }
                }
            }
            return minRemainingValues<Integer.MAX_VALUE?emptyCell:null;
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
        public int countRemainingValues(LableInput[][] lableInput,int row, int col) {
            int count = 0;
            for(int cur=1;cur<=9;cur++)
            {
                if(isValid(lableInput,row,col,cur))
                {
                    count++;
                }
            }
            return count;
        }
        public void setCount(int _count)
        {
            this.count=_count;
        }
        public int  getCount(){
            return count;
        }


}
