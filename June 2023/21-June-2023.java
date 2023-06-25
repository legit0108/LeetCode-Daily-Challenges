// TC: O(nlogn)
// SC: O(n)

class Solution {
    public long minCost(int[] nums, int[] cost) {
        int len = nums.length;
        Pair[] arr = new Pair[len];
        for(int index=0; index<len; index++) arr[index] = new Pair(nums[index], cost[index]);
        
        Arrays.sort(arr, (pair1, pair2)->Long.compare(pair1.num, pair2.num));
        
        long size = 0;
        for(int index=0; index<len; index++){
            size+=(long)cost[index];    
        }
        
        long median = -1;
        long count = 0;
        for(Pair pair: arr){
            count+=pair.cost;
            long index = count-1;
            
            if(index>=size/2){ 
                median = pair.num;
                break;
            }
        }
        
        long minimumCost = 0;
        for(Pair pair: arr){
            minimumCost+=pair.cost*Math.abs(median-pair.num);
        }
        
        return minimumCost;
    }
    
    private class Pair{
        long num;
        long cost;
        
        Pair(long num, long cost){
            this.num = num;
            this.cost = cost;
        }
    }
}