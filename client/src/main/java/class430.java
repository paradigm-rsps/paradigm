final class class430 implements class427 {
	public void vmethod7681(Object var1, Buffer var2) {
		this.method7661((Long)var1, var2);
	}

	public Object vmethod7683(Buffer var1) {
		return var1.readLong();
	}

	void method7661(Long var1, Buffer var2) {
		var2.writeLong(var1);
	}
}
