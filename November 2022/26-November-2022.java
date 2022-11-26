// Solution - 1 : DP (Recursion + Memoization) + Binary Search

// TC : O(totalJobs*log(totalJobs))
// SC : O(totalJobs)

class Solution {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int totalJobs = startTime.length;
        Job[] jobs = new Job[totalJobs];
        Integer dp[] = new Integer[totalJobs];
        
        for(int idx=0; idx<totalJobs; idx++){
            jobs[idx] = new Job(startTime[idx], endTime[idx], profit[idx]);
        }
        
        Arrays.sort(jobs);
        
        return getMaxProfit(jobs, totalJobs-1, dp);
    }
    
    private int getMaxProfit(Job[] jobs, int currIdx, Integer[] dp){
        Job job = jobs[currIdx];
        int startTime = job.startTime;
        int endTime = job.endTime;
        int profit = job.profit;
        
        if(currIdx==0) return profit;
        if(dp[currIdx]!=null) return dp[currIdx];
        
        int maxProfit = getMaxProfit(jobs, currIdx-1, dp);
        
        int nextIdx = binarySearch(jobs, startTime);
        maxProfit = Math.max(maxProfit, profit + (nextIdx>-1?getMaxProfit(jobs, nextIdx, dp):0));
        
        return dp[currIdx] = maxProfit;
    }
    
    private int binarySearch(Job[] jobs, int startTime){
        int low = 0;
        int high = jobs.length-1;
        int nextIdx = -1;
        
        while(low<=high){
            int mid = low + (high-low)/2;
            
            if(jobs[mid].endTime<=startTime){
                nextIdx = mid;
                low = mid+1;
            }else high = mid-1;
        }
        
        return nextIdx;
    }
    
    private class Job implements Comparable<Job>{
        private int startTime;
        private int endTime;
        private int profit;
        
        private Job(int startTime, int endTime, int profit){
            this.startTime = startTime;
            this.endTime = endTime;
            this.profit = profit;
        }
        
        public int compareTo(Job other){
            return this.endTime-other.endTime;
        }
    }
}

// Solution - 2 : DP (Tabulation) + Binary Search

// TC : O(totalJobs*log(totalJobs))
// SC : O(totalJobs)

class Solution {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int totalJobs = startTime.length;
        Job[] jobs = new Job[totalJobs];
        
        for(int idx=0; idx<totalJobs; idx++){
            jobs[idx] = new Job(startTime[idx], endTime[idx], profit[idx]);
        }
        
        Arrays.sort(jobs);
        
        return getMaxProfit(jobs, totalJobs);
    }
    
    private int getMaxProfit(Job[] jobs, int totalJobs){
        int dp[] = new int[totalJobs];
        
        for(int currIdx=0; currIdx<totalJobs; currIdx++){
            Job job = jobs[currIdx];
            int startTime = job.startTime;
            int endTime = job.endTime;
            int profit = job.profit;

            if(currIdx==0) dp[currIdx] = profit;
            else{
                int maxProfit = dp[currIdx-1];
                   
                int nextIdx = binarySearch(jobs, startTime);
                maxProfit = Math.max(maxProfit, profit + (nextIdx>-1?dp[nextIdx]:0));
                    
                dp[currIdx] = maxProfit;
            }
        }
        
        return dp[totalJobs-1];
    }
    
    private int binarySearch(Job[] jobs, int startTime){
        int low = 0;
        int high = jobs.length-1;
        int nextIdx = -1;
        
        while(low<=high){
            int mid = low + (high-low)/2;
            
            if(jobs[mid].endTime<=startTime){
                nextIdx = mid;
                low = mid+1;
            }else high = mid-1;
        }
        
        return nextIdx;
    }
    
    private class Job implements Comparable<Job>{
        private int startTime;
        private int endTime;
        private int profit;
        
        private Job(int startTime, int endTime, int profit){
            this.startTime = startTime;
            this.endTime = endTime;
            this.profit = profit;
        }
        
        public int compareTo(Job other){
            return this.endTime-other.endTime;
        }
    }
}