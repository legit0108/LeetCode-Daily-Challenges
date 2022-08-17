// TC : O(len*wordLen)
// SC : O(len*wordLen)

class Solution {
    public int uniqueMorseRepresentations(String[] words) {
        String map[] = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..",
                        "--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-",
                        "-.--","--.."};
        Set<String> uniqueTransformations = new HashSet();
        int len = words.length;
        
        for(int idx=0;idx<len;idx++){
            String word = words[idx];
            int wordLen = word.length();
            StringBuilder transformation = new StringBuilder();
            
            for(int currIdx=0;currIdx<wordLen;currIdx++){
                char ch = word.charAt(currIdx);
                transformation.append(map[ch-'a']);
            }
            
            uniqueTransformations.add(transformation.toString());
        }
        
        return uniqueTransformations.size();
    }
}