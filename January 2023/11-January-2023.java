// Solution-1: DFS

// TC: O(nodes)
// SC: O(nodes)

class Solution {
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        ArrayList<Integer>[] tree = new ArrayList[n];
        for(int node=0; node<n; node++) tree[node] = new ArrayList();
        
        for(int[] edge: edges){
            int node1 = edge[0];
            int node2 = edge[1];
            
            tree[node1].add(node2);
            tree[node2].add(node1);
        }
        
        return dfs(0, -1, tree, hasApple);
    }
    
    private int dfs(int node, int parent, ArrayList<Integer>[] tree, List<Boolean> hasApple){
        int minTime = 0;
        
        for(int child: tree[node]){
            if(child!=parent){
                int time = dfs(child, node, tree, hasApple);
                minTime+=time;
            }
        }
        
        if(node!=0 && (minTime>0 || hasApple.get(node)==true)) minTime+=2;
        return minTime;
    }
}


// Solution-2: Iterative DFS

// TC: O(nodes)
// SC: O(nodes)

class Solution {
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        ArrayList<Integer>[] tree = new ArrayList[n];
        for(int node=0; node<n; node++) tree[node] = new ArrayList();
        
        for(int[] edge: edges){
            int node1 = edge[0];
            int node2 = edge[1];
            
            tree[node1].add(node2);
            tree[node2].add(node1);
        }
        
        return iterativeDfs(tree, n, hasApple);
    }
    
    private int iterativeDfs(ArrayList<Integer>[] tree, int n, List<Boolean> hasApple){
        int ans = 0;
        int[] minTime = new int[n];
        Stack<Node> stack = new Stack();
        stack.push(new Node(0, -1, 0));
        
        while(stack.size()>0){
            Node node = stack.peek();
            int vertex = node.vertex;
            int parent = node.parent;
            int state = node.state;
            int time = minTime[vertex];
            
            if(state==0){
                for(int child: tree[vertex]){
                    if(child!=parent){
                        stack.push(new Node(child, vertex, 0));
                    }
                }
                
                node.state++;
            }else{
                stack.pop();
                
                if(parent!=-1){
                    if(time>0 || hasApple.get(vertex)==true) time+=2;
                    minTime[parent]+=time;
                }
            }
        }
        
        return minTime[0];
    }
    
    private class Node{
        private int vertex;
        private int parent;
        private int state;
        
        private Node(){
            
        }
        
        private Node(int vertex, int parent, int state){
            this.vertex = vertex;
            this.parent = parent;
            this.state = state;
        }
    }
}


/*
   Solution-3: Kahn's algorithm-like
   Just like performing Kahn's algorithm of topological sort on a tree.
   Sum the degrees of each node first, then peel the tree from those nodes whose degree is 1.
   Once no appropriate node can be removed, return the sum of all degrees.
   Note that we shouldn't peel node 0.
*/

// TC: O(nodes)
// SC: O(nodes)

class Solution {
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        int[] degree = new int[n];
        ArrayList<Integer>[] graph = new ArrayList[n];
        for(int node=0; node<n; node++) graph[node] = new ArrayList();
        
        for(int[] edge: edges){
            int node1 = edge[0];
            int node2 = edge[1];
            
            degree[node1]++;
            degree[node2]++;
            
            graph[node1].add(node2);
            graph[node2].add(node1);
        }
        
        Queue<Integer> queue = new ArrayDeque();
        for(int node=0; node<n; node++){
            if(degree[node]==1) queue.add(node);
        }
        
        int totalEdges = edges.length;
        int removedEdges = 0;
        
        while(queue.size()>0){
            int node = queue.remove();
            if(hasApple.get(node)==true || node==0) continue;
            
            for(int neighbour: graph[node]){
                if(degree[neighbour]>0){
                    degree[neighbour]--;
                    degree[node]--;
                    removedEdges++;
                    
                    if(degree[neighbour]==1) queue.add(neighbour);
                }
            }
        }
        
        int minTime = (totalEdges-removedEdges)*2; // or return summation of degree
        return minTime;
    }
}