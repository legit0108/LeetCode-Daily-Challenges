// TC : O(len)
// SC : O(len)

// idea : assign candies following requirements from end of array to start (suffCandies[idx])
// then assign candies following requirements from start of array to end (prefCandies)
// candies required for a child = max(prefCandies,suffCandies[idx])
// greedily meet requirements by minimizing candies for each child


class Solution {
    public int candy(int[] ratings) {
        int len = ratings.length;
        int suffCandies[] = new int[len];
        suffCandies[len-1] = 1;
        
        for(int idx=len-2;idx>=0;idx--){
            if(ratings[idx]>ratings[idx+1]) suffCandies[idx]=suffCandies[idx+1]+1;
            else suffCandies[idx] = 1;
        }
        
        int prefCandies = suffCandies[0];
        int minCandies = suffCandies[0];
        
        for(int idx=1;idx<len;idx++){
            if(ratings[idx]>ratings[idx-1]) prefCandies++;
            else prefCandies = 1;
            
            minCandies+=Math.max(prefCandies,suffCandies[idx]);
        }
        
        return minCandies;
    }
}