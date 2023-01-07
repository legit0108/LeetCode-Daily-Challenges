/*
 Solution-1, Brute force
 Start from every gas station and check if the circular tour is possible or not
 
 TC: O(stations^2)
 SC: O(1)
*/

/*
 Solution-2, Greedy
 -> Maintain surplus and deficit variables to solve the problem in O(stations) time
 -> surplus = how much extra gas I have when I reach a station
 -> deficit = how much extra gas I need to reach a station
 -> If we cannot reach a station 'B' when starting from 'A', we cannot reach 'B' if we start from any station from 'A' to 'B', including 'B'
 -> Since the solution is guaranteed to be unique, we only check the first starting point we find, if this is not valid, then it is not possible to complete the circular tour

 TC: O(stations)
 SC: O(1)
*/

class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int stations = gas.length;
        int surplus = 0;
        int deficit = 0;
        int start = 0;
        
        for(int idx=0; idx<stations; idx++){
            int availableGas = gas[idx];
            int price = cost[idx];
            
            if(surplus+availableGas < price){
                start = idx+1;
                deficit+=price-(surplus+availableGas);
                surplus = 0;
            }else{
                surplus+=availableGas;
                surplus-=price;
            }
        }
        
        if(surplus>=deficit) return start;
        return -1;
    }
}