// Solution - 1 : Jarvis March

// TC : O(len^2)
// SC : O(len)

class Solution {
    public int[][] outerTrees(int[][] trees) {
        int startPoint[] = trees[0];
        int len = trees.length;
        
        for(int point[] : trees){
            if(point[0]<startPoint[0]) startPoint = point;
        }
        
        int currPoint[] = startPoint;
        Set<int[]> hull = new HashSet();
        hull.add(startPoint);
        
        do{
            Set<int[]> collinearPoints = new HashSet();
            int nextPoint[] = trees[0];
            
            for(int point[] : trees){
                if(point==currPoint) continue;
                
                int inclination = getInclination(currPoint, nextPoint, point);
                
                if(inclination>0){
                    collinearPoints = new HashSet();
                    nextPoint = point;
                }else if(inclination==0){
                    int dist1 = getDist(currPoint, nextPoint);
                    int dist2 = getDist(currPoint, point);
                    
                    if(dist1>dist2){
                        collinearPoints.add(point);
                    }else{
                        collinearPoints.add(nextPoint);
                        nextPoint = point;
                    }
                }
            }
            
            for(int point[] : collinearPoints) hull.add(point);
            hull.add(nextPoint);
            
            currPoint = nextPoint;
        }while(hull.size()<len && !equal(currPoint, startPoint));
        
        return hull.toArray(new int[hull.size()][2]);
    }
    
    private int getInclination(int point1[], int point2[], int point3[]){
        int x1 = point1[0];
        int y1 = point1[1];
        int x2 = point2[0];
        int y2 = point2[1];
        int x3 = point3[0];
        int y3 = point3[1];
        
        return (y3-y1)*(x2-x1) - (y2-y1)*(x3-x1);
    }
    
    private int getDist(int point1[], int point2[]){
        int x1 = point1[0];
        int y1 = point1[1];
        int x2 = point2[0];
        int y2 = point2[1];
        
        return square(x1, x2) + square(y1, y2); 
    }
    
    private int square(int coord1, int coord2){
        return (coord2-coord1)*(coord2-coord1);
    }
    
    private boolean equal(int point1[], int point2[]){
        int x1 = point1[0];
        int y1 = point1[1];
        int x2 = point2[0];
        int y2 = point2[1];
        
        return (x1==x2) && (y1==y2);
    }
}

// Solution - 2 : Graham Scan

// TC : O(len*log(len))
// SC : O(len)


class Solution {
    public int[][] outerTrees(int[][] trees) {
        int len = trees.length;
        
        Arrays.sort(trees, (point1, point2)->
                           ((point1[0]==point2[0])?
                           Integer.compare(point1[1], point2[1])
                          :Integer.compare(point1[0], point2[0])));
        
        
        List<int[]> upperHull = new ArrayList();
        
        for(int idx=0; idx<len; idx++){
           int point[] = trees[idx];
            
           while(upperHull.size()>1 && inclination(upperHull.get(upperHull.size()-2), upperHull.get(upperHull.size()-1), point)>0)
               upperHull.remove(upperHull.size()-1);
           
           upperHull.add(point); 
        }
        
        List<int[]> lowerHull = new ArrayList();
        
        for(int idx=len-1; idx>=0; idx--){
           int point[] = trees[idx];
            
           while(lowerHull.size()>1 && inclination(lowerHull.get(lowerHull.size()-2), lowerHull.get(lowerHull.size()-1), point)>0)
               lowerHull.remove(lowerHull.size()-1);
           
           lowerHull.add(point); 
        }
        
        Set<int[]> hull = new HashSet();
        
        for(int point[] : upperHull) hull.add(point);
        for(int point[] : lowerHull) hull.add(point);
        
        return hull.toArray(new int[hull.size()][2]);
    }
    
    private int inclination(int point1[], int point2[], int point3[]){
        int x1 = point1[0];
        int y1 = point1[1];
        int x2 = point2[0];
        int y2 = point2[1];
        int x3 = point3[0];
        int y3 = point3[1];
        
        return (y3-y1)*(x2-x1) - (y2-y1)*(x3-x1);
    }
}