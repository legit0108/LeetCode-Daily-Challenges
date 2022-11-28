// Solution - 1 : HashMap

// TC : O(len*log(len))
// SC : O(len)

// where len = matches.length

class Solution {
    public List<List<Integer>> findWinners(int[][] matches) {
        HashMap<Integer, Integer> map = new HashMap();
        
        for(int match[] : matches){
            int winner = match[0];
            int loser = match[1];
            
            map.put(winner, map.getOrDefault(winner, 0)+0);
            map.put(loser, map.getOrDefault(loser, 0)+1);
        }
        
        List<List<Integer>> ans = new ArrayList();
        ans.add(new ArrayList()); // lost 0 matches
        ans.add(new ArrayList()); // lost 1 match
        
        for(int player : map.keySet()){
            int lostCount = map.get(player);
            
            if(lostCount==0) ans.get(0).add(player);
            else if(lostCount==1) ans.get(1).add(player);
        }
        
        Collections.sort(ans.get(0));
        Collections.sort(ans.get(1));
        
        return ans;
    }
}

// Solution - 2 : One pass using sets

// TC : O(len*log(len))
// SC : O(len)

// where len = matches.length

class Solution {
    public List<List<Integer>> findWinners(int[][] matches) {
        Set<Integer> set0 = new TreeSet(); // lost 0 matches
        Set<Integer> set1 = new TreeSet(); // lost 1 match
        Set<Integer> losers = new HashSet(); // set of losers
        
        for(int match[] : matches){
            int winner = match[0];
            int loser = match[1];
            
            // set0
            if(!losers.contains(winner)) set0.add(winner);
            if(set0.contains(loser)) set0.remove(loser);
            
            // set1
            if(!losers.contains(loser)) set1.add(loser);
            else set1.remove(loser);
            
            losers.add(loser);
        }
        
        List<List<Integer>> ans = new ArrayList();
        ans.add(new ArrayList(set0)); 
        ans.add(new ArrayList(set1));
        
        return ans;
    }
}

// Solution - 3 : Count Sort

// TC : O(players)
// SC : O(players)

class Solution {
    public List<List<Integer>> findWinners(int[][] matches) {
        int players = (int)1e5;
        Integer map[] = new Integer[players+1];
        
        for(int match[] : matches){
            int winner = match[0];
            int loser = match[1];
            
            if(map[winner]==null) map[winner]=0;
            
            if(map[loser]==null) map[loser] = 1;
            else map[loser]++;
        }
        
        List<List<Integer>> ans = new ArrayList();
        ans.add(new ArrayList()); // lost 0 matches
        ans.add(new ArrayList()); // lost 1 match
        
        for(int player =1; player<=players; player++){
            Integer lostCount = map[player];
            
            if(lostCount!=null){
                if(lostCount==0) ans.get(0).add(player);
                else if(lostCount==1) ans.get(1).add(player);
            }
        }
        
        return ans;
    }
}