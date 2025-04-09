import java.io.*;
import java.util.Queue;

public class Jobloader extends Thread {


    private Queue<Job> jobQueue;

    public Jobloader(Queue<Job> jobQueue) {

        this.jobQueue = jobQueue;
    }

    public void run() {

        try {
            try (BufferedReader reader = new BufferedReader(new FileReader("job.txt"))) {
                String line = reader.readLine();

                while (line != null) {
                    process(line);
                    line = reader.readLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void process(String line) {
        String[] parts = line.split("[:;]");

        int processID = Integer.parseInt(parts[0]); // convert to integer
        int burstTime = Integer.parseInt(parts[1]);
        int priority = Integer.parseInt(parts[2]);
        int memoryRequired = Integer.parseInt(parts[3]);

        Job job = new Job(processID, burstTime, priority, memoryRequired); // create new job
        job.setState("New");
        jobQueue.add(job); 

        //System.out.println("Job loaded :" + job.getId());
        System.out.println(job.toString());
    }

}
