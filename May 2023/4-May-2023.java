/*
 Solution-1: 2 queues
 
 -> Maintain 2 queues, one for Dire, other for Radiant
 
 -> For a member, it is optimal to eliminate the first member of opposite party,
    not only are we banning memmber of opposite party, we are also potentially 
    saving member of our party
    
    Ex: Don't ban first: DRDRDR -> DRDRD_ -> DR_RD_ -> _R_RD_ -> _R_D_ -> _R____ (R wins, if D would have chosen first to ban, D wins)
   
 -> Two earliest members of 2 parties fight, whoever wins gets added back to their queue with increment of n where n = senate.length()
    (Why do we increment by n? Member of opposite party who is present at very end i.e. n-1 can also ban current member, provided current member is first available to ban for opposite party, we also add current member to the end since we will now process this member in the next round (give this member another chance to ban someone in the next round))
    Note that we do not necessarily need to increment by n, we just need to make sure the index we add is greater than all other indices, which is what is done in the final solution
    
 -> Every senator bans some other senator, so length of senate decreases by half each round
    
    If length of senate is n:
    TC: O(n + n/2 + n/4 + .....) = O(2n)
    
 
 TC: O(n)
 SC: O(n)
*/

class Solution {
    public String predictPartyVictory(String senate) {
        int len = senate.length();
        int lastIndex = len;
        
        Queue<Integer> radiant = new ArrayDeque();
        Queue<Integer> dire = new ArrayDeque();
        
        for(int index=0; index<len; index++){
            char senator = senate.charAt(index);
            
            if(senator=='D') dire.add(index);
            else radiant.add(index);
        }
        
        while(radiant.size()>0 && dire.size()>0){
            int radiantIndex = radiant.remove();
            int direIndex = dire.remove();
            
            if(radiantIndex<direIndex) radiant.add(lastIndex);
            else dire.add(lastIndex);
            
            lastIndex++;
        }
        
        if(dire.size()==0) return "Radiant";
        else return "Dire";
    }
}


// -----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----


/*
 Solution-2: Implement Solution-1 using 1 queue
 
 -> Pop front of queue
 
 -> Senator eligible to ban -> float ban on opponent party
    Do not ban a member of the opposite party right now, but in the future (lazy banning)
    Note that banned senator will correspond to the first, i.e., immediate rival of current senator 
    After doing so, push current senator to back of the queue, similar as in first solution
 
 -> Senator not eligible to ban -> implement lazy banning, ignore the senator
    Banning is marked by not adding again in the queue
    
 -> Minute optimization: Maintain count of eligible senators
 
 TC: O(n)
 SC: O(n)
*/

class Solution {
    public String predictPartyVictory(String senate) {
        int len = senate.length();
        int eligibleDires = 0;
        int eligibleRadiants = 0;
        Queue<Character> queue = new ArrayDeque();
        
        for(int index=0; index<len; index++){
            char senator = senate.charAt(index);
            
            if(senator=='R') eligibleRadiants++;
            else eligibleDires++;
            
            queue.add(senator);
        }
        
        int bannedDires = 0;
        int bannedRadiants = 0;
        
        while(eligibleRadiants>0 && eligibleDires>0){
            char senator = queue.remove();
            
            if(senator=='D'){
                if(bannedDires>0){
                    bannedDires--;
                    eligibleDires--;
                }else{
                    bannedRadiants++;
                    queue.add(senator);
                }
            }else{
                if(bannedRadiants>0){
                    bannedRadiants--;
                    eligibleRadiants--;
                }else{
                    bannedDires++;
                    queue.add(senator);
                }
            }
        }
        
        if(eligibleRadiants>0) return "Radiant";
        else return "Dire";
    }
}