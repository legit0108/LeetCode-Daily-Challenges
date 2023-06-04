// Solution-1: BFS
// TC: O(n*n)
// SC: O(n*n)

class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        if(grid[0][0]!=0) return -1;
        
        int[][] dirs = new int[][]{{-1, 0},{1, 0}, {0, 1}, {0, -1}, {-1, -1}, {-1, 1}, {1, -1}, {1,1}};
        int len = grid.length;
        
        Queue<int[]> queue = new ArrayDeque();
        queue.add(new int[]{0, 0});
        int minCells = 1;
        boolean[][] visited = new boolean[len][len];
        
        while(queue.size()>0){
            int size = queue.size();
            
            while(size>0){
                int[] coords = queue.remove();
                size--;
                int row = coords[0];
                int col = coords[1];
                
                if(row==len-1 && col==len-1) return minCells;
                if(visited[row][col]) continue;
                visited[row][col] = true;
                
                for(int[] dir: dirs){
                    int nextRow = row+dir[0];
                    int nextCol = col+dir[1];
                    
                    if(isValid(nextRow, nextCol, len)){
                        if(!visited[nextRow][nextCol] && grid[nextRow][nextCol]==0) queue.add(new int[]{nextRow, nextCol});
                    }
                }
            }
            
            minCells++;
        }
        
        return -1;
    }
    
    private boolean isValid(int row, int col, int len){
        if(row<0 || col<0 || row>=len || col>=len) return false;
        else return true;
    }
}