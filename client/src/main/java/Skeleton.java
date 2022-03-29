public class Skeleton extends Node {
	static IndexedSprite options_buttons_2Sprite;
	int id;
	int count;
	int[] transformTypes;
	int[][] labels;
	class202 field2363;

	public Skeleton(int var1, byte[] var2) {
		this.id = var1;
		Buffer var3 = new Buffer(var2);
		this.count = var3.readUnsignedByte();
		this.transformTypes = new int[this.count];
		this.labels = new int[this.count][];

		int var4;
		for (var4 = 0; var4 < this.count; ++var4) {
			this.transformTypes[var4] = var3.readUnsignedByte();
		}

		for (var4 = 0; var4 < this.count; ++var4) {
			this.labels[var4] = new int[var3.readUnsignedByte()];
		}

		for (var4 = 0; var4 < this.count; ++var4) {
			for (int var5 = 0; var5 < this.labels[var4].length; ++var5) {
				this.labels[var4][var5] = var3.readUnsignedByte();
			}
		}

		if (var3.offset < var3.array.length) {
			var4 = var3.readUnsignedShort();
			if (var4 > 0) {
				this.field2363 = new class202(var3, var4);
			}
		}

	}

	public int method4119() {
		return this.count;
	}

	public class202 method4120() {
		return this.field2363;
	}

	static int method4118(AbstractArchive var0, AbstractArchive var1) {
		int var2 = 0;
		String[] var3 = Login.field881;

		int var4;
		String var5;
		for (var4 = 0; var4 < var3.length; ++var4) {
			var5 = var3[var4];
			if (var0.tryLoadFileByNames(var5, "")) {
				++var2;
			}
		}

		var3 = Login.field914;

		for (var4 = 0; var4 < var3.length; ++var4) {
			var5 = var3[var4];
			if (var1.tryLoadFileByNames(var5, "")) {
				++var2;
			}
		}

		return var2;
	}
}
