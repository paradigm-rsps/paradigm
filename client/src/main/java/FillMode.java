public enum FillMode implements MouseWheel {
	SOLID(0, 0),
	field4698(1, 1),
	field4695(2, 2);

	public final int field4696;
	final int field4697;

	FillMode(int var3, int var4) {
		this.field4696 = var3;
		this.field4697 = var4;
	}

	public int rsOrdinal() {
		return this.field4697;
	}

	static void method8155(String var0) {
		class429.field4593 = var0;

		try {
			String var1 = UserComparator10.client.getParameter(Integer.toString(18));
			String var2 = UserComparator10.client.getParameter(Integer.toString(13));
			String var3 = var1 + "settings=" + var0 + "; version=1; path=/; domain=" + var2;
			if (var0.length() == 0) {
				var3 = var3 + "; Expires=Thu, 01-Jan-1970 00:00:00 GMT; Max-Age=0";
			} else {
				var3 = var3 + "; Expires=" + class160.method3236(WorldMapSprite.method4989() + 94608000000L) + "; Max-Age=" + 94608000L;
			}

			class27.method416(UserComparator10.client, "document.cookie=\"" + var3 + "\"");
		} catch (Throwable var4) {
		}

	}
}
