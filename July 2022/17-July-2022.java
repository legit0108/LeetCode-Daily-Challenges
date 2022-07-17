// Method - 1 : DP , memoization , TLE

// T(n,k) = T(n-1,k-1) + T(n-1,k-2) + ..... + T(n-1,k-n+1)
// TC : O(n*k*min(n,k))
// SC : O(n*k)

class Solution {
    public int kInversePairs(int n, int k) {
        Long dp[][] = new Long[n+1][k+1];
        long mod = (long)1e9+7;
        long ans = fun(n,k,mod,dp);
        
        int finalAns = (int)(ans%mod);
        if(finalAns<0) finalAns+=(int)mod;
        return finalAns;
    }
    
    private long fun(int n,int k,long mod,Long dp[][]){
        if(n==0) return 0;
        if(k==0) return 1;
        if(dp[n][k]!=null) return dp[n][k];
        
        long ans = 0;
        
        for(int num=0;num<=Math.min(k,n-1);num++){
            ans = ((ans%mod) + (fun(n-1,k-num,mod,dp)%mod))%mod;
        }
        
        return dp[n][k] = ans;
    }
}

// Method - 2 : DP , memoization , AC

// the equation that we derived goes like this : 
// T(n,k) = T(n-1,k) + T(n-1,k-1) + ........ + T(n-1,k-n+1) ---> 1

// for k+1 it goes like :
// T(n,k+1) = T(n-1,k+1) + T(n-1,k) +.......... + T(n-1,k+1-n+1) ---> 2

// now when you do 2 - 1 you get :
// T(n,k+1) - T(n,k) = T(n-1,k+1) - T(n-1,k-n+1)
// or T(n,k+1) = T(n,k) + T(n-1,k+1) - T(n-1,k-n+1)

// replace k+1 by k and our final equation becomes : 
// T(n,k) = T(n,k-1) + T(n-1,k) - T(n-1,k-n) 

// and we can use this recurrence relation to solve it in n*k time

// TC : O(n*k)
// SC : O(n*k)

class Solution {
    public int kInversePairs(int n, int k) {
        long mod = (long)1e9+7;
        Long dp[][] = new Long[n+1][k+1];
        
        long ans = fun(n,k,mod,dp);
        int finalAns = (int)(ans%mod);
        if(finalAns<0) finalAns+=(int)mod;
        return finalAns;
    }
    
    private long fun(int n,int k,long mod,Long dp[][]){
        if(n==0||k>((n*(n-1))/2)) return 0;
        if(k==0||k==((n*(n-1))/2)) return 1;
        if(dp[n][k]!=null) return dp[n][k];
        
        long ans = ((fun(n,k-1,mod,dp)%mod) + (fun(n-1,k,mod,dp)%mod))%mod;
        if(k>=n) ans = ((ans%mod) - (fun(n-1,k-n,mod,dp)%mod))%mod;
        
        return dp[n][k] = ans;
    }
}

// Method - 3 : DP , tabulation

// TC : O(n*k)
// SC : O(n*k)

class Solution {
    public int kInversePairs(int n, int k) {
        long mod = (long)1e9+7;
        long dp[][] = new long[n+1][k+1];
        
        for(int curr_n=0;curr_n<=n;curr_n++){
            for(int curr_k=0;curr_k<=k;curr_k++){
                if(curr_n==0||curr_k>(curr_n*(curr_n-1)/2)) continue;
                else if(curr_k==0||curr_k==(curr_n*(curr_n-1)/2)) dp[curr_n][curr_k] = 1;
                else{
                    long ans = ((dp[curr_n][curr_k-1]%mod)+(dp[curr_n-1][curr_k]%mod))%mod;
                    if(curr_k>=curr_n) ans = ((ans%mod)-(dp[curr_n-1][curr_k-curr_n]%mod))%mod;
                    
                    dp[curr_n][curr_k] = ans;
                }
            }
        }
        
        long ans = dp[n][k];
        int finalAns = (int)(ans%mod);
        if(finalAns<0) finalAns+=(int)mod;
        return finalAns;
    }
}

// Method - 4 : DP , tabulation , linear space

// TC : O(n*k)
// SC : O(k)

class Solution {
    public int kInversePairs(int n, int k) {
        long mod = (long)1e9+7;
        long dp[] = new long[k+1];
        
        for(int curr_n=0;curr_n<=n;curr_n++){
            long nextDp[] = new long[k+1];
            
            for(int curr_k=0;curr_k<=k;curr_k++){
                if(curr_n==0||curr_k>(curr_n*(curr_n-1)/2)) continue;
                else if(curr_k==0||curr_k==(curr_n*(curr_n-1)/2)) nextDp[curr_k] = 1;
                else{
                    long ans = ((nextDp[curr_k-1]%mod)+(dp[curr_k]%mod))%mod;
                    if(curr_k>=curr_n) ans = ((ans%mod)-(dp[curr_k-curr_n]%mod))%mod;
                    
                    nextDp[curr_k] = ans;
                }
            }
            
            dp = nextDp;
        }
        
        long ans = dp[k];
        int finalAns = (int)(ans%mod);
        if(finalAns<0) finalAns+=(int)mod;
        return finalAns;
    }
}