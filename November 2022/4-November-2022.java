// TC: O(s.length())
// SC: O(s.length())

class Solution {
    public String reverseVowels(String s) {
        char str[] = s.toCharArray();
        int start = 0;
        int end = str.length-1;
        
        while(start<end){
            if(isVowel(str[start]) && isVowel(str[end])){
                swap(str, start, end);
                start++;
                end--;
            }else if(isVowel(str[start])) end--;
            else if(isVowel(str[end])) start++;
            else{
                start++;
                end--;
            }
        }
        
        return new String(str);
    }
    
    private boolean isVowel(char ch){
        if(ch>='A' && ch<='Z') ch = (char)(ch-('A'-'a')); 
            
        if(ch=='a' || ch=='e' || ch=='i' || ch=='o' || ch=='u') return true;
        else return false;
    }
    
    private void swap(char str[], int start, int end){
        char temp = str[start];
        str[start] = str[end];
        str[end] = temp;
    }
}