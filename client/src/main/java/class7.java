import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class class7 {
	static int field27;
	ExecutorService field25;
	Future field24;
	final Buffer field23;
	final class3 field26;

	public class7(Buffer var1, class3 var2) {
		this.field25 = Executors.newSingleThreadExecutor();
		this.field23 = var1;
		this.field26 = var2;
		this.method51();
	}

	public boolean method42() {
		return this.field24.isDone();
	}

	public void method43() {
		this.field25.shutdown();
		this.field25 = null;
	}

	public Buffer method44() {
		try {
			return (Buffer)this.field24.get();
		} catch (Exception var2) {
			return null;
		}
	}

	void method51() {
		this.field24 = this.field25.submit(new class1(this, this.field23, this.field26));
	}

	public static int method53(int var0, int var1) {
		int var2 = var0 >>> 31;
		return (var0 + var2) / var1 - var2;
	}
}
