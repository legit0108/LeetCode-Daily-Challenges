// Transpose matrix then reverse each row

// TC : O(n*n)
// SC : O(1)

class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        
        for(int row=0;row<n;row++){
            for(int col=0;col<row;col++){
                swap(matrix,row,col); 
            }
        }
        
        for(int row=0;row<n;row++){
            reverse(matrix[row]);
        }
    }
    
    private void reverse(int arr[]){
        int start = 0;
        int end = arr.length-1;
        
        while(start<end){
            swap(arr,start,end);
            start++;
            end--;
        }
    }
    
    private void swap(int arr[],int idx1,int idx2){
        int temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }
    
    private void swap(int matrix[][],int row,int col){
        int temp = matrix[row][col];
        matrix[row][col] = matrix[col][row];
        matrix[col][row] = temp;
    }
}