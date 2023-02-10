// Solution-1: BFS

// TC: O(rows*cols)
// SC: O(rows*cols)

class Solution {
    public int maxDistance(int[][] grid) {
        Queue<Pair> queue = new ArrayDeque();
        int rows = grid.length;
        int cols = grid[0].length;
        
        for(int row=0; row<rows; row++){
            for(int col=0; col<cols; col++){
                if(grid[row][col]==1) queue.add(new Pair(row, col));
            }
        }
        
        boolean[][] visited = new boolean[rows][cols];
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        int maxDist = -1;
        int level = 0;
        
        while(queue.size()>0){
            int size = queue.size();
            
            while(size>0){
                Pair pair = queue.remove();
                int row = pair.row;
                int col = pair.col;
                size--; 
                
                if(visited[row][col]) continue;
                visited[row][col] = true;

                if(grid[row][col]==0) maxDist = Math.max(maxDist, level);

                for(int idx=0; idx<4; idx++){
                    int[] dir = dirs[idx];
                    int nextRow = row+dir[0];
                    int nextCol = col+dir[1];
                    
                    if(cellInRange(nextRow, nextCol, rows, cols)){
                        if(grid[nextRow][nextCol]==0 && !visited[nextRow][nextCol]) queue.add(new Pair(nextRow, nextCol));
                    }
                }
            }
            
            level++;
        }
        
        return maxDist;
    }
    
    private boolean cellInRange(int row, int col, int rows, int cols){
        if(row>=0 && col>=0 && row<rows && col<cols) return true;
        else return false;
    }
    
    private class Pair{
        private int row;
        private int col;
        
        private Pair(){
            
        }

        private Pair(int row, int col){
            this.row = row;
            this.col = col;
        }
    }
}


// Solution-2: Space optimized DP by modifying input

// TC: O(rows*cols)
// SC: O(1)

class Solution {
    public int maxDistance(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int max = rows+cols;
        
        for(int row=0; row<rows; row++){
            for(int col=0; col<cols; col++){
                int val = grid[row][col];
                
                if(val==0) grid[row][col] = max; // init dist with max
                else grid[row][col] = 0;
                
                if(val==0){
                    grid[row][col] = getMinDist(grid, row, col, rows, cols, 0);
                }
            }
        }
        
        int maxDist = -1;
        
        for(int row=rows-1; row>=0; row--){
            for(int col=cols-1; col>=0; col--){
                int dist = grid[row][col];
                if(dist>0){ // init value was 0
                    grid[row][col] = getMinDist(grid, row, col, rows, cols, 1);
                }
                
                dist = grid[row][col]; // final dist
                if(dist!=0 && dist!=max) maxDist = Math.max(maxDist, dist);
            }
        }
        
        return maxDist;
    }
    
    private int getMinDist(int[][] grid, int row, int col, int rows, int cols, int flag){
        int minDist = grid[row][col];
        
        if(flag==0){ // first iteration
            if(row-1>=0) minDist = Math.min(minDist, grid[row-1][col]+1);
            if(col-1>=0) minDist = Math.min(minDist, grid[row][col-1]+1);
        }else{ // second iteration
            if(row+1<rows) minDist = Math.min(minDist, grid[row+1][col]+1);
            if(col+1<cols) minDist = Math.min(minDist, grid[row][col+1]+1);
        }
        
        return minDist;
    }
}