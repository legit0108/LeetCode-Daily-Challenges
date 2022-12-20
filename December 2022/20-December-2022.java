// Solution - 1 : DFS

// TC : O(V + E)
// SC : O(V) 

// where V = vertices = number of rooms, E = number of edges = keys 

class Solution {
    private int roomsVisited;
    
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int totalRooms = rooms.size();
        boolean visited[] = new boolean[totalRooms];
        
        dfs(0, rooms, visited);
        
        return (roomsVisited == totalRooms);
    }
    
    private void dfs(int room, List<List<Integer>> rooms, boolean visited[]){
        visited[room] = true;
        roomsVisited++;
        
        List<Integer> keys = rooms.get(room);
        
        for(int key : keys){
            if(!visited[key]) dfs(key, rooms, visited);
        }
    }
}

// Solution - 2 : BFS

// TC : O(V + E)
// SC : O(V) 

class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int totalRooms = rooms.size();
        
        return bfs(0, rooms, totalRooms);
    }
    
    private boolean bfs(int room, List<List<Integer>> rooms, int totalRooms){
        boolean visited[] = new boolean[totalRooms];
        int roomsVisited = 0;
        
        Queue<Integer> queue = new ArrayDeque();
        queue.add(room);
        
        while(queue.size()>0){
            room = queue.remove();
            
            if(visited[room]) continue;
            
            visited[room] = true;
            roomsVisited++;
            
            List<Integer> keys = rooms.get(room);
            
            for(int key : keys){
                if(!visited[key]) queue.add(key);
            }
        }
        
        return (roomsVisited == totalRooms);
    }
}

// Solution - 3 : Iterative DFS

// TC : O(V + E)
// SC : O(V) 

class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int totalRooms = rooms.size();
        
        return iterativeDfs(0, rooms, totalRooms);
    }
    
    private boolean iterativeDfs(int room, List<List<Integer>> rooms, int totalRooms){
        boolean visited[] = new boolean[totalRooms];
        int roomsVisited = 0;
        
        Stack<Integer> stack = new Stack();
        stack.push(room);
        
        while(stack.size()>0){
            room = stack.pop();
            
            if(visited[room]) continue;
            
            visited[room] = true;
            roomsVisited++;
            
            List<Integer> keys = rooms.get(room);
            
            for(int key : keys){
                if(!visited[key]) stack.push(key);
            }
        }
        
        return (roomsVisited == totalRooms);
    }
}