public final class TileItem extends Renderable {
	public static short[] field1281;
	static Archive archive0;
	int id;
	int quantity;

	TileItem() {
	}

	protected final Model getModel() {
		return class67.ItemDefinition_get(this.id).getModel(this.quantity);
	}

	static void clearItemContainer(int var0) {
		ItemContainer var1 = (ItemContainer)ItemContainer.itemContainers.get(var0);
		if (var1 != null) {
			for (int var2 = 0; var2 < var1.ids.length; ++var2) {
				var1.ids[var2] = -1;
				var1.quantities[var2] = 0;
			}

		}
	}
}
