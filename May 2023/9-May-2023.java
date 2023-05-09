// Simulation

// TC: O(rows*cols)
// SC: O(1) ignoring output space

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList();
        int rows = matrix.length;
        int cols = matrix[0].length;
        int cells = rows*cols;
        Coordinates coordinates = new Coordinates(0, 0, rows-1, cols-1); // startRow, startCol, endRow, endCol
        int direction = 0;
        
        while(list.size()<cells){
            addElements(list, coordinates, matrix, cells, direction);
            direction = (direction+1)%4;
        }
        
        return list;
    }
    
    private void addElements(List<Integer> list, Coordinates coordinates, int[][] matrix, int cells, int direction){ // only this function needs to be changed if let's say you want to traverse the matrix in counterclockwise direction
        int startRow = coordinates.startRow;
        int startCol = coordinates.startCol;
        int endRow = coordinates.endRow;
        int endCol = coordinates.endCol;
        
        if(direction==0){
            addRow(startRow, startCol, endCol, matrix, list, cells);
            coordinates.startRow++;
        }else if(direction==1){
            addCol(endCol, startRow, endRow, matrix, list, cells);
            coordinates.endCol--;
        }else if(direction==2){
            addRow(endRow, endCol, startCol, matrix, list, cells);
            coordinates.endRow--;
        }else if(direction==3){
            addCol(startCol, endRow, startRow, matrix, list, cells);
            coordinates.startCol++;
        }
    }
    
    private void addRow(int row, int startCol, int endCol, int[][] matrix, List<Integer> list, int cells){
        int col = startCol;
        
        if(startCol<endCol){
            while(col<=endCol && list.size()<cells){
                list.add(matrix[row][col]);
                col++;
            }
        }else{
            while(col>=endCol && list.size()<cells){
                list.add(matrix[row][col]);
                col--;
            }
        }
    }
    
    private void addCol(int col, int startRow, int endRow, int[][] matrix, List<Integer> list, int cells){
        int row = startRow;
        
        if(startRow<endRow){
            while(row<=endRow && list.size()<cells){
                list.add(matrix[row][col]);
                row++;
            }
        }else{
            while(row>=endRow && list.size()<cells){
                list.add(matrix[row][col]);
                row--;
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
}