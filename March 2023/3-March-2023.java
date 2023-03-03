// Solution-1: KMP

// TC: O(needle.length() + haystack.length())
// SC: O(needle.length() + haystack.length())

class Solution {
    public int strStr(String haystack, String needle) {
        String str = needle+"#"+haystack;
        int size = str.length();
        int needleLen = needle.length();
        int[] lps = new int[size];
        int idx = 1;
        int len = 0;
        
        while(idx<size){
            if(str.charAt(idx)==str.charAt(len)){
                len++;
                lps[idx] = len;
                
                if(lps[idx]==needleLen) return idx-2*needleLen;
                
                idx++;
            }else{
                if(len>0) len = lps[len-1];
                else idx++;
            }
        }
        
        return -1;
    }
}


// Solution-2: Pattern-hashing

// TC: O(needle.length() + haystack.length())
// SC: O(1)

class Solution {
    public int strStr(String haystack, String needle) {
        int haystackLen = haystack.length();
        int needleLen = needle.length();
        
        if(needleLen>haystackLen) return -1;
        
        long prime = 31;
        long mod = (long)1e9+7;
        long needleHash = generateHash(needle, needleLen, prime, mod);
        
        long factor = 1;
        long currHash = 0;
        
        for(int idx=needleLen-1; idx>=0; idx--){
            long val = ((((long)(haystack.charAt(idx)-'0'+1))*factor)+mod)%mod;
            currHash = ((currHash + val)+mod)%mod;
            if(idx>0) factor = ((factor*prime)+mod)%mod;
        }
        
        if(currHash==needleHash) return 0;
        
        int start = 0;
        int end = needleLen;
        
        while(end<haystackLen){
            long val = ((((long)(haystack.charAt(start)-'0'+1))*factor)+mod)%mod;
            currHash = ((currHash - val)+mod)%mod;
            
            currHash = ((currHash*prime)+mod)%mod;
            
            val = (((long)(haystack.charAt(end)-'0'+1))+mod)%mod;
            currHash = ((currHash + val)+mod)%mod;
            
            if(currHash==needleHash) return start+1;
            
            start++;
            end++;
        }
        
        return -1;
    }
    
    private long generateHash(String str, int len, long prime, long mod){
        long hash = 0;
        long factor = 1;
        
        for(int idx=len-1; idx>=0; idx--){
            long val = ((((long)(str.charAt(idx)-'0'+1))*factor)+mod)%mod;
            hash = ((hash + val)+mod)%mod;
            factor = ((factor*prime)+mod)%mod;
        }
        
        return hash;
    }
}