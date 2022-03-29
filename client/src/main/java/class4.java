public final class class4 {
	static int[] Tiles_hue;
	static Clock clock;
	public static int canvasWidth;
	static Archive archive13;

	static long method16() {
		return Client.field608;
	}

	static void method19() {
		if (class340.field4109 != null) {
			Client.field764 = Client.cycle;
			class340.field4109.method6086();

			for (int var0 = 0; var0 < Client.players.length; ++var0) {
				if (Client.players[var0] != null) {
					class340.field4109.method6091((Client.players[var0].x >> 7) + ApproximateRouteStrategy.baseX, (Client.players[var0].y >> 7) + class250.baseY);
				}
			}
		}

	}
}
