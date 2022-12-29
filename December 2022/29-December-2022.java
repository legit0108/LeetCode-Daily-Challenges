// Solution-1: Sorting + heap, interview friendly code using classes

// TC: O(len + lenlog(len))
// SC: O(len)

class Solution {
    public int[] getOrder(int[][] tasks) {
        int len = tasks.length;
        int[] order = new int[len];
        Job[] jobs = new Job[len];
        PriorityQueue<int[]> CPU;
        CPU = new PriorityQueue<int[]>((job1, job2)->((job1[1]==job2[1])?
                                       Integer.compare(job1[0], job2[0]):
                                       Integer.compare(job1[1], job2[1]))); // {index, processing time}
                                                       // note that it is always better to use Integer.compare() rather than subtraction to avoid overflow
        
        for(int index=0; index<len; index++){
            int[] task = tasks[index];
            jobs[index] = new Job(index, task[0], task[1]);
        }
        
        Arrays.sort(jobs);
        int time = 0;
        int jobsIndex = 0;
        int orderIndex = 0;
        
        while(orderIndex<len){
            if(jobsIndex<len){
                Job job = jobs[jobsIndex];
                int actualIndex = job.index;
                int enqueueTime = job.enqueueTime;
                int processingTime = job.processingTime;
                
                if(CPU.size()==0 && time<enqueueTime) time = enqueueTime;
                
                while(jobsIndex<len && enqueueTime<=time){
                   CPU.add(new int[]{actualIndex, processingTime}); 
                   jobsIndex++;
                    
                   if(jobsIndex<len){
                       job = jobs[jobsIndex];
                       actualIndex = job.index;
                       enqueueTime = job.enqueueTime;
                       processingTime = job.processingTime;
                   } 
                }
            }
            
            int[] job = CPU.remove();
            order[orderIndex] = job[0];
            time+=job[1];
            orderIndex++;
        }
        
        return order;
    }
    
    private class Job implements Comparable<Job>{
        private int index;
        private int enqueueTime;
        private int processingTime;
        
        private Job(){

        }
        
        private Job(int index, int enqueueTime, int processingTime){
            this.index = index;
            this.enqueueTime = enqueueTime;
            this.processingTime = processingTime;
        }
        
        public int compareTo(Job other){
            return this.enqueueTime - other.enqueueTime;
        }
    }
}

// Solution-2: Sorting + heap, but using indexes rather than classes

// TC: O(len + lenlog(len))
// SC: O(len)


class Solution {
    public int[] getOrder(int[][] tasks) {
        int len = tasks.length;
        int[] order = new int[len];
        Integer[] jobs = new Integer[len];
        PriorityQueue<Integer> CPU;
        CPU = new PriorityQueue<Integer>((index1, index2)->((tasks[index1][1]==tasks[index2][1])?
                                       Integer.compare(index1, index2):
                                       Integer.compare(tasks[index1][1], tasks[index2][1])));
        
        for(int index=0; index<len; index++){
            jobs[index] = index;
        }
        
        Arrays.sort(jobs, (index1, index2)->Integer.compare(tasks[index1][0], tasks[index2][0]));
        int time = 0;
        int jobsIndex = 0;
        int orderIndex = 0;
        
        while(orderIndex<len){
            if(jobsIndex<len){
                int index = jobs[jobsIndex];
                int enqueueTime = tasks[index][0];
                int processingTime = tasks[index][1];
                
                if(CPU.size()==0 && time<enqueueTime) time = enqueueTime;
                
                while(jobsIndex<len && enqueueTime<=time){
                   CPU.add(index); 
                   jobsIndex++;
                    
                   if(jobsIndex<len){
                       index = jobs[jobsIndex];
                       enqueueTime = tasks[index][0];
                       processingTime = tasks[index][1];
                   } 
                }
            }
            
            int index = CPU.remove();
            order[orderIndex] = index;
            time+=tasks[index][1];
            orderIndex++;
        }
        
        return order;
    }
}