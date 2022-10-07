// Solution 1: TreeMap + Line Sweep

// TC: O(size*log(size)) for each book
// SC: O(size)
// where size is size of map

class MyCalendarThree {
    TreeMap<Integer, Integer> map;
    public MyCalendarThree() {
        map = new TreeMap();
    }
    
    public int book(int start, int end) {
        map.put(start, map.getOrDefault(start, 0)+1);
        map.put(end, map.getOrDefault(end, 0)-1);
        
        int maxKBooking = -1;
        int events = 0;
        
        for(int time: map.keySet()){
            events+=map.get(time);
            maxKBooking = Math.max(maxKBooking, events);
        }
        
        return maxKBooking;
    }
}

// Solution 2: Segment Tree + Lazy (no lazy propagation)

// TC: O(log(1e9)) for each book
// SC: O(log(1e9))

class MyCalendarThree {
    private HashMap<Integer, Integer> tree; // max for range
    private HashMap<Integer, Integer> lazy; // for a range how many times that range was booked
    
    public MyCalendarThree() {
        tree = new HashMap();
        lazy = new HashMap();
    }
    
    public int book(int start, int end) {
        update(1, start, end-1, 0, (int)1e9);
        return tree.get(1);
    }
    
    private void update(int node, int start, int end, int low, int high){
        if(start>high || end<low) return;
        if(low>=start && high<=end){
            tree.put(node, tree.getOrDefault(node, 0)+1);
            lazy.put(node, lazy.getOrDefault(node, 0)+1);
        }else{
            int mid = low + (high-low)/2;
            int leftNode = node*2;
            int rightNode = node*2+1;
            
            update(leftNode, start, end, low, mid);
            update(rightNode, start, end, mid+1, high);
            
            int maxFromChildren = Math.max(tree.getOrDefault(leftNode, 0), tree.getOrDefault(rightNode, 0));
            int currMax = lazy.getOrDefault(node, 0) +  maxFromChildren;
            tree.put(node, currMax);
        }
    }
}