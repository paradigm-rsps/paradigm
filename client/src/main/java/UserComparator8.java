import rs.ScriptOpcodes;

public class UserComparator8 extends AbstractUserComparator {
	static IndexedSprite[] modIconSprites;
	final boolean reversed;

	public UserComparator8(boolean var1) {
		this.reversed = var1;
	}

	int compareBuddy(Buddy var1, Buddy var2) {
		if (Client.worldId == var1.world) {
			if (var2.world != Client.worldId) {
				return this.reversed ? -1 : 1;
			}
		} else if (var2.world == Client.worldId) {
			return this.reversed ? 1 : -1;
		}

		return this.compareUser(var1, var2);
	}

	public int compare(Object var1, Object var2) {
		return this.compareBuddy((Buddy) var1, (Buddy) var2);
	}

	static final void updatePlayers(PacketBuffer buf) {
		int skipCount = 0;
		buf.toBitMode();

		byte[] skipFlags;
		int i;
		int shouldUpdate;
		int playerIndex;
		for (i = 0; i < Players.localPlayerCount; ++i) {
			playerIndex = Players.localPlayerIndexes[i];
			if ((Players.skipFlags[playerIndex] & 1) == 0) {
				if (skipCount > 0) {
					--skipCount;
					skipFlags = Players.skipFlags;
					skipFlags[playerIndex] = (byte) (skipFlags[playerIndex] | 2);
				} else {
					shouldUpdate = buf.readBits(1);
					if (shouldUpdate == 0) {
						skipCount = NPCComposition.readSkipCount(buf);
						skipFlags = Players.skipFlags;
						skipFlags[playerIndex] = (byte) (skipFlags[playerIndex] | 2);
					} else {
						WorldMapLabelSize.updateLocalPlayer(buf, playerIndex);
					}
				}
			}
		}

		buf.toByteMode();
		if (skipCount != 0) {
			throw new RuntimeException();
		} else {
			buf.toBitMode();

			for (i = 0; i < Players.localPlayerCount; ++i) {
				playerIndex = Players.localPlayerIndexes[i];
				if ((Players.skipFlags[playerIndex] & 1) != 0) {
					if (skipCount > 0) {
						--skipCount;
						skipFlags = Players.skipFlags;
						skipFlags[playerIndex] = (byte) (skipFlags[playerIndex] | 2);
					} else {
						shouldUpdate = buf.readBits(1);
						if (shouldUpdate == 0) {
							skipCount = NPCComposition.readSkipCount(buf);
							skipFlags = Players.skipFlags;
							skipFlags[playerIndex] = (byte) (skipFlags[playerIndex] | 2);
						} else {
							WorldMapLabelSize.updateLocalPlayer(buf, playerIndex);
						}
					}
				}
			}

			buf.toByteMode();
			if (skipCount != 0) {
				throw new RuntimeException();
			} else {
				buf.toBitMode();

				for (i = 0; i < Players.externalPlayerCount; ++i) {
					playerIndex = Players.externalPlayerIndexes[i];
					if ((Players.skipFlags[playerIndex] & 1) != 0) {
						if (skipCount > 0) {
							--skipCount;
							skipFlags = Players.skipFlags;
							skipFlags[playerIndex] = (byte) (skipFlags[playerIndex] | 2);
						} else {
							shouldUpdate = buf.readBits(1);
							if (shouldUpdate == 0) {
								skipCount = NPCComposition.readSkipCount(buf);
								skipFlags = Players.skipFlags;
								skipFlags[playerIndex] = (byte) (skipFlags[playerIndex] | 2);
							} else if (class9.updateExternalPlayer(buf, playerIndex)) {
								skipFlags = Players.skipFlags;
								skipFlags[playerIndex] = (byte) (skipFlags[playerIndex] | 2);
							}
						}
					}
				}

				buf.toByteMode();
				if (skipCount != 0) {
					throw new RuntimeException();
				} else {
					buf.toBitMode();

					for (i = 0; i < Players.externalPlayerCount; ++i) {
						playerIndex = Players.externalPlayerIndexes[i];
						if ((Players.skipFlags[playerIndex] & 1) == 0) {
							if (skipCount > 0) {
								--skipCount;
								skipFlags = Players.skipFlags;
								skipFlags[playerIndex] = (byte) (skipFlags[playerIndex] | 2);
							} else {
								shouldUpdate = buf.readBits(1);
								if (shouldUpdate == 0) {
									skipCount = NPCComposition.readSkipCount(buf);
									skipFlags = Players.skipFlags;
									skipFlags[playerIndex] = (byte) (skipFlags[playerIndex] | 2);
								} else if (class9.updateExternalPlayer(buf, playerIndex)) {
									skipFlags = Players.skipFlags;
									skipFlags[playerIndex] = (byte) (skipFlags[playerIndex] | 2);
								}
							}
						}
					}

					buf.toByteMode();
					if (skipCount != 0) {
						throw new RuntimeException();
					} else {
						Players.localPlayerCount = 0;
						Players.externalPlayerCount = 0;

						for (i = 1; i < 2048; ++i) {
							skipFlags = Players.skipFlags;
							skipFlags[i] = (byte) (skipFlags[i] >> 1);
							Player var3 = Client.players[i];
							if (var3 != null) {
								Players.localPlayerIndexes[++Players.localPlayerCount - 1] = i;
							} else {
								Players.externalPlayerIndexes[++Players.externalPlayerCount - 1] = i;
							}
						}

					}
				}
			}
		}
	}

	static int method2569(int var0, Script var1, boolean var2) {
		int var3;
		if (var0 == ScriptOpcodes.OC_NAME) {
			var3 = Interpreter.Interpreter_intStack[--class295.Interpreter_intStackSize];
			Interpreter.Interpreter_stringStack[++ChatChannel.Interpreter_stringStackSize - 1] = class67.ItemDefinition_get(var3).name;
			return 1;
		} else {
			int var4;
			ItemComposition var5;
			if (var0 == ScriptOpcodes.OC_OP) {
				class295.Interpreter_intStackSize -= 2;
				var3 = Interpreter.Interpreter_intStack[class295.Interpreter_intStackSize];
				var4 = Interpreter.Interpreter_intStack[class295.Interpreter_intStackSize + 1];
				var5 = class67.ItemDefinition_get(var3);
				if (var4 >= 1 && var4 <= 5 && var5.groundActions[var4 - 1] != null) {
					Interpreter.Interpreter_stringStack[++ChatChannel.Interpreter_stringStackSize - 1] = var5.groundActions[var4 - 1];
				} else {
					Interpreter.Interpreter_stringStack[++ChatChannel.Interpreter_stringStackSize - 1] = "";
				}

				return 1;
			} else if (var0 == ScriptOpcodes.OC_IOP) {
				class295.Interpreter_intStackSize -= 2;
				var3 = Interpreter.Interpreter_intStack[class295.Interpreter_intStackSize];
				var4 = Interpreter.Interpreter_intStack[class295.Interpreter_intStackSize + 1];
				var5 = class67.ItemDefinition_get(var3);
				if (var4 >= 1 && var4 <= 5 && var5.inventoryActions[var4 - 1] != null) {
					Interpreter.Interpreter_stringStack[++ChatChannel.Interpreter_stringStackSize - 1] = var5.inventoryActions[var4 - 1];
				} else {
					Interpreter.Interpreter_stringStack[++ChatChannel.Interpreter_stringStackSize - 1] = "";
				}

				return 1;
			} else if (var0 == ScriptOpcodes.OC_COST) {
				var3 = Interpreter.Interpreter_intStack[--class295.Interpreter_intStackSize];
				Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = class67.ItemDefinition_get(var3).price;
				return 1;
			} else if (var0 == ScriptOpcodes.OC_STACKABLE) {
				var3 = Interpreter.Interpreter_intStack[--class295.Interpreter_intStackSize];
				Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = class67.ItemDefinition_get(var3).isStackable == 1 ? 1 : 0;
				return 1;
			} else {
				ItemComposition var7;
				if (var0 == ScriptOpcodes.OC_CERT) {
					var3 = Interpreter.Interpreter_intStack[--class295.Interpreter_intStackSize];
					var7 = class67.ItemDefinition_get(var3);
					if (var7.noteTemplate == -1 && var7.note >= 0) {
						Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = var7.note;
					} else {
						Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = var3;
					}

					return 1;
				} else if (var0 == ScriptOpcodes.OC_UNCERT) {
					var3 = Interpreter.Interpreter_intStack[--class295.Interpreter_intStackSize];
					var7 = class67.ItemDefinition_get(var3);
					if (var7.noteTemplate >= 0 && var7.note >= 0) {
						Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = var7.note;
					} else {
						Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = var3;
					}

					return 1;
				} else if (var0 == ScriptOpcodes.OC_MEMBERS) {
					var3 = Interpreter.Interpreter_intStack[--class295.Interpreter_intStackSize];
					Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = class67.ItemDefinition_get(var3).isMembersOnly ? 1 : 0;
					return 1;
				} else if (var0 == ScriptOpcodes.OC_PLACEHOLDER) {
					var3 = Interpreter.Interpreter_intStack[--class295.Interpreter_intStackSize];
					var7 = class67.ItemDefinition_get(var3);
					if (var7.placeholderTemplate == -1 && var7.placeholder >= 0) {
						Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = var7.placeholder;
					} else {
						Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = var3;
					}

					return 1;
				} else if (var0 == ScriptOpcodes.OC_UNPLACEHOLDER) {
					var3 = Interpreter.Interpreter_intStack[--class295.Interpreter_intStackSize];
					var7 = class67.ItemDefinition_get(var3);
					if (var7.placeholderTemplate >= 0 && var7.placeholder >= 0) {
						Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = var7.placeholder;
					} else {
						Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = var3;
					}

					return 1;
				} else if (var0 == ScriptOpcodes.OC_FIND) {
					String var6 = Interpreter.Interpreter_stringStack[--ChatChannel.Interpreter_stringStackSize];
					var4 = Interpreter.Interpreter_intStack[--class295.Interpreter_intStackSize];
					class13.findItemDefinitions(var6, var4 == 1);
					Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = PendingSpawn.foundItemIdCount;
					return 1;
				} else if (var0 != ScriptOpcodes.OC_FINDNEXT) {
					if (var0 == ScriptOpcodes.OC_FINDRESET) {
						MouseRecorder.foundItemIndex = 0;
						return 1;
					} else if (var0 == 4213) {
						var3 = Interpreter.Interpreter_intStack[--class295.Interpreter_intStackSize];
						var4 = class67.ItemDefinition_get(var3).getShiftClickIndex();
						if (var4 == -1) {
							Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = var4;
						} else {
							Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = var4 + 1;
						}

						return 1;
					} else {
						return 2;
					}
				} else {
					if (class14.foundItemIds != null && MouseRecorder.foundItemIndex < PendingSpawn.foundItemIdCount) {
						Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = class14.foundItemIds[++MouseRecorder.foundItemIndex - 1] & '\uffff';
					} else {
						Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = -1;
					}

					return 1;
				}
			}
		}
	}

	static void method2576(int var0, int var1) {
		ScriptFrame.method1083(PacketWriter.tempMenuAction, var0, var1);
		PacketWriter.tempMenuAction = null;
	}
}
