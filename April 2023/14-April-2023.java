// Idea 1: Longest palindromic subsequence = Longest common subsequence with reversed string

// Solution-1: Recursion + memoization
// TC: O(len*len)
// SC: O(len*len)

class Solution {
    public int longestPalindromeSubseq(String s) {
        int len = s.length();
        Integer[][] dp = new Integer[len+1][len+1];
        
        return findLongestCommonSubseq(s, reverse(s), len, len, dp);
    }
    
    private int findLongestCommonSubseq(String s1, String s2, int len1, int len2, Integer[][] dp){
        if(len1==0 || len2==0) return 0; 
        if(dp[len1][len2]!=null) return dp[len1][len2];
        else{
           int index1 = len1-1;
           int index2 = len2-1;
           int longestCommonSubseqLen = 0;
        
           if(s1.charAt(index1) == s2.charAt(index2)) 
               longestCommonSubseqLen = 1+findLongestCommonSubseq(s1, s2, len1-1, len2-1, dp);
           else longestCommonSubseqLen = Math.max(findLongestCommonSubseq(s1, s2, len1-1, len2, dp), 
                                findLongestCommonSubseq(s1, s2, len1, len2-1, dp));
        
           return dp[len1][len2] = longestCommonSubseqLen;
        }
    }
    
    private String reverse(String str){
        char[] arr = str.toCharArray();
        
        int start = 0;
        int end = arr.length-1;
        
        while(start<end){
            char temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            
            start++;
            end--;
        }
        
        return new String(arr);
    }
}

// Solution-2: Tabulation
// TC: O(len*len)
// SC: O(len*len)

class Solution {
    public int longestPalindromeSubseq(String s) {
        int len = s.length();
        int[][] dp = new int[len+1][len+1];
        
        for(int len1=0; len1<=len; len1++){
            for(int len2=0; len2<=len; len2++){
                if(len1==0 || len2==0) dp[len1][len2] = 0;
                else{
                    int index1 = len1-1;
                    int index2 = len-(len2-1)-1;
                    int longestCommonSubseqLen = 0;
                    
                    if(s.charAt(index1) == s.charAt(index2)) longestCommonSubseqLen = 1+dp[len1-1][len2-1];
                    else longestCommonSubseqLen = Math.max(dp[len1-1][len2], dp[len1][len2-1]);
                    
                    dp[len1][len2] = longestCommonSubseqLen;
                }
            }
        }
        
        return dp[len][len];
    }
}

// Solution-3: Tabulation space-optimized
// TC: O(len*len)
// SC: O(len)

class Solution {
    public int longestPalindromeSubseq(String s) {
        int len = s.length();
        int[] dp = new int[len+1];
        
        for(int len1=0; len1<=len; len1++){
            int prev = 0;
            
            for(int len2=0; len2<=len; len2++){
                if(len1==0 || len2==0) dp[len2] = 0;
                else{
                    int index1 = len1-1;
                    int index2 = len-(len2-1)-1;
                    int longestCommonSubseqLen = 0;
                    
                    if(s.charAt(index1) == s.charAt(index2)) longestCommonSubseqLen = 1+prev;
                    else longestCommonSubseqLen = Math.max(dp[len2], dp[len2-1]);
                    
                    prev = dp[len2];
                    dp[len2] = longestCommonSubseqLen;
                }
            }
        }
        
        return dp[len];
    }
}


/* 
 Idea-2: If start and end characters of current subsequence are equal
 then we can get the longest palindromic subsequence by appending these two
 to the ends of longest palindromic subsequence obtained by recurring for rest of the string

 TC: O(len*len)
 SC: O(len*len), can be optimized to O(len)
*/

class Solution {
    public int longestPalindromeSubseq(String s) {
        int len = s.length();
        int[][] dp = new int[len+1][len+1];
        
        for(int start=len-1; start>=0; start--){
            for(int end=start; end<len; end++){
                char chStart = s.charAt(start);
                char chEnd = s.charAt(end);
                
                if(start==end){
                    if(chStart == chEnd) dp[start][end] = 1;
                }else{
                    if(chStart == chEnd) dp[start][end] = 2+dp[start+1][end-1];
                    else dp[start][end] = Math.max(dp[start+1][end], dp[start][end-1]);  
                }
            }
        }
        
        return dp[0][len-1];
    }
}