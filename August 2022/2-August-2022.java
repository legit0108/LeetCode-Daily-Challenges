// TC : O((rows+cols)*(log(high-low)))
// SC : O(1)

class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int low = matrix[0][0];
        int high = matrix[rows-1][cols-1];
        
        while(low<high){
            int mid = low + (high-low)/2;
            
            int count = find(matrix,rows,cols,mid,k);
            
            if(count>=k) high = mid;
            else low = mid+1;
        }
        
        return low;
    }
    
    private int find(int matrix[][],int rows,int cols,int num,int k){
        int count = 0;
        int row = 0;
        int col = cols-1;
        
        while(row<rows&&col>=0){
            if(matrix[row][col]<=num){
                count+=col+1;
                row++;
            }else col--;
        }
        
        return count;
    }
}