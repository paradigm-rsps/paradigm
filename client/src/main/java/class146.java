public class class146 extends class128 {
	int field1653;
	int field1652;
	// $FF: synthetic field
	final class131 this$0;

	class146(class131 var1) {
		this.this$0 = var1;
	}

	void vmethod3107(Buffer var1) {
		this.field1653 = var1.readInt();
		this.field1652 = var1.readInt();
	}

	void vmethod3104(ClanSettings var1) {
		var1.method2927(this.field1653, this.field1652);
	}

	static final boolean method3041(int var0, int var1, RouteStrategy var2, CollisionMap var3) {
		int var4 = var0;
		int var5 = var1;
		byte var6 = 64;
		byte var7 = 64;
		int var8 = var0 - var6;
		int var9 = var1 - var7;
		class194.directions[var6][var7] = 99;
		class194.distances[var6][var7] = 0;
		byte var10 = 0;
		int var11 = 0;
		class194.bufferX[var10] = var0;
		byte var10001 = var10;
		int var18 = var10 + 1;
		class194.bufferY[var10001] = var1;
		int[][] var12 = var3.flags;

		while (var11 != var18) {
			var4 = class194.bufferX[var11];
			var5 = class194.bufferY[var11];
			var11 = var11 + 1 & 4095;
			int var16 = var4 - var8;
			int var17 = var5 - var9;
			int var13 = var4 - var3.xInset;
			int var14 = var5 - var3.yInset;
			if (var2.hasArrived(2, var4, var5, var3)) {
				VarpDefinition.field1819 = var4;
				class194.field2202 = var5;
				return true;
			}

			int var15 = class194.distances[var16][var17] + 1;
			if (var16 > 0 && class194.directions[var16 - 1][var17] == 0 && (var12[var13 - 1][var14] & 19136782) == 0 && (var12[var13 - 1][var14 + 1] & 19136824) == 0) {
				class194.bufferX[var18] = var4 - 1;
				class194.bufferY[var18] = var5;
				var18 = var18 + 1 & 4095;
				class194.directions[var16 - 1][var17] = 2;
				class194.distances[var16 - 1][var17] = var15;
			}

			if (var16 < 126 && class194.directions[var16 + 1][var17] == 0 && (var12[var13 + 2][var14] & 19136899) == 0 && (var12[var13 + 2][var14 + 1] & 19136992) == 0) {
				class194.bufferX[var18] = var4 + 1;
				class194.bufferY[var18] = var5;
				var18 = var18 + 1 & 4095;
				class194.directions[var16 + 1][var17] = 8;
				class194.distances[var16 + 1][var17] = var15;
			}

			if (var17 > 0 && class194.directions[var16][var17 - 1] == 0 && (var12[var13][var14 - 1] & 19136782) == 0 && (var12[var13 + 1][var14 - 1] & 19136899) == 0) {
				class194.bufferX[var18] = var4;
				class194.bufferY[var18] = var5 - 1;
				var18 = var18 + 1 & 4095;
				class194.directions[var16][var17 - 1] = 1;
				class194.distances[var16][var17 - 1] = var15;
			}

			if (var17 < 126 && class194.directions[var16][var17 + 1] == 0 && (var12[var13][var14 + 2] & 19136824) == 0 && (var12[var13 + 1][var14 + 2] & 19136992) == 0) {
				class194.bufferX[var18] = var4;
				class194.bufferY[var18] = var5 + 1;
				var18 = var18 + 1 & 4095;
				class194.directions[var16][var17 + 1] = 4;
				class194.distances[var16][var17 + 1] = var15;
			}

			if (var16 > 0 && var17 > 0 && class194.directions[var16 - 1][var17 - 1] == 0 && (var12[var13 - 1][var14] & 19136830) == 0 && (var12[var13 - 1][var14 - 1] & 19136782) == 0 && (var12[var13][var14 - 1] & 19136911) == 0) {
				class194.bufferX[var18] = var4 - 1;
				class194.bufferY[var18] = var5 - 1;
				var18 = var18 + 1 & 4095;
				class194.directions[var16 - 1][var17 - 1] = 3;
				class194.distances[var16 - 1][var17 - 1] = var15;
			}

			if (var16 < 126 && var17 > 0 && class194.directions[var16 + 1][var17 - 1] == 0 && (var12[var13 + 1][var14 - 1] & 19136911) == 0 && (var12[var13 + 2][var14 - 1] & 19136899) == 0 && (var12[var13 + 2][var14] & 19136995) == 0) {
				class194.bufferX[var18] = var4 + 1;
				class194.bufferY[var18] = var5 - 1;
				var18 = var18 + 1 & 4095;
				class194.directions[var16 + 1][var17 - 1] = 9;
				class194.distances[var16 + 1][var17 - 1] = var15;
			}

			if (var16 > 0 && var17 < 126 && class194.directions[var16 - 1][var17 + 1] == 0 && (var12[var13 - 1][var14 + 1] & 19136830) == 0 && (var12[var13 - 1][var14 + 2] & 19136824) == 0 && (var12[var13][var14 + 2] & 19137016) == 0) {
				class194.bufferX[var18] = var4 - 1;
				class194.bufferY[var18] = var5 + 1;
				var18 = var18 + 1 & 4095;
				class194.directions[var16 - 1][var17 + 1] = 6;
				class194.distances[var16 - 1][var17 + 1] = var15;
			}

			if (var16 < 126 && var17 < 126 && class194.directions[var16 + 1][var17 + 1] == 0 && (var12[var13 + 1][var14 + 2] & 19137016) == 0 && (var12[var13 + 2][var14 + 2] & 19136992) == 0 && (var12[var13 + 2][var14 + 1] & 19136995) == 0) {
				class194.bufferX[var18] = var4 + 1;
				class194.bufferY[var18] = var5 + 1;
				var18 = var18 + 1 & 4095;
				class194.directions[var16 + 1][var17 + 1] = 12;
				class194.distances[var16 + 1][var17 + 1] = var15;
			}
		}

		VarpDefinition.field1819 = var4;
		class194.field2202 = var5;
		return false;
	}

	public static void method3040() {
		HitSplatDefinition.HitSplatDefinition_cached.clear();
		HitSplatDefinition.HitSplatDefinition_cachedSprites.clear();
		HitSplatDefinition.HitSplatDefinition_cachedFonts.clear();
	}

	static Widget method3043(int var0, Widget var1, int var2, int var3, int var4, int var5, int var6, float[] var7) {
		Widget var8 = new Widget();
		var8.type = var0;
		var8.parentId = var1.id;
		var8.childIndex = var2;
		var8.isIf3 = true;
		var8.xAlignment = var3;
		var8.yAlignment = var4;
		var8.widthAlignment = var5;
		var8.heightAlignment = var6;
		var8.rawX = (int)((float)var1.width * var7[0]);
		var8.rawY = (int)(var7[1] * (float)var1.height);
		var8.rawWidth = (int)(var7[2] * (float)var1.width);
		var8.rawHeight = (int)((float)var1.height * var7[3]);
		return var8;
	}

	static final void rebuildRegion(boolean isInstance, PacketBuffer buf) {
		Client.isInInstance = isInstance;
		int chunkX;
		int chunkY;
		int regionX;
		int regionY;
		int var7;
		int var8;
		if (!Client.isInInstance) {
			chunkX = buf.readUnsignedShortAdd();
			chunkY = buf.readUnsignedShortAdd();
			int regionCount = buf.readUnsignedShort();
			Message.xteaKeys = new int[regionCount][4];

			for (regionX = 0; regionX < regionCount; ++regionX) {
				for (regionY = 0; regionY < 4; ++regionY) {
					Message.xteaKeys[regionX][regionY] = buf.readInt();
				}
			}

			class123.regions = new int[regionCount];
			ObjectComposition.regionMapArchiveIds = new int[regionCount];
			Message.regionLandArchiveIds = new int[regionCount];
			WorldMapSectionType.regionLandArchives = new byte[regionCount][];
			Occluder.regionMapArchives = new byte[regionCount][];
			boolean var16 = false;
			if (Client.field753) {
				if ((chunkX / 8 == 48 || chunkX / 8 == 49) && chunkY / 8 == 48) {
					var16 = true;
				}

				if (chunkX / 8 == 48 && chunkY / 8 == 148) {
					var16 = true;
				}
			}

			regionCount = 0;

			for (regionY = (chunkX - 6) / 8; regionY <= (chunkX + 6) / 8; ++regionY) {
				for (var7 = (chunkY - 6) / 8; var7 <= (chunkY + 6) / 8; ++var7) {
					var8 = var7 + (regionY << 8);
					if (!var16 || var7 != 49 && var7 != 149 && var7 != 147 && regionY != 50 && (regionY != 49 || var7 != 47)) {
						class123.regions[regionCount] = var8;
						ObjectComposition.regionMapArchiveIds[regionCount] = class302.archive5.getGroupId("m" + regionY + "_" + var7);
						Message.regionLandArchiveIds[regionCount] = class302.archive5.getGroupId("l" + regionY + "_" + var7);
						++regionCount;
					}
				}
			}

			class321.method6030(chunkX, chunkY, true);
		} else {
			chunkX = buf.readUnsignedShortLEAdd();
			chunkY = buf.readUnsignedShortLE();
			boolean var15 = buf.readUnsignedByte() == 1;
			regionX = buf.readUnsignedShort();
            buf.toBitMode();

			int var9;
			for (regionY = 0; regionY < 4; ++regionY) {
				for (var7 = 0; var7 < 13; ++var7) {
					for (var8 = 0; var8 < 13; ++var8) {
						var9 = buf.readBits(1);
						if (var9 == 1) {
							Client.instanceChunkTemplates[regionY][var7][var8] = buf.readBits(26);
						} else {
							Client.instanceChunkTemplates[regionY][var7][var8] = -1;
						}
					}
				}
			}

            buf.toByteMode();
            Message.xteaKeys = new int[regionX][4];

			for (regionY = 0; regionY < regionX; ++regionY) {
				for (var7 = 0; var7 < 4; ++var7) {
					Message.xteaKeys[regionY][var7] = buf.readInt();
				}
			}

			class123.regions = new int[regionX];
			ObjectComposition.regionMapArchiveIds = new int[regionX];
			Message.regionLandArchiveIds = new int[regionX];
			WorldMapSectionType.regionLandArchives = new byte[regionX][];
			Occluder.regionMapArchives = new byte[regionX][];
			regionX = 0;

			for (regionY = 0; regionY < 4; ++regionY) {
				for (var7 = 0; var7 < 13; ++var7) {
					for (var8 = 0; var8 < 13; ++var8) {
						var9 = Client.instanceChunkTemplates[regionY][var7][var8];
						if (var9 != -1) {
							int var10 = var9 >> 14 & 1023;
							int var11 = var9 >> 3 & 2047;
							int var12 = (var10 / 8 << 8) + var11 / 8;

							int var13;
							for (var13 = 0; var13 < regionX; ++var13) {
								if (class123.regions[var13] == var12) {
									var12 = -1;
									break;
								}
							}

							if (var12 != -1) {
								class123.regions[regionX] = var12;
								var13 = var12 >> 8 & 255;
								int var14 = var12 & 255;
								ObjectComposition.regionMapArchiveIds[regionX] = class302.archive5.getGroupId("m" + var13 + "_" + var14);
								Message.regionLandArchiveIds[regionX] = class302.archive5.getGroupId("l" + var13 + "_" + var14);
								++regionX;
							}
						}
					}
				}
			}

			class321.method6030(chunkY, chunkX, !var15);
		}

	}
}
