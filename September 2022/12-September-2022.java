// Idea : Gain score by playing lowest value tokens, gain power by playing highest value tokens

// TC : O(len*log(len))
// SC : O(1)

class Solution {
    public int bagOfTokensScore(int[] tokens, int power) {
        Arrays.sort(tokens);
        int len = tokens.length;
        int start = 0;
        int end = len-1;
        int currPower = power;
        int currScore = 0;
        int maxScore = 0;
        
        while(start<=end){
            while(currScore>0 && end>start && currPower < tokens[start]){
                currPower+=tokens[end];
                end--;
                currScore--;
            }
            
            boolean secondLoopRun = false;
            
            while(start<=end && currPower>=tokens[start]){
                currPower-=tokens[start];
                start++;
                currScore++;
                secondLoopRun = true;
            }
             
            maxScore = Math.max(maxScore, currScore);
            if(!secondLoopRun) break;
        }
        
        return maxScore;
    }
}