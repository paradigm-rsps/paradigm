public abstract class class144 extends Node {
	class144() {
	}

	abstract void vmethod3096(Buffer var1);

	abstract void vmethod3093(ClanChannel var1);

	static void method3022(Float var0, Float var1) {
		if (var0 + class114.field1396 < 1.3333334F) {
			float var2 = var0 - 2.0F;
			float var3 = var0 - 1.0F;
			float var4 = (float)Math.sqrt(var2 * var2 - var3 * 4.0F * var3);
			float var5 = (var4 + -var2) * 0.5F;
			if (var1 + class114.field1396 > var5) {
				var1 = var5 - class114.field1396;
			} else {
				var5 = (-var2 - var4) * 0.5F;
				if (var1 < var5 + class114.field1396) {
					var1 = class114.field1396 + var5;
				}
			}
		} else {
			var0 = 1.3333334F - class114.field1396;
			var1 = 0.33333334F - class114.field1396;
		}

	}

	static void method3014() {
		if (class19.localPlayer.x >> 7 == Client.destinationX && class19.localPlayer.y >> 7 == Client.destinationY) {
			Client.destinationX = 0;
		}

	}
}
