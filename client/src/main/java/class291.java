import java.net.MalformedURLException;
import java.net.URL;

public class class291 {
	public static Buffer NetCache_responseArchiveBuffer;
	UrlRequest field3302;
	SpritePixels field3305;

	class291(String var1, UrlRequester var2) {
		try {
			this.field3302 = var2.request(new URL(var1));
		} catch (MalformedURLException var4) {
			this.field3302 = null;
		}

	}

	class291(UrlRequest var1) {
		this.field3302 = var1;
	}

	SpritePixels method5586() {
		if (this.field3305 == null && this.field3302 != null && this.field3302.isDone()) {
			if (this.field3302.getResponse() != null) {
				this.field3305 = class29.method433(this.field3302.getResponse());
			}

			this.field3302 = null;
		}

		return this.field3305;
	}

	static final boolean method5588(char var0) {
		return var0 == 160 || var0 == ' ' || var0 == '_' || var0 == '-';
	}

	static final void method5587() {
		class428.method7654(class175.field1883, ReflectionCheck.field250, ModeWhere.field4080);
		class132.method2864(WorldMapRegion.field2706, class457.field4747);
		if (EnumComposition.cameraX == class175.field1883 && ReflectionCheck.field250 == FriendSystem.cameraY && CollisionMap.cameraZ == ModeWhere.field4080 && Language.cameraPitch == WorldMapRegion.field2706 && MusicPatchNode2.cameraYaw == class457.field4747) {
			Client.field739 = false;
			Client.isCameraLocked = false;
			class12.field62 = 0;
			WorldMapSectionType.field2778 = 0;
			class351.field4157 = 0;
			ClanChannel.field1660 = 0;
			JagexCache.field1737 = 0;
			class33.field231 = 0;
			Tiles.field996 = 0;
			PcmPlayer.field302 = 0;
			DevicePcmPlayerProvider.field146 = 0;
			ClanSettings.field1608 = 0;
		}

	}

	static void method5589() {
		Client.packetWriter.addNode(ItemContainer.getPacketBufferNode(ClientPacket.field2989, Client.packetWriter.isaacCipher));
		Client.oculusOrbState = 0;
	}
}
