// Note: You must write an algorithm that uses only constant extra space.

// TC: O(len)
// SC: O(1)

class Solution {
    public int compress(char[] chars) {
        int strIdx = 0;
        int idx = 0;
        int len = chars.length;
        
        while(idx<len){
            int count = 1;
            int factor = 1;
            
            // count contiguous same characters
            while(idx+1<len && chars[idx]==chars[idx+1]){
                idx++;
                count++;
                
                if(count==factor*10) factor*=10;
            }
            
            chars[strIdx] = chars[idx];
            strIdx++;
            
            // if count>1, append the count as well (iterate over number in O(1) space) 
            while(count>1 && factor>0){
                int digit = (count/factor)%10;
                chars[strIdx] = (char)('0'+digit);
                
                factor/=10;
                strIdx++;
            }
            
            idx++;
        }
        
        return strIdx;
    }
}