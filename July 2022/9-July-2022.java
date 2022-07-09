// TC : O(len) 
// SC : O(k)

class Solution {
    public int maxResult(int[] nums, int k) {
        Deque<int[]> deque = new ArrayDeque();
        int len = nums.length;
        int score = -1;
        
        for(int idx = len-1;idx>=0;idx--){
           while(deque.size()>0){
              int pair[] = deque.peekFirst();
              int pairIdx = pair[0];
              if(pairIdx>idx+k) deque.removeFirst();
              else break;
           }
            
           int currScore = nums[idx];
           if(deque.size()>0) currScore+=deque.peekFirst()[1];
           if(idx==0) score = currScore;
            
           while(deque.size()>0
           &&currScore>=deque.peekLast()[1]){
               deque.removeLast();
           }
            
           deque.addLast(new int[]{idx,currScore});
        }
        
        return score;
    }
}
