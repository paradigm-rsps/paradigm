import rs.ScriptOpcodes;

public class JagexCache {
	static int field1737;
	public static AbstractArchive EnumDefinition_archive;
	public static int idxCount;
	public static BufferedFile JagexCache_randomDat;
	public static BufferedFile JagexCache_dat2File;
	public static BufferedFile JagexCache_idx255File;
	static Archive archive18;
	static Varcs varcs;

	static {
		JagexCache_randomDat = null;
		JagexCache_dat2File = null;
		JagexCache_idx255File = null;
	}

	public static int method3206(int var0, int var1) {
		int var2;
		for (var2 = 1; var1 > 1; var1 >>= 1) {
			if ((var1 & 1) != 0) {
				var2 = var0 * var2;
			}

			var0 *= var0;
		}

		if (var1 == 1) {
			return var0 * var2;
		} else {
			return var2;
		}
	}

	public static class6[] method3224() {
		return new class6[]{class6.field22};
	}

	public static String intToString(int var0, boolean var1) {
		return var1 && var0 >= 0 ? class345.method6478(var0, 10, var1) : Integer.toString(var0);
	}

	static int method3227(int var0, Script var1, boolean var2) {
		if (var0 == ScriptOpcodes.LOGOUT) {
			Client.logoutTimer = 250;
			return 1;
		} else if (var0 != 5631 && var0 != 5633) {
			if (var0 == 5632) {
				Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = 26;
				return 1;
			} else {
				return 2;
			}
		} else {
			ChatChannel.Interpreter_stringStackSize -= 2;
			return 1;
		}
	}
}
