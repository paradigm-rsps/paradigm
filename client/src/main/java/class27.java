import netscape.javascript.JSObject;

import java.applet.Applet;

public class class27 {
	public static void method416(Applet var0, String var1) throws Throwable {
		JSObject.getWindow(var0).eval(var1);
	}

	public static Object method412(Applet var0, String var1, Object[] var2) throws Throwable {
		return JSObject.getWindow(var0).call(var1, var2);
	}
}
