// TC: O(len)
// SC: O(len)

class Solution {
    public boolean canMakeArithmeticProgression(int[] arr) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int len = arr.length;
        Set<Integer> set = new HashSet();
        
        for(int index=0; index<len; index++){
            int num = arr[index];
            
            if(num>max) max = num;
            if(num<min) min = num;
            
            set.add(num);
        }
        
        if(((max-min)%(len-1))!=0) return false;
        int diff = (max-min)/(len-1);
        
        for(int index=0; index<len; index++){
            int val = min+index*diff;
            if(!set.contains(val)) return false;
        }
        
        return true;
    }
}