// Solution-1: Using List of List

// TC: O(len)
// SC: O(len)

class Solution {
    public String convert(String s, int numRows) {
        int len = s.length();
        int rows = numRows;
        
        if(rows==1 || len==1) return s;
        
        List<List<Character>> lists = new ArrayList<List<Character>>();

        int row = 0;
        int idx = 0;
        int incr = 0;
        
        while(idx<len){
           if(row>=lists.size()) lists.add(new ArrayList<Character>());
            
           List<Character> list = lists.get(row);
           list.add(s.charAt(idx));
           
           if(row==0) incr = 1;
           else if(row==numRows-1) incr = -1;
           
           idx++;
           row+=incr; 
        }
        
        StringBuilder str = new StringBuilder();
        
        for(List<Character> list: lists){
            for(char ch: list) str.append(ch);
        }
        
        return str.toString();
    }
}


// Solution-2: One pass

// TC: O(len)
// SC: O(1), ignoring the space required for output

class Solution {
    public String convert(String s, int numRows) {
        int len = s.length();
        int rows = numRows;
        
        if(rows==1 || len==1) return s;
        
        StringBuilder str = new StringBuilder();

        int charsInSection = 2*(numRows-1);
        int row = 0;
        
        while(row<rows){
            int idx = row;
            
            while(idx<len){
                str.append(s.charAt(idx));
                
                if(row>0 && row<rows-1){
                // If current row is not the first or last row
                // then we have to add one more character of current section.
                    
                    int secondIdx = idx+(charsInSection-2*row);
                    
                    if(secondIdx<len) str.append(s.charAt(secondIdx));
                }
                
                // Jump to same row's first character of next section.
                idx+=charsInSection;
            }
            
            row++;
        }
        
        return str.toString();
    }
}