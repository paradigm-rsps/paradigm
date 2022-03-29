import java.awt.*;

public class class241 {
	static FontMetrics loginScreenFontMetrics;
	public static int field2833;

	static final void method5001(String var0) {
		StringBuilder var10000 = (new StringBuilder()).append(var0);
		Object var10001 = null;
		String var1 = var10000.append(" is already on your friend list").toString();
		Login.addGameMessage(30, "", var1);
	}

	static void method5002(SequenceDefinition var0, int var1, int var2, int var3) {
		if (Client.soundEffectCount < 50 && Interpreter.clientPreferences.method2286() != 0) {
			if (var0.field2170 != null && var0.field2170.containsKey(var1)) {
				HealthBarUpdate.method2210((Integer)var0.field2170.get(var1), var2, var3);
			}
		}
	}
}
