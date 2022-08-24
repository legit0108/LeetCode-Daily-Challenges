/*

Doing without loop/recursion,
check if highest power of 3 which fits in integer range % n == 0
this method will work for all prime numbers 2,3,5,11 etc
but it won't work for non prime numbers, example 4
highest power of 4 % 2 will be 0, but 2 is not a power of 4

TC : O(1)
SC : O(1)

*/

class Solution {
    public boolean isPowerOfThree(int n) {
        if(n<=0) return false;
        
        int highestPowOfThree = 1162261467;
        if(highestPowOfThree%n==0) return true;
        return false;
    }
}