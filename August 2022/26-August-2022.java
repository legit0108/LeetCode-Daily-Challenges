/*

Generate all powers of 2 <= MAX_N and check if each power can be formed or not

TC : O(log(MAX_N)*log(MAX_N))
SC : O(1)

*/

class Solution {
    private final int MAX_N = (int)1e9;
    
    public boolean reorderedPowerOf2(int n) {
        long counter = getCounter(n);
        
        for(int currNum = 1;currNum<=MAX_N;currNum*=2){
            long currCounter = getCounter(currNum);
            if(counter==currCounter) return true;
        }
        
        return false;
    }
    
    private long getCounter(int num){
        long counter = 0;
        
        while(num>0){
            int dig = num%10;
            counter+=(long)Math.pow(10,dig);
            num/=10;
        }
        
        return counter;
    }
}