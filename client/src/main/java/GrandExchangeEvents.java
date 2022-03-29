import rs.ScriptOpcodes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GrandExchangeEvents {
	public static Comparator GrandExchangeEvents_ageComparator;
	public static Comparator GrandExchangeEvents_priceComparator;
	public static Comparator GrandExchangeEvents_nameComparator;
	public static Comparator GrandExchangeEvents_quantityComparator;
	public final List events;

	static {
		GrandExchangeEvents_ageComparator = new GrandExchangeOfferAgeComparator();
		new GrandExchangeOfferWorldComparator();
		GrandExchangeEvents_priceComparator = new GrandExchangeOfferUnitPriceComparator();
		GrandExchangeEvents_nameComparator = new GrandExchangeOfferNameComparator();
		GrandExchangeEvents_quantityComparator = new GrandExchangeOfferTotalQuantityComparator();
	}

	public GrandExchangeEvents(Buffer var1, boolean var2) {
		int var3 = var1.readUnsignedShort();
		boolean var4 = var1.readUnsignedByte() == 1;
		byte var5;
		if (var4) {
			var5 = 1;
		} else {
			var5 = 0;
		}

		int var6 = var1.readUnsignedShort();
		this.events = new ArrayList(var6);

		for (int var7 = 0; var7 < var6; ++var7) {
			this.events.add(new GrandExchangeEvent(var1, var5, var3));
		}

	}

	public void sort(Comparator var1, boolean var2) {
		if (var2) {
			Collections.sort(this.events, var1);
		} else {
			Collections.sort(this.events, Collections.reverseOrder(var1));
		}

	}

	static int method5993(int var0, Script var1, boolean var2) {
		Widget var3 = var2 ? class432.scriptDotWidget : class341.scriptActiveWidget;
		if (var0 == ScriptOpcodes.CC_GETINVOBJECT) {
			Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = var3.itemId;
			return 1;
		} else if (var0 == ScriptOpcodes.CC_GETINVCOUNT) {
			if (var3.itemId != -1) {
				Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = var3.itemQuantity;
			} else {
				Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = 0;
			}

			return 1;
		} else if (var0 == ScriptOpcodes.CC_GETID) {
			Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = var3.childIndex;
			return 1;
		} else if (var0 == 1707) {
			Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = var3.method5642() ? 1 : 0;
			return 1;
		} else if (var0 == 1708) {
			return class302.method5773(var3);
		} else {
			return var0 == 1708 ? WorldMapSectionType.method4952(var3) : 2;
		}
	}

	static void revalidateWidgetScroll(Widget[] var0, Widget var1, boolean var2) {
		int var3 = var1.scrollWidth != 0 ? var1.scrollWidth * 519655973 * 737180077 : var1.width * 1149774003 * -646003077;
		int var4 = var1.scrollHeight != 0 ? var1.scrollHeight * 590865599 * -4879553 : var1.height * -1323757205 * 1884328771;
		class65.resizeInterface(var0, var1.id, var3, var4, var2);
		if (var1.children != null) {
			class65.resizeInterface(var1.children, var1.id, var3, var4, var2);
		}

		InterfaceParent var5 = (InterfaceParent)Client.interfaceParents.get(var1.id);
		if (var5 != null) {
			int var6 = var5.group;
			if (MusicPatchNode2.loadInterface(var6)) {
				class65.resizeInterface(EnumComposition.Widget_interfaceComponents[var6], -1, var3, var4, var2);
			}
		}

		if (var1.contentType == 1337) {
		}

	}
}
