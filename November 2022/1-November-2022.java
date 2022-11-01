// Simulation

// TC: O(rows*cols)
// SC: O(1)

class Solution {
    public int[] findBall(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int ans[] = new int [cols];
        
        for(int col=0; col<cols; col++){
            ans[col] = getExitCol(grid, 0, col, rows, cols);
        }
        
        return ans;
    }
    
    private int getExitCol(int grid[][], int row, int col, int rows, int cols){
        while(row<rows){
            int val = grid[row][col];
            
            if(val==1){
               if(col+1>=cols || grid[row][col+1]==-1) return -1;
               row++;
               col++;
            }else{
               if(col-1<0 || grid[row][col-1]==1) return -1;
               row++;
               col--; 
            }
        }
        
        return col;
    }
}