public enum StudioGame implements MouseWheel {
	runescape("runescape", "RuneScape", 0),
	stellardawn("stellardawn", "Stellar Dawn", 1),
	game3("game3", "Game 3", 2),
	game4("game4", "Game 4", 3),
	game5("game5", "Game 5", 4),
	oldscape("oldscape", "RuneScape 2007", 5);

	public final String name;
	final int id;

	StudioGame(String var3, String var4, int var5) {
		this.name = var3;
		this.id = var5;
	}

	public int rsOrdinal() {
		return this.id;
	}

	public static void method5783(AbstractArchive var0) {
		InvDefinition.InvDefinition_archive = var0;
	}

	static void method5785() {
		class150.method3089(24);
		GrandExchangeEvent.setLoginResponseString("", "You were disconnected from the server.", "");
	}

	static WorldMap getWorldMap() {
		return ClanChannel.worldMap;
	}
}
