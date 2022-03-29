import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionCheck extends Node {
	static GrandExchangeEvents grandExchangeEvents;
	static int field250;
	public static String operatingSystemName;
	static Archive archive20;
	int id;
	int size;
	int[] operations;
	int[] creationErrors;
	Field[] fields;
	int[] intReplaceValues;
	Method[] methods;
	byte[][][] arguments;

	ReflectionCheck() {
	}

	public static AccessFile getPreferencesFile(String var0, String var1, boolean var2) {
		File var3 = new File(VertexNormal.cacheDir, "preferences" + var0 + ".dat");
		if (var3.exists()) {
			try {
				AccessFile var10 = new AccessFile(var3, "rw", 10000L);
				return var10;
			} catch (IOException var9) {
			}
		}

		String var4 = "";
		if (SecureRandomFuture.cacheGamebuild == 33) {
			var4 = "_rc";
		} else if (SecureRandomFuture.cacheGamebuild == 34) {
			var4 = "_wip";
		}

		File var5 = new File(Statics1.userHomeDirectory, "jagex_" + var1 + "_preferences" + var0 + var4 + ".dat");
		AccessFile var6;
		if (!var2 && var5.exists()) {
			try {
				var6 = new AccessFile(var5, "rw", 10000L);
				return var6;
			} catch (IOException var8) {
			}
		}

		try {
			var6 = new AccessFile(var3, "rw", 10000L);
			return var6;
		} catch (IOException var7) {
			throw new RuntimeException();
		}
	}

	static int getWindowedMode() {
		return Client.isResizable ? 2 : 1;
	}

	static String method637(int var0) {
		if (var0 < 0) {
			return "";
		} else {
			return Client.menuTargets[var0].length() > 0 ? Client.menuActions[var0] + " " + Client.menuTargets[var0] : Client.menuActions[var0];
		}
	}
}
