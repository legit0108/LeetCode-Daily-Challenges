// For every character we have two options: delete that character or form 
// a contiguous group of that character

// TC: O(k*n*n)
// SC: O(k*n)

class Solution {
    public int getLengthOfOptimalCompression(String s, int k) {
        int len = s.length();
        Integer dp[][] = new Integer[len][k+1];
        
        return getLengthOfOptimalCompression(s, 0, len, k, dp);
    }
    
    private int getLengthOfOptimalCompression(String s, int idx, int len, int k, Integer dp[][]){
        if(idx==len) return 0;
        if(dp[idx][k]!=null) return dp[idx][k];
        
        int minLen = Integer.MAX_VALUE;
        
        if(k>0){ // delete current character
            int currLen = getLengthOfOptimalCompression(s, idx+1, len, k-1, dp);
            minLen = Math.min(minLen, currLen);
        }
        
        int initIdx = idx;
        char initCh = s.charAt(initIdx);
        int removals = 0;
        
        while(idx<len){ // form a group of initCh, remove all other characters
            char currCh = s.charAt(idx);
            if(currCh!=initCh) removals++;
            
            if(removals<=k){
                int groupLen = (idx-initIdx+1)-removals;
                int currLen = ((groupLen==1)?1:1+getDigits(groupLen))
                              + getLengthOfOptimalCompression(s, idx+1, len, k-removals, dp);
                minLen = Math.min(minLen, currLen);
            }else break;
            
            idx++;
        }
        
        return dp[initIdx][k] = minLen;
    }
    
    private int getDigits(int num){
        if(num>=1&&num<=9) return 1;
        else if(num>=10&&num<=99) return 2;
        else return 3;
    }
}