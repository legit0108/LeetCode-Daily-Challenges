// At every point you get min efficient engineer
// kick out engineer with least speed once size exceeds k

// TC : O(nlogn)
// SC : O(n)

class Solution {
    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        Engineer engineers[] = new Engineer[n];
        
        for(int idx = 0;idx<n;idx++) engineers[idx] = new Engineer(speed[idx], efficiency[idx]);
        Arrays.sort(engineers);
        
        long maxPerformance = 0;
        long sum = 0;
        long mod = (long)1e9+7;
        int idx = 0;
        PriorityQueue<Long> speeds = new PriorityQueue<Long>();
        
        while(idx<n){
            Engineer engineer = engineers[idx];
            long currSpeed = engineer.speed;
            long currEfficiency = engineer.efficiency;
            
            if(speeds.size()==k){
                long minSpeed = speeds.remove();
                sum = sum-minSpeed;
            }
            
            speeds.add(currSpeed);
            sum = sum+currSpeed;
            long minEfficiency = currEfficiency;
            
            long currPerformance = sum*minEfficiency;
            maxPerformance = Math.max(maxPerformance, currPerformance);
            idx++;
        }
        
        int ans = (int)((maxPerformance+mod)%mod);
        return ans;
    }
    
    private class Engineer implements Comparable<Engineer> {
        private long speed;
        private long efficiency;
        
        private Engineer(long speed,long efficiency){
            this.speed = speed;
            this.efficiency = efficiency;
        }
        
        public int compareTo(Engineer other){
            if(this.efficiency > other.efficiency) return -1;
            return 1;
        }
    }
}