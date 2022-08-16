// TC : O(len)
// SC : O(26) / O(1)

class Solution {
    public int firstUniqChar(String s) {
        int len = s.length();
        int nonRepeatCharIdx = 0;
        int idx = 0;
        int map[] = new int[26];
        
        while(idx<len){
            char ch = s.charAt(idx);
            map[ch-'a']++;
            
            while(nonRepeatCharIdx<idx&&map[s.charAt(nonRepeatCharIdx)-'a']>1) nonRepeatCharIdx++;
            idx++;
        }
        
        if(map[s.charAt(nonRepeatCharIdx)-'a']>1) return -1;
        return nonRepeatCharIdx;
    }
}