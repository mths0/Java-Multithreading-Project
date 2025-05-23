
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class Priority extends Scheduler {

    private PriorityQueue<Job> priorityQueue;
    private int starvationThreshold ;// change this threshold if needed
    private int currentTime = 0;
    private boolean there_space;
    private List<Job> starvedJobs ;
    public Priority(Queue<Job> jobQueue, MemoryManager memoryManager,Start_load Start_load) {
        super(jobQueue, memoryManager,Start_load);
        priorityQueue = new PriorityQueue<>();
        starvationThreshold = 50;
        starvedJobs = new ArrayList<>();
    }

    @Override
    public void scheduler() {
    	there_space=false;
      Job firstJob=readyQueue.peek();
      int i=0;
        // Move all ready jobs to priority queue
        while (!readyQueue.isEmpty()) {
            Job currentJob = readyQueue.poll();
            if(firstJob==currentJob&&i!=0)break;
            if(!priorityQueue.find(currentJob)) {
            	currentJob.setArrivalTime(currentTime);
            priorityQueue.Enqueue(currentJob, currentJob.getPriority());}
            readyQueue.add(currentJob);
         i++;
        } 
        
        
        // Serve jobs based on priority
        while (!priorityQueue.isEmpty()&&!there_space) {
            Job currentJob = priorityQueue.serve();
            readyQueue.remove(currentJob);
            executeJob(currentJob);
            currentJob.setState("Terminated");
            executedQueue.add(currentJob);
            System.out.println(GC());

            currentJob.setWaitingTime(currentTime - currentJob.getArrivalTime());
            currentTime += currentJob.getBurstTime();
            currentJob.setTurnaroundTime(currentJob.getWaitingTime() + currentJob.getBurstTime());

            

            

            if (memoryManager.deallocateMemory(currentJob.getMemoryRequired())&&!jobQueue.isEmpty()) {
               
            	there_space=true;
            }
        }
if(priorityQueue.isEmpty()) {
        // Gantt Chart
        
        System.out.println(GC());

        
        System.out.println("\nResults:");
        int totalWaiting = 0;
        int totalTurnaround = 0;

        for (Job job : executedQueue) {
   
            totalWaiting += job.getWaitingTime();
            totalTurnaround += job.getTurnaroundTime();
        }

       double avgWaitingTime=(double) totalWaiting / executedQueue.size();
       double avgTurnaroundTime=(double) totalTurnaround / executedQueue.size();
        System.out.printf("Average Waiting Time: %.2f ms\n", avgWaitingTime);
        System.out.printf("Average Turnaround Time: %.2f ms\n", avgTurnaroundTime);
        while (!executedQueue.isEmpty()) {
            Job job = executedQueue.poll();
            if(job.getWaitingTime()>=avgWaitingTime)starvedJobs.add(job);
        }

        
        if (!starvedJobs.isEmpty()) {
            System.out.println("\nStarved Jobs Detected:");
            for (Job j : starvedJobs) {
                System.out.println("P" + j.getId() + " | Waited: " + j.getWaitingTime() + "ms");
            }
        }
    }}

    
   
}
