import java.util.Comparator;

class class360 implements Comparator {
	// $FF: synthetic field
	final class361 this$0;

	class360(class361 var1) {
		this.this$0 = var1;
	}

	int method6627(class362 var1, class362 var2) {
		if (var1.field4236 > var2.field4236) {
			return 1;
		} else {
			return var1.field4236 < var2.field4236 ? -1 : 0;
		}
	}

	public int compare(Object var1, Object var2) {
		return this.method6627((class362)var1, (class362)var2);
	}

	public boolean equals(Object var1) {
		return super.equals(var1);
	}

	static String method6636() {
		String var0;
		if (Interpreter.clientPreferences.method2257()) {
			String var2 = Login.Login_username;
			int var4 = var2.length();
			char[] var5 = new char[var4];

			for (int var6 = 0; var6 < var4; ++var6) {
				var5[var6] = '*';
			}

			String var3 = new String(var5);
			var0 = var3;
		} else {
			var0 = Login.Login_username;
		}

		return var0;
	}
}
