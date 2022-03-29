public class class133 extends class128 {
	int field1560;
	int field1558;
	int field1562;
	int field1561;
	// $FF: synthetic field
	final class131 this$0;

	class133(class131 var1) {
		this.this$0 = var1;
	}

	void vmethod3107(Buffer var1) {
		this.field1560 = var1.readInt();
		this.field1561 = var1.readInt();
		this.field1558 = var1.readUnsignedByte();
		this.field1562 = var1.readUnsignedByte();
	}

	void vmethod3104(ClanSettings var1) {
		var1.method2928(this.field1560, this.field1561, this.field1558, this.field1562);
	}
}
