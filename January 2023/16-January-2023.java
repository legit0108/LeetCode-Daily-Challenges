// Implementation-based


// Solution-1

// TC: O(len)
// SC: O(1) excluding output space

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> list = new ArrayList();
        int idx = 0;
        int len = intervals.length;
        int start1 = newInterval[0];
        int end1 = newInterval[1];
        
        while(idx<len){
            int[] interval = intervals[idx];
            int start2 = interval[0];
            int end2 = interval[1];
            
            if(start1<=end2) break;
                
            list.add(interval);
            idx++;
        }
        
        if(idx<len){
            int[] interval = intervals[idx];
            int start2 = interval[0];
            int end2 = interval[1];
            
            if(overlap(start1, start2, end1, end2)){
                list.add(new int[]{Math.min(start1, start2), Math.max(end1, end2)});
                idx++;
            }else list.add(newInterval);
            
            while(idx<len){
                interval = intervals[idx];
                int[] lastInterval = list.get(list.size()-1);
                
                start1 = lastInterval[0];
                start2 = interval[0];
                end1 = lastInterval[1];
                end2 = interval[1];
                
                if(overlap(start1, start2, end1, end2)){
                    lastInterval[1] = Math.max(end1, end2);
                }else list.add(interval);
                
                idx++;
            }
        }else list.add(newInterval);
        
        return list.toArray(new int[list.size()][2]);
    }
    
    private boolean overlap(int start1, int start2, int end1, int end2){
        int maxOfStarts = Math.max(start1, start2);
        int minOfEnds = Math.min(end1, end2);
        
        if(maxOfStarts<=minOfEnds) return true;
        else return false;
    }
}


// Solution-2

// TC: O(len)
// SC: O(1) excluding output space

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int start1 = newInterval[0];
        int end1 = newInterval[1];
        int len = intervals.length;
        int[] intervalToInsert = new int[]{start1, end1};
        List<int[]> result = new ArrayList();
        
        for(int idx=0; idx<len; idx++){
            int[] interval = intervals[idx];
            int start2 = interval[0];
            int end2 = interval[1];
            
            if(intervalToInsert==null || end2<start1) result.add(interval);
            else if(start2>end1){
                result.add(intervalToInsert);
                result.add(interval);
                
                intervalToInsert = null;
            }else{
                intervalToInsert[0] = Math.min(start1, start2);
                intervalToInsert[1] = Math.max(end1, end2);
                
                start1 = intervalToInsert[0];
                end1 = intervalToInsert[1];
            }
        }
        
        if(intervalToInsert!=null) result.add(intervalToInsert);
        return result.toArray(new int[result.size()][2]);
    }
}