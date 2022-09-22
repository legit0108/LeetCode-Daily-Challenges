// TC : O(len)
// SC : O(len)

class Solution {
    public String reverseWords(String s) {
        char str[] = s.toCharArray();
        int start = 0;
        int end = 0;
        int len = str.length;
        
        while(end < len){
            while(end < len && str[end]!=' ') end++;
            reverse(str, start, end-1);
            
            end++;
            start = end;
        }
        
        return new String(str);
    }
    
    private void reverse(char str[], int start, int end){
        while(start < end){
            swap(str, start, end);
            start++;
            end--;
        }
    }
    
    private void swap(char str[], int idx1, int idx2){
        char temp = str[idx1];
        str[idx1] = str[idx2];
        str[idx2] = temp;
    }
}