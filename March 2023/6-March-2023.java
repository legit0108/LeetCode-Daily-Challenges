// Solution-1: Linear Search 
// Every number smaller than k present in arr pushes k one step further

// TC: O(len)
// SC: O(1)

class Solution {
    public int findKthPositive(int[] arr, int k) {
        for(int val: arr){
            if(val<=k) k++;
            else break;
        }
        
        return k;
    }
}


// Solution-2: Binary Search
// Find kth missing positive by converging low and high

// TC: O(log(high-low)*log(len))
// SC: O(1)

class Solution {
    public int findKthPositive(int[] arr, int k) {
        int len = arr.length;
        int low = 1;
        int high = arr[len-1]+k;
        
        while(low<high){
            int mid = low + (high-low)/2;
            
            int idx = find(arr, mid, len);
            
            int count = 0;
            if(idx==-1) count = mid;
            else{
                int val = arr[idx];
                count = (val-(idx+1)) + (mid-val); // missing numbers < val + missing numbers between mid and val
            }
            
            if(count>=k) high = mid;
            else low = mid+1;
        }
        
        return low;
    }
    
    private int find(int[] arr, int val, int len){ // find idx such that arr[idx]<=val
        int low = 0;
        int high = len-1;
        int idx = -1;
        
        while(low<=high){
            int mid = low + (high-low)/2;
            
            if(arr[mid]<=val){
                idx = mid;
                low = mid+1;
            }else high = mid-1;
        }
        
        return idx;
    }
}


// Solution-3: Binary Search

// TC: O(log(len))
// SC: O(1)

class Solution {
    public int findKthPositive(int[] arr, int k) {
        int len = arr.length;
        int low = 0;
        int high = len;
        
        while(low<high){
            int mid = low + (high-low)/2;
            
            int count = arr[mid]-(mid+1); // count of missing numbers < arr[mid]
            
            if(count>=k) high = mid;
            else low = mid+1;
        }
        
        /*
        Explanation on why low + k:
        We use binary search to find the smallest index, low, such that there are more than k missing numbers in [0, A[low]]. 
        The actual number of missing numbers in [0, A[low-1]] is A[low-1] - (low - 1) - 1 = A[low-1] - low. 
        Counting from A[low-1], The k-th missing number is therefore (A[low-1] + k - (A[low-1] - low) = low + k
        */
        
        return low+k;
    }
}