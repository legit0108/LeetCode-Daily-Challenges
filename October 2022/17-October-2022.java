// Solution-1: HashSet

// TC: O(len)
// SC: O(26) / O(1)

class Solution {
    public boolean checkIfPangram(String sentence) {
        int len = sentence.length();
        HashSet<Character> set = new HashSet();
        
        for(int idx=0; idx<len; idx++){
            set.add(sentence.charAt(idx));
        }
        
        return set.size()==26;
    }
}

// Solution-2: Bit manipulation

// TC: O(len)
// SC: O(1)

class Solution {
    public boolean checkIfPangram(String sentence) {
        int len = sentence.length();
        int mask = 0;
        
        for(int idx=0; idx<len; idx++){
            mask|=1<<(sentence.charAt(idx)-'a');
        }
        
        return mask==(1<<26)-1;
    }
}