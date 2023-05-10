// Simulation, similar to 54. Spiral Matrix

// TC: O(n*n)
// SC: O(1) excluding output space

class Solution {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        Coordinates coordinates = new Coordinates(0, 0, n-1, n-1); // startRow, startCol, endRow, endCol
        Pair pair = new Pair(1, 0); // cellNumber, direction
        int cells = n*n;
        
        while(pair.cellNumber<=cells){ 
            fillCells(matrix, coordinates, pair);
            pair.direction = (pair.direction+1)%4;
        }
        
        return matrix;
    }
    
    private void fillCells(int[][] matrix, Coordinates coordinates, Pair pair){ // only this function needs to be changed if we want to fill cells in a counterclockwise order
        int startRow = coordinates.startRow;
        int startCol = coordinates.startCol;
        int endRow = coordinates.endRow;
        int endCol = coordinates.endCol;
        int direction = pair.direction;
        
        if(direction==0){
            fillRow(matrix, pair, startRow, startCol, endCol);
            coordinates.startRow++;
        }else if(direction==1){
            fillCol(matrix, pair, endCol, startRow, endRow);
            coordinates.endCol--;
        }else if(direction==2){
            fillRow(matrix, pair, endRow, endCol, startCol);
            coordinates.endRow--;
        }else if(direction==3){
            fillCol(matrix, pair, startCol, endRow, startRow);
            coordinates.startCol++;
        }
    }
    
    private void fillRow(int[][] matrix, Pair pair, int row, int startCol, int endCol){
        int col = startCol;
        
        if(startCol<endCol){
            while(col<=endCol){
                matrix[row][col] = pair.cellNumber;
                col++;
                pair.cellNumber++;
            }
        }else{
            while(col>=endCol){
                matrix[row][col] = pair.cellNumber;
                col--;
                pair.cellNumber++;
            }
        }
    }
    
    private void fillCol(int[][] matrix, Pair pair, int col, int startRow, int endRow){
        int row = startRow;
        
        if(startRow<endRow){
            while(row<=endRow){
                matrix[row][col] = pair.cellNumber;
                row++;
                pair.cellNumber++;
            }
        }else{
            while(row>=endRow){
                matrix[row][col] = pair.cellNumber;
                row--;
                pair.cellNumber++;
            }
        }
    }
    
    private class Coordinates{
        int startRow;
        int startCol;
        int endRow;
        int endCol;
        
        Coordinates(){}
        
        Coordinates(int startRow, int startCol, int endRow, int endCol){
            this.startRow = startRow;
            this.startCol = startCol;
            this.endRow = endRow;
            this.endCol = endCol;
        }
    }
    
    private class Pair{
        int cellNumber;
        int direction;
        
        Pair(){}
        
        Pair(int cellNumber, int direction){
            this.cellNumber = cellNumber;
            this.direction = direction;
        }
    }
}


// -----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----


// Solution-2: Shorter code using dirs array

// TC: O(n*n)
// SC: O(1) excluding output space

class Solution {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int dirs[][] = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int row = 0;
        int col = 0;
        int cells = n*n;
        int cellNumber = 1;
        int direction = 0;
        
        while(cellNumber<=cells){ 
            matrix[row][col] = cellNumber++;
            
            int[] dir = dirs[direction];
            int rowDelta = dir[0];
            int colDelta = dir[1];
            
            int nextRow = ((row+rowDelta)+n)%n;
            int nextCol = ((col+colDelta)+n)%n;
            
            if(matrix[nextRow][nextCol]!=0) direction = (direction+1)%4;
            
            dir = dirs[direction];
            rowDelta = dir[0];
            colDelta = dir[1];
            
            row+=rowDelta;
            col+=colDelta;
        }
        
        return matrix;
    }
}