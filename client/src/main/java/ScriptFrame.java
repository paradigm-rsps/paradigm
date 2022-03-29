public class ScriptFrame {
	Script script;
	int pc;
	int[] intLocals;
	String[] stringLocals;

	ScriptFrame() {
		this.pc = -1;
	}

	static SpritePixels method1084() {
		SpritePixels var0 = new SpritePixels();
		var0.width = class451.SpriteBuffer_spriteWidth;
		var0.height = class451.SpriteBuffer_spriteHeight;
		var0.xOffset = class451.SpriteBuffer_xOffsets[0];
		var0.yOffset = class451.SpriteBuffer_yOffsets[0];
		var0.subWidth = class451.SpriteBuffer_spriteWidths[0];
		var0.subHeight = class451.SpriteBuffer_spriteHeights[0];
		int var1 = var0.subWidth * var0.subHeight;
		byte[] var2 = class460.SpriteBuffer_pixels[0];
		var0.pixels = new int[var1];

		for (int var3 = 0; var3 < var1; ++var3) {
			var0.pixels[var3] = GrandExchangeOfferUnitPriceComparator.SpriteBuffer_spritePalette[var2[var3] & 255];
		}

		RouteStrategy.method3872();
		return var0;
	}

	static final void method1083(MenuAction var0, int var1, int var2) {
		SoundCache.menuAction(var0.param0, var0.param1, var0.opcode, var0.identifier, var0.action, var0.action, var1, var2);
	}
}
