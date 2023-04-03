// Solution-0: For every person, pair that person up with some other person (if possible)
// TC: O(n^2), n = people.length


/* 
Solution-1: Sorting + Two Pointers
 Ideas: 
 -> If the person with least weight cannot be paired up with the one with maximum weight,
    the one with maximum weight sits alone (cannot be paired up with anyone else)
 -> Else we pair both of them up, it is not optimal to pair up the person with least weight
    with some other person
   
 TC: O(nlogn)
 SC: O(n)
*/

class Solution {
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int start = 0;
        int end = people.length-1;
        int minBoats = 0;
        
        while(start<=end){
            int sumOfWeight = people[start]+people[end];
                
            if(sumOfWeight<=limit){
                start++;
                end--;
            }else end--;
                
            minBoats++;
        }
        
        return minBoats;
    }
}



// Solution-2: Optimize Solution-1 using count/bucket sort

// TC: O(n + limit)
// SC: O(limit)

class Solution {
    public int numRescueBoats(int[] people, int limit) {
        int[] count = new int[limit+1];
        for(int weight: people) count[weight]++;
        
        int start = 0;
        int end = limit;
        int minBoats = 0;
        
        while(start<=end){
            while(start<=end && count[start]<=0) start++;
            while(start<=end && count[end]<=0) end--;
            
            if(start<=end){
                int sumOfWeights = start+end;
                    
                if(sumOfWeights<=limit){
                    count[start]--;
                    count[end]--;
                }else{ 
                    count[end]--;
                }
                    
                minBoats++;
            }
        }
        
        return minBoats;
    }
}


/*
 Follow ups:

 If >2 people can sit in one boat:
  -> There is no limit on size of the boat: LeetCode 1986
  -> There is some fixed limit N such that N>2: Bin Packing problem (NP Hard)
 */