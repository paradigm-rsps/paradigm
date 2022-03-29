import java.lang.ref.SoftReference;

public class SoftWrapper extends Wrapper {
	SoftReference ref;

	SoftWrapper(Object var1, int var2) {
		super(var2);
		this.ref = new SoftReference(var1);
	}

	Object get() {
		return this.ref.get();
	}

	boolean isSoft() {
		return true;
	}
}
