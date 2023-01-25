// Solution-1

// TC: O(nodes)
// SC: O(2*nodes)

// Find the distance from node1 to all nodes
// then do the same for node2
// then find the node with minimum maximum distance
// bfs/dfs both work in the same way here
// because every node has at max one outgoing edge

class Solution {
    public int closestMeetingNode(int[] edges, int node1, int node2) {
        int nodes = edges.length;
        Integer dist1[] = new Integer[nodes];
        Integer dist2[] = new Integer[nodes];
        int ansNode = -1;
        int minDist = Integer.MAX_VALUE;
        
        computeDistance(edges,node1,dist1);
        computeDistance(edges,node2,dist2);
        
        for(int node=0;node<nodes;node++){
            if(dist1[node]!=null&&dist2[node]!=null){
                int currMaxDist = Math.max(dist1[node],dist2[node]);
				
                if(currMaxDist<minDist){
                    minDist = currMaxDist;
                    ansNode = node;
                }
            }
        }
        
        return ansNode;
    }
    
    private void computeDistance(int edges[],int node,Integer dist[]){
        int currDist = 0;
        
        while(node!=-1&&dist[node]==null){
            dist[node] = currDist;
            currDist++;
            node = edges[node];
        }
    }
}


// Solution-2: Same idea as solution 1 but implemented using 1 dist array

// TC: O(nodes)
// SC: O(nodes)

class Solution {
    public int closestMeetingNode(int[] edges, int node1, int node2) {
        int nodes = edges.length; 
        int distFromNode1 = 0;
        int distFromNode2 = 0;
        int currNode1 = node1;
        int currNode2 = node2;
        int node = -1;
        Integer minDist = null;
        Integer[] dist = new Integer[nodes];
        
        while(currNode1!=-1){
            if(dist[currNode1]==null){
                dist[currNode1] = distFromNode1;
                currNode1 = edges[currNode1];
                distFromNode1++;
            }else currNode1 = -1; // cycle
        }
        
        while(currNode2!=-1){
            Integer distance = dist[currNode2];
            
            if(distance==null || distance!=-1){
                if(distance==null) dist[currNode2] = distFromNode2;
                else{
                    dist[currNode2] = Math.max(dist[currNode2], distFromNode2);
                    int currDist = dist[currNode2];

                    if(minDist==null){
                        minDist = currDist;
                        node = currNode2;
                    }else if(currDist<minDist){
                        minDist = currDist;
                        node = currNode2;
                    }else if(currDist==minDist) node = Math.min(node, currNode2);
                }
                
                dist[currNode2] = -1;
                currNode2 = edges[currNode2];
                distFromNode2++;
            }else currNode2 = -1; // cycle
        }
    
        return node;
    }
}