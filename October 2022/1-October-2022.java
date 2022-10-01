// Fibonacci dp, f(n) = f(n-1) + f(n-2)

// TC : O(len)
// SC : O(1)

class Solution {
    public int numDecodings(String s) {
        int len = s.length();
        int ways1 = 1; // number of ways to decode length-1
        int ways2 = 0; // number of ways to decode length-2
        
        for(int idx=0; idx<len; idx++){
            int num = s.charAt(idx)-'0';
            int currWays = (num == 0)?0:ways1;
            
            if(idx>=1){
                num = (s.charAt(idx-1)-'0')*10+num;
                if(num>=10 && num<=26) currWays+=ways2;
            }
            
            ways2 = ways1;
            ways1 = currWays;
        }
        
        return ways1; // number of ways to decode length-1 (entire s)
    }
}