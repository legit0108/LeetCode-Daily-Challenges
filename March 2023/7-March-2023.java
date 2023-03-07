// Binary search on answer

// TC: O(len*log(high-low))
// SC: O(1)

class Solution {
    public long minimumTime(int[] time, int totalTrips) {
        int len = time.length;
        long min = Long.MAX_VALUE;
        
        for(int idx=0; idx<len; idx++) min = Math.min(min, time[idx]);
        
        long low = min;
        long high = min*(long)totalTrips;
        
        while(low<high){
            long mid = low + (high-low)/2;
            
            if(tripsPossible(time, mid, totalTrips)) high = mid;
            else low = mid+1;
        }
        
        return low;
    }
    
    private boolean tripsPossible(int[] times, long currTime, int totalTrips){
        long trips = 0;
        
        for(long time: times){
            trips+=currTime/time; // number of trips possible given 'time' for 1 trip and total time = currTime -> currTime/time
        }
        
        return trips>=totalTrips;
    }
}