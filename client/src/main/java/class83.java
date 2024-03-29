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

	static final void updatePlayers(PacketBuffer buf, int var1) {
		int var2 = buf.offset;
		Players.Players_pendingUpdateCount = 0;
		UserComparator8.updatePlayers(buf);

		for (int i = 0; i < Players.Players_pendingUpdateCount; ++i) {
			int playerIndex = Players.Players_pendingUpdateIndices[i];
			Player player = Client.players[playerIndex];
			int mask = buf.readUnsignedByte();
			if ((mask & 64) != 0) {
				mask += buf.readUnsignedByte() << 8;
			}

			HealthBarUpdate.readPlayerUpdateFlags(buf, playerIndex, player, mask);
		}

		if (buf.offset - var2 != var1) {
			throw new RuntimeException(buf.offset - var2 + " " + var1);
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
