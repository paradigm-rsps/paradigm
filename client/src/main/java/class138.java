public class class138 extends class128 {
	int field1590;
	String field1587;
	// $FF: synthetic field
	final class131 this$0;

	class138(class131 var1) {
		this.this$0 = var1;
	}

	void vmethod3107(Buffer var1) {
		this.field1590 = var1.readInt();
		this.field1587 = var1.readStringCp1252NullTerminated();
	}

	void vmethod3104(ClanSettings var1) {
		var1.method2956(this.field1590, this.field1587);
	}
}
