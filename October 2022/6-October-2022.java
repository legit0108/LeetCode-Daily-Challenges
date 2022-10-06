// Solution-1: HashMap + TreeMap

// TC:
// set: O(log(size))
// get: O(log(size))

// SC: O(size)

class TimeMap {
    private HashMap<String, TreeMap<Integer, String>> map;
    
    public TimeMap() {
       map = new HashMap(); 
    }
    
    public void set(String key, String value, int timestamp) {
       if(map.containsKey(key)==false) map.put(key, new TreeMap<Integer,String>());
       map.get(key).put(timestamp, value); 
    }
    
    public String get(String key, int timestamp) {
       TreeMap<Integer, String> treeMap = map.get(key);
       if(treeMap==null) return "";
       
       Integer timestamp_prev = treeMap.floorKey(timestamp);
       if(timestamp_prev==null) return "";
       
       String value = treeMap.get(timestamp_prev); 
       if(value==null) return "";
       return value;
    }
}

// Solution-2: HashMap + Binary Search

// TC:
// set: O(1)
// get: O(log(size))

// SC: O(size)

class TimeMap {
    private HashMap<String, List<Pair>> map;
    
    public TimeMap() {
       map = new HashMap(); 
    }
    
    public void set(String key, String value, int timestamp) {
       if(map.containsKey(key)==false) map.put(key, new ArrayList());
       map.get(key).add(new Pair(timestamp, value));
    }
    
    public String get(String key, int timestamp) {
       List<Pair> list = map.get(key);
       if(list==null) return "";
       
       int idx = binarySearch(list, timestamp);
       if(idx==-1) return "";
       return list.get(idx).value; 
    }
    
    private int binarySearch(List<Pair> list, int timestamp){
       int low = 0;
       int high = list.size()-1;
       int idx = -1;
        
       while(low<=high){
           int mid = low + (high-low)/2;
           
           Pair pair = list.get(mid);
           int currTimeStamp = pair.timestamp;
           
           if(currTimeStamp<=timestamp){
               idx = mid;
               low = mid+1;
           }else high = mid-1;
       }
       
       return idx;
    }
    
    private class Pair{
       private int timestamp;
       private String value;
        
       private Pair(){
           
       } 
       
       private Pair(int timestamp, String value){
          this.timestamp = timestamp;
          this.value = value; 
       } 
    }
}