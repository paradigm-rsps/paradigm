public class class118 implements MouseWheel {
	static final class118 field1452;
	static final class118 field1444;
	static final class118 field1443;
	static final class118 field1446;
	static final class118 field1445;
	static final class118 field1448;
	final int field1449;
	final int field1450;
	final int field1447;

	static {
		field1452 = new class118(0, 0, null, 0);
		field1444 = new class118(1, 1, null, 9);
		field1443 = new class118(2, 2, null, 3);
		field1446 = new class118(3, 3, null, 6);
		field1445 = new class118(4, 4, null, 1);
		field1448 = new class118(5, 5, null, 3);
	}

	class118(int var1, int var2, String var3, int var4) {
		this.field1449 = var1;
		this.field1450 = var2;
		this.field1447 = var4;
	}

	int method2727() {
		return this.field1447;
	}

	public int rsOrdinal() {
		return this.field1450;
	}

	public static int compareStrings(CharSequence var0, CharSequence var1, Language var2) {
		int var3 = var0.length();
		int var4 = var1.length();
		int var5 = 0;
		int var6 = 0;
		char var7 = 0;
		byte var8 = 0;

		while (var5 - var7 < var3 || var6 - var8 < var4) {
			if (var5 - var7 >= var3) {
				return -1;
			}

			if (var6 - var8 >= var4) {
				return 1;
			}

			char var9;
			if (var7 != 0) {
				var9 = var7;
				boolean var14 = false;
			} else {
				var9 = var0.charAt(var5++);
			}

			char var10;
			if (var8 != 0) {
				var10 = (char)var8;
				boolean var15 = false;
			} else {
				var10 = var1.charAt(var6++);
			}

			var7 = MenuAction.method1877(var9);
			byte var11;
			if (var10 == 198) {
				var11 = 69;
			} else if (var10 == 230) {
				var11 = 101;
			} else if (var10 == 223) {
				var11 = 115;
			} else if (var10 == 338) {
				var11 = 69;
			} else if (var10 == 339) {
				var11 = 101;
			} else {
				var11 = 0;
			}

			var8 = var11;
			var9 = GameEngine.standardizeChar(var9, var2);
			var10 = GameEngine.standardizeChar(var10, var2);
			if (var9 != var10 && Character.toUpperCase(var9) != Character.toUpperCase(var10)) {
				var9 = Character.toLowerCase(var9);
				var10 = Character.toLowerCase(var10);
				if (var10 != var9) {
					return WorldMapData_0.lowercaseChar(var9, var2) - WorldMapData_0.lowercaseChar(var10, var2);
				}
			}
		}

		int var16 = Math.min(var3, var4);

		char var12;
		int var17;
		for (var17 = 0; var17 < var16; ++var17) {
			if (var2 == Language.Language_FR) {
				var5 = var3 - 1 - var17;
				var6 = var4 - 1 - var17;
			} else {
				var6 = var17;
				var5 = var17;
			}

			char var18 = var0.charAt(var5);
			var12 = var1.charAt(var6);
			if (var18 != var12 && Character.toUpperCase(var18) != Character.toUpperCase(var12)) {
				var18 = Character.toLowerCase(var18);
				var12 = Character.toLowerCase(var12);
				if (var12 != var18) {
					return WorldMapData_0.lowercaseChar(var18, var2) - WorldMapData_0.lowercaseChar(var12, var2);
				}
			}
		}

		var17 = var3 - var4;
		if (var17 != 0) {
			return var17;
		} else {
			for (int var19 = 0; var19 < var16; ++var19) {
				var12 = var0.charAt(var19);
				char var13 = var1.charAt(var19);
				if (var12 != var13) {
					return WorldMapData_0.lowercaseChar(var12, var2) - WorldMapData_0.lowercaseChar(var13, var2);
				}
			}

			return 0;
		}
	}

	public static String method2737(Buffer var0) {
		return DynamicObject.method1988(var0, 32767);
	}
}
