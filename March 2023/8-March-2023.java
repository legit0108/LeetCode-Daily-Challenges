// Binary search on answer

// TC: O(piles.length*log(high-low))
// SC: O(1)

class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int max = 0;
        long sum = 0;
        
        for(int pile: piles){ 
            sum+=(long)pile;
            max = Math.max(max, pile);
        }
        
        int low = (int)((sum+(long)h-1l)/(long)h); // min time cannot be less than ceil(sum/h)
        int high = max; // min time cannot be more than max(piles) 
    
        while(low<high){
            int mid = low + (high-low)/2;
            
            if(canFinishBananas(piles, mid, h)) high = mid;
            else low = mid+1;
        }
        
        return low;
    }
    
    private boolean canFinishBananas(int[] piles, int speed, int hours){
        int time = 0;
        
        for(int pile: piles){
            time+=(pile+speed-1)/speed; // ceil(pile/speed)
        }
        
        return time<=hours;
    }
}