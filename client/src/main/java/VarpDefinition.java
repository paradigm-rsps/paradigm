public class VarpDefinition extends DualNode {
	public static AbstractArchive VarpDefinition_archive;
	public static int VarpDefinition_fileCount;
	static EvictingDualNodeHashTable VarpDefinition_cached;
	static int field1819;
	static SoundSystem soundSystem;
	static SpritePixels[] headIconHintSprites;
	public int type;

	static {
		VarpDefinition_cached = new EvictingDualNodeHashTable(64);
	}

	VarpDefinition() {
		this.type = 0;
	}

	void decode(Buffer var1) {
		while (true) {
			int var2 = var1.readUnsignedByte();
			if (var2 == 0) {
				return;
			}

			this.decodeNext(var1, var2);
		}
	}

	void decodeNext(Buffer var1, int var2) {
		if (var2 == 5) {
			this.type = var1.readUnsignedShort();
		}

	}

	public static byte[] method3353(Object var0, boolean var1) {
		if (var0 == null) {
			return null;
		} else if (var0 instanceof byte[]) {
			byte[] var3 = (byte[]) var0;
			return var1 ? class359.method6624(var3) : var3;
		} else if (var0 instanceof AbstractByteArrayCopier) {
			AbstractByteArrayCopier var2 = (AbstractByteArrayCopier)var0;
			return var2.get();
		} else {
			throw new IllegalArgumentException();
		}
	}

	static final void method3345() {
        int[] var0 = Players.gpiLocalPlayerIndexes;

        int var1;
        for (var1 = 0; var1 < Players.gpiLocalPlayerCount; ++var1) {
            Player var4 = Client.gpiLocalPlayers[var0[var1]];
            if (var4 != null && var4.overheadTextCyclesRemaining > 0) {
                --var4.overheadTextCyclesRemaining;
                if (var4.overheadTextCyclesRemaining == 0) {
                    var4.overheadText = null;
                }
            }
        }

        for (var1 = 0; var1 < Client.npcCount; ++var1) {
            int var2 = Client.npcIndices[var1];
			NPC var3 = Client.npcs[var2];
			if (var3 != null && var3.overheadTextCyclesRemaining > 0) {
				--var3.overheadTextCyclesRemaining;
				if (var3.overheadTextCyclesRemaining == 0) {
					var3.overheadText = null;
				}
			}
		}

	}

	static void addCancelMenuEntry() {
		class268.method5228();
		Client.menuActions[0] = "Cancel";
		Client.menuTargets[0] = "";
		Client.menuOpcodes[0] = 1006;
		Client.menuShiftClick[0] = false;
		Client.menuOptionsCount = 1;
	}
}
