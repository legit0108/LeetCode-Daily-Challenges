// Solution-1: Brute force

// visit works in O(history.size()), forward, backward in O(1)

class BrowserHistory {
    private List<String> history;
    private int currURL;
    
    public BrowserHistory(String homepage) {
        history = new ArrayList<String>();
        history.add(homepage);
    }
    
    public void visit(String url) {
        while(history.size()>(currURL+1)) history.remove(history.size()-1);
        
        currURL++;
        history.add(url);
    }
    
    public String back(int steps) {
        currURL = Math.max(currURL-steps, 0);
        
        return history.get(currURL);
    }
    
    public String forward(int steps) {
        currURL = Math.min(currURL+steps, history.size()-1);
        
        return history.get(currURL);
    }
}

/**
 * Your BrowserHistory object will be instantiated and called as such:
 * BrowserHistory obj = new BrowserHistory(homepage);
 * obj.visit(url);
 * String param_2 = obj.back(steps);
 * String param_3 = obj.forward(steps);
 */


//-----X-----X-----X-----


// Solution-2: HashMap + LinkedList

// LinkedList to facilitate removal in O(1)
// HashMap to facilitate get in O(1)

// All functions run in O(1) time 

class BrowserHistory {
    private LinkedList history;
    private HashMap<Integer, Node> map;
    private int currURL;
    
    public BrowserHistory(String homepage) {
        history = new LinkedList();
        map = new HashMap();
        
        history.add(homepage);
        map.put(currURL, history.getTail());
    }
    
    public void visit(String url) {
        Node tab = map.get(currURL);
        history.setTail(tab);
        history.setSize(currURL+1);
        
        currURL=history.getSize();
        history.add(url);
        map.put(currURL, history.getTail());
        
    }
    
    public String back(int steps) {
        currURL = Math.max(currURL-steps, 0);
        
        return map.get(currURL).url;
    }
    
    public String forward(int steps) {
        currURL = Math.min(currURL+steps, history.getSize()-1);
        
        return map.get(currURL).url;
    }
    
    private class LinkedList{
        private Node head;
        private Node tail;
        private int size;

        private void add(String url){
            Node node = new Node(url);

            if(head==null) head = tail = node;
            else{
                tail.next = node;
                tail = node;
            }

            size++;
        }

        private Node getTail(){
            return tail;
        }

        private void setTail(Node node){
            node.next = null;
            tail = node;
        }

        private int getSize(){
            return size;
        }
        
        private void setSize(int size){
            this.size = size;
        }
    }
    
    private class Node{
        private String url;
        private Node next;

        private Node(){

        }

        private Node(String url){
            this.url = url;
        }
    }
}


//-----X-----X-----X-----


// Solution-3: Dynamic array

// All functions work in O(1) time

class BrowserHistory {
    private List<String> history;
    private int lastURL;
    private int currURL;
    
    public BrowserHistory(String homepage) {
        history = new ArrayList();
        history.add(homepage);
    }
    
    public void visit(String url) {
        currURL++;
        
        if(currURL<history.size()) history.set(currURL, url);
        else history.add(url);
        
        lastURL = currURL;
    }
    
    public String back(int steps) {
        currURL = Math.max(0, currURL-steps);
        return history.get(currURL);
    }
    
    public String forward(int steps) {
        currURL = Math.min(lastURL, currURL+steps);
        return history.get(currURL);
    }
}
