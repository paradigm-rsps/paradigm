import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class class341 implements Iterator {
	static Widget scriptActiveWidget;
	class342 field4112;
	int field4114;
	int field4113;

	class341(class342 var1) {
		this.field4114 = 0;
		this.field4113 = this.field4112.field4115;
		this.field4112 = var1;
	}

	public boolean hasNext() {
		return this.field4114 < this.field4112.field4117;
	}

	public void remove() {
		throw new UnsupportedOperationException();
	}

	public Object next() {
		if (this.field4112.field4115 != this.field4113) {
			throw new ConcurrentModificationException();
		} else if (this.field4114 < this.field4112.field4117) {
			Object var1 = this.field4112.field4121[this.field4114].field4107;
			++this.field4114;
			return var1;
		} else {
			throw new NoSuchElementException();
		}
	}

	static final void PcmStream_disable(PcmStream var0) {
		var0.active = false;
		if (var0.sound != null) {
			var0.sound.position = 0;
		}

		for (PcmStream var1 = var0.firstSubStream(); var1 != null; var1 = var0.nextSubStream()) {
			PcmStream_disable(var1);
		}

	}
}
