// Two pointers, pick elements off from ends one by one

// TC : O(arr.length)
// SC : O(1)

class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int start = 0;
        int end = arr.length-1;
        
        while(end-start+1 > k){
            int diff1 = Math.abs(arr[start]-x);
            int diff2 = Math.abs(arr[end]-x);
            
            if(diff1 <= diff2) end--;
            else start++;
        }
        
        List<Integer> closestElements = new ArrayList();
        for(int idx=start; idx<=end; idx++) closestElements.add(arr[idx]);
        
        return closestElements;
    }
}