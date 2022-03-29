public class SpriteMask extends DualNode {
	public final int width;
	public final int height;
	public final int[] xWidths;
	public final int[] xStarts;

	SpriteMask(int var1, int var2, int[] var3, int[] var4, int var5) {
		this.width = var1;
		this.height = var2;
		this.xWidths = var3;
		this.xStarts = var4;
	}

	public boolean contains(int var1, int var2) {
		if (var2 >= 0 && var2 < this.xStarts.length) {
			int var3 = this.xStarts[var2];
            return var1 >= var3 && var1 <= var3 + this.xWidths[var2];
		}

		return false;
	}

	static void setTapToDrop(boolean var0) {
		Client.tapToDrop = var0;
	}
}
