// The idea is same as Stone Game II (yesterday's question), except we do not need to keep track of extra value m 

// Solution-1: Recursion + Memoization
// TC: O(n)
// SC: O(n)

class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int len = stoneValue.length;
        int[] prefixSumArr = getPrefixSumArr(stoneValue, len);
        
        Integer[] dp = new Integer[len];
        int scoreOfAlice = stoneGameIII(0, len, stoneValue, prefixSumArr, dp);
        int scoreOfBob = prefixSumArr[len-1]-scoreOfAlice;
        
        if(scoreOfAlice>scoreOfBob) return "Alice";
        else if(scoreOfBob>scoreOfAlice) return "Bob";
        else return "Tie";
    }
    
    private int stoneGameIII(int index, int len, int[] stoneValue, int[] prefixSumArr, Integer[] dp){
        if(index>=len) return 0;
        if(dp[index]!=null) return dp[index];
            
        int prefixSum = 0;
        int sum = prefixSumArr[len-1]-(index>0?prefixSumArr[index-1]:0);
        int firstIndex = index;
        int lastIndex = Math.min(index+2, len-1);
        int maxScore = -(int)1e9;
            
        while(index<=lastIndex){
            int value = stoneValue[index];
            prefixSum+=value;
            sum-=value;
            
            maxScore = Math.max(maxScore, prefixSum + (sum-stoneGameIII(index+1, len, stoneValue, prefixSumArr, dp)));
            index++;
        }
        
        return dp[firstIndex] = maxScore;
    }
    
    private int[] getPrefixSumArr(int[] arr, int len){
        int[] prefixSumArr = new int[len];
        
        for(int index=0; index<len; index++){
            prefixSumArr[index] = arr[index];
            if(index>0) prefixSumArr[index]+=prefixSumArr[index-1];
        }
        
        return prefixSumArr;
    }
}


// Solution-2: Tabulation
// TC: O(n)
// SC: O(n)

class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int len = stoneValue.length;
        int[] prefixSumArr = getPrefixSumArr(stoneValue, len);
        
        int[] dp = new int[len+1];
        
        for(int firstIndex=len-1; firstIndex>=0; firstIndex--){
            int prefixSum = 0;
            int sum = prefixSumArr[len-1] - (firstIndex>0?prefixSumArr[firstIndex-1]:0);
            int lastIndex = Math.min(firstIndex+2, len-1);
            int maxScore = -(int)1e9;
            
            for(int index=firstIndex; index<=lastIndex; index++){
                int value = stoneValue[index];
                prefixSum+=value;
                sum-=value;
                
                maxScore = Math.max(maxScore, prefixSum+(sum-dp[index+1]));
            }
            
            dp[firstIndex] = maxScore;
        }
        
        int scoreOfAlice = dp[0];
        int scoreOfBob = prefixSumArr[len-1]-scoreOfAlice;
        
        if(scoreOfAlice>scoreOfBob) return "Alice";
        else if(scoreOfBob>scoreOfAlice) return "Bob";
        else return "Tie";
    }
    
    private int[] getPrefixSumArr(int[] arr, int len){
        int[] prefixSumArr = new int[len];
        
        for(int index=0; index<len; index++){
            prefixSumArr[index] = arr[index];
            if(index>0) prefixSumArr[index]+=prefixSumArr[index-1];
        }
        
        return prefixSumArr;
    }
}


// Solution-3: Constant space

// The idea is that at every turn each player will try to maximize the difference between their score and the opponents score
// The ideal case would be to maximize it such that it is positive implying score of current player is greater than their opponent
// Since we only need next 3 values to update current dp value, we can do it in O(1) space

// TC: O(n)
// SC: O(1)

class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int len = stoneValue.length;
        int[] dp = new int[3];
        
        for(int index=len-1; index>=0; index--){
            int sum = stoneValue[index];
            
            int takeOne = sum-dp[(index+1)%3];
            
            int takeTwo = -(int)1e9;
            if(index+1<len){
                sum+=stoneValue[index+1];
                takeTwo = sum-dp[(index+2)%3];
            }
            
            int takeThree = -(int)1e9;
            if(index+2<len){
                sum+=stoneValue[index+2];
                takeThree = sum-dp[(index+3)%3];
            }
            
            dp[index%3] = Math.max(Math.max(takeOne, takeTwo), takeThree);
        }
        
        int score = dp[0];
        
        if(score>0) return "Alice"; // score of alice > score of bob
        else if(score<0) return "Bob"; // score of alice < score of bob
        else return "Tie"; // score of alice = score of bob
    }
}