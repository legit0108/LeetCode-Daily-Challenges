// Solution - 1 : DP

// TC : O(rows*cols)
// SC : O(rows*cols)

class Solution {
    public int minFallingPathSum(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        int dp[][] = new int[rows][cols];
        int minFallingPathSum = Integer.MAX_VALUE;
        
        for(int row=rows-1; row>=0; row--){
            for(int col=cols-1; col>=0; col--){
                int val = matrix[row][col];
                
                dp[row][col] = val + getMin(dp, row, col, rows, cols);
                
                if(row==0) minFallingPathSum = Math.min(minFallingPathSum, dp[row][col]);
            }
        }
        
        return minFallingPathSum;
    }
    
    private int getMin(int dp[][], int row, int col, int rows, int cols){
        int min = Integer.MAX_VALUE;
        
        if(row+1<rows && col-1>=0) min = Math.min(min, dp[row+1][col-1]);
        if(row+1<rows) min = Math.min(min, dp[row+1][col]);
        if(row+1<rows && col+1<cols) min = Math.min(min, dp[row+1][col+1]);
        
        if(min==Integer.MAX_VALUE) min = 0;
        
        return min;
    }
}

// Solution - 2 : Space-optimized DP

// TC : O(rows*cols)
// SC : O(cols)

class Solution {
    public int minFallingPathSum(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        int dp[] = new int[cols];
        int minFallingPathSum = Integer.MAX_VALUE;
        
        for(int row=rows-1; row>=0; row--){
            int nextDp[] = new int[cols];
            
            for(int col=cols-1; col>=0; col--){
                int val = matrix[row][col];
                
                nextDp[col] = val + getMin(dp, row, col, rows, cols);
                
                if(row==0) minFallingPathSum = Math.min(minFallingPathSum, nextDp[col]);
            }
            
            dp = nextDp;
        }
        
        return minFallingPathSum;
    }
    
    private int getMin(int dp[], int row, int col, int rows, int cols){
        int min = Integer.MAX_VALUE;
        
        if(row+1<rows && col-1>=0) min = Math.min(min, dp[col-1]);
        if(row+1<rows) min = Math.min(min, dp[col]);
        if(row+1<rows && col+1<cols) min = Math.min(min, dp[col+1]);
        
        if(min==Integer.MAX_VALUE) min = 0;
        
        return min;
    }
}