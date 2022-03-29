public class class151 extends class144 {
	String field1684;
	byte field1682;
	byte field1683;
	// $FF: synthetic field
	final class145 this$0;

	class151(class145 var1) {
		this.this$0 = var1;
	}

	void vmethod3096(Buffer var1) {
		this.field1684 = var1.readStringCp1252NullTerminatedOrNull();
		if (this.field1684 != null) {
			var1.readUnsignedByte();
			this.field1682 = var1.readByte();
			this.field1683 = var1.readByte();
		}

	}

	void vmethod3093(ClanChannel var1) {
		var1.name = this.field1684;
		if (this.field1684 != null) {
			var1.field1666 = this.field1682;
			var1.field1669 = this.field1683;
		}

	}

	public static FloorUnderlayDefinition method3100(int var0) {
		FloorUnderlayDefinition var1 = (FloorUnderlayDefinition)FloorUnderlayDefinition.FloorUnderlayDefinition_cached.get(var0);
		if (var1 != null) {
			return var1;
		} else {
			byte[] var2 = FloorUnderlayDefinition.FloorUnderlayDefinition_archive.takeFile(1, var0);
			var1 = new FloorUnderlayDefinition();
			if (var2 != null) {
				var1.decode(new Buffer(var2), var0);
			}

			var1.postDecode();
			FloorUnderlayDefinition.FloorUnderlayDefinition_cached.put(var1, var0);
			return var1;
		}
	}
}
