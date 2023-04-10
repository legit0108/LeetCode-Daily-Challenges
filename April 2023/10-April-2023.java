// Standard stack implementation

// TC: O(s.length())
// SC: O(s.length())

class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack();
        
        for(int index=0; index<s.length(); index++){
            char bracket = s.charAt(index);
            
            if(isOpeningBracket(bracket)) stack.push(bracket);
            else{
                if(top(stack)!=matchingOpeningBracket(bracket)) return false;
            }
        }
        
        if(stack.size()==0) return true;
        else return false;
    }
    
    private boolean isOpeningBracket(char bracket){ // returns true if bracket is an opening bracket
        if(bracket=='(' || bracket=='{' || bracket=='[') return true;
        else return false;
    }
    
    private char matchingOpeningBracket(char bracket){ // returns matching opening bracket corresponding to closing bracket
        if(bracket==')') return '(';
        else if(bracket=='}') return '{';
        else return '[';
    }
    
    private char top(Stack<Character> stack){ // returns top of stack
        if(stack.size()==0) return '!';
        else return stack.pop();
    }
}


/* 
 Follow up: How would you solve the same problem given brackets of only one type?

 -> We notice that in this case, size of stack either increases by one or decreases by one
 -> Also, we do not need to store the information about type of bracket since there is only one type of bracket
 -> So we simulate stack using an integer variable
 -> Since there is only one type of bracket, just maintaining count of opening brackets is enough
 
 TC: O(s.length())
 SC: O(1)
*/

class Solution {
    public boolean isValid(String s) {
        int openingBracketCount = 0;
        
        for(int index=0; index<s.length(); index++){
            char bracket = s.charAt(index);
            
            if(bracket=='(') openingBracketCount++;
            else{
                if(openingBracketCount<=0) return false;
                else openingBracketCount--;
            }
        }
        
        if(openingBracketCount==0) return true;
        else return false;
    }
}