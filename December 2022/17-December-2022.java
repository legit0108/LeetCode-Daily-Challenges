// Solution - 1 : Stack

// TC : O(tokens.length)
// SC : O(tokens.length)

class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack();
        
        for(String token : tokens){
            char ch = token.charAt(0);
            int len = token.length();
            
            if(len==1 && !(ch>='0' && ch<='9')){
                int val2 = stack.pop();
                int val1 = stack.pop();
                char operator = ch;
                
                int val = eval(val1, val2, operator);
                
                stack.push(val);
            }else{
                int val = Integer.parseInt(token);
                
                stack.push(val);
            }
        }
        
        return stack.pop();
    }
    
    private int eval(int val1, int val2, char operator){
        if(operator=='+') return val1+val2;
        else if(operator=='-') return val1-val2;
        else if(operator=='*') return val1*val2;
        else return val1/val2;
    }
}

// Solution - 2 : Recursion

// TC : O(tokens.length)
// SC : O(1) if recursive stack space ignored

class Solution {
    private int idx;
    
    public int evalRPN(String[] tokens) {
        idx = tokens.length-1;
        
        return eval(tokens);
    }
    
    private int eval(String tokens[]){
        String token = tokens[idx];
        char ch = token.charAt(0);
        int len = token.length();
        
        if(len==1 && !(ch>='0' && ch<='9')){
            char operator = ch;
            
            idx--;
            int val2 = eval(tokens);
            
            idx--;
            int val1 = eval(tokens);
            
            int val = calc(val1, val2, operator);
            
            return val;
        }else return Integer.parseInt(token);
    }
    
    private int calc(int val1, int val2, char operator){
        if(operator=='+') return val1+val2;
        else if(operator=='-') return val1-val2;
        else if(operator=='*') return val1*val2;
        else return val1/val2;
    }
}

// Solution - 3 : Recursion without global variable

// TC : O(tokens.length)
// SC : O(1) if recursive stack space ignored

class Solution {
    public int evalRPN(String[] tokens) {
        int idx[] = new int[]{tokens.length-1};
        
        return eval(tokens, idx);
    }
    
    private int eval(String tokens[], int idx[]){
        String token = tokens[idx[0]];
        char ch = token.charAt(0);
        int len = token.length();
        
        if(len==1 && !(ch>='0' && ch<='9')){
            char operator = ch;
            
            idx[0]--;
            int val2 = eval(tokens, idx);
            
            idx[0]--;
            int val1 = eval(tokens, idx);
            
            int val = calc(val1, val2, operator);
            
            return val;
        }else return Integer.parseInt(token);
    }
    
    private int calc(int val1, int val2, char operator){
        if(operator=='+') return val1+val2;
        else if(operator=='-') return val1-val2;
        else if(operator=='*') return val1*val2;
        else return val1/val2;
    }
}