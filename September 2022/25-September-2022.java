// Method-1 : Array
// TC : O(1) for each operation
// SC : O(k) for circular queue

class MyCircularQueue {
    private int arr[];
    private int size;
    private int currSize;
    private int frontIdx;
    
    public MyCircularQueue(int k) {
        size = k;
        arr = new int[size];
        currSize = 0;
        frontIdx = 0;
    }
    
    public boolean enQueue(int value) {
        if(isFull()) return false;
        
        int rearIdx = (frontIdx + currSize) % size;
        arr[rearIdx] = value;
        currSize++;
        
        return true;
    }
    
    public boolean deQueue() {
        if(isEmpty()) return false;
        
        arr[frontIdx] = 0;
        frontIdx = (frontIdx + 1) % size;
        currSize--;
        
        return true;
    }
    
    public int Front() {
        if(isEmpty()) return -1;
        return arr[frontIdx];
    }
    
    public int Rear() {
        if(isEmpty()) return -1;
        
        int rearIdx = (frontIdx + (currSize - 1)) % size;
        return arr[rearIdx];
    }
    
    public boolean isEmpty() {
        if(currSize == 0) return true;
        return false;
    }
    
    public boolean isFull() {
        if(currSize == size) return true;
        return false;
    }
}

// Method-2 : Circular Linked List
// TC : O(1) for each operation
// SC : O(k) for circular queue

class MyCircularQueue {
    private Node head;
    private Node tail;
    private int size;
    private int currSize;
    
    public MyCircularQueue(int k) {
        head = tail = null;
        size = k;
        currSize = 0;
    }
    
    public boolean enQueue(int value) {
        if(isFull()) return false;
        
        Node node = new Node(value);
        
        if(tail == null){
            head = tail = node;
        }else{
            tail.next = node;
            tail = node;
        }
        
        tail.next = head;
        currSize++;
        
        return true;
    }
    
    public boolean deQueue() {
        if(isEmpty()) return false;
        
        if(currSize == 1){
            head = tail = null;
        }else{
            Node next = head.next;
            head.next = null;
            head = next;
            tail.next = head;
        }
        
        currSize--;
        return true;
    }
    
    public int Front() {
        if(isEmpty()) return -1;
        return head.val;
    }
    
    public int Rear() {
        if(isEmpty()) return -1;
        return tail.val;
    }
    
    public boolean isEmpty() {
        if(currSize == 0) return true;
        return false;
    }
    
    public boolean isFull() {
        if(currSize == size) return true;
        return false;
    }
    
    private class Node{
        private int val;
        private Node next;
        
        private Node(){
            
        }
        
        private Node(int val){
            this.val = val;
        }
        
        private Node(int val, Node next){
            this.val = val;
            this.next = next;
        }
    }
}