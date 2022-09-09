// TC : O(len*log(len))
// SC : O(1)

class Solution {
    public int numberOfWeakCharacters(int[][] properties) {
        int len = properties.length;
        Arrays.sort(properties, (property1, property2)->(property1[0] == property2[0])?
                                Integer.compare(property1[1], property2[1]) : Integer.compare(property2[0], property1[0]));
        int maxDefense = properties[0][1];
        int weakCharacters = 0;
        
        for(int idx = 1;idx<len;idx++){
            if(properties[idx][1] < maxDefense) weakCharacters++;
            else maxDefense = properties[idx][1];
        }
        
        return weakCharacters;
    }
}