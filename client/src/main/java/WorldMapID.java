public class WorldMapID {
	static final WorldMapID field2829;
	static final WorldMapID field2826;
	static AbstractArchive ItemDefinition_archive;
	final int value;

	static {
		field2829 = new WorldMapID(0);
		field2826 = new WorldMapID(1);
	}

	WorldMapID(int var1) {
		this.value = var1;
	}

	public static void method5000(int var0) {
		if (var0 != -1) {
			if (Frames.Widget_loadedInterfaces[var0]) {
				class122.Widget_archive.clearFilesGroup(var0);
				if (EnumComposition.Widget_interfaceComponents[var0] != null) {
					boolean var1 = true;

					for (int var2 = 0; var2 < EnumComposition.Widget_interfaceComponents[var0].length; ++var2) {
						if (EnumComposition.Widget_interfaceComponents[var0][var2] != null) {
							if (EnumComposition.Widget_interfaceComponents[var0][var2].type != 2) {
								EnumComposition.Widget_interfaceComponents[var0][var2] = null;
							} else {
								var1 = false;
							}
						}
					}

					if (var1) {
						EnumComposition.Widget_interfaceComponents[var0] = null;
					}

					Frames.Widget_loadedInterfaces[var0] = false;
				}
			}
		}
	}
}
