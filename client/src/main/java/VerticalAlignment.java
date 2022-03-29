public enum VerticalAlignment implements MouseWheel {
	field1947(2, 0),
	VerticalAlignment_centered(0, 1),
	field1944(1, 2);

	public static short[] field1950;
	public final int value;
	final int id;

	VerticalAlignment(int var3, int var4) {
		this.value = var3;
		this.id = var4;
	}

	public int rsOrdinal() {
		return this.id;
	}
}
