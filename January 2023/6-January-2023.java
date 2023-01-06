// Sort and pick the ice cream bars with the least cost to maximize the count of ice cream bars 


// Solution-1: Using Arrays.sort()

// TC: O(len*log(len))
// SC: O(sort) 

class Solution {
    public int maxIceCream(int[] costs, int coins) {
        int len = costs.length;
        int iceCreamBars = 0;
        Arrays.sort(costs);
        
        for(int idx=0; idx<len; idx++){
            int price = costs[idx];
            
            if(coins>=price){
                coins-=price;
                iceCreamBars++;
            }else break;
        }
        
        return iceCreamBars;
    }
}


// Solution-2: Using count sort/bucket sort

// TC: O(len + maxPrice)
// SC: O(maxPrice) 


class Solution {
    public int maxIceCream(int[] costs, int coins) {
        int len = costs.length;
        int maxPrice = 0;
        
        for(int idx=0; idx<len; idx++){
            int price = costs[idx];
            
            maxPrice = Math.max(maxPrice, price);
        }
        
        int[] map = new int[maxPrice+1];
        
        for(int idx=0; idx<len; idx++){
            int price = costs[idx];
            map[price]++;
        }
        
        int iceCreamBars = 0;
        
        for(int price=1; price<=maxPrice; price++){
            int freq = map[price];
            
            int amount = Math.min(coins, freq*price);
            iceCreamBars+=amount/price;
            
            coins-=amount;
        }
        
        return iceCreamBars;
    }
}