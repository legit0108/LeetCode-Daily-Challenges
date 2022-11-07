// Solution - 1: Reverse and find

// TC: O(log(num))
// SC: O(1)

class Solution {
    public int maximum69Number (int num) {
        num = rev(num);
        return findNum(num);
    }
    
    private int rev(int num){
        int revNum = 0;
        
        while(num>0){
            int dig = num%10;
            revNum=revNum*10+dig;
            num/=10;
        }
        
        return revNum;
    }
    
    private int findNum(int num){
        boolean foundSix = false;
        int ans = 0;
        
        while(num>0){
            int dig = num%10;
            
            if(dig==6){
                if(foundSix) ans = ans*10+dig;
                else{
                    ans = ans*10+9;
                    foundSix = true;
                }
            }else ans = ans*10+dig;
            
            num/=10;
        }
        
        return ans;
    }
}

// Solution - 2: One pass

// TC: O(log(num))
// SC: O(1)

class Solution {
    public int maximum69Number (int num) {
        int number = num;
        int factor = 1;
        int toAdd = 0;
        
        while(number>0){
            int dig = number%10;
            number/=10;
            
            if(dig==6){
                toAdd = 3*factor;
            }
            
            factor*=10;
        }
        
        return num+toAdd;
    }
}