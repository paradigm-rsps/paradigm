public abstract class Clock {
	Clock() {
	}

	public abstract void mark();

	public abstract int wait(int var1, int var2);

	public static int method3322(int var0, int var1, int var2, int var3, int var4, int var5) {
		if ((var5 & 1) == 1) {
			int var6 = var3;
			var3 = var4;
			var4 = var6;
		}

		var2 &= 3;
		if (var2 == 0) {
			return var1;
		} else if (var2 == 1) {
			return 7 - var0 - (var3 - 1);
		} else {
			return var2 == 2 ? 7 - var1 - (var4 - 1) : var0;
		}
	}

	static IndexedSprite method3323() {
		IndexedSprite var0 = new IndexedSprite();
		var0.width = class451.SpriteBuffer_spriteWidth;
		var0.height = class451.SpriteBuffer_spriteHeight;
		var0.xOffset = class451.SpriteBuffer_xOffsets[0];
		var0.yOffset = class451.SpriteBuffer_yOffsets[0];
		var0.subWidth = class451.SpriteBuffer_spriteWidths[0];
		var0.subHeight = class451.SpriteBuffer_spriteHeights[0];
		var0.palette = GrandExchangeOfferUnitPriceComparator.SpriteBuffer_spritePalette;
		var0.pixels = class460.SpriteBuffer_pixels[0];
		RouteStrategy.method3872();
		return var0;
	}
}
