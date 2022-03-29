public class LoginPacket implements class261 {
	public static final LoginPacket field3135;
	static final LoginPacket field3132;
	public static final LoginPacket field3133;
	public static final LoginPacket field3134;
	public static final LoginPacket field3137;
	static final LoginPacket field3136;
	static final LoginPacket[] LoginPacket_indexedValues;
	public final int id;

	static {
		field3135 = new LoginPacket(14, 0);
		field3132 = new LoginPacket(15, 4);
		field3133 = new LoginPacket(16, -2);
		field3134 = new LoginPacket(18, -2);
		field3137 = new LoginPacket(19, -2);
		field3136 = new LoginPacket(27, 0);
		LoginPacket_indexedValues = new LoginPacket[32];
		LoginPacket[] var0 = VertexNormal.method4495();

		for (int var1 = 0; var1 < var0.length; ++var1) {
			LoginPacket_indexedValues[var0[var1].id] = var0[var1];
		}

	}

	LoginPacket(int var1, int var2) {
		this.id = var1;
	}
}
