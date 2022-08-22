/*

Doing by loops or recursion is trivial
we can do it without iteration or recursion using bit masking
for a number to be a power of 4, it should be greater than zero
and power of 2, if we look at powers of 4 : 

num | binary
4^3 = 1000000
4^2 = 0010000
4^1 = 0000100
4^0 = 0000001

If we have a mask of alternating bits : 1010101, we can always capture
the only bit present in powers of 4

TC : O(1)
SC : O(1)

*/

class Solution {
    public boolean isPowerOfFour(int n) {
        if(n<=0||(n&(n-1))!=0) return false; // check if number is greater than 0 and power of 2
        
        int mask = 0b01010101010101010101010101010101; // mask to capture only set bit
        
        if((n&mask)!=0) return true;
        return false;
    }
}