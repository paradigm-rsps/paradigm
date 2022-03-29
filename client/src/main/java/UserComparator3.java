public class UserComparator3 extends AbstractUserComparator {
	final boolean reversed;

	public UserComparator3(boolean var1) {
		this.reversed = var1;
	}

	int compareBuddy(Buddy var1, Buddy var2) {
		if (var2.world != var1.world) {
			return this.reversed ? var1.world - var2.world : var2.world - var1.world;
		} else {
			return this.compareUser(var1, var2);
		}
	}

	public int compare(Object var1, Object var2) {
		return this.compareBuddy((Buddy)var1, (Buddy)var2);
	}

	public static boolean method2600(int var0) {
		return var0 >= 0 && var0 < 112 && KeyHandler.KeyHandler_pressedKeys[var0];
	}

	static Widget method2601(Widget var0) {
		int var1 = class270.method5241(WorldMapSection2.getWidgetFlags(var0));
		if (var1 == 0) {
			return null;
		} else {
			for (int var2 = 0; var2 < var1; ++var2) {
				var0 = HorizontalAlignment.getWidget(var0.parentId);
				if (var0 == null) {
					return null;
				}
			}

			return var0;
		}
	}
}
