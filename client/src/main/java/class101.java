import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URLConnection;

public class class101 extends UrlRequester {
	static int worldPort;

	void vmethod2538(UrlRequest var1) throws IOException {
		URLConnection var2 = null;

		try {
			try {
				String var3 = var1.url.getProtocol();
				if (var3.equals("http")) {
					var2 = this.method2536(var1);
				} else {
					if (!var3.equals("https")) {
						var1.isDone0 = true;
						return;
					}

					var2 = this.method2537(var1);
				}

				this.method2515(var2, var1);
			} catch (IOException var7) {
			}

		} finally {
			var1.isDone0 = true;
			if (var2 != null) {
				if (var2 instanceof HttpURLConnection) {
					((HttpURLConnection)var2).disconnect();
				} else if (var2 instanceof HttpsURLConnection) {
					((HttpsURLConnection)var2).disconnect();
				}
			}

		}
	}

	URLConnection method2536(UrlRequest var1) throws IOException {
		URLConnection var2 = var1.url.openConnection();
		this.method2524(var2);
		return var2;
	}

	URLConnection method2537(UrlRequest var1) throws IOException {
		HttpsURLConnection var2 = (HttpsURLConnection)var1.url.openConnection();
		var2.setSSLSocketFactory(new class15());
		this.method2524(var2);
		return var2;
	}

	static Script getScript(int var0) {
		Script var1 = (Script)Script.Script_cached.get(var0);
		if (var1 != null) {
			return var1;
		} else {
			byte[] var2 = class135.archive12.takeFile(var0, 0);
			if (var2 == null) {
				return null;
			} else {
				var1 = Message.newScript(var2);
				Script.Script_cached.put(var1, var0);
				return var1;
			}
		}
	}
}
