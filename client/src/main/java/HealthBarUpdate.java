public class HealthBarUpdate extends Node {
	int cycle;
	int health;
	int health2;
	int cycleOffset;

	HealthBarUpdate(int var1, int var2, int var3, int var4) {
		this.cycle = var1;
		this.health = var2;
		this.health2 = var3;
		this.cycleOffset = var4;
	}

	void set(int var1, int var2, int var3, int var4) {
		this.cycle = var1;
		this.health = var2;
		this.health2 = var3;
		this.cycleOffset = var4;
	}

	public static void method2217(AbstractArchive var0) {
		FloorUnderlayDefinition.FloorUnderlayDefinition_archive = var0;
	}

	static int ItemContainer_getCount(int var0, int var1) {
		ItemContainer var2 = (ItemContainer)ItemContainer.itemContainers.get(var0);
		if (var2 == null) {
			return 0;
		} else {
			return var1 >= 0 && var1 < var2.quantities.length ? var2.quantities[var1] : 0;
		}
	}

	static final void decodePlayerUpdateFlag(PacketBuffer buf, int var1, Player player, int mask) {
		byte var4 = class193.field2192.field2194;
		// 0
		if ((mask & 8192) != 0) {
			player.field1133 = Client.cycle + buf.readUnsignedShortAdd();
			player.field1185 = Client.cycle + buf.readUnsignedShortLE();
			player.field1146 = buf.method7955();
			player.field1187 = buf.method7955();
			player.field1188 = buf.method7925();
			player.field1189 = (byte) buf.method7790();
		}

		// 1
		if ((mask & 256) != 0) {
			var4 = buf.method7925();
		}

		int dataLength;
		int var8;
		int var9;
		int var12;

		// 2
		if ((mask & 1) != 0) {
			dataLength = buf.readUnsignedShortLE();
			PlayerType var6 = (PlayerType) ChatChannel.findEnumerated(HitSplatDefinition.PlayerType_values(), buf.readUnsignedByte());
			boolean var7 = buf.readUnsignedByteSub() == 1;
			var8 = buf.readUnsignedByteSub();
			var9 = buf.offset;
			if (player.username != null && player.appearance != null) {
				boolean var10 = var6.isUser && class155.friendSystem.isIgnored(player.username);

				if (!var10 && Client.field603 == 0 && !player.isHidden) {
					Players.field1299.offset = 0;
					buf.readBytesReversed(Players.field1299.array, 0, var8);
					Players.field1299.offset = 0;
					String var11 = AbstractFont.escapeBrackets(AbstractByteArrayCopier.method5528(class118.method2737(Players.field1299)));
					player.overheadText = var11.trim();
					player.overheadTextColor = dataLength >> 8;
					player.overheadTextEffect = dataLength & 255;
					player.overheadTextCyclesRemaining = 150;
					player.isAutoChatting = var7;
					player.field1152 = player != class19.localPlayer && var6.isUser && "" != Client.field712 && var11.toLowerCase().indexOf(Client.field712) == -1;
					if (var6.isPrivileged) {
						var12 = var7 ? 91 : 1;
					} else {
						var12 = var7 ? 90 : 2;
					}

					if (var6.modIcon != -1) {
						Login.addGameMessage(var12, class351.method6579(var6.modIcon) + player.username.getName(), var11);
					} else {
						Login.addGameMessage(var12, player.username.getName(), var11);
					}
				}
			}

			buf.offset = var8 + var9;
		}

		// 3
		if ((mask & 2048) != 0) {
			for (dataLength = 0; dataLength < 3; ++dataLength) {
				player.actions[dataLength] = buf.readStringCp1252NullTerminated();
			}
		}

		// 4
		if ((mask & 4096) != 0) {
			player.spotAnimation = buf.readUnsignedShortLE();
			dataLength = buf.readIntLE();
			player.spotAnimationHeight = dataLength >> 16;
			player.field1173 = (dataLength & 65535) + Client.cycle;
			player.spotAnimationFrame = 0;
			player.spotAnimationFrameCycle = 0;
			if (player.field1173 > Client.cycle) {
				player.spotAnimationFrame = -1;
			}

			if (player.spotAnimation == 65535) {
				player.spotAnimation = -1;
			}
		}

		// Appearance : 5 - 0x80
		if ((mask & 128) != 0) {
			dataLength = buf.readUnsignedByteSub();
			byte[] data = new byte[dataLength];
			Buffer appearanceBuf = new Buffer(data);
			buf.readBytesReversed(data, 0, dataLength);
			Players.cached_appearances[var1] = appearanceBuf;
			player.read(appearanceBuf);
		}

		if ((mask & 512) != 0) {
			player.field1175 = buf.method7792();
			player.field1177 = buf.readByte();
			player.field1176 = buf.method7925();
			player.field1178 = buf.method7955();
			player.field1179 = buf.readUnsignedShortAdd() + Client.cycle;
			player.field1180 = buf.readUnsignedShortLEAdd() + Client.cycle;
			player.field1181 = buf.readUnsignedShortLE();
			if (player.field1109) {
				player.field1175 += player.tileX;
				player.field1177 += player.tileY;
				player.field1176 += player.tileX;
				player.field1178 += player.tileY;
				player.pathLength = 0;
			} else {
				player.field1175 += player.pathX[0];
				player.field1177 += player.pathY[0];
				player.field1176 += player.pathX[0];
				player.field1178 += player.pathY[0];
				player.pathLength = 1;
			}

			player.field1134 = 0;
		}

		if ((mask & 16) != 0) {
			player.field1160 = buf.readUnsignedShort();
			if (player.pathLength == 0) {
				player.orientation = player.field1160;
				player.field1160 = -1;
			}
		}

		if ((mask & 2) != 0) {
			player.overheadText = buf.readStringCp1252NullTerminated();
			if (player.overheadText.charAt(0) == '~') {
				player.overheadText = player.overheadText.substring(1);
				Login.addGameMessage(2, player.username.getName(), player.overheadText);
			} else if (player == class19.localPlayer) {
				Login.addGameMessage(2, player.username.getName(), player.overheadText);
			}

			player.isAutoChatting = false;
			player.overheadTextColor = 0;
			player.overheadTextEffect = 0;
			player.overheadTextCyclesRemaining = 150;
		}

		int var14;
		if ((mask & 8) != 0) {
			dataLength = buf.readUnsignedShortLEAdd();
			if (dataLength == 65535) {
				dataLength = -1;
			}

			var14 = buf.readUnsignedByteSub();
			KeyHandler.performPlayerAnimation(player, dataLength, var14);
		}

		if ((mask & 16384) != 0) {
			Players.field1285[var1] = (class193) ChatChannel.findEnumerated(class124.method2801(), buf.method7955());
		}

		if ((mask & 32) != 0) {
			player.targetIndex = buf.readUnsignedShortLE();
			if (player.targetIndex == 65535) {
				player.targetIndex = -1;
			}
		}

		if ((mask & 4) != 0) {
			dataLength = buf.method7790();
			int var16;
			int var18;
			int var19;
			if (dataLength > 0) {
				for (var14 = 0; var14 < dataLength; ++var14) {
					var8 = -1;
					var9 = -1;
					var19 = -1;
					var18 = buf.readUShortSmart();
					if (var18 == 32767) {
						var18 = buf.readUShortSmart();
						var9 = buf.readUShortSmart();
						var8 = buf.readUShortSmart();
						var19 = buf.readUShortSmart();
					} else if (var18 != 32766) {
						var9 = buf.readUShortSmart();
					} else {
						var18 = -1;
					}

					var16 = buf.readUShortSmart();
					player.addHitSplat(var18, var9, var8, var19, Client.cycle, var16);
				}
			}

			var14 = buf.readUnsignedByte();
			if (var14 > 0) {
				for (var18 = 0; var18 < var14; ++var18) {
					var8 = buf.readUShortSmart();
					var9 = buf.readUShortSmart();
					if (var9 != 32767) {
						var19 = buf.readUShortSmart();
						var16 = buf.readUnsignedByte();
						var12 = var9 > 0 ? buf.method7790() : var16;
						player.addHealthBar(var8, Client.cycle, var9, var19, var16, var12);
					} else {
						player.removeHealthBar(var8);
					}
				}
			}
		}

		if (player.field1109) {
			if (var4 == 127) {
				player.resetPath(player.tileX, player.tileY);
			} else {
				class193 var15;
				if (var4 != class193.field2192.field2194) {
					var15 = (class193) ChatChannel.findEnumerated(class124.method2801(), var4);
				} else {
					var15 = Players.field1285[var1];
				}

				player.method2144(player.tileX, player.tileY, var15);
			}
		}

	}

	static void method2210(int var0, int var1, int var2) {
		if (var0 != 0) {
			int var3 = var0 >> 8;
			int var4 = var0 >> 4 & 7;
			int var5 = var0 & 15;
			Client.soundEffectIds[Client.soundEffectCount] = var3;
			Client.queuedSoundEffectLoops[Client.soundEffectCount] = var4;
			Client.queuedSoundEffectDelays[Client.soundEffectCount] = 0;
			Client.soundEffects[Client.soundEffectCount] = null;
			int var6 = (var1 - 64) / 128;
			int var7 = (var2 - 64) / 128;
			Client.soundLocations[Client.soundEffectCount] = var5 + (var7 << 8) + (var6 << 16);
			++Client.soundEffectCount;
		}
	}
}
