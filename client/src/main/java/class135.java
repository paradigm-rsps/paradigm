public class class135 extends class128 {
	static Archive archive12;
	boolean field1575;
	byte field1573;
	byte field1574;
	byte field1576;
	byte field1572;
	// $FF: synthetic field
	final class131 this$0;

	class135(class131 var1) {
		this.this$0 = var1;
	}

	void vmethod3107(Buffer var1) {
		this.field1575 = var1.readUnsignedByte() == 1;
		this.field1573 = var1.readByte();
		this.field1574 = var1.readByte();
		this.field1576 = var1.readByte();
		this.field1572 = var1.readByte();
	}

	void vmethod3104(ClanSettings var1) {
		var1.allowGuests = this.field1575;
		var1.field1602 = this.field1573;
		var1.field1603 = this.field1574;
		var1.field1604 = this.field1576;
		var1.field1613 = this.field1572;
	}
}
