// TC: O(nlogk)
// SC: O(n)

// where n is the number of words

class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> map = new HashMap();
        for(String word: words) map.put(word, map.getOrDefault(word, 0)+1);
        
        PriorityQueue<String> heap = new PriorityQueue<String>((str1, str2)->(map.get(str1)==map.get(str2))?
                                                              str2.compareTo(str1):
                                                              Integer.compare(map.get(str1), map.get(str2)));
        
        for(String word: map.keySet()){
            heap.add(word);
            if(heap.size()>k) heap.remove();
        }
        
        List<String> list = new ArrayList();
        while(heap.size()>0) list.add(heap.remove());
        
        Collections.reverse(list); // can use linkedlist (addFirst), no need to reverse in that case
        return list;
    }
}