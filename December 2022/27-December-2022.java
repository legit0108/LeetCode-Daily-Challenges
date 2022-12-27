// Solution-1: Using pair class

// TC: O(lenlog(len))
// SC: O(len)

class Solution {
    public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        int len = capacity.length;
        Pair[] arr = new Pair[len];
        
        for(int idx=0; idx<len; idx++) arr[idx] = new Pair(rocks[idx], capacity[idx]);
        
        Arrays.sort(arr);
        
        int maxBags = 0;
        
        for(int idx=0; idx<len; idx++){
            Pair pair = arr[idx];
            int maxCapacity = pair.maxCapacity;
            int currRocks = pair.currRocks;
            
            int increase = Math.min(additionalRocks, maxCapacity - currRocks);
            if((currRocks + increase) == maxCapacity) maxBags++;
            additionalRocks-=increase;
        }
        
        return maxBags;
    }
    
    private class Pair implements Comparable<Pair>{
        private int currRocks;
        private int maxCapacity;
        
        private Pair(int currRocks, int maxCapacity){
            this.currRocks = currRocks;
            this.maxCapacity = maxCapacity;
        }
        
        public int compareTo(Pair other){
            int diff1 = this.maxCapacity - this.currRocks;
            int diff2 = other.maxCapacity - other.currRocks;
            
            return diff1-diff2;
        }
    }
}

// Solution-2: Using int array

// TC: O(lenlog(len))
// SC: O(len)

class Solution {
    public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        int len = capacity.length;
        int[] arr = new int[len];
        
        for(int idx=0; idx<len; idx++) arr[idx] = capacity[idx] - rocks[idx];
        
        Arrays.sort(arr);
        
        int maxBags = 0;
        
        for(int idx=0; idx<len; idx++){
            int diff = arr[idx];
            int increase = Math.min(additionalRocks, diff);
            
            if(increase == diff) maxBags++;
            additionalRocks-=increase;
        }
        
        return maxBags;
    }
}