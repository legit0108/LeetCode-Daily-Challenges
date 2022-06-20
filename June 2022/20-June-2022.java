// Insert reverse of every word in trie
// Length of shortest reference string = sum of node to root path nodes for all leaf nodes
// Implemented below in one pass

// TC : O(words.length*words[idx].length())
// SC : O(words.length*words[idx].length())


class Solution {
    public int minimumLengthEncoding(String[] words) {
        TrieNode root = new TrieNode();
        int len = words.length;
        int count = 1;
        
        for(int idx=0;idx<len;idx++){
            count+=insert(root,words[idx]);
        }
        
        return count;
    }
    
    private int insert(TrieNode root,String word){
        int len = word.length();
        TrieNode curr = root;
        boolean flag = false;
        int ans = 0;
        
        for(int idx = len-1;idx>=0;idx--){
            char ch = word.charAt(idx);
            
            if(curr.isEmpty&&!flag) ans-=len-idx;
            if(curr.children[ch-'a']==null){
                curr.children[ch-'a'] = new TrieNode();
                flag = true;
                curr.isEmpty = false;
            }
            
            curr = curr.children[ch-'a'];
        }
        
        if(flag) ans+=len+1;
        return ans;
    }
    
    private class TrieNode{
        private TrieNode children[];
        private boolean isEmpty;
        
        private TrieNode(){
            children = new TrieNode[26];
            isEmpty = true;
        }
    }
}