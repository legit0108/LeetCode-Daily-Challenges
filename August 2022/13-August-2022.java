// TC : O(len*totalWords*wordLen*wordLen)
// SC : O(totalWords*wordLen)

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> indices = new ArrayList();
        int len = s.length();
        int totalWords = words.length;
        int wordLen = words[0].length();
        HashMap<String,Integer> wordsMap = new HashMap();
        
        for(String word : words) wordsMap.put(word,wordsMap.getOrDefault(word,0)+1);
        
        mainLoop : 
        for(int start=0 , end=totalWords*wordLen-1;end<len;start++,end++){ // O(len)
            HashMap<String,Integer> currMap = new HashMap();
            StringBuilder str = new StringBuilder();
            
            for(int idx=start;idx<=end;idx++){ // O(totalWords*wordLen)
                char ch = s.charAt(idx);
                str.append(ch);
                
                if(str.length()==wordLen){ // O(wordLen)
                    String currStr = str.toString();
                    currMap.put(currStr,currMap.getOrDefault(currStr,0)+1);
                    
                    int currMapFreq = currMap.get(currStr);
                    int wordsMapFreq = wordsMap.getOrDefault(currStr,0);
                    
                    if(wordsMapFreq<currMapFreq) continue mainLoop;
                    
                    str = new StringBuilder();
                }
            }
            
            if(equal(currMap,wordsMap)) indices.add(start);
        }
        
        return indices;
    }
    
    private boolean equal(HashMap<String,Integer> currMap,HashMap<String,Integer> wordsMap){
        for(String word : currMap.keySet()){
            int currMapFreq = currMap.get(word);
            int wordsMapFreq = wordsMap.getOrDefault(word,0);
            
            if(currMapFreq<wordsMapFreq) return false;
        }
        
        return true;
    }
}