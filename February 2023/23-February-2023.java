/*
Ideas: 

First, we greedily choose the most profitable available project. Then our capital increases by the profit of this project, and some new projects that were unavailable before might become available now. If we choose a project other than the most profitable one, our capital increases by a value less than the maximum possible, and fewer new options become available. It means we should greedily choose the maximum profit every time. We can repeat this process of choosing the most profitable project and then updating the projects we can afford until we finish k projects or cannot afford any new ones.

When our capital grows, we have more options to choose from, and the smaller capital a project requires, the sooner it becomes available. Thus we can sort the projects by increasing capital and keep a pointer to the first unavailable project. As we gain more money, we can increment this pointer to unlock more projects.

TC: O(len*log(len))
SC: O(len)
*/

class Solution {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int len = profits.length;
        Pair[] arr = new Pair[len];
        
        for(int idx=0; idx<len; idx++) arr[idx] = new Pair(profits[idx], capital[idx]);
        
        Arrays.sort(arr);
        
        int idx = 0;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
        
        while(k>0){
            while(idx<len && arr[idx].capital<=w){
                maxHeap.add(arr[idx].profit);
                idx++;
            }
            
            if(maxHeap.size()==0) break;
            
            w+=maxHeap.remove();
            k--;
        }
        
        return w;
    }
    
    private class Pair implements Comparable<Pair>{
        private int profit;
        private int capital;
        
        private Pair(){
            
        }
        
        private Pair(int profit, int capital){
           this.profit = profit;
           this.capital = capital;
        }
        
        public int compareTo(Pair pair){
            return this.capital-pair.capital;
        }
    }
}