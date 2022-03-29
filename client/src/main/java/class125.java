public class class125 extends class128 {
	int field1524;
	int field1521;
	int field1522;
	int field1523;
	// $FF: synthetic field
	final class131 this$0;

	class125(class131 var1) {
		this.this$0 = var1;
		this.field1524 = -1;
	}

	void vmethod3107(Buffer var1) {
		this.field1524 = var1.readUnsignedShort();
		this.field1521 = var1.readInt();
		this.field1522 = var1.readUnsignedByte();
		this.field1523 = var1.readUnsignedByte();
	}

	void vmethod3104(ClanSettings var1) {
		var1.method2926(this.field1524, this.field1521, this.field1522, this.field1523);
	}

	public static final void method2804(class47 var0) {
		BuddyRankComparator.pcmPlayerProvider = var0;
	}

	public static void method2803(boolean var0) {
		if (var0 != HealthBar.ItemDefinition_inMembersWorld) {
			ItemComposition.ItemDefinition_cached.clear();
			ItemComposition.ItemDefinition_cachedModels.clear();
			ItemComposition.ItemDefinition_cachedSprites.clear();
			HealthBar.ItemDefinition_inMembersWorld = var0;
		}

	}
}
