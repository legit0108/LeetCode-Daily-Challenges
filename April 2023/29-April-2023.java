/*
 Union-Find + Two-Pointers
 
 Ideas: 
 Offline queries
 Sort queries in increasing order of limit, edge list in increasing order of dist
 Process queries in increasing order of limit, join edges from edge list having dist<limit
 If find(node1) == find(node2) for current query after joining all edges having dist<limit,
 ans for query is true else false
 
 TC: O(n + elog(e) + qlog(q)), where e = number of edges, q = number of queries
 SC: O(q + n)
*/

class Solution {
    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        int totalQueries = queries.length;
        List<int[]> queriesList = new ArrayList();
        for(int index=0; index<totalQueries; index++){
            int[] query = queries[index];
            int node1 = query[0];
            int node2 = query[1];
            int limit = query[2];
            
            queriesList.add(new int[]{node1, node2, limit, index});
        }
        
        Collections.sort(queriesList, (query1, query2)->Integer.compare(query1[2], query2[2]));
        Arrays.sort(edgeList, (edge1, edge2)->Integer.compare(edge1[2], edge2[2]));
        
        UnionFind dsu = new UnionFind(n);
        
        int edgeListIndex = 0;
        int edgeListLen = edgeList.length;
        int queriesListIndex = 0;
        int queriesListLen = queriesList.size();
        
        boolean[] answer = new boolean[queriesListLen];
        
        while(queriesListIndex<queriesListLen){
            int[] query = queriesList.get(queriesListIndex);
            int queryNode1 = query[0];
            int queryNode2 = query[1];
            int limit = query[2];
            int index = query[3];
            
            while(edgeListIndex<edgeListLen && edgeList[edgeListIndex][2]<limit){
                int[] edge = edgeList[edgeListIndex];
                int node1 = edge[0];
                int node2 = edge[1];
                
                dsu.union(node1, node2);
                
                edgeListIndex++;
            }
            
            if(dsu.find(queryNode1) == dsu.find(queryNode2)) answer[index] = true;
            else answer[index] = false;
            
            queriesListIndex++;
        }
        
        return answer;
    }
    
    private class UnionFind{
        int[] parent;
        int[] rank;
        int nodes;
        
        UnionFind(){}
        
        UnionFind(int nodes){
            this.nodes = nodes;
            
            parent = new int[nodes];
            rank = new int[nodes];
            
            for(int node=0; node<nodes; node++) parent[node] = node;
        }
        
        boolean union(int node1, int node2){
            int root1 = find(node1);
            int root2 = find(node2);
            
            if(root1==root2) return false;
            
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
            
            return true;
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