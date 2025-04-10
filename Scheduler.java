import java.util.LinkedList;
import java.util.Queue;

public abstract class Scheduler extends Thread {

    protected MemoryManager memoryManager;
    protected Queue<Job> readyQueue;
    protected Queue<Job> executedQueue;
    protected Queue<Job> jobQueue;

    public Scheduler(Queue<Job> jobQueue, MemoryManager memoryManager) {
        this.jobQueue = jobQueue;
        this.memoryManager = memoryManager;
        this.readyQueue = new LinkedList<>();
        this.executedQueue = new LinkedList<>();
    }

    // Thread to fill ready queue (based on memory availability)
    @Override
    public void run() {
        while (!jobQueue.isEmpty()) {
            Job currentJob = jobQueue.peek();
            if (memoryManager.allocateMemory(currentJob.getMemoryRequired())) {
                readyQueue.add(currentJob);
                currentJob.setState("Ready");
                System.out.println(currentJob.getId() + " " + currentJob.getState()); //! remove line
                jobQueue.poll();
            } else {
                break;

            }
        }

        // Start actual scheduling after job preparation
        scheduler();
    }

    public abstract void scheduler();
    public abstract void addRemindJop(int currentTime);

    public void executeJob(Job job) {
        
        job.setState("Running");
       
        try {
            // Simulate execution time
            Thread.sleep(job.getBurstTime() ); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }

    public void calculateWaitingTime() {}  // can be overridden by child
    public void calculateTurnaroundTime() {}

    public String GC() {
        StringBuilder chart = new StringBuilder();
        int currentTime = 0;
        
        for (Job job : executedQueue) {
            chart.append("|").append(currentTime).append(" P").append(job.getId()).append(" ");
            currentTime += job.getBurstTime();
        }
    
        chart.append("|").append(currentTime); // Final time at the end
        System.out.println("----------------------------------------------");
        return chart.toString();
    }
    
}
