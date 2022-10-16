// TC: O(jobs*d*jobs)
// SC: O(jobs*d)

class Solution {
    public int minDifficulty(int[] jobDifficulty, int d) {
        int jobs = jobDifficulty.length;
        if(jobs<d) return -1;
        
        int dp[][] = new int[jobs+1][d+1];
        
        for(int job = jobs; job>=0; job--){
            for(int day = d; day>=1; day--){
                if(job==jobs) dp[job][day] = 0;
                else if(day==1){
                    int maxDifficulty = 0;
                    
                    for(int currJob = job; currJob<jobs; currJob++){
                        maxDifficulty = Math.max(maxDifficulty, jobDifficulty[currJob]);
                    }
                    
                    dp[job][day] = maxDifficulty;
                }else{
                    int minDifficulty = Integer.MAX_VALUE;
                    int currMaxDifficulty = 0;
        
                    for(int currJob=job; currJob<jobs-(day-1); currJob++){
                        currMaxDifficulty = Math.max(currMaxDifficulty, jobDifficulty[currJob]);
                        int currDifficulty = currMaxDifficulty + dp[currJob+1][day-1];
                        minDifficulty = Math.min(minDifficulty, currDifficulty);
                    }
                    
                    dp[job][day] = minDifficulty;
                }
            }
        }
        
        return dp[0][d];
    }
}