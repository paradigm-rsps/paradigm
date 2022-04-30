public abstract class class166 {
	String field1800;
	// $FF: synthetic field
	final class155 this$0;

	class166(class155 var1, String var2) {
		this.this$0 = var1;
		this.field1800 = var2;
	}

	public abstract int vmethod3297();

	public String vmethod3296() {
		return null;
	}

	public int vmethod3299() {
		return -1;
	}

	public String method3295() {
		return this.field1800;
	}

	public static void method3310(AbstractArchive var0) {
		StructComposition.StructDefinition_archive = var0;
	}

	public static int[] method3311() {
		int[] var0 = new int[KeyHandler.field134];

		for (int var1 = 0; var1 < KeyHandler.field134; ++var1) {
			var0[var1] = KeyHandler.field133[var1];
		}

		return var0;
	}

	static void addPlayerToScene(Player var0, boolean var1) {
		if (var0 != null && var0.isVisible() && !var0.isHidden) {
            var0.isUnanimated = (Client.isLowDetail && Players.gpiLocalPlayerCount > 50 || Players.gpiLocalPlayerCount > 200) && var1 && var0.movementSequence == var0.idleSequence;

			int var2 = var0.x >> 7;
			int var3 = var0.y >> 7;
			if (var2 >= 0 && var2 < 104 && var3 >= 0 && var3 < 104) {
				long var4 = MilliClock.calculateTag(0, 0, 0, false, var0.index);
				if (var0.model0 != null && Client.cycle >= var0.animationCycleStart && Client.cycle < var0.animationCycleEnd) {
					var0.isUnanimated = false;
					var0.tileHeight = Archive.getTileHeight(var0.x, var0.y, class160.Client_plane);
					var0.playerCycle = Client.cycle;
					FriendSystem.scene.addNullableObject(class160.Client_plane, var0.x, var0.y, var0.tileHeight, 60, var0, var0.rotation, var4, var0.minX, var0.minY, var0.maxX, var0.maxY);
				} else {
					if ((var0.x & 127) == 64 && (var0.y & 127) == 64) {
						if (Client.tileLastDrawnActor[var2][var3] == Client.viewportDrawCount) {
							return;
						}

						Client.tileLastDrawnActor[var2][var3] = Client.viewportDrawCount;
					}

					var0.tileHeight = Archive.getTileHeight(var0.x, var0.y, class160.Client_plane);
					var0.playerCycle = Client.cycle;
					FriendSystem.scene.drawEntity(class160.Client_plane, var0.x, var0.y, var0.tileHeight, 60, var0, var0.rotation, var4, var0.isWalking);
				}
			}
		}

	}
}
