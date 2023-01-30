class Solution {
    public int tribonacci(int n) {
        if(n==0) return 0;
        else if(n<=2) return 1;
        else{
            int Tn1 = 1;
            int Tn2 = 1;
            int Tn3 = 0;
            
            for(int itr=3; itr<=n; itr++){
                int Tn = Tn1+Tn2+Tn3;
                Tn3 = Tn2;
                Tn2 = Tn1;
                Tn1 = Tn;
            }
            
            return Tn1;
        }
    }
}