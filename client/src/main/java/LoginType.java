public class LoginType {
	public static final LoginType oldscape;
	static final LoginType field4552;
	static final LoginType field4554;
	static final LoginType field4551;
	static final LoginType field4555;
	static final LoginType field4556;
	static final LoginType field4557;
	static final LoginType field4558;
	public static final LoginType field4559;
	final int field4560;
	final String field4561;

	static {
		oldscape = new LoginType(8, 0, "", "");
		field4552 = new LoginType(5, 1, "", "");
		field4554 = new LoginType(4, 2, "", "");
		field4551 = new LoginType(1, 3, "", "");
		field4555 = new LoginType(0, 4, "", "");
		field4556 = new LoginType(3, 5, "", "");
		field4557 = new LoginType(6, 6, "", "");
		field4558 = new LoginType(2, 7, "", "");
		field4559 = new LoginType(7, -1, "", "", true, new LoginType[]{oldscape, field4552, field4554, field4555, field4551});
	}

	LoginType(int var1, int var2, String var3, String var4) {
		this.field4560 = var1;
		this.field4561 = var4;
	}

	LoginType(int var1, int var2, String var3, String var4, boolean var5, LoginType[] var6) {
		this.field4560 = var1;
		this.field4561 = var4;
	}

	public String toString() {
		return this.field4561;
	}
}
