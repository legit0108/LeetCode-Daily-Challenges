// Sliding window

// TC: O(len_s + len_t)
// SC: O(26) / O(1)

class Solution {
    public String minWindow(String s, String t) {
        HashMap<Character, Integer> map_t = new HashMap();
        int len_t = t.length();
        
        for(int idx=0; idx<len_t; idx++){
            char ch = t.charAt(idx);
            map_t.put(ch, map_t.getOrDefault(ch, 0)+1);
        }
        
        HashMap<Character, Integer> map_s = new HashMap();
        int len_s = s.length();
        int start = -1;
        int end = -1;
        int minLen = Integer.MAX_VALUE;
        int startIdx = -1;
        int count = 0;
        
        while(true){
            boolean flag1 = false;
            boolean flag2 = false;
            
            while(end+1<len_s && count<len_t){
                flag1 = true;
                end++;
                
                char ch = s.charAt(end);
                map_s.put(ch, map_s.getOrDefault(ch, 0)+1);
                
                if(map_s.getOrDefault(ch, 0)<=map_t.getOrDefault(ch, 0)) count++;
            }
            
            while(count==len_t){
                flag2 = true;
                int currLen = end-start;
                
                if(currLen<minLen){
                    minLen = currLen;
                    startIdx = start+1;
                }
                
                start++;
                char ch = s.charAt(start);
                map_s.put(ch, map_s.get(ch)-1);
                
                if(map_s.getOrDefault(ch, 0)<map_t.getOrDefault(ch, 0)) count--;
            }
            
            if(flag1==false && flag2==false) break;
        }
        
        if(minLen==Integer.MAX_VALUE) return "";
        return s.substring(startIdx, startIdx+minLen);
    }
}