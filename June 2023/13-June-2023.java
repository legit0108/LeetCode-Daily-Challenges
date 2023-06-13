// TC: O(n^3)
// SC: O(1)

class Solution {
    public int equalPairs(int[][] grid) {
        int n = grid.length;
        int count = 0;
        
        for(int i =0; i<n; i++){
            for(int j=0; j<n; j++){
                int equalCount = 0;
                
                for(int k=0; k<n; k++){
                    if(grid[i][k]==grid[k][j]) equalCount++;    
                }
                
                if(equalCount==n) count++;
            }    
        }
        
        return count;
    }
}