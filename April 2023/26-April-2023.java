// Solution-1: 
// TC ~ O(1) or we can say O(digits in num)^2
// SC: O(1)

class Solution {
    public int addDigits(int num) {
        while(num/10>0){
            int sum = 0;
            
            while(num>0){
                sum+=num%10;
                num/=10;
            }
            
            num = sum;
        }
        
        return num;
    }
}


//-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----


/*
 Solution-2: 
 
 Note that there is a pattern:
 The answer for 0 is 0
 else:
 
 1 -> 1  |  10 -> 1  |  19 -> 1
 2 -> 2  |  11 -> 2  |  20 -> 2
 3 -> 3  |  12 -> 3  |  21 -> 3
 .       |  .        |  .
 .       |  .        |  .
 .       |  .        |  .
 9 -> 9  |  18 -> 9  |  27 -> 9
 
 According to the pattern, if num is 0 we return 0
 else if num%9 is 0 we return 9
 else we return num%9
 
 The question is asking us to find digital root
 
 Any number where it's digits add to 9 is always divisible by 9
 Therefore the 'digital root' for any number divisible by 9 is always 9
 Also, the answer for 0 is 0

 When num is other than 0 or 9:
 the answer will always n % 9 for a given number n. (AKA the difference between given number n and the nearest number that is divisible by 9, since numbers divisible by 9 always have a digital root of 9)

 Let's assume that the initial number has 4 digits, which means it will be in the form: abcd
 Let abcd = a * 1000 + b * 100 + c * 10 + d
 or abcd = a + b + c + d + (999 * a + 99 * b + 9 * c) -> (1)
 If we take remainder with 9 in (1), assuming a+b+c+d results in one digit, then the remainder will have same value as a+b+c+d
 The final one-digit number will have the same remainder when divided by 9 as the initial number, and because it only has one digit, it is the remainder itself
*/

class Solution {
    public int addDigits(int num) {
        if(num==0) return 0;
        
        int ans = num%9;
        if(ans==0) ans = 9;
        
        return ans;
        
        // One liner: return 1 + (num-1)%9; (or use ternary operator with this solution)
    }
}