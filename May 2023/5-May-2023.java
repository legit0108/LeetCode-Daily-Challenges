/*
 Brute force solution: Check vowels by iterating through all windows of size k
 TC: O(s.length()*k)

 Better: Sliding Window
 Maintain count of vowels in every window of size k
 Increment/Decrement counter as size of window shifts by 1 
 TC: O(s.length())
 SC: O(1)
*/


class Solution {
    public int maxVowels(String s, int k) {
        int maxVowelCount = 0;
        int currVowelCount = 0;
        
        for(int index=0; index<s.length(); index++){
            if(index>=k && isVowel(s.charAt(index-k))) currVowelCount--;
            if(isVowel(s.charAt(index))) currVowelCount++;
            
            maxVowelCount = Math.max(maxVowelCount, currVowelCount);
        }
        
        return maxVowelCount;
    }
    
    private boolean isVowel(char ch){
        if(ch=='a' || ch=='e' || ch=='i' || ch=='o' || ch=='u') return true;
        else return false;
    }
}