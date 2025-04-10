import java.util.Queue;

public class FCFSScheduler extends Scheduler {

    public FCFSScheduler(Queue<Job> jobQueue, MemoryManager memoryManager) {
        super(jobQueue, memoryManager);
    }

    @Override
    public void scheduler() {
        int currentTime = 0;

        while (!readyQueue.isEmpty()) {
            Job currentJob = readyQueue.poll();
           
            // Simulate execution
            executeJob(currentJob);
            // Gantt Chart
            System.out.println(GC());
            

            // Set times
            currentJob.setWaitingTime(currentTime - currentJob.getArrivalTime());
            currentTime += currentJob.getBurstTime();
            currentJob.setTurnaroundTime(currentJob.getWaitingTime() + currentJob.getBurstTime());

            executedQueue.add(currentJob);

            if (memoryManager.deallocateMemory(currentJob.getMemoryRequired())) {
                addRemindJop(currentTime);
            }
        }

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
    }

    @Override
    public void addRemindJop(int currentTime) {
        while (!jobQueue.isEmpty()) {
            Job currentJob = jobQueue.peek();
            if (memoryManager.allocateMemory(currentJob.getMemoryRequired())) {
                currentJob.setState("Ready");
                currentJob.setArrivalTime(currentTime);
                readyQueue.add(currentJob);
                jobQueue.poll();
            } else {
                break;
            }
        }
    }
}
