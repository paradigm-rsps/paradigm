public class class129 extends class128 {
	static ClanSettings field1535;
	int field1536;
	boolean field1534;
	// $FF: synthetic field
	final class131 this$0;

	class129(class131 var1) {
		this.this$0 = var1;
		this.field1536 = -1;
	}

	void vmethod3107(Buffer var1) {
		this.field1536 = var1.readUnsignedShort();
		this.field1534 = var1.readUnsignedByte() == 1;
	}

	void vmethod3104(ClanSettings var1) {
		var1.method2925(this.field1536, this.field1534);
	}

	static String method2849(String var0, boolean var1) {
		String var2 = var1 ? "https://" : "http://";
		if (Client.gameBuild == 1) {
			var0 = var0 + "-wtrc";
		} else if (Client.gameBuild == 2) {
			var0 = var0 + "-wtqa";
		} else if (Client.gameBuild == 3) {
			var0 = var0 + "-wtwip";
		} else if (Client.gameBuild == 5) {
			var0 = var0 + "-wti";
		} else if (Client.gameBuild == 4) {
			var0 = "local";
		}

		String var3 = "";
		if (class429.field4593 != null) {
			var3 = "/p=" + class429.field4593;
		}

		String var4 = "runescape.com";
		return var2 + var0 + "." + var4 + "/l=" + FriendSystem.clientLanguage + "/a=" + class7.field27 + var3 + "/";
	}
}
