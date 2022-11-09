// TC: O(1) over all calls
// SC: O(size of stack)

class StockSpanner {
    private Stack<Pair> stack;
    
    public StockSpanner() {
       stack = new Stack(); 
    }
    
    public int next(int price) {
       int span = 1;
        
       while(stack.size()>0 && stack.peek().val<=price){
           span+=stack.pop().count;
       }
        
       stack.push(new Pair(price, span));
       return span; 
    }
    
    private class Pair{
        private int val;
        private int count;

        private Pair(){

        }

        private Pair(int val, int count){
            this.val = val;
            this.count = count;
        }
    }
}