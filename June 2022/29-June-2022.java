// Sort people on basis of descending order of height
// if height is same sort them on basis of ascending order of
// people required in front of them
// greedily form result by inserting people 
// at index equal to number of people required in front of them

// TC : O(len*len)
// SC : O(len)

class Solution {
    public int[][] reconstructQueue(int[][] people) {
         Arrays.sort(people,(people1,people2)->
         ((people1[0]==people2[0])?
         Integer.compare(people1[1],people2[1])
         :Integer.compare(people2[0],people1[0])));
         
         List<int[]> result = new ArrayList();            
         int len = people.length;
                     
         for(int idx=0;idx<len;idx++){
             int idxToAdd = people[idx][1];
             result.add(idxToAdd,people[idx]);
         }
         
         return result.toArray(new int[result.size()][2]);            
    }
}