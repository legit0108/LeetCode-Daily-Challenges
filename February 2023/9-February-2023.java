// Solution-1

// TC: O(ideas.length*(26*26+name.length()))
// SC: O(ideas.length*name.length())

class Solution {
    public long distinctNames(String[] ideas) {
        HashMap<Character, HashSet<String>> map = new HashMap();
        
        for(String name: ideas){
            char firstCh = name.charAt(0);
            String substr = name.substring(1);
            
            if(!map.containsKey(firstCh)) map.put(firstCh, new HashSet());
            map.get(firstCh).add(substr);
        }
        
        long count = 0;
        
        for(char ch1: map.keySet()){
            HashSet<String> set1 = map.get(ch1);
            long size1 = set1.size();
            
            for(char ch2: map.keySet()){
                if(ch1==ch2) continue;
                
                HashSet<String> set2 = map.get(ch2);
                long size2 = set2.size();
                
                long commonNames = getCommonNames(set1, set2);
                count+=(size1-commonNames)*(size2-commonNames); // cross product of all names except same names
            }
        }
        
        return count;
    }
    
    private long getCommonNames(HashSet<String> set1, HashSet<String> set2){
        long commonNames = 0;
        
        for(String substr: set1){
            if(set2.contains(substr)) commonNames++;
        }
        
        return commonNames;
    }
}


// Solution-2

// TC: O(ideas.length*(name.length() + 26*name.length()))
// SC: O(ideas.length*name.length())

class Solution {
    public long distinctNames(String[] ideas) {
        HashSet<String> set = new HashSet();
        
        for(String name: ideas) set.add(name);
        
        int[][]map = new int[26][26]; // how many times can a character be swapped with another character
        
        for(String name: ideas){
            char firstCh = name.charAt(0);
            String substr = name.substring(1);
            
            for(char ch='a'; ch<='z'; ch++){
                String str = ch+substr;
                
                if(!set.contains(str)) map[firstCh-'a'][ch-'a']++; // firstCh can be swapped with ch map[firstCh-'a'][ch-'a'] times
            }
        }
        
        long count = 0;
        
        for(int idx1=0; idx1<26; idx1++){
            for(int idx2=0; idx2<26; idx2++) count+=map[idx1][idx2]*map[idx2][idx1]; // cross product
        }
        
        return count;
    }
}