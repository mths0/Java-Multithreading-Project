import java.util.LinkedList;
import java.util.Queue;

public abstract class Scheduler extends Thread {

    protected MemoryManager memoryManager;
    protected Queue<Job> readyQueue;
    protected Queue<Job> executedQueue;
    protected Queue<Job> jobQueue;
    private Start_load Start_load;
    public Scheduler(Queue<Job> jobQueue, MemoryManager memoryManager,Start_load Start_load) {
        this.jobQueue = jobQueue;
        this.memoryManager = memoryManager;
        this.readyQueue = new LinkedList<>();
        this.executedQueue = new LinkedList<>();
        this.Start_load=Start_load;
    }

    // Thread to fill ready queue and implement (based on memory availability)
    @Override
    public void run() {
    	while(!Start_load.Start_load);// waiting to jobloader be load
    	try {
			Thread.sleep(55);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
    	double S=System.currentTimeMillis();
        while (!jobQueue.isEmpty()||!readyQueue.isEmpty()) {
            Job currentJob = jobQueue.peek();
            if (currentJob!=null&&memoryManager.allocateMemory(currentJob.getMemoryRequired())) {
            	
                readyQueue.add(currentJob);
                currentJob.setState("Ready");
                System.out.println(currentJob.getId() + " " + currentJob.getState()); //! remove line
                jobQueue.poll();
            } else {
            	scheduler();

            }
        }
        double f=System.currentTimeMillis();
        System.out.println("total time taken "+(f-S)/1000);

      
        
    }

    
   
    public void executeJob(Job job) {
        
        job.setState("Running");
       
        try {
            // Simulate execution time
            Thread.sleep(job.getBurstTime()); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }
    public abstract void scheduler();
  

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
