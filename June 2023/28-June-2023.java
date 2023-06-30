// TC: O(nlogn)
// SC: O(n)

class Solution {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        ArrayList<Pair>[] graph = new ArrayList[n];
        for(int node=0; node<n; node++) graph[node] = new ArrayList();
        
        for(int index=0; index<edges.length; index++){
            int[] edge = edges[index];
            int node1 = edge[0];
            int node2 = edge[1];
            double weight = succProb[index];
            
            graph[node1].add(new Pair(node2, weight));
            graph[node2].add(new Pair(node1, weight));
        }
        
        boolean[] visited = new boolean[n];
        PriorityQueue<Pair> maxHeap = new PriorityQueue();
        maxHeap.add(new Pair(start, 1.0));
        
        while(maxHeap.size()>0){
            Pair pair = maxHeap.remove();
            if(visited[pair.node]) continue;
            visited[pair.node] = true;
            
            if(pair.node==end) return pair.weight;
            
            for(Pair neighbour: graph[pair.node]){
                if(!visited[neighbour.node]) maxHeap.add(new Pair(neighbour.node, pair.weight*neighbour.weight));
            }
        }
        
        return 0.0;
    }
    
    private class Pair implements Comparable<Pair>{
        int node;
        double weight;
        
        Pair(int node, double weight){
            this.node = node;
            this.weight = weight;
        }
        
        public int compareTo(Pair other){
            return Double.compare(other.weight, this.weight);
        }
    }
}