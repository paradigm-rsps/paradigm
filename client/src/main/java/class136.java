import rs.ScriptOpcodes;

public class class136 extends class144 {
	static int widgetDragDuration;
	int field1581;
	// $FF: synthetic field
	final class145 this$0;

	class136(class145 var1) {
		this.this$0 = var1;
		this.field1581 = -1;
	}

	void vmethod3096(Buffer var1) {
		this.field1581 = var1.readUnsignedShort();
		var1.readUnsignedByte();
		if (var1.readUnsignedByte() != 255) {
			--var1.offset;
			var1.readLong();
		}

	}

	void vmethod3093(ClanChannel var1) {
		var1.removeMember(this.field1581);
	}

	static int method2896(int var0, Script var1, boolean var2) {
		Widget var3 = var2 ? class432.scriptDotWidget : class341.scriptActiveWidget;
		if (var0 == ScriptOpcodes.CC_GETTARGETMASK) {
			Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = WorldMapSection2.Widget_unpackTargetMask(WorldMapSection2.getWidgetFlags(var3));
			return 1;
		} else if (var0 != ScriptOpcodes.CC_GETOP) {
			if (var0 == ScriptOpcodes.CC_GETOPBASE) {
				if (var3.dataText == null) {
					Interpreter.Interpreter_stringStack[++ChatChannel.Interpreter_stringStackSize - 1] = "";
				} else {
					Interpreter.Interpreter_stringStack[++ChatChannel.Interpreter_stringStackSize - 1] = var3.dataText;
				}

				return 1;
			} else {
				return 2;
			}
		} else {
			int var4 = Interpreter.Interpreter_intStack[--class295.Interpreter_intStackSize];
			--var4;
			if (var3.actions != null && var4 < var3.actions.length && var3.actions[var4] != null) {
				Interpreter.Interpreter_stringStack[++ChatChannel.Interpreter_stringStackSize - 1] = var3.actions[var4];
			} else {
				Interpreter.Interpreter_stringStack[++ChatChannel.Interpreter_stringStackSize - 1] = "";
			}

			return 1;
		}
	}
}
