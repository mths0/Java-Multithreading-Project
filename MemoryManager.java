public class MemoryManager {

	private int TotalMemory;// max memory
	private int UsedMemory = 0;// memory that allocated

	public MemoryManager() {

		this.TotalMemory = 2048;

	}

	public boolean allocateMemory(int memorySize) {
		
			if (memorySize <= 0)
				return false;

			else if (UsedMemory + memorySize <= TotalMemory) {
				UsedMemory += memorySize;
				return true;
			} else {
<<<<<<< HEAD
				return false;

=======
				//throw new OutOfMemoryError("There is not enough memory to allocate " + memorySize + " MP");
				return false;
>>>>>>> branch 'main' of https://github.com/mths0/Java-Multithreading-Project
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
