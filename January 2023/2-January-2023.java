// Solution-1: Using 3 boolean variables, interview friendly code

// TC: O(len)
// SC: O(1)

class Solution {
    public boolean detectCapitalUse(String word) {
        int len = word.length();
        char firstCh = word.charAt(0);
        
        boolean allCapital = (firstCh>='A' && firstCh<='Z');
        boolean allLower = (firstCh>='a' && firstCh<='z');
        boolean firstCapital = allCapital;
        
        for(int idx=1; idx<len; idx++){
            char ch = word.charAt(idx);
            
            if(ch>='A' && ch<='Z'){
                allLower = false;
                firstCapital = false;
            }else allCapital = false;
        }
        
        return (allCapital || allLower || firstCapital);
    }
}

// Solution-2: Using 1 int variable

// TC: O(len)
// SC: O(1)

class Solution {
    public boolean detectCapitalUse(String word) {
        int len = word.length();
        int capitalCount = 0;
        
        for(int idx=0; idx<len; idx++){
            char ch = word.charAt(idx);
            
            if(ch>='A' && ch<='Z') capitalCount++;
        }
        
        if(capitalCount==0 || capitalCount==len) return true;
        else if(capitalCount==1 && ('Z'-word.charAt(0))>=0) return true;
        else return false;
    }
}