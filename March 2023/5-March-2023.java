// BFS

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