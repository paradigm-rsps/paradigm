import java.io.File;
import java.io.IOException;

public class ViewportMouse {
	public static boolean ViewportMouse_isInViewport;
	public static int ViewportMouse_x;
	public static int ViewportMouse_y;
	public static boolean ViewportMouse_false0;
	static int field2577;
	static int field2581;
	static int field2579;
	public static int ViewportMouse_entityCount;
	public static long[] ViewportMouse_entityTags;
	static Archive archive15;
	static Font fontPlain12;

	static {
		ViewportMouse_isInViewport = false;
		ViewportMouse_x = 0;
		ViewportMouse_y = 0;
		ViewportMouse_false0 = false;
		ViewportMouse_entityCount = 0;
		ViewportMouse_entityTags = new long[1000];
	}

	static final boolean method4463(char var0) {
		if (Character.isISOControl(var0)) {
			return false;
		} else if (NetFileRequest.isAlphaNumeric(var0)) {
			return true;
		} else {
			char[] var1 = class422.field4564;

			int var2;
			char var3;
			for (var2 = 0; var2 < var1.length; ++var2) {
				var3 = var1[var2];
				if (var0 == var3) {
					return true;
				}
			}

			var1 = class422.field4562;

			for (var2 = 0; var2 < var1.length; ++var2) {
				var3 = var1[var2];
				if (var0 == var3) {
					return true;
				}
			}

			return false;
		}
	}

	static void method4493(File var0, File var1) {
		try {
			AccessFile var2 = new AccessFile(AccessFile.JagexCache_locationFile, "rw", 10000L);
			Buffer var3 = new Buffer(500);
			var3.writeByte(3);
			var3.writeByte(var1 != null ? 1 : 0);
			var3.writeCESU8(var0.getPath());
			if (var1 != null) {
				var3.writeCESU8("");
			}

			var2.write(var3.array, 0, var3.offset);
			var2.close();
		} catch (IOException var4) {
			var4.printStackTrace();
		}

	}

	static final int method4492() {
		float var0 = 200.0F * ((float)Interpreter.clientPreferences.method2266() - 0.5F);
		return 100 - Math.round(var0);
	}
}
