class Solution {
    private enum Color{
        RED, 
        BLUE,
        WHITE
    }
    
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        ArrayList<Pair>[] graph = createGraph(redEdges, blueEdges, n);
        
        int[] answer = new int[n];
        Arrays.fill(answer, -1);
        
        bfs(graph, n, answer);
        
        return answer;
    }
    
    private void bfs(ArrayList<Pair>[] graph, int nodes, int[] answer){
        Queue<Pair> queue = new ArrayDeque();
        HashMap<Integer, HashSet<Color>> visited = new HashMap();
        queue.add(new Pair(0, Color.WHITE)); // assign initial color as white
        visited.put(0, new HashSet<Color>());
        int level = 0;
        
        while(queue.size()>0){
            int size = queue.size();
            
            while(size>0){
                Pair pair = queue.remove();
                int node = pair.node;
                Color color = pair.color;
                size--;
                
                HashSet<Color> set = visited.get(node);
                if(set.contains(color)) continue;
                set.add(color);
                
                int pathLength = answer[node];
                if(pathLength==-1) answer[node] = level;
                else answer[node] = Math.min(pathLength, level);
                
                for(int idx=0; idx<graph[node].size(); idx++){
                    pair = graph[node].get(idx);
                    int neighbourNode = pair.node;
                    Color neighbourEdgeColor = pair.color; // color of edge through which we reach neighbourNode
                    
                    if(visited.get(neighbourNode)==null) visited.put(neighbourNode, new HashSet<Color>());
                    
                    if(!visited.get(neighbourNode).contains(neighbourEdgeColor) && neighbourEdgeColor!=color){
                         queue.add(new Pair(neighbourNode, neighbourEdgeColor));
                    }
                }
            }
            
            level++;
        }
    }
    
    private ArrayList<Pair>[] createGraph(int[][] redEdges, int[][] blueEdges, int nodes){
        ArrayList<Pair>[] graph = new ArrayList[nodes];
        for(int node=0; node<nodes; node++) graph[node] = new ArrayList();
        
        for(int[] edge: redEdges){
            int node1 = edge[0];
            int node2 = edge[1];
            
            graph[node1].add(new Pair(node2, Color.RED));
        }
        
        for(int[] edge: blueEdges){
            int node1 = edge[0];
            int node2 = edge[1];
            
            graph[node1].add(new Pair(node2, Color.BLUE));
        }
        
        return graph;
    }
    
    private class Pair{
        private int node;
        private Color color;
        
        private Pair(int node, Color color){
            this.node = node;
            this.color = color;
        }
    }
}