/*

Ideas: 

 To get min deviation, we can either increase the min element or decrease the max element
 To always access the max element, we can use a heap
 To decrease our available options, we can make all numbers as maximum as possible initially
 Now we are only left with one operation -> Reduce the max
 So we keep doing it till we cannot do it anymore (max becomes odd)
 
TC: O(len*log(len)*log(num)), where len = nums.length, for all num in nums
SC: O(len)

*/

class Solution {
    public int minimumDeviation(int[] nums) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
        int minElem = Integer.MAX_VALUE;
        
        for(int num: nums){
            if(num%2!=0) num*=2; // Make the number as max as possible (we can multiply by 2 in case of odd numbers)
            
            maxHeap.add(num);
            minElem = Math.min(minElem, num);
        }
        
        int minDeviation = Integer.MAX_VALUE;
        
        // Only operation left -> Decrease the max element, we can do this by dividing each number by 2 since all numbers are even in our heap
        
        while(maxHeap.peek()%2==0){
            int top = maxHeap.remove();
            
            minDeviation = Math.min(minDeviation, top-minElem);
            
            top/=2;
            minElem = Math.min(minElem, top);
            maxHeap.add(top);
        }
        
        /* 
         We cannot reduce the top element any further (since its odd)
         What about increasing the min element to minimize deviation?
         If it is even, we can only divide it by 2, so we cannot increase it 
         If it is odd, we have already accounted for this since we pushed only even elements in the heap
        */
        
        minDeviation = Math.min(minDeviation, maxHeap.peek()-minElem);
        
        return minDeviation;
    }
}