public class MemoryManager {
	
    private int TotalMemory=2048;//max memory 
    private int UsedMemory=0;//memory that allocated


	public MemoryManager(int TotalMemory){
		this.TotalMemory = TotalMemory;

	}
	
	public boolean allocateMemory(int memorySize) {
		try {
		if(memorySize<=0) 
			throw new IllegalArgumentException("Invalid memory size");
			
		if(UsedMemory+memorySize<=TotalMemory) {
			UsedMemory+=memorySize;
			return true;
		}
		else {
			throw new OutOfMemoryError("There is not enough memory to allocate "+memorySize+" MP");

	}}catch (Exception e) {
		System.err.println(e.getMessage());
		return false;
	}
		}
	public boolean deallocateMemory(int memorySize) {
		try {
		if(memorySize<=0) 
			throw new IllegalArgumentException("Invalid memory size");
		
		if(memorySize<=UsedMemory) {
		UsedMemory-=memorySize;
		return true;
		}
		
		else 
			throw new IllegalStateException("amount of freed memory is greter than used memory");
		
		
	}catch (Exception e) {
		System.err.println(e.getMessage());
		return false;
	}}
	
    
    
}
