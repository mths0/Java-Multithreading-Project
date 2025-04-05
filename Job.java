public class Job {
    private int id;
    private int burstTime;
    private int originalBurstTime; // added
    private int priority;
    private int memoryRequired;
    private String state;
    private int waitingTime;
    private int turnaroundTime;
    private int arrivalTime;

    public Job(int id, int burstTime, int priority, int memoryRequired) {
        this.id = id;
        this.burstTime = burstTime;
        this.originalBurstTime = burstTime; 
        this.priority = priority;
        this.memoryRequired = memoryRequired;
        this.state = "New";
        this.waitingTime = 0;
        this.turnaroundTime = 0;
        this.arrivalTime = 0;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getBurstTime() { return burstTime; }
    public void setBurstTime(int burstTime) { this.burstTime = burstTime; }

    public int getOriginalBurstTime() { return originalBurstTime; }

    public int getPriority() { return priority; }
    public void setPriority(int priority) { this.priority = priority; }

    public int getMemoryRequired() { return memoryRequired; }
    public void setMemoryRequired(int memoryRequired) { this.memoryRequired = memoryRequired; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public int getWaitingTime() { return waitingTime; }
    public void setWaitingTime(int waitingTime) { this.waitingTime = waitingTime; }

    public int getTurnaroundTime() { return turnaroundTime; }
    public void setTurnaroundTime(int turnaroundTime) { this.turnaroundTime = turnaroundTime; }

    public int getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(int arrivalTime) { this.arrivalTime = arrivalTime; }

    public void updateState(String newState) { this.state = newState; }

    @Override
    public String toString() {
        return "Job [id=" + id +
                ", burstTime=" + burstTime +
                ", originalBurstTime=" + originalBurstTime +
                ", priority=" + priority +
                ", memoryRequired=" + memoryRequired +
                ", state=" + state +
                ", waitingTime=" + waitingTime +
                ", turnaroundTime=" + turnaroundTime +
                ", arrivalTime=" + arrivalTime + "]";
    }
}
