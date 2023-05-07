// Solution-1: Solve LIS in nlogn using Binary Search, n = length of array

// TC: O(nlogn)
// SC: O(n), can be brought down to O(1) by modifying input

class Solution {
    public int[] longestObstacleCourseAtEachPosition(int[] obstacles) {
        int len = obstacles.length;
        int[] ans = new int[len];
        
        List<Integer> lis = new ArrayList();
        
        for(int index=0; index<len; index++){
            int obstacle = obstacles[index];
            int searchIndex = binarySearch(obstacle, lis, lis.size());
            
            ans[index] = searchIndex+1;
            
            if(searchIndex<lis.size()) lis.set(searchIndex, obstacle);
            else lis.add(obstacle);
        }
        
        return ans;
    }
    
    private int binarySearch(int val, List<Integer> list, int size){
        int low = 0;
        int high = size-1;
        int searchIndex = size;
        
        while(low<=high){
            int mid = low + (high-low)/2;
            
            if(((int)list.get(mid))>val){
                searchIndex = mid;
                high = mid-1;
            }else low = mid+1;
        }
        
        return searchIndex;
    }
}


// -----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----


// Solution-2: Range max using segment tree

// TC: O(nlogn)
// SC: O(n)

class Solution {
    public int[] longestObstacleCourseAtEachPosition(int[] obstacles) {
        obstacles = coordinateCompression(obstacles);
        SegmentTree tree = new SegmentTree(getMax(obstacles)+1);
        
        int len = obstacles.length;
        int[] ans = new int[len];
        
        for(int index=0; index<len; index++){
            int obstacle = obstacles[index];
            
            ans[index] = 1+tree.get(obstacle);
            
            tree.update(obstacle, ans[index]);
        }
        
        return ans;
    }
    
    private int[] coordinateCompression(int[] arr){
        int len = arr.length;
        int[] clonedArr = new int[len];
        
        for(int index=0; index<len; index++) clonedArr[index] = arr[index];
        
        Arrays.sort(clonedArr);
        HashMap<Integer, Integer> map = new HashMap();
        int id = 0;
        
        for(int index=0; index<len; index++){
            if(index>0 && clonedArr[index]==clonedArr[index-1]) continue;
            else map.put(clonedArr[index], id++);
        }
        
        int[] compressedArr = new int[len];
        for(int index=0; index<len; index++) compressedArr[index] = map.get(arr[index]);
        
        return compressedArr;
    }
    
    private int getMax(int[] arr){
        int max = 0;
        
        for(int val: arr) max = Math.max(max, val);
        
        return max;
    }
    
    private class SegmentTree{
        int[] tree;
        int len;
        
        SegmentTree(int len){
            tree = new int[4*len];
            this.len = len;
        }
        
        void update(int index, int val){
            update(1, 0, len-1, index, val);
        }
        
        void update(int node, int start, int end, int index, int val){
            if(start==end) tree[node]=val;
            else{
                int mid = start + (end-start)/2;
                
                if(index<=mid){
                    update(node*2, start, mid, index, val);
                }else update(node*2+1, mid+1, end, index, val);
                
                tree[node] = Math.max(tree[node*2], tree[node*2+1]);
            }
        }
        
        int get(int num){
            return query(1, 0, len-1, 0, num);
        }
        
        int query(int node, int start, int end, int left, int right){
            if(end<left || start>right) return 0;
            else if(start>=left && end<=right) return tree[node];
            else{
                int mid = start + (end-start)/2;
                
                return Math.max(query(node*2, start, mid, left, right), query(node*2+1, mid+1, end, left, right));
            }
        }
    }
}


// -----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----


// Solution-3: Range max using BIT
// Normally we cannot solve range max/min with BIT, 
// but we can solve range max/min with BIT for queries in the range [1...n] instead of [left...right] provided maximum of a range always increases, minimum always decreases
// We look at maximum of a prefix here, which is monotonic, so we can solve this problem using BIT

// TC: O(nlogn)
// SC: O(n)

class Solution {
    public int[] longestObstacleCourseAtEachPosition(int[] obstacles) {
        FenwickTree tree = new FenwickTree(getMax(obstacles)+1);
        
        int len = obstacles.length;
        int[] ans = new int[len];
        
        for(int index=0; index<len; index++){
            int obstacle = obstacles[index];
            
            ans[index] = 1+tree.get(obstacle);
            
            tree.update(obstacle, ans[index]);
        }
        
        return ans;
    }
    
    private int getMax(int[] arr){
        int max = 0;
        
        for(int val: arr) max = Math.max(max, val);
        
        return max;
    }
    
    private class FenwickTree{
        int[] farr;
        int len;
        
        FenwickTree(int n){
            this.len = n+1;
            farr = new int[len];
        }
        
        int get(int index){
            int max = 0;
            
            while(index>0){
                max=Math.max(max, farr[index]);
                index-=getLsb(index);
            }
            
            return max;
        }
        
        void update(int index, int val){
            while(index<len){
                farr[index]=Math.max(farr[index], val);
                index+=getLsb(index);
            }
        }
        
        int getLsb(int num){
            return num&(-num);
        }
    }
}