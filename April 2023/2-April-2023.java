/* 
  Solution-1: Sort + Binary Search
  Idea: If a potion is valid for some spell, all potions greater than or equal to that potion are also valid potions  

  TC: O(totalPotions*log(totalPotions) + totalSpells*log(totalPotions))
  SC: O(1)
*/

class Solution {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int totalSpells = spells.length;
        int totalPotions = potions.length;
        int[] pairs = new int[totalSpells];
        
        Arrays.sort(potions);
        
        for(int index=0; index<totalSpells; index++){
            pairs[index] = getCount(potions, totalPotions, spells[index], success);
        }
        
        return pairs;
    }
    
    private int getCount(int[] arr, int len, long val, long leastProduct){ // get count of all index in arr such that arr[index]*val>=leastProduct
        int low = 0;
        int high = len-1;
        int index = len;
        
        while(low<=high){
            int mid = low + (high-low)/2;
            long product = (long)arr[mid]*val;
            
            if(product>=leastProduct){
                index = mid;
                high = mid-1;
            }else low = mid+1;
        }
        
        int count = len-index;
        return count;
    }
}


// -----X-----X-----X-----X-----X-----X-----X-----


/* 
  Solution-2: Sort + Two pointers
  Ideas: 
  -> If a potion is valid for some spell, all potions greater than or equal to that potion are also valid potions  
  -> If spells are processed in sorted order then all potions for previously iterated spells are also valid for the current spell

  TC: O(totalPotions*log(totalPotions) + totalSpells*log(totalSpells))
  SC: O(totalSpells)
*/

class Solution {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int totalSpells = spells.length;
        int totalPotions = potions.length;
        Integer[] indices = new Integer[totalSpells];
        
        for(int index=0; index<totalSpells; index++) indices[index] = index;
        Arrays.sort(indices, (index1, index2)->Integer.compare(spells[index2], spells[index1]));
        Arrays.sort(potions);
        
        int[] pairs = new int[totalSpells];
        int lastValidIndex = 0;
        
        for(int index: indices){
            long spell = spells[index];
            
            while(lastValidIndex<totalPotions){
                long product = spell*(long)potions[lastValidIndex];
                if(product>=success) break;
                
                lastValidIndex++;
            }
            
            pairs[index] = totalPotions-lastValidIndex;
        }
        
        return pairs;
    }
}


// -----X-----X-----X-----X-----X-----X-----X-----


/* 
  Solution-3: Prefix Sum
  Idea: If a potion is valid for some spell, it is valid for all spells greater than that spell

  TC: O(potions.length + spells.length + maxSpell)
  SC: O(maxSpell)
*/

class Solution {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int maxSpell = 0;
        for(int spell: spells) maxSpell = Math.max(maxSpell, spell);
        
        int[] prefixSum = new int[maxSpell+1];
        for(long potion: potions){
            long leastSpell = (success+potion-1l)/potion;
            if(leastSpell<=maxSpell) prefixSum[(int)leastSpell]++;
        }
        
        for(int index=1; index<=maxSpell; index++) prefixSum[index]+=prefixSum[index-1];
        
        int len = spells.length;
        int[] pairs = new int[len];
        
        for(int index=0; index<len; index++) pairs[index] = prefixSum[spells[index]];
        
        return pairs;
    }
}