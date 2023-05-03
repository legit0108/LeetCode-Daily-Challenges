// Solution-1: Two sets
// TC: O(nums1.length + nums2.length)
// SC: O(nums1.length + nums2.length)

class Solution {
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet();
        Set<Integer> set2 = new HashSet();
        
        for(int num : nums1) set1.add(num);
        for(int num : nums2) set2.add(num);
        
        List<List<Integer>> list = new ArrayList();
        List<Integer> list1 = new ArrayList();
        List<Integer> list2 = new ArrayList();
        list.add(list1);
        list.add(list2);
        
        for(int num : set1){
            if(!set2.contains(num)) list1.add(num);
        }
        for(int num : set2){
            if(!set1.contains(num)) list2.add(num);
        }
        
        return list;
    }
}


// Solution-2: One Map
// TC: O(nums1.length + nums2.length)
// SC: O(nums1.length + nums2.length)

class Solution {
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap();
        
        for(int num: nums1) map.put(num, 1);
        for(int num: nums2){
            if(map.containsKey(num)){
                int flag = map.get(num);
                
                if(flag==1) map.put(num, 3);
                else continue;
            }else map.put(num, 2);
        }
        
        List<Integer> list1 = new ArrayList();
        List<Integer> list2 = new ArrayList();
        
        for(int num: map.keySet()){
            int flag = map.get(num);
            
            if(flag==1) list1.add(num);
            else if(flag==2) list2.add(num);
        }
        
        List<List<Integer>> answer = new ArrayList();
        answer.add(list1);
        answer.add(list2);
        
        return answer;
    }
}