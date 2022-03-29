import rs.ScriptOpcodes;

public class class115 {
	static int oculusOrbFocalPointY;
	public final int field1410;
	public class115 field1404;
	float[][] field1406;
	final class387[] field1403;
	class387[] field1411;
	class387[] field1408;
	class387 field1409;
	boolean field1405;
	class387 field1407;
	boolean field1412;
	class387 field1413;
	float[][] field1414;
	float[][] field1415;
	float[][] field1416;

	public class115(int var1, Buffer var2, boolean var3) {
		this.field1409 = new class387();
		this.field1405 = true;
		this.field1407 = new class387();
		this.field1412 = true;
		this.field1413 = new class387();
		this.field1410 = var2.readShort();
		this.field1403 = new class387[var1];
		this.field1411 = new class387[this.field1403.length];
		this.field1408 = new class387[this.field1403.length];
		this.field1406 = new float[this.field1403.length][3];

		for (int var4 = 0; var4 < this.field1403.length; ++var4) {
			this.field1403[var4] = new class387(var2, var3);
			this.field1406[var4][0] = var2.method7965();
			this.field1406[var4][1] = var2.method7965();
			this.field1406[var4][2] = var2.method7965();
		}

		this.method2686();
	}

	void method2686() {
		this.field1414 = new float[this.field1403.length][3];
		this.field1415 = new float[this.field1403.length][3];
		this.field1416 = new float[this.field1403.length][3];
		class387 var1 = HealthBarDefinition.method3428();

		for (int var2 = 0; var2 < this.field1403.length; ++var2) {
			class387 var3 = this.method2651(var2);
			var1.method7064(var3);
			var1.method7111();
			this.field1414[var2] = var1.method7072();
			this.field1415[var2][0] = var3.field4347[12];
			this.field1415[var2][1] = var3.field4347[13];
			this.field1415[var2][2] = var3.field4347[14];
			this.field1416[var2] = var3.method7074();
		}

		var1.method7095();
	}

	class387 method2651(int var1) {
		return this.field1403[var1];
	}

	class387 method2662(int var1) {
		if (this.field1411[var1] == null) {
			this.field1411[var1] = new class387(this.method2651(var1));
			if (this.field1404 != null) {
				this.field1411[var1].method7109(this.field1404.method2662(var1));
			} else {
				this.field1411[var1].method7109(class387.field4348);
			}
		}

		return this.field1411[var1];
	}

	class387 method2653(int var1) {
		if (this.field1408[var1] == null) {
			this.field1408[var1] = new class387(this.method2662(var1));
			this.field1408[var1].method7111();
		}

		return this.field1408[var1];
	}

	void method2654(class387 var1) {
		this.field1409.method7064(var1);
		this.field1405 = true;
		this.field1412 = true;
	}

	class387 method2650() {
		return this.field1409;
	}

	class387 method2649() {
		if (this.field1405) {
			this.field1407.method7064(this.method2650());
			if (this.field1404 != null) {
				this.field1407.method7109(this.field1404.method2649());
			}

			this.field1405 = false;
		}

		return this.field1407;
	}

	public class387 method2657(int var1) {
		if (this.field1412) {
			this.field1413.method7064(this.method2653(var1));
			this.field1413.method7109(this.method2649());
			this.field1412 = false;
		}

		return this.field1413;
	}

	float[] method2658(int var1) {
		return this.field1414[var1];
	}

	float[] method2652(int var1) {
		return this.field1415[var1];
	}

	float[] method2687(int var1) {
		return this.field1416[var1];
	}

	public static void method2685(AbstractArchive var0) {
	}

	static int method2681(int var0, Script var1, boolean var2) {
		Widget var3;
		if (var0 == ScriptOpcodes.IF_GETINVOBJECT) {
			var3 = HorizontalAlignment.getWidget(Interpreter.Interpreter_intStack[--class295.Interpreter_intStackSize]);
			Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = var3.itemId;
			return 1;
		} else if (var0 == ScriptOpcodes.IF_GETINVCOUNT) {
			var3 = HorizontalAlignment.getWidget(Interpreter.Interpreter_intStack[--class295.Interpreter_intStackSize]);
			if (var3.itemId != -1) {
				Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = var3.itemQuantity;
			} else {
				Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = 0;
			}

			return 1;
		} else if (var0 == ScriptOpcodes.IF_HASSUB) {
			int var5 = Interpreter.Interpreter_intStack[--class295.Interpreter_intStackSize];
			InterfaceParent var4 = (InterfaceParent)Client.interfaceParents.get(var5);
			if (var4 != null) {
				Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = 1;
			} else {
				Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = 0;
			}

			return 1;
		} else if (var0 == ScriptOpcodes.IF_GETTOP) {
			Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = Client.rootInterface;
			return 1;
		} else if (var0 == 1707) {
			var3 = HorizontalAlignment.getWidget(Interpreter.Interpreter_intStack[--class295.Interpreter_intStackSize]);
			Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = var3.method5642() ? 1 : 0;
			return 1;
		} else if (var0 == 1708) {
			var3 = HorizontalAlignment.getWidget(Interpreter.Interpreter_intStack[--class295.Interpreter_intStackSize]);
			return class302.method5773(var3);
		} else if (var0 == 1708) {
			var3 = HorizontalAlignment.getWidget(Interpreter.Interpreter_intStack[--class295.Interpreter_intStackSize]);
			return WorldMapSectionType.method4952(var3);
		} else {
			return 2;
		}
	}
}
