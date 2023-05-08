// Solution-1: Two loops
// TC: O(size)
// SC: O(1)

class Solution {
    public int diagonalSum(int[][] mat) {
        int sum = 0;
        int size = mat.length;
        
        sum+=getDiagonalSum(mat, 0, 0, size-1, size-1, 1, 1); // get first diagonal sum
        sum+=getDiagonalSum(mat, 0, size-1, size-1, 0, 1, -1); // get second diagonal sum
        if(size%2!=0) sum-=mat[size/2][size/2]; // middle element counted twice in case size is odd, subtract it
        
        return sum;
    }
    
    private int getDiagonalSum(int[][] mat, int srcRow, int srcCol, int destRow, int destCol, int rowDelta, int colDelta){
        int sum = 0;
        int row = srcRow;
        int col = srcCol;
        
        while(row<=destRow){
            sum+=mat[row][col];
            
            row+=rowDelta;
            col+=colDelta;
        }
        
        return sum;
    }
}


// Solution-2: One loop
// TC: O(size)
// SC: O(1)

class Solution {
    public int diagonalSum(int[][] mat) {
        int size = mat.length;
        int index = 0;
        int sum = 0;
        
        while(index<size){
            int[] row = mat[index];
            
            sum+=row[index];
            if(size-index-1!=index) sum+=row[size-index-1];
            
            index++;
        }
        
        return sum;
    }
}