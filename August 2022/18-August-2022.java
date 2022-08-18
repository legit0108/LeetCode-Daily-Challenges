// Method - 1 : HashMap + PriorityQueue
// TC : O(len*log(len))
// SC : O(len)

class Solution {
    public int minSetSize(int[] arr) {
        int len = arr.length;
        HashMap<Integer,Integer> map = new HashMap();
        
        for(int idx=0;idx<len;idx++){
            map.put(arr[idx],map.getOrDefault(arr[idx],0)+1);
        }
        
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>((num1,num2)->Integer.compare(map.get(num2),map.get(num1)));
        for(int num : map.keySet()){
            maxHeap.add(num);
        }
        
        int minSetSize = 0;
        int currLen = len;
        
        while(currLen>len/2){
            int num = maxHeap.remove();
            int removals = map.get(num);
            
            minSetSize++;
            currLen-=removals;
        }
        
        return minSetSize;
    }
}

// Method - 2 : Bucket sort based
// TC : O(len)
// SC : O(len)

class Solution {
    public int minSetSize(int[] arr) {
        int len = arr.length;
        HashMap<Integer,Integer> map = new HashMap();
        
        for(int idx=0;idx<len;idx++){
            map.put(arr[idx],map.getOrDefault(arr[idx],0)+1);
        }
        
        int sortedFreq[] = new int[len+1];
        
        for(int num : map.keySet()){
            int freq = map.get(num);
            sortedFreq[freq]++;
        }
        
        int minSetSize = 0;
        int remainingLen = len/2;
        int currFreq = len;
        
        while(remainingLen>0){
            while(currFreq>=1&&sortedFreq[currFreq]==0) currFreq--;
            
            int removals = Math.min(sortedFreq[currFreq],(remainingLen+currFreq-1)/currFreq);
            remainingLen-=removals*currFreq;
            minSetSize+=removals;
            sortedFreq[currFreq]-=removals;
        }
        
        return minSetSize;
    }
}