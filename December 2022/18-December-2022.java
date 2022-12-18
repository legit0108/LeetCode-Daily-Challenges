// Solution - 1 : Monotonic stack

// TC : O(len)
// SC : O(len)

class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> stack = new Stack();
        int len = temperatures.length;
        int ans[] = new int[len];
        
        for(int idx=len-1; idx>=0; idx--){
            int temperature = temperatures[idx];
            
            while(stack.size()>0 && temperature>=temperatures[stack.peek()]) stack.pop();
            
            if(stack.size()>0) ans[idx] = stack.peek()-idx;
            else ans[idx] = 0;
            
            stack.push(idx);
        }
        
        return ans;
    }
}

// Solution - 2 : Optimized space

// TC : O(len) (amortized)
// SC : O(1) ignoring output space

class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int len = temperatures.length;
        int ans[] = new int[len];
        
        for(int idx1=len-1; idx1>=0; idx1--){
            int temperature = temperatures[idx1];
            int idx2 = idx1+1;
            
            while(idx2<len && temperature>=temperatures[idx2]){
                if(ans[idx2]==0) idx2 = len;
                else idx2 = idx2+ans[idx2];
            }
            
            if(idx2!=len) ans[idx1] = idx2-idx1;
            else ans[idx1] = 0;
        }
        
        return ans;
    }
}