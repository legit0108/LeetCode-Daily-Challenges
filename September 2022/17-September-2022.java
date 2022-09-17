// TC : O(len * (words[idx].length())^2)
// SC : O(len * words[idx].length())

class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        TrieNode root = new TrieNode();
        int len = words.length;
        List<List<Integer>> palindromicPairs = new ArrayList();
        
        for(int idx = 0;idx<len;idx++){
            insert(words, idx, root);
        }
        
        for(int idx = 0;idx<len;idx++){
            search(words, idx, root, palindromicPairs);
        }
        
        return palindromicPairs;
    }
    
    private void insert(String words[], int idx, TrieNode root){
        TrieNode curr = root;
        int len = words[idx].length();
        
        for(int wordIdx = len-1;wordIdx>=0;wordIdx--){
            char ch = words[idx].charAt(wordIdx);
            if(curr.children[ch-'a'] == null) curr.children[ch-'a'] = new TrieNode();
            
            if(isPalindromic(words[idx], 0, wordIdx)) curr.list.add(idx);
            curr = curr.children[ch-'a'];
        }
        
        curr.list.add(idx); // add idx because curr is always one step behind idx, right now curr is at last character
        curr.index = idx;
    }
    
    private void search(String words[], int idx, TrieNode root, List<List<Integer>> palindromicPairs){
        TrieNode curr = root;
        int len = words[idx].length();
        
        for(int wordIdx = 0;wordIdx<len && curr!=null;wordIdx++){
            if(curr.index>=0 && curr.index!=idx && isPalindromic(words[idx], wordIdx, len-1)){
                // prefix of first string + palindromic suffix of first string + entire second string
                // note that curr.index!=idx is not required here, because words[idx] has not ended yet
                
                List<Integer> palindromicPair = new ArrayList();
                palindromicPair.add(idx);
                palindromicPair.add(curr.index);
                
                palindromicPairs.add(palindromicPair);
            }
            
            curr = curr.children[words[idx].charAt(wordIdx)-'a'];
        }
        
        if(curr!=null){
            for(int index : curr.list){
                // entire first string + palindromic prefix of second string + remaining part of second string

                if(index != idx){ //required here, because words[idx] ended
                    List<Integer> palindromicPair = new ArrayList();
                    palindromicPair.add(idx);
                    palindromicPair.add(index);

                    palindromicPairs.add(palindromicPair);
                }
            }
        }
    }
    
    private boolean isPalindromic(String word, int start, int end){
        while(start < end){
            if(word.charAt(start) != word.charAt(end)) return false;
            start++;
            end--;
        }
        
        return true;
    }
    
    private class TrieNode{
        private TrieNode children[];
        private int index;
        private List<Integer> list;
        
        private TrieNode(){
            children = new TrieNode[26];
            index = -1;
            list = new ArrayList();
        }
    }
}