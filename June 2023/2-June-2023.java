// TC: O(n^3)
// SC: O(n)

class Solution {
    public int maximumDetonation(int[][] bombs) {
        int totalBombs = bombs.length;
        int maxBombs = 0;
        HashMap<String, Integer> map = new HashMap();
        
        for(int[] bomb: bombs){
            String point = getPoint(bomb);
            map.put(point, map.getOrDefault(point, 0)+1);
        }
        
        for(int index=0; index<totalBombs; index++){
            maxBombs = Math.max(maxBombs, bfs(bombs, bombs[index], totalBombs, map));
        }
        
        return maxBombs;
    }
    
    private int bfs(int[][] bombs, int[] bomb, int totalBombs, HashMap<String, Integer> map){
        Queue<int[]> queue = new ArrayDeque();
        queue.add(bomb);
        HashSet<String> visited = new HashSet();
        int bombsDenoted = 0;
        
        while(queue.size()>0){
            bomb = queue.remove();
            int x1 = bomb[0];
            int y1  = bomb[1];
            int radius = bomb[2];
            
            String point = getPoint(bomb);
            if(visited.contains(point)) continue;
            
            visited.add(point);
            bombsDenoted+=map.get(point);
            
            for(int index=0; index<totalBombs; index++){
                bomb = bombs[index];
                int x2 = bomb[0];
                int y2 = bomb[1];
                
                if(getDist(x1, x2, y1, y2)<=(long)radius*(long)radius) 
                    queue.add(bomb);
            }
        }
        
        return bombsDenoted;
    }
    
    private long getDist(int x1, int x2, int y1, int y2){
        long xDiff = x1-x2;
        long yDiff = y1-y2;
        
        return xDiff*xDiff + yDiff*yDiff;
    }
    
    private String getPoint(int[] coord){
        return "("+coord[0]+","+coord[1]+")";
    }
    
    private boolean isInRange(int val, int min, int max){
        return val>=min && val<=max;
    }
}