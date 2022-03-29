public enum class82 implements MouseWheel {
	field1072(0, -1),
	field1064(1, 1),
	field1065(2, 7),
	field1069(3, 8),
	field1067(4, 9);

	static boolean field1070;
	static int field1071;
	final int field1068;
	final int field1063;

	class82(int var3, int var4) {
		this.field1068 = var3;
		this.field1063 = var4;
	}

	public int rsOrdinal() {
		return this.field1063;
	}

	public static void method2122() {
		ItemComposition.ItemDefinition_cachedSprites.clear();
	}

	static void method2123(int var0) {
		Client.oculusOrbState = var0;
	}
}
