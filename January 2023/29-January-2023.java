// Two HashMaps + Doubly Linked List

// TC: O(1)
// SC: O(n), where n = size of LFU

class LFUCache {
    private HashMap<Integer, DoublyLinkedListNode> map;
    private HashMap<Integer, DoublyLinkedList> freqList;
    private int currSize;
    private int capacity;
    private int minFreq;
    
    public LFUCache(int capacity) {
        map = new HashMap();
        freqList = new HashMap();
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if(map.containsKey(key)){
            DoublyLinkedListNode node = map.get(key);
            
            update(node);
            
            return node.value;
        }else return -1;
    }
    
    public void put(int key, int value) {
        if(capacity==0) return;
        
        if(map.containsKey(key)){ 
            DoublyLinkedListNode node = map.get(key);
            node.value = value;
            
            update(node);
        }else{
            DoublyLinkedListNode node = new DoublyLinkedListNode(key, value);
            
            if(currSize>=capacity){
                DoublyLinkedList lfuList = freqList.get(minFreq);
                DoublyLinkedListNode lfuNode = lfuList.tail.prev;
                
                lfuList.remove(lfuNode);
                map.remove(lfuNode.key);
            }
            
            if(!freqList.containsKey(1)) freqList.put(1, new DoublyLinkedList());
            
            DoublyLinkedList list = freqList.get(1);
            list.add(node);
            map.put(key, node);
            
            minFreq = 1;
            
            if(currSize<capacity) currSize++;
        }
    }
    
    private void update(DoublyLinkedListNode node){
        int freq = node.freq;
        DoublyLinkedList list = freqList.get(freq);
        list.remove(node);
        
        if(list.size()==0){
            if(freq==minFreq) minFreq++;
            
            freqList.remove(freq);
        }
        
        freq++;
        node.freq = freq;
        
        if(!freqList.containsKey(freq)) freqList.put(freq, new DoublyLinkedList()); 
        list = freqList.get(freq);
        list.add(node);
    }
    
    private class DoublyLinkedListNode{
        private DoublyLinkedListNode next;
        private DoublyLinkedListNode prev;
        private int key;
        private int value;
        private int freq;
            
        private DoublyLinkedListNode(int key, int value){
            this.key = key;
            this.value = value;
            this.freq = 1;
        }
    }
    
    private class DoublyLinkedList{
        private DoublyLinkedListNode head;
        private DoublyLinkedListNode tail;
        private int size;
        
        private DoublyLinkedList(){
            head = new DoublyLinkedListNode(-1, -1);
            tail = new DoublyLinkedListNode(-1, -1);
            
            head.next = tail;
            tail.prev = head;
        }
        
        private void add(DoublyLinkedListNode node){
            DoublyLinkedListNode next = head.next;
            
            head.next = node;
            node.prev = head;
            
            node.next = next;
            next.prev = node;
            
            size++;
        }
        
        private void remove(DoublyLinkedListNode node){
            DoublyLinkedListNode next = node.next;
            DoublyLinkedListNode prev = node.prev;
            
            node.prev.next = next;
            node.next.prev = prev;
            
            node.next = null;
            node.prev = null;
            
            size--;
        }
        
        private int size(){
            return this.size;
        }
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */