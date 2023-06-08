class Solution {
    public int minFlips(int a, int b, int c) {
        int min_flips = 0;
        
        for(int bit=0; bit<32; bit++){
            int mask = 1<<bit;
            
            if((c&mask)>0){
                if((a&mask)>0 && (b&mask)>0) min_flips+=0;
                else if((a&mask)==0 && (b&mask)==0) min_flips+=1;
            }else{
                if((a&mask)>0 && (b&mask)>0) min_flips+=2;
                else if((a&mask)!=0 || (b&mask)!=0) min_flips+=1;
            }
        }
        
        return min_flips;
    }
}