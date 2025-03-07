//main class

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class main {

public static void main(String[] args) {
    //initialize 
    
    Queue<Job> jobQueue = new LinkedList<>();
    MemoryManager memoryManager = new MemoryManager(2048);

    //start job thread
    Jobloader jobloader = new Jobloader(jobQueue);
    jobloader.start();
    System.out.println(jobQueue.toString());

    //user select Scheduling Algorithm
    Scanner scanner = new Scanner(System.in);
    System.out.println("Select Scheduling Algorithm: [FCFS, RR, Priority]");
    String choice = scanner.nextLine();

    //select based on user choice
    Scheduler scheduler = null;
    if(choice.equalsIgnoreCase("FCFS")){
        scheduler = new FCFSScheduler(jobQueue, memoryManager);
    }else if(choice.equalsIgnoreCase("RR")){
        scheduler = new RoundRobin(jobQueue);
    // TODO make Priority class
   /*  }else if(choice.equalsIgnoreCase("Priority")){
        scheduler = new Priority(jobQueue);
    }*/
    }else{
        System.out.println("Invalid choice. FCFS Running as Default .");
        scheduler = new FCFSScheduler(jobQueue, memoryManager);
    }

    //start system handler (if needed)
    
    //start Scheduler Thread
    scheduler.start();



    //wait for all threads
    try {
        jobloader.join();
        scheduler.join();
    } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    
    System.out.println("All jobs have been executed.");
}


}