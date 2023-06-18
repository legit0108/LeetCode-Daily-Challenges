// DP
// TC: O(rows*cols)
// SC: O(rows*cols)

class Solution {
    public int countPaths(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        long paths = 0;
        long mod = (long)1e9+7;
        Long[][] dp = new Long[rows][cols];
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0,-1}};  
        
        for(int row=0; row<rows; row++){
            for(int col=0; col<cols; col++){
                long pathsFromCell = getPaths(row, col, grid, dp, dirs, rows, cols, mod);
                paths = ((paths+pathsFromCell)+mod)%mod;
            }
        }
        
        int ans = (int)(paths%mod);
        if(ans<0) ans+=(int)mod;
        return ans;
    }
    
    private long getPaths(int row, int col, int[][] grid, Long[][] dp, int[][] dirs, int rows, int cols, long mod){
        if(dp[row][col]!=null) return dp[row][col];
        long val = grid[row][col];
        long paths = 1;
        
        for(int index=0; index<4; index++){
            int[] dir = dirs[index];
            
            int nextRow = row+dir[0];
            int nextCol = col+dir[1];
            
            if(isValid(nextRow, nextCol, rows, cols)){
                if(grid[nextRow][nextCol]>val){
                    paths = ((paths+getPaths(nextRow, nextCol, grid, dp, dirs, rows, cols, mod))+mod)%mod;
                }
            }
        }
        
        return dp[row][col] = paths;
    }
    
    private boolean isValid(int row, int col, int rows, int cols){
        if(row>=0 && row<rows && col>=0 && col<cols) return true;
        else return false;
    }
}