// Solution-1: Iterative

// TC: O(len_a + len_b)
// SC: O(1) excluding space required for output

class Solution {
    public String addBinary(String a, String b) {
        int len_a = a.length();
        int len_b = b.length();
        int idx_a = len_a-1;
        int idx_b = len_b-1;
        int carry = 0;
        StringBuilder str = new StringBuilder();
        
        while(idx_a>=0 || idx_b>=0 || carry>0){
            int sum = getDigitAt(a, idx_a) + getDigitAt(b, idx_b) + carry;
            
            int digit = sum%2;
            carry = sum/2;
            
            str.append(digit);
            
            idx_a--;
            idx_b--;
        }
        
        return str.reverse().toString();
    }
    
    private int getDigitAt(String str, int idx){
        if(idx<0) return 0;
        else return str.charAt(idx)-'0';
    }
}


// Solution-2: Recursive

// TC: O(len_a + len_b)
// SC: O(len_a + len_b)

class Solution {
    public String addBinary(String a, String b) {
        StringBuilder str = new StringBuilder();
        
        int carry = solve(a, b, 0, 0, a.length(), b.length(), str);
        if(carry>0) str.append(carry);
        
        return str.reverse().toString();
    }
    
    private int solve(String a, String b, int idx_a, int idx_b, int len_a, int len_b, StringBuilder str){
        if(idx_a==len_a && idx_b==len_b) return 0;
        
        int carry = 0;
        int sum = 0;
        
        if((len_a-idx_a) > (len_b-idx_b)){
            carry = solve(a, b, idx_a+1, idx_b, len_a, len_b, str);
            sum  = (a.charAt(idx_a)-'0') + carry;
        }else if((len_a-idx_a) < (len_b-idx_b)){
            carry = solve(a, b, idx_a, idx_b+1, len_a, len_b, str);
            sum  = (b.charAt(idx_b)-'0') + carry;
        }else{
            carry = solve(a, b, idx_a+1, idx_b+1, len_a, len_b, str);
            sum = (a.charAt(idx_a)-'0') + (b.charAt(idx_b)-'0') + carry;
        }
        
        int digit = sum%2;
        carry = sum/2;
            
        str.append(digit);
           
        return carry;
    }
}