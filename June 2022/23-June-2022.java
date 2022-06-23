// TC : O(nlogn)
// SC : O(n)
// where n is length of courses

class Solution {
    public int scheduleCourse(int[][] courses) {
        // sort course by lastDay , greedily try to finish courses
        // with earlier deadlines first
        
        Arrays.sort(courses,(course1,course2)->Integer.compare(course1[1],course2[1]));
        PriorityQueue<Integer> maxHeap;
        maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
        int len = courses.length;
        int currTime = 0;
        
        for(int idx=0;idx<len;idx++){
            int duration = courses[idx][0];
            int lastDay = courses[idx][1];
            
            // always add a course if possible
            // if current addition is not optimal
            // it can be taken care of in the future
            // if course cannot be added
            // look at the maximum duration course so far 
            // excluding this course will be the best choice 
            // if and only if duration of this course
            // is more than duration of current course
            
            if((currTime+duration)>lastDay){
                if((maxHeap.size()==0)||maxHeap.peek()<=duration) continue;
                int maxDuration = maxHeap.remove();
                currTime-=maxDuration;
            }
            
            currTime+=duration;
            maxHeap.add(duration);
        }
        
        //at the end our maxHeap contains maximum size subset of courses
        
        int size = maxHeap.size();
        return size;
    }
}