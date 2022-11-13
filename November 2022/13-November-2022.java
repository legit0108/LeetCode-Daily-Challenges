// TC: O(len)
// SC: O(len)

class Solution {
    public String reverseWords(String s) {
        String words[] = s.split(" ");
        StringBuilder str = new StringBuilder();
        int len = words.length;
        int idx = len-1;
        
        while(idx>=0){
            String word = words[idx];
            
            if(!(word.equals("") || word.equals(" "))){
                str.append(word).append(" ");
            }
            
            idx--;
        }
        
        String ans = str.substring(0, str.length()-1);
        return ans;
    }
}