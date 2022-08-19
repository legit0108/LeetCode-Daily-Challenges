// Idea : Append current element to smallest length
// consecutive sequence obtained previously if the sequence exists
// else start new sequence from current element
// we can do this using map + minheap , but that solution
// will work in len*log(len) time where len is length of nums
// since we only need to look at sequences of length 3
// we can do it using 3 hashmaps

// TC : O(len)
// SC : O(len)

class Solution {
    public boolean isPossible(int[] nums) {
        HashMap<Integer,Integer> lenOneSubseq = new HashMap();
        HashMap<Integer,Integer> lenTwoSubseq = new HashMap();
        HashMap<Integer,Integer> lenThreeAndAboveSubseq = new HashMap();
        
        for(int num : nums){
            int prevNum = num-1;
            
            if(lenOneSubseq.containsKey(prevNum)==false&&lenTwoSubseq.containsKey(prevNum)==false&&lenThreeAndAboveSubseq.containsKey(prevNum)==false){
                lenOneSubseq.put(num,lenOneSubseq.getOrDefault(num,0)+1);
            }else if(lenOneSubseq.containsKey(prevNum)){
                calc(lenOneSubseq,lenTwoSubseq,prevNum,num);
            }else if(lenTwoSubseq.containsKey(prevNum)){
                calc(lenTwoSubseq,lenThreeAndAboveSubseq,prevNum,num);
            }else if(lenThreeAndAboveSubseq.containsKey(prevNum)){
                calc(lenThreeAndAboveSubseq,lenThreeAndAboveSubseq,prevNum,num);
            }
        }
        
        if(lenOneSubseq.size()==0&&lenTwoSubseq.size()==0) return true;
        return false;
    }
    
    private void calc(HashMap<Integer,Integer> map1, HashMap<Integer,Integer> map2, int prevNum, int num){
        map2.put(num,map2.getOrDefault(num,0)+1);
        map1.put(prevNum,map1.get(prevNum)-1);
        
        if(map1.get(prevNum)==0) map1.remove(prevNum);
    }
}
    