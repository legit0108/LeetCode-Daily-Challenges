// BFS

// TC: O(rows*cols)
// SC: O(rows*cols)

class Solution {
    public int snakesAndLadders(int[][] board) {
        int rows = board.length;
        int cols = board[0].length;
        int flag = 0;
        int cell = 1;
        HashMap<Integer, Integer> map = new HashMap();
        
        for(int row=rows-1; row>=0; row--){
            if(flag==0){
                for(int col=0; col<cols; col++){
                    int val = board[row][col];
                    
                    if(val==-1) map.put(cell, cell);
                    else map.put(cell, val);
                    
                    cell++;
                }
            }else{
                for(int col=cols-1; col>=0; col--){
                    int val = board[row][col];
                    
                    if(val==-1) map.put(cell, cell);
                    else map.put(cell, val);
                    
                    cell++;
                }
            }
            
            flag^=1;
        }
        
        int cells = rows*cols;
        int minMoves = 0;
        int initialCell = 1;
        int finalCell = cells;
        Queue<Integer> queue = new ArrayDeque();
        Set<Integer> visited = new HashSet();
        queue.add(initialCell);
        
        while(queue.size()>0){
            int size = queue.size();
            
            while(size>0){
                int currCell = queue.remove();
                size--;
                
                if(visited.contains(currCell)) continue;
                if(currCell == finalCell) return minMoves;
                visited.add(currCell);
                
                for(int move=1; move<=6; move++){
                    Integer nextCell = map.get(currCell+move);
                    
                    if(nextCell!=null && !visited.contains(nextCell)) queue.add(nextCell);
                }
            }
            
            minMoves++;
        }
        
        return -1;
    }
}