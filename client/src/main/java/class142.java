public class class142 extends class128 {
	static int field1634;
	static SpritePixels[] mapDotSprites;
	int field1630;
	// $FF: synthetic field
	final class131 this$0;

	class142(class131 var1) {
		this.this$0 = var1;
		this.field1630 = -1;
	}

	void vmethod3107(Buffer var1) {
		this.field1630 = var1.readUnsignedShort();
	}

	void vmethod3104(ClanSettings var1) {
		var1.method2922(this.field1630);
	}

	public static boolean method3004(int var0, int var1) {
		return (var0 >> var1 + 1 & 1) != 0;
	}
}
