/* 

-> Go in reverse direction
-> generate ?????... from sequence
-> find substring which matches stamp exactly
-> ? is a no-op , matches any sequence since we can override stamp
-> reverse operations in end since we went from target to original string

TC : O(stampLen*stampLen*strLen)
SC : O(strLen+size)

*/

class Solution {
    public int[] movesToStamp(String stamp, String target) {
        List<Integer> indexList = new ArrayList();
        int stampLen = stamp.length();
        char str[] = target.toCharArray();
        int strLen = str.length;
        char targetStr[] = new char[strLen];
        Arrays.fill(targetStr,'?');
        
        while(!Arrays.equals(str,targetStr)){
            int stampIndex = getStampIndex(str,stamp,stampLen,strLen);
            
            if(stampIndex<0) return new int[0];
            else update(str,stampIndex,stamp,stampLen);
            indexList.add(stampIndex);
        }
        
        int size = indexList.size();
        int indexArr[] = new int[size];
        
        for(int idx=0;idx<size;idx++) indexArr[idx] = indexList.get(size-idx-1);
        return indexArr;
    }
    
    private void update(char str[],int idx,String stamp,int stampLen){
        int lastIdx = idx+stampLen-1;
        
        while(idx<=lastIdx){
            str[idx] = '?';
            idx++;
        }
    }
    
    private int getStampIndex(char str[],String stamp,int stampLen,int strLen){
        for(int idx=0;idx<=strLen-stampLen;idx++){
            int currIdx = idx;
            int stampIdx = 0;
            boolean foundMatchingChar = false;
            
            while(currIdx<strLen&&stampIdx<stampLen&&(str[currIdx]=='?'||str[currIdx]==stamp.charAt(stampIdx))){
                if(str[currIdx]!='?'){
                    foundMatchingChar = true;
                }
                
                currIdx++;
                stampIdx++;
            }
            
            if(stampIdx==stampLen&&foundMatchingChar) return idx;
        }
        
        return -1;
    }
}