// TC : O(words1.length + words2.length)
// SC : O(1) excluding output space

class Solution {
    public List<String> wordSubsets(String[] words1, String[] words2) {
        List<String> list = new ArrayList();
        int words2Map[] = new int[26];
        
        for(String word : words2){
            int len = word.length();
            int map[] = new int[26];
            
            for(int idx=0;idx<len;idx++){
                char ch = word.charAt(idx);
                map[ch-'a']++;
                words2Map[ch-'a']=Math.max(words2Map[ch-'a'],map[ch-'a']);
            }
        }
        
        for(String word:words1){
            int map[] = new int[26];
            int len = word.length();
            boolean isValid = true;
            
            for(int idx=0;idx<len;idx++){
                char ch = word.charAt(idx);
                map[ch-'a']++;
            }
            
            for(int idx=0;idx<26;idx++){
                if(map[idx]<words2Map[idx]){
                    isValid = false;
                    break;
                } 
            }
            
            if(isValid) list.add(word);
        }
        
        return list;
    }
}