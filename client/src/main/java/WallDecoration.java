public final class WallDecoration {
	int z;
	int x;
	int y;
	int orientation;
	int orientation2;
	int xOffset;
	int yOffset;
	public Renderable renderable1;
	public Renderable renderable2;
	public long tag;
	int flags;

	WallDecoration() {
		this.tag = 0L;
		this.flags = 0;
	}

	public static IndexedSprite[] method4516(AbstractArchive var0, int var1, int var2) {
		if (!GrandExchangeEvent.method6045(var0, var1, var2)) {
			return null;
		} else {
			IndexedSprite[] var4 = new IndexedSprite[class451.SpriteBuffer_spriteCount];

			for (int var5 = 0; var5 < class451.SpriteBuffer_spriteCount; ++var5) {
				IndexedSprite var6 = var4[var5] = new IndexedSprite();
				var6.width = class451.SpriteBuffer_spriteWidth;
				var6.height = class451.SpriteBuffer_spriteHeight;
				var6.xOffset = class451.SpriteBuffer_xOffsets[var5];
				var6.yOffset = class451.SpriteBuffer_yOffsets[var5];
				var6.subWidth = class451.SpriteBuffer_spriteWidths[var5];
				var6.subHeight = class451.SpriteBuffer_spriteHeights[var5];
				var6.palette = GrandExchangeOfferUnitPriceComparator.SpriteBuffer_spritePalette;
				var6.pixels = class460.SpriteBuffer_pixels[var5];
			}

			RouteStrategy.method3872();
			return var4;
		}
	}

	static World getNextWorldListWorld() {
		return World.World_listCount < World.World_count ? World.World_worlds[++World.World_listCount - 1] : null;
	}
}
