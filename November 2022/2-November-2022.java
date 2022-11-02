// BFS

// TC: exponential
// SC: O(mutations)

class Solution {
    public int minMutation(String start, String end, String[] bank) {
        Set<String> validMutations = new HashSet();
        for(String mutation : bank) validMutations.add(mutation);
        
        Queue<String> queue = new ArrayDeque();
        Set<String> visited = new HashSet();
        char options[] = {'A', 'C', 'G', 'T'};
        
        queue.add(start);
        int minMoves = 0;
        
        while(queue.size()>0){
            int size = queue.size();
            
            while(size>0){
                String mutation = queue.remove();
                size--;
                
                if(visited.contains(mutation)) continue;
                if(mutation.equals(end)) return minMoves;
                
                visited.add(mutation);
                int len = mutation.length();
                
                for(int idx=0; idx<len; idx++){
                    for(char ch : options){
                        if(ch==mutation.charAt(idx)) continue;
                        
                        String nextMutation = mutation.substring(0, idx) + ch + mutation.substring(idx+1);
                        
                        if(!visited.contains(nextMutation) && validMutations.contains(nextMutation)){
                            queue.add(nextMutation);
                        }
                    }
                }
            }
            
            minMoves++;
        }
        
        return -1;
    }
}