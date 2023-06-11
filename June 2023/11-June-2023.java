// TC: O(log(n))
// SC: O(1)

class SnapshotArray {
    private HashMap<Integer, List<Pair>> map;
    private int length;
    private int snap_count;
    
    public SnapshotArray(int length) {
        map = new HashMap();
        this.length = length;
        
        for(int index=0; index<length; index++){
            List<Pair> list = new ArrayList();
            list.add(new Pair(0, 0));
            map.put(index, list);
        }
    }
    
    public void set(int index, int val) {
        List<Pair> list = map.get(index);
        int snap_count = get_snap_count();
        
        if(list.get(list.size()-1).snap_id!=snap_count){
            if(list.get(list.size()-1).val!=val) list.add(new Pair(snap_count, val));
        }else list.get(list.size()-1).val = val;
    }

    public int snap() {
        set_snap_count();
        return get_snap_count()-1;
    }
   
    public int get(int index, int snap_id) {
        List<Pair> list = map.get(index);
        
        return binarySearch(list, snap_id);
    }
    
    private int get_snap_count(){
        return snap_count;
    }
    
    private void set_snap_count(){
        snap_count++;
    }
    
    private int binarySearch(List<Pair> list, int snap_id){
        int low = 0;
        int high = list.size()-1;
        int val = -1;
        
        while(low<=high){
            int mid = low + (high-low)/2;
            int curr_val = list.get(mid).val;
                
            if(list.get(mid).snap_id<=snap_id){
                val = curr_val;
                low = mid+1;
            }else high = mid-1;
        }
        
        return val;
    }
    
    private class Pair{
        int snap_id;
        int val;
        
        Pair(int snap_id, int val){
            this.snap_id = snap_id;
            this.val = val;
        }
    }
}

/**
 * Your SnapshotArray object will be instantiated and called as such:
 * SnapshotArray obj = new SnapshotArray(length);
 * obj.set(index,val);
 * int param_2 = obj.snap();
 * int param_3 = obj.get(index,snap_id);
 */