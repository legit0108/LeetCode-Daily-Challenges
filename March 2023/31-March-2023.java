// Solution-1: Recursion + memoization
// TC: O(rows*cols*k*(rows+cols))
// SC: O(rows*cols*k)

class Solution {
    public int ways(String[] pizza, int k) {
        int rows = pizza.length;
        int cols = pizza[0].length();
        
        long[][] count = getCount(rows, cols, pizza); // count[row][col] stores the count of apples in the submatrix from (row, col) to (rows-1, cols-1)
        
        Long[][][] dp = new Long[rows][cols][k+1];
        long mod = (long)1e9+7;
        long waysToCutPizza = getWaysToCutPizza(0, 0, k, rows, cols, mod, count, dp);
        
        int ans = (int)((waysToCutPizza+mod)%mod);
        if(ans<0) ans+=(int)mod;
        return ans;
    }
    
    private long[][] getCount(int rows, int cols, String[] pizza){
        long[][] count = new long[rows][cols];
        
        for(int row=rows-1; row>=0; row--){
            for(int col=cols-1; col>=0; col--){
                if(pizza[row].charAt(col)=='A') count[row][col]++;
                
                if(row+1<rows) count[row][col]+=count[row+1][col];
                if(col+1<cols) count[row][col]+=count[row][col+1];
                
                if(row+1<rows && col+1<cols) count[row][col]-=count[row+1][col+1];
            }
        }
        
        return count;
    }
    
    private long getWaysToCutPizza(int row, int col, int k, int rows, int cols, long mod, long[][] count, Long[][][] dp){
        if(row>=rows || col>=cols) return 0;
        if(k==1){
            if(count[row][col]>0) return 1; // if 1 piece is left, return 1 only if the entire piece has atleast one apple
            else return 0;
        }
        if(dp[row][col][k]!=null) return dp[row][col][k];
        
        long waysToCutPizza = 0;
        
        for(int currRow=row; currRow<rows; currRow++){ // horizontal cuts
            long currCount = count[row][col];
            if(currRow+1<rows) currCount-=count[currRow+1][col];
            
            if(currCount>0){
                long currWaysToCutPizza = getWaysToCutPizza(currRow+1, col, k-1, rows, cols, mod, count, dp);
                waysToCutPizza = ((waysToCutPizza+currWaysToCutPizza)+mod)%mod;
            }
        }
        
        for(int currCol=col; currCol<cols; currCol++){ // vertical cuts
            long currCount = count[row][col];
            if(currCol+1<cols) currCount-=count[row][currCol+1];
            
            if(currCount>0){
                long currWaysToCutPizza = getWaysToCutPizza(row, currCol+1, k-1, rows, cols, mod, count, dp);
                waysToCutPizza = ((waysToCutPizza+currWaysToCutPizza)+mod)%mod;
            }
        }
        
        return dp[row][col][k] = waysToCutPizza;
    }
}


// -----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----


// Solution-2: Iterative DP
// TC: O(rows*cols*k*(rows+cols))
// SC: O(rows*cols*k)

class Solution {
    public int ways(String[] pizza, int k) {
        int rows = pizza.length;
        int cols = pizza[0].length();
        
        long[][] count = getCount(rows, cols, pizza);
        
        long[][][] dp = new long[rows+1][cols+1][k+1];
        long mod = (long)1e9+7;
        
        for(int pieces=1; pieces<=k; pieces++){
            for(int row=0; row<rows; row++){
                for(int col=0; col<cols; col++){
                    if(pieces==1){
                        if(count[row][col]>0) dp[row][col][pieces] = 1;
                    }
                    else{
                        long waysToCutPizza = 0;
        
                        for(int currRow=row; currRow<rows; currRow++){
                            long currCount = count[row][col];
                            if(currRow+1<rows) currCount-=count[currRow+1][col];

                            if(currCount>0){
                                long currWaysToCutPizza = dp[currRow+1][col][pieces-1];
                                waysToCutPizza = ((waysToCutPizza+currWaysToCutPizza)+mod)%mod;
                            }
                        }
        
                        for(int currCol=col; currCol<cols; currCol++){
                            long currCount = count[row][col];
                            if(currCol+1<cols) currCount-=count[row][currCol+1];

                            if(currCount>0){
                                long currWaysToCutPizza = dp[row][currCol+1][pieces-1];
                                waysToCutPizza = ((waysToCutPizza+currWaysToCutPizza)+mod)%mod;
                            }
                        }
        
                        dp[row][col][pieces] = waysToCutPizza;
                    }
                }
            }
        }
        
        long waysToCutPizza = dp[0][0][k];
        
        int ans = (int)((waysToCutPizza+mod)%mod);
        if(ans<0) ans+=(int)mod;
        return ans;
    }
    
    private long[][] getCount(int rows, int cols, String[] pizza){
        long[][] count = new long[rows][cols];
        
        for(int row=rows-1; row>=0; row--){
            for(int col=cols-1; col>=0; col--){
                if(pizza[row].charAt(col)=='A') count[row][col]++;
                
                if(row+1<rows) count[row][col]+=count[row+1][col];
                if(col+1<cols) count[row][col]+=count[row][col+1];
                
                if(row+1<rows && col+1<cols) count[row][col]-=count[row+1][col+1];
            }
        }
        
        return count;
    }
}


// -----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----


// Solution-3: Iterative DP, space optimized
// Optimize Solution-2 since the value of the current state only depends on its immediate states

// TC: O(rows*cols*k*(rows+cols))
// SC: O(rows*cols)

class Solution {
    public int ways(String[] pizza, int k) {
        int rows = pizza.length;
        int cols = pizza[0].length();
        
        long[][] count = getCount(rows, cols, pizza);
        
        long[][]dp = new long[rows+1][cols+1];
        long mod = (long)1e9+7;
        
        for(int pieces=1; pieces<=k; pieces++){
            for(int row=0; row<rows; row++){
                for(int col=0; col<cols; col++){
                    if(pieces==1){
                        if(count[row][col]>0) dp[row][col] = 1;
                    }
                    else{
                        long waysToCutPizza = 0;
        
                        for(int currRow=row; currRow<rows; currRow++){
                            long currCount = count[row][col];
                            if(currRow+1<rows) currCount-=count[currRow+1][col];

                            if(currCount>0){
                                long currWaysToCutPizza = dp[currRow+1][col];
                                waysToCutPizza = ((waysToCutPizza+currWaysToCutPizza)+mod)%mod;
                            }
                        }
        
                        for(int currCol=col; currCol<cols; currCol++){
                            long currCount = count[row][col];
                            if(currCol+1<cols) currCount-=count[row][currCol+1];

                            if(currCount>0){
                                long currWaysToCutPizza = dp[row][currCol+1];
                                waysToCutPizza = ((waysToCutPizza+currWaysToCutPizza)+mod)%mod;
                            }
                        }
        
                        dp[row][col] = waysToCutPizza;
                    }
                }
            }
        }
        
        long waysToCutPizza = dp[0][0];
        
        int ans = (int)((waysToCutPizza+mod)%mod);
        if(ans<0) ans+=(int)mod;
        return ans;
    }
    
    private long[][] getCount(int rows, int cols, String[] pizza){
        long[][] count = new long[rows][cols];
        
        for(int row=rows-1; row>=0; row--){
            for(int col=cols-1; col>=0; col--){
                if(pizza[row].charAt(col)=='A') count[row][col]++;
                
                if(row+1<rows) count[row][col]+=count[row+1][col];
                if(col+1<cols) count[row][col]+=count[row][col+1];
                
                if(row+1<rows && col+1<cols) count[row][col]-=count[row+1][col+1];
            }
        }
        
        return count;
    }
}