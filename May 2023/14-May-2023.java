// DP + Bitmasking

// TC: O(2^m*(m*m))
// SC: O(2^m)
// where m = 2*nums.length

// Solution-1: Memoization

class Solution {
    public int maxScore(int[] nums) {
        int len = nums.length;
        Integer[] dp = new Integer[1<<len];
        return getMaxScore(nums, 0, 1, len, dp);
    }
    
    private int getMaxScore(int[] nums, int mask, int operation, int len, Integer[] dp){
        if(operation>len/2) return 0;
        if(dp[mask]!=null) return dp[mask];
        
        int maxScore = 0;
        
        for(int index1=0; index1<len; index1++){
            for(int index2=index1+1; index2<len; index2++){
                if(get(mask, index1)==0 && get(mask, index2)==0){
                    int newMask = set(mask, index1);
                    newMask = set(newMask, index2);
                    
                    maxScore = Math.max(maxScore, operation*getGcd(nums[index1], nums[index2]) + getMaxScore(nums, newMask, operation+1, len, dp));
                
                }
            }
        }
        
        return dp[mask] = maxScore;
    }
    
    private int get(int mask, int bit){
        if((mask&(1<<bit))==0) return 0;
        else return 1;
    }
    
    private int set(int mask, int bit){
        mask = mask|(1<<bit);
        return mask;
    }
    
    private int getGcd(int num1, int num2){
        while(num2>0){
            int rem = num1%num2;
            num1 = num2;
            num2 = rem;
        }
        
        return num1;
    }
}


//-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----


// Solution-2: Tabulation

class Solution {
    public int maxScore(int[] nums) {
        int len = nums.length;
        
        int[][] gcdOfAllPairs = new int[len][len];
        
        for(int index1=0; index1<len; index1++){
            for(int index2=index1+1; index2<len; index2++){
                gcdOfAllPairs[index1][index2] = getGcd(nums[index1], nums[index2]);
            }
        }
        
        int[] dp = new int[1<<len];
        
        for(int mask=(1<<len)-1; mask>=0; mask--){
            int bitCount = Integer.bitCount(mask);
            if(bitCount%2!=0) continue;
            
            int operation = 1+bitCount/2;
            int maxScore = 0;
            
            for(int index1=0; index1<len; index1++){
                for(int index2=index1+1; index2<len; index2++){
                    if(get(mask, index1)==0 && get(mask, index2)==0){
                        int newMask = set(mask, index1);
                        newMask = set(newMask, index2);

                        maxScore = Math.max(maxScore, operation*gcdOfAllPairs[index1][index2] + dp[newMask]);
                    }
                }
            }
            
            dp[mask] = maxScore;
        }
                                            
        return dp[0];
    }
    
    private int get(int mask, int bit){
        if((mask&(1<<bit))==0) return 0;
        else return 1;
    }
    
    private int set(int mask, int bit){
        mask = mask|(1<<bit);
        return mask;
    }
    
    private int getGcd(int num1, int num2){
        while(num2>0){
            int rem = num1%num2;
            num1 = num2;
            num2 = rem;
        }
        
        return num1;
    }
}