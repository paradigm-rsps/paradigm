public class class307 {
	static int field3588;
	static int menuX;

	static void savePreferences() {
		AccessFile var0 = null;

		try {
			var0 = ReflectionCheck.getPreferencesFile("", Login.field917.name, true);
			Buffer var1 = Interpreter.clientPreferences.toBuffer();
			var0.write(var1.array, 0, var1.offset);
		} catch (Exception var3) {
		}

		try {
			if (var0 != null) {
				var0.closeSync(true);
			}
		} catch (Exception var2) {
		}

	}
}
