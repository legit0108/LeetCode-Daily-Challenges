/*
// Definition for a QuadTree node.
class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;

    
    public Node() {
        this.val = false;
        this.isLeaf = false;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }
    
    public Node(boolean val, boolean isLeaf) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }
    
    public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }
};
*/


// Solution-1: Brute force

// TC: O(n*n*log(n))
// SC: O(log(n))

class Solution {
    public Node construct(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        return construct(grid, 0, 0, rows-1, cols-1, rows, cols);
    }
    
    private Node construct(int[][] grid, int startRow, int startCol, int endRow, int endCol, int rows, int cols){
        int result = traverse(grid, startRow, startCol, endRow, endCol);
        
        if(result==0) return new Node(false, true);
        else if(result==1) return new Node(true, true);
        else{
            Node root = new Node();
            
            rows = rows/2;
            cols = cols/2;
            int centreRow = startRow+(rows-1);
            int centreCol = startCol+(cols-1);
            
            root.topLeft = construct(grid, startRow, startCol, centreRow, centreCol, rows, cols);
            root.topRight = construct(grid, startRow, centreCol+1, centreRow, endCol, rows, cols);
            root.bottomLeft = construct(grid, centreRow+1, startCol, endRow, centreCol, rows, cols);
            root.bottomRight = construct(grid, centreRow+1, centreCol+1, endRow, endCol , rows, cols);
        
            return root;
        }
    }
    
    private int traverse(int[][] grid, int startRow, int startCol, int endRow, int endCol){
        boolean allOnes = true;
        boolean allZeros = true;
        
        for(int row=startRow; row<=endRow; row++){
            for(int col=startCol; col<=endCol; col++){
                int val = grid[row][col];
                
                if(val==0) allOnes = false;
                else allZeros = false;
            }
        }
        
        if(allZeros) return 0;
        else if(allOnes) return 1;
        else return -1;
    }
}


// Solution-2: Optimize Solution-1 using prefix sum

// TC: O(n*n)
// SC: O(n*n)

class Solution {
    public Node construct(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        
        int[][] prefixSum = getPrefixSum(grid, rows, cols);
        
        return construct(grid, 0, 0, rows-1, cols-1, rows, cols, prefixSum);
    }
    
    private int[][] getPrefixSum(int[][] grid, int rows, int cols){
        int[][] prefixSum = new int[rows][cols];
        
        for(int row=0; row<rows; row++){
            for(int col=0; col<cols; col++){
                int val = grid[row][col];
                
                if(row==0 && col==0) prefixSum[row][col] = val;
                else if(row==0) prefixSum[row][col] = val + prefixSum[row][col-1];
                else if(col==0) prefixSum[row][col] = val + prefixSum[row-1][col];
                else{
                    prefixSum[row][col] = val + prefixSum[row][col-1] + prefixSum[row-1][col] - prefixSum[row-1][col-1];
                }
            }
        }
        
        return prefixSum;
    }
    
    private Node construct(int[][] grid, int startRow, int startCol, int endRow, int endCol, int rows, int cols, int[][] prefixSum){
        int sum = getSum(grid, startRow, startCol, endRow, endCol, prefixSum);
        
        if(sum==0) return new Node(false, true);
        else if(sum==rows*cols) return new Node(true, true);
        else{
            Node root = new Node();
            
            rows = rows/2;
            cols = cols/2;
            int centreRow = startRow+(rows-1);
            int centreCol = startCol+(cols-1);
            
            root.topLeft = construct(grid, startRow, startCol, centreRow, centreCol, rows, cols, prefixSum);
            root.topRight = construct(grid, startRow, centreCol+1, centreRow, endCol, rows, cols, prefixSum);
            root.bottomLeft = construct(grid, centreRow+1, startCol, endRow, centreCol, rows, cols, prefixSum);
            root.bottomRight = construct(grid, centreRow+1, centreCol+1, endRow, endCol , rows, cols, prefixSum);
        
            return root;
        }
    }
    
    private int getSum(int[][] grid, int startRow, int startCol, int endRow, int endCol, int[][] prefixSum){
        int sum = prefixSum[endRow][endCol];
        
        if(startRow-1>=0) sum-=prefixSum[startRow-1][endCol];
        if(startCol-1>=0) sum-=prefixSum[endRow][startCol-1];
        if(startRow-1>=0 && startCol-1>=0) sum+=prefixSum[startRow-1][startCol-1];
        
        return sum;
    }
}


// Solution-3: One pass without prefix sum
// Current node is a leaf node if all its children are leaf node having same value

// TC: O(n*n)
// SC: O(log(n))

class Solution {
    public Node construct(int[][] grid) {
        int len = grid.length;
        
        return construct(grid, 0, 0, len);
    }
    
    private Node construct(int[][] grid, int row, int col, int len){
        if(len==1){
            int val = grid[row][col];
            
            if(val==1) return new Node(true, true);
            else return new Node(false, true);
        }
        else{
            Node root = new Node();
            len = len/2;
            
            Node topLeft = construct(grid, row, col, len);
            Node topRight = construct(grid, row, col+len, len);
            Node bottomLeft = construct(grid, row+len, col, len);
            Node bottomRight = construct(grid, row+len, col+len, len);
            
            if(leafNode(topLeft, topRight, bottomLeft, bottomRight)) return new Node(topLeft.val, true);
            else return new Node(false, false, topLeft, topRight, bottomLeft, bottomRight);
        }
    }
    
    private boolean leafNode(Node topLeft, Node topRight, Node bottomLeft, Node bottomRight){
        if(topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf
           && topLeft.val == topRight.val && topRight.val == bottomLeft.val && bottomLeft.val == bottomRight.val) return true;
        else return false;
    }
}