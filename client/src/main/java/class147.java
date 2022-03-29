public class class147 extends class128 {
	public static AbstractArchive musicTrackArchive;
	int field1658;
	// $FF: synthetic field
	final class131 this$0;

	class147(class131 var1) {
		this.this$0 = var1;
		this.field1658 = -1;
	}

	void vmethod3107(Buffer var1) {
		this.field1658 = var1.readUnsignedShort();
	}

	void vmethod3104(ClanSettings var1) {
		var1.method2924(this.field1658);
	}

	static final void method3052() {
		int var0 = PcmPlayer.field302 * 128 + 64;
		int var1 = DevicePcmPlayerProvider.field146 * 128 + 64;
		int var2 = Archive.getTileHeight(var0, var1, class160.Client_plane) - ClanSettings.field1608;
		class428.method7654(var0, var2, var1);
		var0 = class12.field62 * 128 + 64;
		var1 = WorldMapSectionType.field2778 * 128 + 64;
		var2 = Archive.getTileHeight(var0, var1, class160.Client_plane) - class351.field4157;
		int var3 = var0 - EnumComposition.cameraX;
		int var4 = var2 - FriendSystem.cameraY;
		int var5 = var1 - CollisionMap.cameraZ;
		int var6 = (int)Math.sqrt(var5 * var5 + var3 * var3);
		int var7 = (int)(Math.atan2(var4, var6) * 325.9490051269531D) & 2047;
		int var8 = (int)(Math.atan2(var3, var5) * -325.9490051269531D) & 2047;
		class132.method2864(var7, var8);
	}

	static final void method3051(int var0, int var1, boolean var2) {
		if (Client.currentClanChannels[var0] != null) {
			if (var1 >= 0 && var1 < Client.currentClanChannels[var0].method3056()) {
				ClanChannelMember var3 = (ClanChannelMember)Client.currentClanChannels[var0].members.get(var1);
				PacketBufferNode var4 = ItemContainer.getPacketBufferNode(ClientPacket.field2967, Client.packetWriter.isaacCipher);
				var4.packetBuffer.writeByte(4 + ItemLayer.stringCp1252NullTerminatedByteSize(var3.username.getName()));
				var4.packetBuffer.writeByte(var0);
				var4.packetBuffer.writeShort(var1);
				var4.packetBuffer.writeBoolean(var2);
				var4.packetBuffer.writeStringCp1252NullTerminated(var3.username.getName());
				Client.packetWriter.addNode(var4);
			}
		}
	}
}
