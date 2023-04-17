// TC: O(candies.length)
// SC: O(1) ignoring space required for result

class Solution {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int maxCandy = candies[0];
        for(int candy: candies) maxCandy = Math.max(maxCandy, candy);
        
        List<Boolean> result = new ArrayList();
        for(int candy: candies){
            if(candy+extraCandies>=maxCandy) result.add(true); // it does not matter if candy == maxCandy (if extraCandies added to maxCandy then it will surely be the maximum amongst all)
            else result.add(false);
        }
        
        return result;
    }
}