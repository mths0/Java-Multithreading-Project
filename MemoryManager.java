public class MemoryManager {

	private int TotalMemory = 2048;// max memory
	private int UsedMemory = 0;// memory that allocated

	public MemoryManager() {
		

	}

	public boolean allocateMemory(int memorySize) {
		
			if (memorySize <= 0)
				return false;

			else if (UsedMemory + memorySize <= TotalMemory) {
				UsedMemory += memorySize;
				return true;
			} else {
				return false;

			}
		
	}

	public boolean deallocateMemory(int memorySize) {
		
			if (memorySize <= 0)
				return false;

			if (memorySize <= UsedMemory) {
				UsedMemory = UsedMemory-memorySize;
				return true;
			}

			else
				return false;

		
	}

	public int getTotalMemory() {
		return TotalMemory;
	}

	public int getUsedMemory() {
		return UsedMemory;
	}

}
