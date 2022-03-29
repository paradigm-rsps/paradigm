public class VarcInt extends DualNode {
	public static AbstractArchive VarcInt_archive;
	public static EvictingDualNodeHashTable VarcInt_cached;
	public boolean persist;

	static {
		VarcInt_cached = new EvictingDualNodeHashTable(64);
	}

	public VarcInt() {
		this.persist = false;
	}

	public void method3386(Buffer var1) {
		while (true) {
			int var2 = var1.readUnsignedByte();
			if (var2 == 0) {
				return;
			}

			this.method3387(var1, var2);
		}
	}

	void method3387(Buffer var1, int var2) {
		if (var2 == 2) {
			this.persist = true;
		}

	}
}
