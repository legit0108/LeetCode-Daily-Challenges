// Backtracking

// TC: O(cells*4^(len))
// SC: O(len)

// where cells = rows*cols and len = word.length()

class Solution {
    public boolean exist(char[][] board, String word) {
        int len = word.length();
        int rows = board.length;
        int cols = board[0].length;
        
        for(int row=0; row<rows; row++){
            for(int col=0; col<cols; col++){
                boolean result = search(board, word, row, col, rows, cols, 0, len);
                
                if(result==true) return true;
            }
        }
        
        return false;
    }
    
    private boolean search(char[][] board, String word, int row, int col, int rows, int cols, int idx, int len){
        if(idx>=len) return true;
        if(row<0 || col<0 || row>=rows || col>=cols) return false;
        
        char ch = board[row][col];
        
        if(ch=='!' || ch!=word.charAt(idx)) return false;
        
        board[row][col] = '!';
        
        boolean ans = search(board, word, row-1, col, rows, cols, idx+1, len) ||
                      search(board, word, row+1, col, rows, cols, idx+1, len) ||
                      search(board, word, row, col-1, rows, cols, idx+1, len) ||
                      search(board, word, row, col+1, rows, cols, idx+1, len);
        
        board[row][col] = ch;
        
        return ans;
    }
}