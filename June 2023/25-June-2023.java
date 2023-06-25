class Solution {
    public int countRoutes(int[] locations, int start, int finish, int fuel) {
        long mod = (long)1e9+7;
        int len = locations.length;
        Long[][] dp = new Long[len][fuel+1];
        long routes = getTotalRoutes(locations, start, finish, fuel, len, dp, mod);
        
        int ans = (int)((routes+mod)%mod);
        if(ans<0) ans+=(int)mod;
        
        return ans;
    }
    
    private long getTotalRoutes(int[] locations, int position, int finish, int fuel, int len, Long[][] dp, long mod){
        if(dp[position][fuel]!=null) return dp[position][fuel];
        
        long routes = (position==finish)?1l:0l;
        
        for(int index=0; index<len; index++){
            if(index!=position){
                int reqdFuel = Math.abs(locations[index]-locations[position]);
                
                if(fuel>=reqdFuel) routes = ((routes+getTotalRoutes(locations, index, finish, fuel-reqdFuel, len, dp, mod))+mod)%mod;
            }
        }
        
        return dp[position][fuel] = routes;
    }
}