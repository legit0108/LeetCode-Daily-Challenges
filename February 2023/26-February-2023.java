// DP, chose the minimum among 3 available options

// Solution-1
// TC: O(len1*len2)
// SC: O(len1*len2)

class Solution {
    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1+1][len2+1];
        
        for(int currLen1=0; currLen1<=len1; currLen1++){
            for(int currLen2=0; currLen2<=len2; currLen2++){
                if(currLen1==0) dp[currLen1][currLen2] = currLen2;
                else if(currLen2==0) dp[currLen1][currLen2] = currLen1;
                else{
                    int idx1 = currLen1-1;
                    int idx2 = currLen2-1;
                    
                    if(word1.charAt(idx1) == word2.charAt(idx2)) dp[currLen1][currLen2] = dp[currLen1-1][currLen2-1]; // if characters are same, find minimum operations required for the rest of the string
                    else{
                        dp[currLen1][currLen2] = 1 + Math.min(Math.min(dp[currLen1][currLen2-1], dp[currLen1-1][currLen2]), dp[currLen1-1][currLen2-1]); // 1 + min(insert, delete, replace) 
                    }
                }
            }
        }
        
        return dp[len1][len2];
    }
}


// Solution-2
// TC: O(len1*len2)
// SC: O(len2)

class Solution {
    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        int[] dp = new int[len2+1];
        
        for(int currLen1=0; currLen1<=len1; currLen1++){
            int[] new_dp = new int[len2+1];
            
            for(int currLen2=0; currLen2<=len2; currLen2++){
                if(currLen1==0) new_dp[currLen2] = currLen2;
                else if(currLen2==0) new_dp[currLen2] = currLen1;
                else{
                    int idx1 = currLen1-1;
                    int idx2 = currLen2-1;
                    
                    if(word1.charAt(idx1) == word2.charAt(idx2)) new_dp[currLen2] = dp[currLen2-1];
                    else{
                        new_dp[currLen2] = 1 + Math.min(Math.min(new_dp[currLen2-1], dp[currLen2]), dp[currLen2-1]);
                    }
                }
            }
            
            dp = new_dp;
        }
        
        return dp[len2];
    }
}