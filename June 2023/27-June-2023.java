// TC: O(klogk)
// SC: O(k)

class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> minHeap;
        minHeap = new PriorityQueue<int[]>((pair1, pair2)->
                  Integer.compare(nums1[pair1[0]]+nums2[pair1[1]], nums1[pair2[0]]+nums2[pair2[1]]));
        minHeap.add(new int[]{0, 0});
        int len1 = nums1.length;
        int len2 = nums2.length;
        List<List<Integer>> list = new ArrayList();
        HashSet<String> visited = new HashSet();
        
        while(k>0 && list.size()<(long)len1*(long)len2){
            int[] indices = minHeap.remove();
            int index1 = indices[0];
            int index2 = indices[1];
            
            String hash = index1+","+index2;
            if(visited.contains(hash)) continue;
            visited.add(hash);
            
            List<Integer> pair = new ArrayList();
            pair.add(nums1[index1]);
            pair.add(nums2[index2]);
        
            list.add(pair);
            k--;
            
            if(index1+1<len1) minHeap.add(new int[]{index1+1, index2});
            if(index2+1<len2) minHeap.add(new int[]{index1, index2+1});
        }
        
        return list;
    }
}