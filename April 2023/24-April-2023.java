// Solution-1: Brute force, sort at each step
// TC: O(len*len*log(len))
// SC: O(sort)

class Solution {
    public int lastStoneWeight(int[] stones) {
        int len = stones.length;
        Arrays.sort(stones);
        
        for(int index=len-1; index>=1; index--){
            stones[index-1]=stones[index]-stones[index-1];
            Arrays.sort(stones);
        }
        
        return stones[0];
    }
}


// Solution-2: Simulate via heap
// TC: O(len*log(len))
// SC: O(len)

class Solution {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue(Collections.reverseOrder());
        for(int stone: stones) maxHeap.add(stone);
        
        while(maxHeap.size()>1){
            int heaviestStone = maxHeap.remove();
            int secondHeaviestStone = maxHeap.remove();
            
            if(heaviestStone==secondHeaviestStone) continue;
            else maxHeap.add(heaviestStone-secondHeaviestStone);
        }
        
        if(maxHeap.size()==0) return 0;
        else return maxHeap.remove();
    }
}