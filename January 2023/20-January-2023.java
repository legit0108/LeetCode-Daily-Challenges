// Since we need to return all the results, all the solutions run in exponential time and space


// Solution-1: Recursion/DFS, avoid duplicates using set

class Solution {
    public List<List<Integer>> findSubsequences(int[] nums) {
        Set<List<Integer>> result = new HashSet();
        dfs(0, nums.length, nums, new ArrayList(), result);
        return new ArrayList(result);
    }
    
    private void dfs(int idx, int len, int[] nums, List<Integer> list, Set<List<Integer>> result){
        if(idx==len){
            if(list.size()>=2) result.add(new ArrayList(list));
            return;
        }
        
        int num = nums[idx];
        int size = list.size();
        
        if(size==0 || num>=list.get(size-1)){
            list.add(num);
            dfs(idx+1, len, nums, list, result);
            list.remove(size);
        }
        
        dfs(idx+1, len, nums, list, result);
    }
}


// Solution-2: Iteratively using bit-masking, avoid duplicates using set

class Solution {
    public List<List<Integer>> findSubsequences(int[] nums) {
        Set<List<Integer>> result = new HashSet();
        int len = nums.length;
        
        for(int mask=0; mask<(1<<len); mask++){
            List<Integer> subsequence = new ArrayList();
            boolean increasing = true;
            int prevVal = -101;
            
            for(int idx=0; idx<len; idx++){
                int val = nums[idx];
                
                if((mask&(1<<idx))!=0){
                    if(val<prevVal){
                        increasing = false;
                        break;
                    }
                    
                    subsequence.add(val);
                    prevVal = val;
                }
            }
            
            if(subsequence.size()>1 && increasing) result.add(new ArrayList(subsequence));
        }
        
        return new ArrayList(result);
    }
}


// Solution-3: Recursion/DFS, avoiding duplicates without set

class Solution {
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> result = new ArrayList();
        dfs(0, nums.length, nums, new ArrayList(), result);
        return result;
    }
    
    private void dfs(int idx, int len, int[] nums, List<Integer> list, List<List<Integer>> result){
        if(idx==len){
            if(list.size()>=2) result.add(new ArrayList(list));
            return;
        }
        
        int num = nums[idx];
        int size = list.size();
        int lastNum = (size>0?list.get(size-1):101);
        
        if(size==0 || num>=lastNum){
            list.add(num);
            dfs(idx+1, len, nums, list, result);
            list.remove(size);
        }
        
        if(size>0 && lastNum==num) return; // avoid duplicates
        dfs(idx+1, len, nums, list, result);
    }
}