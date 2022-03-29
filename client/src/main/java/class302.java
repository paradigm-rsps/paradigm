public class class302 {
	static final class302 field3557;
	static final class302 field3555;
	static Archive archive5;
	final int field3556;
	final int field3554;

	static {
		field3557 = new class302(51, 27, 800, 0, 16, 16);
		field3555 = new class302(25, 28, 800, 656, 40, 40);
	}

	class302(int var1, int var2, int var3, int var4, int var5, int var6) {
		this.field3556 = var5;
		this.field3554 = var6;
	}

	static int method5773(Widget var0) {
		if (var0.type != 11) {
			--ChatChannel.Interpreter_stringStackSize;
			Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = -1;
			return 1;
		} else {
			String var1 = Interpreter.Interpreter_stringStack[--ChatChannel.Interpreter_stringStackSize];
			Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = var0.method5663(var1);
			return 1;
		}
	}
}
