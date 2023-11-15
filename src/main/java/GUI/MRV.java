package GUI;

import java.util.LinkedList;
import java.util.Queue;

public class MRV {
        private Queue<Integer[]> queue;

        public Queue<Integer[]> getQueue() {
            return queue;
        }
        private int[][] board;
        private int count=0;
        public MRV(int [][]_board)
        {

            this.board=_board;
            queue=new LinkedList<>();
        }

        public boolean sloveSudokuWithMRV()
        {
            if(MRVheurictic())
                return true;
            return false;
        }
        public  boolean MRVheurictic(){
            int [] emptyCell=findEmptyCellMRV();
            int row,col;
            if(emptyCell==null)
                return true;
            row = emptyCell[0];
            col = emptyCell[1];
            for(int i=1;i<=9;i++)
            {
                if(isValid(row,col,i))
                {
                    board[row][col]=i;
                    count++;
                    if(MRVheurictic())
                        return true;
                    board[row][col]=0;
                }
            }
            return false;
        }
        public  int[] findEmptyCellMRV() {
            int[] emptyCell= new int[2];
            int minRemainingValues =Integer.MAX_VALUE;
            for(int row=0;row<9;row++)
            {
                for (int col =0;col<9;col++)
                {
                    if(board[row][col]==0)
                    {
                        int remainingValue=countRemainingValues(row,col);
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
        public  boolean isValid(int row,int columns,int cur)
        {
            for (int i=0;i<9;i++)
            {
                if(board[i][columns]==cur||board[row][i]==cur)
                    return false;
            }
            int boxRow= row/3 *3;
            int boxColumns=columns/3*3;
            for(int i=boxRow;i<boxRow+3;i++)
            {
                for(int j=boxColumns;j<boxColumns+3;j++){
                    if(board[i][j]==cur)
                    {
                        return false;
                    }
                }
            }
            return true;
        }
        public int countRemainingValues(int row, int col) {
            int count = 0;
            for(int cur=1;cur<=9;cur++)
            {
                if(isValid(row,col,cur))
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
        public void setBoard(int[][] _board)
        {
            this.board=_board;
        }
        public int  getCount(){
            return count;
        }
        public int[][] getBoard(){
            return board;
        }

}
