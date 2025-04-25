//main class

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;

public class main {

public static void main(String[] args) {
    //initialize 
    
	Queue<Job> jobQueue = new LinkedBlockingQueue<Job>();
    MemoryManager memoryManager = new MemoryManager();
    Start_load start_load=new Start_load();
    start_load.Start_load=false;
    //start job thread
    Jobloader jobloader = new Jobloader(jobQueue,start_load);
    
    //System.out.println();

    //user select Scheduling Algorithm
    Scanner scanner = new Scanner(System.in);
    System.out.println("\n Select Scheduling Algorithm: [FCFS, RR, Priority]:");
    String choice = scanner.nextLine();

    //select based on user choice
    Scheduler scheduler = null;
    if(choice.equalsIgnoreCase("FCFS")){
        scheduler = new FCFSScheduler(jobQueue, memoryManager,start_load);
        
        
    }else if(choice.equalsIgnoreCase("RR")){
        scheduler = new RoundRobin(jobQueue, memoryManager,start_load);
     }else if(choice.equalsIgnoreCase("Priority")){
        scheduler = new Priority(jobQueue, memoryManager,start_load);
    }else{
        System.out.println("Invalid choice. FCFS Running as Default .");
        scheduler = new FCFSScheduler(jobQueue, memoryManager,start_load);
    }
    
    //start system handler (if needed)
    jobloader.start();
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
