import java.util.Queue;

public class FCFSScheduler extends Scheduler{

   

    public FCFSScheduler(Queue<Job> JopQueue, MemoryManager memoryManager) {
		super(JopQueue, memoryManager);
		
	}

	@Override
    public void scheduler() {
        int currentTime = 0;

        while(! readyQueue.isEmpty()){
            Job currentJob = readyQueue.poll();
           executeJob(currentJob);
           
            currentJob.setWaitingTime(currentTime-currentJob.getArrivalTime());
            currentTime += currentJob.getBurstTime();
            executedQueue.add(currentJob);
           if( memoryManager.deallocateMemory(currentJob.getMemoryRequired())) {
        	   addRemaindJop(currentTime);
           }

            currentJob.setTurnaroundTime(currentJob.getWaitingTime() + currentJob.getBurstTime());
        }
        System.out.println(GC());
    }

	public void addRemaindJop(int currentTime) {
		 while(!WitingQueue.isEmpty()) {
	       	   Job currentJob=WitingQueue.peek();
	       	   if(memoryManager.allocateMemory(currentJob.getMemoryRequired())) {
	       		currentJob.setArrivalTime(currentTime);
	       		  readyQueue.add(currentJob);
	       		  WitingQueue.poll();
	       	   }
	       	   else break;
	       	   
	          }
		
	}
	public String GC() {
		String process="";
		while (!executedQueue.isEmpty()) {
			process+=executedQueue.poll().getId()+"|";
		}
		return process;
	}


    
}