// We need to look at terminal values of p and q and
// then find receptor hit 
// we only look at terminal values 
// because for original values , we can always extend 
// our mirror and obtain same result (same receptor hit)
// as that obtained with terminal values
// we obtain terminal values by removing all factors of 2
// from p and q till we can't do it for either p or q
// for terminal values we can obtain receptor hit
// by dry running base test cases

// TC : O(log(min(p,q)))
// SC : O(1)

class Solution {
    public int mirrorReflection(int p, int q) {
        while(p%2==0&&q%2==0){
            p/=2; // p>>=1 for faster execution
            q/=2; // q>>=1 for faster execution
        }
        
        if(p%2==0&&q%2!=0) return 2;
        else if(p%2!=0&&q%2==0) return 0;
        else return 1; // p and q both are odd , we never obtain even terminal values for both p,q
        
    }
}