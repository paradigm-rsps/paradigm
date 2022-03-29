public class class152 extends class128 {
	static String field1686;
	int field1687;
	long field1689;
	// $FF: synthetic field
	final class131 this$0;

	class152(class131 var1) {
		this.this$0 = var1;
	}

	void vmethod3107(Buffer var1) {
		this.field1687 = var1.readInt();
		this.field1689 = var1.readLong();
	}

	void vmethod3104(ClanSettings var1) {
		var1.method2938(this.field1687, this.field1689);
	}

	static void method3102(SequenceDefinition var0, int var1, int var2, int var3) {
		if (Client.soundEffectCount < 50 && Interpreter.clientPreferences.method2286() != 0) {
			if (var0.soundEffects != null && var1 < var0.soundEffects.length) {
				HealthBarUpdate.method2210(var0.soundEffects[var1], var2, var3);
			}
		}
	}
}
