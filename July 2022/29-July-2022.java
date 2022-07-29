// TC : O(len*pattern.length())
// SC : O(1) excluding output space

class Solution {
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> list = new ArrayList();
        int len = words.length;
        
        for(int idx=0;idx<len;idx++){
            if(match(pattern,words[idx])) list.add(words[idx]);
        }
        
        return list;
    }
    
    private boolean match(String str1,String str2){
        HashMap<Character,Character> map = new HashMap();
        HashSet<Character> used = new HashSet();
        int len1 = str1.length();
        int len2 = str2.length();
        if(len1!=len2) return false;
        
        for(int idx=0;idx<len1;idx++){
            if(map.get(str1.charAt(idx))==null){
                if(used.contains(str2.charAt(idx))) return false;
                map.put(str1.charAt(idx),str2.charAt(idx));
                used.add(str2.charAt(idx));
            }else{
                char mappedCh = map.get(str1.charAt(idx));
                if(mappedCh!=str2.charAt(idx)) return false;
            }
        }
        
        return true;
    }
}