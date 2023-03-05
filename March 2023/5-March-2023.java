// Solution-1: BFS

// TC: O(len)
// SC: O(len)

class Solution {
    public int minJumps(int[] arr) {
        int len = arr.length;
        HashMap<Integer, List<Integer>> map = new HashMap();
        
        for(int idx=0; idx<len; idx++){
            int val = arr[idx];
            if(!map.containsKey(val)) map.put(val, new ArrayList());
            map.get(val).add(idx);
        }
        
        Queue<Integer> queue = new ArrayDeque();
        HashSet<Integer> visited = new HashSet();
        queue.add(0);
        int minSteps = 0;
        
        while(queue.size()>0){
            int size = queue.size();
            
            while(size>0){
                int idx = queue.remove();
                size--;
                
                if(visited.contains(idx)) continue;
                if(idx==len-1) return minSteps;
                
                visited.add(idx);
                
                if(idx+1<len && !visited.contains(idx+1)) queue.add(idx+1); // op-1
                if(idx-1>=0 && !visited.contains(idx-1)) queue.add(idx-1); // op-2
                
                int val = arr[idx];
                if(map.containsKey(val)){
                    List<Integer> list = map.get(val);
                    
                    for(int nextIdx: list){
                        if(!visited.contains(nextIdx)) queue.add(nextIdx);
                    }
                    
                    map.remove(val); // Only need to perform op-3 once for a value (for min steps)
                }
            }
            
            minSteps++;
        }
        
        return -1;
    }
}


/*
 Solution-2: Bidirectional BFS

 In the later part of our original BFS method, the layer may be long and takes a long time to compute the next layer. 
 In this situation, we can compute the layer from the end, which may be short and takes less time.

 TC: O(len)
 SC: O(len)
*/ 

class Solution {
    public int minJumps(int[] arr) {
        int len = arr.length;
        HashMap<Integer, List<Integer>> map = new HashMap();
        
        for(int idx=0; idx<len; idx++){
            int val = arr[idx];
            if(!map.containsKey(val)) map.put(val, new ArrayList());
            map.get(val).add(idx);
        }
        
        HashSet<Integer> queue1 = new HashSet();
        HashSet<Integer> queue2 = new HashSet();
        HashSet<Integer> visited = new HashSet();
        queue1.add(0);
        queue2.add(len-1);
        int minSteps = 0;
        
        while(queue1.size()>0){
            if(queue1.size()>queue2.size()){ // search from the side with fewer nodes
                HashSet<Integer> temp = queue1;
                queue1 = queue2;
                queue2 = temp;
            }
            
            HashSet<Integer> queue = new HashSet();
            
            for(int idx: queue1){
                if(visited.contains(idx)) continue;
                if(queue2.contains(idx)) return minSteps; // contains in O(1) if queue is hashset
                
                visited.add(idx);
                
                if(idx+1<len && !visited.contains(idx+1)) queue.add(idx+1); // op-1
                if(idx-1>=0 && !visited.contains(idx-1)) queue.add(idx-1); // op-2
                
                int val = arr[idx];
                if(map.containsKey(val)){
                    List<Integer> list = map.get(val);
                    
                    for(int nextIdx: list){
                        if(!visited.contains(nextIdx)) queue.add(nextIdx);
                    }
                    
                    map.remove(val); // Only need to perform op-3 once for a value to get min moves
                }
            }
            
            queue1 = queue;
            minSteps++;
        }
        
        return -1;
    }
}