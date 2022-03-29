public class class401 {
	static final int[] field4398;
	static final int[] field4393;

	static {
		field4398 = new int[2048];
		field4393 = new int[2048];
		double var0 = 0.0030679615757712823D;

		for (int var2 = 0; var2 < 2048; ++var2) {
			field4398[var2] = (int)(65536.0D * Math.sin((double)var2 * var0));
			field4393[var2] = (int)(65536.0D * Math.cos((double)var2 * var0));
		}

	}

	public static int method7247(int var0, int var1) {
		return (var0 << 8) + var1;
	}

	static void playSong(int var0) {
		if (var0 == -1 && !Client.field731) {
			WorldMapEvent.midiPcmStream.clear();
			class273.musicPlayerStatus = 1;
			class147.musicTrackArchive = null;
		} else if (var0 != -1 && var0 != Client.currentTrackGroupId && Interpreter.clientPreferences.method2288() != 0 && !Client.field731) {
			Archive var1 = ArchiveLoader.archive6;
			int var2 = Interpreter.clientPreferences.method2288();
			class273.musicPlayerStatus = 1;
			class147.musicTrackArchive = var1;
			ArchiveLoader.musicTrackGroupId = var0;
			class273.musicTrackFileId = 0;
			DevicePcmPlayerProvider.musicTrackVolume = var2;
			class260.musicTrackBoolean = false;
			class273.pcmSampleLength = 2;
		}

		Client.currentTrackGroupId = var0;
	}
}
