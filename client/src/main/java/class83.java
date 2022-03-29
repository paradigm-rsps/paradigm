public enum class83 implements MouseWheel {
	field1081(0, -1),
	field1074(1, 2),
	field1075(2, 3),
	field1076(3, 4),
	field1077(4, 5),
	field1078(5, 6);

	static ClanChannel guestClanChannel;
	static LoginType loginType;
	final int field1079;
	final int field1073;

	class83(int var3, int var4) {
		this.field1079 = var3;
		this.field1073 = var4;
	}

	public int rsOrdinal() {
		return this.field1073;
	}

	static final void updatePlayers(PacketBuffer var0, int var1) {
		int var2 = var0.offset;
		Players.Players_pendingUpdateCount = 0;
		UserComparator8.method2575(var0);

		for (int var3 = 0; var3 < Players.Players_pendingUpdateCount; ++var3) {
			int var4 = Players.Players_pendingUpdateIndices[var3];
			Player var5 = Client.players[var4];
			int var6 = var0.readUnsignedByte();
			if ((var6 & 64) != 0) {
				var6 += var0.readUnsignedByte() << 8;
			}

			HealthBarUpdate.method2213(var0, var4, var5, var6);
		}

		if (var0.offset - var2 != var1) {
			throw new RuntimeException(var0.offset - var2 + " " + var1);
		}
	}

	public static PrivateChatMode method2124(int var0) {
		PrivateChatMode[] var1 = Canvas.method389();

		for (int var2 = 0; var2 < var1.length; ++var2) {
			PrivateChatMode var3 = var1[var2];
			if (var0 == var3.field4737) {
				return var3;
			}
		}

		return null;
	}
}
