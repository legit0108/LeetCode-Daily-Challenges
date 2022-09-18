// Method-1: Water trapped at a point is bound by min(maxToLeft, maxToRight)

// TC : O(len)
// SC : O(len)

class Solution {
    public int trap(int[] height) {
        int len = height.length;
        
        int maxToLeft[] = new int[len];
        maxToLeft[0] = height[0];
        for(int idx = 1;idx<len;idx++) maxToLeft[idx] = Math.max(maxToLeft[idx-1], height[idx]);
        
        int maxToRight[] = new int[len];
        maxToRight[len-1] = height[len-1];
        for(int idx = len-2;idx>=0;idx--) maxToRight[idx] = Math.max(maxToRight[idx+1], height[idx]);
        
        int trappedWater = 0;
        for(int idx = 0;idx<len;idx++){
            trappedWater+=Math.min(maxToLeft[idx], maxToRight[idx])-height[idx];
        }
        
        return trappedWater;
    }
}

// Method-2: Monotonic stack, one pass

// TC : O(len)
// SC : O(len)

class Solution {
    public int trap(int[] height) {
        int len = height.length;
        Stack<Integer> stack = new Stack();
        int trappedWater = 0;
        
        for(int idx = 0;idx<len;idx++){
            while(stack.size()>0 && height[idx]>height[stack.peek()]){
                int currIdx = stack.pop();
                
                if(stack.size()>0){
                    int maxIdx = stack.peek();
                    int width = idx-maxIdx-1;
                    int bound = Math.min(height[idx], height[maxIdx]);

                    trappedWater+=width*(bound - height[currIdx]);
                }
            }
            
            stack.push(idx);
        }
        
        return trappedWater;
    }
}

// Method-3: Two pointers

// TC : O(len)
// SC : O(1)

class Solution {
    public int trap(int[] height) {
        int len = height.length;
        int maxToLeft = 0;
        int maxToRight = 0;
        int start = 0;
        int end = len-1;
        int trappedWater = 0;
        
        while(start<end){
            if(height[start]<height[end]){
                if(height[start]>maxToLeft) maxToLeft = height[start];
                else{
                    trappedWater+=maxToLeft-height[start];
                }
                
                start++;
            }else{
                if(height[end]>maxToRight) maxToRight = height[end];
                else{
                    trappedWater+=maxToRight-height[end];
                }
                
                end--;
            }
        }
        
        return trappedWater;
    }
}