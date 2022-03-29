import java.io.IOException;

public abstract class AbstractSocket {
	protected AbstractSocket() {
	}

	public abstract void close();

	public abstract int readUnsignedByte() throws IOException;

	public abstract int available() throws IOException;

	public abstract boolean isAvailable(int var1) throws IOException;

	public abstract int read(byte[] var1, int var2, int var3) throws IOException;

	public abstract void write(byte[] var1, int var2, int var3) throws IOException;

	public static Font method6899(AbstractArchive var0, AbstractArchive var1, String var2, String var3) {
		int var4 = var0.getGroupId(var2);
		int var5 = var0.getFileId(var4, var3);
		return class19.method319(var0, var1, var4, var5);
	}

	static final int method6884(int var0, int var1) {
		int var2 = var1 * 57 + var0;
		var2 ^= var2 << 13;
		int var3 = (var2 * var2 * 15731 + 789221) * var2 + 1376312589 & Integer.MAX_VALUE;
		return var3 >> 19 & 255;
	}
}
