// Maintain distance like dijkstra
// Update distance vector if less distance found
// For each node maintain shortest parents
// Finally backtrack from destination to source
// BFS -> shortest path
// DFS -> backtracking from destination to source

// TC ~ O(nodes*nodes)
// SC ~ O(nodes*nodes)

class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> wordSet = new HashSet(wordList);
        if(wordSet.contains(endWord)==false) return new ArrayList();
        if(wordSet.contains(beginWord)==false) wordSet.add(beginWord); // need to make graph so add beginWord
        
        HashMap<String,HashSet<String>> graph = createGraph(beginWord,wordSet);
        HashMap<String,HashSet<String>> subGraph = createSubGraph(beginWord,graph,wordSet);
        
        List<String> path = new ArrayList();
        List<List<String>> allPaths = new ArrayList();
        dfs(subGraph,endWord,path,allPaths);
        
        if(allPaths.size()==1&&allPaths.get(0).size()==1) allPaths = new ArrayList();
        return allPaths;
    }
    
    private HashMap<String,HashSet<String>> createGraph(String beginWord,HashSet<String> wordSet){
        HashMap<String,HashSet<String>> graph = new HashMap();
        
        for(String word : wordSet) graph.put(word , new HashSet());
        
        for(String node1 : wordSet){
            for(String node2 : wordSet){
                if(canFormConnection(node1,node2)){
                    graph.get(node1).add(node2);
                    graph.get(node2).add(node1);
                } 
            }
        }
        
        return graph;
    }
    
    private boolean canFormConnection(String node1, String node2){ // can form connection if only 1 different character between 2 nodes
        int len = node1.length();
        int diffChars = 0;
        
        for(int idx=0;idx<len;idx++){
            if(node1.charAt(idx)!=node2.charAt(idx)) diffChars++;
        }
        
        return diffChars==1;
    }
    
    private HashMap<String,HashSet<String>> createSubGraph(String beginWord,HashMap<String,HashSet<String>> graph,HashSet<String> wordSet){
        HashMap<String,HashSet<String>> subGraph = new HashMap();
        HashMap<String,Integer> distance = new HashMap(); // shortest distance from node to parent
        Queue<String> queue = new ArrayDeque();
        queue.add(beginWord);
        distance.put(beginWord,0);
        
        while(queue.size()>0){
            String parent = queue.remove();
            HashSet<String> neighbours = graph.get(parent);
            
            for(String neighbour : neighbours){
                if(distance.get(neighbour)==null){
                    subGraph.put(neighbour,new HashSet());
                    subGraph.get(neighbour).add(parent);
                    distance.put(neighbour,distance.get(parent)+1);
                    queue.add(neighbour);
                }else{
                    int prevDistance = distance.get(neighbour);
                    int currDistance = distance.get(parent)+1;
                    
                    if(currDistance==prevDistance) subGraph.get(neighbour).add(parent);
                    else if(currDistance<prevDistance){
                        subGraph.put(neighbour,new HashSet());
                        subGraph.get(neighbour).add(parent);
                        distance.put(neighbour,currDistance);
                    }
                }
            }
        }
        
        return subGraph;
    }
    
    private void dfs(HashMap<String,HashSet<String>> subGraph,String word,List<String> path,List<List<String>> allPaths){
        path.add(word);
        
        if(subGraph.get(word)==null){
            List<String> currPath = new ArrayList(path);
            Collections.reverse(currPath);
            allPaths.add(currPath);
        }else{
            HashSet<String> parents = subGraph.get(word);
            
            for(String parent : parents){
                dfs(subGraph,parent,path,allPaths);
            }
        }
        
        path.remove(path.size()-1);
    }
    
}