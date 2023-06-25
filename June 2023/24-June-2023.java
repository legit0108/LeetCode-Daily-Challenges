// TC: O(len*sum(rods))
// SC: O(len*sum(rods))

class Solution {
    public int tallestBillboard(int[] rods) {
        int len = rods.length;
        int offset = 5000;
        Integer[][] dp  = new Integer[len][2*offset+1];
        int tallestBillboardHeight = getTallestBillboardHeight(rods, 0, 0, len, offset, dp);
        
        if(tallestBillboardHeight<0) return 0;
        else return tallestBillboardHeight;
    }
    
    private int getTallestBillboardHeight(int[] rods, int index, int difference, int len, int offset, Integer[][] dp){
        if(index==rods.length){
            if(difference==0) return 0;
            else return -(int)1e9;
        }
        
        if(dp[index][difference+offset]!=null) return dp[index][difference+offset];
        
        int rod = rods[index];
        int doNotTake = getTallestBillboardHeight(rods, index+1, difference, len, offset, dp);
        int takeInSet1 = getTallestBillboardHeight(rods, index+1, difference+rod, len, offset, dp);
        int takeInSet2 = rod + getTallestBillboardHeight(rods, index+1, difference-rod, len, offset, dp);
        
        int tallestHeight = Math.max(doNotTake, Math.max(takeInSet1, takeInSet2));
        dp[index][difference+offset] = tallestHeight;
        
        return tallestHeight;
    }
}