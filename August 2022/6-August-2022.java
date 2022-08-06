// TC : O(log(buckets))
// SC : O(1)

class Solution {
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        if(buckets==1) return 0;
        
        int base = 1 + minutesToTest/minutesToDie;
        int pigs = 1;
        int factor = 1;
        
        while(factor*base<buckets){
            factor*=base;
            pigs++;
        }
        
        return pigs;
    }
}