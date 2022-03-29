public class class140 extends class144 {
	String field1623;
	int field1625;
	byte field1624;
	// $FF: synthetic field
	final class145 this$0;

	class140(class145 var1) {
		this.this$0 = var1;
		this.field1623 = null;
	}

	void vmethod3096(Buffer var1) {
		if (var1.readUnsignedByte() != 255) {
			--var1.offset;
			var1.readLong();
		}

		this.field1623 = var1.readStringCp1252NullTerminatedOrNull();
		this.field1625 = var1.readUnsignedShort();
		this.field1624 = var1.readByte();
		var1.readLong();
	}

	void vmethod3093(ClanChannel var1) {
		ClanChannelMember var2 = new ClanChannelMember();
		var2.username = new Username(this.field1623);
		var2.world = this.field1625;
		var2.rank = this.field1624;
		var1.addMember(var2);
	}

	public static void method2985() {
		WorldMapRegion.WorldMapRegion_cachedSprites.demote(5);
	}
}
