import java.util.Queue;

public class RoundRobin extends Scheduler{
   private final int QuantumTime = 7;


    public RoundRobin(Queue<Job> JopQueue, MemoryManager memoryManager) {
        super(JopQueue, memoryManager);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void scheduler() {
            int currentTime=0;

        while(!readyQueue.isEmpty()) {
            Job job=readyQueue.poll();
            int executionTime;

            if(job.getBurstTime()>=QuantumTime)
                executionTime=QuantumTime;
            else {
                executionTime=job.getBurstTime();
            }

            job.setWaitingTime(job.getWaitingTime()-executionTime);
            executeJob(job,executionTime);
            currentTime+=executionTime;

           if(job.getBurstTime()>0){
               readyQueue.add(job);

           } else{
               job.setWaitingTime((currentTime-job.getArrivalTime())+job.getWaitingTime());
               job.setTurnaroundTime(currentTime-job.getArrivalTime());
               executedQueue.add(job);
               if( memoryManager.deallocateMemory(job.getMemoryRequired())) {
            	   addRemaindJop(currentTime);
               }
           }


        }
    }






    public void executeJob(Job job,int executionTime){

        System.out.println("Executing Job ID: " + job.getId()+" for "+executionTime+"ms");
        try {
            Thread.sleep(executionTime * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        job.setBurstTime(job.getBurstTime()-executionTime);
        if(job.getBurstTime()==0)
        System.out.println("Finished Job ID: " + job.getId());
    }

    public void addRemaindJop(int arrivaltime) {
        while(!WitingQueue.isEmpty()) {
            Job currentJob=WitingQueue.peek();
            if(memoryManager.allocateMemory(currentJob.getMemoryRequired())) {
                currentJob.setArrivalTime(arrivaltime);
                readyQueue.add(currentJob);
                WitingQueue.poll();
            }
            else break;

        }
    }

    
}
