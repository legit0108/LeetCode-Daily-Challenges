// The problem boils down to checking if the given graph is bipartite or not 

// Solution - 1: DFS + graph coloring

// Let V be the number of vertices in our graph, E be the number of edges

// TC: O(V + E)
// SC: O(V + E)

class Solution {
    public boolean possibleBipartition(int n, int[][] dislikes) {
        ArrayList<Integer>[] graph = createGraph(dislikes, n);
        
        if(isBipartite(graph, n)) return true;
        else return false;
    }
    
    private ArrayList<Integer>[] createGraph(int[][] edges, int nodes){
        ArrayList<Integer>[] graph = new ArrayList[nodes+1];
        
        for(int node=1; node<=nodes; node++) graph[node] = new ArrayList();
        
        for(int[] edge: edges){
            int node1 =  edge[0];
            int node2 = edge[1];
            
            graph[node1].add(node2);
            graph[node2].add(node1);
        }
        
        return graph;
    }
    
    private boolean isBipartite(ArrayList<Integer>[] graph, int nodes){
        boolean[] visited = new boolean[nodes+1];
        int[] color = new int[nodes+1];
        
        for(int node=1; node<=nodes; node++){
            if(visited[node] == false){
                boolean flag = dfs(graph, node, visited, color, 0);
                
                if(!flag) return false;
            }
        }
        
        return true;
    }
    
    private boolean dfs(ArrayList<Integer>[] graph, int node, boolean[] visited, int[] color, int colorAssigned){
        visited[node] = true;
        color[node] = colorAssigned;
        
        for(int neighbour: graph[node]){
            if(!visited[neighbour]){
                boolean flag = dfs(graph, neighbour, visited, color, colorAssigned^1);
                
                if(!flag) return false;
            }else if(color[neighbour] == colorAssigned) return false;
        }
        
        return true;
    }
}

// Solution-2: BFS + graph coloring

// TC: O(V + E)
// SC: O(V + E)

class Solution {
    public boolean possibleBipartition(int n, int[][] dislikes) {
        ArrayList<Integer>[] graph = createGraph(dislikes, n);
        
        if(isBipartite(graph, n)) return true;
        else return false;
    }
    
    private ArrayList<Integer>[] createGraph(int[][] edges, int nodes){
        ArrayList<Integer>[] graph = new ArrayList[nodes+1];
        
        for(int node=1; node<=nodes; node++) graph[node] = new ArrayList();
        
        for(int[] edge: edges){
            int node1 =  edge[0];
            int node2 = edge[1];
            
            graph[node1].add(node2);
            graph[node2].add(node1);
        }
        
        return graph;
    }
    
    private boolean isBipartite(ArrayList<Integer>[] graph, int nodes){
        boolean[] visited = new boolean[nodes+1];
        int[] color = new int[nodes+1]; 
        
        for(int node=1; node<=nodes; node++){
            if(visited[node] == false){
                boolean flag = bfs(graph, node, visited, color);
                
                if(!flag) return false;
            }
        }
        
        return true;
    }
    
    private boolean bfs(ArrayList<Integer>[] graph, int node, boolean[] visited, int[] color){
        Queue<int[]> queue = new ArrayDeque();
        queue.add(new int[]{node, 0}); // node, colorAssigned
        
        while(queue.size() > 0){
            int[] pair = queue.remove();
            node = pair[0];
            int colorAssigned = pair[1];
                
            if(visited[node]) continue;
                
            visited[node] = true;
            color[node] = colorAssigned;
                
            for(int neighbour: graph[node]){
                if(!visited[neighbour]) queue.add(new int[]{neighbour, colorAssigned^1});
                else if(color[neighbour] == colorAssigned) return false;
            }
        }
        
        return true;
    }
}

// Solution-3: DFS + checking odd cycles

// TC: O(V + E)
// SC: O(V + E)

class Solution {
    public boolean possibleBipartition(int n, int[][] dislikes) {
        ArrayList<Integer>[] graph = createGraph(dislikes, n);
        
        if(isBipartite(graph, n)) return true;
        else return false;
    }
    
    private ArrayList<Integer>[] createGraph(int[][] edges, int nodes){
        ArrayList<Integer>[] graph = new ArrayList[nodes+1];
        
        for(int node=1; node<=nodes; node++) graph[node] = new ArrayList();
        
        for(int[] edge: edges){
            int node1 =  edge[0];
            int node2 = edge[1];
            
            graph[node1].add(node2);
            graph[node2].add(node1);
        }
        
        return graph;
    }
    
    private boolean isBipartite(ArrayList<Integer>[] graph, int nodes){
        boolean[] visited = new boolean[nodes+1];
        int[] level = new int[nodes+1];
        
        for(int node=1; node<=nodes; node++){
            if(visited[node] == false){
                boolean flag = dfs(graph, node, visited, level, 1);
                
                if(!flag) return false;
            }
        }
        
        return true;
    }
    
    private boolean dfs(ArrayList<Integer>[] graph, int node, boolean[] visited, int[] level, int currLevel){
        visited[node] = true;
        level[node] = currLevel;
        
        for(int neighbour: graph[node]){
            if(!visited[neighbour]){
                boolean flag = dfs(graph, neighbour, visited, level, currLevel+1);
                
                if(!flag) return false;
            }else if((level[neighbour]-currLevel)%2 == 0) return false;
        }
        
        return true;
    }
}

// Solution-4: BFS + checking odd cycles

// TC: O(V + E)
// SC: O(V + E)

class Solution {
    public boolean possibleBipartition(int n, int[][] dislikes) {
        ArrayList<Integer>[] graph = createGraph(dislikes, n);
        
        if(isBipartite(graph, n)) return true;
        else return false;
    }
    
    private ArrayList<Integer>[] createGraph(int[][] edges, int nodes){
        ArrayList<Integer>[] graph = new ArrayList[nodes+1];
        
        for(int node=1; node<=nodes; node++) graph[node] = new ArrayList();
        
        for(int[] edge: edges){
            int node1 =  edge[0];
            int node2 = edge[1];
            
            graph[node1].add(node2);
            graph[node2].add(node1);
        }
        
        return graph;
    }
    
    private boolean isBipartite(ArrayList<Integer>[] graph, int nodes){
        boolean[] visited = new boolean[nodes+1];
        int[] level = new int[nodes+1]; 
        
        for(int node=1; node<=nodes; node++){
            if(visited[node] == false){
                boolean flag = bfs(graph, node, visited, level);
                
                if(!flag) return false;
            }
        }
        
        return true;
    }
    
    private boolean bfs(ArrayList<Integer>[] graph, int node, boolean[] visited, int[] level){
        Queue<Integer> queue = new ArrayDeque();
        queue.add(node);
        int currLevel = 1;
        
        while(queue.size() > 0){
            int size = queue.size();
            
            while(size > 0){
                node = queue.remove();
                size--;
                
                if(visited[node]){
                    if(level[node] != currLevel) return false;  
                }else{
                    visited[node] = true;
                    level[node] = currLevel;
                }
                
                for(int neighbour: graph[node]){
                    if(!visited[neighbour]) queue.add(neighbour);
                }
            }
            
            currLevel++;
        }
        
        return true;
    }
}

// Solution-5: Union-find

// TC: O(V + E)
// SC: O(V + E)

class Solution {
    public boolean possibleBipartition(int n, int[][] dislikes) {
        ArrayList<Integer>[] graph = createGraph(dislikes, n);
        
        if(isBipartite(graph, n)) return true;
        else return false;
    }
    
    private ArrayList<Integer>[] createGraph(int[][] edges, int nodes){
        ArrayList<Integer>[] graph = new ArrayList[nodes+1];
        
        for(int node=1; node<=nodes; node++) graph[node] = new ArrayList();
        
        for(int[] edge: edges){
            int node1 =  edge[0];
            int node2 = edge[1];
            
            graph[node1].add(node2);
            graph[node2].add(node1);
        }
        
        return graph;
    }
    
    private boolean isBipartite(ArrayList<Integer>[] graph, int nodes){
        int par[] = new int[nodes+1];
        int rank[] = new int[nodes+1];
        
        for(int node=1; node<=nodes; node++){
            par[node] = node;
            rank[node] = 1;
        }
        
        for(int node =1; node<=nodes; node++){
            ArrayList<Integer> neighbours = graph[node];
            
            if(neighbours.size() > 0){
                int firstNeighbour = neighbours.get(0);

                for(int neighbour: neighbours){
                    if(find(neighbour, par) == find(node, par)) return false;
                    else union(neighbour, firstNeighbour, par, rank);
                }
            }
        }
        
        return true;
    }
    
    private void union(int node1, int node2, int[] par, int[] rank){
        int leader1 = find(node1, par);
        int leader2 = find(node2, par);
        
        if(leader1 == leader2) return;
        
        int rankLeader1 = rank[leader1];
        int rankLeader2 = rank[leader2];
        
        if(rankLeader1 < rankLeader2){
            par[leader1] = leader2;
        }else if(rankLeader2 < rankLeader1){
            par[leader2] = leader1;
        }else{
            par[leader1] = leader2;
            rank[leader2]++;
        }
    }
    
    private int find(int node, int[] par){
        int parent = par[node];
        
        if(parent == node) return node;
        
        return par[node] = find(parent, par);
    }
}