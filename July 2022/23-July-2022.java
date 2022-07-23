// TC : O(len*log(len))
// SC : O(len)

// Method - 1 : Fenwick tree / binary indexed tree

class Solution {
    public List<Integer> countSmaller(int[] nums) {
        int len = nums.length;
        List<Integer> ans = new ArrayList();
        HashSet<Integer> set = new HashSet();
        HashMap<Integer,Integer> numToIdx = new HashMap();
        int size;
        List<Integer> numsClone;
        int fenArr[];
        
        for(int idx=0;idx<len;idx++){
            set.add(nums[idx]);
        }
        
        numsClone = new ArrayList(set);
        Collections.sort(numsClone);
        size = numsClone.size();
        fenArr = new int[size+1];
        
        for(int idx=0;idx<size;idx++){
           numToIdx.put(numsClone.get(idx),idx+1); 
        }
        
        for(int currIdx=len-1;currIdx>=0;currIdx--){
           int idx = numToIdx.get(nums[currIdx]);
           int count = query(fenArr,idx-1,size);
           ans.add(count);
           update(fenArr,idx,size); 
        }
        
        Collections.reverse(ans);
        return ans;
    }
    
    private int query(int fenArr[],int num,int size){
        int ans = 0;
        
        while(num>0){
            ans+=fenArr[num];
            num-=(num&(-num));
        }
        
        return ans;
    }
    
    private void update(int fenArr[],int num,int size){
        while(num<size){
            fenArr[num]++;
            num+=(num&(-num));
        }
    }
}

// Method - 2 : Segment tree

class Solution {
    public List<Integer> countSmaller(int[] nums) {
        int len = nums.length;
        List<Integer> ans = new ArrayList();
        HashSet<Integer> set = new HashSet();
        HashMap<Integer,Integer> numToIdx = new HashMap();
        int size;
        List<Integer> numsClone;
        int tree[];
        
        for(int idx=0;idx<len;idx++){
            set.add(nums[idx]);
        }
        
        numsClone = new ArrayList(set);
        Collections.sort(numsClone);
        size = numsClone.size();
        tree = new int[4*size];
        
        for(int idx=0;idx<size;idx++){
           numToIdx.put(numsClone.get(idx),idx+1); 
        }
        
        for(int currIdx=len-1;currIdx>=0;currIdx--){
           int idx = numToIdx.get(nums[currIdx]);
           int count = query(tree,1,1,size,1,idx-1);
           ans.add(count);
           update(tree,1,1,size,idx); 
        }
        
        Collections.reverse(ans);
        return ans;
    }
    
    private int query(int tree[],int node,int start,int end,int low,int high){
        if(high<start||end<low) return 0;
        if(start>=low&&end<=high) return tree[node];
        int mid = start + (end-start)/2;
        int queryLeft = query(tree,node*2,start,mid,low,high);
        int queryRight = query(tree,node*2+1,mid+1,end,low,high);
        
        return queryLeft+queryRight;
    }
    
    private void update(int tree[],int currNode,int start,int end,int num){
        if(start>end) return;
        if(start==end){
            tree[currNode]++;
            return;
        }
        
        int mid = start + (end-start)/2;
        if(num<=mid) update(tree,currNode*2,start,mid,num);
        else update(tree,currNode*2+1,mid+1,end,num);
        
        tree[currNode] = tree[currNode*2]+tree[currNode*2+1];
    }
}