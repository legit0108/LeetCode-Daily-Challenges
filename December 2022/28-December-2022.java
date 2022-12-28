// Take floor from current maximum element to minimize total

// TC: O(len + k*log(len))
// SC: O(len)

// where len = piles.length

class Solution {
    public int minStoneSum(int[] piles, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
        
        int sum = 0;
        for(int pile: piles){
            sum+=pile;
            maxHeap.add(pile);
        }
        
        while(k>0){
            int maxStones = maxHeap.remove();
            int floorVal = maxStones/2;
            
            sum-=floorVal;
            maxStones-=floorVal;
            k--;
            
            if(maxStones>0) maxHeap.add(maxStones);
        }
        
        return sum;
    }
}