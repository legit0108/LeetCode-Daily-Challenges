// TC : O(len*log(len)+strLen*log(len)) (treat string manipulations as O(1))
// SC : O(1) (ignore output space)

class Solution {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Arrays.sort(products);
        List<List<String>> ans = new ArrayList();
        int strLen = searchWord.length();
        int len = products.length;
        
        for(int idx=0;idx<strLen;idx++){
            String str = searchWord.substring(0,idx+1);
            int binIdx = binarySearch(products,str);
            List<String> list = new ArrayList();
            
            if(binIdx!=-1){
                int start = binIdx;
                int end = Math.min(binIdx+2,len-1);
                
                for(int currIdx = start;currIdx<=end;currIdx++){
                    if(products[currIdx].startsWith(str)) list.add(products[currIdx]);
                }
            }
            
            ans.add(list);
        }
        
        return ans;
    }
    
    private int binarySearch(String arr[],String str){
        int low = 0;
        int high = arr.length-1;
        int idx = -1;
        
        while(low<=high){
            int mid = low+(high-low)/2;
            if(arr[mid].compareTo(str)<0) low = mid+1;
            else if(arr[mid].compareTo(str)>0){
                idx = mid;
                high = mid-1;
            }else{
                idx = mid;
                break;
            }
        }
        
        return idx;
    }
}