
public class Class {
	private int max;
	private String name;
	private String block;
	private int added = 0;
	
	public Class(int m, String n, String b) {
		max = m;
		name = n;
		block = b;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}
	public String toString() {
		return "Capacity: " + max + " Class Name: " + name + " in block " + block;
	}
	public boolean canAdd() {

		if(added < max ) {
			//added++;
			return true;
		}
		else {
			return false;
		}
			
	}

}
