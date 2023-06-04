// TC: O(1) for all methods ignoring String manipulation 

class UndergroundSystem {
    private HashMap<String, Pair> map; // (duration, count)
    private HashMap<Integer, String> stationMap; 
    private HashMap<Integer, Long> timeMap;
        
    public UndergroundSystem() {
        map = new HashMap();
        stationMap = new HashMap();
        timeMap = new HashMap();
    }
    
    public void checkIn(int id, String stationName, int t) {
        stationMap.put(id, stationName);
        timeMap.put(id, (long)t);
    }
    
    public void checkOut(int id, String stationName, int t) {
        String key = getKey(stationMap.get(id), stationName);
        long duration = t-timeMap.get(id);
        
        if(!map.containsKey(key)) map.put(key, new Pair(0l, 0l));
        Pair pair = map.get(key);
        
        pair.duration+=duration;
        pair.count++;
        
        stationMap.remove(id);
        timeMap.remove(id);
    }
    
    public double getAverageTime(String startStation, String endStation) {
        String key = getKey(startStation, endStation);
        
        Pair pair = map.get(key);
        double average = pair.duration*1.0/pair.count;
        
        return average;
    }
    
    private String getKey(String startStation, String endStation){
        return startStation+","+endStation;
    }
    
    private class Pair{
        long duration;
        long count;
        
        Pair(){
            
        }
        
        Pair(long duration, long count){
            this.duration = duration;
            this.count = count;
        }
    }
}

/**
 * Your UndergroundSystem object will be instantiated and called as such:
 * UndergroundSystem obj = new UndergroundSystem();
 * obj.checkIn(id,stationName,t);
 * obj.checkOut(id,stationName,t);
 * double param_3 = obj.getAverageTime(startStation,endStation);
 */