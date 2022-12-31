// Backtracking

// TC: O(3^(rows*cols))
// SC: O(rows*cols)

class Solution {
    public int uniquePathsIII(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int startRow = -1;
        int startCol = -1;
        int destRow = -1;
        int destCol = -1;
        int emptySquares = 1; // to account for starting cell
        
        for(int row=0; row<rows; row++){
            for(int col=0; col<cols; col++){
                int val = grid[row][col];
                
                if(val==0) emptySquares++;
                else if(val==1){
                    startRow = row;
                    startCol = col;
                }
                else if(val==2){
                    destRow = row;
                    destCol = col;
                }
            }
        }
        
        return uniquePaths(startRow, startCol, destRow, destCol, rows, cols, emptySquares, grid, new boolean[rows][cols]);
    }
    
    private int uniquePaths(int row, int col, int destRow, int destCol, int rows, int cols, int emptySquares, int[][] grid, boolean[][] visited){
        if(row<0 || col<0 || row>=rows || col>=cols || visited[row][col] || grid[row][col]==-1) return 0;
        if(row==destRow && col==destCol){
            if(emptySquares==0) return 1;
            else return 0;
        }
        
        visited[row][col] = true;
        
        int paths = uniquePaths(row-1, col, destRow, destCol, rows, cols, emptySquares-1, grid, visited)
                   +uniquePaths(row+1, col, destRow, destCol, rows, cols, emptySquares-1, grid, visited)
                   +uniquePaths(row, col-1, destRow, destCol, rows, cols, emptySquares-1, grid, visited)
                   +uniquePaths(row, col+1, destRow, destCol, rows, cols, emptySquares-1, grid, visited);
        
        visited[row][col] = false;
        
        return paths;
    }
}