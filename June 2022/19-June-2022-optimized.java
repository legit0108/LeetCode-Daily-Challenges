// We can further improve the complexity of our code using two pointers
// TC : O(len*log(len)+strLen)
// SC : O(1)


class Solution {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Arrays.sort(products);
        List<List<String>> ans = new ArrayList();
        int strLen = searchWord.length();
        int len = products.length;
        int start = 0;
        int end = len-1;
        
        for(int idx=0;idx<strLen&&start<=end;idx++){
            List<String> list = new ArrayList();
            
            while(start<=end&&((products[start].length()<=idx)
            ||(products[start].charAt(idx)!=searchWord.charAt(idx)))) start++;
            
            while(start<=end&&((products[end].length()<=idx)
            ||(products[end].charAt(idx)!=searchWord.charAt(idx)))) end--;
            
                
            for(int currIdx = start;currIdx<=end&&list.size()<3;currIdx++){
                list.add(products[currIdx]);
            }
            
            ans.add(list);
        }
        
        while(ans.size()<strLen) ans.add(new ArrayList());
        return ans;
    }
}