// Geometry

// TC: O(1)
// SC: O(1)

class Solution {
    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        // common part
        int intersect_x1 = Math.max(ax1, bx1);
        int intersect_y1 = Math.max(ay1, by1);
        int intersect_x2 = Math.min(ax2, bx2);
        int intersect_y2 = Math.min(ay2, by2);
        
        // sides
        int first_side_a = get_side_from_coordinates(ax1, ax2);
        int second_side_a = get_side_from_coordinates(ay1, ay2);
        int first_side_b =  get_side_from_coordinates(bx1, bx2);
        int second_side_b = get_side_from_coordinates(by1, by2);
        int first_side_intersect = get_side_from_coordinates(intersect_x1, intersect_x2);
        int second_side_intersect = get_side_from_coordinates(intersect_y1, intersect_y2);
        
        // area
        int area_of_a = get_area_from_sides(first_side_a, second_side_a);
        int area_of_b = get_area_from_sides(first_side_b, second_side_b);
        int area_of_intersect = get_area_from_sides(first_side_intersect, second_side_intersect);
        
        // total_area = area_of_a + area_of_b - area_of_intersect (subtract area_of_intersect since it is counted twice)
        
        int total_area = area_of_a + area_of_b - area_of_intersect;
        
        return total_area;
    }
    
    private int get_side_from_coordinates(int start, int end){
        if((start>=0 && end>=0) || (start<=0 && end<=0)) return end-start;
        else return Math.abs(start) + Math.abs(end);
    }
    
    private int get_area_from_sides(int first_side, int second_side){
        if(first_side<0 || second_side<0) return 0; // no intersection
        else return first_side*second_side;
    }
}