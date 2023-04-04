// Solution-1: Using Set
// Idea: Start a new substring as soon as you encounter a duplicate character 

// TC: O(s.length())
// SC: O(set.size())

class Solution {
    public int partitionString(String s) {
        int substrings = 1;
        HashSet<Character> set = new HashSet();
        
        for(int index=0; index<s.length(); index++){
            char ch = s.charAt(index);
            
            if(set.contains(ch)){
                substrings++;
                set = new HashSet();
            }
            
            set.add(ch);
        }
        
        return substrings;
    }
}


/* 
 Solution-2: Track the last index
 Idea: If the last index of occurence of a character is greater than or equal to the start of our current substring,
 then that means we are encountering said character for the second time after starting the current substring, so we perform a split here

 TC: O(s.length())
 SC: O(26)
*/

class Solution {
    public int partitionString(String s) {
        int substrings = 0;
        int currSubstringStartIndex = 0;
        int[] lastIndex = new int[26];
        
        for(int index=0; index<s.length(); index++){
            int position = s.charAt(index)-'a';
            
            if(lastIndex[position]>=currSubstringStartIndex){
                currSubstringStartIndex = index+1;
                substrings++;
            }
            
            lastIndex[position] = index+1;
        }
        
        return substrings;
    }
}


// Solution-3: Bit Manipulation
// Idea: Optimize Solution-1 by using an integer mask as a set 

// TC: O(s.length())
// SC: O(1)

class Solution {
    public int partitionString(String s) {
        int substrings = 1;
        int mask = 0;
        
        for(int index=0; index<s.length(); index++){
            int bit = s.charAt(index)-'a';
            
            if(bitSet(mask, bit)){
                mask = 0; // reset mask for the next substring
                substrings++;
            }
            
            mask = setBit(mask, bit);
        }
        
        return substrings;
    }
    
    private boolean bitSet(int mask, int bit){ // returns true if bit is set in mask
        return (mask&(1<<bit))!=0;
    }
    
    private int setBit(int mask, int bit){ // sets bit int mask
        return mask|(1<<bit);
    }
}