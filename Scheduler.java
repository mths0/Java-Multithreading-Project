import java.util.LinkedList;
import java.util.Queue;

public abstract class Scheduler extends Thread {

    protected MemoryManager memoryManager;
    protected Queue<Job> readyQueue;
    protected Queue<Job> waitingQueue;
    protected Queue<Job> executedQueue;
    protected Queue<Job> jobQueue;

    public Scheduler(Queue<Job> jobQueue, MemoryManager memoryManager) {
        this.jobQueue = jobQueue;
        this.memoryManager = memoryManager;

        this.readyQueue = new LinkedList<>();
        this.waitingQueue = new LinkedList<>();
        this.executedQueue = new LinkedList<>();
    }

    // Thread to fill ready queue (based on memory availability)
    @Override
    public void run() {
        while (!jobQueue.isEmpty()) {
            Job currentJob = jobQueue.peek();
            if (memoryManager.allocateMemory(currentJob.getMemoryRequired())) {
                currentJob = jobQueue.poll();
                readyQueue.add(currentJob);

                currentJob.setState("Ready");
                System.out.println(currentJob.getId() + " " + currentJob.getState()); //! remove line
            } else {
                break;
                // currentJob.setState("Waiting");
                //System.out.println(currentJob.getId() + " " + currentJob.getState()); //! remove line
            }
        }

        // Start actual scheduling after job preparation
        scheduler();
    }

    public abstract void scheduler();
    public abstract void addRemaindJop(int currentTime);

    public void executeJob(Job job) {
        //System.out.println("Executing Job ID: " + job.getId());
        job.setState("Running");
        //System.out.println(job.getId() + " " + job.getState()); //! remove line
        try {
            Thread.sleep(job.getBurstTime() ); // Simulate execution time
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //System.out.println("Finished Job ID: " + job.getId());
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
