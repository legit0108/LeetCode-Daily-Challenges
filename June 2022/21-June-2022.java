// TC : O(lenlog(bricks))
// SC : O(bricks)

class Solution {
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
        int len = heights.length;
        
        for(int idx=0;idx<len-1;idx++){
            if(heights[idx]>=heights[idx+1]) continue;
            else{
                int diff = heights[idx+1]-heights[idx];
                
                if(diff<=bricks){
                    // initially use bricks for jumps
                    // optimize in future
                    maxHeap.add(diff);
                    bricks-=diff;
                }else if(ladders>0){
                    if(maxHeap.size()>0){
                        int maxDiff = maxHeap.peek();
                        
                        if(maxDiff>=diff){
                           // look at past
                           // use ladder where maxDiff bricks used and use diff bricks 
                           // for current jump
                           // it is optimal to use ladders for big jumps 
                           maxHeap.remove(); 
                           ladders--; 
                           bricks+=maxDiff;
                           bricks-=diff;
                           maxHeap.add(diff); 
                        }else ladders--;
                    }else ladders--;
                }else return idx;
            }
        }
        
        return len-1;
    }
}