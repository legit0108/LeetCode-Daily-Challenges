// Solution-1: Brute force/hard coding
// Since number of calls are bounded by 1000, we only insert 1000 numbers in our 'infinite set'

class SmallestInfiniteSet {
    private TreeSet<Integer> set;
    
    public SmallestInfiniteSet() {
        set = new TreeSet();
        
        for(int num=1; num<=1000; num++) set.add(num);
    }
    
    public int popSmallest() {
        int smallest = set.first();
        set.remove(smallest);
        return smallest;
    }
    
    public void addBack(int num) {
        set.add(num);
    }
}

/**
 * Your SmallestInfiniteSet object will be instantiated and called as such:
 * SmallestInfiniteSet obj = new SmallestInfiniteSet();
 * int param_1 = obj.popSmallest();
 * obj.addBack(num);
 */


//-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----


/*
 Solution-2: 
 What if constraints are not given?
 We maintain an integer variable 'curr' to keep track of smallest integer in our infinite set
 We maintain a set for all integers < curr or for all integers that have been added back to our set after popSmallest()
 We can either implement this 'sorted set' using a min heap and a hashset, or a treeset
 This is the best solution for this problem, it does not depend entirely on constraints like previous solution
*/

class SmallestInfiniteSet {
    private HashSet<Integer> set;
    private PriorityQueue<Integer> minHeap;
    private int curr;
    
    public SmallestInfiniteSet() {
        set = new HashSet();
        minHeap = new PriorityQueue();
        curr = 1;
    }
    
    public int popSmallest() { // O(log(n)) 
        int smallest = -1;
        
        if(minHeap.size()>0) smallest = minHeap.remove(); // if set of added back integers contains some elements, we return minimum of this set
        else smallest = curr++; // else 'curr' is our smallest integer and we increment curr since smallest has been popped
        
        if(set.contains(smallest)) set.remove(smallest); // when smallest == curr, it is not in set
        
        return smallest;
    }
    
    public void addBack(int num) { // O(log(n))
        if(num>=curr || set.contains(num)) return; // we only insert elements < curr in set, also they should not be present previously in set
        
        minHeap.add(num);
        set.add(num);
    }
}


//-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----


/*
 Solution-3: 
 We can also implement Solution-2 using a treeset (sorted set)
*/

class SmallestInfiniteSet {
    private TreeSet<Integer> sortedSet;
    private int curr;
    
    public SmallestInfiniteSet() {
        sortedSet = new TreeSet();
        curr = 1;
    }
    
    public int popSmallest() { // O(log(n)) 
        int smallest = -1;
        
        if(sortedSet.size()>0) smallest = sortedSet.first();
        else smallest = curr++;
        
        if(sortedSet.contains(smallest)) sortedSet.remove(smallest);
        
        return smallest;
    }
    
    public void addBack(int num) { // O(log(n))
        if(num>=curr || sortedSet.contains(num)) return;
        
        sortedSet.add(num);
    }
}