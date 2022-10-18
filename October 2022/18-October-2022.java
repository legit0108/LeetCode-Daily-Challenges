// TC: O(n*len(currStr))
// SC: O(currStr)

class Solution {
    public String countAndSay(int n) {
        StringBuilder currStr = new StringBuilder("1");
        
        for(int term=1; term<n; term++){
            int idx = 0;
            StringBuilder str = new StringBuilder();
            int len = currStr.length();
            
            while(idx<len){
               int count = 1;
                
               while(idx+1<len && currStr.charAt(idx)==currStr.charAt(idx+1)){
                   count++;
                   idx++;
               }
               
               str.append(count).append(currStr.charAt(idx));
               idx++; 
            }
            
            currStr = str;
        }
        
        return currStr.toString();
    }
}