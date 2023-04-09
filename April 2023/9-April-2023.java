// Both the solutions run in:
// TC: O(V+E) = O(26*(V+E))
// SC: O(V+E) = O(26*V + E)


// Solution-1: BFS
// Topological Sorting / Kahn's Algorithm

class Solution {
    public int largestPathValue(String colors, int[][] edges) {
        int nodes = colors.length();
        int[][] maxFreqColors = new int[nodes][26]; // maximum frequency of colors ending at a particular node
        ArrayList<Integer>[] graph = new ArrayList[nodes];
        int[] indegree = new int[nodes];
        
        createGraph(edges, graph, indegree, maxFreqColors, colors, nodes);
        
        return bfs(graph, indegree, maxFreqColors, colors, nodes);
    }
    
    private int bfs(ArrayList<Integer>[] graph, int[] indegree, int[][] maxFreqColors, String colors, int nodes){
        Queue<Integer> queue = new ArrayDeque();
        for(int node=0; node<nodes; node++){
            if(indegree[node]==0) queue.add(node);
        }
        
        while(queue.size()>0){
            int node = queue.remove();
            ArrayList<Integer> neighbors = graph[node];
            
            for(int neighbor: neighbors){
                indegree[neighbor]--;
                int colorOfNeighbor = colors.charAt(neighbor)-'a';
                
                for(int color=0; color<26; color++){
                    int freqColor = maxFreqColors[node][color];
                    if(color==colorOfNeighbor) freqColor++;
                    
                    maxFreqColors[neighbor][color] = Math.max(maxFreqColors[neighbor][color], freqColor);
                }
                
                if(indegree[neighbor]==0) queue.add(neighbor);   
            }
        }
        
        int largestColorValue = 0;
        
        for(int node=0; node<nodes; node++){
            if(indegree[node]!=0) return -1;
            
            for(int color=0; color<26; color++) largestColorValue = Math.max(largestColorValue, maxFreqColors[node][color]);
        }
        
        return largestColorValue;
    }
    
    private void createGraph(int[][] edges, ArrayList<Integer>[] graph, int[] indegree, int[][] maxFreqColors, String colors, int nodes){
        for(int node=0; node<nodes; node++){
            graph[node] = new ArrayList();
            maxFreqColors[node][colors.charAt(node)-'a'] = 1;
        }
        
        for(int[] edge: edges){
            int node1 = edge[0];
            int node2 = edge[1];
            
            graph[node1].add(node2);
            indegree[node2]++;
        }
    }
}


// Solution-2: DFS
// Similar idea as that of finding cycle in directed graph using DFS
// We update maxFreqColors for a node while returning from a neighbor (since neighbor will be calculated by this point)

class Solution {
    public int largestPathValue(String colors, int[][] edges) {
        int nodes = colors.length();
        int[][] maxFreqColors = new int[nodes][26]; // maximum frequency of colors ending at a particular node
        ArrayList<Integer>[] graph = new ArrayList[nodes];
        
        createGraph(edges, graph, colors, nodes);
        
        boolean[] visited = new boolean[nodes];
        boolean[] inStack = new boolean[nodes];
        
        for(int node=0; node<nodes; node++){
            if(!visited[node]){
                boolean foundCycle = dfs(node, graph, colors, maxFreqColors, visited, inStack);
                if(foundCycle) return -1;
            }
        }
        
        int largestColorValue = 0;
        for(int node=0; node<nodes; node++){
            for(int color=0; color<26; color++) 
                largestColorValue = Math.max(largestColorValue, maxFreqColors[node][color]);
        }
        
        return largestColorValue;
    }
    
    private boolean dfs(int node, ArrayList<Integer>[] graph, String colors, int[][] maxFreqColors, boolean[] visited, boolean[] inStack){
        visited[node] = true;
        inStack[node] = true;
        
        ArrayList<Integer> neighbors = graph[node];
        for(int neighbor: neighbors){
            if(!visited[neighbor]){
                boolean foundCycle = dfs(neighbor, graph, colors, maxFreqColors, visited, inStack);
                if(foundCycle) return true;
            }else if(inStack[neighbor]) return true;
            
            for(int color=0; color<26; color++){
                maxFreqColors[node][color] = Math.max(maxFreqColors[node][color], maxFreqColors[neighbor][color]);
            }
        }
        
        maxFreqColors[node][colors.charAt(node)-'a']++;
        inStack[node] = false;
        
        return false;
    }
    
    private void createGraph(int[][] edges, ArrayList<Integer>[] graph, String colors, int nodes){
        for(int node=0; node<nodes; node++){
            graph[node] = new ArrayList();
        }
        
        for(int[] edge: edges){
            int node1 = edge[0];
            int node2 = edge[1];
            
            graph[node1].add(node2);
        }
    }
    
}