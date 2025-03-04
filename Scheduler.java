import java.util.Queue;

public abstract class Scheduler extends Thread{
    
    protected Queue<Job> readyQueue;
    protected Queue<Job> executedQueue;

    public Scheduler(Queue<Job> readyQueue){
        this.readyQueue = readyQueue;
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


}
