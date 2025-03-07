import java.util.Queue;


public class Priority  extends Scheduler  {

	 protected PriorityQueue<Job> PriorityQueue;
	public Priority(Queue<Job> JopQueue) {
		
		super(JopQueue);
		PriorityQueue=new PriorityQueue<>();
		
	}


	 public void scheduler() {
	        int currentTime = 0;
	        while(! readyQueue.isEmpty()){
	        	 Job currentJob = readyQueue.poll();
	       
	        	PriorityQueue.Enqueue(currentJob, currentJob.getPriority());
	        }
	        	
	       
	        while(! PriorityQueue.isEmpty()){
	            Job currentJob = PriorityQueue.serve();
	            executeJob(currentJob);
	            currentJob.setWaitingTime(currentTime);
	            currentTime += currentJob.getBurstTime();
	            executedQueue.add(currentJob);
	           if( memoryManager.deallocateMemory(currentJob.getMemoryRequired())) {
	        	   addRemaindJop();
	           }

	            currentJob.setTurnaroundTime(currentJob.getWaitingTime() + currentJob.getBurstTime());
	        }System.out.println(GC());
	    }

		public void addRemaindJop() {
			 while(!WitingQueue.isEmpty()) {
		       	   Job currentJob=WitingQueue.peek();
		       	   if(memoryManager.allocateMemory(currentJob.getMemoryRequired())) {
		       		PriorityQueue.Enqueue(currentJob, currentJob.getPriority());
		       		  WitingQueue.poll();
		       	   }
		       	   else break;
		       	   
		          }
			
		}public String GC() {
			String process="";
			while (!executedQueue.isEmpty()) {
				process+=executedQueue.poll().getId()+"|";
			}
			return process;
		}}
