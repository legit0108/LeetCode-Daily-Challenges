// Sliding window

// TC: O(len1+len2)
// SC: O(1) / O(26)

class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        
        if(len1>len2) return false;
        
        int[] map = new int[26];
        
        for(int idx=0; idx<len1; idx++){
            char ch = s1.charAt(idx);
            map[ch-'a']++;
        }
        
        int count = len1; // number of unequal characters in windows of size len1
            
        for(int idx=0; idx<len2; idx++){
            char ch = s2.charAt(idx);
            int pos = ch-'a';
            
            if(map[pos]>0) count--;
            map[pos]--;
            
            if(idx>=len1){
                ch = s2.charAt(idx-len1);
                pos = ch-'a';
                
                if(map[pos]>=0) count++;
                map[pos]++;
            }
            
            if(count==0) return true; // no unequal characters -> permutation of s1 found
        }
        
        return false;
    }
}