// TC: O(log(n))
// SC: O(1)

class Solution {
    public boolean isUgly(int n) {
        if(n<=0) return false;
        
        int divisors[] = {2,3,5};
        
        for(int divisor : divisors){
            while(n%divisor==0) n/=divisor;
        }
        
        return n==1;
    }
}