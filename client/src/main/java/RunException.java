import java.applet.Applet;

public class RunException extends RuntimeException {
	public static Applet RunException_applet;
	public static String localPlayerName;
	public static int RunException_revision;
	public static int clientType;
	static byte[][][] field4754;
	String message;
	Throwable throwable;

	RunException(Throwable var1, String var2) {
		this.message = var2;
		this.throwable = var1;
	}
}
