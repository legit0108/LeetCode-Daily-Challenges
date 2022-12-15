// Solution - 1 : DP

// TC : O(size1*size2)
// SC : O(size1*size2)

class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int size1 = text1.length();
        int size2 = text2.length();
        
        int dp[][] = new int[size1+1][size2+1];
        
        for(int len1=0; len1<=size1; len1++){
            for(int len2=0; len2<=size2; len2++){
                if(len1==0 || len2==0) dp[len1][len2] = 0;
                else{
                    int idx1 = len1-1;
                    int idx2 = len2-1;
                
                    if(text1.charAt(idx1) == text2.charAt(idx2)) dp[len1][len2] = 1 + dp[len1-1][len2-1];
                    else dp[len1][len2] = Math.max(dp[len1-1][len2], dp[len1][len2-1]);
                }
            }
        }
        
        return dp[size1][size2];
    }
}

// Solution - 2 : Space optimized DP

// TC : O(size1*size2)
// SC : O(size2)

class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int size1 = text1.length();
        int size2 = text2.length();
        
        int dp[] = new int[size2+1];
        
        for(int len1=0; len1<=size1; len1++){
            int nextDp[] = new int[size2+1];
            
            for(int len2=0; len2<=size2; len2++){
                if(len1==0 || len2==0) nextDp[len2] = 0;
                else{
                    int idx1 = len1-1;
                    int idx2 = len2-1;
                
                    if(text1.charAt(idx1) == text2.charAt(idx2)) nextDp[len2] = 1 + dp[len2-1];
                    else nextDp[len2] = Math.max(dp[len2], nextDp[len2-1]);
                }
            }
            
            dp = nextDp;
        }
        
        return dp[size2];
    }
}

// Bonus solution : Space optimized DP but using only single row

// TC : O(size1*size2)
// SC : O(size2)

class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int size1 = text1.length();
        int size2 = text2.length();
        
        int dp[] = new int[size2+1];
        
        for(int len1=0; len1<=size1; len1++){
            int prevAns = 0;
            
            for(int len2=0; len2<=size2; len2++){
                if(len1==0 || len2==0) dp[len2] = 0;
                else{
                    int idx1 = len1-1;
                    int idx2 = len2-1;
                    
                    int ans = 0;
                    
                    if(text1.charAt(idx1) == text2.charAt(idx2)) ans = 1 + prevAns;
                    else ans = Math.max(dp[len2], dp[len2-1]);
                    
                    prevAns = dp[len2];
                    dp[len2] = ans;
                }
            }
        }
        
        return dp[size2];
    }
}