// Brute force with pruning

// TC: O(2^size)
// SC: O(size)

class Solution {
    private int maxLen;
    
    public int maxLength(List<String> arr) {
        HashMap<String, Boolean> isUnique = new HashMap();
        HashMap<String, Integer> masks = new HashMap();
        int size = arr.size();
        
        for(int idx=0; idx<size; idx++){
            String str = arr.get(idx);
            int strLen = str.length();
            int mask = 0;
            boolean unique = true;
            
            for(int strIdx=0; strIdx<strLen; strIdx++){
                char ch = str.charAt(strIdx);
                int pos = 1<<(ch-'a');
                
                if((mask&pos)!=0) unique = false;
                mask|=pos;
            }
            
            masks.put(str, mask);
            isUnique.put(str, unique);
        }
        
        findMaxLen(0, 0, 0, size, arr, isUnique, masks);
        return maxLen;
    }
    
    private void findMaxLen(int idx, int maskSoFar, int lenSoFar, int size, List<String> arr, HashMap<String, Boolean> isUnique, HashMap<String, Integer> masks){
        if(idx==size){
            maxLen = Math.max(maxLen, lenSoFar);
            return;
        }
        
        String currStr = arr.get(idx);
        boolean unique = isUnique.get(currStr);
        
        findMaxLen(idx+1, maskSoFar, lenSoFar, size, arr, isUnique, masks);
            
        if(unique){
            int currMask = masks.get(currStr);
            
            if((maskSoFar&currMask)==0){
                maskSoFar|=currMask;
                lenSoFar+=currStr.length();
                
                findMaxLen(idx+1, maskSoFar, lenSoFar, size, arr, isUnique, masks);
            }
        }
    }
}