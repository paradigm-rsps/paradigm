import java.util.Comparator;
import java.util.Map.Entry;

class class18 implements Comparator {
	static class158 mouseWheel;
	// $FF: synthetic field
	final class10 this$0;

	class18(class10 var1) {
		this.this$0 = var1;
	}

	int method283(Entry var1, Entry var2) {
		return ((Float)var2.getValue()).compareTo((Float)var1.getValue());
	}

	public int compare(Object var1, Object var2) {
		return this.method283((Entry)var1, (Entry)var2);
	}

	public boolean equals(Object var1) {
		return super.equals(var1);
	}

	public static int method282(int var0) {
		return class400.field4390[var0 & 16383];
	}

	public static int method294() {
		return KeyHandler.KeyHandler_idleCycles;
	}

	static int Messages_getLastChatID(int var0) {
		Message var1 = (Message)Messages.Messages_hashTable.get(var0);
		if (var1 == null) {
			return -1;
		} else {
			return var1.previousDual == Messages.Messages_queue.sentinel ? -1 : ((Message)var1.previousDual).count;
		}
	}
}
