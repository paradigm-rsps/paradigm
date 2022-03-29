public class Bounds {
	static SpritePixels[] headIconPkSprites;
	public int lowX;
	public int lowY;
	public int highX;
	public int highY;

	public Bounds(int var1, int var2, int var3, int var4) {
		this.setLow(var1, var2);
		this.setHigh(var3, var4);
	}

	public Bounds(int var1, int var2) {
		this(0, 0, var1, var2);
	}

	public void setLow(int var1, int var2) {
		this.lowX = var1;
		this.lowY = var2;
	}

	public void setHigh(int var1, int var2) {
		this.highX = var1;
		this.highY = var2;
	}

	public boolean method6983(int var1, int var2) {
		return var1 >= this.lowX && var1 < this.lowX + this.highX && var2 >= this.lowY && var2 < this.lowY + this.highY;
	}

	public void method6985(Bounds var1, Bounds var2) {
		this.method6986(var1, var2);
		this.method7001(var1, var2);
	}

	void method6986(Bounds var1, Bounds var2) {
		var2.lowX = this.lowX;
		var2.highX = this.highX;
		if (this.lowX < var1.lowX) {
			var2.highX = (var2.highX * -23049715 - (var1.lowX * -23049715 - this.lowX * -23049715)) * -1428810555;
			var2.lowX = var1.lowX;
		}

		if (var2.method6988() > var1.method6988()) {
			var2.highX -= var2.method6988() - var1.method6988();
		}

		if (var2.highX < 0) {
			var2.highX = 0;
		}

	}

	void method7001(Bounds var1, Bounds var2) {
		var2.lowY = this.lowY;
		var2.highY = this.highY;
		if (this.lowY < var1.lowY) {
			var2.highY = (var2.highY * 206931315 - (var1.lowY * 206931315 - this.lowY * 206931315)) * -1673451589;
			var2.lowY = var1.lowY;
		}

		if (var2.method6989() > var1.method6989()) {
			var2.highY -= var2.method6989() - var1.method6989();
		}

		if (var2.highY < 0) {
			var2.highY = 0;
		}

	}

	int method6988() {
		return this.lowX + this.highX;
	}

	int method6989() {
		return this.highY + this.lowY;
	}

	public String toString() {
		return null;
	}
}
