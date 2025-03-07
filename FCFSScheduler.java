import java.util.Queue;

public class FCFSScheduler extends Scheduler{

   

    public FCFSScheduler(Queue<Job> JopQueue) {
		super(JopQueue);
		// TODO Auto-generated constructor stub
	}

	@Override
    public void scheduler() {
        int currentTime = 0;

        while(! readyQueue.isEmpty()){
            Job currentJob = readyQueue.poll();
            executeJob(currentJob);
            memoryManager.deallocateMemory(currentJob.getMemoryRequired());
            currentJob.setWaitingTime(currentTime);
            currentTime += currentJob.getBurstTime();
            executedQueue.add(currentJob);
           if( memoryManager.deallocateMemory(currentJob.getMemoryRequired())) {
        	   addRemaindJop();
           }

            currentJob.setTurnaroundTime(currentJob.getWaitingTime() + currentJob.getBurstTime());
        }
    }

	public void addRemaindJop() {
		 while(!WitingQueue.isEmpty()) {
	       	   Job currentJob=WitingQueue.peek();
	       	   if(memoryManager.allocateMemory(currentJob.getMemoryRequired())) {
	       		  readyQueue.add(currentJob);
	       		  WitingQueue.poll();
	       	   }
	       	   else break;
	       	   
	          }
		
	}


    
}