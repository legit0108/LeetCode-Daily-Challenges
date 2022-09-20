// Longest Common Substring for arrays

// TC : O(len1 * len2)
// SC : O(len2)

class Solution {
    public int findLength(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int dp[] = new int[len2+1];
        int maxLenSubarr = 0;
        
        for(int currLen1 = 0;currLen1<=len1;currLen1++){
            for(int currLen2 = len2;currLen2>=0;currLen2--){
                if(currLen1==0 || currLen2==0) dp[currLen2] = 0;
                else{
                    int idx1 = currLen1-1;
                    int idx2 = currLen2-1;
                    
                    if(nums1[idx1] == nums2[idx2]) dp[currLen2] = 1 + dp[currLen2-1];
                    else dp[currLen2] = 0;
                }
                
                maxLenSubarr = Math.max(maxLenSubarr, dp[currLen2]);
            }
        }
        
        return maxLenSubarr;
    }
}