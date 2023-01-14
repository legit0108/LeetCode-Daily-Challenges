class Solution {
    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        int[] parent = new int[26];
        int[] rank = new int[26];
        int[] minCh = new int[26];
        int len = s1.length();
        
        for(char ch='a'; ch<='z'; ch++){
            int idx = ch-'a';
            
            parent[idx] = idx;
            rank[idx] = 1;
            minCh[idx] = idx;
        }
        
        for(int idx=0; idx<len; idx++){
            union(s1.charAt(idx)-'a', s2.charAt(idx)-'a', parent, rank, minCh);
        }
        
        len = baseStr.length();
        StringBuilder str = new StringBuilder();
        
        for(int idx=0; idx<len; idx++){
            int leader = find(baseStr.charAt(idx)-'a', parent);
            char ch = (char)('a'+minCh[leader]);
            str.append(ch);
        }
        
        return str.toString();
    }
    
    private void union(int node1, int node2, int[] parent, int[] rank, int[] minCh){
        int leader1 = find(node1, parent);
        int leader2 = find(node2, parent);
        
        if(leader1 == leader2) return;
        
        int rankLeader1 = rank[leader1];
        int rankLeader2 = rank[leader2];
        int min1 = minCh[leader1];
        int min2 = minCh[leader2];
        
        if(rankLeader1<rankLeader2){
            parent[leader1] = leader2;
            
            if(min1<min2) minCh[leader2] = min1;
        }else if(rankLeader2<rankLeader1){
            parent[leader2] = leader1;
            
            if(min2<min1) minCh[leader1] = min2;
        }else{
            parent[leader1] = leader2;
            rank[leader2]++;
            
            if(min1<min2) minCh[leader2] = min1;
        }
    }
    
    private int find(int node, int[] parent){
        int leader = node;
        
        while(leader!=parent[leader]) leader = parent[leader];
        
        while(node!=parent[node]){
            int par = parent[node];
            parent[node] = leader;
            node = par;
        }
        
        return leader;
    }
}