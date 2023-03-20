// TC: O(plots)
// SC: O(1)

class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int plots = flowerbed.length;
        int plot = 0;
        
        while(plot<plots && n>0){
            if(flowerbed[plot]==0){ // can possibly place flower
                if(plot+1<plots && flowerbed[plot+1]==1){ // if next plot is not empty, we cannot place flower at current plot
                    plot++;
                    continue;
                }
                
                n--; // next plot is empty (or it does not exist), can place flower
            }
            
            plot+=2; // either we placed a flower at current plot, or current plot was not empty, either way, we cannot place flower at next plot, increment plot by 2
        }
        
        return n==0; // return true if you can place all flowers
    }
}