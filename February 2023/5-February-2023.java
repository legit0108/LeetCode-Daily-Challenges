// Sliding window

// TC: O(pLen+sLen)
// SC: O(1) / O(26)

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList();
        int[] map = new int[26];
        int pLen = p.length();
        
        for(int idx=0; idx<pLen; idx++){
            map[p.charAt(idx)-'a']++;
        }
        
        int start = 0;
        int end = 0;
        int sLen = s.length();
        int count = pLen;
        
        while(end<sLen){
            int pos = s.charAt(end)-'a';
            if(map[pos]>0) count--;
            map[pos]--;
            
            if(count==0) list.add(start);
            
            if(end-start+1>=pLen){
                pos = s.charAt(start)-'a';
                map[pos]++;
                if(map[pos]>0) count++;
                
                start++;
            }
            
            end++;
        }
        
        return list;
    }
}