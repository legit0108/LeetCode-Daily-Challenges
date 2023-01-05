// Shoot an arrow at the minimum end point of overlapping balloons to minimize the number of arrows


// Solution-1: Sort based on the end point, the code automatically takes care of encountering the minimum endpoint first

// TC: O(len*log(len))
// SC: O(1)

class Solution {
    public int findMinArrowShots(int[][] points) {
        int len = points.length;
        Arrays.sort(points, ((point1, point2)->Integer.compare(point1[1], point2[1])));
        int end = points[0][1];
        int minArrows = 1;
        
        for(int idx=1; idx<len; idx++){
            int[] point = points[idx];
            int currStart = point[0];
            int currEnd = point[1];
            
            if(currStart>end){
                minArrows++;
                end = currEnd;
            }
        }
        
        return minArrows;
    }
}


// Solution-2: Sort based on the start point, keep track of the minimum end point

// TC: O(len*log(len))
// SC: O(1)

class Solution {
    public int findMinArrowShots(int[][] points) {
        int len = points.length;
        Arrays.sort(points, ((point1, point2)->Integer.compare(point1[0], point2[0])));
        int end = points[0][1];
        int minArrows = 1;
        
        for(int idx=1; idx<len; idx++){
            int[] point = points[idx];
            int currStart = point[0];
            int currEnd = point[1];
            
            if(currStart>end){
                minArrows++;
                end = currEnd;
            }else end = Math.min(end, currEnd);
        }
        
        return minArrows;
    }
}