// Solution-1: Traverse matrix row-wise

// TC: O(rows*cols)
// SC: O(cols)

class Solution {
    public int minDeletionSize(String[] strs) {
        int rows = strs.length;
        int cols = strs[0].length();
        char[] prev = new char[cols];
        Arrays.fill(prev, 'a');
        int minDels = 0;
        
        for(int row=0; row<rows; row++){
            String str = strs[row];
            
            for(int col=0; col<cols; col++){
                char currCh = str.charAt(col);
                char prevCh = prev[col];
                
                if(prevCh!='#'){
                    if(currCh<prevCh){
                        minDels++;
                        prev[col] = '#';
                    }else prev[col] = currCh;
                }
            }
        }
        
        return minDels;
    }
}

// Solution-2: Traverse matrix column-wise

// TC: O(rows*cols)
// SC: O(1)

class Solution {
    public int minDeletionSize(String[] strs) {
        int rows = strs.length;
        int cols = strs[0].length();
        int minDels = 0;
        
        for(int col=0; col<cols; col++){
            char prevCh = 'a';
            
            for(int row=0; row<rows; row++){
                char currCh = strs[row].charAt(col);
                
                if(currCh<prevCh){
                    minDels++;
                    break;
                }
                
                prevCh = currCh;
            }
        }
        
        return minDels;
    }
}