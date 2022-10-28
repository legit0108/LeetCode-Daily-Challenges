// TC: O(strs.length*str.length())
// SC: O(strs.length*str.length())

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<HashMap<Character, Integer>, List<String>> map = new HashMap();
        
        for(String str: strs){
            HashMap<Character, Integer> freqMap = getMap(str);
            if(!map.containsKey(freqMap)) map.put(freqMap, new ArrayList<String>());
            map.get(freqMap).add(str);
        }
        
        List<List<String>> anagrams = new ArrayList();
        
        for(HashMap<Character, Integer> freqMap: map.keySet()){
            anagrams.add(map.get(freqMap));
        }
        
        return anagrams;
    }
    
    private HashMap<Character, Integer> getMap(String str){
        int len = str.length();
        HashMap<Character, Integer> freqMap = new HashMap();
        
        for(int idx=0; idx<len; idx++){
            char ch = str.charAt(idx);
            freqMap.put(ch, freqMap.getOrDefault(ch, 0)+1);
        }
        
        return freqMap;
    }
}