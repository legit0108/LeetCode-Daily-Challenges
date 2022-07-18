// Subarray sum equals target , but in 2D
// TC : O(rows*rows*cols)
// SC : O(cols)

class Solution {
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int arr[];
        int count = 0;
        
        for(int row=0;row<rows;row++){
            arr = new int[cols];
            
            for(int currRow=row;currRow<rows;currRow++){
                for(int currCol=0;currCol<cols;currCol++){
                    arr[currCol]+=matrix[currRow][currCol];
                }
                
                count+=getCount(arr,target,cols);
            }
        }
        
        return count;
    }
    
    private int getCount(int arr[],int target,int len){
        HashMap<Integer,Integer> map = new HashMap();
        map.put(0,1);
        int count = 0;
        int sum = 0;
        
        for(int idx=0;idx<len;idx++){
            sum+=arr[idx];
            count+=map.getOrDefault(sum-target,0);
            map.put(sum,map.getOrDefault(sum,0)+1);
        }
        
        return count;
    }
}