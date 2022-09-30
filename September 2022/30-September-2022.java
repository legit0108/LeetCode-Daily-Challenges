// TC : O(len*log(len))
// SC : O(len)

class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<int[]> modifiedBuildings = new ArrayList();
        int len = buildings.length;
        
        for(int idx=0; idx<len; idx++){
            int left = buildings[idx][0];
            int right = buildings[idx][1];
            int height = buildings[idx][2];
            
            modifiedBuildings.add(new int[]{left, -height});
            modifiedBuildings.add(new int[]{right, height});
        }
        
        Collections.sort(modifiedBuildings, 
                        (modifiedBuilding1, modifiedBuilding2)->(
                        (modifiedBuilding1[0] == modifiedBuilding2[0])?
                        Integer.compare(modifiedBuilding1[1], modifiedBuilding2[1]):
                        Integer.compare(modifiedBuilding1[0], modifiedBuilding2[0])));
        
        int size = modifiedBuildings.size();
        List<List<Integer>> skyline = new ArrayList();
        TreeMap<Integer, Integer> map = new TreeMap();
        int currHeight = 0;
        map.put(0, 1);
        
        for(int idx=0; idx<size; idx++){
            int modifiedBuilding[] = modifiedBuildings.get(idx);
            int coordinate = modifiedBuilding[0];
            int modifiedBuildingHeight = modifiedBuilding[1];
            
            if(modifiedBuildingHeight < 0){
                map.put(-modifiedBuildingHeight, map.getOrDefault(-modifiedBuildingHeight, 0)+1);
            }else{
                map.put(modifiedBuildingHeight, map.get(modifiedBuildingHeight)-1);
                if(map.get(modifiedBuildingHeight) == 0) map.remove(modifiedBuildingHeight);
            }
            
            int currMaxHeight = map.lastKey();
            
            if(currHeight != currMaxHeight){
                List<Integer> point = new ArrayList();
                point.add(coordinate);
                point.add(currMaxHeight);
                
                currHeight = currMaxHeight;
                skyline.add(point);
            }
        }
        
        return skyline;
    }
}