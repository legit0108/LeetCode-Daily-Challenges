// Sliding window


// Solution-1: Using HashMap

// TC: O(len)
// SC: O(1)

class Solution {
    public int totalFruit(int[] fruits) {
        int len = fruits.length;
        int start = 0;
        int end = 0;
        int maxFruits = 0;
        HashMap<Integer, Integer> map = new HashMap();
        
        while(end<len){
            while(end<len && map.size()<=2){
                maxFruits = Math.max(maxFruits, end-start);
                
                int fruit = fruits[end];
                map.put(fruit, map.getOrDefault(fruit, 0)+1);
                
                end++;
            }
            
            if(map.size()<=2) maxFruits = Math.max(maxFruits, end-start);
            
            while(map.size()>2){
                int fruit = fruits[start];
                map.put(fruit, map.get(fruit)-1);
                
                if(((int)map.get(fruit))==0) map.remove(fruit);
                
                start++;
            }
        }
        
        return maxFruits;
    }
}


// Solution-2: Simulate map using 2 variables (You only have two baskets)

// TC: O(len)
// SC: O(1)

class Solution {
    public int totalFruit(int[] fruits) {
        int len = fruits.length;
        int start = 0;
        int end = 0;
        int maxFruits = 0;
        int fruit1 = -1;
        int freq1 = 0;
        int fruit2 = -1;
        int freq2 = 0;
        int size = 0;
        boolean release = false; // true-> release, false-> acquire
        
        while(end<len){
            while(end<len && size<=2){
                maxFruits = Math.max(maxFruits, end-start);
                
                int fruit = fruits[end];
                
                if(fruit==fruit1){
                    freq1++;
                }else if(fruit==fruit2){
                    freq2++;
                }else{
                    if(fruit1==-1){
                        fruit1 = fruit;
                        freq1 = 1;
                        size++;
                    }else if(fruit2==-1){
                        fruit2 = fruit;
                        freq2 = 1;
                        size++;
                    }else{
                        release = true;
                        break;
                    }
                }
                
                end++;
            }
            
            if(size<=2) maxFruits = Math.max(maxFruits, end-start);
            
            while(release){
                int fruit = fruits[start];
                
                if(fruit==fruit1){
                    freq1--;
                    
                    if(freq1==0){
                        fruit1 = -1;
                        size--;
                        release = false;
                    }
                }else{ // fruit == fruit2
                    freq2--;
                    
                    if(freq2==0){
                        fruit2 = -1;
                        size--;
                        release = false;
                    }
                }
                
                start++;
            }
        }
        
        return maxFruits;
    }
}