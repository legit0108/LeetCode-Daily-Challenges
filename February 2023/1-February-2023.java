// Solution-1: Brute force
// Check all prefixes

// TC: O(len1*len2)
// SC: O(1) ignoring output space 

class Solution {
    public String gcdOfStrings(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        
        if(len2>len1) return gcdOfStrings(str2, str1);
        
        int ans = -1;
        int currLen = 1;
        
        while(currLen<=len2){
            if(equal(str1, str2, currLen)) ans = currLen;
            
            currLen++;
        }
        
        if(ans==-1) return "";
        else return str2.substring(0, ans);
    }
    
    private boolean equal(String str1, String str2, int len){
        int len1 = str1.length();
        int len2 = str2.length();
        int idx = 0;
        int idx1 = 0;
        int idx2 = 0;
        boolean flag = false;
        
        while(idx1<len1 || idx2<len2){
            if(idx2==len2){
                if(!flag && idx<len) return false;
                else flag = true;
            }
            
            if(idx==len) idx = 0;
            
            if(str1.charAt(idx1)!=str2.charAt(idx)) return false;
            
            if(idx2<len2){
               if(str2.charAt(idx2)!=str2.charAt(idx)) return false;
               else idx2++;
            }
            
            idx++;
            idx1++;
        }
        
        if(idx==len && idx1==len1) return true;
        else return false;
    }
}


/*
 Solution-2: Using GCD
 
 If two strings have some common prefix, then concatenating them in either order should result in the same string
 str1+str2 == str2+str1 implies str1, str2 have a common gcd string
 Proof:
  str1 = mGCD
  str2 = nGCD
  
  str1+str2 = (m+n)GCD = nGCD + mGCD = str2+str1, where GCD is the gcd string of str1, str2
 Both strings are made up of substring added multiple times
 Since they are multiples, we just need to find the gcd of the length of two strings 
 Once it is established there is a valid gcd, the number of characters to repeat will be the gcd of lengths of strings
  
 TC: O(str1.length() + str2.length())
 SC: O(str1.length() + str2.length())
*/

class Solution {
    public String gcdOfStrings(String str1, String str2) {
        if(!((str1+str2).equals(str2+str1))) return "";
        
        int gcdLen = getGcd(str1.length(), str2.length());
        
        return str1.substring(0, gcdLen);
    }
    
    private int getGcd(int num1, int num2){
        while(num2>0){
            int rem = num1%num2;
            num1 = num2;
            num2 = rem;
        }
        
        return num1;
    }
}