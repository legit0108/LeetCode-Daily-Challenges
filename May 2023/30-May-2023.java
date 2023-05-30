// Solution-1: Use boolean array
// The set works in O(1) always but this is not scalable, does not conform with IRL scenarios

class MyHashSet {
    private boolean[] set;
    private int max;
    
    public MyHashSet() {
        max = (int)1e6;
        set = new boolean[max+1];
    }
    
    public void add(int key) {
        if(!contains(key)) set[key] = true;
    }
    
    public void remove(int key) {
        if(contains(key)) set[key] = false;
    }
    
    public boolean contains(int key) {
        return set[key]==true;
    }
    
}


// Solution-2: Proper hashset implementation
// The set works in O(1) average time

 class MyHashSet {
    private double loadFactor;
    private int count;
    private int capacity;
    private List<Integer>[] buckets;
    
    public MyHashSet() {
        capacity = 1000;
        loadFactor = 10;
        init();
    }
    
    public void add(int key) {
        if(contains(key)) return;
        
        double factor = count/capacity;
        if(factor>loadFactor) reHash();
        
        int bucketIndex = key%capacity;
        buckets[bucketIndex].add(key);
        count++;
    }
    
    public void remove(int key) {
        if(!contains(key)) return;
        
        int bucketIndex = key%capacity;
        int index = getIndexWithinBucket(bucketIndex, key);
        buckets[bucketIndex].remove(index);
        count--;
    }
    
    public boolean contains(int key) {
        int bucketIndex = key%capacity;
        
        int index = getIndexWithinBucket(bucketIndex, key);
        return index!=-1;
    }
    
    private void reHash(){
        List<Integer>[] oldBuckets = buckets;
        capacity = capacity*2;
        count = 0;
        init();
        
        for(List<Integer> bucket: oldBuckets){
            for(int key: bucket) add(key);
        }
    }
    
    private int getIndexWithinBucket(int bucketIndex, int key){
        List<Integer> bucket = buckets[bucketIndex];
        
        for(int index=0; index<bucket.size(); index++){
            if(bucket.get(index)==key) return index;
        }
        
        return -1;
    }
    
    private void init(){
        buckets = new ArrayList[capacity];
        
        for(int bucketIndex=0; bucketIndex<capacity; bucketIndex++) buckets[bucketIndex] = new ArrayList();
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */