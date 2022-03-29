public class FriendLoginUpdate extends Link {
	public int field4259;
	public Username username;
	public short world;

	FriendLoginUpdate(Username var1, int var2) {
		this.field4259 = (int)(WorldMapSprite.method4989() / 1000L);
		this.username = var1;
		this.world = (short)var2;
	}

	static final void method6731(long var0) {
		try {
			Thread.sleep(var0);
		} catch (InterruptedException var3) {
		}

	}
}
