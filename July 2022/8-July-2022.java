// Method-1 : top-down
// TC : O(m * target * n * n)
// SC : O(m * target * n)

class Solution {
    Integer dp[][][];
    
    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        dp = new Integer[m+1][target+1][22];
        int minCost = paint(houses,cost,m,n,target,21);
        if(minCost==(int)1e9) return -1;
        return minCost;
    }
    
    private int paint(int houses[],int cost[][],int m,int n,int target,int lastColor){
        if(m==0){
            if(target==1) return 0;
            return (int)1e9;
        }
        if(target==0) return (int)1e9;
        
        if(dp[m][target][lastColor]!=null) return dp[m][target][lastColor];
        
        int minCost = (int)1e9;
        
        if(houses[m-1]!=0){
            int color = houses[m-1];
            if(color==lastColor||lastColor==21){
                minCost = paint(houses,cost,m-1,n,target,color);
            }
            else minCost = paint(houses,cost,m-1,n,target-1,color);
        }else{
            for(int color = n;color>=1;color--){
                if(color==lastColor||lastColor==21){ 
                    minCost = Math.min(minCost,cost[m-1][color-1]
                              +paint(houses,cost,m-1,n,target,color));
                }else minCost = Math.min(minCost,cost[m-1][color-1]
                              +paint(houses,cost,m-1,n,target-1,color)); 
            }
        }
        
        return dp[m][target][lastColor] = minCost;
    }
}

// Method-2 : bottom up
// TC : O(m * target * n * n)
// SC : O(m * target * n)

class Solution {
    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        int dp[][][] = new int[m+1][target+1][22];
        
        for(int curr_m=0;curr_m<=m;curr_m++){
            for(int curr_target=0;curr_target<=target;curr_target++){
                for(int lastColor=0;lastColor<=21;lastColor++){
                    if(curr_m==0){
                        if(curr_target==1) dp[curr_m][curr_target][lastColor] = 0;
                        else dp[curr_m][curr_target][lastColor] = (int)1e9;
                    }else if(curr_target==0){
                        dp[curr_m][curr_target][lastColor] = (int)1e9;
                    }else{
                        if(houses[curr_m-1]!=0){
                            int color = houses[curr_m-1];
                            
                            if(color==lastColor||lastColor==21){
                                dp[curr_m][curr_target][lastColor] 
                                = dp[curr_m-1][curr_target][color];
                            }else{
                                dp[curr_m][curr_target][lastColor]
                                = dp[curr_m-1][curr_target-1][color];    
                            }
                        }else{
                            int minCost = (int)1e9;
                            
                            for(int color=n;color>=1;color--){
                                if(color==lastColor||lastColor==21){
                                    minCost = Math.min(minCost,cost[curr_m-1][color-1]
                                              +dp[curr_m-1][curr_target][color]);
                                }else{
                                    minCost = Math.min(minCost,cost[curr_m-1][color-1]
                                              +dp[curr_m-1][curr_target-1][color]);
                                }
                            }
                            
                            dp[curr_m][curr_target][lastColor] = minCost;
                        }
                    }
                }
            }
        }
        
        int minCost = dp[m][target][21]; 
        if(minCost==(int)1e9) return -1;
        return minCost;
    }
}

// Method-3 : space optimized bottom-up
// TC : O(m * target * n * n)
// SC : O(target * n)

class Solution {
    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        int dp[][][] = new int[2][target+1][22];
        
        for(int curr_m=0;curr_m<=m;curr_m++){
            for(int curr_target=0;curr_target<=target;curr_target++){
                for(int lastColor=0;lastColor<=21;lastColor++){
                    if(curr_m==0){
                        if(curr_target==1) dp[1][curr_target][lastColor] = 0;
                        else dp[1][curr_target][lastColor] = (int)1e9;
                    }else if(curr_target==0){
                        dp[1][curr_target][lastColor] = (int)1e9;
                    }else{
                        if(houses[curr_m-1]!=0){
                            int color = houses[curr_m-1];
                            
                            if(color==lastColor||lastColor==21){
                                dp[1][curr_target][lastColor] 
                                = dp[0][curr_target][color];
                            }else{
                                dp[1][curr_target][lastColor]
                                = dp[0][curr_target-1][color];    
                            }
                        }else{
                            int minCost = (int)1e9;
                            
                            for(int color=n;color>=1;color--){
                                if(color==lastColor||lastColor==21){
                                    minCost = Math.min(minCost,cost[curr_m-1][color-1]
                                              +dp[0][curr_target][color]);
                                }else{
                                    minCost = Math.min(minCost,cost[curr_m-1][color-1]
                                              +dp[0][curr_target-1][color]);
                                }
                            }
                            
                            dp[1][curr_target][lastColor] = minCost;
                        }
                    }
                }
            }
            
            for(int curr_target=0;curr_target<=target;curr_target++){
                for(int lastColor=0;lastColor<=21;lastColor++){
                    dp[0][curr_target][lastColor] = dp[1][curr_target][lastColor];
                }
            }
        }
        
        int minCost = dp[1][target][21]; 
        if(minCost==(int)1e9) return -1;
        return minCost;
    }
}