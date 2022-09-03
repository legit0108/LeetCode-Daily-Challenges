// Method-1 : DFS / Recursion

// TC : O(2^n)
// SC : O(2^n)

class Solution {
    public int[] numsSameConsecDiff(int n, int k) {
        List<Integer> ansList = new ArrayList();
        
        for(int dig = 1;dig<=9;dig++){
            generate(ansList, dig, k, 1, n);
        }
        
        int size = ansList.size();
        int ansArr[] = new int[size];
        for(int idx=0;idx<size;idx++) ansArr[idx] = ansList.get(idx);
        
        return ansArr;
    }
    
    private void generate(List<Integer> ansList, int num, int k, int lenSoFar, int len){
        if(lenSoFar==len){
            ansList.add(num);
            return;
        }
        
        int lastDig = num%10;
        
        if(lastDig+k<=9){
            int nextDig = lastDig+k;
            generate(ansList, num*10+nextDig, k, lenSoFar+1, len);
        }
        
        if(k>0 && lastDig-k>=0){
            int nextDig = lastDig-k;
            generate(ansList, num*10+nextDig, k, lenSoFar+1, len);
        }
    }
}

// Method-2 : BFS / Iteration

// TC : O(2^n)
// SC : O(2^n)

class Solution {
    public int[] numsSameConsecDiff(int n, int k) {
        List<Integer> ansList = new ArrayList();
        generate(ansList, k, n);
        
        int size = ansList.size();
        int ansArr[] = new int[size];
        for(int idx=0;idx<size;idx++) ansArr[idx] = ansList.get(idx);
        
        return ansArr;
    }
    
    private void generate(List<Integer> ansList, int k, int len){
        Queue<Integer> queue = new ArrayDeque();
        for(int dig = 1;dig<=9;dig++) queue.add(dig);
        int currLen = 1;
        
        while(queue.size()>0){
            int size = queue.size();
            
            while(size>0){
                int num = queue.remove();
                size--;
                
                if(currLen==len){
                    ansList.add(num);
                    continue;
                }
                
                int lastDig = num%10;
                
                if(lastDig+k<=9) queue.add(num*10 + (lastDig+k));
                if(k>0 && lastDig-k>=0) queue.add(num*10 + (lastDig-k));
            }
            
            currLen++;
        }
    }
}