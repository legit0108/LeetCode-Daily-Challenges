// TC: O(nlogn)
// SC: O(n)

class Solution {
    public long totalCost(int[] costs, int k, int candidates) {
        PriorityQueue<Integer> minHeap1;
        minHeap1 = new PriorityQueue<Integer>((index1, index2)->
                                             costs[index1]==costs[index2]?
                                             Integer.compare(index1, index2):
                                             Integer.compare(costs[index1], costs[index2]));
     
        PriorityQueue<Integer> minHeap2;
        minHeap2 = new PriorityQueue<Integer>((index1, index2)->
                                             costs[index1]==costs[index2]?
                                             Integer.compare(index1, index2):
                                             Integer.compare(costs[index1], costs[index2]));
        
        int len = costs.length;
        boolean[] visited = new boolean[len];
        int start = 0;
        int end = len-1;
        long totalCost = 0;
        
        while(k>0){
            while(start<len && minHeap1.size()<candidates) minHeap1.add(start++);
            while(end>=0 && minHeap2.size()<candidates) minHeap2.add(end--);
            
            while(minHeap1.size()>0 && visited[minHeap1.peek()]) minHeap1.remove();
            while(minHeap2.size()>0 && visited[minHeap2.peek()]) minHeap2.remove();
            
            if(minHeap1.size()>0 && minHeap2.size()>0){
                int index1 = minHeap1.peek();
                int index2 = minHeap2.peek();
                long cost1 = costs[index1];
                long cost2 = costs[index2];
                
                if(cost1<cost2){
                    totalCost+=cost1;
                    visited[index1] = true;
                    minHeap1.remove();
                }else if(cost2<cost1){
                    totalCost+=cost2;
                    visited[index2] = true;
                    minHeap2.remove();
                }else{
                    if(index1<index2){
                        totalCost+=cost1;
                        visited[index1] = true;
                        minHeap1.remove();
                    }else{
                        totalCost+=cost2;
                        visited[index2] = true;
                        minHeap2.remove();
                    }
                }
            }else if(minHeap1.size()>0){
                int index = minHeap1.remove();
                long cost = costs[index];
                
                visited[index] = true;
                totalCost+=cost;
            }else{
                int index = minHeap2.remove();
                long cost = costs[index];
                
                visited[index] = true;
                totalCost+=cost;
            }
            
            k--;
        }
        
        return totalCost;
    }
}