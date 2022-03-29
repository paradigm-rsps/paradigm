public class StructComposition extends DualNode {
	static AbstractArchive StructDefinition_archive;
	static EvictingDualNodeHashTable StructDefinition_cached;
	IterableNodeHashTable params;

	static {
		StructDefinition_cached = new EvictingDualNodeHashTable(64);
	}

	StructComposition() {
	}

	void postDecode() {
	}

	void decode(Buffer var1) {
		while (true) {
			int var2 = var1.readUnsignedByte();
			if (var2 == 0) {
				return;
			}

			this.decodeNext(var1, var2);
		}
	}

	void decodeNext(Buffer var1, int var2) {
		if (var2 == 249) {
			this.params = NetFileRequest.readStringIntParameters(var1, this.params);
		}

	}

	public int getIntParam(int var1, int var2) {
		return ClanSettings.method2976(this.params, var1, var2);
	}

	public String getStringParam(int var1, String var2) {
		return class19.method315(this.params, var1, var2);
	}

	public static void method3605() {
		ParamComposition.ParamDefinition_cached.clear();
	}
}
