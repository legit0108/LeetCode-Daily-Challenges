// Points having the same slope lie on the same line, pick each point as a pivot point and calculate the slope between the two points

// TC: O(points.length*points.length)
// SC: O(points.length)


class Solution {
    public int maxPoints(int[][] points) {
        int max_points = 1;
        
        for(int[] point1: points){
            int x1 = point1[0];
            int y1 = point1[1];
            
            HashMap<Double, Integer> slope_map = new HashMap();
            
            for(int[] point2: points){
                if(point1 == point2) continue;
                
                int x2 = point2[0];
                int y2 = point2[1];
                
                int diff_y = y2-y1;
                int diff_x = x2-x1;
                
                double slope;
                if(diff_x == 0) slope = Double.MAX_VALUE;
                else slope = (diff_y*1.0)/(diff_x*1.0);
                
                slope_map.put(slope, slope_map.getOrDefault(slope, 0)+1);
                max_points = Math.max(max_points, slope_map.get(slope)+1);
            }
        }
        
        return max_points;
    }
}