// Solution-1: Using Stack

// TC: O(path.length())
// SC: O(path.length()) 

class Solution {
    public String simplifyPath(String path) {
        String[] arr = path.split("/");
        Stack<String> stack = new Stack();
        
        for(String str: arr){
            if(str.length()==0 || str.equals("/") || str.equals(".")) continue;
            else if(str.equals("..")){
                if(stack.size()>0) stack.pop();    
            }else stack.push(str);
        }
        
        StringBuilder canonicalPath = new StringBuilder("/");
        for(String str: stack) canonicalPath.append(str).append("/");
        if(canonicalPath.length()>1) canonicalPath.deleteCharAt(canonicalPath.length()-1);
        
        return canonicalPath.toString();
    }
}


// Solution-2: Use array as stack

// TC: O(path.length())
// SC: O(path.length()) 

class Solution {
    public String simplifyPath(String path) {
        String[] arr = path.split("/");
        int top = -1;
        
        for(String str: arr){
            if(str.length()==0 || str.equals("/") || str.equals(".")) continue;
            else if(str.equals("..")){
                if(top>-1){ 
                    arr[top] = null;
                    top--;
                }
            }else{
                top++;
                arr[top] = str;
            }
        }
        
        StringBuilder canonicalPath = new StringBuilder("/");
        for(int index=0; index<=top; index++) canonicalPath.append(arr[index]).append("/");
        if(canonicalPath.length()>1) canonicalPath.deleteCharAt(canonicalPath.length()-1);
        
        return canonicalPath.toString();
    }
}