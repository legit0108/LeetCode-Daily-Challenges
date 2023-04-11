// We use a stack to keep track of characters encountered so far
// We pop from our stack when we encounter a '*'
// Simulate stack using StringBuilder

// TC: O(s.length())
// SC: O(s.length())

class Solution {
    public String removeStars(String s) {
        StringBuilder str = new StringBuilder();
        
        for(int index=0; index<s.length(); index++){
            char ch = s.charAt(index);
            
            if(ch=='*') str.deleteCharAt(str.length()-1);
            else str.append(ch);
        }
        
        return str.toString();
    }
}


// Follow up: Can you do it in constant space?
// We use input string as a stack

// TC: O(s.length())
// SC: O(1)

class Solution {
    public String removeStars(String s) {
        char[] str = s.toCharArray();
        int top = -1;
        
        for(int index=0; index<str.length; index++){
            char ch = str[index];
            
            if(ch=='*'){
                top--;
            }else{
                top++;
                str[top] = ch;
            }
        }
        
        // Final string will be stored in str from [0.....top]
        return new String(str).substring(0, top+1);
    }
}