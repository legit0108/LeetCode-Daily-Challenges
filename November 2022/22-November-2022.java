// Solution - 1 : DP

// TC : O(n*sqrt(n))
// SC : O(n)

class Solution {
    public int numSquares(int n) {
        int dp[] = new int[n+1];
        dp[1] = 1;
        
        for(int currNum= 2; currNum<=n; currNum++){
            int ans = Integer.MAX_VALUE;
            
            for(int num=1; num*num<=currNum; num++){
                ans = Math.min(ans, dp[currNum-num*num]+1);
            }
            
            dp[currNum] = ans;
        }
        
        return dp[n];
    }
}

// Solution - 2 : BFS

// TC : O(n*sqrt(n))
// SC : O(n)

class Solution {
    public int numSquares(int n) {
        Queue<Integer> queue = new ArrayDeque();
        Set<Integer> visited = new HashSet();
        queue.add(0);
        
        int leastPerfectSquares = 0;
        
        while(queue.size()>0){
           int size = queue.size();
           
           while(size>0){
               int num = queue.remove();
               size--;
               
               if(visited.contains(num)) continue;
               if(num==n) return leastPerfectSquares;
               
               visited.add(num);
               
               for(int perfectSquare = 1; num+perfectSquare*perfectSquare<=n; perfectSquare++){
                   int nextNum = num+perfectSquare*perfectSquare;
                   
                   if(!visited.contains(nextNum)) queue.add(nextNum);
               }
           } 
            
           leastPerfectSquares++; 
        }
        
        return -1; // never hit
    }
}

// Solution - 3 : Lagrange's four square theorem

// TC : O(sqrt(n))
// SC : O(1)

class Solution {
    public int numSquares(int n) {
        int sqrt = (int)(Math.sqrt(n));
        if((sqrt*sqrt) == n) return 1;
        
        for(int num1 = 1; num1*num1<=n; num1++){
            int diff = n-num1*num1;
            
            int num2 = (int)(Math.sqrt(diff));
            if((num2*num2) == diff) return 2;
        }
        
        while(n%4==0) n/=4;
        
        if(n%8!=7) return 3;
        else return 4;
    }
}