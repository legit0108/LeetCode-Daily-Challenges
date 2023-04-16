// Idea: Preprocess the count of characters at every index of a word in words, apply DP


// Solution-1: Recursion + memoization
// TC: O(wordsLen*wordLen + targetLen*wordLen)
// SC: O(wordLen + targetLen*wordLen)

class Solution {
    public int numWays(String[] words, String target) {
        int wordsLen = words.length;
        int wordLen = words[0].length();
        long[][] map = new long[wordLen][26];
        
        for(int wordsIndex=0; wordsIndex<wordsLen; wordsIndex++){
            String word = words[wordsIndex];
            
            for(int index=0; index<wordLen; index++){
                char ch = word.charAt(index);
                int position = ch-'a';
                map[index][position]++;
            }
        }
        
        
        long mod = (long)1e9+7;
        int targetLen = target.length();
        long ways = getNumWays(0, 0, targetLen, wordLen, target, map, new Long[targetLen][wordLen], mod);
        
        int ans = (int)(ways%mod);
        if(ans<0) ans+=(int)mod;
        
        return ans;
    }
    
    private long getNumWays(int targetIndex, int wordIndex, int targetLen, int wordLen, String target, long[][] map, Long[][] dp, long mod){
        if(targetIndex==targetLen) return 1l;
        if(wordIndex==wordLen) return 0l;
        if(dp[targetIndex][wordIndex]!=null) return dp[targetIndex][wordIndex];
        
        char ch = target.charAt(targetIndex);
        int position = ch-'a';
        
        long pick = map[wordIndex][position]*getNumWays(targetIndex+1, wordIndex+1, targetLen, wordLen, target, map, dp, mod);
        long skip = getNumWays(targetIndex, wordIndex+1, targetLen, wordLen, target, map, dp, mod);
        
        long ways = ((pick+skip)+mod)%mod;
        return dp[targetIndex][wordIndex] = ways;
    }
}


// Solution-2: Tabulation
// TC: O(wordsLen*wordLen + targetLen*wordLen)
// SC: O(wordLen + targetLen*wordLen)

class Solution {
    public int numWays(String[] words, String target) {
        int wordsLen = words.length;
        int wordLen = words[0].length();
        long[][] map = new long[wordLen][26];
        
        for(int wordsIndex=0; wordsIndex<wordsLen; wordsIndex++){
            String word = words[wordsIndex];
            
            for(int index=0; index<wordLen; index++){
                char ch = word.charAt(index);
                int position = ch-'a';
                map[index][position]++;
            }
        }
        
        
        long mod = (long)1e9+7;
        int targetLen = target.length();
        long[][] dp = new long[wordLen+1][targetLen+1];
        
        for(int wordIndex=wordLen; wordIndex>=0; wordIndex--){
            for(int targetIndex=0; targetIndex<=targetLen; targetIndex++){
                if(targetIndex==targetLen) dp[wordIndex][targetIndex] = 1;
                else if(wordIndex==wordLen) dp[wordIndex][targetIndex] = 0;
                else{
                    char ch = target.charAt(targetIndex);
                    int position = ch-'a';

                    long pick = map[wordIndex][position]*dp[wordIndex+1][targetIndex+1];
                    long skip = dp[wordIndex+1][targetIndex];

                    long ways = ((pick+skip)+mod)%mod;
                    dp[wordIndex][targetIndex] = ways;
                }
            }
        }
        
        long ways = dp[0][0];
        
        int ans = (int)(ways%mod);
        if(ans<0) ans+=(int)mod;
        
        return ans;
    }
}


// Solution-3: Space optimized tabulation
// TC: O(wordsLen*wordLen + targetLen*wordLen)
// SC: O(wordLen + targetLen)

class Solution {
    public int numWays(String[] words, String target) {
        int wordsLen = words.length;
        int wordLen = words[0].length();
        long[][] map = new long[wordLen][26];
        
        for(int wordsIndex=0; wordsIndex<wordsLen; wordsIndex++){
            String word = words[wordsIndex];
            
            for(int index=0; index<wordLen; index++){
                char ch = word.charAt(index);
                int position = ch-'a';
                map[index][position]++;
            }
        }
        
        
        long mod = (long)1e9+7;
        int targetLen = target.length();
        long[] dp = new long[targetLen+1];
        
        for(int wordIndex=wordLen; wordIndex>=0; wordIndex--){
            for(int targetIndex=0; targetIndex<=targetLen; targetIndex++){
                if(targetIndex==targetLen) dp[targetIndex] = 1;
                else if(wordIndex==wordLen) dp[targetIndex] = 0;
                else{
                    char ch = target.charAt(targetIndex);
                    int position = ch-'a';

                    long pick = map[wordIndex][position]*dp[targetIndex+1];
                    long skip = dp[targetIndex];

                    long ways = ((pick+skip)+mod)%mod;
                    dp[targetIndex] = ways;
                }
            }
        }
        
        long ways = dp[0];
        
        int ans = (int)(ways%mod);
        if(ans<0) ans+=(int)mod;
        
        return ans;
    }
}


// Solution-4: Further optimize Solution-3 by counting frequency of characters on the go
// TC: O(wordLen*(wordsLen+targetLen))
// SC: O(targetLen)

class Solution {
    public int numWays(String[] words, String target) {
        int wordsLen = words.length;
        int wordLen = words[0].length();
        int targetLen = target.length();
        
        long mod = (long)1e9+7;
        long[] dp = new long[targetLen+1];
        
        for(int wordIndex=wordLen; wordIndex>=0; wordIndex--){
            long[] map = new long[26];
            
            if(wordIndex<wordLen){
                for(int wordsIndex=0; wordsIndex<wordsLen; wordsIndex++){
                    String word = words[wordsIndex];
                    char ch = word.charAt(wordIndex);

                    int position = ch-'a';
                    map[position]++;
                }
            }
            
            for(int targetIndex=0; targetIndex<=targetLen; targetIndex++){
                if(targetIndex==targetLen) dp[targetIndex] = 1;
                else if(wordIndex==wordLen) dp[targetIndex] = 0;
                else{
                    char ch = target.charAt(targetIndex);
                    int position = ch-'a';

                    long pick = map[position]*dp[targetIndex+1];
                    long skip = dp[targetIndex];

                    long ways = ((pick+skip)+mod)%mod;
                    dp[targetIndex] = ways;
                }
            }
        }
        
        long ways = dp[0];
        
        int ans = (int)(ways%mod);
        if(ans<0) ans+=(int)mod;
        
        return ans;
    }
}