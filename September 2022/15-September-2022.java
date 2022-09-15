// TC : O(len*log(len))
// SC : O(len)

class Solution {
    public int[] findOriginalArray(int[] changed) {
        int len = changed.length;
        if(len%2!=0) return new int[0];
        Arrays.sort(changed);
        
        int original[] = new int[len/2];
        int originalIdx = 0;
        HashMap<Integer,Integer> map = new HashMap();
        
        for(int num : changed) map.put(num,map.getOrDefault(num,0)+1);
        
        for(int num : changed){
            int freq = map.get(num);
            
            if(freq==0) continue;
            else{
                if(map.containsKey(2*num) && ((int)map.get(2*num))>0){
                    original[originalIdx] = num;
                    originalIdx++;
                    
                    map.put(num,map.get(num)-1);
                    map.put(2*num,map.get(2*num)-1);
                }else return new int[0];
            }
        }
        
        return original;
    }
}