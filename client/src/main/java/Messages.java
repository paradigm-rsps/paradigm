import java.util.HashMap;
import java.util.Map;

public class Messages {
	static final Map Messages_channels;
	static final IterableNodeHashTable Messages_hashTable;
	static final IterableDualNodeQueue Messages_queue;
	static int Messages_count;
	static int oculusOrbFocalPointX;

	static {
		Messages_channels = new HashMap();
		Messages_hashTable = new IterableNodeHashTable(1024);
		Messages_queue = new IterableDualNodeQueue();
		Messages_count = 0;
	}

	static void resumePauseWidget(int var0, int var1) {
		PacketBufferNode var2 = ItemContainer.getPacketBufferNode(ClientPacket.field2943, Client.packetWriter.isaacCipher);
		var2.packetBuffer.writeIntME(var0);
        var2.packetBuffer.writeShortLE(var1);
        Client.packetWriter.addNode(var2);
	}

	static final void drawSpriteOnMinimap(int var0, int var1, int var2, int var3, SpritePixels var4, SpriteMask var5) {
		if (var4 != null) {
			int var6 = Client.camAngleY & 2047;
			int var7 = var3 * var3 + var2 * var2;
			if (var7 <= 6400) {
				int var8 = Rasterizer3D.Rasterizer3D_sine[var6];
				int var9 = Rasterizer3D.Rasterizer3D_cosine[var6];
				int var10 = var3 * var8 + var9 * var2 >> 16;
				int var11 = var3 * var9 - var8 * var2 >> 16;
				if (var7 > 2500) {
					var4.method8178(var10 + var5.width / 2 - var4.width / 2, var5.height / 2 - var11 - var4.height / 2, var0, var1, var5.width, var5.height, var5.xStarts, var5.xWidths);
				} else {
					var4.drawTransBgAt(var0 + var10 + var5.width / 2 - var4.width / 2, var5.height / 2 + var1 - var11 - var4.height / 2);
				}

			}
		}
	}
}
