// TC : O(len)
// SC : O(1)

class Solution {
    public boolean validUtf8(int[] data) {
        int len = data.length;
        int remainingBytes = 0;
        
        int mask1 = 0b11111000;
        int mask2 = 0b11110000;
        int mask3 = 0b11100000;
        int mask4 = 0b11000000;
        int mask5 = 0b10000000;
        
        for(int idx = 0;idx<len;idx++){
            if(remainingBytes > 0){
                if((data[idx]&mask4) != mask5) return false;
                else remainingBytes--;
            }else{
                if((data[idx]&mask1) == mask2) remainingBytes = 3;
                else if((data[idx]&mask2) == mask3) remainingBytes = 2;
                else if((data[idx]&mask3) == mask4) remainingBytes = 1;
                else if((data[idx]&mask5) == 0) remainingBytes = 0;
                else return false;
            }
        }
        
        if(remainingBytes == 0) return true;
        return false;
    }
}