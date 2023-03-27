// Solution-1: DP
// TC: O(rows*cols)
// SC: O(rows*cols)

class Solution {
    public int minPathSum(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] dp = new int[rows][cols];
        
        for(int row=0; row<rows; row++){
           for(int col=0; col<cols; col++){
               int num = grid[row][col];
               
               dp[row][col] = num + getMinOfTopAndLeft(dp, row, col); // can reach current cell from either top cell or left cell, take the minimum of two
           }
        }
        
        return dp[rows-1][cols-1];
    }
    
    private int getMinOfTopAndLeft(int[][] dp, int row, int col){
        int min = Integer.MAX_VALUE;
        
        if(row-1>=0) min = Math.min(min, dp[row-1][col]);
        if(col-1>=0) min = Math.min(min, dp[row][col-1]);
        
        if(min==Integer.MAX_VALUE) min = 0; // row==0 && col==0
        return min;
    }
}


// -----X-----X-----X-----X-----X-----X-----X-----X-----


// Solution-2: Space optimized DP
// TC: O(rows*cols)
// SC: O(cols)

class Solution {
    public int minPathSum(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[] dp = new int[cols];
        
        for(int row=0; row<rows; row++){
           for(int col=0; col<cols; col++){
               int num = grid[row][col];
               
               dp[col] = num + getMinOfTopAndLeft(dp, row, col);
           }
        }
        
        return dp[cols-1];
    }
    
    private int getMinOfTopAndLeft(int[] dp, int row, int col){
        int min = Integer.MAX_VALUE;
        
        if(row-1>=0) min = Math.min(min, dp[col]);
        if(col-1>=0) min = Math.min(min, dp[col-1]);
        
        if(min==Integer.MAX_VALUE) min = 0;
        return min;
    }
}