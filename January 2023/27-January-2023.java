// Trie + DP

// TC: O(words.length*word.length()*word.length())
// SC: O(words.length*word.length())

class Solution {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        TrieNode root = new TrieNode();
        
        for(String word: words) insert(word, word.length(), root);
        
        List<String> list = new ArrayList();
        
        for(String word: words){
            if(isConcatenatedWord(word, word.length(), root)) list.add(word);
        }
        
        return list;
    }
    
    private boolean isConcatenatedWord(String word, int len, TrieNode root){
        int[] dp = new int[len+1];
        
        for(int idx1=len-1; idx1>=0; idx1--){
            TrieNode curr = root;
            int maxPartitions = -(int)1e9;
            
            for(int idx2=idx1; idx2<len; idx2++){
                char ch = word.charAt(idx2);
                int pos = ch-'a';
                
                curr = curr.children[pos];
                
                if(curr==null) break;
                else{
                    if(curr.wordEnd) maxPartitions = Math.max(maxPartitions, 1+dp[idx2+1]);
                }
            }
            
            dp[idx1] = maxPartitions;
        }
        
        if(dp[0]>=2) return true;
        else return false;
    }
    
    private void insert(String word, int len, TrieNode root){
        TrieNode curr = root;
        
        for(int idx=0; idx<len; idx++){
            char ch = word.charAt(idx);
            int pos = ch-'a';
            
            if(curr.children[pos]==null) curr.children[pos] = new TrieNode();
            
            curr = curr.children[pos];
        }
        
        curr.wordEnd = true;
    }
    
    private class TrieNode{
        private TrieNode[] children;
        private boolean wordEnd;
        
        private TrieNode(){
           children = new TrieNode[26]; 
        }
    }
}