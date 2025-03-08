import java.util.LinkedList;
import java.util.Queue;

public abstract class Scheduler extends Thread{
    
    protected MemoryManager memoryManager;
    protected Queue<Job> readyQueue;
    protected Queue<Job> WitingQueue;
    protected Queue<Job> executedQueue;
    protected PriorityQueue<Job> PriorityQueue;
    protected Queue<Job> JopQueue;
    
    public Scheduler(Queue<Job> JobQueue , MemoryManager memoryManager){
    	this.JopQueue=JobQueue;
    	this.memoryManager = memoryManager;
    	readyQueue= new LinkedList<>(); 
    	WitingQueue=new LinkedList<>(); 
    	executedQueue=new LinkedList<>();
    	
    	
    }
   
 //thread to fill ready queue 
    @Override
	public void run() {
    	  while(!JopQueue.isEmpty()) {
       	   Job currentJob=JopQueue.poll();
       	   if(memoryManager.allocateMemory(currentJob.getMemoryRequired())) {
       		  readyQueue.add(currentJob);
       		
       	   }
       	   else {
       		   WitingQueue.add(currentJob);
       	   }
          }
	}



	public abstract void scheduler();

    public void executeJob(Job job){

        System.out.println("Executing Job ID: " + job.getId());
        try {
            Thread.sleep(job.getBurstTime() * 10);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Finished Job ID: " + job.getId());
    }
    public void calculateWaitingTime(){}

    public void calculateTurnaroundTime(){}
    public abstract void addRemaindJop(int currentTime);


}
