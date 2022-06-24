// Idea : when going from initial state to final state
// updation occurs with introduction of maximum element of next state 
// instead of thinking about going from initial state to final state
// backtrack from final state to initial state 
// if it is possible to do so then target can be constructed
// we use a max heap to backtrack from final state to initial state

// TC : O(lenlog(len))
// SC : O(len)

class Solution {
    public boolean isPossible(int[] target) {
        long totalSum = 0;
        int len = target.length;
        PriorityQueue<Long> maxHeap = new PriorityQueue<Long>(Collections.reverseOrder());
        
        for(int num : target){
            long val = num;
            totalSum+=val;
            maxHeap.add(val);
        }
        
        while(maxHeap.size()>0){
            long max = maxHeap.remove();
            long remainingSum = totalSum-max;
            
            if(max==1||remainingSum==1) return true; //initial state of [1,1,1,1,...] reached
            
            if(remainingSum==0||remainingSum>max) return false; // invalid case 
            
            //KEY STEP , avoid TLE which occurs when 
            //max-remainingSum done over a while loop
            //to get updated max 
            //consider : [3,100]
            
            long updatedMax = max%remainingSum; 
            
            if(updatedMax==0) return false; // invalid case
            
            //continue processing previous states
            //till invalid case reached
            //or [1,1,1,....] reached
            
            maxHeap.add(updatedMax);
            totalSum = remainingSum+updatedMax;
        }
        
        return true;
    }
}