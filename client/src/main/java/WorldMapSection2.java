public class WorldMapSection2 implements WorldMapSection {
	int minPlane;
	int planes;
	int regionStartX;
	int regionStartY;
	int regionEndX;
	int regionEndY;
	int field2670;
	int field2674;
	int field2678;
	int field2679;

	WorldMapSection2() {
	}

	public void expandBounds(WorldMapArea var1) {
		if (var1.regionLowX > this.field2670) {
			var1.regionLowX = this.field2670;
		}

		if (var1.regionHighX < this.field2678) {
			var1.regionHighX = this.field2678;
		}

		if (var1.regionLowY > this.field2674) {
			var1.regionLowY = this.field2674;
		}

		if (var1.regionHighY < this.field2679) {
			var1.regionHighY = this.field2679;
		}

	}

	public boolean containsCoord(int var1, int var2, int var3) {
		if (var1 >= this.minPlane && var1 < this.minPlane + this.planes) {
			return var2 >> 6 >= this.regionStartX && var2 >> 6 <= this.regionEndX && var3 >> 6 >= this.regionStartY && var3 >> 6 <= this.regionEndY;
		} else {
			return false;
		}
	}

	public boolean containsPosition(int var1, int var2) {
		return var1 >> 6 >= this.field2670 && var1 >> 6 <= this.field2678 && var2 >> 6 >= this.field2674 && var2 >> 6 <= this.field2679;
	}

	public int[] getBorderTileLengths(int var1, int var2, int var3) {
		if (!this.containsCoord(var1, var2, var3)) {
			return null;
		} else {
			int[] var4 = new int[]{var2 + (this.field2670 * 64 - this.regionStartX * 64), var3 + (this.field2674 * 64 - this.regionStartY * 64)};
			return var4;
		}
	}

	public Coord coord(int var1, int var2) {
		if (!this.containsPosition(var1, var2)) {
			return null;
		} else {
			int var3 = this.regionStartX * 64 - this.field2670 * 64 + var1;
			int var4 = var2 + (this.regionStartY * 64 - this.field2674 * 64);
			return new Coord(this.minPlane, var3, var4);
		}
	}

	public void read(Buffer var1) {
		this.minPlane = var1.readUnsignedByte();
		this.planes = var1.readUnsignedByte();
		this.regionStartX = var1.readUnsignedShort();
		this.regionStartY = var1.readUnsignedShort();
		this.regionEndX = var1.readUnsignedShort();
		this.regionEndY = var1.readUnsignedShort();
		this.field2670 = var1.readUnsignedShort();
		this.field2674 = var1.readUnsignedShort();
		this.field2678 = var1.readUnsignedShort();
		this.field2679 = var1.readUnsignedShort();
		this.postRead();
	}

	void postRead() {
	}

	public static void method4561(AbstractArchive var0, int var1, int var2, int var3, boolean var4) {
		class273.musicPlayerStatus = 1;
		class147.musicTrackArchive = var0;
		ArchiveLoader.musicTrackGroupId = var1;
		class273.musicTrackFileId = var2;
		DevicePcmPlayerProvider.musicTrackVolume = var3;
		class260.musicTrackBoolean = var4;
		class273.pcmSampleLength = 10000;
	}

	public static int Widget_unpackTargetMask(int var0) {
		return var0 >> 11 & 63;
	}

	static int getWidgetFlags(Widget var0) {
		IntegerNode var1 = (IntegerNode)Client.widgetFlags.get(((long)var0.id << 32) + (long)var0.childIndex);
		return var1 != null ? var1.integer : var0.flags;
	}
}
