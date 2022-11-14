// Connected components

// TC: O(len)
// SC: O(len)

class Solution {
    private int connectedComps;
    
    public int removeStones(int[][] stones) {
        int len = stones.length;
        int largestStones = 0;
        connectedComps = len;
        
        HashMap<Integer, Integer> rowMap = new HashMap();
        HashMap<Integer, Integer> colMap = new HashMap();
        HashMap<Integer, Integer> parent = new HashMap();
        HashMap<Integer, Integer> rank = new HashMap();
        
        for(int idx=0; idx<len; idx++){
            int stone[] = stones[idx];
            int row = stone[0];
            int col = stone[1];
            
            parent.put(idx, idx);
            rank.put(idx, 0);
            
            if(!rowMap.containsKey(row)) rowMap.put(row, idx);
            else union(rowMap.get(row), idx, parent, rank);
            
            if(!colMap.containsKey(col)) colMap.put(col, idx);
            else union(colMap.get(col), idx, parent, rank);
        }
        
        return len - connectedComps;
    }
    
    private void union(int idx1, int idx2, HashMap<Integer, Integer> parent, HashMap<Integer, Integer> rank){
        int parent1 = find(idx1, parent);
        int parent2 = find(idx2, parent);
        
        if(parent1==parent2) return;
        
        int rank1 = rank.get(parent1);
        int rank2 = rank.get(parent2);
        
        if(rank1<rank2){
            parent.put(parent1, parent2);
        }else if(rank1>rank2){
            parent.put(parent2, parent1);
        }else{
            parent.put(parent1, parent2);
            rank.put(parent2, rank2+1);
        }
        
        connectedComps--;
    }
    
    private int find(int idx, HashMap<Integer, Integer> parent){
        int par = parent.get(idx);
        if(par==idx) return idx;
        
        par = find(par, parent);
        parent.put(idx, par);
        
        return par;
    }
}