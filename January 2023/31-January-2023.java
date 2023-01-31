// Solution-1: LIS-like DP
// Sort by score, if the score is the same, then sort by age, apply DP

// TC: O(len^2)
// SC: O(len)

class Solution {
    public int bestTeamScore(int[] scores, int[] ages) {
        int len = scores.length;
        Player[] players = new Player[len];
        for(int idx=0; idx<len; idx++) players[idx] = new Player(scores[idx], ages[idx]);
        
        Arrays.sort(players);
        int[] dp = new int[len];
        int highestScore = 0;
        
        for(int curr=0; curr<len; curr++){
            Player currPlayer = players[curr];
            int score = currPlayer.score;
            int age = currPlayer.age;
            int maxScore = 0;
            
            for(int prev=0; prev<curr; prev++){
                Player prevPlayer = players[prev];
                
                if(prevPlayer.age<=age) maxScore = Math.max(maxScore, dp[prev]);
            }
            
            dp[curr] = maxScore+score;
            highestScore = Math.max(highestScore, dp[curr]);
        }
        
        return highestScore;
    }
    
    private class Player implements Comparable<Player>{
        private int score;
        private int age;
        
        private Player(){
            
        }
        
        private Player(int score, int age){
            this.score = score;
            this.age = age;
        }
        
        public int compareTo(Player other){
            int score1 = this.score;
            int age1 = this.age;
            int score2 = other.score;
            int age2 = other.age;
            
            if(score1!=score2) return score1-score2;
            else return age1-age2;
        }
    } 
}


// Solution-2: Improve Solution-1 by using Fenwick Tree / BIT 

// TC: O(len*log(maxAge))
// SC: O(len + maxAge)

class Solution {
    public int bestTeamScore(int[] scores, int[] ages) {
        int len = scores.length;
        int maxAge = 0;
        Player[] players = new Player[len];
        
        for(int idx=0; idx<len; idx++){
            int score = scores[idx];
            int age = ages[idx];
            
            players[idx] = new Player(score, age);
            maxAge = Math.max(maxAge, age);
        }
        
        Arrays.sort(players);
        FenwickTree tree = new FenwickTree(maxAge+1);
        int highestScore = 0;
        
        for(int curr=0; curr<len; curr++){
            Player currPlayer = players[curr];
            int score = currPlayer.score;
            int age = currPlayer.age;
            
            int maxScore = tree.query(age)+score;
            highestScore = Math.max(highestScore, maxScore);
            tree.update(age, maxScore);
        }
        
        return highestScore;
    }
    
    private class Player implements Comparable<Player>{
        private int score;
        private int age;
        
        private Player(){
            
        }
        
        private Player(int score, int age){
            this.score = score;
            this.age = age;
        }
        
        public int compareTo(Player other){
            int score1 = this.score;
            int age1 = this.age;
            int score2 = other.score;
            int age2 = other.age;
            
            if(score1!=score2) return score1-score2;
            else return age1-age2;
        }
    }
    
    private class FenwickTree{
        private int[] arr;
        
        private FenwickTree(int size){
            arr = new int[size];
        }
        
        private int query(int num){
            int max = 0;

            while(num>0){
                max=Math.max(max, arr[num]);
                num-=num&(-num);
            }

            return max;
        }

        private void update(int num, int curr){
            while(num<arr.length){
                arr[num] = Math.max(arr[num], curr);
                num+=num&(-num);
            }
        }
    }
}


// Solution-3: TreeMap + DP

// TC: O(len*log(len))
// SC: O(len)

class Solution {
    public int bestTeamScore(int[] scores, int[] ages) {
        int len = scores.length;
        Player[] players = new Player[len];
        for(int idx=0; idx<len; idx++) players[idx] = new Player(scores[idx], ages[idx]);
        
        Arrays.sort(players);
        TreeMap<Integer, Integer> dp = new TreeMap(); // Key: Individual score, Value: Highest team score with a score of individual player <= key
        int highestScore = 0;
        
        for(Player player: players){
            int score = player.score;
            int age = player.age;
            int currTeamScore = score;
            Integer floorKey = dp.floorKey(score);
            
            if(floorKey!=null){
                currTeamScore+=dp.get(floorKey);
                
                while(dp.ceilingKey(score)!=null){ // Remove teams with a higher score of individual players, but a lesser overall team score 
                    Integer ceilKey = dp.ceilingKey(score);
                    int teamScore = dp.get(ceilKey);
                    
                    if(teamScore<=currTeamScore) dp.remove(ceilKey);
                    else break;
                }
            }
            
            highestScore = Math.max(highestScore, currTeamScore);
            dp.put(score, currTeamScore);
        }
        
        return highestScore;
    }
    
    private class Player implements Comparable<Player>{
        private int score;
        private int age;
        
        private Player(){
            
        }
        
        private Player(int score, int age){
            this.score = score;
            this.age = age;
        }
        
        public int compareTo(Player other){
            int score1 = this.score;
            int age1 = this.age;
            int score2 = other.score;
            int age2 = other.age;
            
            if(age1!=age2) return age1-age2;
            else return score1-score2;
        }
    } 
}