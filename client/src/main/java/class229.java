public class class229 implements WorldMapSection {
	int field2736;
	int field2734;
	int field2733;
	int field2738;
	int field2737;
	int field2743;
	int field2739;
	int field2740;
	int field2741;
	int field2742;

	class229() {
	}

	public void expandBounds(WorldMapArea var1) {
		if (var1.regionLowX > this.field2737) {
			var1.regionLowX = this.field2737;
		}

		if (var1.regionHighX < this.field2737) {
			var1.regionHighX = this.field2737;
		}

		if (var1.regionLowY > this.field2743) {
			var1.regionLowY = this.field2743;
		}

		if (var1.regionHighY < this.field2743) {
			var1.regionHighY = this.field2743;
		}

	}

	public boolean containsCoord(int var1, int var2, int var3) {
		if (var1 >= this.field2736 && var1 < this.field2734 + this.field2736) {
			return var2 >= (this.field2733 << 6) + (this.field2739 << 3) && var2 <= (this.field2733 << 6) + (this.field2739 << 3) + 7 && var3 >= (this.field2738 << 6) + (this.field2740 << 3) && var3 <= (this.field2738 << 6) + (this.field2740 << 3) + 7;
		} else {
			return false;
		}
	}

	public boolean containsPosition(int var1, int var2) {
		return var1 >= (this.field2737 << 6) + (this.field2741 << 3) && var1 <= (this.field2737 << 6) + (this.field2741 << 3) + 7 && var2 >= (this.field2743 << 6) + (this.field2742 << 3) && var2 <= (this.field2743 << 6) + (this.field2742 << 3) + 7;
	}

	public int[] getBorderTileLengths(int var1, int var2, int var3) {
		if (!this.containsCoord(var1, var2, var3)) {
			return null;
		} else {
			int[] var4 = new int[]{var2 + (this.field2737 * 64 - this.field2733 * 64) + (this.field2741 * 8 - this.field2739 * 8), var3 + (this.field2743 * 64 - this.field2738 * 64) + (this.field2742 * 8 - this.field2740 * 8)};
			return var4;
		}
	}

	public Coord coord(int var1, int var2) {
		if (!this.containsPosition(var1, var2)) {
			return null;
		} else {
			int var3 = this.field2733 * 64 - this.field2737 * 64 + (this.field2739 * 8 - this.field2741 * 8) + var1;
			int var4 = var2 + (this.field2738 * 64 - this.field2743 * 64) + (this.field2740 * 8 - this.field2742 * 8);
			return new Coord(this.field2736, var3, var4);
		}
	}

	public void read(Buffer var1) {
		this.field2736 = var1.readUnsignedByte();
		this.field2734 = var1.readUnsignedByte();
		this.field2733 = var1.readUnsignedShort();
		this.field2739 = var1.readUnsignedByte();
		this.field2738 = var1.readUnsignedShort();
		this.field2740 = var1.readUnsignedByte();
		this.field2737 = var1.readUnsignedShort();
		this.field2741 = var1.readUnsignedByte();
		this.field2743 = var1.readUnsignedShort();
		this.field2742 = var1.readUnsignedByte();
		this.method4870();
	}

	void method4870() {
	}
}
