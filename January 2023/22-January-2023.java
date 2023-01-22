// Backtracking

// TC: exponential
// SC: exponential

class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> partitions = new ArrayList();
        findPartitions(s, 0, new ArrayList<String>(), partitions);
        return partitions;
    }
    
    private void findPartitions(String s, int start, List<String> partition, List<List<String>> partitions){
        int len = s.length();
        
        if(start==len){
            partitions.add(new ArrayList(partition));
            return;
        }
        
        for(int end=start; end<len; end++){
            if(isPalindromic(s, start, end)){ // can improve palindromic check by introducing isPalindrome[][] (DP)
                String substr = s.substring(start, end+1);
                partition.add(substr);
                
                findPartitions(s, end+1, partition, partitions);
                
                partition.remove(partition.size()-1);
            }
        }
    }
    
    private boolean isPalindromic(String str, int start, int end){
        while(start<end){
            if(str.charAt(start) != str.charAt(end)){
                return false;
            }
            
            start++;
            end--;
        }
        
        return true;
    }
}