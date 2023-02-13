// TC: O(1)
// SC: O(1)

class Solution {
    public int countOdds(int low, int high) {
        int range = high-low+1;
        int count = 0;
        
        if(low%2!=0 && high%2!=0) count=range/2+1; // [odd, ...., odd] (range is odd)
        else count = range/2; // [odd, ...., even] (range is even), [even, ...., odd] (range is even), [even, ...., even] (range is odd), but count remains same
        
        return count;
    }
}