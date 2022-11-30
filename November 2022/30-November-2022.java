// Solution - 1 : Map + Set

// TC : O(arr.length)
// SC : O(arr.length)

class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap();
        
        for(int val : arr){ 
            map.put(val, map.getOrDefault(val, 0)+1);
        }
        
        HashSet<Integer> set = new HashSet();
        
        for(int val : map.keySet()){
            int freq = map.get(val);
            
            if(set.contains(freq)) return false;
            else set.add(freq);
        }
        
        return true;
    }
}

// Solution - 2 : Solution - 1 but using arrays

// TC : O(maxLimit)
// SC : O(maxLimit)

class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        int maxLimit = 1000;
        int map[] = new int[2*maxLimit+1];
        
        for(int val : arr){ 
            map[val+maxLimit]++;
        }
        
        boolean set[] = new boolean[maxLimit+1];
        
        for(int freq : map){
            if(freq>0){
                if(set[freq]) return false;
                else set[freq] = true;
            }
        }
        
        return true;
    }
}