// Solution-0: Brute force, recursion + memoization

// TC: O(days.length*500)
// SC: O(500*500)

class Solution {
    public int mincostTickets(int[] days, int[] costs) {
        Integer[][] dp = new Integer[500][500];
        return mincostTickets(days, costs, dp, 0, days[0]-1);
    }
    
    private int mincostTickets(int[] days, int[] costs, Integer[][] dp, int index, int lastValidDay){
        if(index>=days.length) return 0;
        
        if(dp[index][lastValidDay]!=null) return dp[index][lastValidDay];
        
        if(days[index]<=lastValidDay) return dp[index][lastValidDay] = mincostTickets(days, costs, dp, index+1, lastValidDay);
        
        int minCost = Math.min(Math.min(getCost(days, costs, dp, index, lastValidDay, 1), getCost(days, costs, dp, index, lastValidDay, 7)), getCost(days, costs, dp, index, lastValidDay, 30));
        
        return dp[index][lastValidDay] = minCost;
    }
    
    private int getCost(int[] days, int[] costs, Integer[][] dp, int index, int lastValidDay, int pass){ // get cost corresponding to pass
        int idx = -1;
        if(pass==1) idx = 0;
        else if(pass==7) idx = 1;
        else if(pass==30) idx = 2;
        
        int cost = costs[idx] + mincostTickets(days, costs, dp, index+1, days[index]+pass-1);
        return cost;
    }
}


// -----X-----X-----X-----X-----X-----X-----X-----X-----X-----


// Solution-2: Iterative DP

// TC: O(lastDay-firstDay)
// SC: O(lastDay)

class Solution {
    public int mincostTickets(int[] days, int[] costs) {
        int len = days.length;
        int firstDay = days[0];
        int lastDay = days[len-1];
        
        int[] dp = new int[lastDay+1];
        int index = len-1;
        
        for(int day=lastDay; day>=firstDay && index>=0; day--){
            if(day==days[index]){ 
                dp[day] = Math.min(Math.min(getCost(dp, costs, day, 1), getCost(dp, costs, day, 7)), getCost(dp, costs, day, 30));
                index--;
            }else if(day<lastDay) dp[day] = dp[day+1]; 
        }
        
        return dp[firstDay];
    }
    
    private int getCost(int[] dp, int[] costs, int day, int pass){ // get cost corresponding to pass
        int index = -1;
        if(pass==1) index = 0;
        else if(pass==7) index = 1;
        else if(pass==30) index = 2;
        
        int cost = costs[index] + get(dp, day+pass);
        return cost;
    }
    
    private int get(int[] dp, int index){ // get value at dp[index]
        if(index>=dp.length) return 0;
        
        return dp[index];
    }
}


// -----X-----X-----X-----X-----X-----X-----X-----X-----X-----


// Solution-3: Iterative DP

// TC: O(days.length)
// SC: O(days.length)

class Solution {
    public int mincostTickets(int[] days, int[] costs) {
        int len = days.length;
        int[] dp = new int[len];
        int weekPointer = len-1; // point to last day we can travel to if 7-day pass bought today
        int monthPointer = len-1; // point to last day we can travel to if 30-day pass bought today
        
        for(int index=len-1; index>=0; index--){
            int day = days[index];
            
            while(day+7<=days[weekPointer]) weekPointer--;
            while(day+30<=days[monthPointer]) monthPointer--;
            
            dp[index] = Math.min(Math.min(costs[0]+get(dp, index+1), costs[1]+get(dp, weekPointer+1)), costs[2]+get(dp, monthPointer+1));
        }
        
        return dp[0];
    }
    
    private int get(int[] dp, int index){
        if(index>=dp.length) return 0;
        return dp[index];
    }
}