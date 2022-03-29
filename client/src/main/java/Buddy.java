public class Buddy extends User {
	public int world;
	public int int2;
	public int rank;

	Buddy() {
		this.world = -1;
	}

	void set(int var1, int var2) {
		this.world = var1;
		this.int2 = var2;
	}

	public int getWorld() {
		return this.world;
	}

	public boolean hasWorld() {
		return this.world > 0;
	}
}
