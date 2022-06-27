// TC : O(len)
// SC : O(1)

// Observation : The minimmum number of positive deci-binary
// numbers needed so that they sum up to n 
// will be same as max digit which occurs in n

class Solution {
    public int minPartitions(String n) {
        int len = n.length();
        char maxDig = n.charAt(0);
        
        for(int idx=1;idx<len;idx++){
            char currDig = n.charAt(idx);
            if(currDig>maxDig) maxDig = currDig;
        }
        
        int num = maxDig-'0';
        return num;
    }
}