// For a judge, outdegree is 0 and indegree is n-1, 
// We can implement this using indegree, outdegree arrays or just one degree array

// TC: O(trust.length + n)
// SC: O(n)

class Solution {
    public int findJudge(int n, int[][] trust) {
        int[] degree = new int[n+1];
        
        for(int[] pair: trust){
            int person1 = pair[0];
            int person2 = pair[1];
            
            degree[person1]--;
            degree[person2]++;
        }
        
        for(int person=1; person<=n; person++){
            if(degree[person] == n-1) return person;
        }
        
        return -1;
    }
}