// TC : O(len)
// SC : O(1)

class Solution {
    public int romanToInt(String s) {
        int len = s.length();
        int number = 0;
        int idx = 0;
        
        while(idx<len){
           if(idx==len-1){
               int val = getVal(s.charAt(idx));
               number+=val;
               idx++;
           }else{
               int val1 = getVal(s.charAt(idx));
               int val2 = getVal(s.charAt(idx+1));
               
               if(val1>=val2){
                   number+=val1;
                   idx++;
               }else{
                   number+=val2-val1;
                   idx+=2;
               }
           }
        }
        
        return number;
    }
    
    private int getVal(char ch){
        if(ch=='I') return 1;
        else if(ch=='V') return 5;
        else if(ch=='X') return 10;
        else if(ch=='L') return 50;
        else if(ch=='C') return 100;
        else if(ch=='D') return 500;
        else return 1000;
    }
}