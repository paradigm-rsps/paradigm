public class class134 extends class144 {
	static ClanSettings guestClanSettings;
	static int gameCyclesToDo;
	static int loginBoxCenter;
	int field1566;
	byte field1564;
	int field1565;
	String field1567;
	// $FF: synthetic field
	final class145 this$0;

	class134(class145 var1) {
		this.this$0 = var1;
		this.field1566 = -1;
	}

	void vmethod3096(Buffer var1) {
		this.field1566 = var1.readUnsignedShort();
		this.field1564 = var1.readByte();
		this.field1565 = var1.readUnsignedShort();
		var1.readLong();
		this.field1567 = var1.readStringCp1252NullTerminated();
	}

	void vmethod3093(ClanChannel var1) {
		ClanChannelMember var2 = (ClanChannelMember)var1.members.get(this.field1566);
		var2.rank = this.field1564;
		var2.world = this.field1565;
		var2.username = new Username(this.field1567);
	}
}
