public class ScriptEvent extends Node {
	public static byte[][][] ByteArrayPool_arrays;
	static class426 HitSplatDefinition_cachedSprites;
	Object[] args;
	boolean isMouseInputEvent;
	Widget widget;
	int mouseX;
	int mouseY;
	int opIndex;
	Widget dragTarget;
	int keyTyped;
	int keyPressed;
	String targetName;
	int field1053;
	int type;

	public ScriptEvent() {
		this.type = 76;
	}

	public void setArgs(Object[] var1) {
		this.args = var1;
	}

	public void setType(int var1) {
		this.type = var1;
	}

	static void method2111() {
		Tiles.Tiles_underlays = null;
		Tiles.Tiles_overlays = null;
		Tiles.Tiles_shapes = null;
		RunException.field4754 = null;
		DirectByteArrayCopier.field3277 = null;
		GrandExchangeOfferNameComparator.field4010 = null;
		Widget.field3495 = null;
		class4.Tiles_hue = null;
		Login.Tiles_saturation = null;
		WorldMapSectionType.Tiles_lightness = null;
		class300.Tiles_hueMultiplier = null;
		Tiles.field986 = null;
	}
}
