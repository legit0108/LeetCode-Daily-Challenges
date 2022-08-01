// Method - 1 : DP

// TC : O(m*n)
// SC : O(m*n)

class Solution {
    public int uniquePaths(int m, int n) {
        long dp[][] = new long[m][n];
        
        for(int row=0;row<m;row++){
            for(int col=0;col<n;col++){
                if(row==0&&col==0) dp[row][col] = 1;
                else if(row==0) dp[row][col] = dp[row][col-1];
                else if(col==0) dp[row][col] = dp[row-1][col];
                else dp[row][col] = dp[row-1][col]+dp[row][col-1];
            }
        }
        
        return (int)dp[m-1][n-1];
    }
}

// Method - 2 : Space optimized DP

// TC : O(m*n)
// SC : O(n)

class Solution {
    public int uniquePaths(int m, int n) {
        long dp[] = new long[n];
        
        for(int row=0;row<m;row++){
            for(int col=0;col<n;col++){
                if(row==0&&col==0) dp[0] = 1;
                else if(row==0) dp[col] = dp[col-1];
                else if(col==0) dp[col] = dp[col];
                else dp[col] = dp[col]+dp[col-1];
            }
        }
        
        return (int)dp[n-1];
    }
}

// Method - 3 : Math

// We need to select m-1 down moves or n-1 right moves from total of m+n-2 moves
// TC : O(min(m,n))
// SC : O(1)

class Solution {
    public int uniquePaths(int m, int n) {
        return ncr(m+n-2,n-1);
    }
    
    private int ncr(long n,long r){
        long ans = 1;
        if(n-r<r) r = n-r;
        
        for(long num = 0;num<r;num++){
            ans*=n-num;
            ans/=num+1;
        }
        
        return (int)ans;
    }
}