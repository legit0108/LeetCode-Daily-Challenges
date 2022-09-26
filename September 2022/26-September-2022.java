// TC : O(equations.length * alpha), alpha being constant can be ignored
// SC : O(26) / O(1)

class Solution {
    public boolean equationsPossible(String[] equations) {
        int parent[] = new int[26];
        int rank[] = new int[26];
        
        for(int var = 0; var < 26; var++) parent[var] = var;
        
        for(String equation : equations){
            char var1 = equation.charAt(0);
            char var2 = equation.charAt(3);
            
            if(equation.charAt(1) == '=') union(var1-'a', var2-'a', parent, rank);
        }
        
        for(String equation : equations){
            char var1 = equation.charAt(0);
            char var2 = equation.charAt(3);
            
            if(equation.charAt(1) == '!'){
                int par_var1 = find(var1-'a', parent);
                int par_var2 = find(var2-'a', parent);
                
                if(par_var1 == par_var2) return false;
            }
        }
        
        return true;
    }
    
    private void union(int var1, int var2, int parent[], int rank[]){
        int par_var1 = find(var1, parent);
        int par_var2 = find(var2, parent);
        
        if(par_var1 == par_var2) return;
        
        if(rank[par_var1] > rank[par_var2]){
            parent[par_var2] = par_var1;
        }else if(rank[par_var1] < rank[par_var2]){
            parent[par_var1] = par_var2;
        }else{
            parent[par_var2] = par_var1;
            rank[par_var1]++;
        }
    }
    
    private int find(int var, int parent[]){
        if(parent[var] == var) return var;
        return parent[var] = find(parent[var], parent);
    }
}