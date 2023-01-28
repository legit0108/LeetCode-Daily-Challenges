/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(value);
 * int[][] param_2 = obj.getIntervals();
 */


// Solution-1: Using TreeSet

class SummaryRanges {
    private TreeSet<Integer> set;
    
    public SummaryRanges() {
        set = new TreeSet(); 
    }
    
    public void addNum(int value) { // O(log(set.size()))
        set.add(value); 
    }
    
    public int[][] getIntervals() { // O(set.size())
        List<int[]> intervals = new ArrayList();
        int start = -1;
        int end = -1;
        
        for(int num: set){
            if(end==-1){
                start = num;
                end = num;
            }else{
                if(num==end+1){
                    end = num;
                }else{
                    int[] interval = new int[]{start, end};
                    intervals.add(interval);
                    
                    start = num;
                    end  = num;
                }
            }
        }
        
        int[] interval = new int[]{start, end};
        intervals.add(interval);
        
        return intervals.toArray(new int[intervals.size()][2]);
    }
}


// Solution-2: Using HashSet

class SummaryRanges {
    private HashSet<Integer> set;
    private int min;
    private int max;
    
    public SummaryRanges() {
        set = new HashSet();
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
    }
    
    public void addNum(int value) { // O(1)
        set.add(value); 
        min = Math.min(min, value);
        max = Math.max(max, value);
    }
    
    public int[][] getIntervals() { // O(max-min)
        List<int[]> intervals = new ArrayList();
        int start = -1;
        int end = -1;
        
        for(int num=min; num<=max; num++){
            if(set.contains(num)){
                if(end==-1){
                    start = num;
                    end = num;
                }else{
                    if(num==end+1){
                        end = num;
                    }else{
                        int[] interval = new int[]{start, end};
                        intervals.add(interval);

                        start = num;
                        end  = num;
                    }
                }
            }
        }
        
        int[] interval = new int[]{start, end};
        intervals.add(interval);
        
        return intervals.toArray(new int[intervals.size()][2]);
    }
}
