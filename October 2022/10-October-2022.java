// Find first idx such that character at that idx is not 'a', replace this idx with 'a'
// deal with some edge cases

// TC: O(len)
// SC: O(len) 

class Solution {
    public String breakPalindrome(String palindrome) {
        char str[] = palindrome.toCharArray();
        int len = str.length;
        int firstValidIdx = -1;
        
        for(int idx=0; idx<len; idx++){
            if(str[idx]!='a'){
                if(len%2!=0 && idx==len/2) continue;
                else{
                    firstValidIdx = idx;
                    break;
                }
            }
        }
        
        if(firstValidIdx==-1){
            if(len==1) return "";
            else str[len-1] = 'b';
        }else str[firstValidIdx] = 'a';
        
        return new String(str);
    }
}