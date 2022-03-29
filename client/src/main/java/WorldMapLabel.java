public class WorldMapLabel {
	public static AbstractArchive Widget_spritesArchive;
	String text;
	int width;
	int height;
	WorldMapLabelSize size;

	WorldMapLabel(String var1, int var2, int var3, WorldMapLabelSize var4) {
		this.text = var1;
		this.width = var2;
		this.height = var3;
		this.size = var4;
	}

	public static long method4997(int var0) {
		return ViewportMouse.ViewportMouse_entityTags[var0];
	}
}
