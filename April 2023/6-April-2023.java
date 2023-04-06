/*
  Solution-1: DFS 
  
  -> In a closed island, each cell is surrounded by water on all 4 sides
  -> We cannot return early in DFS (as soon as we find a side with no water)
  -> To avoid counting an island as closed even when it is not, 
     we need to visit the entire component even if we find a side with no water
     
  TC: O(rows*cols)
  SC: O(rows*cols) 
 */

class Solution {
    public int closedIsland(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        
        int closedIslands = 0;
        
        for(int row=0; row<rows; row++){
            for(int col=0; col<cols; col++){
                if(grid[row][col]==0 && !visited[row][col]){
                    boolean foundWaterOnAllSides = dfs(row, col, rows, cols, grid, visited);
                    if(foundWaterOnAllSides) closedIslands++;
                }
            }
        }
        
        return closedIslands;
    }
    
    private boolean dfs(int row, int col, int rows, int cols, int[][] grid, boolean[][] visited){
        if(row<0 || col<0 || row>=rows || col>=cols) return false;
        if(visited[row][col] || grid[row][col]==1) return true;
        
        visited[row][col] = true;
        
        boolean foundWaterOnLeft = dfs(row, col-1, rows, cols, grid, visited);
        boolean foundWaterOnRight = dfs(row, col+1, rows, cols, grid, visited);
        boolean foundWaterOnTop = dfs(row-1, col, rows, cols, grid, visited);
        boolean foundWaterOnBottom = dfs(row+1, col, rows, cols, grid, visited);
        
        if(foundWaterOnLeft && foundWaterOnRight && foundWaterOnTop && foundWaterOnBottom) return true;
        else return false;
    }
}


// Solution-2: BFS

// -> Same idea as DFS
// -> Any connected component having land on border of the grid is not a closed island
// -> Avoid early return, same reason as in DFS

// TC: O(rows*cols)
// SC: O(rows*cols)

class Solution {
    public int closedIsland(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        
        int closedIslands = 0;
        
        for(int row=0; row<rows; row++){
            for(int col=0; col<cols; col++){
                if(grid[row][col]==0 && !visited[row][col]){
                    boolean foundWaterOnAllSides = bfs(row, col, rows, cols, grid, visited);
                    if(foundWaterOnAllSides) closedIslands++;
                }
            }
        }
        
        return closedIslands;
    }
    
    private boolean bfs(int row, int col, int rows, int cols, int[][] grid, boolean[][] visited){
        Queue<int[]> queue = new ArrayDeque();
        queue.add(new int[]{row, col});
        boolean foundWaterOnAllSides = true;
        
        while(queue.size()>0){
            int[] cell = queue.remove();
            row = cell[0];
            col = cell[1];
            
            if(row<0 || col<0 || row>=rows || col>=cols) foundWaterOnAllSides = false;
            else{ 
                if(visited[row][col] || grid[row][col]==1) continue;
            
                visited[row][col] = true;
            
                queue.add(new int[]{row, col-1});
                queue.add(new int[]{row, col+1});
                queue.add(new int[]{row-1, col});
                queue.add(new int[]{row+1, col});
            }
        }
        
        return foundWaterOnAllSides;
    }
}


// Solution-3: Union-Find
 
// -> Use rows*cols as a dummy parent of border cells
// -> Any island having the same root as our dummy parent is not a closed island

// TC: O(rows*cols) amortized
// SC: O(rows*cols)

class Solution {
    public int closedIsland(int[][] grid) {
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        int rows = grid.length;
        int cols = grid[0].length;
        UnionFind dsu = new UnionFind(rows*cols+1);
        int invalidCell = rows*cols;
        
        for(int row=0; row<rows; row++){
            for(int col=0; col<cols; col++){
                if(grid[row][col]==0){
                   int cell1 = row*cols+col;
                   if(isBorderCell(row, col, rows, cols)) dsu.union(cell1, invalidCell); 
                   else{
                       for(int index=0; index<4; index++){
                           int[] dir = dirs[index];
                           int adjRow = row+dir[0];
                           int adjCol = col+dir[1];

                           if(adjRow>=0 && adjRow<rows && adjCol>=0 && adjCol<cols && grid[adjRow][adjCol]==0){
                               int cell2 = adjRow*cols+adjCol;
                               dsu.union(cell1, cell2);
                           }
                       }
                   }
                }
            }
        }
        
        int invalidRoot = dsu.find(invalidCell);
        int closedIslands = 0;
        
        for(int row=0; row<rows; row++){
            for(int col=0; col<cols; col++){
                if(grid[row][col]==0){
                    int cell = row*cols+col;
                    int root = dsu.find(cell);
                    
                    if(root==cell && root!=invalidRoot) closedIslands++;
                }
            }
        }
        
        return closedIslands;
    }
    
    private boolean isBorderCell(int row, int col, int rows, int cols){
        if(row==0 || col==0 || row==rows-1 || col==cols-1) return true;
        else return false;
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
            
            for(int node=0; node<nodes; node++){
                parent[node] = node;
            }
        }
        
        int find(int node){
            int initNode = node;
            
            while(node!=parent[node]) node = parent[node];
            
            int root = node;
            node = initNode;
            
            while(node!=root){
                int par = parent[node];
                parent[node] = root;
                node = par;
            }
            
            return root;
        }
        
        void union(int node1, int node2){
            int root1 = find(node1);
            int root2 = find(node2);
            
            if(root1==root2) return;
            
            int rank1 = rank[root1];
            int rank2 = rank[root2];
            
            if(rank1<rank2) connect(root1, root2);
            else if(rank2<rank1) connect(root2, root1);
            else{
                connect(root1, root2);
                rank[root2]++;
            }
        }
        
        void connect(int root1, int root2){
            parent[root1] = root2;
        }
    }
}