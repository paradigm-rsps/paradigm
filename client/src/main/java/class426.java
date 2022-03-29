import java.util.Iterator;

public class class426 extends class392 implements class252 {
	final AbstractArchive field4586;
	final DemotingHashTable field4585;
	final int field4587;

	public class426(StudioGame var1, int var2, Language var3, AbstractArchive var4) {
		super(var1, var3, var4 != null ? var4.getGroupFileCount(var2) : 0);
		this.field4585 = new DemotingHashTable(64);
		this.field4586 = var4;
		this.field4587 = var2;
	}

	protected class394 vmethod7638(int var1) {
		synchronized(this.field4585) {
			class393 var2 = (class393)this.field4585.get(var1);
			if (var2 == null) {
				var2 = this.method7639(var1);
				this.field4585.method5145(var2, var1);
			}

			return var2;
		}
	}

	class393 method7639(int var1) {
		byte[] var2 = this.field4586.takeFile(this.field4587, var1);
		class393 var3 = new class393(var1);
		if (var2 != null) {
			var3.method7199(new Buffer(var2));
		}

		return var3;
	}

	public void method7640() {
		synchronized(this.field4585) {
			this.field4585.clear();
		}
	}

	public Iterator iterator() {
		return new class425(this);
	}
}
