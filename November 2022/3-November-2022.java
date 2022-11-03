// Solution - 1

// TC: O(words.length)
// SC: O(1)

class Solution {
    public int longestPalindrome(String[] words) {
        int map[][] = new int[26][26];
        int maxLen = 0;
        
        for(String word : words){
           char firstCh = word.charAt(0);
           char secondCh = word.charAt(1);
           
           if(map[secondCh-'a'][firstCh-'a']>0){
               maxLen+=4;
               map[secondCh-'a'][firstCh-'a']--;
           }else{
               map[firstCh-'a'][secondCh-'a']++;
           } 
        }
        
        for(int idx=0; idx<26; idx++){
            if(map[idx][idx]>0){
                maxLen+=2;
                break;
            }
        }
        
        return maxLen;
    }
}

// Solution-2 : One pass 

// TC: O(words.length)
// SC: O(1)

class Solution {
    public int longestPalindrome(String[] words) {
        int map[][] = new int[26][26];
        int maxLen = 0;
        int middleStrs = 0;
        
        for(String word : words){
           char firstCh = word.charAt(0);
           char secondCh = word.charAt(1);
           
           if(map[secondCh-'a'][firstCh-'a']>0){
               maxLen+=4;
               map[secondCh-'a'][firstCh-'a']--;
               
               if(firstCh==secondCh) middleStrs--;
           }else{
               map[firstCh-'a'][secondCh-'a']++;
               
               if(firstCh==secondCh) middleStrs++;
           } 
        }
        
        if(middleStrs>0) maxLen+=2;
        return maxLen;
    }
}