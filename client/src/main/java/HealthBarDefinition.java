public class HealthBarDefinition extends DualNode {
	public static AbstractArchive HealthBarDefinition_archive;
	public static AbstractArchive field1867;
	static EvictingDualNodeHashTable HealthBarDefinition_cached;
	static EvictingDualNodeHashTable HealthBarDefinition_cachedSprites;
	public int field1871;
	public int int1;
	public int int2;
	public int int3;
	public int field1876;
	public int int5;
	int frontSpriteID;
	int backSpriteID;
	public int width;
	public int widthPadding;

	static {
		HealthBarDefinition_cached = new EvictingDualNodeHashTable(64);
		HealthBarDefinition_cachedSprites = new EvictingDualNodeHashTable(64);
	}

	HealthBarDefinition() {
		this.int1 = 255;
		this.int2 = 255;
		this.int3 = -1;
		this.field1876 = 1;
		this.int5 = 70;
		this.frontSpriteID = -1;
		this.backSpriteID = -1;
		this.width = 30;
		this.widthPadding = 0;
	}

	void decode(Buffer var1) {
		while (true) {
			int var2 = var1.readUnsignedByte();
			if (var2 == 0) {
				return;
			}

			this.decodeNext(var1, var2);
		}
	}

	void decodeNext(Buffer var1, int var2) {
		if (var2 == 1) {
			var1.readUnsignedShort();
		} else if (var2 == 2) {
			this.int1 = var1.readUnsignedByte();
		} else if (var2 == 3) {
			this.int2 = var1.readUnsignedByte();
		} else if (var2 == 4) {
			this.int3 = 0;
		} else if (var2 == 5) {
			this.int5 = var1.readUnsignedShort();
		} else if (var2 == 6) {
			var1.readUnsignedByte();
		} else if (var2 == 7) {
			this.frontSpriteID = var1.method7742();
		} else if (var2 == 8) {
			this.backSpriteID = var1.method7742();
		} else if (var2 == 11) {
			this.int3 = var1.readUnsignedShort();
		} else if (var2 == 14) {
			this.width = var1.readUnsignedByte();
		} else if (var2 == 15) {
			this.widthPadding = var1.readUnsignedByte();
		}

	}

	public SpritePixels getFrontSprite() {
		if (this.frontSpriteID < 0) {
			return null;
		} else {
			SpritePixels var1 = (SpritePixels)HealthBarDefinition_cachedSprites.get(this.frontSpriteID);
			if (var1 != null) {
				return var1;
			} else {
				var1 = InterfaceParent.SpriteBuffer_getSprite(field1867, this.frontSpriteID, 0);
				if (var1 != null) {
					HealthBarDefinition_cachedSprites.put(var1, this.frontSpriteID);
				}

				return var1;
			}
		}
	}

	public SpritePixels getBackSprite() {
		if (this.backSpriteID < 0) {
			return null;
		} else {
			SpritePixels var1 = (SpritePixels)HealthBarDefinition_cachedSprites.get(this.backSpriteID);
			if (var1 != null) {
				return var1;
			} else {
				var1 = InterfaceParent.SpriteBuffer_getSprite(field1867, this.backSpriteID, 0);
				if (var1 != null) {
					HealthBarDefinition_cachedSprites.put(var1, this.backSpriteID);
				}

				return var1;
			}
		}
	}

	public static class387 method3428() {
		synchronized(class387.field4344) {
			if (class387.field4346 == 0) {
				return new class387();
			} else {
				class387.field4344[--class387.field4346].method7061();
				return class387.field4344[class387.field4346];
			}
		}
	}

	public static float method3433(int var0) {
		var0 &= 16383;
		return (float)((double)((float)var0 / 16384.0F) * 6.283185307179586D);
	}
}
