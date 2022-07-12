// TC : O(4^len)
// SC : O(len)

// this question is same as partitioning array into 4 subsets
// such that sum of each subset is same

class Solution {
    public boolean makesquare(int[] matchsticks) {
        int sum = 0;
        int len = matchsticks.length;
        
        for(int idx=0;idx<len;idx++){
            sum+=matchsticks[idx];
        }
        
        if(sum%4!=0) return false;
        
        return isPossible(matchsticks,0,new int[4],sum,len);
    }
    
    private boolean isPossible(int matchsticks[],int idx,int subsetSums[],int sum,int len){
        if(idx==len){
            for(int currIdx=1;currIdx<4;currIdx++){
                if(subsetSums[currIdx]!=subsetSums[currIdx-1]) return false;
            }
            
            return true;
        }
        
        for(int currIdx=0;currIdx<4;currIdx++){
            if(subsetSums[currIdx]>sum/4) return false; 
        }
        
        for(int currIdx=0;currIdx<4;currIdx++){
            if(subsetSums[currIdx]==0){
                subsetSums[currIdx]+=matchsticks[idx];
                boolean check = isPossible(matchsticks,idx+1,subsetSums,sum,len);
                if(check) return true;
                subsetSums[currIdx]-=matchsticks[idx];
                break;
            }else{
                subsetSums[currIdx]+=matchsticks[idx];
                boolean check = isPossible(matchsticks,idx+1,subsetSums,sum,len);
                if(check) return true;
                subsetSums[currIdx]-=matchsticks[idx];
            }
        }
        
        return false;
    }
}