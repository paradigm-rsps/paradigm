import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class class420 implements class419 {
	Map field4549;
	final class444 field4550;

	public class420(class444 var1) {
		this.field4550 = var1;
	}

	public int vmethod7559(int var1) {
		if (this.field4549 != null) {
			class445 var2 = (class445)this.field4549.get(var1);
			if (var2 != null) {
				return (Integer)var2.field4681;
			}
		}

		return (Integer)this.field4550.vmethod8046(var1);
	}

	public void vmethod7561(int var1, Object var2) {
		if (this.field4549 == null) {
			this.field4549 = new HashMap();
			this.field4549.put(var1, new class445(var1, var2));
		} else {
			class445 var3 = (class445)this.field4549.get(var1);
			if (var3 == null) {
				this.field4549.put(var1, new class445(var1, var2));
			} else {
				var3.field4681 = var2;
			}
		}

	}

	public Iterator iterator() {
		return this.field4549 == null ? Collections.emptyIterator() : this.field4549.values().iterator();
	}
}
