// Solution - 1 : Push efficient

class MyQueue { 
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;
    
    public MyQueue() {
       stack1 = new Stack();
       stack2 = new Stack();
    }
    
    public void push(int x) { // O(1)
       stack1.push(x); 
    }
    
    public int pop() { // O(size)
       transfer(stack1, stack2, 1);
        
       int val = stack1.pop();
        
       transfer(stack2, stack1, 0);
        
       return val; 
    }
    
    public int peek() { // O(size)
       transfer(stack1, stack2, 1);
        
       int val = stack1.pop();
       stack2.push(val); 
        
       transfer(stack2, stack1, 0);
        
       return val; 
    }
    
    public boolean empty() {
       if(stack1.size()>0) return false;
       else return true;
    }
    
    private void transfer(Stack<Integer> stack1, Stack<Integer> stack2, int limit){
       while(stack1.size()>limit){
           stack2.push(stack1.pop());
       }
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */

 // Solution - 2 : Pop efficient

class MyQueue { 
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;
    
    public MyQueue() {
       stack1 = new Stack();
       stack2 = new Stack();
    }
    
    public void push(int x) { // O(size)
       transfer(stack1, stack2, 0);
        
       stack1.push(x);
        
       transfer(stack2, stack1, 0); 
    }
    
    public int pop() { // O(1)
       return stack1.pop(); 
    }
    
    public int peek() { // O(1)
       return stack1.peek(); 
    }
    
    public boolean empty() {
       if(stack1.size()>0) return false;
       else return true;
    }
    
    private void transfer(Stack<Integer> stack1, Stack<Integer> stack2, int limit){
       while(stack1.size()>limit){
           stack2.push(stack1.pop());
       }
    }
}

// Solution - 3 : Using one explicit stack

class MyQueue { 
    private Stack<Integer> stack;
    
    public MyQueue() {
        stack = new Stack();
    }
    
    public void push(int x) { // O(size)
        if(stack.size()==0) stack.push(x);
        else{
            int val = stack.pop();
            
            push(x);
            
            stack.push(val);
        }
    }
    
    public int pop() { // O(1)
       return stack.pop();
    }
    
    public int peek() { // O(1)
       return stack.peek(); 
    }
    
    public boolean empty() {
       if(stack.size()>0) return false;
       else return true; 
    }
}

// Solution - 4 : Amortized O(1)

class MyQueue { 
    private Stack<Integer> input;
    private Stack<Integer> output;
    
    public MyQueue() {
       input = new Stack();
       output = new Stack();
    }
    
    public void push(int x) { // O(1) amortized 
       input.push(x);
    }
    
    public int pop() { // O(1) amortized
       peek();
        
       return output.pop();
    }
    
    public int peek() { // O(1) amortized
       if(output.size()==0){
            while(input.size()>0) output.push(input.pop()); 
       }
        
       return output.peek();
    }
    
    public boolean empty() {
       if(input.size()==0 && output.size()==0) return true;
       else return false;
    }
}