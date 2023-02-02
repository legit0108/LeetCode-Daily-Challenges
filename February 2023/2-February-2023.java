// Compare adjacent words

// TC: O(words.length*word.length())
// SC: O(1)

class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        int[] map = new int[26];
        
        for(int idx=0; idx<26; idx++){
            char ch = order.charAt(idx);
            map[ch-'a'] = idx;
        }
        
        int len = words.length;
        
        for(int idx=0; idx<len-1; idx++){
            if(isBigger(words[idx], words[idx+1], map)) return false;
        }
        
        return true;
    }
    
    private boolean isBigger(String word1, String word2, int[] map){ // returns true if word1 is lexicographically bigger than word2
        int len1 = word1.length();
        int len2 = word2.length();
            
        int len = Math.min(len1, len2);
        boolean flag = false;
            
        for(int idx=0; idx<len; idx++){
            char ch1 = word1.charAt(idx);
            char ch2 = word2.charAt(idx);
                
            if(ch1!=ch2){
                if(map[ch1-'a']>map[ch2-'a']) return true;
                else{
                   flag = true;
                   break;
                }
            }
        }
            
        if(!flag && len1>len2) return true;
        else return false;
    }
}