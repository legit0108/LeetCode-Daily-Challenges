// TC : O(len)
// SC : O(len)

class Solution {
    public String pushDominoes(String dominoes) {
        int len = dominoes.length();
        char str[] = new char[len+2];
        
        str[0] = 'L';
        str[len+1] = 'R';
        for(int idx = 1; idx<=len; idx++) str[idx] = dominoes.charAt(idx-1); 
    
        int prevIdx = 0;
        int idx = 1;
        len = str.length;
        
        while(idx < len){
            if(str[idx] != '.'){
                fill(str, prevIdx, idx);
                prevIdx = idx;
            }
            
            idx++;
        }
        
        String finalState = new String(str);
        finalState = finalState.substring(1, finalState.length()-1);
        return finalState;
    }
    
    private void fill(char str[], int start, int end){
        if(str[start] == 'L' && str[end] == 'R') return;
        
        if(str[start] == 'R' && str[end] == 'L'){
            while(start < end){
                str[start] = 'R';
                str[end] = 'L';
                
                start++;
                end--;
            }
        }else{
            for(int idx = start+1; idx<=end; idx++) str[idx] = str[start];
        }
    }
}