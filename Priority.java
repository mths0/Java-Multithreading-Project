import java.util.*;

public class Priority extends Scheduler {

    protected PriorityQueue<Job> priorityQueue;

    public Priority(Queue<Job> jobQueue, MemoryManager memoryManager) {
        super(jobQueue, memoryManager);
        priorityQueue = new PriorityQueue<>();
    }

    @Override
    public void scheduler() {
        int currentTime = 0;
        List<Job> starvedJobs = new ArrayList<>();
        int starvationThreshold = 50; // change this threshold if needed

        // Move all ready jobs to priority queue
        while (!readyQueue.isEmpty()) {
            Job currentJob = readyQueue.poll();
            priorityQueue.Enqueue(currentJob, currentJob.getPriority());
        }

        // Serve jobs based on priority
        while (!priorityQueue.isEmpty()) {
            Job currentJob = priorityQueue.serve();
            executeJob(currentJob);
            System.out.println(GC());

            currentJob.setWaitingTime(currentTime - currentJob.getArrivalTime());
            currentTime += currentJob.getBurstTime();
            currentJob.setTurnaroundTime(currentJob.getWaitingTime() + currentJob.getBurstTime());

            // Starvation check
            if (currentJob.getWaitingTime() > starvationThreshold) {
                starvedJobs.add(currentJob);
            }

            executedQueue.add(currentJob);

            if (memoryManager.deallocateMemory(currentJob.getMemoryRequired())) {
                addRemindJop(currentTime);
            }
        }

        // Gantt Chart
        
        System.out.println(GC());

        // Results Summary
        System.out.println("\nResults:");
        int totalWaiting = 0;
        int totalTurnaround = 0;

        for (Job job : executedQueue) {
   
            totalWaiting += job.getWaitingTime();
            totalTurnaround += job.getTurnaroundTime();
        }

        System.out.printf("Average Waiting Time: %.2f ms\n", (double) totalWaiting / executedQueue.size());
        System.out.printf("Average Turnaround Time: %.2f ms\n", (double) totalTurnaround / executedQueue.size());

        // Print starved jobs
        if (!starvedJobs.isEmpty()) {
            System.out.println("\nStarved Jobs Detected:");
            for (Job j : starvedJobs) {
                System.out.println("P" + j.getId() + " | Waited: " + j.getWaitingTime() + "ms");
            }
        }
    }

    @Override
    public void addRemindJop(int currentTime) {
        while (!jobQueue.isEmpty()) {
            Job currentJob = jobQueue.peek();
            if (memoryManager.allocateMemory(currentJob.getMemoryRequired())) {
                currentJob.setArrivalTime(currentTime);
                priorityQueue.Enqueue(currentJob, currentJob.getPriority());
                jobQueue.poll();
            } else {
                break;
            }
        }
    }
}
