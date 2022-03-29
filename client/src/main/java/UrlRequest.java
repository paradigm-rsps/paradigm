import java.net.URL;

public class UrlRequest {
	static ClanChannel field1344;
	static int field1346;
	final URL url;
	volatile boolean isDone0;
	volatile byte[] response0;

	UrlRequest(URL var1) {
		this.url = var1;
	}

	public boolean isDone() {
		return this.isDone0;
	}

	public byte[] getResponse() {
		return this.response0;
	}

	public String method2546() {
		return this.url.toString();
	}

	static int method2545(int var0, int var1, int var2) {
		return var0 << 28 | var1 << 14 | var2;
	}

	static final void method2555(int var0, int var1) {
		if (Client.currentClanChannels[var0] != null) {
			if (var1 >= 0 && var1 < Client.currentClanChannels[var0].method3056()) {
				ClanChannelMember var2 = (ClanChannelMember)Client.currentClanChannels[var0].members.get(var1);
				if (var2.rank == -1) {
					PacketBufferNode var3 = ItemContainer.getPacketBufferNode(ClientPacket.field2953, Client.packetWriter.isaacCipher);
					var3.packetBuffer.writeByte(3 + ItemLayer.stringCp1252NullTerminatedByteSize(var2.username.getName()));
					var3.packetBuffer.writeByte(var0);
					var3.packetBuffer.writeShort(var1);
					var3.packetBuffer.writeStringCp1252NullTerminated(var2.username.getName());
					Client.packetWriter.addNode(var3);
				}
			}
		}
	}
}
