// TC: O(len^2)
// SC: O(len) for output

class Solution {
    public String orderlyQueue(String s, int k) {
        if(k==1){
            int len = s.length();
            String ans = null;
            
            for(int idx=0; idx<len; idx++){
                String str = s.substring(idx) + s.substring(0, idx);
                if(ans==null || str.compareTo(ans)<0) ans = str;
            }
            
            return ans;
        }else return sort(s);
    }
    
    private String sort(String s){
        char str[] = s.toCharArray();
        Arrays.sort(str);
        return new String(str);
    }
}