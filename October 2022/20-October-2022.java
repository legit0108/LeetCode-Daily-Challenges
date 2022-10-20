// Implement brute force

class Solution {
    public String intToRoman(int num) {
        int vals[] = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String map[] = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int len = vals.length;
        StringBuilder romanNumeral = new StringBuilder();
        
        for(int idx=0; idx<len; idx++){
            int closestNum = vals[idx];
            String mappedVal = map[idx];
            
            while(num>=closestNum){
                romanNumeral.append(mappedVal);
                num-=closestNum;
            }
        }
        
        return romanNumeral.toString();
    }
}