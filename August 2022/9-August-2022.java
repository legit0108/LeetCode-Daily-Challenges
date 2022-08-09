// TC : O(len*len)
// SC : O(len)

class Solution {
    public int numFactoredBinaryTrees(int[] arr) {
        Arrays.sort(arr);
        int len = arr.length;
        long ways = 0;
        long mod = (long)1e9+7;
        HashMap<Long,Long> dp = new HashMap();
        
        for(int rootIdx=0;rootIdx<len;rootIdx++){
            long root = (long)arr[rootIdx];
            dp.put(root,1l);
            
            for(int childIdx = 0;childIdx<rootIdx;childIdx++){
                long div = arr[childIdx];
                
                if(root%div==0){
                    long factor1 = div;
                    long factor2 = root/div;
                    long currWays = ((dp.getOrDefault(factor1,0l)%mod)*(dp.getOrDefault(factor2,0l)%mod))%mod;
                    
                    dp.put(root,dp.get(root)+currWays);
                }
            }
            
            ways = ((ways%mod) + (dp.get(root)%mod))%mod;
        }
        
        int finalAns = (int)(ways%mod);
        if(finalAns<0) finalAns+=(int)mod;
        return finalAns;
    }
}