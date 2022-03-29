import java.util.Date;

public abstract class class250 implements class252 {
	static int baseY;
	protected int field2872;

	protected class250(StudioGame var1, Language var2, int var3) {
		this.field2872 = var3;
	}

	static Date method5137() {
		java.util.Calendar var0 = java.util.Calendar.getInstance();
		var0.set(2, 0);
		var0.set(5, 1);
		var0.set(1, 1900);
		return var0.getTime();
	}
}
