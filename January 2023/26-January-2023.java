// Solution-1: BFS, revisit a node if lesser cost found or lesser stops found

// TC: O(n*n*k)
// SC: O(n*n*k)

class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        ArrayList<Pair>[] graph = new ArrayList[n];
        for(int node=0; node<n; node++) graph[node] = new ArrayList();
        
        for(int[] flight: flights){
            int from = flight[0];
            int to = flight[1];
            int price = flight[2];
            
            graph[from].add(new Pair(to, price));
        }
        
        Queue<Tuple> queue = new ArrayDeque();
        queue.add(new Tuple(src, 0, -1));
        Integer[] minCost = new Integer[n];
        Integer[] minStops = new Integer[n];
        
        while(queue.size()>0){
            Tuple tuple = queue.remove();
            int node = tuple.node;
            int costSoFar = tuple.costSoFar;
            int stopsSoFar = tuple.stopsSoFar;
            
            Integer minCostSoFar = minCost[node];
            Integer minStopsSoFar = minStops[node];
            
            if(isSmaller(costSoFar, minCostSoFar)) minCost[node] = costSoFar;
            if(isSmaller(stopsSoFar, minStopsSoFar)) minStops[node] = stopsSoFar;
            
            for(Pair pair: graph[node]){
                int neighbour = pair.neighbour;
                int weight = pair.weight;
                
                int costToNeighbour = costSoFar + weight;
                int stopsToNeighbour = stopsSoFar + 1;
                
                Integer currCost = minCost[neighbour];
                Integer currStops = minStops[neighbour];
                
                if(stopsToNeighbour<=k && (isSmaller(costToNeighbour, currCost) || isSmaller(stopsToNeighbour, currStops))){
                    queue.add(new Tuple(neighbour, costToNeighbour, stopsToNeighbour));
                }
            }
        }
        
        Integer cheapestCost = minCost[dst];
        
        if(cheapestCost==null) return -1;
        else return cheapestCost;
    }
    
    private boolean isSmaller(Integer curr, Integer min){
        if(min==null) return true;
        else{
            if(curr<min) return true;
            else return false;
        }
    }
    
    private class Tuple{
        private int node;
        private int costSoFar;
        private int stopsSoFar;
        
        private Tuple(){
        
        }
        
        private Tuple(int node, int costSoFar, int stopsSoFar){
            this.node = node;
            this.costSoFar = costSoFar;
            this.stopsSoFar = stopsSoFar;
        }
    }
    
    private class Pair{
        private int neighbour;
        private int weight;
        
        private Pair(){
            
        }
        
        private Pair(int neighbour, int weight){
            this.neighbour = neighbour;
            this.weight = weight;
        }
    }
}


// Solution-2: Bellman Ford

// TC: O(n*n*k)
// SC: O(n)

class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Integer[] minDist = new Integer[n];
        minDist[src] = 0;
        
        for(int itr=0; itr<=k; itr++){ // Bellman Ford iterates over n-1 edges for n nodes, for k stops we have k+2 nodes and thus k+1 edges
            Integer[] nextMinDist = new Integer[n];
            
            for(int node=0; node<n; node++) nextMinDist[node] = minDist[node];
            
            for(int[] flight: flights){
                int from = flight[0];
                int to = flight[1];
                int price = flight[2];
                
                if(minDist[from]!=null){
                    if(nextMinDist[to]==null) nextMinDist[to] = minDist[from] + price;
                    else nextMinDist[to] = Math.min(nextMinDist[to], minDist[from] + price);
                }
            }
            
            minDist = nextMinDist;
        }
        
        Integer cheapestPrice = minDist[dst];
        
        if(cheapestPrice!=null) return cheapestPrice;
        else return -1;
    }
}