// Solution - 1 : Find out for how many subarrays current value is the minimum value using next smaller element to left, right

// TC : O(len)
// SC : O(len)


class Solution {
    public int sumSubarrayMins(int[] arr) {
        int len = arr.length;
        
        int nextSmallerToLeft[] = getNextSmallerToLeft(arr, len);
        int nextSmallerToRight[] = getNextSmallerToRight(arr, len);
        
        long sum = 0;
        long mod = (long)1e9+7;
        
        for(int idx=0; idx<len; idx++){
            int start = nextSmallerToLeft[idx]+1;
            int end = nextSmallerToRight[idx]-1;
            
            long count = (idx-start+1)*(end-idx+1);
            long weight = ((count*((long)arr[idx]))+mod)%mod;
            
            sum = ((sum+weight)+mod)%mod;
        }
        
        int ans = (int)(sum%mod);
        if(ans<0) ans+=(int)mod;
        
        return ans;
    }
    
    private int[] getNextSmallerToLeft(int[] arr, int len){
        Stack<Integer> stack = new Stack();
        int nextSmallerToLeft[] = new int[len];
        
        for(int idx=0; idx<len; idx++){
            int val = arr[idx];
            
            while(stack.size()>0 && val<=arr[stack.peek()]) stack.pop();
            
            if(stack.size()==0) nextSmallerToLeft[idx] = -1;
            else nextSmallerToLeft[idx] = stack.peek();
            
            stack.push(idx);
        }
        
        return nextSmallerToLeft;
    }
    
    private int[] getNextSmallerToRight(int[] arr, int len){
        Stack<Integer> stack = new Stack();
        int nextSmallerToRight[] = new int[len];
        
        for(int idx=len-1; idx>=0; idx--){
            int val = arr[idx];
            
            while(stack.size()>0 && val<arr[stack.peek()]) stack.pop();
            
            if(stack.size()==0) nextSmallerToRight[idx] = len;
            else nextSmallerToRight[idx] = stack.peek();
            
            stack.push(idx);
        }
        
        return nextSmallerToRight;
    }
}

// Solution - 2 : Same as first solution but one pass
// 1) and 2) together deal with duplicates, in the same way duplicates are dealt with in first solution (only once)

// TC : O(len)
// SC : O(len)


class Solution {
    public int sumSubarrayMins(int[] arr) {
        int len = arr.length;
        long sum = 0;
        long mod = (long)1e9+7;
        
        Stack<Integer> stack = new Stack();
        stack.push(-1);
        
        for(int idx=0; idx<=len; idx++){
            int val = (idx<len?arr[idx]:0);
            
            while(stack.peek()!=-1 && arr[stack.peek()]>val){ // 1) note that we pop when top of stack>val
                int index = stack.pop();
                int nextSmallerToRight = idx;
                int nextSmallerToLeft = stack.peek();
                
                int start = nextSmallerToLeft+1;
                int end = nextSmallerToRight-1;
                
                long count = (((index-start+1)*(end-index+1))+mod)%mod;
                long weight = ((count*((long)arr[index]))+mod)%mod;
                sum = ((sum+weight)+mod)%mod;
            }
            
            stack.push(idx); // 2) note that we push when top of stack<=val
        }
        
        int ans = (int)(sum%mod);
        if(ans<0) ans+=(int)mod;
        return ans;
    }
}