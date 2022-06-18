// BRUTE FORCE : generate all pairs of suffix and prefix , put them in a map , then process queries

// BETTER : string = "abc"
// store : "abc{abc" , "bc{abc" , "c{abc" in Trie , then process queries

// TC : O(N*L^2 + Q*L)
// SC : O(N*L^2)
// N -> length of words , L -> average length of string , Q -> number of queries

class WordFilter {
    private TrieNode root;
    
    public WordFilter(String[] words) {
        root = new TrieNode();
        int len = words.length;
        
        for(int idx=0;idx<len;idx++){
            String word = words[idx];
            int strLen = word.length();
            
            for(int strIdx=0;strIdx<strLen;strIdx++){
                String str = word.substring(strIdx,strLen)+"{"+word;
                insert(str,idx);
            }
        }
    }
    
    public int f(String prefix, String suffix) {
        String str = suffix+"{"+prefix;
        int len = str.length();
        TrieNode curr = root;
        
        for(int idx=0;idx<len;idx++){
            char ch = str.charAt(idx);
            if(curr.children[ch-'a']==null) return -1;
            curr = curr.children[ch-'a'];
        }
        
        return curr.idx;
    }
    
    private void insert(String str,int strIdx){
        int len = str.length();
        TrieNode curr = root;
        
        for(int idx=0;idx<len;idx++){
            char ch = str.charAt(idx);
            
            if(curr.children[ch-'a']==null) curr.children[ch-'a'] = new TrieNode();
            curr = curr.children[ch-'a'];
            
            curr.idx = strIdx;
        }
    }
    
    private class TrieNode{
        private TrieNode children[];
        private int idx;
        
        private TrieNode(){
            children = new TrieNode[27];
            idx = -1;
        }
    }
}

/**
 * Your WordFilter object will be instantiated and called as such:
 * WordFilter obj = new WordFilter(words);
 * int param_1 = obj.f(prefix,suffix);
 */