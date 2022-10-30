// BFS but revisit a cell if it is being visited currently with lesser obstacles

// TC: O(rows*cols*k)
// SC: O(rows*cols*k)

// In worst case we need to add each cell into queue k times

class Solution {
    public int shortestPath(int[][] grid, int k) {
        int rows = grid.length;
        int cols = grid[0].length;
        Integer visited[][] = new Integer[rows][cols];
        
        Queue<int[]> queue = new ArrayDeque();
        int dirs[][] = {{-1,0}, {1,0}, {0,1}, {0,-1}};
        queue.add(new int[]{0,0,0}); // row,col,obstacles
        int minSteps = 0;
        
        while(queue.size()>0){
            int size = queue.size();
            
            while(size>0){
                int arr[] = queue.remove();
                int row = arr[0];
                int col = arr[1];
                int obstacles = arr[2];
                size--;
                
                if(visited[row][col]!=null && obstacles>=visited[row][col]) continue;
                if(row==rows-1 && col==cols-1) return minSteps;
                visited[row][col] = obstacles;
                
                for(int dir=0; dir<4; dir++){
                    int nextRow = row+dirs[dir][0];
                    int nextCol = col+dirs[dir][1];
                    
                    if(nextRow>=0 && nextCol>=0 && nextRow<rows && nextCol<cols){
                        int currObstacles = obstacles + ((grid[nextRow][nextCol]==1)?1:0);
                        
                        if(currObstacles<=k){
                            if(visited[nextRow][nextCol]==null || currObstacles<visited[nextRow][nextCol]){
                                queue.add(new int[]{nextRow,nextCol,currObstacles});
                            }
                        }
                    }
                }
            }
            
            minSteps++;
        }
        
        return -1;
    }
}