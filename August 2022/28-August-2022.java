/*

Group diagonals using map, sort each diagonal using min heap

TC : O(rows*cols*log(min(rows,cols)))
SC : O(rows*cols)

*/

class Solution {
    public int[][] diagonalSort(int[][] mat) {
        HashMap<Integer,PriorityQueue<Integer>> map = new HashMap();
        int rows = mat.length;
        int cols = mat[0].length;
        
        for(int row=0;row<rows;row++){
            for(int col=0;col<cols;col++){
                int diagonal = row-col;
                if(!map.containsKey(diagonal)) map.put(diagonal,new PriorityQueue());
                map.get(diagonal).add(mat[row][col]);
            }
        }
        
        for(int row=0;row<rows;row++){
            for(int col=0;col<cols;col++){
                int diagonal = row-col;
                mat[row][col] = map.get(diagonal).remove();
            }
        }
        
        return mat;
    }
}