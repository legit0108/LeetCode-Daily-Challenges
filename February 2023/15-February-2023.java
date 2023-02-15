// Simple Math

// TC: O(max(len, log(k)))
// SC: O(max(len, log(k)))

class Solution {
    public List<Integer> addToArrayForm(int[] num, int k) {
        LinkedList<Integer> array_form = new LinkedList();
        int len = num.length;
        int idx = len-1;
        int carry = 0;
        
        while(idx>=0 || k>0 || carry>0){
            int sum = ((idx>=0)?num[idx]:0) + k%10 + carry;
            
            int digit = sum%10;
            carry = sum/10;
            
            array_form.addFirst(digit);
            
            idx--;
            k = k/10;
        }
        
        return array_form;
    }
}