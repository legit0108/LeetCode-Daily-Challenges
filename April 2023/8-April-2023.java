/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/


// Note that all the solutions run in O(V+E) time and O(V) space
// where V = number of vertices, E = number of edges

// Solution-1: DFS

class Solution {
    public Node cloneGraph(Node node) {
        HashMap<Node, Node> map = new HashMap();
        
        dfs(node, map);
        
        return map.get(node);
    }
    
    private void dfs(Node node, HashMap<Node, Node> map){
        if(node==null) return;
        
        Node clonedNode = new Node(node.val);
        map.put(node, clonedNode);
        
        List<Node> neighbors = node.neighbors;
        for(Node neighbor: neighbors){
            if(!map.containsKey(neighbor)) dfs(neighbor, map);
            
            clonedNode.neighbors.add(map.get(neighbor));
        }
    }
}


// Solution-2: BFS

class Solution {
    public Node cloneGraph(Node node) {
        HashMap<Node, Node> map = new HashMap();
        
        bfs(node, map);
        
        return map.get(node);
    }
    
    private void bfs(Node node, HashMap<Node, Node> map){
        if(node==null) return;
        
        Queue<Node> queue = new ArrayDeque();
        queue.add(node);
        map.put(node, new Node(node.val));
        
        while(queue.size()>0){
            node = queue.remove();
            Node clonedNode = map.get(node);
            
            List<Node> neighbors = node.neighbors;
            for(Node neighbor: neighbors){
                if(!map.containsKey(neighbor)){
                    map.put(neighbor, new Node(neighbor.val));
                    queue.add(neighbor);
                }
                
                clonedNode.neighbors.add(map.get(neighbor));
            }
        }
    }
}


// Solution-3: Using array as map

class Solution {
    Node[] map = new Node[101];
    
    public Node cloneGraph(Node node) {
        if(node==null) return null;
        
        int val = node.val;
        if(map[val]==null){
            map[val] = new Node(val);
            Node clonedNode = map[val];
            
            List<Node> neighbors = node.neighbors;
            for(Node neighbor: neighbors){
                clonedNode.neighbors.add(cloneGraph(neighbor));
            }
        }
        
        return map[val];
    }
}