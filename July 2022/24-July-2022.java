// TC : O(rows+cols)
// SC : O(1)

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int row = 0;
        int col = cols-1;
        
        while(row<rows&&col>=0){
            if(matrix[row][col]>target) col--;
            else if(matrix[row][col]<target) row++;
            else return true;
        }
        
        return false;
    }
}