// Solution - 1 : HashMap + Sorting

// TC : O(lenlog(len))
// SC : O(len)

class Solution {
    public String frequencySort(String s) {
        int len = s.length();
        Character[] str = new Character[len];
        HashMap<Character, Integer> map = new HashMap();
        
        for(int idx=0; idx<len; idx++){
            char ch = s.charAt(idx);
            str[idx] = ch;
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        
        Arrays.sort(str, (char1, char2) -> (((int)map.get(char1) == (int)map.get(char2)) ? 
                    Integer.compare(char1, char2) : Integer.compare(map.get(char2), map.get(char1))));
       
        StringBuilder sortedStr = new StringBuilder();
        for(int idx=0; idx<len; idx++) sortedStr.append(str[idx]);
        
        return sortedStr.toString();
    }
}

// Solution - 2 : HashMap + PriorityQueue

// TC : O(len + distinct(s)*log(distinct(s)))
// SC : O(len)

class Solution {
    public String frequencySort(String s) {
        int len = s.length();
        HashMap<Character, Integer> map = new HashMap();
        PriorityQueue<Character> heap = new PriorityQueue<Character>((char1, char2) -> 
                                            Integer.compare(map.get(char2), map.get(char1)));
        
        for(int idx=0; idx<len; idx++){
            char ch = s.charAt(idx);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        
        for(char ch : map.keySet()) heap.add(ch);
        
        StringBuilder sortedStr = new StringBuilder();
        
        while(heap.size()>0){
            char ch = heap.remove();
            int freq = map.get(ch);
            
            while(freq>0){
                sortedStr.append(ch);
                freq--;
            }
        }
        
        return sortedStr.toString();
    }
}

// Solution - 3 : Bucket Sort

// TC : O(len)
// SC : O(len)

class Solution {
    public String frequencySort(String s) {
        int len = s.length();
        HashMap<Character, Integer> map = new HashMap();
        int maxFreq = 0;
        int minFreq = len;
        
        for(int idx=0; idx<len; idx++){
            char ch = s.charAt(idx);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
            
            int freq = map.get(ch);
            maxFreq = Math.max(maxFreq, freq);
            minFreq = Math.min(minFreq, freq);
        }
        
        HashMap<Integer, ArrayList<Character>> freqMap = new HashMap();
        
        for(char ch : map.keySet()){
            int freq = map.get(ch);
            
            if(freqMap.get(freq) == null) freqMap.put(freq, new ArrayList());
            freqMap.get(freq).add(ch);
        }
        
        StringBuilder sortedStr = new StringBuilder();
        
        for(int freq=maxFreq; freq>=minFreq; freq--){
            ArrayList<Character> list = freqMap.get(freq);
            
            if(list!=null){
                for(int idx = 0; idx<list.size(); idx++){
                    int count = freq;
                    char ch = list.get(idx);
                    
                    while(count>0){
                        sortedStr.append(ch);
                        count--;
                    }
                }
            }
        }
        
        return sortedStr.toString();
    }
}
