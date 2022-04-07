public class DynamicObject extends Renderable {
	static Bounds field973;
	static SpritePixels[] headIconPrayerSprites;
	int id;
	int type;
	int orientation;
	int plane;
	int x;
	int y;
	SequenceDefinition sequenceDefinition;
	int frame;
	int cycleStart;

	DynamicObject(int var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, Renderable var9) {
		this.id = var1;
		this.type = var2;
		this.orientation = var3;
		this.plane = var4;
		this.x = var5;
		this.y = var6;
		if (var7 != -1) {
			this.sequenceDefinition = ItemContainer.SequenceDefinition_get(var7);
			this.frame = 0;
			this.cycleStart = Client.cycle - 1;
			if (this.sequenceDefinition.field2172 == 0 && var9 != null && var9 instanceof DynamicObject) {
				DynamicObject var10 = (DynamicObject)var9;
				if (var10.sequenceDefinition == this.sequenceDefinition) {
					this.frame = var10.frame;
					this.cycleStart = var10.cycleStart;
					return;
				}
			}

			if (var8 && this.sequenceDefinition.frameCount != -1) {
				if (!this.sequenceDefinition.isCachedModelIdSet()) {
					this.frame = (int)(Math.random() * (double)this.sequenceDefinition.frameIds.length);
					this.cycleStart -= (int)(Math.random() * (double)this.sequenceDefinition.frameLengths[this.frame]);
				} else {
					this.frame = (int)(Math.random() * (double)this.sequenceDefinition.method3827());
				}
			}
		}

	}

	protected final Model getModel() {
		int var2;
		if (this.sequenceDefinition != null) {
			int var1 = Client.cycle - this.cycleStart;
			if (var1 > 100 && this.sequenceDefinition.frameCount > 0) {
				var1 = 100;
			}

			if (this.sequenceDefinition.isCachedModelIdSet()) {
				var2 = this.sequenceDefinition.method3827();
				this.frame += var1;
				var1 = 0;
				if (this.frame >= var2) {
					this.frame = var2 - this.sequenceDefinition.frameCount;
					if (this.frame < 0 || this.frame > var2) {
						this.sequenceDefinition = null;
					}
				}
			} else {
				label78: {
					do {
						do {
							if (var1 <= this.sequenceDefinition.frameLengths[this.frame]) {
								break label78;
							}

							var1 -= this.sequenceDefinition.frameLengths[this.frame];
							++this.frame;
						} while(this.frame < this.sequenceDefinition.frameIds.length);

						this.frame -= this.sequenceDefinition.frameCount;
					} while(this.frame >= 0 && this.frame < this.sequenceDefinition.frameIds.length);

					this.sequenceDefinition = null;
				}
			}

			this.cycleStart = Client.cycle - var1;
		}

		ObjectComposition var12 = class116.getObjectDefinition(this.id);
		if (var12.transforms != null) {
			var12 = var12.transform();
		}

		if (var12 == null) {
			return null;
		} else {
			int var3;
			if (this.orientation != 1 && this.orientation != 3) {
				var2 = var12.sizeX;
				var3 = var12.sizeY;
			} else {
				var2 = var12.sizeY;
				var3 = var12.sizeX;
			}

			int var4 = (var2 >> 1) + this.x;
			int var5 = (var2 + 1 >> 1) + this.x;
			int var6 = (var3 >> 1) + this.y;
			int var7 = (var3 + 1 >> 1) + this.y;
			int[][] var8 = Tiles.Tiles_heights[this.plane];
			int var9 = var8[var4][var6] + var8[var5][var6] + var8[var4][var7] + var8[var5][var7] >> 2;
			int var10 = (this.x << 7) + (var2 << 6);
			int var11 = (this.y << 7) + (var3 << 6);
			return var12.getModelDynamic(this.type, this.orientation, var8, var10, var9, var11, this.sequenceDefinition, this.frame);
		}
	}

	static String method1988(Buffer var0, int var1) {
		try {
			int var2 = var0.readUShortSmart();
			if (var2 > var1) {
				var2 = var1;
			}

			byte[] var3 = new byte[var2];
			var0.offset += class282.huffman.decompress(var0.array, var0.offset, var3, 0, var2);
			String var4 = class113.decodeStringCp1252(var3, 0, var2);
			return var4;
		} catch (Exception var6) {
			return "Cabbage";
		}
	}

	public static int method1987(int var0) {
		long var2 = ViewportMouse.ViewportMouse_entityTags[var0];
		int var1 = (int)(var2 >>> 0 & 127L);
		return var1;
	}

	static final void method1985(String var0, int var1) {
		PacketBufferNode var2 = ItemContainer.getPacketBufferNode(ClientPacket.field2924, Client.packetWriter.isaacCipher);
		var2.packetBuffer.writeByte(ItemLayer.stringCp1252NullTerminatedByteSize(var0) + 1);
		var2.packetBuffer.writeStringCp1252NullTerminated(var0);
		var2.packetBuffer.method7787(var1);
		Client.packetWriter.addNode(var2);
	}

	static final void updateNpcs(boolean var0, PacketBuffer var1) {
		Client.field634 = 0;
		Client.field609 = 0;
		class385.method7028();
		SecureRandomCallable.method2066(var0, var1);

		int var2;
		int var3;
		for (var2 = 0; var2 < Client.field609; ++var2) {
			var3 = Client.field533[var2];
			NPC var4 = Client.npcs[var3];
			int var5 = var1.readUnsignedByte();
			int var6;
			if (class162.field1768 && (var5 & 64) != 0) {
				var6 = var1.readUnsignedByte();
				var5 += var6 << 8;
			}

			int var7;
			int var8;
			int var9;
			if ((var5 & 1) != 0) {
				var6 = var1.readUnsignedShort();
				var7 = var1.readUnsignedShortLEAdd();
				if (class162.field1768) {
					var4.field1183 = var1.method7789() == 1;
				}

				var8 = var4.x - (var6 - ApproximateRouteStrategy.baseX - ApproximateRouteStrategy.baseX) * 64;
				var9 = var4.y - (var7 - class250.baseY - class250.baseY) * 64;
				if (var8 != 0 || var9 != 0) {
					var4.field1160 = (int)(Math.atan2(var8, var9) * 325.949D) & 2047;
				}
			}

			if ((var5 & 1024) != 0) {
				var4.field1161 = var1.readInt();
			}

			if ((var5 & 16) != 0) {
				var4.spotAnimation = var1.readUnsignedShort();
				var6 = var1.readInt();
				var4.spotAnimationHeight = var6 >> 16;
				var4.field1173 = (var6 & 65535) + Client.cycle;
				var4.spotAnimationFrame = 0;
				var4.spotAnimationFrameCycle = 0;
				if (var4.field1173 > Client.cycle) {
					var4.spotAnimationFrame = -1;
				}

				if (var4.spotAnimation == 65535) {
					var4.spotAnimation = -1;
				}
			}

			if ((var5 & 128) != 0) {
				var6 = var1.readUnsignedByte();
				int var10;
				int var11;
				int var12;
				if (var6 > 0) {
					for (var7 = 0; var7 < var6; ++var7) {
						var9 = -1;
						var10 = -1;
						var11 = -1;
						var8 = var1.readUShortSmart();
						if (var8 == 32767) {
							var8 = var1.readUShortSmart();
							var10 = var1.readUShortSmart();
							var9 = var1.readUShortSmart();
							var11 = var1.readUShortSmart();
						} else if (var8 != 32766) {
							var10 = var1.readUShortSmart();
						} else {
							var8 = -1;
						}

						var12 = var1.readUShortSmart();
						var4.addHitSplat(var8, var10, var9, var11, Client.cycle, var12);
					}
				}

				var7 = var1.readUnsignedByteNEG();
				if (var7 > 0) {
					for (var8 = 0; var8 < var7; ++var8) {
						var9 = var1.readUShortSmart();
						var10 = var1.readUShortSmart();
						if (var10 != 32767) {
							var11 = var1.readUShortSmart();
							var12 = var1.readUnsignedByte();
							int var13 = var10 > 0 ? var1.method7789() : var12;
							var4.addHealthBar(var9, Client.cycle, var10, var11, var12, var13);
						} else {
							var4.removeHealthBar(var9);
						}
					}
				}
			}

			if (class162.field1768 && (var5 & 256) != 0 || !class162.field1768 && (var5 & 64) != 0) {
                var4.field1175 = var1.readByteSUB();
                var4.field1177 = var1.readByteADD();
                var4.field1176 = var1.readByte();
                var4.field1178 = var1.readByte();
                var4.field1179 = var1.readUnsignedShortLEAdd() + Client.cycle;
                var4.field1180 = var1.readUnsignedShort() + Client.cycle;
                var4.field1181 = var1.readUnsignedShortLEAdd();
                var4.pathLength = 1;
                var4.field1134 = 0;
                var4.field1175 += var4.pathX[0];
                var4.field1177 += var4.pathY[0];
                var4.field1176 += var4.pathX[0];
				var4.field1178 += var4.pathY[0];
			}

			if ((var5 & 512) != 0) {
                var4.field1133 = Client.cycle + var1.readUnsignedShortLE();
                var4.field1185 = Client.cycle + var1.readUnsignedShortAdd();
                var4.field1146 = var1.readByte();
                var4.field1187 = var1.readByteADD();
                var4.field1188 = var1.readByteADD();
                var4.field1189 = (byte) var1.readUnsignedByteSUB();
            }

			if ((var5 & 2) != 0) {
				var4.overheadText = var1.readStringCp1252NullTerminated();
				var4.overheadTextCyclesRemaining = 100;
			}

			if ((var5 & 4) != 0) {
				var4.targetIndex = var1.readUnsignedShortAdd();
				if (var4.targetIndex == 65535) {
					var4.targetIndex = -1;
				}
			}

			if ((var5 & 32) != 0) {
				var4.definition = class9.getNpcDefinition(var1.readUnsignedShortLEAdd());
				var4.field1145 = var4.definition.size;
				var4.field1192 = var4.definition.rotation;
				var4.walkSequence = var4.definition.walkSequence;
				var4.walkBackSequence = var4.definition.walkBackSequence;
				var4.walkLeftSequence = var4.definition.walkLeftSequence;
				var4.walkRightSequence = var4.definition.walkRightSequence;
				var4.idleSequence = var4.definition.idleSequence;
				var4.turnLeftSequence = var4.definition.turnLeftSequence;
				var4.turnRightSequence = var4.definition.turnRightSequence;
			}

			if ((var5 & 8) != 0) {
				var6 = var1.readUnsignedShortLE();
				if (var6 == 65535) {
					var6 = -1;
				}

				var7 = var1.method7789();
				if (var6 == var4.sequence && var6 != -1) {
					var8 = ItemContainer.SequenceDefinition_get(var6).field2172;
					if (var8 == 1) {
						var4.sequenceFrame = 0;
						var4.sequenceFrameCycle = 0;
						var4.sequenceDelay = var7;
						var4.field1169 = 0;
					}

					if (var8 == 2) {
						var4.field1169 = 0;
					}
				} else if (var6 == -1 || var4.sequence == -1 || ItemContainer.SequenceDefinition_get(var6).field2166 >= ItemContainer.SequenceDefinition_get(var4.sequence).field2166) {
					var4.sequence = var6;
					var4.sequenceFrame = 0;
					var4.sequenceFrameCycle = 0;
					var4.sequenceDelay = var7;
					var4.field1169 = 0;
					var4.field1134 = var4.pathLength;
				}
			}
		}

		for (var2 = 0; var2 < Client.field634; ++var2) {
			var3 = Client.field612[var2];
			if (Client.npcs[var3].npcCycle != Client.cycle) {
				Client.npcs[var3].definition = null;
				Client.npcs[var3] = null;
			}
		}

		if (var1.offset != Client.packetWriter.serverPacketLength) {
			throw new RuntimeException(var1.offset + "," + Client.packetWriter.serverPacketLength);
		} else {
			for (var2 = 0; var2 < Client.npcCount; ++var2) {
				if (Client.npcs[Client.npcIndices[var2]] == null) {
					throw new RuntimeException(var2 + "," + Client.npcCount);
				}
			}

		}
	}

	static final void insertMenuItem(String var0, String var1, int var2, int var3, int var4, int var5, boolean var6) {
		if (!Client.isMenuOpen) {
			if (Client.menuOptionsCount < 500) {
				Client.menuActions[Client.menuOptionsCount] = var0;
				Client.menuTargets[Client.menuOptionsCount] = var1;
				Client.menuOpcodes[Client.menuOptionsCount] = var2;
				Client.menuIdentifiers[Client.menuOptionsCount] = var3;
				Client.menuArguments1[Client.menuOptionsCount] = var4;
				Client.menuArguments2[Client.menuOptionsCount] = var5;
				Client.menuShiftClick[Client.menuOptionsCount] = var6;
				++Client.menuOptionsCount;
			}

		}
	}

	static final void method1982(int var0, int var1, int var2, boolean var3) {
		if (MusicPatchNode2.loadInterface(var0)) {
			class65.resizeInterface(EnumComposition.Widget_interfaceComponents[var0], -1, var1, var2, var3);
		}
	}
}
