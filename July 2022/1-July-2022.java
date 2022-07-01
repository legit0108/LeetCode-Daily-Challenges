// TC : O(lenlog(len))
// SC : O(1)
// Sort boxTypes on basis of number of units per box
// greedily pick boxes with bigger value of number of units per box

class Solution {
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes,(box1,box2)->(Integer.compare(box1[1],box2[1])));
        int maxUnits = 0;
        int len = boxTypes.length;
        
        for(int idx=len-1;idx>=0;idx--){
            int size = boxTypes[idx][0];
            int units = boxTypes[idx][1];
            
            int canTake = Math.min(truckSize,size);
            maxUnits+=canTake*units;
            
            truckSize-=canTake;
        }
        
        return maxUnits;
    }
}