/*
 Solution-1: Brute force, TLE
 
 Ideas: 
 If you toggle a bulb odd number of times, it is ON 
 If toggled even number of times, it is OFF
 For all bulbs from 1 to n, if any bulb has odd divisors increment count 
 We look at divisors of a bulb since the bulb is toggled in every 'divisor' round
 
 TC: O(n*sqrt(n))
 SC: O(1)
*/

class Solution {
    public int bulbSwitch(int n) {
        int count = 0;
        
        for(int num=1; num<=n; num++){
            int divisorCount = getDivisorCount(num);
            
            if(divisorCount%2!=0) count++;
        }
        
        return count;
    }
    
    private int getDivisorCount(int num){
        int divisorCount = 0;
        
        for(int divisor=1; divisor<=num; divisor++){
            if(num%divisor==0){
                int divisor1 = divisor;
                int divisor2 = num/divisor;
                
                if(divisor1==divisor2) divisorCount++;
                else divisorCount+=2;
            }
        }
        
        return divisorCount;
    }
}


/*
 Solution-2:
 
 Divisors come in pairs
 example:  for num = 12 we have: (1, 12), (2, 6), (3, 4), total even divisors
 exception: num is a square number
 example: for num = 36 we have: (1, 36), (2, 18), (3, 12), (4, 9) and 6 (total odd divisors)
 so just count the square numbers
 
 Perfect squares have odd number of divisors 
 all other numbers have even number of divisors
 
 TC: O(1) assuming sqrt works in O(1)
 SC: O(1)
*/

class Solution {
    public int bulbSwitch(int n) {
        return (int)Math.sqrt(n);
    }
    
//     private int getDivisorCount(int num){
//         int divisorCount = 0;
        
//         for(int divisor=1; divisor<=num; divisor++){
//             if(num%divisor==0){
//                 int divisor1 = divisor;
//                 int divisor2 = num/divisor;
                
//                 if(divisor1==divisor2) divisorCount++; // note that this condition is only hit for perfect squares !
//                 else divisorCount+=2;
//             }
//         }
        
//         return divisorCount;
//     }
}