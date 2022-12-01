// TC: O(len)
// SC: O(1)

class Solution {
    public boolean halvesAreAlike(String s) {
        int len = s.length();
        int firstHalfVowelCount = 0;
        int secondHalfVowelCount = 0;
        
        for(int idx=0; idx<len; idx++){
            char ch = s.charAt(idx);
            
            if(isVowel(ch)){
                if(idx<len/2) firstHalfVowelCount++;
                else secondHalfVowelCount++;
            }
        }
        
        if(firstHalfVowelCount == secondHalfVowelCount) return true;
        else return false;
    }
    
    private boolean isVowel(char ch){
        if(ch=='a' || ch=='e' || ch=='i' || ch=='o' || ch=='u') return true;
        else if(ch=='A' || ch=='E' || ch=='I' || ch=='O' || ch=='U') return true;
        else return false;
    }
}