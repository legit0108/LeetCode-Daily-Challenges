// All functions implemented in O(1)

class RandomizedSet {
    HashMap<Integer, Integer> map;
    Random random;
    List<Integer> list;
    
    public RandomizedSet() {
        map = new HashMap();
        random = new Random();
        list = new ArrayList(); 
    }
    
    public boolean insert(int val) {
        if(map.containsKey(val)) return false;
       
        int idx = getSize();
        list.add(val);
        map.put(val, idx);
        
        return true;
    }
    
    public boolean remove(int val) {
        if(!map.containsKey(val)) return false;
        
        int idx = map.get(val);
        int lastIdx = getSize()-1;
        
        if(idx!=lastIdx) swap(idx, lastIdx);
        
        map.remove(list.get(lastIdx));
        list.remove(lastIdx);
        
        return true;
    }
    
    public int getRandom() {
        int idx = random.nextInt(getSize());
        return list.get(idx);
    }
    
    private int getSize(){
        return list.size();
    }
    
    private void swap(int idx1, int idx2){
        int val = list.get(idx1);
        list.set(idx1, list.get(idx2));
        list.set(idx2, val);
        
        map.put(list.get(idx1), idx1);
        map.put(list.get(idx2), idx2);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */