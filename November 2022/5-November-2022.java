// TC: exponential with pruning
// SC: O(words.length + rows*cols)

class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        TrieNode root = new TrieNode();
        
        for(String word: words){
            insert(root, word);
        }
        
        List<String> list = new ArrayList();
        int rows = board.length;
        int cols = board[0].length;
        
        for(int row=0; row<rows; row++){
            for(int col=0; col<cols; col++){
                char ch = board[row][col];
                search(board, root, row, col, rows, cols, list);
            }
        }
        
        return list;
    }
    
    private void search(char[][] board, TrieNode curr, int row, int col, int rows, int cols, List<String> list){
        if(row<0 || col<0 || row>=rows || col>=cols || board[row][col]=='!') return;
        
        char ch = board[row][col];
        curr = curr.children[ch-'a'];
        if(curr==null) return;
        
        String wordEnd = curr.wordEnd;
        if(wordEnd!=null){
            list.add(wordEnd);
            curr.wordEnd = null;
        }
        
        board[row][col] = '!';
        search(board, curr, row-1, col, rows, cols, list);
        search(board, curr, row+1, col, rows, cols, list);
        search(board, curr, row, col-1, rows, cols, list);
        search(board, curr, row, col+1, rows, cols, list);
        board[row][col] = ch;
    }

    private void insert(TrieNode root, String word){
        int len = word.length();
        TrieNode curr = root;
        
        for(int idx=0; idx<len; idx++){
            char ch = word.charAt(idx);
            if(curr.children[ch-'a']==null) curr.children[ch-'a'] = new TrieNode();
            curr = curr.children[ch-'a'];
        }
        
        curr.wordEnd = word;
    }
    
    private class TrieNode{
        private TrieNode children[];
        private String wordEnd;
        
        private TrieNode(){
           children = new TrieNode[26];
           wordEnd = null; 
        }
    }
}