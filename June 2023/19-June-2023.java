class Solution {
    public int largestAltitude(int[] gain) {
        int largestAlt = 0;
        int sum = 0;
        
        for(int netGain: gain){
            sum+=netGain;
            largestAlt = Math.max(largestAlt, sum);
        }
        
        return largestAlt;
    }
}