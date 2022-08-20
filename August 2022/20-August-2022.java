// Fill maximum amount of fuel to ensure you can go as far as possible in min stops

// TC : O(len*log(len))
// SC : O(len)

class Solution {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        int len = stations.length;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
        int currPos = startFuel;
        int minStops = 0;
        
        for(int idx=0;idx<len;idx++){
            int pos = stations[idx][0];
            int fuel = stations[idx][1];
            
            while(currPos<pos&&maxHeap.size()>0){
                currPos+=maxHeap.remove();
                minStops++;
            }
            
            if(currPos<pos) return -1;
            maxHeap.add(fuel);
        }
        
        while(currPos<target&&maxHeap.size()>0){
            currPos+=maxHeap.remove();
            minStops++;
        }
            
        if(currPos<target) return -1;
        return minStops;
    }
}