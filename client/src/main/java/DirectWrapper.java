public class DirectWrapper extends Wrapper {
	Object obj;

	DirectWrapper(Object var1, int var2) {
		super(var2);
		this.obj = var1;
	}

	Object get() {
		return this.obj;
	}

	boolean isSoft() {
		return false;
	}
}
