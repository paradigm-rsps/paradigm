import org.bouncycastle.crypto.tls.TlsClientProtocol;

import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.cert.Certificate;

class class12 extends SSLSocket {
	static int field62;
	static Widget field64;
	Certificate[] field65;
	// $FF: synthetic field
	final class15 this$0;
	// $FF: synthetic field
	final TlsClientProtocol val$tlsClientProtocol;
	// $FF: synthetic field
	final String val$host;

	class12(class15 var1, TlsClientProtocol var2, String var3) {
		this.this$0 = var1;
		this.val$tlsClientProtocol = var2;
		this.val$host = var3;
	}

	public OutputStream getOutputStream() throws IOException {
		return this.val$tlsClientProtocol.getOutputStream();
	}

	public void addHandshakeCompletedListener(HandshakeCompletedListener var1) {
	}

	public String[] getEnabledCipherSuites() {
		return null;
	}

	public String[] getEnabledProtocols() {
		return null;
	}

	public SSLSession getSession() {
		return new class17(this);
	}

	public String[] getSupportedProtocols() {
		return null;
	}

	public String[] getSupportedCipherSuites() {
		return null;
	}

	public boolean getUseClientMode() {
		return false;
	}

	public boolean getWantClientAuth() {
		return false;
	}

	public void removeHandshakeCompletedListener(HandshakeCompletedListener var1) {
	}

	public void setEnableSessionCreation(boolean var1) {
	}

	public void setWantClientAuth(boolean var1) {
	}

	public boolean getEnableSessionCreation() {
		return false;
	}

	public void setUseClientMode(boolean var1) {
	}

	public void setEnabledProtocols(String[] var1) {
	}

	public void startHandshake() throws IOException {
		this.val$tlsClientProtocol.connect(new class13(this));
	}

	public synchronized void close() throws IOException {
		this.val$tlsClientProtocol.close();
	}

	public void setNeedClientAuth(boolean var1) {
	}

	public void setEnabledCipherSuites(String[] var1) {
	}

	public boolean getNeedClientAuth() {
		return false;
	}

	public InputStream getInputStream() throws IOException {
		return this.val$tlsClientProtocol.getInputStream();
	}

	public static InvDefinition getInvDefinition(int var0) {
		InvDefinition var1 = (InvDefinition)InvDefinition.InvDefinition_cached.get(var0);
		if (var1 != null) {
			return var1;
		} else {
			byte[] var2 = InvDefinition.InvDefinition_archive.takeFile(5, var0);
			var1 = new InvDefinition();
			if (var2 != null) {
				var1.decode(new Buffer(var2));
			}

			InvDefinition.InvDefinition_cached.put(var1, var0);
			return var1;
		}
	}
}
