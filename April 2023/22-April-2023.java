// Solution-1: DP, Recursion + Memoization
// TC: O(len*len)
// SC: O(len*len)

class Solution {
    public int minInsertions(String s) {
        int len = s.length();
        return getMinInsertions(s, 0, len-1, new Integer[len][len]);
    }
    
    private int getMinInsertions(String s, int start, int end, Integer[][] dp){
        if(start>=end) return 0;
        if(dp[start][end]!=null) return dp[start][end];
        
        if(s.charAt(start) == s.charAt(end)) return dp[start][end] = getMinInsertions(s, start+1, end-1, dp); // if ends are equal we recur for rest of the string
        else return dp[start][end] = 1+Math.min(getMinInsertions(s, start+1, end, dp), getMinInsertions(s, start, end-1, dp)); // else we can either add a character at the front or end, we return min of these two
    }
}


// -----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X


// Solution-2: DP, Recursion + Memoization
// Minimum number of insertions required will be equal to length of string minus length of longest palindromic subsequence
// TC: O(len*len)
// SC: O(len*len)

class Solution {
    public int minInsertions(String s) {
        int len = s.length();
        return len-getLongestPalindromicSubseq(s, len, len, new Integer[len+1][len+1]);
    }
    
    private int getLongestPalindromicSubseq(String s, int len1, int len2, Integer[][] dp){
        if(len1==0 || len2==0) return 0;
        if(dp[len1][len2]!=null) return dp[len1][len2];
        
        int index1 = len1-1;
        int index2 = s.length()-len2;
        
        if(s.charAt(index1) == s.charAt(index2)) return dp[len1][len2] = 1+getLongestPalindromicSubseq(s, len1-1, len2-1, dp);
        else return dp[len1][len2] = Math.max(getLongestPalindromicSubseq(s, len1-1, len2, dp), getLongestPalindromicSubseq(s, len1, len2-1, dp));
    }
}


// Solution-3: DP, Tabulation
// TC: O(len*len)
// SC: O(len*len)

class Solution {
    public int minInsertions(String s) {
        int len = s.length();
        return len-getLongestPalindromicSubseq(s);
    }
    
    private int getLongestPalindromicSubseq(String s){
        int len = s.length();
        int[][] dp = new int[len+1][len+1];
        
        for(int len1=0; len1<=len; len1++){
            for(int len2=0; len2<=len; len2++){
                if(len1==0 || len2==0) continue;
                else{
                    int index1 = len1-1;
                    int index2 = len-len2;
                    
                    if(s.charAt(index1) == s.charAt(index2)) dp[len1][len2] = 1+dp[len1-1][len2-1];
                    else dp[len1][len2] = Math.max(dp[len1-1][len2], dp[len1][len2-1]);
                }
            }
        }
        
        return dp[len][len];
    }
}


// Solution-4: Space optimized DP
// TC: O(len*len)
// SC: O(len)

class Solution {
    public int minInsertions(String s) {
        int len = s.length();
        return len-getLongestPalindromicSubseq(s);
    }
    
    private int getLongestPalindromicSubseq(String s){
        int len = s.length();
        int[] dp = new int[len+1];
        
        for(int len1=0; len1<=len; len1++){
            int prev = 0;
            
            for(int len2=0; len2<=len; len2++){
                if(len1==0 || len2==0) continue;
                else{
                    int index1 = len1-1;
                    int index2 = len-len2;
                    
                    int curr = dp[len2];
                    
                    if(s.charAt(index1) == s.charAt(index2)) dp[len2] = 1+prev;
                    else dp[len2] = Math.max(dp[len2], dp[len2-1]);
                    
                    prev = curr;
                }
            }
        }
        
        return dp[len];
    }
}