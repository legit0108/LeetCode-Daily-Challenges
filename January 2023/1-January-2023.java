// Solution-1: Using set of words

// TC: O(len*word.length()), since equals() runs in O(word.length()) time
// SC: O(len)

class Solution {
    public boolean wordPattern(String pattern, String s) {
        int len = pattern.length();
        String[] words = s.split(" ");
        
        if(len!=words.length) return false;
        
        HashSet<String> usedWords = new HashSet();
        HashMap<Character, String> map = new HashMap();
        
        for(int idx=0; idx<len; idx++){
            char ch = pattern.charAt(idx);
            String word = words[idx];
            
            if(map.containsKey(ch)){
                String mappedWord = map.get(ch);
                
                if(!mappedWord.equals(word)) return false;
            }else{
                if(usedWords.contains(word)) return false;
                
                map.put(ch, word);
                usedWords.add(word);
            }
        }
        
        return true;
    }
}

// Solution-2: Using set/array of characters

// TC: O(len)
// SC: O(len)

class Solution {
    public boolean wordPattern(String pattern, String s) {
        int len = pattern.length();
        String[] words = s.split(" ");
        
        if(len!=words.length) return false;
        
        boolean[] used = new boolean[26];
        HashMap<String, Character> map = new HashMap();
        
        for(int idx=0; idx<len; idx++){
            char ch = pattern.charAt(idx);
            String word = words[idx];
            
            if(map.containsKey(word)){
                char mappedCh = map.get(word);
                
                if(!(mappedCh == ch)) return false;
            }else{
                int pos = ch-'a';
                if(used[pos]) return false;
                
                map.put(word, ch);
                used[pos] = true;
            }
        }
        
        return true;
    }
}

// Solution-3: Using 1 map
// For bijection, word in words and character in pattern should point to same index

// TC: O(len)
// SC: O(len)

class Solution {
    public boolean wordPattern(String pattern, String s) {
        int len = pattern.length();
        String[] words = s.split(" ");
        
        if(len!=words.length) return false;
        
        HashMap map = new HashMap(); // map of objects to deal with both string and character
        
        for(int idx=0; idx<len; idx++){
            char ch = pattern.charAt(idx);
            String word = words[idx];
            
            // note that map.put() return previous value of key if present else null
            Integer idx1 = (Integer)map.put(ch, idx);
            Integer idx2 = (Integer)map.put(word, idx);
            
            if(idx1==null || idx2==null){
                if(!(idx1==null && idx2==null)) return false;
            }else{
                if(!idx1.equals(idx2)) return false;
            }
        }
        
        return true;
    }
}