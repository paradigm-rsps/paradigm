import java.util.Comparator;

public class GrandExchangeOfferOwnWorldComparator implements Comparator {
	static String worldHost;
	boolean filterWorlds;

	GrandExchangeOfferOwnWorldComparator() {
	}

	int compare_bridged(GrandExchangeEvent var1, GrandExchangeEvent var2) {
		if (var2.world == var1.world) {
			return 0;
		} else {
			if (this.filterWorlds) {
				if (Client.worldId == var1.world) {
					return -1;
				}

				if (var2.world == Client.worldId) {
					return 1;
				}
			}

			return var1.world < var2.world ? -1 : 1;
		}
	}

	public int compare(Object var1, Object var2) {
		return this.compare_bridged((GrandExchangeEvent)var1, (GrandExchangeEvent)var2);
	}

	public boolean equals(Object var1) {
		return super.equals(var1);
	}

	static final int method1133() {
		if (Interpreter.clientPreferences.method2255()) {
			return class160.Client_plane;
		} else {
			int var0 = Archive.getTileHeight(EnumComposition.cameraX, CollisionMap.cameraZ, class160.Client_plane);
			return var0 - FriendSystem.cameraY < 800 && (Tiles.Tiles_renderFlags[class160.Client_plane][EnumComposition.cameraX >> 7][CollisionMap.cameraZ >> 7] & 4) != 0 ? class160.Client_plane : 3;
		}
	}
}
