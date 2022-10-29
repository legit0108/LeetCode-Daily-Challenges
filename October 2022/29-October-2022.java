// Sort in descending order of grow time, then proceed with simulating plant and bloom
// Larger grow time being processed first allows both grow and plant to occur at same time

// TC: O(nlogn)
// SC: O(n)

class Solution {
    public int earliestFullBloom(int[] plantTime, int[] growTime) {
        int n = plantTime.length;
        int seeds[][] = new int[n][2];
        
        for(int idx=0; idx<n; idx++){
            seeds[idx][0] = plantTime[idx];
            seeds[idx][1] = growTime[idx];
        }
        
        Arrays.sort(seeds, (seed1, seed2)->Integer.compare(seed2[1], seed1[1]));
        int plantTimeSoFar = 0;
        int earliestBloomTime = 0;
        
        for(int seed[] : seeds){
            plantTimeSoFar+=seed[0];
            earliestBloomTime = Math.max(earliestBloomTime, plantTimeSoFar+seed[1]);
        }
        
        return earliestBloomTime;
    }
}