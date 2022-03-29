public enum PlayerType implements MouseWheel {
	PlayerType_normal(0, -1, true, false, true),
	PlayerType_playerModerator(1, 0, true, true, true),
	PlayerType_jagexModerator(2, 1, true, true, false),
	PlayerType_ironman(3, 2, false, false, true),
	PlayerType_ultimateIronman(4, 3, false, false, true),
	PlayerType_hardcoreIronman(5, 10, false, false, true),
	field3919(6, 22, false, false, true),
	field3926(7, 41, false, false, true),
	field3931(8, 42, false, false, true);

	final int id;
	public final int modIcon;
	public final boolean isPrivileged;
	public final boolean isUser;

	PlayerType(int var3, int var4, boolean var5, boolean var6, boolean var7) {
		this.id = var3;
		this.modIcon = var4;
		this.isPrivileged = var6;
		this.isUser = var7;
	}

	public int rsOrdinal() {
		return this.id;
	}

	public static FillMode[] FillMode_values() {
		return new FillMode[]{FillMode.field4695, FillMode.field4698, FillMode.SOLID};
	}
}
