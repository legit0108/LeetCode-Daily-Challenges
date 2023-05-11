// Solution-1: DP
// Match all occurrences of nums2 in nums1

// TC: O(len1*len2*len1)
// SC: O(len1*len2)

class Solution {
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        Integer[][] dp = new Integer[len1][len2];
        
        return getMaxUncrossedLines(nums1, nums2, dp, len1-1, len2-1);
    }
    
    private int getMaxUncrossedLines(int[] nums1, int[] nums2, Integer[][] dp, int index1, int index2){
        if(index2<0 || index1<0) return 0;
        if(dp[index1][index2]!=null) return dp[index1][index2];
        
        int maxUncrossedLines = getMaxUncrossedLines(nums1, nums2, dp, index1, index2-1);
        
        for(int index=index1; index>=0; index--){
            if(nums1[index]==nums2[index2]){
                maxUncrossedLines = Math.max(maxUncrossedLines, 1+getMaxUncrossedLines(nums1, nums2, dp, index-1, index2-1));
            }
        }
        
        return dp[index1][index2]= maxUncrossedLines;
    }
}


//-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----


// Solution-2: LCS
// Lines do not intersect each other if common subsequence of nums1, nums2 is taken
// Maximize common subsequence -> LCS

// TC: O(len1*len2)
// SC: O(len1*len2)

class Solution {
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int size1 = nums1.length;
        int size2 = nums2.length;
        int[][] dp = new int[size1+1][size2+1];
        
        for(int len1=0; len1<=size1; len1++){
            for(int len2=0; len2<=size2; len2++){
                if(len1==0 || len2==0) dp[len1][len2] = 0;
                else{
                    int index1 = len1-1;
                    int index2 = len2-1;
                    
                    if(nums1[index1]==nums2[index2]) dp[len1][len2] = 1 + dp[len1-1][len2-1];
                    else dp[len1][len2] = Math.max(dp[len1-1][len2], dp[len1][len2-1]);
                }
            }
        }
        
        return dp[size1][size2];
    }
}

// Space optimized LCS
// TC: O(len1*len2)
// SC: O(min(len1, len2))

class Solution {
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int size1 = nums1.length;
        int size2 = nums2.length;
        if(size1<size2) return maxUncrossedLines(nums2, nums1);
        
        int[] dp = new int[size2+1];
        
        for(int len1=0; len1<=size1; len1++){
            int prev = 0;
            
            for(int len2=0; len2<=size2; len2++){
                if(len1==0 || len2==0) dp[len2] = 0;
                else{
                    int index1 = len1-1;
                    int index2 = len2-1;
                    int curr = dp[len2];
                    
                    if(nums1[index1]==nums2[index2]) dp[len2] = 1 + prev;
                    else dp[len2] = Math.max(curr, dp[len2-1]);
                    
                    prev = curr;
                }
            }
        }
        
        return dp[size2];
    }
}