public class MemoryManager {
	
    private int TotalMemory=2048;//max memory 
    private int UsedMempry=0;//memory that allocated
	
	private boolean allocateMemory(int memorySize) {
		try {
		if(memorySize<=0) 
			throw new IllegalArgumentException("Invalid memory size");
			
		if(UsedMempry+memorySize<=TotalMemory) {
			UsedMempry+=memorySize;
			return true;
		}
		else {
			throw new OutOfMemoryError("There is not enough memory to allocate "+memorySize+" MP");

	}}catch (Exception e) {
		System.err.println(e.getMessage());
		return false;
	}
		}
	private boolean deallocateMemory(int memorySize) {
		try {
		if(memorySize<=0) 
			throw new IllegalArgumentException("Invalid memory size");
		
		if(memorySize<=UsedMempry) {
		UsedMempry-=memorySize;
		return true;
		}
		
		else 
			throw new IllegalStateException("amount of freed memory is greter than used memory");
		
		
	}catch (Exception e) {
		System.err.println(e.getMessage());
		return false;
	}}
	
    
    
}
