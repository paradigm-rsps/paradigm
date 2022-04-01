public abstract class RouteStrategy {
	public int approxDestinationX;
	public int approxDestinationY;
	public int approxDestinationSizeX;
	public int approxDestinationSizeY;

	protected RouteStrategy() {
	}

	protected abstract boolean hasArrived(int var1, int var2, int var3, CollisionMap var4);

	static void getIndex(Archive archive, int index) {
		if (class122.NetCache_reference != null) {
			class122.NetCache_reference.offset = index * 8 + 5;
			int var2 = class122.NetCache_reference.readInt();
			int var3 = class122.NetCache_reference.readInt();
			archive.loadIndex(var2, var3);
		} else {
			TriBool.requestNetFile(null, 255, 255, 0, (byte)0, true);
			NetCache.NetCache_archives[index] = archive;
		}
	}

	public static boolean isCharPrintable(char var0) {
		if (var0 >= ' ' && var0 <= '~') {
			return true;
		} else if (var0 >= 160 && var0 <= 255) {
			return true;
		} else {
			return var0 == 8364 || var0 == 338 || var0 == 8212 || var0 == 339 || var0 == 376;
		}
	}

	static void method3872() {
		class451.SpriteBuffer_xOffsets = null;
		class451.SpriteBuffer_yOffsets = null;
		class451.SpriteBuffer_spriteWidths = null;
		class451.SpriteBuffer_spriteHeights = null;
		GrandExchangeOfferUnitPriceComparator.SpriteBuffer_spritePalette = null;
		class460.SpriteBuffer_pixels = null;
	}

	static final int method3869() {
		if (Interpreter.clientPreferences.method2255()) {
			return class160.Client_plane;
		} else {
			int var0 = 3;
			if (Language.cameraPitch < 310) {
				int var1;
				int var2;
				if (Client.oculusOrbState == 1) {
					var1 = Messages.oculusOrbFocalPointX >> 7;
					var2 = class115.oculusOrbFocalPointY >> 7;
				} else {
					var1 = class19.localPlayer.x >> 7;
					var2 = class19.localPlayer.y >> 7;
				}

				int var3 = EnumComposition.cameraX >> 7;
				int var4 = CollisionMap.cameraZ >> 7;
				if (var3 < 0 || var4 < 0 || var3 >= 104 || var4 >= 104) {
					return class160.Client_plane;
				}

				if (var1 < 0 || var2 < 0 || var1 >= 104 || var2 >= 104) {
					return class160.Client_plane;
				}

				if ((Tiles.Tiles_renderFlags[class160.Client_plane][var3][var4] & 4) != 0) {
					var0 = class160.Client_plane;
				}

				int var5;
				if (var1 > var3) {
					var5 = var1 - var3;
				} else {
					var5 = var3 - var1;
				}

				int var6;
				if (var2 > var4) {
					var6 = var2 - var4;
				} else {
					var6 = var4 - var2;
				}

				int var7;
				int var8;
				if (var5 > var6) {
					var7 = var6 * 65536 / var5;
					var8 = 32768;

					while (var3 != var1) {
						if (var3 < var1) {
							++var3;
						} else if (var3 > var1) {
							--var3;
						}

						if ((Tiles.Tiles_renderFlags[class160.Client_plane][var3][var4] & 4) != 0) {
							var0 = class160.Client_plane;
						}

						var8 += var7;
						if (var8 >= 65536) {
							var8 -= 65536;
							if (var4 < var2) {
								++var4;
							} else if (var4 > var2) {
								--var4;
							}

							if ((Tiles.Tiles_renderFlags[class160.Client_plane][var3][var4] & 4) != 0) {
								var0 = class160.Client_plane;
							}
						}
					}
				} else if (var6 > 0) {
					var7 = var5 * 65536 / var6;
					var8 = 32768;

					while (var2 != var4) {
						if (var4 < var2) {
							++var4;
						} else if (var4 > var2) {
							--var4;
						}

						if ((Tiles.Tiles_renderFlags[class160.Client_plane][var3][var4] & 4) != 0) {
							var0 = class160.Client_plane;
						}

						var8 += var7;
						if (var8 >= 65536) {
							var8 -= 65536;
							if (var3 < var1) {
								++var3;
							} else if (var3 > var1) {
								--var3;
							}

							if ((Tiles.Tiles_renderFlags[class160.Client_plane][var3][var4] & 4) != 0) {
								var0 = class160.Client_plane;
							}
						}
					}
				}
			}

			if (class19.localPlayer.x >= 0 && class19.localPlayer.y >= 0 && class19.localPlayer.x < 13312 && class19.localPlayer.y < 13312) {
				if ((Tiles.Tiles_renderFlags[class160.Client_plane][class19.localPlayer.x >> 7][class19.localPlayer.y >> 7] & 4) != 0) {
					var0 = class160.Client_plane;
				}

				return var0;
			} else {
				return class160.Client_plane;
			}
		}
	}

	static final void method3875() {
		Client.field687 = Client.cycleCntr;
		class82.field1070 = true;
	}
}
