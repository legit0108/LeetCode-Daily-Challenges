// Solution-1: Sorting
// TC: O(nlogn)
// SC: O(n)
// where n = nums.length

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap();
        
        for(int num: nums){
            map.put(num, map.getOrDefault(num, 0)+1);
        }
        
        ArrayList<Pair<Integer, Integer>> list = new ArrayList();
        
        for(int num: map.keySet()){
            list.add(new Pair(num, map.get(num)));
        }
        
        Collections.sort(list, (pair1, pair2)->Integer.compare(pair1.getValue(), pair2.getValue()));
        int[] ans = new int[k];
        int ansIndex = 0;
        
        for(int index=list.size()-1; index>=list.size()-k; index--) ans[ansIndex++] = list.get(index).getKey();
        
        return ans;
    }
}


// Solution-2: Min heap
// TC: O(nlogk + klogk)
// SC: O(n)

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap();
        
        for(int num: nums){
            map.put(num, map.getOrDefault(num, 0)+1);
        }
        
        PriorityQueue<Integer> minHeap = new PriorityQueue((num1, num2)->Integer.compare(map.get(num1), map.get(num2)));
        
        for(int num: map.keySet()){
            minHeap.add(num);
            if(minHeap.size()>k) minHeap.remove();
        }
        
        int[] ans = new int[k];
        
        for(int index=0; index<k; index++) ans[index] = minHeap.remove();
        
        return ans;
    }
}


// Solution-3: Max heap
// TC: O(nlogk)
// SC: O(n)

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap();
        
        for(int num: nums){
            map.put(num, map.getOrDefault(num, 0)+1);
        }
        
        int size = map.size();
        PriorityQueue<Integer> maxHeap = new PriorityQueue((num1, num2)->Integer.compare(map.get(num2), map.get(num1)));
        int[] ans = new int[k];
        int index = 0;
        
        for(int num: map.keySet()){
            maxHeap.add(num);
            
            if(maxHeap.size()>(size-k)){
                ans[index++] = maxHeap.remove();
            }
        }
        
        return ans;
    }
}


// Solution-4: Quick Select
// TC: O(n) average case, O(n^2) worst case
// SC: O(n)

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap();
        
        for(int num: nums){
            map.put(num, map.getOrDefault(num, 0)+1);
        }
        
        ArrayList<Pair<Integer, Integer>> list = new ArrayList();
        
        for(int num: map.keySet()){
            list.add(new Pair(num, map.get(num)));
        }
        
        quickSelect(list, list.size()-k);
        
        int[] ans = new int[k];
        int ansIndex = 0;
        
        for(int index=list.size()-1; index>=list.size()-k; index--) ans[ansIndex++] = list.get(index).getKey();
        
        return ans;
    }
    
    private void quickSelect(List<Pair<Integer, Integer>> list, int index){
        int size = list.size();
        int low = 0;
        int high = size-1;
        Random random = new Random();
        
        while(low<high){
            int pivotIndex = low+random.nextInt(high-low+1); // pivot on random index to avoid worst case
            
            int partitionIndex = partition(list, pivotIndex, high+1);
            
            if(partitionIndex==index) break;
            else if(partitionIndex<index) low = partitionIndex+1;
            else high = partitionIndex-1;
        }
    }
    
    private int partition(List<Pair<Integer, Integer>> list, int pivotIndex, int size){
        swap(list, pivotIndex, size-1);
        Pair<Integer, Integer> pivot = list.get(size-1);
        int partitionIndex = 0;
        
        for(int index=0; index<size; index++){
            if(list.get(index).getValue()<=pivot.getValue()){
                swap(list, index, partitionIndex);
                partitionIndex++;
            }
        }
        
        return partitionIndex-1;
    }
    
    private void swap(List<Pair<Integer, Integer>> list, int index1, int index2){
        Pair temp = list.get(index2);
        list.set(index2, list.get(index1));
        list.set(index1, temp);
    }
}


// Solution-5: Bucket Sort
// TC: O(n)
// SC: O(n)

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap();
        int maxFreq = 0;
        
        for(int num: nums){
            map.put(num, map.getOrDefault(num, 0)+1);
            maxFreq = Math.max(maxFreq, map.get(num));
        }
        
        ArrayList<Integer>[] buckets = new ArrayList[maxFreq+1];
        for(int freq=0; freq<=maxFreq; freq++) buckets[freq] = new ArrayList();
        
        for(int num: map.keySet()){
            int freq = map.get(num);
            buckets[freq].add(num);
        }
        
        int[] ans = new int[k];
        int index = k-1;
        
        for(int freq=maxFreq; freq>=0 && index>=0; freq--){
            ArrayList<Integer> bucket = buckets[freq];
            
            for(int bucketIndex=0; bucketIndex<bucket.size() && index>=0; bucketIndex++){
                int num = bucket.get(bucketIndex);
                ans[index--] = num;
            }
        }
        
        return ans;
    }
}