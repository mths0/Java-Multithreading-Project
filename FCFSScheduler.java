import java.util.Queue;

public class FCFSScheduler extends Scheduler{

    public FCFSScheduler(Queue<Job> readyQueue) {
        super(readyQueue);
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

            currentJob.setTurnaroundTime(currentJob.getWaitingTime() + currentJob.getBurstTime());
        }
    }


    
}