public class class351 {
	static int field4157;
	int field4162;
	int field4156;
	int field4158;
	int field4159;

	public String toString() {
		boolean var1 = true;
		int var2 = 10 - Integer.toString(this.field4162).length();
		int var3 = 10 - Integer.toString(this.field4158).length();
		int var4 = 10 - Integer.toString(this.field4156).length();
		String var5 = "          ".substring(10 - var2);
		String var6 = "          ".substring(10 - var3);
		String var7 = "          ".substring(10 - var4);
		return "    Size: " + this.field4162 + var5 + "Created: " + this.field4156 + var7 + "Total used: " + this.field4158 + var6 + "Max-In-Use: " + this.field4159;
	}

	static String method6579(int var0) {
		return "<img=" + var0 + ">";
	}
}
