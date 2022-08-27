/* 

Brute force : generate all rectangular sums, takes O(rows*cols)^2 time
Better : 2D Kadane's + TreeSet 
   
TC : O(cols*cols*rowslog(rows))
SC : O(rows)

Can add pruning to solution for further improvement : 
Use pure kadanes algorithm for getMaxSum function for pruning
If maxSum obtained <=k no need to use treeset
Asymptotically it will still have same complexity

*/

class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int maxSum = Integer.MIN_VALUE;
        
        for(int col1=0;col1<cols;col1++){
            int arr[] = new int[rows];
            
            for(int col2=col1;col2<cols;col2++){
                for(int row=0;row<rows;row++) arr[row]+=matrix[row][col2];
                maxSum = Math.max(maxSum,getMaxSum(arr,k,rows));
            }
        }
        
        return maxSum;
    }
    
    private int getMaxSum(int arr[],int k,int len){
        TreeSet<Integer> prefixSums = new TreeSet();
        prefixSums.add(0);
        int prefixSum = 0;
        int maxSum = Integer.MIN_VALUE;
        
        for(int idx=0;idx<len;idx++){
            prefixSum+=arr[idx];
            Integer currSum = prefixSums.ceiling(prefixSum-k);
            
            if(currSum!=null) maxSum = Math.max(maxSum,prefixSum-currSum);
            prefixSums.add(prefixSum);
        }
        
        return maxSum;
    }
}