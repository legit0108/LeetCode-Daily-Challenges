// Prefix sum + map

// TC: O(nums.length)
// SC: O(k)

class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap();
        map.put(0, 1);
        int sum = 0;
        int count = 0;
        
        for(int num: nums){
            sum+=num;
            
            int rem = sum%k;
            if(rem<0) rem+=k;
            
            int freq = map.getOrDefault(rem, 0);
            count+=freq;
            map.put(rem, freq+1);
        }
        
        return count;
    }
}