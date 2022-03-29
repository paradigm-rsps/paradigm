public abstract class class392 extends class250 implements class444 {
	protected class392(StudioGame var1, Language var2, int var3) {
		super(var1, var2, var3);
	}

	protected abstract class394 vmethod7638(int var1);

	public int method7171() {
		return super.field2872;
	}

	public Object vmethod8046(int var1) {
		class394 var2 = this.vmethod7638(var1);
		return var2 != null && var2.method7201() ? var2.method7200() : null;
	}

	public class445 method7173(Buffer var1) {
		int var2 = var1.readUnsignedShort();
		class394 var3 = this.vmethod7638(var2);
		class445 var4 = new class445(var2);
		Class var5 = var3.field4378.field4599;
		if (var5 == Integer.class) {
			var4.field4681 = var1.readInt();
		} else if (var5 == Long.class) {
			var4.field4681 = var1.readLong();
		} else if (var5 == String.class) {
			var4.field4681 = var1.readStringCp1252NullCircumfixed();
		} else {
			if (!class440.class.isAssignableFrom(var5)) {
				throw new IllegalStateException();
			}

			try {
				class440 var6 = (class440)var5.newInstance();
				var6.method8012(var1);
				var4.field4681 = var6;
			} catch (InstantiationException var7) {
			} catch (IllegalAccessException var8) {
			}
		}

		return var4;
	}

	public static int method7190(int var0, int var1) {
		return (int)Math.round(Math.atan2(var0, var1) * 2607.5945876176133D) & 16383;
	}

	public static void method7191(int var0, int var1, int var2, boolean var3) {
		PacketBufferNode var4 = ItemContainer.getPacketBufferNode(ClientPacket.field2940, Client.packetWriter.isaacCipher);
		var4.packetBuffer.writeShort(var0);
		var4.packetBuffer.method7796(var1);
		var4.packetBuffer.method7788(var2);
		var4.packetBuffer.method7806(var3 ? Client.field571 * 1761289457 * 34294801 : 0);
		Client.packetWriter.addNode(var4);
	}
}
