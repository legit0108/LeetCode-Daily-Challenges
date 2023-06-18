// TC: O(nmlogn)
// SC: O(nm)

class Solution {
    public int makeArrayIncreasing(int[] arr1, int[] arr2) {
        HashMap<String, Integer> dp = new HashMap();
        Arrays.sort(arr2);
        
        int minOps = makeArrayIncreasing(0, -1, arr1, arr2, dp);
        
        if(minOps==(int)1e9) return -1;
        else return minOps;
    }
    
    private int makeArrayIncreasing(int index, int prev, int[] arr1, int[] arr2, HashMap<String, Integer> dp){
        if(index==arr1.length) return 0;
        
        String key = index+","+prev;
        if(dp.get(key)!=null) return dp.get(key);
        
        int minOps = (int)1e9;
        int val = arr1[index];
        
        if(val>prev) minOps = makeArrayIncreasing(index+1, val, arr1, arr2, dp);
        
        int pivotIndex = binarySearch(prev, arr2);
        if(pivotIndex!=-1) minOps = Math.min(minOps, 1+makeArrayIncreasing(index+1, arr2[pivotIndex], arr1, arr2, dp));
        
        dp.put(key, minOps);
        return minOps;
    }
    
    private int binarySearch(int val, int[] arr){
        int low = 0;
        int high = arr.length-1;
        int pivotIndex = -1;
        
        while(low<=high){
            int mid = low + (high-low)/2;
            
            if(arr[mid]>val){
                pivotIndex = mid;
                high = mid-1;
            }else low = mid+1;
        }
        
        return pivotIndex;
    }
}