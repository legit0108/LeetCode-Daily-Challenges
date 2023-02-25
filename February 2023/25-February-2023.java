// We get the maximum profit by selling stock on any given day by buying stock at its least price in the past

// TC: O(prices.length)
// SC: O(1)

class Solution {
    public int maxProfit(int[] prices) {
        int costPrice = Integer.MAX_VALUE;
        int profit = 0;
        
        for(int price: prices){
            if(price<costPrice) costPrice = price;
            else profit = Math.max(profit, price-costPrice);
        }
        
        return profit;
    }
}