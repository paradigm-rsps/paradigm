public class class431 implements MouseWheel {
	public static final class431 field4598;
	public static final class431 field4595;
	public static final class431 field4600;
	final int field4597;
	public final int field4594;
	public final Class field4599;
	public final class427 field4596;

	static {
		field4598 = new class431(0, 0, Integer.class, new class428());
		field4595 = new class431(2, 1, Long.class, new class430());
		field4600 = new class431(1, 2, String.class, new class432());
	}

	class431(int var1, int var2, Class var3, class427 var4) {
		this.field4597 = var1;
		this.field4594 = var2;
		this.field4599 = var3;
		this.field4596 = var4;
	}

	public Object method7671(Buffer var1) {
		return this.field4596.vmethod7683(var1);
	}

	public int rsOrdinal() {
		return this.field4594;
	}

	public static boolean method7673(int var0) {
		return (var0 & 1) != 0;
	}
}
