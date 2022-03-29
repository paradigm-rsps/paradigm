import rs.ScriptOpcodes;

public final class ItemLayer {
	int z;
	int x;
	int y;
	Renderable first;
	Renderable second;
	Renderable third;
	long tag;
	int height;

	ItemLayer() {
	}

	static int method3896(int var0, Script var1, boolean var2) {
		boolean var3 = true;
		Widget var4;
		if (var0 >= 2000) {
			var0 -= 1000;
			var4 = HorizontalAlignment.getWidget(Interpreter.Interpreter_intStack[--class295.Interpreter_intStackSize]);
			var3 = false;
		} else {
			var4 = var2 ? class432.scriptDotWidget : class341.scriptActiveWidget;
		}

		int var11;
		if (var0 == ScriptOpcodes.CC_SETOP) {
			var11 = Interpreter.Interpreter_intStack[--class295.Interpreter_intStackSize] - 1;
			if (var11 >= 0 && var11 <= 9) {
				var4.setAction(var11, Interpreter.Interpreter_stringStack[--ChatChannel.Interpreter_stringStackSize]);
				return 1;
			} else {
				--ChatChannel.Interpreter_stringStackSize;
				return 1;
			}
		} else {
			int var6;
			if (var0 == ScriptOpcodes.CC_SETDRAGGABLE) {
				class295.Interpreter_intStackSize -= 2;
				var11 = Interpreter.Interpreter_intStack[class295.Interpreter_intStackSize];
				var6 = Interpreter.Interpreter_intStack[class295.Interpreter_intStackSize + 1];
				var4.parent = class143.getWidgetChild(var11, var6);
				return 1;
			} else if (var0 == ScriptOpcodes.CC_SETDRAGGABLEBEHAVIOR) {
				var4.isScrollBar = Interpreter.Interpreter_intStack[--class295.Interpreter_intStackSize] == 1;
				return 1;
			} else if (var0 == ScriptOpcodes.CC_SETDRAGDEADZONE) {
				var4.dragZoneSize = Interpreter.Interpreter_intStack[--class295.Interpreter_intStackSize];
				return 1;
			} else if (var0 == ScriptOpcodes.CC_SETDRAGDEADTIME) {
				var4.dragThreshold = Interpreter.Interpreter_intStack[--class295.Interpreter_intStackSize];
				return 1;
			} else if (var0 == ScriptOpcodes.CC_SETOPBASE) {
				var4.dataText = Interpreter.Interpreter_stringStack[--ChatChannel.Interpreter_stringStackSize];
				return 1;
			} else if (var0 == ScriptOpcodes.CC_SETTARGETVERB) {
				var4.spellActionName = Interpreter.Interpreter_stringStack[--ChatChannel.Interpreter_stringStackSize];
				return 1;
			} else if (var0 == ScriptOpcodes.CC_CLEAROPS) {
				var4.actions = null;
				return 1;
			} else if (var0 == 1308) {
				var4.prioritizeMenuEntry = Interpreter.Interpreter_intStack[--class295.Interpreter_intStackSize] == 1;
				return 1;
			} else if (var0 == 1309) {
				--class295.Interpreter_intStackSize;
				return 1;
			} else {
				int var7;
				byte[] var8;
				if (var0 != ScriptOpcodes.CC_SETOPKEY) {
					byte var5;
					if (var0 == ScriptOpcodes.CC_SETOPTKEY) {
						class295.Interpreter_intStackSize -= 2;
						var5 = 10;
						var8 = new byte[]{(byte)Interpreter.Interpreter_intStack[class295.Interpreter_intStackSize]};
						byte[] var9 = new byte[]{(byte)Interpreter.Interpreter_intStack[class295.Interpreter_intStackSize + 1]};
						class400.Widget_setKey(var4, var5, var8, var9);
						return 1;
					} else if (var0 == ScriptOpcodes.CC_SETOPKEYRATE) {
						class295.Interpreter_intStackSize -= 3;
						var11 = Interpreter.Interpreter_intStack[class295.Interpreter_intStackSize] - 1;
						var6 = Interpreter.Interpreter_intStack[class295.Interpreter_intStackSize + 1];
						var7 = Interpreter.Interpreter_intStack[class295.Interpreter_intStackSize + 2];
						if (var11 >= 0 && var11 <= 9) {
							class268.Widget_setKeyRate(var4, var11, var6, var7);
							return 1;
						} else {
							throw new RuntimeException();
						}
					} else if (var0 == ScriptOpcodes.CC_SETOPTKEYRATE) {
						var5 = 10;
						var6 = Interpreter.Interpreter_intStack[--class295.Interpreter_intStackSize];
						var7 = Interpreter.Interpreter_intStack[--class295.Interpreter_intStackSize];
						class268.Widget_setKeyRate(var4, var5, var6, var7);
						return 1;
					} else if (var0 == ScriptOpcodes.CC_SETOPKEYIGNOREHELD) {
						--class295.Interpreter_intStackSize;
						var11 = Interpreter.Interpreter_intStack[class295.Interpreter_intStackSize] - 1;
						if (var11 >= 0 && var11 <= 9) {
							Client.Widget_setKeyIgnoreHeld(var4, var11);
							return 1;
						} else {
							throw new RuntimeException();
						}
					} else if (var0 == ScriptOpcodes.CC_SETOPTKEYIGNOREHELD) {
						var5 = 10;
						Client.Widget_setKeyIgnoreHeld(var4, var5);
						return 1;
					} else {
						return 2;
					}
				} else {
					byte[] var10 = null;
					var8 = null;
					if (var3) {
						class295.Interpreter_intStackSize -= 10;

						for (var7 = 0; var7 < 10 && Interpreter.Interpreter_intStack[var7 + class295.Interpreter_intStackSize] >= 0; var7 += 2) {
						}

						if (var7 > 0) {
							var10 = new byte[var7 / 2];
							var8 = new byte[var7 / 2];

							for (var7 -= 2; var7 >= 0; var7 -= 2) {
								var10[var7 / 2] = (byte)Interpreter.Interpreter_intStack[var7 + class295.Interpreter_intStackSize];
								var8[var7 / 2] = (byte)Interpreter.Interpreter_intStack[var7 + class295.Interpreter_intStackSize + 1];
							}
						}
					} else {
						class295.Interpreter_intStackSize -= 2;
						var10 = new byte[]{(byte)Interpreter.Interpreter_intStack[class295.Interpreter_intStackSize]};
						var8 = new byte[]{(byte)Interpreter.Interpreter_intStack[class295.Interpreter_intStackSize + 1]};
					}

					var7 = Interpreter.Interpreter_intStack[--class295.Interpreter_intStackSize] - 1;
					if (var7 >= 0 && var7 <= 9) {
						class400.Widget_setKey(var4, var7, var10, var8);
						return 1;
					} else {
						throw new RuntimeException();
					}
				}
			}
		}
	}

	public static int stringCp1252NullTerminatedByteSize(String var0) {
		return var0.length() + 1;
	}

	static void method3895(Archive var0, String var1) {
		ArchiveLoader var2 = new ArchiveLoader(var0, var1);
		Client.archiveLoaders.add(var2);
		Client.field767 += var2.groupCount;
	}
}
