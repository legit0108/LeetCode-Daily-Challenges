/*
-> Note that this problem is very similar to 1254. Number of Closed Islands (Refer to 6-April-2023.java commit)
-> In that problem, we had to count the number of components having no boundary cell
-> Here we need to count the total number of cells in all such components having no boundary cell

All the solutions run in O(rows*cols) time and space (Union-Find is amortized O(rows*cols))
*/


// Solution-1: DFS

class Solution {
    private boolean foundBoundary;
    
    public int numEnclaves(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int count = 0;
        
        for(int row=0; row<rows; row++){
            for(int col=0; col<cols; col++){
                if(grid[row][col]==1 && !visited[row][col]){
                    foundBoundary = false;
                    
                    int cells = dfs(row, col, rows, cols, grid, visited);
                    
                    if(!foundBoundary) count+=cells; 
                }
            }
        }
        
        return count;
    }
    
    private int dfs(int row, int col, int rows, int cols, int[][] grid, boolean[][] visited){
        if(isInvalidCell(row, col, rows, cols, grid, visited)) return 0;
        if(isBoundaryCell(row, col, rows, cols)) foundBoundary = true;
         
        visited[row][col] = true;
        int cells = 1;
        
        cells+=dfs(row-1, col, rows, cols, grid, visited)+
               dfs(row+1, col, rows, cols, grid, visited)+
               dfs(row, col-1, rows, cols, grid, visited)+
               dfs(row, col+1, rows, cols, grid, visited);
           
        return cells;   
    }
    
    private boolean isInvalidCell(int row, int col, int rows, int cols, int[][] grid, boolean[][] visited){
        if(row<0 || col<0 || row>=rows || col>=cols || grid[row][col]==0 || visited[row][col]) return true;
        else return false;
    }
    
    private boolean isBoundaryCell(int row, int col, int rows, int cols){
        if(row==0 || col==0 || row==rows-1 || col==cols-1) return true;
        else return false;
    }
}


// Solution-2: BFS

class Solution {
    private boolean foundBoundary;
    
    public int numEnclaves(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int count = 0;
        
        for(int row=0; row<rows; row++){
            for(int col=0; col<cols; col++){
                if(grid[row][col]==1 && !visited[row][col]){
                    foundBoundary = false;
                    
                    int cells = bfs(row, col, rows, cols, grid, visited);
                    
                    if(!foundBoundary) count+=cells; 
                }
            }
        }
        
        return count;
    }
    
    private int bfs(int row, int col, int rows, int cols, int[][] grid, boolean[][] visited){
        Queue<Integer> queue = new ArrayDeque();
        int cell = getCellFromCoordinates(row, col, cols);
        queue.add(cell);
        int cells = 0;
        
        while(queue.size()>0){
            cell = queue.remove();
            int[] coordinates = getCoordinatesFromCell(cell, cols);
            row = coordinates[0];
            col = coordinates[1];
            
            if(isInvalidCell(row, col, rows, cols, grid, visited)) continue;
            if(isBoundaryCell(row, col, rows, cols)) foundBoundary = true;
         
            visited[row][col] = true;
            cells++;
            
            queue.add(getCellFromCoordinates(row-1, col, cols));
            queue.add(getCellFromCoordinates(row+1, col, cols));
            queue.add(getCellFromCoordinates(row, col-1, cols));
            queue.add(getCellFromCoordinates(row, col+1, cols));
        }
                      
        return cells;   
    }
    
    private int[] getCoordinatesFromCell(int cell, int cols){
        return new int[]{cell/cols, cell%cols};
    }
    
    private int getCellFromCoordinates(int row, int col, int cols){
        return row*cols+col;
    }
    
    private boolean isInvalidCell(int row, int col, int rows, int cols, int[][] grid, boolean[][] visited){
        if(row<0 || col<0 || row>=rows || col>=cols || grid[row][col]==0 || visited[row][col]) return true;
        else return false;
    }
    
    private boolean isBoundaryCell(int row, int col, int rows, int cols){
        if(row==0 || col==0 || row==rows-1 || col==cols-1) return true;
        else return false;
    }
}


// Solution-3: Union-Find

class Solution {
    public int numEnclaves(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int cells = rows*cols;
        
        UnionFind dsu = new UnionFind(cells+1);
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int invalidCell = cells;
        
        for(int row=0; row<rows; row++){
            for(int col=0; col<cols; col++){
                if(grid[row][col]==1){
                    int cell1 = getCellFromCoordinates(row, col, cols);
                    
                    if(isBoundaryCell(row, col, rows, cols)) dsu.union(cell1, invalidCell);
                    else{
                        for(int[] dir: dirs){
                            int adjRow = row + dir[0];
                            int adjCol = col + dir[1];
                            
                            if(!isInvalidCell(adjRow, adjCol, rows, cols, grid)){
                                int cell2 = getCellFromCoordinates(adjRow, adjCol, cols);
                                dsu.union(cell1, cell2);
                            }
                        }
                    }
                }
            }
        }
        
        int invalidRoot = dsu.find(invalidCell);
        int count = 0;
        
        for(int row=0; row<rows; row++){
            for(int col=0; col<cols; col++){
                if(grid[row][col]==1){
                    int cell = getCellFromCoordinates(row, col, cols);
                    int root = dsu.find(cell);
                    
                    if(root!=invalidRoot) count++;
                }
            }
        }
        
        return count;
    }
    
    private int getCellFromCoordinates(int row, int col, int cols){
        return row*cols+col;
    }
    
    private boolean isInvalidCell(int row, int col, int rows, int cols, int[][] grid){
        if(row<0 || col<0 || row>=rows || col>=cols || grid[row][col]==0) return true;
        else return false;
    }
    
    private boolean isBoundaryCell(int row, int col, int rows, int cols){
        if(row==0 || col==0 || row==rows-1 || col==cols-1) return true;
        else return false;
    }
    
    private class UnionFind{
        int[] parent;
        int[] rank;
        int nodes;
        
        UnionFind(int nodes){
            this.nodes = nodes;
            parent = new int[nodes];
            rank = new int[nodes];
            
            for(int node=0; node<nodes; node++) parent[node] = node;
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