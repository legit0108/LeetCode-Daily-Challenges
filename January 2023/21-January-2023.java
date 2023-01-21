// Backtracking, time and space exponential since we need to generate all possible results

class Solution {
    public List<String> restoreIpAddresses(String s) {
        int len = s.length();
        if(len>12) return new ArrayList<String>();
        
        List<String> IpAddresses = new ArrayList<String>();
        restoreIpAddresses(s, 0, len, new ArrayList<Integer>(), IpAddresses);
        
        return IpAddresses;
    }
    
    private void restoreIpAddresses(String s, int startIdx, int len, List<Integer> nums, List<String> IpAddresses){
        if(startIdx>=len){
            if(nums.size()!=4) return;
            
            StringBuilder IpAddress = new StringBuilder();
            
            for(int num: nums){
                if(!(num>=0 && num<=255)) return;
                
                IpAddress.append(num).append(".");
            }
            
            IpAddresses.add(IpAddress.toString().substring(0, IpAddress.length()-1));
            return;
        }
        
        int num = 0;
        int idx = startIdx;
        int endIdx = Math.min(startIdx+3, len);
        
        while(idx<endIdx){
            int dig = s.charAt(idx)-'0';
            num = num*10+dig;
            
            nums.add(num);
            restoreIpAddresses(s, idx+1, len, nums, IpAddresses);
            nums.remove(nums.size()-1);
            
            if(idx==startIdx && dig==0) break;
            idx++;
        }
    }
}