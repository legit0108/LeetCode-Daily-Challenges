// Solution-1: Two-pass, at every index, find flips required to convert to monotonic increasing string, take minimum overall indices

// TC: O(len)
// SC: O(1)

class Solution {
    public int minFlipsMonoIncr(String s) {
        int len = s.length();
        int ones = 0;
        
        for(int idx=0; idx<len; idx++){
            char ch = s.charAt(idx);
            
            if(ch=='1') ones++;
        }
        
        int minFlips = ones;
        int zeros = 0;
        
        for(int idx=len-1; idx>=0; idx--){
            char ch = s.charAt(idx);
            
            if(ch=='0') zeros++;
            if(ch=='1') ones--;
            
            minFlips = Math.min(minFlips, zeros+ones);
        }
        
        return minFlips;
    }
}


/*
Solution-2: DP, One-pass

This is a typical case of DP.

Let's see the sub-question of DP first.

Suppose that you have a string s, and the solution to the mono increase question is already solved. That is, for string s, minFlips flips are required for the string, and there were ones '1's in the original string s.

Let's see the next step of DP.

Within the string s, a new incoming character, say ch, is appended to the original string. The question is that, how should minFlips be updated based on the sub-question? We should discuss it case by case.

-> When '1' comes, no more flips should be applied since '1' is appended to the tail of the original string.
-> When '0' comes, things become a little bit complicated. There are two options for us: flip the newly appended '0' to '1' after minFlips flips for the original string or flip ones '1's in the original string to '0'. Hence, the result of the next step of DP, in the '0' case, is min(minFlips+1, ones).

Based on this analysis, the solution comes.

TC: O(len)
SC: O(1)
*/

class Solution {
    public int minFlipsMonoIncr(String s) {
        int len = s.length();
        int minFlips = 0;
        int ones = 0;
        
        for(int idx=0; idx<len; idx++){
            char ch = s.charAt(idx);
            
            if(ch=='0'){
                minFlips = Math.min(minFlips+1, ones);
            }else ones++;
        }
        
        return minFlips;
    }
}