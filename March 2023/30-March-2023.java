// Solution-1: Recursion + memoization using HashMap<String, Boolean> as dp

class Solution {
    public boolean isScramble(String s1, String s2) {
        return isScrambledString(s2, s1, new HashMap<String, Boolean>());
    }
    
    private boolean isScrambledString(String s2, String s1, HashMap<String, Boolean> dp){ // returns true if s2 is a scrambled string of s1   
        int len = s2.length();
        if(len==1) return s1.equals(s2); // stop splitting when length is 1
        
        String key = s2+","+s1;
        if(dp.containsKey(key)) return dp.get(key);
        
        boolean ans = false;
        
        for(int index=1; index<len && !ans; index++){
            ans|=isScrambledString(s2.substring(0, index), s1.substring(0, index), dp) && isScrambledString(s2.substring(index), s1.substring(index), dp); // split but do not swap
            ans|=isScrambledString(s2.substring(0, index), s1.substring(len-index), dp) && isScrambledString(s2.substring(index), s1.substring(0, len-index), dp); // split and swap
        }
        
        dp.put(key, ans);
        return ans;
    }
}


// -----X-----X-----X-----X-----X-----X-----X-----X-----


// Solution-2: Recursion + memoization using 3D dp
// TC: O(len^4)
// SC: O(len^3)

class Solution {
    public boolean isScramble(String s1, String s2) {
        int len = s2.length();
        Boolean[][][] dp = new Boolean[len][len][len+1];
        return isScrambledString(s2, s1, 0, 0, len, dp);
    }
    
    private boolean isScrambledString(String s2, String s1, int start2, int start1, int len, Boolean[][][] dp){ // returns true if part of s2 from start2 having length len is a scrambled string of part of s1 starting from start1 having length len 
        if(len==1) return s2.charAt(start2)==s1.charAt(start1); // stop splitting when length is 1
        if(dp[start1][start2][len]!=null) return dp[start1][start2][len];
        
        boolean ans = false;
        
        for(int splitLen=1; splitLen<len && !ans; splitLen++){
            ans|=isScrambledString(s2, s1, start2, start1, splitLen, dp) && isScrambledString(s2, s1, start2+splitLen, start1+splitLen, len-splitLen, dp); // split but do not swap
            ans|=isScrambledString(s2, s1, start2+splitLen, start1, len-splitLen, dp) && isScrambledString(s2, s1, start2, start1+(len-splitLen), splitLen, dp); // split and swap
        }
        
        return dp[start1][start2][len] = ans;
    }
}


// -----X-----X-----X-----X-----X-----X-----X-----X-----


// Solution-3: Tabulation
// TC: O(length^4)
// SC: O(length^3)

class Solution {
    public boolean isScramble(String s1, String s2) {
        int length = s2.length();
        boolean[][][] dp = new boolean[length][length][length+1];
        
        for(int len=1; len<=length; len++){
            for(int start2=0; start2<=length-len; start2++){
                for(int start1=0; start1<=length-len; start1++){
                    if(len==1) dp[start2][start1][len] = (s2.charAt(start2)==s1.charAt(start1));
                    else{
                        boolean ans = false;
                        
                        for(int splitLen=1; splitLen<len && !ans; splitLen++){
                            ans|=dp[start2][start1][splitLen] && dp[start2+splitLen][start1+splitLen][len-splitLen]; // split but do not swap
                            ans|=dp[start2+splitLen][start1][len-splitLen] && dp[start2][start1+(len-splitLen)][splitLen]; // split and swap
                        }
                        
                        dp[start2][start1][len] = ans;
                    }
                }
            }
        }
        
        
        return dp[0][0][length];
    }
}