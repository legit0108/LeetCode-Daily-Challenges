// Solution-1: StringBuilder

// TC: O(word1.length+word2.length)
// SC: O(word1.length+word2.length)

class Solution {
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        StringBuilder str1 = new StringBuilder();
        StringBuilder str2 = new StringBuilder();
        
        for(String word: word1) str1.append(word);
        for(String word: word2) str2.append(word);
        
        return str1.toString().equals(str2.toString());
    }
}

// Solution-2: Constant space

// TC: O(len1+len2)
// SC: O(1)

class Solution {
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        int idx1 = 0;
        int strIdx1 = 0;
        int idx2 = 0;
        int strIdx2 = 0;
        int len1 = word1.length;
        int len2 = word2.length;
        
        while(idx1<len1 && idx2<len2){
            if(strIdx1==word1[idx1].length()){
                idx1++;
                strIdx1 = 0;
            }
            if(strIdx2==word2[idx2].length()){
                idx2++;
                strIdx2 = 0;
            }
            if(idx1<len1 && idx2<len2){
                if(word1[idx1].charAt(strIdx1)==word2[idx2].charAt(strIdx2)){
                    strIdx1++;
                    strIdx2++;
                }else break;
            }
        }
        
        return (idx1==len1) && (idx2==len2);
    }
}

// Solution-3: One liner

// TC: O(word1.length+word2.length)
// SC: O(word1.length+word2.length)

class Solution {
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        return String.join("", word1).equals(String.join("", word2));
    }
}