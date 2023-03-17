// TC: O(word.length())
// SC: O(number of words * word.length())

class Trie {
    private TrieNode root;
    
    public Trie() {
        root = new TrieNode();
    }
    
    public void insert(String word) { // O(word.length())
        TrieNode curr = root;
        
        for(int idx=0; idx<word.length(); idx++){
            char ch = word.charAt(idx);
            
            if(!curr.containsKey(ch)) curr.put(ch, new TrieNode());
            curr = curr.get(ch);
        }
        
        curr.setEnd();
    }
    
    public boolean search(String word) { // O(word.length())
        TrieNode node = find(word);
        
        return node!=null && node.isEnd();
    }
    
    public boolean startsWith(String prefix) { // O(prefix.length())
        TrieNode node = find(prefix);
        
        return node!=null;
    }
    
    private TrieNode find(String word){
        TrieNode curr = root;
            
        for(int idx=0; idx<word.length(); idx++){
            char ch = word.charAt(idx);
                
            if(!curr.containsKey(ch)) return null;
            curr = curr.get(ch);
        }
            
        return curr;
    }
    
    private class TrieNode{
        private TrieNode[] children;
        private boolean wordEnd;
        private int size;
        
        private TrieNode(){
            size = 26;
            children = new TrieNode[size];
            wordEnd = false;
        }
        
        private boolean containsKey(char ch){
            return this.children[ch-'a']!=null;
        }
        
        private TrieNode get(char ch){
            return this.children[ch-'a'];
        }
        
        private void put(char ch, TrieNode node){
            this.children[ch-'a'] = node;
        }
        
        private boolean isEnd(){
            return this.wordEnd;
        }
        
        private void setEnd(){
            this.wordEnd = true;
        }
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */