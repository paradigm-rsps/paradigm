import java.io.File;

public class VertexNormal {
	public static File cacheDir;
	int x;
	int y;
	int z;
	int magnitude;

	VertexNormal() {
	}

	VertexNormal(VertexNormal var1) {
		this.x = var1.x;
		this.y = var1.y;
		this.z = var1.z;
		this.magnitude = var1.magnitude;
	}

	static LoginPacket[] method4495() {
		return new LoginPacket[]{LoginPacket.field3133, LoginPacket.field3134, LoginPacket.field3135, LoginPacket.field3137, LoginPacket.field3132, LoginPacket.field3136};
	}

	public static void method4496(Huffman var0) {
		class282.huffman = var0;
	}

	static void method4498() {
		if (Client.renderSelf) {
			class166.addPlayerToScene(class19.localPlayer, false);
		}

	}

	static void method4497(int var0, String var1) {
		int var2 = Players.Players_count;
		int[] var3 = Players.Players_indices;
		boolean var4 = false;
		Username var5 = new Username(var1, class83.loginType);

		for (int var6 = 0; var6 < var2; ++var6) {
			Player var7 = Client.players[var3[var6]];
			if (var7 != null && var7 != class19.localPlayer && var7.username != null && var7.username.equals(var5)) {
				PacketBufferNode var8;
				if (var0 == 1) {
					var8 = ItemContainer.getPacketBufferNode(ClientPacket.field2913, Client.packetWriter.isaacCipher);
                    var8.packetBuffer.writeShortLE(var3[var6]);
                    var8.packetBuffer.method7786(0);
					Client.packetWriter.addNode(var8);
				} else if (var0 == 4) {
					var8 = ItemContainer.getPacketBufferNode(ClientPacket.field2904, Client.packetWriter.isaacCipher);
					var8.packetBuffer.method7787(0);
					var8.packetBuffer.writeShort(var3[var6]);
					Client.packetWriter.addNode(var8);
				} else if (var0 == 6) {
                    var8 = ItemContainer.getPacketBufferNode(ClientPacket.field2903, Client.packetWriter.isaacCipher);
                    var8.packetBuffer.writeShortLEADD(var3[var6]);
                    var8.packetBuffer.writeByte(0);
					Client.packetWriter.addNode(var8);
				} else if (var0 == 7) {
					var8 = ItemContainer.getPacketBufferNode(ClientPacket.field2898, Client.packetWriter.isaacCipher);
					var8.packetBuffer.writeShort(var3[var6]);
					var8.packetBuffer.method7786(0);
					Client.packetWriter.addNode(var8);
				}

				var4 = true;
				break;
			}
		}

		if (!var4) {
			Login.addGameMessage(4, "", "Unable to find " + var1);
		}

	}
}
