// The idea is to find the first island, then use BFS to reach the second island from the first island in minimum steps
// The first island can be found using BFS/DFS/Union Find
// All three solutions run in O(size*size) time and space


// Solution-1: DFS -> BFS

class Solution {
    public int shortestBridge(int[][] grid) {
        int size = grid.length;
        Queue<Pair<Integer, Integer>> queue = new ArrayDeque();
        boolean[][] visited = new boolean[size][size];
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        
        mainLoop:
        for(int row=0; row<size; row++){
            for(int col=0; col<size; col++){
                if(grid[row][col]==1){
                    dfs(queue, grid, visited, dirs, row, col, size);
                    break mainLoop;
                }
            }
        }
        
        return bfs(queue, grid, visited, dirs, size);
    }
    
    private void dfs(Queue<Pair<Integer, Integer>> queue, int[][] grid, boolean[][] visited, int[][] dirs, int row, int col, int size){
        visited[row][col] = true;
        queue.add(new Pair(row, col));
        
        for(int[] dir: dirs){
            int adjRow = row+dir[0];
            int adjCol = col+dir[1];

            if(isValidCell(adjRow, adjCol, size)){
                if(grid[adjRow][adjCol]==1 && !visited[adjRow][adjCol]) dfs(queue, grid, visited, dirs, adjRow, adjCol, size);
            }
        }
    }
    
    private int bfs(Queue<Pair<Integer, Integer>> queue, int[][] grid, boolean[][] visited, int[][] dirs, int size){
        int minFlips = -1;
        int breadth = 0;
        
        mainLoop:
        while(queue.size()>0){
            int cells = queue.size();
            
            while(cells>0){
                Pair<Integer, Integer> pair = queue.remove();
                cells--;
                int row = pair.getKey();
                int col = pair.getValue();
                int val = grid[row][col];
                
                if(visited[row][col] && val==0) continue;
                else if(!visited[row][col] && val==1){
                    minFlips = breadth-1;
                    break mainLoop;
                }
                
                visited[row][col] = true;
                for(int[] dir: dirs){
                    int adjRow = row+dir[0];
                    int adjCol = col+dir[1];

                    if(isValidCell(adjRow, adjCol, size)){
                        if(!visited[adjRow][adjCol]) queue.add(new Pair(adjRow, adjCol));
                    }
                }
            }
            
            breadth++;
        }
        
        return minFlips;
    }
    
    private boolean isValidCell(int row, int col, int size){
        if(row>=0 && col>=0 && row<size && col<size) return true;
        else return false;
    }
}


//-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----


// Solution-2: BFS -> BFS

class Solution {
    public int shortestBridge(int[][] grid) {
        int size = grid.length;
        Queue<Pair<Integer, Integer>> queue = new ArrayDeque();
        boolean[][] visited = new boolean[size][size];
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        
        mainLoop:
        for(int row=0; row<size; row++){
            for(int col=0; col<size; col++){
                if(grid[row][col]==1){
                    bfs(queue, grid, visited, dirs, row, col, size);
                    break mainLoop;
                }
            }
        }
        
        return bfs(queue, grid, visited, dirs, size);
    }
    
    private void bfs(Queue<Pair<Integer, Integer>> queue, int[][] grid, boolean[][] visited, int[][] dirs, int row, int col, int size){
        Queue<Pair<Integer, Integer>> temp = new ArrayDeque();
        temp.add(new Pair(row, col));
        
        while(temp.size()>0){
            int cells = temp.size();
            
            while(cells>0){
                Pair<Integer, Integer> pair = temp.remove();
                cells--;
                row = pair.getKey();
                col = pair.getValue();
                int val = grid[row][col];
                
                if(visited[row][col]) continue;
                visited[row][col] = true;
                queue.add(pair);

                for(int[] dir: dirs){
                    int adjRow = row+dir[0];
                    int adjCol = col+dir[1];

                    if(isValidCell(adjRow, adjCol, size)){
                        if(grid[adjRow][adjCol]==1 && !visited[adjRow][adjCol]) temp.add(new Pair(adjRow, adjCol));
                    }
                }
            }
        }
    }
    
    private int bfs(Queue<Pair<Integer, Integer>> queue, int[][] grid, boolean[][] visited, int[][] dirs, int size){
        int minFlips = -1;
        int breadth = 0;
        
        mainLoop:
        while(queue.size()>0){
            int cells = queue.size();
            
            while(cells>0){
                Pair<Integer, Integer> pair = queue.remove();
                cells--;
                int row = pair.getKey();
                int col = pair.getValue();
                int val = grid[row][col];
                
                if(visited[row][col] && val==0) continue;
                else if(!visited[row][col] && val==1){
                    minFlips = breadth-1;
                    break mainLoop;
                }
                
                visited[row][col] = true;
                for(int[] dir: dirs){
                    int adjRow = row+dir[0];
                    int adjCol = col+dir[1];

                    if(isValidCell(adjRow, adjCol, size)){
                        if(!visited[adjRow][adjCol]) queue.add(new Pair(adjRow, adjCol));
                    }
                }
            }
            
            breadth++;
        }
        
        return minFlips;
    }
    
    private boolean isValidCell(int row, int col, int size){
        if(row>=0 && col>=0 && row<size && col<size) return true;
        else return false;
    }
}


//-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----


// Solution-3: Union Find -> BFS

class Solution {
    public int shortestBridge(int[][] grid) {
        int size = grid.length;
        Queue<Pair<Integer, Integer>> queue = new ArrayDeque();
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        UnionFind dsu = new UnionFind(size*size);
        int cellContainingOne = -1;
        
        mainLoop:
        for(int row=0; row<size; row++){
            for(int col=0; col<size; col++){
                if(grid[row][col]==1){
                    int cell1 = getCellFromCoordinates(row, col, size);
                    cellContainingOne = cell1;
                    
                    for(int[] dir: dirs){
                        int adjRow = row+dir[0];
                        int adjCol = col+dir[1];
                        
                        if(isValidCell(adjRow, adjCol, size)){
                            if(grid[adjRow][adjCol]==1){
                                int cell2 = getCellFromCoordinates(adjRow, adjCol, size);
                                
                                dsu.union(cell1, cell2);
                            }
                        }
                    }
                }
            }
        }
        
        int root = dsu.find(cellContainingOne);
        
        for(int row=0; row<size; row++){
            for(int col=0; col<size; col++){
                int cell = getCellFromCoordinates(row, col, size);
                
                if(dsu.find(cell) == root) queue.add(new Pair(row, col));
            }
        }
        
        return bfs(queue, grid, dirs, dsu, root, size);
    }
    
    
    private int bfs(Queue<Pair<Integer, Integer>> queue, int[][] grid, int[][] dirs, UnionFind dsu, int root, int size){
        boolean[][] visited = new boolean[size][size];
        int minFlips = -1;
        int breadth = 0;
        
        mainLoop:
        while(queue.size()>0){
            int cells = queue.size();
            
            while(cells>0){
                Pair<Integer, Integer> pair = queue.remove();
                cells--;
                int row = pair.getKey();
                int col = pair.getValue();
                int val = grid[row][col];
                
                if(visited[row][col]) continue;
                
                int cell = getCellFromCoordinates(row, col, size);
                if(val==1 && dsu.find(cell)!=root){ // reached a one such that it has a root different from roots of cells in queue implies we have reached the second island
                    minFlips = breadth-1;
                    break mainLoop;
                }
                
                visited[row][col] = true;
                
                for(int[] dir: dirs){
                    int adjRow = row+dir[0];
                    int adjCol = col+dir[1];

                    if(isValidCell(adjRow, adjCol, size)){
                        if(!visited[adjRow][adjCol]) queue.add(new Pair(adjRow, adjCol));
                    }
                }
            }
            
            breadth++;
        }
        
        return minFlips;
    }
    
    private boolean isValidCell(int row, int col, int size){
        if(row>=0 && col>=0 && row<size && col<size) return true;
        else return false;
    }
    
    private int getCellFromCoordinates(int row, int col, int size){
        return row*size+col;
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
        
        void union(int node1, int node2){
            int root1 = find(node1);
            int root2 = find(node2);
            
            if(root1==root2) return;
            
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
    }
}