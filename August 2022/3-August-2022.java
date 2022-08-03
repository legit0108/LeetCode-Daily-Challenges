// The event which starts before end should end before start

// TC : O(log(len)) for book
// SC : O(len) for map
// where len is size of map

class MyCalendar {
    private TreeMap<Integer,Integer> map;
    
    public MyCalendar() {
        map = new TreeMap();
    }
    
    public boolean book(int start, int end) {
        end--;
        
        Integer prevEventStart = map.floorKey(end);
        if(prevEventStart!=null){
            Integer prevEventEnd = map.get(prevEventStart);
            if(prevEventEnd>=start) return false;
        }
        
        map.put(start,end);
        return true;
    }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */