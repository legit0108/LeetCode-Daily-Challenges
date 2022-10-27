/*

 The most intuitive way to solve this is to move around the images, 
 find overlap for every move, then find maximum of overlap for every move
 
 We can move img2 from bottom right corner to top left corner,
 find overlap for a set of overlapping pixels, then find 
 maximum of overlap for all such sets of pixels
 
 This way we cover all translations (top, right, bottom, left),
 whether it be from img1 to img2 or img2 to img1
 
 TC: O(n^4)
 SC: O(1)
 
 */

class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        int maxOverlap = 0;
        
        for(int rowOffset = -n; rowOffset<=n; rowOffset++){
            for(int colOffset= -n; colOffset<=n; colOffset++){
                maxOverlap = Math.max(maxOverlap, getOverlap(img1, img2, rowOffset, colOffset, n));
            }
        }
        
        return maxOverlap;
    }
    
    private int getOverlap(int img1[][], int img2[][], int rowOffset, int colOffset, int n){
        int currOverlap = 0;
        
        for(int row1=0; row1<n; row1++){
            for(int col1=0; col1<n; col1++){
                int row2 = row1+rowOffset;
                int col2 = col1+colOffset;
                
                if(row1<0 || col1<0 || row2<0 || col2<0 || row1>=n || col1>=n || row2>=n || col2>=n) continue;
                
                if(img1[row1][col1]==1 && img2[row2][col2]==1) currOverlap++;
            }
        }
        
        return currOverlap;
    }
}