import javax.net.ssl.HttpsURLConnection;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.*;
import java.util.Map.Entry;

public class class10 {
	HttpsURLConnection field49;
	final Map field47;
	class398 field46;
	Map field48;
	final class9 field53;
	boolean field51;
	boolean field52;
	int field50;

	public class10(URL var1, class9 var2) throws IOException {
		this.field51 = false;
		this.field52 = false;
		this.field50 = 300000;
		if (!var2.method65()) {
			throw new UnsupportedEncodingException("Unsupported request method used " + var2.method73());
		} else {
			this.field49 = (HttpsURLConnection)var1.openConnection();
			this.field49.setSSLSocketFactory(new class15());
			this.field53 = var2;
			this.field47 = new HashMap();
			this.field48 = new HashMap();
		}
	}

	public void method87(String var1, String var2) {
		if (!this.field51) {
			this.field47.put(var1, var2);
		}
	}

	String method100() {
		ArrayList var1 = new ArrayList(this.field48.entrySet());
		Collections.sort(var1, new class18(this));
		StringBuilder var2 = new StringBuilder();
		Iterator var3 = var1.iterator();

		while (var3.hasNext()) {
			Entry var4 = (Entry)var3.next();
			if (var2.length() > 0) {
				var2.append(",");
			}

			var2.append(((class397)var4.getKey()).method7217());
			float var5 = (Float)var4.getValue();
			if (var5 < 1.0F) {
				String var6 = Float.toString(var5).substring(0, 4);
				var2.append(";q=" + var6);
			}
		}

		return var2.toString();
	}

	void method89() throws ProtocolException {
		if (!this.field51) {
			this.field49.setRequestMethod(this.field53.method73());
			if (!this.field48.isEmpty()) {
				this.field47.put("Accept", this.method100());
			}

			Iterator var1 = this.field47.entrySet().iterator();

			while (var1.hasNext()) {
				Entry var2 = (Entry)var1.next();
				this.field49.setRequestProperty((String)var2.getKey(), (String)var2.getValue());
			}

			if (this.field53.method67() && this.field46 != null) {
				this.field49.setDoOutput(true);
				ByteArrayOutputStream var13 = new ByteArrayOutputStream();

				try {
					var13.write(this.field46.vmethod7229());
					var13.writeTo(this.field49.getOutputStream());
				} catch (IOException var11) {
					var11.printStackTrace();
				} finally {
					if (var13 != null) {
						try {
							var13.close();
						} catch (IOException var10) {
							var10.printStackTrace();
						}
					}

				}
			}

			this.field49.setConnectTimeout(this.field50);
			this.field49.setInstanceFollowRedirects(this.field52);
			this.field51 = true;
		}
	}

	boolean method105() throws IOException {
		if (!this.field51) {
			this.method89();
		}

		this.field49.connect();
		return this.field49.getResponseCode() == -1;
	}

	class21 method91() {
		try {
			if (!this.field51 || this.field49.getResponseCode() == -1) {
				return new class21("No REST response has been received yet.");
			}
		} catch (IOException var10) {
			this.field49.disconnect();
			return new class21("Error decoding REST response code: " + var10.getMessage());
		}

		class21 var1 = null;

		class21 var3;
		try {
			var1 = new class21(this.field49);
			return var1;
		} catch (IOException var8) {
			var3 = new class21("Error decoding REST response: " + var8.getMessage());
		} finally {
			this.field49.disconnect();
		}

		return var3;
	}

	static final void method106(String var0) {
		StringBuilder var10000 = new StringBuilder();
		Object var10001 = null;
		var10000 = var10000.append("Please remove ").append(var0);
		var10001 = null;
		String var1 = var10000.append(" from your friend list first").toString();
		Login.addGameMessage(30, "", var1);
	}

	static final void worldToScreen(int var0, int var1, int var2) {
		if (var0 >= 128 && var1 >= 128 && var0 <= 13056 && var1 <= 13056) {
			int var3 = Archive.getTileHeight(var0, var1, class160.Client_plane) - var2;
			var0 -= EnumComposition.cameraX;
			var3 -= FriendSystem.cameraY;
			var1 -= CollisionMap.cameraZ;
			int var4 = Rasterizer3D.Rasterizer3D_sine[Language.cameraPitch];
			int var5 = Rasterizer3D.Rasterizer3D_cosine[Language.cameraPitch];
			int var6 = Rasterizer3D.Rasterizer3D_sine[MusicPatchNode2.cameraYaw];
			int var7 = Rasterizer3D.Rasterizer3D_cosine[MusicPatchNode2.cameraYaw];
			int var8 = var6 * var1 + var0 * var7 >> 16;
			var1 = var7 * var1 - var0 * var6 >> 16;
			var0 = var8;
			var8 = var5 * var3 - var4 * var1 >> 16;
			var1 = var5 * var1 + var4 * var3 >> 16;
			if (var1 >= 50) {
				Client.viewportTempX = var0 * Client.viewportZoom / var1 + Client.viewportWidth / 2;
				Client.viewportTempY = var8 * Client.viewportZoom / var1 + Client.viewportHeight / 2;
			} else {
				Client.viewportTempX = -1;
				Client.viewportTempY = -1;
			}

		} else {
			Client.viewportTempX = -1;
			Client.viewportTempY = -1;
		}
	}

	static final void clanKickUser(String var0) {
		if (Statics1.friendsChat != null) {
			PacketBufferNode var1 = ItemContainer.getPacketBufferNode(ClientPacket.field2921, Client.packetWriter.isaacCipher);
			var1.packetBuffer.writeByte(ItemLayer.stringCp1252NullTerminatedByteSize(var0));
			var1.packetBuffer.writeStringCp1252NullTerminated(var0);
			Client.packetWriter.addNode(var1);
		}
	}
}
