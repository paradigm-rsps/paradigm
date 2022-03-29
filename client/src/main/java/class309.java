public enum class309 implements MouseWheel {
	field3918(-1),
	field3910(0),
	field3912(1),
	field3911(2);

	public static int canvasHeight;
	final int field3914;

	class309(int var3) {
		this.field3914 = var3;
	}

	public int rsOrdinal() {
		return this.field3914;
	}

	public static void method5804() {
		if (NetCache.NetCache_socket != null) {
			NetCache.NetCache_socket.close();
		}

	}
}
