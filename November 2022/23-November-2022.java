// Solution - 1 : Maps for each row, col, grid

// TC: O(size*size)
// SC: O(size)

class Solution {
    public boolean isValidSudoku(char[][] board) {
        int size = board.length;
        
        int rowMap[][] = new int[size][10];
        int colMap[][] = new int[size][10];
        int gridMap[][] = new int[size][10];
        
        for(int row=0; row<size; row++){
            for(int col=0; col<size; col++){
                char ch = board[row][col];
                
                if(ch!='.'){
                    int num = ch-'0';

                    if(repetition(rowMap, colMap, gridMap, row, col, num)) return false;
                    
                    fill(rowMap, colMap, gridMap, row, col, num);
                }
            }
        }
        
        return true;
    }
    
    private boolean repetition(int rowMap[][], int colMap[][], int gridMap[][], int row, int col, int num){
        if(rowMap[row][num]==1 || colMap[col][num]==1 || gridMap[row/3*3+col/3][num]==1) return true;
        else return false;
    }
    
    private void fill(int rowMap[][], int colMap[][], int gridMap[][], int row, int col, int num){
        rowMap[row][num] = 1;
        colMap[col][num] = 1;
        gridMap[row/3*3+col/3][num] = 1;
    }
}

// Solution - 2 : Bit Manipulation

// TC: O(size*size)
// SC: O(size)

class Solution {
    public boolean isValidSudoku(char[][] board) {
        int size = board.length;
        
        int rowMap[] = new int[size];
        int colMap[] = new int[size];
        int gridMap[] = new int[size];
        
        for(int row=0; row<size; row++){
            for(int col=0; col<size; col++){
                char ch = board[row][col];
                
                if(ch!='.'){
                    int num = ch-'0';

                    if(repetition(rowMap, colMap, gridMap, row, col, num)) return false;
                    
                    fill(rowMap, colMap, gridMap, row, col, num);
                }
            }
        }
        
        return true;
    }
    
    private boolean repetition(int rowMap[], int colMap[], int gridMap[], int row, int col, int num){
        int mask = 1<<num;
        
        if(((rowMap[row]&mask)>0) || ((colMap[col]&mask)>0) || ((gridMap[row/3*3+col/3]&mask)>0)) return true;
        else return false;
    }
    
    private void fill(int rowMap[], int colMap[], int gridMap[], int row, int col, int num){
        int mask = 1<<num;
        
        rowMap[row]|=mask;
        colMap[col]|=mask;
        gridMap[row/3*3+col/3]|=mask;
    }
}

