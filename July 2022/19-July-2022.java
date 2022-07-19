// TC : O(numRows*numRows)
// SC : O(numRows*numRows)

class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList();
        ans.add(new ArrayList());
        ans.get(0).add(1);
        
        for(int row=1;row<numRows;row++){
            List<Integer> list = new ArrayList();
            List<Integer> prevList = ans.get(row-1);
            
            for(int col=0;col<=row;col++){
                if(col==0||col==row) list.add(1);
                else{
                    int num = prevList.get(col-1)
                             +prevList.get(col);
                    
                    list.add(num);
                }
            }
            
            ans.add(list);
        }
        
        return ans;
    }
}