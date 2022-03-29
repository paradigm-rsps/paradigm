public class class143 extends class128 {
	static IndexedSprite field1639;
	long field1636;
	String field1637;
	int field1638;
	// $FF: synthetic field
	final class131 this$0;

	class143(class131 var1) {
		this.this$0 = var1;
		this.field1636 = -1L;
		this.field1637 = null;
		this.field1638 = 0;
	}

	void vmethod3107(Buffer var1) {
		if (var1.readUnsignedByte() != 255) {
			--var1.offset;
			this.field1636 = var1.readLong();
		}

		this.field1637 = var1.readStringCp1252NullTerminatedOrNull();
		this.field1638 = var1.readUnsignedShort();
	}

	void vmethod3104(ClanSettings var1) {
		var1.method2918(this.field1636, this.field1637, this.field1638);
	}

	public static Widget getWidgetChild(int var0, int var1) {
		Widget var2 = HorizontalAlignment.getWidget(var0);
		if (var1 == -1) {
			return var2;
		} else {
			return var2 != null && var2.children != null && var1 < var2.children.length ? var2.children[var1] : null;
		}
	}

	static Frames getFrames(int var0) {
		Frames var1 = (Frames)SequenceDefinition.SequenceDefinition_cachedFrames.get(var0);
		if (var1 != null) {
			return var1;
		} else {
			var1 = class346.method6480(SequenceDefinition.SequenceDefinition_animationsArchive, DevicePcmPlayerProvider.SequenceDefinition_skeletonsArchive, var0, false);
			if (var1 != null) {
				SequenceDefinition.SequenceDefinition_cachedFrames.put(var1, var0);
			}

			return var1;
		}
	}
}
