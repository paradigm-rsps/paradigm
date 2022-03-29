public final class SceneTilePaint {
	int swColor;
	int seColor;
	int neColor;
	int nwColor;
	int texture;
	boolean isFlat;
	int rgb;

	SceneTilePaint(int var1, int var2, int var3, int var4, int var5, int var6, boolean var7) {
		this.isFlat = true;
		this.swColor = var1;
		this.seColor = var2;
		this.neColor = var3;
		this.nwColor = var4;
		this.texture = var5;
		this.rgb = var6;
		this.isFlat = var7;
	}

	static int method4500(int var0, int var1) {
		ItemContainer var2 = (ItemContainer)ItemContainer.itemContainers.get(var0);
		if (var2 == null) {
			return -1;
		} else {
			return var1 >= 0 && var1 < var2.ids.length ? var2.ids[var1] : -1;
		}
	}

	static void method4499() {
		for (InterfaceParent var0 = (InterfaceParent)Client.interfaceParents.first(); var0 != null; var0 = (InterfaceParent)Client.interfaceParents.next()) {
			int var1 = var0.group;
			if (MusicPatchNode2.loadInterface(var1)) {
				boolean var2 = true;
				Widget[] var3 = EnumComposition.Widget_interfaceComponents[var1];

				int var4;
				for (var4 = 0; var4 < var3.length; ++var4) {
					if (var3[var4] != null) {
						var2 = var3[var4].isIf3;
						break;
					}
				}

				if (!var2) {
					var4 = (int)var0.key;
					Widget var5 = HorizontalAlignment.getWidget(var4);
					if (var5 != null) {
						class290.invalidateWidget(var5);
					}
				}
			}
		}

	}
}
