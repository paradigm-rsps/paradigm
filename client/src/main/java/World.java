public class World {
	static World[] World_worlds;
	static int World_count;
	static int World_listCount;
	static int[] World_sortOption2;
	static int[] World_sortOption1;
	static Archive archive10;
	int id;
	int properties;
	int population;
	String host;
	String activity;
	int location;
	int index;

	static {
		World_count = 0;
		World_listCount = 0;
		World_sortOption2 = new int[]{1, 1, 1, 1};
		World_sortOption1 = new int[]{0, 1, 2, 3};
	}

	World() {
	}

	boolean isMembersOnly() {
		return (1 & this.properties) != 0;
	}

	boolean method1628() {
		return (2 & this.properties) != 0;
	}

	boolean isPvp() {
		return (4 & this.properties) != 0;
	}

	boolean method1612() {
		return (8 & this.properties) != 0;
	}

	boolean isDeadman() {
		return (536870912 & this.properties) != 0;
	}

	boolean isBeta() {
		return (33554432 & this.properties) != 0;
	}

	boolean method1638() {
		return (1073741824 & this.properties) != 0;
	}

	public static void method1655() {
		try {
			JagexCache.JagexCache_dat2File.close();

			for (int var0 = 0; var0 < JagexCache.idxCount; ++var0) {
				class194.JagexCache_idxFiles[var0].close();
			}

			JagexCache.JagexCache_idx255File.close();
			JagexCache.JagexCache_randomDat.close();
		} catch (Exception var2) {
		}

	}
}
