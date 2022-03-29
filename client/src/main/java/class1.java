import java.util.concurrent.Callable;

public class class1 implements Callable {
	public static char field3;
	final Buffer field4;
	final class3 field0;
	// $FF: synthetic field
	final class7 this$0;

	class1(class7 var1, Buffer var2, class3 var3) {
		this.this$0 = var1;
		this.field4 = var2;
		this.field0 = var3;
	}

	public Object call() {
		return this.field0.vmethod11(this.field4);
	}

	public static void runScriptEvent(ScriptEvent var0) {
		class175.runScript(var0, 500000, 475000);
	}
}
