public class class355 {
	static IndexedSprite[] runesSprite;

	public static synchronized byte[] ByteArrayPool_getArray(int var0) {
		return ByteArrayPool.ByteArrayPool_getArrayBool(var0, false);
	}

	public static boolean method6620(int var0) {
		return (var0 >> 22 & 1) != 0;
	}
}
