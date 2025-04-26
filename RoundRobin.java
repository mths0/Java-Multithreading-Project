
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class RoundRobin extends Scheduler {
    private final int QuantumTime = 7;
    private int currentTime ;
    private Start_load start_load;
    private final List<String> executionLog = new ArrayList<>();
    private final List<Integer> timeStamps = new ArrayList<>();

public RoundRobin(Queue<Job> jobQueue, MemoryManager memoryManager,Start_load Start_load) {
        super(jobQueue, memoryManager,Start_load);
        timeStamps.add(0);
        start_load=Start_load;
        currentTime = 0;
    }

    @Override
public void scheduler() {
        
boolean there_space=false;

       while (!readyQueue.isEmpty()&&!there_space) {
            Job job = readyQueue.poll();

            // Mark start time
            
            executionLog.add("P" + job.getId());

            int executionTime = Math.min(job.getBurstTime(), QuantumTime);

            
            
            // Execute
            executeJob(job, executionTime);
            currentTime += executionTime;
            timeStamps.add(currentTime);
            System.out.println(GC());
            if (job.getBurstTime() > 0) {
                // Not finished, go back to queue
                readyQueue.add(job);
            } else {
                // Job completed
                job.setTurnaroundTime(currentTime );
                job.setWaitingTime(job.getTurnaroundTime() - job.getOriginalBurstTime());
                executedQueue.add(job);
                job.setState("Terminated");

                // Free memory
                if (memoryManager.deallocateMemory(job.getMemoryRequired())) {
                	there_space=true;
                }
            }
        }
if(readyQueue.isEmpty()) {
        // Add final time to complete the Gantt Chart
        timeStamps.add(currentTime);

        // Gantt Chart
        System.out.println("\nGantt Chart:");
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
    }}

    public void executeJob(Job job, int executionTime) {
        try {
            Thread.sleep(executionTime * 10); // simulate time
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        job.setBurstTime(job.getBurstTime() - executionTime);
    }
  

    public String GC() {
        StringBuilder chart = new StringBuilder();

        for (int i = 0; i < executionLog.size(); i++) {
            chart.append("|").append(timeStamps.get(i)).append(" ").append(executionLog.get(i)).append(" ");
        }

        chart.append("|").append(timeStamps.get(timeStamps.size()-1 ));
        System.out.println("----------------------------------------------");
        return chart.toString();
    }
}
