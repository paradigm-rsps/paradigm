public class class273 {
	public static AbstractArchive musicPatchesArchive;
	public static AbstractArchive musicSamplesArchive;
	public static AbstractArchive soundEffectsArchive;
	public static int musicPlayerStatus;
	public static int musicTrackFileId;
	public static int pcmSampleLength;
	static MusicTrack musicTrack;
	static SoundCache soundCache;

	static {
		musicPlayerStatus = 0;
	}

	public static String method5271(CharSequence[] var0, int var1, int var2) {
		if (var2 == 0) {
			return "";
		} else if (var2 == 1) {
			CharSequence var10 = var0[var1];
			return var10 == null ? "null" : var10.toString();
		} else {
			int var3 = var2 + var1;
			int var4 = 0;

			for (int var5 = var1; var5 < var3; ++var5) {
				CharSequence var9 = var0[var5];
				if (var9 == null) {
					var4 += 4;
				} else {
					var4 += var9.length();
				}
			}

			StringBuilder var8 = new StringBuilder(var4);

			for (int var6 = var1; var6 < var3; ++var6) {
				CharSequence var7 = var0[var6];
				if (var7 == null) {
					var8.append("null");
				} else {
					var8.append(var7);
				}
			}

			return var8.toString();
		}
	}

	static boolean method5267(int var0) {
		for (int var1 = 0; var1 < Client.field713; ++var1) {
			if (Client.field568[var1] == var0) {
				return true;
			}
		}

		return false;
	}
}
