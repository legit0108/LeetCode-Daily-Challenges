// TC: O(len)
// SC: O(len)

class Solution {
    public String makeGood(String s) {
        StringBuilder str = new StringBuilder();
        int len = s.length();
        
        for(int idx=0; idx<len; idx++){
            char ch = s.charAt(idx);
            int strLen = str.length();
            
            if(strLen==0) str.append(ch);
            else{
                if(foundBadAdjacent(str.charAt(strLen-1), ch)) str.deleteCharAt(strLen-1);
                else str.append(ch);
            }
        }
        
        return str.toString();
    }
    
    private boolean foundBadAdjacent(char ch1, char ch2){
        if(ch1>='A' && ch1<='Z'){
            if(ch2>='A' && ch2<='Z') return false;
            
            ch2 = (char)(ch2+('A'-'a'));
            if(ch2==ch1) return true;
        }else{
            if(ch2>='a' && ch2<='z') return false;
            
            ch2 = (char)(ch2-('A'-'a'));
            if(ch2==ch1) return true;
        }
        
        return false;
    }
}