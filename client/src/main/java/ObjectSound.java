public final class ObjectSound extends Node {
	static NodeDeque objectSounds;
	int plane;
	int x;
	int y;
	int maxX;
	int maxY;
	int field813;
	int soundEffectId;
	RawPcmStream stream1;
	ObjectComposition obj;
	int field809;
	int field819;
	int[] soundEffectIds;
	int field821;
	RawPcmStream stream2;

	static {
		objectSounds = new NodeDeque();
	}

	ObjectSound() {
	}

	void set() {
		int var1 = this.soundEffectId;
		ObjectComposition var2 = this.obj.transform();
		if (var2 != null) {
			this.soundEffectId = var2.ambientSoundId;
			this.field813 = var2.int7 * 128;
			this.field809 = var2.int5;
			this.field819 = var2.int6;
			this.soundEffectIds = var2.soundEffectIds;
		} else {
			this.soundEffectId = -1;
			this.field813 = 0;
			this.field809 = 0;
			this.field819 = 0;
			this.soundEffectIds = null;
		}

		if (var1 != this.soundEffectId && this.stream1 != null) {
			WorldMapIcon_1.pcmStreamMixer.removeSubStream(this.stream1);
			this.stream1 = null;
		}

	}

	static String method1734(String var0) {
		PlayerType[] var1 = HitSplatDefinition.PlayerType_values();

		for (int var2 = 0; var2 < var1.length; ++var2) {
			PlayerType var3 = var1[var2];
			if (var3.modIcon != -1 && var0.startsWith(class351.method6579(var3.modIcon))) {
				var0 = var0.substring(6 + Integer.toString(var3.modIcon).length());
				break;
			}
		}

		return var0;
	}
}
