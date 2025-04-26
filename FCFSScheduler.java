import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class FCFSScheduler extends Scheduler {
     private boolean there_space;
     private int currentTime ;
    public FCFSScheduler(Queue<Job> jobQueue, MemoryManager memoryManager,Start_load Start_load) {
        super(jobQueue, memoryManager,Start_load);
        there_space=false;
        currentTime = 0;
    }

    @Override
    public void scheduler() {
        
        there_space=false;
        while (!readyQueue.isEmpty()&&!there_space) {
            Job currentJob = readyQueue.poll();
           
            // Simulate execution
            executeJob(currentJob);
            executedQueue.add(currentJob);
            // Gantt Chart
            System.out.println(GC());
            

            // Set times
            currentJob.setWaitingTime(currentTime );
            currentTime += currentJob.getBurstTime();
            currentJob.setTurnaroundTime(currentJob.getWaitingTime() + currentJob.getBurstTime());
            currentJob.setState("Terminated");
            

            if (memoryManager.deallocateMemory(currentJob.getMemoryRequired())) {
               there_space=true;
            }
        }
if(readyQueue.isEmpty()) {
        // Print Gantt Chart
        System.out.println("\nGantt Chart:");
        System.out.println(GC());

        // Print Results
        System.out.println("\nResults:");
        int totalWaiting = 0;
        int totalTurnaround = 0;

        for (Job job : executedQueue) {
      
            totalWaiting += job.getWaitingTime();
            totalTurnaround += job.getTurnaroundTime();
        }

        System.out.printf("Average Waiting Time: %.2f ms\n"   , (double) totalWaiting / executedQueue.size());
        System.out.printf("Average Turnaround Time: %.2f ms\n", (double) totalTurnaround / executedQueue.size());
    }}

  
   
}
