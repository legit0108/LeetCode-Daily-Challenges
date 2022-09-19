// TC : O(sum(paths[idx].length()))
// SC : O(sum(paths[idx].length()))

class Solution {
    public List<List<String>> findDuplicate(String[] paths) {
        HashMap<String, List<String>> map = new HashMap();
        
        for(String path: paths){
            String arr[] = path.split(" ");
            String root = arr[0];
            int len = arr.length;
            
            for(int idx = 1; idx<len; idx++){
                String pair[] = generatePair(arr[idx]);
                String fileName = pair[0];
                String content = pair[1];
                
                if(!map.containsKey(content)) map.put(content,new ArrayList());
                map.get(content).add(root + "/" + fileName);
            }
        }
        
        List<List<String>> duplicatePaths = new ArrayList();
        for(String content: map.keySet()){
            List<String> list = map.get(content);
            int size = list.size();
            
            if(size >= 2){
                duplicatePaths.add(list); 
            }
        }
        
        return duplicatePaths;
    }
    
    private String[] generatePair(String str){
        String pair[] = str.split("\\(");
        pair[1] = pair[1].substring(0, pair[1].length()-1);
        
        return pair; 
    }
}