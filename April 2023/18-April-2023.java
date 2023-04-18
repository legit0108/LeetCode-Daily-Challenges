// Solution-1: Merge using two pointers
// TC: O(len1+len2)
// SC: O(len1+len2)

class Solution {
    public String mergeAlternately(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        char[] mergedStr = new char[len1+len2];
        
        int index1 = 0;
        int index2 = 0;
        int index = 0;
        
        while(index1<len1 || index2<len2){
            if(index1<len1) mergedStr[index++] = word1.charAt(index1++);
            if(index2<len2) mergedStr[index++] = word2.charAt(index2++);
        }
        
        return new String(mergedStr);
    }
}


// Solution-2: Merge using one pointer
// TC: O(len1+len2)
// SC: O(len1+len2)

class Solution {
    public String mergeAlternately(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        StringBuilder mergedStr = new StringBuilder(); 
        
        int index = 0;
        
        while(index<Math.max(len1, len2)){
            if(index<len1) mergedStr.append(word1.charAt(index));
            if(index<len2) mergedStr.append(word2.charAt(index));
            
            index++;
        }
        
        return mergedStr.toString();
    }
}