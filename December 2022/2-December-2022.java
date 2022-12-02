// To be close strings, both strings should have same set of characters and their frequencies should be anagrams of each other 

// Solution-1

// TC: O(len)
// SC: O(1), all maps have constant size

class Solution {
    public boolean closeStrings(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        
        if(len1!=len2) return false;
        
        int len = len1;
        
        HashMap<Character, Integer> map1 = new HashMap();
        HashMap<Character, Integer> map2 = new HashMap();
        
        for(int idx=0; idx<len; idx++){
            char ch1 = word1.charAt(idx);
            char ch2 = word2.charAt(idx);
            
            map1.put(ch1, map1.getOrDefault(ch1, 0)+1);
            map2.put(ch2, map2.getOrDefault(ch2, 0)+1);
        }
        
        HashMap<Integer, Integer> map = new HashMap();
        
        for(char ch : map1.keySet()){
            if(!map2.containsKey(ch)) return false;
            
            int freq = map1.get(ch);
            map.put(freq, map.getOrDefault(freq, 0)+1);
        }
        
        for(char ch : map2.keySet()){
            if(!map1.containsKey(ch)) return false;
            
            int freq = map2.get(ch);
            map.put(freq, map.getOrDefault(freq, 0)-1);
            
            if(map.get(freq) == 0) map.remove(freq);
        }
        
        return map.size()==0;
    }
}

// Solution-2

// TC: O(len)
// SC: O(1)

class Solution {
    public boolean closeStrings(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        
        if(len1!=len2) return false;
        
        int len = len1;
        int size = 26;
        
        int map1[] = new int[size];
        int map2[] = new int[size];
        
        for(int idx=0; idx<len; idx++){
            char ch1 = word1.charAt(idx);
            char ch2 = word2.charAt(idx);
            
            map1[ch1-'a']++;
            map2[ch2-'a']++;
        }
        
        for(int idx=0; idx<size; idx++){
            int freq1 = map1[idx];
            int freq2 = map2[idx];
            
            if(freq1==0 && freq2==0) continue;
            if(freq1==0 || freq2==0) return false;
        }
        
        Arrays.sort(map1);
        Arrays.sort(map2);
        
        for(int idx=0; idx<size; idx++){
            if(map1[idx]!=map2[idx]) return false;
        }
        
        return true;
    }
}