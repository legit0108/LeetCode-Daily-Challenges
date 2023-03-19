// TC: O(words*word.length())
// SC: O(words*word.length())


// Solution-1: Hash Table 

class WordDictionary {
    private HashMap<Integer, List<String>> map;
    
    public WordDictionary() {
        map = new HashMap(); 
    }
    
    public void addWord(String word) {
        int len = word.length();
        
        if(!map.containsKey(len)) map.put(len, new ArrayList());
        
        List<String> list = map.get(len);
        list.add(word);
    }
    
    public boolean search(String word) {
        List<String> list = map.get(word.length());
        
        if(list==null) return false;
        
        for(String str: list){
            if(isEqual(word, str)) return true;
        }
        
        return false;
    }
    
    private boolean isEqual(String word, String str){
        for(int index=0; index<word.length(); index++){
            char ch = word.charAt(index);
            
            if(ch=='.') continue;
            else if(ch!=str.charAt(index)) return false;
        }
        
        return true;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */


// -----X-----X-----X-----X-----X-----
 

// Solution-2: Trie + DFS

class WordDictionary {
    private Trie trie;
    
    public WordDictionary() {
        trie = new Trie();
    }
    
    public void addWord(String word) {
        trie.addWord(word);
    }
    
    public boolean search(String word) {
        return trie.contains(word);
    }
    
    private class Trie{
        private TrieNode root;
        private int size;
            
        private Trie(){
            size = 26;
            root = new TrieNode(size);
        }
        
        private void addWord(String word){
            int len = word.length();
            TrieNode curr = root;
            
            for(int index=0; index<len; index++){
                int position = word.charAt(index)-'a';
                
                if(!curr.containsKey(position)) curr.put(position, new TrieNode(size));
                
                curr = curr.get(position);
            }
            
            curr.setEnd();
        }
        
        private boolean contains(String word){
            return dfs(root, word, 0);
        }
        
        private boolean dfs(TrieNode curr, String word, int index){
            if(index>=word.length()) return curr.getEnd();
            
            char ch = word.charAt(index);
            
            if(ch=='.'){
                TrieNode[] children = curr.getChildren();
                
                for(TrieNode child: children){
                    if(foundWord(child, word, index)) return true;
                }
            }else{
                int position = ch-'a';
                
                TrieNode child = curr.get(position);
                
                if(foundWord(child, word, index)) return true;
            }
            
            return false;
        }
        
        private boolean foundWord(TrieNode node, String word, int index){
            if(node==null) return false;
            
            boolean found = dfs(node, word, index+1);
            
            return found;
        }
        
        private class TrieNode{
            private TrieNode[] children;
            private boolean end;
            
            private TrieNode(int size){
                children = new TrieNode[size];
            }
            
            private TrieNode[] getChildren(){
                return children;
            }
            
            private TrieNode get(int position){
                return children[position];
            }
            
            private boolean getEnd(){
                return end;
            }
            
            private boolean containsKey(int position){
                return children[position]!=null;
            }
            
            private void setEnd(){
                end = true;
            }
            
            private void put(int position, TrieNode node){
                children[position] = node;
            }
        }
    }
}


// -----X-----X-----X-----X-----X-----


// Solution-3: Trie + BFS

class WordDictionary {
    private Trie trie;
    
    public WordDictionary() {
        trie = new Trie();
    }
    
    public void addWord(String word) {
        trie.addWord(word);
    }
    
    public boolean search(String word) {
        return trie.contains(word);
    }
    
    private class Trie{
        private TrieNode root;
        private int size;
            
        private Trie(){
            size = 26;
            root = new TrieNode(size);
        }
        
        private void addWord(String word){
            int len = word.length();
            TrieNode curr = root;
            
            for(int index=0; index<len; index++){
                int position = word.charAt(index)-'a';
                
                if(!curr.containsKey(position)) curr.put(position, new TrieNode(size));
                
                curr = curr.get(position);
            }
            
            curr.setEnd();
        }
        
        private boolean contains(String word){
            return bfs(word);
        }
        
        private boolean bfs(String word){
            Queue<TrieNode> queue = new ArrayDeque();
            queue.add(root);
            int index = 0;
            
            while(queue.size()>0){
                int size = queue.size();
                
                while(size>0){
                    TrieNode curr = queue.remove();
                    size--;
                    
                    if(index>=word.length()){
                        if(curr.isEnd()) return true;
                    }else{
                        char ch = word.charAt(index);
                         
                        if(ch=='.'){
                            TrieNode[] children = curr.getChildren();
                            
                            for(TrieNode child: children){
                                if(child!=null) queue.add(child);
                            }
                        }else{
                            int position = ch-'a';

                            TrieNode child = curr.get(position);
                            
                            if(child!=null) queue.add(child);
                        }
                    }
                }
                
                index++;
            }
            
            return false;
        }
        
        private class TrieNode{
            private TrieNode[] children;
            private boolean end;
            
            private TrieNode(int size){
                children = new TrieNode[size];
            }
            
            private TrieNode[] getChildren(){
                return children;
            }
            
            private TrieNode get(int position){
                return children[position];
            }
            
            private boolean isEnd(){
                return end;
            }
            
            private boolean containsKey(int position){
                return children[position]!=null;
            }
            
            private void setEnd(){
                end = true;
            }
            
            private void put(int position, TrieNode node){
                children[position] = node;
            }
        }
    }
}