final class class432 implements class427 {
	static Widget scriptDotWidget;

	public void vmethod7681(Object var1, Buffer var2) {
		this.method7682((String)var1, var2);
	}

	public Object vmethod7683(Buffer var1) {
		return var1.readStringCp1252NullTerminated();
	}

	void method7682(String var1, Buffer var2) {
		var2.writeStringCp1252NullTerminated(var1);
	}
}
