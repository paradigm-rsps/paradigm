import java.security.SecureRandom;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SecureRandomFuture {
	public static int field949;
	public static int cacheGamebuild;
	ExecutorService executor;
	Future future;

	SecureRandomFuture() {
		this.executor = Executors.newSingleThreadExecutor();
		this.future = this.executor.submit(new SecureRandomCallable());
	}

	void shutdown() {
		this.executor.shutdown();
		this.executor = null;
	}

	boolean isDone() {
		return this.future.isDone();
	}

	SecureRandom get() {
		try {
			return (SecureRandom)this.future.get();
		} catch (Exception var2) {
			return NPC.method2364();
		}
	}

	public static final void insertMenuItemNoShift(String var0, String var1, int var2, int var3, int var4, int var5) {
		DynamicObject.insertMenuItem(var0, var1, var2, var3, var4, var5, false);
	}

	static final void method1968() {
        for (int var0 = 0; var0 < Players.gpiLocalPlayerCount; ++var0) {
            Player var1 = Client.gpiLocalPlayers[Players.gpiLocalPlayerIndexes[var0]];
            var1.method2143();
        }

    }
}
