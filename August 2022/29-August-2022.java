// DFS
// TC : O(rows*cols)
// SC : O(rows*cols)

class Solution {
    public int numIslands(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int islands = 0;
        boolean visited[][] = new boolean[rows][cols];
        
        for(int row=0;row<rows;row++){
            for(int col=0;col<cols;col++){
                if(grid[row][col]=='1'&&!visited[row][col]){
                    dfs(grid,row,col,rows,cols,visited);
                    islands++;
                }
            }
        }
        
        return islands;
    }
    
    private void dfs(char grid[][],int row,int col,int rows,int cols,boolean visited[][]){
        if(row<0||col<0||row>=rows||col>=cols||grid[row][col]=='0'||visited[row][col]) return;

        visited[row][col] = true;
        
        dfs(grid,row-1,col,rows,cols,visited);
        dfs(grid,row+1,col,rows,cols,visited);
        dfs(grid,row,col-1,rows,cols,visited);
        dfs(grid,row,col+1,rows,cols,visited);
    }
}

// BFS
// TC : O(rows*cols)
// SC : O(rows*cols)

class Solution {
    public int numIslands(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int islands = 0;
        boolean visited[][] = new boolean[rows][cols];
        
        for(int row=0;row<rows;row++){
            for(int col=0;col<cols;col++){
                if(grid[row][col]=='1'&&!visited[row][col]){
                    bfs(grid,row,col,rows,cols,visited);
                    islands++;
                }
            }
        }
        
        return islands;
    }
    
    private void bfs(char grid[][],int row,int col,int rows,int cols,boolean visited[][]){
        Queue<int[]> queue = new ArrayDeque();
        queue.add(new int[]{row,col});
        
        while(queue.size()>0){
            int cell[] = queue.remove();
            int currRow = cell[0];
            int currCol = cell[1];
            
            if(visited[currRow][currCol]) continue;
            visited[currRow][currCol] = true;
            
            if(currRow-1>=0&&grid[currRow-1][currCol]=='1'&&!visited[currRow-1][currCol]) 
                queue.add(new int[]{currRow-1,currCol});
            if(currRow+1<rows&&grid[currRow+1][currCol]=='1'&&!visited[currRow+1][currCol]) 
                queue.add(new int[]{currRow+1,currCol});
            if(currCol-1>=0&&grid[currRow][currCol-1]=='1'&&!visited[currRow][currCol-1]) 
                queue.add(new int[]{currRow,currCol-1});
            if(currCol+1<cols&&grid[currRow][currCol+1]=='1'&&!visited[currRow][currCol+1]) 
                queue.add(new int[]{currRow,currCol+1});
        }
    }
}

// Union-Find, 2 directions is enough
// TC : O(rows*cols*alpha), alpha being constant can be ignored
// SC : O(rows*cols)

class Solution {
    public int numIslands(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int islands = 0;
        int parent[] = new int[rows*cols];
        int rank[] = new int[rows*cols];
        
        for(int row=0;row<rows;row++){
            for(int col=0;col<cols;col++){
                int cell = row*cols+col;
                parent[cell] = cell;
                
                if(grid[row][col]=='1') islands++;
            }
        }
        
        for(int row=0;row<rows;row++){
            for(int col=0;col<cols;col++){
                if(grid[row][col]=='1'){
                    islands-=union(grid,row,col,row+1,col,rows,cols,parent,rank);
                    islands-=union(grid,row,col,row,col+1,rows,cols,parent,rank);
                }
            }
        }
        
        return islands;
    }
    
    private int union(char[][] grid,int row1,int col1,int row2,int col2,int rows,int cols,int parent[],int rank[]){
        if(row1<0||col1<0||row1>=rows||col1>=cols||row2<0||col2<0||row2>=rows||col2>=cols||grid[row1][col1]!='1'
        ||grid[row2][col2]!='1') return 0;
        
        int cell1 = row1*cols+col1;
        int cell2 = row2*cols+col2;
        
        int parentCell1 = find(parent,cell1);
        int parentCell2 = find(parent,cell2);
        
        if(parentCell1==parentCell2) return 0;
        
        if(rank[parentCell1]>rank[parentCell2]){
            parent[parentCell2] = parentCell1;
        }else if(rank[parentCell1]<rank[parentCell2]){
            parent[parentCell1] = parentCell2;
        }else{
            parent[parentCell2] = parentCell1;
            rank[parentCell1]++;
        }
        
        return 1;
    }
    
    private int find(int parent[],int cell){
        if(parent[cell]==cell) return cell;
        return parent[cell] = find(parent,parent[cell]);
    }
}