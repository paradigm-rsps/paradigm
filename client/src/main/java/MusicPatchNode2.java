public class MusicPatchNode2 {
	static SpritePixels sceneMinimapSprite;
	static int cameraYaw;
	byte[] field3165;
	byte[] field3162;
	int field3164;
	int field3168;
	int field3163;
	int field3173;
	int field3171;
	int field3169;
	int field3170;

	MusicPatchNode2() {
	}

	public static boolean isWorldMapEvent(int var0) {
		return var0 == 10 || var0 == 11 || var0 == 12 || var0 == 13 || var0 == 14 || var0 == 15 || var0 == 16 || var0 == 17;
	}

	public static boolean loadInterface(int var0) {
		if (Frames.Widget_loadedInterfaces[var0]) {
			return true;
		} else if (!class122.Widget_archive.tryLoadGroup(var0)) {
			return false;
		} else {
			int var1 = class122.Widget_archive.getGroupFileCount(var0);
			if (var1 == 0) {
				Frames.Widget_loadedInterfaces[var0] = true;
				return true;
			} else {
				if (EnumComposition.Widget_interfaceComponents[var0] == null) {
					EnumComposition.Widget_interfaceComponents[var0] = new Widget[var1];
				}

				for (int var2 = 0; var2 < var1; ++var2) {
					if (EnumComposition.Widget_interfaceComponents[var0][var2] == null) {
						byte[] var3 = class122.Widget_archive.takeFile(var0, var2);
						if (var3 != null) {
							EnumComposition.Widget_interfaceComponents[var0][var2] = new Widget();
							EnumComposition.Widget_interfaceComponents[var0][var2].id = var2 + (var0 << 16);
							if (var3[0] == -1) {
								EnumComposition.Widget_interfaceComponents[var0][var2].decode(new Buffer(var3));
							} else {
								EnumComposition.Widget_interfaceComponents[var0][var2].decodeLegacy(new Buffer(var3));
							}
						}
					}
				}

				Frames.Widget_loadedInterfaces[var0] = true;
				return true;
			}
		}
	}
}
