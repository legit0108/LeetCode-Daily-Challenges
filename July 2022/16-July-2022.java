// Method - 1 : Recursion + memoization / Top Down

// TC : O(m*n*maxMove)
// SC : O(m*n*maxMove)

class Solution {
    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        long mod = (long)1e9+7;
        Long dp[][][] = new Long[m][n][maxMove+1];
        long paths = findPaths(m,n,maxMove,startRow,startColumn,mod,dp);
        
        int finalPaths = (int)(paths%mod);
        if(finalPaths<0) finalPaths+=(int)mod;
        return finalPaths;
    }
    
    private long findPaths(int rows,int cols,int movesLeft,int row,int col,long mod,
    Long dp[][][]){
        if(row>=rows||col>=cols||row<0||col<0) return 1;
        if(movesLeft==0) return 0;
        if(dp[row][col][movesLeft]!=null) return dp[row][col][movesLeft];
        long paths = 0;
        
        paths = ((paths%mod) + (findPaths(rows,cols,movesLeft-1,row-1,col,mod,dp)%mod))%mod;
        paths = ((paths%mod) + (findPaths(rows,cols,movesLeft-1,row+1,col,mod,dp)%mod))%mod;
        paths = ((paths%mod) + (findPaths(rows,cols,movesLeft-1,row,col-1,mod,dp)%mod))%mod;
        paths = ((paths%mod) + (findPaths(rows,cols,movesLeft-1,row,col+1,mod,dp)%mod))%mod;
        
        return dp[row][col][movesLeft] = paths;
    }
}

// Method - 2 : Tabulation / Bottom Up

// TC : O(m*n*maxMove)
// SC : O(m*n)

class Solution {
    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        long dp[][] = new long[m][n];
        long mod = (long)1e9+7;
        int dirs[][] = {{-1,0},{1,0},{0,1},{0,-1}};
        dp[startRow][startColumn] = 1;
        long paths = 0;
        
        for(int move =0;move<maxMove;move++){
            long nextDp[][] = new long[m][n];
            
            for(int row=0;row<m;row++){
                for(int col=0;col<n;col++){
                    for(int dir=0;dir<4;dir++){
                        int currRow = row+dirs[dir][0];
                        int currCol = col+dirs[dir][1];
                        
                        if(currRow<0||currCol<0||currRow>=m||currCol>=n){
                            paths = ((paths%mod)+(dp[row][col]%mod))%mod; 
                        }else{
                            nextDp[row][col] 
                            = ((nextDp[row][col]%mod) + (dp[currRow][currCol]%mod))%mod;
                        }
                    }
                }
            }
            
            dp = nextDp;
        }
        
        int finalPaths = (int)paths;
        if(finalPaths<0) finalPaths+=(int)mod;
        return finalPaths;
    }
}