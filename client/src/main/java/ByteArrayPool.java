import java.util.ArrayList;
import java.util.HashMap;

public class ByteArrayPool {
	static int ByteArrayPool_smallCount;
	static int ByteArrayPool_mediumCount;
	static int ByteArrayPool_largeCount;
	static int field4169;
	static int field4178;
	static int field4165;
	static int field4172;
	static int field4177;
	static byte[][] ByteArrayPool_small;
	static byte[][] ByteArrayPool_medium;
	static byte[][] ByteArrayPool_large;
	static byte[][] field4166;
	public static int[] ByteArrayPool_alternativeSizes;
	public static ArrayList field4173;

	static {
		ByteArrayPool_smallCount = 0;
		ByteArrayPool_mediumCount = 0;
		ByteArrayPool_largeCount = 0;
		field4169 = 0;
		field4178 = 1000;
		field4165 = 250;
		field4172 = 100;
		field4177 = 50;
		ByteArrayPool_small = new byte[1000][];
		ByteArrayPool_medium = new byte[250][];
		ByteArrayPool_large = new byte[100][];
		field4166 = new byte[50][];
		field4173 = new ArrayList();
		field4173.clear();
		field4173.add(100);
		field4173.add(5000);
		field4173.add(10000);
		field4173.add(30000);
		new HashMap();
	}

	static synchronized byte[] ByteArrayPool_getArrayBool(int var0, boolean var1) {
		byte[] var4;
		if (var0 != 100) {
			if (var0 < 100) {
			}
		} else if (ByteArrayPool_smallCount > 0) {
			var4 = ByteArrayPool_small[--ByteArrayPool_smallCount];
			ByteArrayPool_small[ByteArrayPool_smallCount] = null;
			return var4;
		}

		if (var0 != 5000) {
			if (var0 < 5000) {
			}
		} else if (ByteArrayPool_mediumCount > 0) {
			var4 = ByteArrayPool_medium[--ByteArrayPool_mediumCount];
			ByteArrayPool_medium[ByteArrayPool_mediumCount] = null;
			return var4;
		}

		if (var0 != 10000) {
			if (var0 < 10000) {
			}
		} else if (ByteArrayPool_largeCount > 0) {
			var4 = ByteArrayPool_large[--ByteArrayPool_largeCount];
			ByteArrayPool_large[ByteArrayPool_largeCount] = null;
			return var4;
		}

		if (var0 != 30000) {
			if (var0 < 30000) {
			}
		} else if (field4169 > 0) {
			var4 = field4166[--field4169];
			field4166[field4169] = null;
			return var4;
		}

		if (ScriptEvent.ByteArrayPool_arrays != null) {
			for (int var2 = 0; var2 < ByteArrayPool_alternativeSizes.length; ++var2) {
				if (ByteArrayPool_alternativeSizes[var2] != var0) {
					if (var0 < ByteArrayPool_alternativeSizes[var2]) {
					}
				} else if (GameObject.ByteArrayPool_altSizeArrayCounts[var2] > 0) {
					byte[] var3 = ScriptEvent.ByteArrayPool_arrays[var2][--GameObject.ByteArrayPool_altSizeArrayCounts[var2]];
					ScriptEvent.ByteArrayPool_arrays[var2][GameObject.ByteArrayPool_altSizeArrayCounts[var2]] = null;
					return var3;
				}
			}
		}

		return new byte[var0];
	}
}
