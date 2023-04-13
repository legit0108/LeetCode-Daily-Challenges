// Use stack to simulate operations of pushed and popped

// TC: O(len)
// SC: O(len), can be optimized to O(1) by using pushed as a stack

class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        ArrayDeque<Integer> stack = new ArrayDeque();
        int len = pushed.length;
        int poppedIndex = 0;
        
        for(int pushedIndex=0; pushedIndex<len; pushedIndex++){
            stack.push(pushed[pushedIndex]);
            
            while(stack.size()>0 && stack.peek()==popped[poppedIndex]){ // pop from stack till top of stack matches popped
                stack.pop();
                poppedIndex++;
            }
        }
        
        if(poppedIndex==len) return true; // return true if all elements of popped have been covered
        else return false;
    }
}