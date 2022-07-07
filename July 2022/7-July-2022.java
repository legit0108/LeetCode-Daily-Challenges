// Method-1 : DP

// TC : O(len1*len2)
// SC : O(len1*len2)

// check if s3 is interleaving of s1 , s2 by comparing characters
// at corresponding indices , and recurring for rest of the string

class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int len1 = s1.length();
        int len2 = s2.length();
        int len3 = s3.length();
        
        if(len1+len2!=len3) return false;
        
        boolean dp[][] = new boolean[len1+1][len2+1];
        for(int idx1=0;idx1<=len1;idx1++){
            for(int idx2=0;idx2<=len2;idx2++){
                if(idx1==0&&idx2==0) dp[idx1][idx2] = true;
                else{
                    if(idx1>0){
                        if(s1.charAt(idx1-1)==s3.charAt(idx1+idx2-1)) 
                        dp[idx1][idx2]|=dp[idx1-1][idx2];
                    }

                    if(idx2>0){
                        if(s2.charAt(idx2-1)==s3.charAt(idx1+idx2-1)) 
                        dp[idx1][idx2]|=dp[idx1][idx2-1];
                    }
                }
            }
        }
        
        return dp[len1][len2];
    }
}

// Method-2 : DP

// TC : O(len1*len2)
// SC : O(len2)

class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int len1 = s1.length();
        int len2 = s2.length();
        int len3 = s3.length();
        
        if(len1+len2!=len3) return false;
        
        boolean dp[] = new boolean[len2+1];
        for(int idx1=0;idx1<=len1;idx1++){
            for(int idx2=0;idx2<=len2;idx2++){
                if(idx1==0&&idx2==0) dp[idx2] = true;
                else{
                    boolean ans = false;
                    
                    if(idx1>0){
                        if(s1.charAt(idx1-1)==s3.charAt(idx1+idx2-1)) 
                        ans|=dp[idx2];
                    }

                    if(idx2>0){
                        if(s2.charAt(idx2-1)==s3.charAt(idx1+idx2-1)) 
                        ans|=dp[idx2-1];
                    }
                    
                    dp[idx2] = ans;
                }
            }
        }
        
        return dp[len2];
    }
}

// Method-3 : BFS

// TC : O(len1*len2)
// SC : O(len1*len2)

class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int len1 = s1.length();
        int len2 = s2.length();
        int len3 = s3.length();
        if(len1+len2!=len3) return false;
        
        boolean visited[][] = new boolean[len1+1][len2+1];
        Queue<int[]> queue = new ArrayDeque();
        queue.add(new int[]{0,0});
        
        while(queue.size()>0){
            int arr[] = queue.remove();
            int idx1 = arr[0];
            int idx2 = arr[1];
            if(visited[idx1][idx2]) continue;
            visited[idx1][idx2] = true;
            
            if(idx1==len1&&idx2==len2) return true;
            if(idx1<len1&&s1.charAt(idx1)==s3.charAt(idx1+idx2)){
                if(!visited[idx1+1][idx2]) queue.add(new int[]{idx1+1,idx2});
            }
            if(idx2<len2&&s2.charAt(idx2)==s3.charAt(idx1+idx2)){
                if(!visited[idx1][idx2+1]) queue.add(new int[]{idx1,idx2+1});
            }
        }
        
        return false;
    }
}