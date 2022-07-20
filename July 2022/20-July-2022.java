// Method - 1 : Brute force , TLE

// TC : O(words.length*(s.length()+words[idx].length()))
// SC : O(1)

class Solution {
    public int numMatchingSubseq(String s, String[] words) {
        int len = words.length;
        int matchingSubseq = 0;
        
        for(int idx=0;idx<len;idx++){
            if(isSubsequence(s,words[idx])) matchingSubseq++;
        }
        
        return matchingSubseq;
    }
    
    private boolean isSubsequence(String s,String str){
        int s_len = s.length();
        int str_len = str.length();
        int s_idx = 0;
        int str_idx = 0;
        
        while(s_idx<s_len&&str_idx<str_len){
            if(s.charAt(s_idx)==str.charAt(str_idx)){
                str_idx++;
            }
            
            s_idx++;
        }
        
        return str_idx==str_len;
    }
}

// Method - 2 : HashMap + Binary Search , AC , better

// TC : O(s.length()+words.length*(words[idx].length()*log(s.length())))
// SC : O(s.length())

class Solution {
    public int numMatchingSubseq(String s, String[] words) {
        HashMap<Character,List<Integer>> map = new HashMap();
        int len = s.length();
        int matchingSubseq = 0;
        
        for(int idx=0;idx<len;idx++){
            char ch = s.charAt(idx);
            List<Integer> list = map.getOrDefault(ch,new ArrayList());
            
            list.add(idx);
            map.put(ch,list);
        }
        
        len = words.length;
        for(int idx=0;idx<len;idx++){
            int searchIdx = -1;
            int strLen = words[idx].length();
            
            for(int currIdx=0;currIdx<strLen;currIdx++){
                char currCh = words[idx].charAt(currIdx);
                List<Integer> list = map.getOrDefault(currCh,new ArrayList());
                
                searchIdx = binarySearch(list,searchIdx);
                if(searchIdx==-1) break;
            }
            
            if(searchIdx!=-1) matchingSubseq++;
        }
        
        return matchingSubseq;
    }
    
    private int binarySearch(List<Integer> list,int idx){
        int low = 0;
        int high = list.size()-1;
        int searchIdx = -1;
        
        while(low<=high){
            int mid = low + (high-low)/2;
            
            if(((int)list.get(mid))>idx){
                searchIdx = list.get(mid);
                high = mid-1;
            }else low = mid+1;
        }
        
        return searchIdx;
    }
}

// Method - 3 : Map + Node class , optimal

// TC : O(s.length()+words.length*(words[idx].length()))
// SC : O(words.length)

class Solution {
    public int numMatchingSubseq(String s, String[] words) {
        ArrayList<Node> lists[] = new ArrayList[26];
        for(int idx=0;idx<26;idx++) lists[idx] = new ArrayList();
        int len = words.length;
        int matchingSubseq = 0;
        
        for(int idx=0;idx<len;idx++){
            char ch = words[idx].charAt(0);
            lists[ch-'a'].add(new Node(words[idx],0));
        }
        
        len = s.length();
        for(int idx=0;idx<len;idx++){
            char ch = s.charAt(idx);
            ArrayList<Node> list = lists[ch-'a'];
            
            lists[ch-'a'] = new ArrayList(); // avoid ConcurrentModificationException
            for(Node node : list){
                node.idx++; // process next character
                
                if(node.idx==node.str.length()) matchingSubseq++;
                else{
                    ch = node.str.charAt(node.idx);
                    lists[ch-'a'].add(node);
                }
            }    
        }
        
        return matchingSubseq;
    }
    
    private class Node{
        String str;
        int idx;

        Node(String str,int idx){
            this.str = str;
            this.idx = idx;
        }
    }
}
