public class Strings {
	public static String field3731;
	public static String field3684;
	public static String field3645;
	public static String field3870;
	public static String field3871;

	static {
		field3731 = "Please visit the support page for assistance.";
		field3684 = "Please visit the support page for assistance.";
		field3645 = "";
		field3870 = "Page has opened in a new window.";
		field3871 = "(Please check your popup blocker.)";
	}

	static final void readPlayerGpi(PacketBuffer var0) {
		var0.toBitMode();
		int var1 = Client.localPlayerIndex;
		Player var2 = class19.localPlayer = Client.players[var1] = new Player();
        var2.index = var1;
        int var3 = var0.readBits(30);
        byte var4 = (byte) (var3 >> 28);
        int var5 = var3 >> 14 & 16383;
        int var6 = var3 & 16383;
        var2.pathX[0] = var5 - ApproximateRouteStrategy.baseX;
        var2.x = (var2.pathX[0] << 7) + (var2.transformedSize() << 6);
        var2.pathY[0] = var6 - class250.baseY;
        var2.y = (var2.pathY[0] << 7) + (var2.transformedSize() << 6);
        class160.Client_plane = var2.plane = var4;
        if (Players.cached_appearances[var1] != null) {
            var2.read(Players.cached_appearances[var1]);
        }

        Players.localPlayerCount = 0;
        Players.localPlayerIndexes[++Players.localPlayerCount - 1] = var1;
        Players.skipFlags[var1] = 0;
        Players.externalPlayerCount = 0;

        for (int var7 = 1; var7 < 2048; ++var7) {
            if (var7 != var1) {
                int var8 = var0.readBits(18);
                int var9 = var8 >> 16;
                int var10 = var8 >> 8 & 597;
                int var11 = var8 & 597;
                Players.Players_regions[var7] = (var10 << 14) + var11 + (var9 << 28);
                Players.Players_orientations[var7] = 0;
                Players.Players_targetIndices[var7] = -1;
                Players.externalPlayerIndexes[++Players.externalPlayerCount - 1] = var7;
                Players.skipFlags[var7] = 0;
            }
		}

        var0.toByteMode();
	}

	static Message Messages_getMessage(int var0) {
		return (Message)Messages.Messages_hashTable.get(var0);
	}

	static final void method5795() {
        for (int var0 = 0; var0 < Players.localPlayerCount; ++var0) {
            Player var1 = Client.players[Players.localPlayerIndexes[var0]];
            var1.clearIsFriend();
        }

        class20.method326();
        if (Statics1.friendsChat != null) {
            Statics1.friendsChat.clearFriends();
        }

    }

	static final void method5798() {
		Client.field726 = Client.cycleCntr;
		class345.ClanChat_inClanChat = true;
	}
}
