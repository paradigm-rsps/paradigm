import java.util.HashSet;
import java.util.Set;

public enum ModeWhere implements MouseWheel {
	field4073("", 0, new class327[]{class327.field4050}),
	field4084("", 1, new class327[]{class327.field4052, class327.field4050}),
	field4074("", 2, new class327[]{class327.field4052, class327.field4051, class327.field4050}),
	field4075("", 3, new class327[]{class327.field4052}),
	field4076("", 4),
	field4077("", 5, new class327[]{class327.field4052, class327.field4050}),
	field4086("", 6, new class327[]{class327.field4050}),
	field4079("", 8, new class327[]{class327.field4052, class327.field4050}),
	field4091("", 9, new class327[]{class327.field4052, class327.field4051}),
	field4082("", 10, new class327[]{class327.field4052}),
	field4078("", 11, new class327[]{class327.field4052}),
	field4083("", 12, new class327[]{class327.field4052, class327.field4050}),
	field4072("", 13, new class327[]{class327.field4052});

	static int field4080;
	protected static String field4087;
	final int id;
	final Set field4088;

	ModeWhere(String var3, int var4, class327[] var5) {
		this.field4088 = new HashSet();
		this.id = var4;
		class327[] var6 = var5;

		for (int var7 = 0; var7 < var6.length; ++var7) {
			class327 var8 = var6[var7];
			this.field4088.add(var8);
		}

	}

	ModeWhere(String var3, int var4) {
		this.field4088 = new HashSet();
		this.id = var4;
	}

	public int rsOrdinal() {
		return this.id;
	}

	static int method6144(int var0, Script var1, boolean var2) {
		if (var0 == 7400) {
			--class295.Interpreter_intStackSize;
			--ChatChannel.Interpreter_stringStackSize;
			return 1;
		} else if (var0 == 7401) {
			--class295.Interpreter_intStackSize;
			--ChatChannel.Interpreter_stringStackSize;
			return 1;
		} else if (var0 == 7402) {
			class295.Interpreter_intStackSize -= 2;
			--ChatChannel.Interpreter_stringStackSize;
			return 1;
		} else if (var0 == 7403) {
			class295.Interpreter_intStackSize -= 2;
			--ChatChannel.Interpreter_stringStackSize;
			return 1;
		} else if (var0 == 7404) {
			--class295.Interpreter_intStackSize;
			--ChatChannel.Interpreter_stringStackSize;
			return 1;
		} else if (var0 == 7405) {
			class295.Interpreter_intStackSize -= 2;
			return 1;
		} else if (var0 == 7406) {
			--class295.Interpreter_intStackSize;
			Interpreter.Interpreter_stringStack[++ChatChannel.Interpreter_stringStackSize - 1] = "";
			return 1;
		} else if (var0 == 7407) {
			Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = 0;
			return 1;
		} else if (var0 == 7408) {
			class295.Interpreter_intStackSize -= 2;
			--ChatChannel.Interpreter_stringStackSize;
			Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = 0;
			return 1;
		} else if (var0 == 7409) {
			--class295.Interpreter_intStackSize;
			return 1;
		} else {
			return 2;
		}
	}

	static final void method6145(Actor var0) {
		int var1 = Math.max(1, var0.field1179 - Client.cycle);
		int var2 = var0.field1145 * 64 + var0.field1175 * 128;
		int var3 = var0.field1145 * 64 + var0.field1177 * 128;
		var0.x += (var2 - var0.x) / var1;
		var0.y += (var3 - var0.y) / var1;
		var0.field1197 = 0;
		var0.orientation = var0.field1181;
	}
}
