// BFS

// TC: O(rows*cols)
// SC: O(rows*cols)

class Solution {
    public int nearestExit(char[][] maze, int[] entrance) {
        int rows = maze.length;
        int cols = maze[0].length;
        
        boolean visited[][] = new boolean[rows][cols];
        Queue<int[]> queue = new ArrayDeque();
        int dirs[][] = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        
        queue.add(entrance);
        int minMoves = 0;
        
        while(queue.size()>0){
            int size = queue.size();
            
            while(size>0){
                int cell[] = queue.remove();
                size--;
                
                int row = cell[0];
                int col = cell[1];
                
                if(visited[row][col]) continue;
                if(isBorderCell(row, col, rows, cols) && !isEntranceCell(row, col, entrance)) return minMoves;
                
                visited[row][col] = true;
                
                for(int dir=0; dir<4; dir++){
                    int nextRow = row+dirs[dir][0];
                    int nextCol = col+dirs[dir][1];
                    
                    if(isValidCell(nextRow, nextCol, rows, cols, maze, visited))
                        queue.add(new int[]{nextRow, nextCol});
                }
            }
            
            minMoves++;
        }
        
        return -1;
    }
    
    private boolean isBorderCell(int row, int col, int rows, int cols){
        if(row==0 || col==0 || row==rows-1 || col==cols-1) return true;
        else return false;
    }
    
    private boolean isEntranceCell(int row, int col, int entrance[]){
        if(row==entrance[0] && col==entrance[1]) return true;
        else return false;
    }
    
    private boolean isValidCell(int row, int col, int rows, int cols, char maze[][], boolean visited[][]){
        if(row<0 || col<0 || row>=rows || col>=cols || visited[row][col] || maze[row][col]=='+') return false;
        else return true;
    }
}