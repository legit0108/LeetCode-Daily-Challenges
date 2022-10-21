// Check difference between current index and last index since that is the shortest such difference

// TC: O(len)
// SC: O(len)

class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        int len = nums.length;
        HashMap<Integer, Integer> map = new HashMap();
        
        for(int idx=0; idx<len; idx++){
            int val = nums[idx];
            
            if(map.containsKey(val)){
                int lastIdx = map.get(val);
                if(idx-lastIdx<=k) return true;
            }
            
            map.put(val, idx);
        }
        
        return false;
    }
}