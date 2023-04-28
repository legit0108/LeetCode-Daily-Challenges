/* Idea: If for 2 strings, they have exactly two different characters,
   then we can swap character at these two indices in either string to obtain the other
   (since all strings are anagramic in nature)
   We form a graph of these strings, connecting two nodes if they are similar */


// Solution-1: DFS
// TC: O(strs.length*strs.length*str.length())
// SC: O(strs.length*strs.length)

class Solution {
    public int numSimilarGroups(String[] strs) {
       int nodes = strs.length; 
       ArrayList<Integer>[] graph = new ArrayList[nodes];
        
       for(int index1=0; index1<nodes; index1++){
           String str1 = strs[index1];
           graph[index1] = new ArrayList();
           ArrayList<Integer> neighbors = graph[index1];
           
           for(int index2=0; index2<nodes; index2++){
               String str2 = strs[index2];
               if(areSimilar(strs[index1], strs[index2])) neighbors.add(index2);
           }
       }
        
       boolean[] visited = new boolean[nodes]; 
       int count = 0;
       
       for(int node=0; node<nodes; node++){
           if(!visited[node]){
               count++;
               dfs(node, graph, visited);
           }
       }
            
       return count; 
    }
    
    private boolean areSimilar(String str1, String str2){
       int diffCount = 0;
        
       for(int index=0; index<str1.length(); index++){
           if(str1.charAt(index)!=str2.charAt(index)) diffCount++;
       }
        
       return diffCount==0 || diffCount==2; 
    }
    
    private void dfs(int node, ArrayList<Integer>[] graph, boolean[] visited){
       visited[node] = true;
       
       ArrayList<Integer> neighbors = graph[node];
       for(int neighbor: neighbors){
           if(!visited[neighbor]) dfs(neighbor, graph, visited);
       } 
    }
}


// -----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----


// Solution-2: BFS
// TC: O(strs.length*strs.length*str.length())
// SC: O(strs.length*strs.length)

class Solution {
    public int numSimilarGroups(String[] strs) {
       int nodes = strs.length; 
       ArrayList<Integer>[] graph = new ArrayList[nodes];
        
       for(int index1=0; index1<nodes; index1++){
           String str1 = strs[index1];
           graph[index1] = new ArrayList();
           ArrayList<Integer> neighbors = graph[index1];
           
           for(int index2=0; index2<nodes; index2++){
               String str2 = strs[index2];
               if(areSimilar(strs[index1], strs[index2])) neighbors.add(index2);
           }
       }
        
       boolean[] visited = new boolean[nodes]; 
       int count = 0;
       
       for(int node=0; node<nodes; node++){
           if(!visited[node]){
               count++;
               bfs(node, graph, visited);
           }
       }
            
       return count; 
    }
    
    private boolean areSimilar(String str1, String str2){
       int diffCount = 0;
        
       for(int index=0; index<str1.length(); index++){
           if(str1.charAt(index)!=str2.charAt(index)) diffCount++;
       }
        
       return diffCount==0 || diffCount==2; 
    }
    
    private void bfs(int node, ArrayList<Integer>[] graph, boolean[] visited){
       Queue<Integer> queue = new ArrayDeque();
       queue.add(node);
       
       while(queue.size()>0){
           node = queue.remove();
           if(visited[node]) continue;
           
           visited[node] = true;
       
           ArrayList<Integer> neighbors = graph[node];
           for(int neighbor: neighbors){
               if(!visited[neighbor]) queue.add(neighbor);
           } 
       }
    }
}


// -----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----


// Solution-3: Union-Find
// TC: O(strs.length*strs.length*str.length())
// SC: O(strs.length)


class Solution {
    public int numSimilarGroups(String[] strs) {
       int len = strs.length; 
       UnionFind dsu = new UnionFind(len);
        
       for(int index1=0; index1<len; index1++){
           String str1 = strs[index1];
           
           for(int index2=0; index2<len; index2++){
               String str2 = strs[index2];
               if(areSimilar(str1, str2)) dsu.union(index1, index2);
           }
       }
        
       return dsu.components; 
    }

    private boolean areSimilar(String str1, String str2){
       int diffCount = 0;
        
       for(int index=0; index<str1.length(); index++){
           if(str1.charAt(index)!=str2.charAt(index)) diffCount++;
       }
        
       return diffCount==0 || diffCount==2; 
    }
    
    private class UnionFind{
        int[] parent;
        int[] rank;
        int components;
        int nodes;
        
        UnionFind(){}
        
        UnionFind(int nodes){
            this.nodes = nodes;
            components = nodes;
            
            parent = new int[nodes];
            rank = new int[nodes];
            
            for(int node=0; node<nodes; node++) parent[node] = node;
        }
        
        void union(int node1, int node2){
            int root1 = find(node1);
            int root2 = find(node2);
            
            if(root1==root2) return;
            
            int rank1 = rank[root1];
            int rank2 = rank[root2];
            
            if(rank1<rank2){
                parent[root1] = root2;
            }else if(rank2<rank1){
                parent[root2] = root1;
            }else{
                parent[root1] = root2;
                rank[root2]++;
            }
            
            components--;
        }
        
        int find(int node){
            int root = node;
            int initNode = node;
            
            while(parent[root]!=root) root = parent[root];
            
            node = initNode;
            
            while(node!=root){
                int par = parent[node];
                parent[node] = root;
                node = par;
            }
            
            return root;
        }
    }
}