// Slope should be same for adjacent points

// TC: O(n)
// SC: O(1)

class Solution {
    public boolean checkStraightLine(int[][] coordinates) {
        for(int index=2; index<coordinates.length; index++){
            long x1 = coordinates[index-2][0];
            long y1 = coordinates[index-2][1];
            long x2 = coordinates[index-1][0];
            long y2 = coordinates[index-1][1];
            long x3 = coordinates[index][0];
            long y3 = coordinates[index][1];
            
            long factor1 = (y2-y1)*(x3-x2);
            long factor2 = (y3-y2)*(x2-x1);
            
            if(factor1!=factor2) return false;
        }
        
        return true;
    }
}