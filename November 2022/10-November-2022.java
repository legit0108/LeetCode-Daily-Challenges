// Solution-1: StringBuilder

// TC: O(len)
// SC: O(len)

class Solution {
    public String removeDuplicates(String s) {
        StringBuilder str = new StringBuilder();
        int len = s.length();
        
        for(int idx=0; idx<len; idx++){
            char ch = s.charAt(idx);
            int currLen = str.length();
            
            if(currLen>0 && (str.charAt(currLen-1)==ch)){
                str.deleteCharAt(currLen-1);
            }else str.append(ch);
        }
        
        return str.toString();
    }
}

// Solution-2: Array

// TC: O(len)
// SC: O(len)

class Solution {
    public String removeDuplicates(String s) {
        char str[] = s.toCharArray();
        int top = -1;
        int len = str.length;
        
        for(int idx=0; idx<len; idx++){
            char ch = str[idx];
            
            if(top!=-1 && str[top]==ch){
                str[top]=' ';
                top--;
            }else{
                top++;
                str[top] = ch;
            }
        }
        
        return new String(str).substring(0, top+1);
    }
}