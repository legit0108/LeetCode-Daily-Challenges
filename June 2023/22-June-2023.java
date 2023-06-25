// TC: O(n)
// SC: O(1)

class Solution {
    public int maxProfit(int[] prices, int fee) {
        long boughtStateProfit = -prices[0];
        long soldStateProfit = 0;
        long transactionFee = fee;
        
        for(int index=1; index<prices.length; index++){
            long price = prices[index];
            
            long currBoughtStateProfit = soldStateProfit-price;
            long currSoldStateProfit = boughtStateProfit+price-transactionFee;
            
            boughtStateProfit = Math.max(boughtStateProfit, currBoughtStateProfit);
            soldStateProfit = Math.max(soldStateProfit, currSoldStateProfit);
        }
        
        return (int)soldStateProfit;
    }
}