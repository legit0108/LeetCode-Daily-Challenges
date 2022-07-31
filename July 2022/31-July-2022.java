// Method - 1 : Fenwick Tree / BIT

// TC : O(len) for build and O(log(len)) for query and update
// SC : O(len)

class NumArray {
    int fenwickArr[];
    int len;
    int nums[];
    
    public NumArray(int[] nums) {
        this.nums = nums;
        len = nums.length+1;
        fenwickArr = new int[len];
        int prefixSum[] = new int[len];
        
        for(int idx=1;idx<len;idx++){
            prefixSum[idx] = nums[idx-1]+prefixSum[idx-1];
        }
        
        for(int idx=1;idx<len;idx++){
            fenwickArr[idx] = prefixSum[idx] - prefixSum[idx-(idx&(-idx))];
        }
    }
    
    public void update(int index, int val) {
        int toAdd = val-nums[index];
        nums[index] = val;
        index++;
        
        while(index<len){
            fenwickArr[index]+=toAdd;
            index+=(index&(-index));
        }
    }
    
    public int sumRange(int left, int right) {
        left++;
        right++;
        
        return query(right)-query(left-1);
    }
    
    private int query(int index){
        int sum = 0;
        
        while(index>0){
            sum+=fenwickArr[index];
            index-=(index&(-index));
        }
        
        return sum;
    }
}

// Method - 2 : Segment Tree

// TC : O(len) for build and O(log(len)) for query and update
// SC : O(len)

class NumArray {
   int tree[];
   int nums[];
   int len;
   
   public NumArray(int[] nums) {
      this.nums = nums;
      len = nums.length;
      tree = new int[4*len];
      
      build(1,0,len-1); 
   }
   
   private void build(int node,int start,int end){
       if(start==end){
           tree[node] = nums[start];
           return;
       }
       
       int mid = start + (end-start)/2;
       build(node*2,start,mid);
       build(node*2+1,mid+1,end);
       
       tree[node] = tree[node*2]+tree[node*2+1];
   }
   
   public void update(int index, int val) {
       update(1,0,len-1,index,val-nums[index]);
       nums[index] = val;
   }
   
   private void update(int node,int start,int end,int index,int toAdd){
       if(start==end){
           tree[node]+=toAdd;
           return;
       }
       
       int mid = start + (end-start)/2;
       if(index<=mid) update(node*2,start,mid,index,toAdd);
       else update(node*2+1,mid+1,end,index,toAdd);
       
       tree[node] = tree[node*2]+tree[node*2+1];
   }
   
   public int sumRange(int left, int right) {
       return query(1,0,len-1,left,right);
   }
   
   private int query(int node,int start,int end,int left,int right){
       if(start>right||end<left) return 0;
       if(start>=left&&end<=right) return tree[node];
       
       int mid = start + (end-start)/2;
       return query(node*2,start,mid,left,right) + query(node*2+1,mid+1,end,left,right);
   }
}

/**
* Your NumArray object will be instantiated and called as such:
* NumArray obj = new NumArray(nums);
* obj.update(index,val);
* int param_2 = obj.sumRange(left,right);
*/