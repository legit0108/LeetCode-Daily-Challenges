// Flood from the ocean

// Method-1 : BFS

// TC : O(rows*cols)
// SC : O(rows*cols)

class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int rows = heights.length;
        int cols = heights[0].length;
        
        boolean pacificVisited[][] = new boolean[rows][cols];
        boolean atlanticVisited[][] = new boolean[rows][cols];
        
        for(int row=0;row<rows;row++){
            bfs(row, 0, rows, cols, heights, pacificVisited);
            bfs(row, cols-1, rows, cols, heights, atlanticVisited);
        }
        
        for(int col=0;col<cols;col++){
            bfs(0, col, rows, cols, heights, pacificVisited);
            bfs(rows-1, col, rows, cols, heights, atlanticVisited);
        }
        
        List<List<Integer>> coordinates = new ArrayList();
        
        for(int row=0;row<rows;row++){
            for(int col=0;col<cols;col++){
                if(pacificVisited[row][col]&&atlanticVisited[row][col]){
                    List<Integer> coordinate = new ArrayList();
                    
                    coordinate.add(row);
                    coordinate.add(col);
                    
                    coordinates.add(coordinate);
                }   
            }
        }
        
        return coordinates;
    }
    
    private void bfs(int row, int col, int rows, int cols, int heights[][], boolean visited[][]){
        int dirs[][] = {{-1,0}, {1,0}, {0,1}, {0,-1}};
        
        Queue<int[]> queue = new ArrayDeque();
        queue.add(new int[]{row,col});
        
        while(queue.size()>0){
            int coordinate[] = queue.remove();
            
            row = coordinate[0];
            col = coordinate[1];
            
            if(visited[row][col]) continue;
            visited[row][col] = true;
            
            for(int dir=0;dir<4;dir++){
                int nextPossibleRow = row+dirs[dir][0];
                int nextPossibleCol = col+dirs[dir][1];
                
                if(nextPossibleRow>=0&&nextPossibleCol>=0&&nextPossibleRow<rows&&nextPossibleCol<cols){
                    if(heights[nextPossibleRow][nextPossibleCol]>=heights[row][col]&&!visited[nextPossibleRow][nextPossibleCol]){
                        queue.add(new int[]{nextPossibleRow,nextPossibleCol});
                    }
                }
            }
        }
    }
}

// Flood from the ocean

// Method-2 : DFS

// TC : O(rows*cols)
// SC : O(rows*cols)

class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int rows = heights.length;
        int cols = heights[0].length;
        
        boolean pacificVisited[][] = new boolean[rows][cols];
        boolean atlanticVisited[][] = new boolean[rows][cols];
        
        for(int row=0;row<rows;row++){
            dfs(row, 0, rows, cols, heights, pacificVisited);
            dfs(row, cols-1, rows, cols, heights, atlanticVisited);
        }
        
        for(int col=0;col<cols;col++){
            dfs(0, col, rows, cols, heights, pacificVisited);
            dfs(rows-1, col, rows, cols, heights, atlanticVisited);
        }
        
        List<List<Integer>> coordinates = new ArrayList();
        
        for(int row=0;row<rows;row++){
            for(int col=0;col<cols;col++){
                if(pacificVisited[row][col]&&atlanticVisited[row][col]){
                    List<Integer> coordinate = new ArrayList();
                    
                    coordinate.add(row);
                    coordinate.add(col);
                    
                    coordinates.add(coordinate);
                }   
            }
        }
        
        return coordinates;
    }
    
    private int dirs[][] = {{-1,0}, {1,0}, {0,1}, {0,-1}};
    
    private void dfs(int row, int col, int rows, int cols, int heights[][], boolean visited[][]){
        visited[row][col] = true;
        
        for(int dir=0;dir<4;dir++){
            int nextPossibleRow = row+dirs[dir][0];
            int nextPossibleCol = col+dirs[dir][1];
            
            if(nextPossibleRow>=0&&nextPossibleCol>=0&&nextPossibleRow<rows&&nextPossibleCol<cols){
                if(heights[nextPossibleRow][nextPossibleCol]>=heights[row][col]&&!visited[nextPossibleRow][nextPossibleCol]){
                    dfs(nextPossibleRow, nextPossibleCol, rows, cols, heights, visited);
                }
            }
        }
    }
}