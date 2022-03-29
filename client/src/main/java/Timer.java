public class Timer {
	long field4185;
	long field4183;
	public boolean field4182;
	long field4189;
	long field4186;
	long field4187;
	int field4188;
	int field4184;
	int field4190;
	int field4191;

	public Timer() {
		this.field4185 = -1L;
		this.field4183 = -1L;
		this.field4182 = false;
		this.field4189 = 0L;
		this.field4186 = 0L;
		this.field4187 = 0L;
		this.field4188 = 0;
		this.field4184 = 0;
		this.field4190 = 0;
		this.field4191 = 0;
	}

	public void method6594() {
		this.field4185 = WorldMapSprite.method4989();
	}

	public void method6595() {
		if (this.field4185 != -1L) {
			this.field4186 = WorldMapSprite.method4989() - this.field4185;
			this.field4185 = -1L;
		}

	}

	public void method6596(int var1) {
		this.field4183 = WorldMapSprite.method4989();
		this.field4188 = var1;
	}

	public void method6597() {
		if (this.field4183 != -1L) {
			this.field4189 = WorldMapSprite.method4989() - this.field4183;
			this.field4183 = -1L;
		}

		++this.field4190;
		this.field4182 = true;
	}

	public void method6593() {
		this.field4182 = false;
		this.field4184 = 0;
	}

	public void method6598() {
		this.method6597();
	}

	public void write(Buffer var1) {
		long var2 = this.field4186;
		var2 /= 10L;
		if (var2 < 0L) {
			var2 = 0L;
		} else if (var2 > 65535L) {
			var2 = 65535L;
		}

		var1.writeShort((int)var2);
		long var4 = this.field4189;
		var4 /= 10L;
		if (var4 < 0L) {
			var4 = 0L;
		} else if (var4 > 65535L) {
			var4 = 65535L;
		}

		var1.writeShort((int)var4);
		long var6 = this.field4187;
		var6 /= 10L;
		if (var6 < 0L) {
			var6 = 0L;
		} else if (var6 > 65535L) {
			var6 = 65535L;
		}

		var1.writeShort((int)var6);
		var1.writeShort(this.field4188);
		var1.writeShort(this.field4184);
		var1.writeShort(this.field4190);
		var1.writeShort(this.field4191);
	}
}
