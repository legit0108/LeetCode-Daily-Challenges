// TC : O(len)
// SC : O(1)

class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length()!=t.length()) return false;
        
        int len = s.length();
        int map[] = new int[26];
        
        for(int idx=0;idx<len;idx++){
            map[s.charAt(idx)-'a']++;
            map[t.charAt(idx)-'a']--;
        }
        
        for(int idx=0;idx<26;idx++){
            if(map[idx]!=0) return false;
        }
        
        return true;
    }
}