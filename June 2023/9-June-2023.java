// TC: O(logn)
// SC: O(1)

class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        int low = 0;
        int high = letters.length-1;
        int index = -1;
        
        while(low<=high){
            int mid = low + (high-low)/2;
            
            if(letters[mid]>target){
                index = mid;
                high = mid-1;
            }else low = mid+1;
        }
        
        if(index==-1) return letters[0];
        else return letters[index];
    }
}