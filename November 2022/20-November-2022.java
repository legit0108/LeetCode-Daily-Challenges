// Solution-1: Using pair

// TC: O(len)
// SC: O(len)

class Solution {
    public int calculate(String s) {
        int len = s.length();
        int sign = 1;
        int result = 0;
        int num = 0;
        Stack<Pair> stack = new Stack();
        
        for(int idx=0; idx<len; idx++){
            char ch = s.charAt(idx);
            
            if(isDigit(ch)){
                while(idx<len){
                    ch = s.charAt(idx);
                    
                    if(isDigit(ch)){
                        num = num*10+(ch-'0');
                    }else break;
                    
                    idx++;
                }
                
                idx--;
                stack.push(new Pair(false, sign*num));
                num = 0;
            }
            else if(ch=='('){
                stack.push(new Pair(true, sign));
                sign = 1;
            }else if(ch==')'){
                int val = 0;
                
                while(!stack.peek().isSign) val+=stack.pop().val;
                val=stack.pop().val*val;
                
                stack.push(new Pair(false, val));
                sign = 1;
            }else if(ch=='+' || ch=='-'){
                if(ch=='+') sign = 1;
                else sign = -1;
            }
        }
        
        while(stack.size()>0) result+=stack.pop().val;
        return result;
    }
    
    private boolean isDigit(char ch){
        if(ch>='0' && ch<='9') return true;
        else return false;
    }
    
    private class Pair{
        private boolean isSign;
        private int val;
        
        private Pair(boolean isSign, int val){
            this.isSign = isSign;
            this.val = val;
        }
        
        private Pair(){
            
        }
    }
}

// Solution-2: Using Stack<Integer>

// TC: O(len)
// SC: O(len)

class Solution {
    public int calculate(String s) {
        int len = s.length();
        int sign = 1;
        int result = 0;
        Stack<Integer> stack = new Stack();
        
        for(int idx=0; idx<len; idx++){
            char ch = s.charAt(idx);
            
            if(isDigit(ch)){
                int num = 0;
                 
                while(idx<len){
                    ch = s.charAt(idx);
                    
                    if(isDigit(ch)) num = num*10+(ch-'0');
                    else break;
                    
                    idx++;
                }
                
                idx--;
                result+=sign*num;
            }else if(ch=='('){
                stack.push(result);
                stack.push(sign);
                
                result = 0;
                sign = 1;
            }else if(ch==')'){
                result=result*stack.pop();
                result+=stack.pop();
                
                sign = 1;
            }else if(ch=='+' || ch=='-'){
                if(ch=='+') sign = 1;
                else sign = -1;
            }
        }
        
        return result;
    }
    
    private boolean isDigit(char ch){
        if(ch>='0' && ch<='9') return true;
        else return false;
    }
}