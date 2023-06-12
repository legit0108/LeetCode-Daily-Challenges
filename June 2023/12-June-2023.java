// TC: O(len)
// SC: O(len)

class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> ranges = new ArrayList();
        int index = 0;
        int len = nums.length;
        
        while(index<len){
            int startIndex = index;
            
            while(index+1<len && ((long)nums[index+1])==((long)nums[index]+1l)){
                index++;   
            }
            
            int endIndex = index;
            
            if(startIndex==endIndex) ranges.add(Integer.toString(nums[index]));
            else ranges.add(Integer.toString(nums[startIndex])+"->"+Integer.toString(nums[endIndex]));
            
            index++;
        }
        
        return ranges;
    }
}