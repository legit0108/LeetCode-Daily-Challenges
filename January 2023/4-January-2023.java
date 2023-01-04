/*
 Tasks of a particular difficulty will be of form 3n, 3n+1, 3n+2
 if tasks are of form 3n+1 with n = 0 or there exists only 1 task with particular difficulty we return -1
 else we can complete tasks of a particular difficulty in ceil(totalTasks/3) rounds
 note that this will be minimum because we will perform tasks 3 at a time, thus minimizing rounds
*/

// TC: O(tasks.length)
// SC: O(tasks.length)

class Solution {
    public int minimumRounds(int[] tasks) {
        HashMap<Integer, Integer> map = new HashMap();
        
        for(int task: tasks){
            map.put(task, map.getOrDefault(task, 0)+1);
        }
        
        int minRounds = 0;
        
        for(int difficulty: map.keySet()){
            int totalTasks = map.get(difficulty);
            
            if(totalTasks==1) return -1;
            
            int rounds = (totalTasks+3-1)/3; // ceil(a/b) = (a+b-1)/b
            minRounds+=rounds;
        }
        
        return minRounds;
    }
}