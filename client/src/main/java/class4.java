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
            Client.prevCycle = Client.cycle;
            class340.field4109.method6086();

            for (int index = 0; index < Client.gpiLocalPlayers.length; ++index) {
                if (Client.gpiLocalPlayers[index] != null) {
                    class340.field4109.method6091((Client.gpiLocalPlayers[index].x >> 7) + ApproximateRouteStrategy.baseX, (Client.gpiLocalPlayers[index].y >> 7) + class250.baseY);
                }
            }
        }

	}
}
