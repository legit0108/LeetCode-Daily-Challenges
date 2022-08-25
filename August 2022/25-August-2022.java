// TC : O(ransomNoteLen + magazineLen)
// SC : O(26) / O(1)

class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        int ransomNoteLen = ransomNote.length();
        int magazineLen = magazine.length();
        int map[] = new int[26];
        
        for(int idx=0;idx<magazineLen;idx++){
            char ch = magazine.charAt(idx);
            map[ch-'a']++;
        }
        
        for(int idx=0;idx<ransomNoteLen;idx++){
            char ch = ransomNote.charAt(idx);
            if(map[ch-'a']==0) return false;
            map[ch-'a']--;
        }
        
        return true;
    }
}