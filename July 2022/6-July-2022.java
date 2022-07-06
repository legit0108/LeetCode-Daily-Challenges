// TC : O(n)
// SC : O(1)

// fib(n) = fib(n-1) + fib(n-2)

class Solution {
    public int fib(int n) {
        if(n==0||n==1) return n;
        
        int last = 1;
        int secondLast = 0;
        
        for(int num=2;num<=n;num++){
            int curr = last+secondLast;
            secondLast = last;
            last = curr;
        }
        
        return last;
    }
}