// idea : look at top two frequent characters
// if they have same frequency then perform deletion
// if top two frequent characters have different frequency 
// then the top frequent character will differ in frequency
// from all characters , so we do not need to process it again

// TC : O(len)
// SC : O(1)
// because our maxHeap will contain atmost 26 characters
// s contains only lowercase English letters

class Solution {
    public int minDeletions(String s) {
       int len = s.length();
       int map[] = new int[26];
       PriorityQueue<Character> maxHeap;
       maxHeap = new PriorityQueue<Character>((char1,char2)->
                 (map[char2-'a']-map[char1-'a']));
        
       for(int idx=0;idx<len;idx++){
           char ch = s.charAt(idx);
           map[ch-'a']++;
       }
        
       for(char ch ='a';ch<='z';ch++){
           if(map[ch-'a']>0) maxHeap.add(ch); 
       }
        
       int minDels = 0;
        
       while(maxHeap.size()>1){
           char mostFreq = maxHeap.remove();
           char secondMostFreq = maxHeap.remove();
           
           if(map[mostFreq-'a']==map[secondMostFreq-'a']){
               map[secondMostFreq-'a']--;
               minDels++;
               maxHeap.add(mostFreq);
           }
           
           if(map[secondMostFreq-'a']>0) maxHeap.add(secondMostFreq);
       }
       
       return minDels; 
    }
}