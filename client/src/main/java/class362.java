public final class class362 implements Comparable {
	Object field4235;
	Object field4234;
	long field4236;
	long field4237;

	class362(Object var1, Object var2) {
		this.field4235 = var1;
		this.field4234 = var2;
	}

	int method6652(class362 var1) {
		if (this.field4237 < var1.field4237) {
			return -1;
		} else {
			return this.field4237 > var1.field4237 ? 1 : 0;
		}
	}

	public boolean equals(Object var1) {
		if (var1 instanceof class362) {
			return this.field4234.equals(((class362)var1).field4234);
		} else {
			throw new IllegalArgumentException();
		}
	}

	public int hashCode() {
		return this.field4234.hashCode();
	}

	public int compareTo(Object var1) {
		return this.method6652((class362)var1);
	}
}
