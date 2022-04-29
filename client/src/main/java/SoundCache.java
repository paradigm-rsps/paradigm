import rs.ScriptOpcodes;

public class SoundCache {
	static int selectedItemId;
	AbstractArchive soundEffectIndex;
	AbstractArchive musicSampleIndex;
	NodeHashTable musicSamples;
	NodeHashTable rawSounds;

	public SoundCache(AbstractArchive var1, AbstractArchive var2) {
		this.musicSamples = new NodeHashTable(256);
		this.rawSounds = new NodeHashTable(256);
		this.soundEffectIndex = var1;
		this.musicSampleIndex = var2;
	}

	RawSound getSoundEffect0(int var1, int var2, int[] var3) {
		int var4 = var2 ^ (var1 << 4 & 65535 | var1 >>> 12);
		var4 |= var1 << 16;
		long var5 = var4;
		RawSound var7 = (RawSound)this.rawSounds.get(var5);
		if (var7 != null) {
			return var7;
		} else if (var3 != null && var3[0] <= 0) {
			return null;
		} else {
			SoundEffect var8 = SoundEffect.readSoundEffect(this.soundEffectIndex, var1, var2);
			if (var8 == null) {
				return null;
			} else {
				var7 = var8.toRawSound();
				this.rawSounds.put(var7, var5);
				if (var3 != null) {
					var3[0] -= var7.samples.length;
				}

				return var7;
			}
		}
	}

	RawSound getMusicSample0(int var1, int var2, int[] var3) {
		int var4 = var2 ^ (var1 << 4 & 65535 | var1 >>> 12);
		var4 |= var1 << 16;
		long var5 = (long)var4 ^ 4294967296L;
		RawSound var7 = (RawSound)this.rawSounds.get(var5);
		if (var7 != null) {
			return var7;
		} else if (var3 != null && var3[0] <= 0) {
			return null;
		} else {
			VorbisSample var8 = (VorbisSample)this.musicSamples.get(var5);
			if (var8 == null) {
				var8 = VorbisSample.readMusicSample(this.musicSampleIndex, var1, var2);
				if (var8 == null) {
					return null;
				}

				this.musicSamples.put(var8, var5);
			}

			var7 = var8.toRawSound(var3);
			if (var7 == null) {
				return null;
			} else {
				var8.remove();
				this.rawSounds.put(var7, var5);
				return var7;
			}
		}
	}

	public RawSound getSoundEffect(int var1, int[] var2) {
		if (this.soundEffectIndex.getGroupCount() == 1) {
			return this.getSoundEffect0(0, var1, var2);
		} else if (this.soundEffectIndex.getGroupFileCount(var1) == 1) {
			return this.getSoundEffect0(var1, 0, var2);
		} else {
			throw new RuntimeException();
		}
	}

	public RawSound getMusicSample(int var1, int[] var2) {
		if (this.musicSampleIndex.getGroupCount() == 1) {
			return this.getMusicSample0(0, var1, var2);
		} else if (this.musicSampleIndex.getGroupFileCount(var1) == 1) {
			return this.getMusicSample0(var1, 0, var2);
		} else {
			throw new RuntimeException();
		}
	}

	public static VarpDefinition VarpDefinition_get(int var0) {
		VarpDefinition var1 = (VarpDefinition)VarpDefinition.VarpDefinition_cached.get(var0);
		if (var1 != null) {
			return var1;
		} else {
			byte[] var2 = VarpDefinition.VarpDefinition_archive.takeFile(16, var0);
			var1 = new VarpDefinition();
			if (var2 != null) {
				var1.decode(new Buffer(var2));
			}

			VarpDefinition.VarpDefinition_cached.put(var1, var0);
			return var1;
		}
	}

	static int method803(int var0, Script var1, boolean var2) {
		Widget var3 = HorizontalAlignment.getWidget(Interpreter.Interpreter_intStack[--class295.Interpreter_intStackSize]);
		if (var0 == ScriptOpcodes.IF_GETX) {
			Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = var3.x;
			return 1;
		} else if (var0 == ScriptOpcodes.IF_GETY) {
			Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = var3.y;
			return 1;
		} else if (var0 == ScriptOpcodes.IF_GETWIDTH) {
			Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = var3.width;
			return 1;
		} else if (var0 == ScriptOpcodes.IF_GETHEIGHT) {
			Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = var3.height;
			return 1;
		} else if (var0 == ScriptOpcodes.IF_GETHIDE) {
            Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = var3.isHidden ? 1 : 0;
            return 1;
        } else if (var0 == ScriptOpcodes.IF_GETLAYER) {
            Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = var3.parentId;
            return 1;
        } else {
            return 2;
        }
    }

    static final void menuAction(int x, int y, int actionOpcode, int menuIndex, String var4, String var5, int mouseX, int mouseY) {
        if (actionOpcode >= 2000) {
            actionOpcode -= 2000;
        }

        PacketBufferNode var8;
        if (actionOpcode == 1) {
            Client.mouseCrossX = mouseX;
            Client.mouseCrossY = mouseY;
            Client.mouseCrossColor = 2;
            Client.mouseCrossState = 0;
            Client.destinationX = x;
            Client.destinationY = y;
            var8 = ItemContainer.getPacketBufferNode(ClientPacket.field2994, Client.packetWriter.isaacCipher);
            var8.packetBuffer.writeShortLE(class19.selectedItemSlot);
            var8.packetBuffer.writeShortLE(selectedItemId);
            var8.packetBuffer.method7786(KeyHandler.KeyHandler_pressedKeys[82] ? 1 : 0);
            var8.packetBuffer.writeShort(x + ApproximateRouteStrategy.baseX);
            var8.packetBuffer.writeShort(menuIndex);
            var8.packetBuffer.method7746(PendingSpawn.selectedItemWidget);
            var8.packetBuffer.writeShortADD(class250.baseY + y);
            Client.packetWriter.addNode(var8);
        } else if (actionOpcode == 2) {
            Client.mouseCrossX = mouseX;
            Client.mouseCrossY = mouseY;
            Client.mouseCrossColor = 2;
            Client.mouseCrossState = 0;
            Client.destinationX = x;
            Client.destinationY = y;
            var8 = ItemContainer.getPacketBufferNode(ClientPacket.field2894, Client.packetWriter.isaacCipher);
            var8.packetBuffer.writeShortADD(Client.field688);
            var8.packetBuffer.writeByte(KeyHandler.KeyHandler_pressedKeys[82] ? 1 : 0);
            var8.packetBuffer.writeShortLEADD(class250.baseY + y);
            var8.packetBuffer.writeShortADD(Client.selectedSpellChildIndex);
            var8.packetBuffer.writeShortADD(x + ApproximateRouteStrategy.baseX);
            var8.packetBuffer.method7746(class92.selectedSpellWidget);
            var8.packetBuffer.writeShortLE(menuIndex);
            Client.packetWriter.addNode(var8);
        } else if (actionOpcode == 3) {
            Client.mouseCrossX = mouseX;
            Client.mouseCrossY = mouseY;
            Client.mouseCrossColor = 2;
            Client.mouseCrossState = 0;
            Client.destinationX = x;
            Client.destinationY = y;
            var8 = ItemContainer.getPacketBufferNode(ClientPacket.field2978, Client.packetWriter.isaacCipher);
            var8.packetBuffer.writeShortADD(class250.baseY + y);
            var8.packetBuffer.writeShortLE(x + ApproximateRouteStrategy.baseX);
            var8.packetBuffer.writeShort(menuIndex);
            var8.packetBuffer.method7787(KeyHandler.KeyHandler_pressedKeys[82] ? 1 : 0);
            Client.packetWriter.addNode(var8);
        } else if (actionOpcode == 4) {
            Client.mouseCrossX = mouseX;
            Client.mouseCrossY = mouseY;
            Client.mouseCrossColor = 2;
            Client.mouseCrossState = 0;
            Client.destinationX = x;
            Client.destinationY = y;
            var8 = ItemContainer.getPacketBufferNode(ClientPacket.field2901, Client.packetWriter.isaacCipher);
            var8.packetBuffer.writeByteSUB(KeyHandler.KeyHandler_pressedKeys[82] ? 1 : 0);
            var8.packetBuffer.writeShort(class250.baseY + y);
            var8.packetBuffer.writeShortLEADD(menuIndex);
            var8.packetBuffer.writeShortLE(x + ApproximateRouteStrategy.baseX);
            Client.packetWriter.addNode(var8);
        } else if (actionOpcode == 5) {
            Client.mouseCrossX = mouseX;
            Client.mouseCrossY = mouseY;
            Client.mouseCrossColor = 2;
            Client.mouseCrossState = 0;
            Client.destinationX = x;
            Client.destinationY = y;
            var8 = ItemContainer.getPacketBufferNode(ClientPacket.field2927, Client.packetWriter.isaacCipher);
            var8.packetBuffer.method7787(KeyHandler.KeyHandler_pressedKeys[82] ? 1 : 0);
            var8.packetBuffer.writeShortADD(x + ApproximateRouteStrategy.baseX);
            var8.packetBuffer.writeShort(class250.baseY + y);
            var8.packetBuffer.writeShortADD(menuIndex);
            Client.packetWriter.addNode(var8);
        } else if (actionOpcode == 6) {
            Client.mouseCrossX = mouseX;
            Client.mouseCrossY = mouseY;
            Client.mouseCrossColor = 2;
            Client.mouseCrossState = 0;
            Client.destinationX = x;
            Client.destinationY = y;
            var8 = ItemContainer.getPacketBufferNode(ClientPacket.field2977, Client.packetWriter.isaacCipher);
            var8.packetBuffer.method7786(KeyHandler.KeyHandler_pressedKeys[82] ? 1 : 0);
            var8.packetBuffer.writeShortLEADD(x + ApproximateRouteStrategy.baseX);
            var8.packetBuffer.writeShortADD(menuIndex);
            var8.packetBuffer.writeShortADD(class250.baseY + y);
            Client.packetWriter.addNode(var8);
        } else {
            PacketBufferNode var9;
            NPC var13;
            if (actionOpcode == 7) {
                var13 = Client.npcs[menuIndex];
                if (var13 != null) {
                    Client.mouseCrossX = mouseX;
                    Client.mouseCrossY = mouseY;
                    Client.mouseCrossColor = 2;
                    Client.mouseCrossState = 0;
                    Client.destinationX = x;
                    Client.destinationY = y;
                    var9 = ItemContainer.getPacketBufferNode(ClientPacket.field2947, Client.packetWriter.isaacCipher);
                    var9.packetBuffer.writeShortLE(menuIndex);
                    var9.packetBuffer.writeIntME(PendingSpawn.selectedItemWidget);
                    var9.packetBuffer.writeShortLEADD(selectedItemId);
                    var9.packetBuffer.writeByteSUB(KeyHandler.KeyHandler_pressedKeys[82] ? 1 : 0);
                    var9.packetBuffer.writeShortADD(class19.selectedItemSlot);
                    Client.packetWriter.addNode(var9);
                }
            } else if (actionOpcode == 8) {
                var13 = Client.npcs[menuIndex];
                if (var13 != null) {
                    Client.mouseCrossX = mouseX;
                    Client.mouseCrossY = mouseY;
                    Client.mouseCrossColor = 2;
                    Client.mouseCrossState = 0;
                    Client.destinationX = x;
                    Client.destinationY = y;
                    var9 = ItemContainer.getPacketBufferNode(ClientPacket.field2985, Client.packetWriter.isaacCipher);
                    var9.packetBuffer.method7787(KeyHandler.KeyHandler_pressedKeys[82] ? 1 : 0);
                    var9.packetBuffer.writeShort(Client.selectedSpellChildIndex);
                    var9.packetBuffer.writeShortLE(menuIndex);
                    var9.packetBuffer.method7806(class92.selectedSpellWidget);
                    var9.packetBuffer.writeShortADD(Client.field688);
                    Client.packetWriter.addNode(var9);
                }
            } else if (actionOpcode == 9) {
                var13 = Client.npcs[menuIndex];
                if (var13 != null) {
                    Client.mouseCrossX = mouseX;
                    Client.mouseCrossY = mouseY;
                    Client.mouseCrossColor = 2;
                    Client.mouseCrossState = 0;
                    Client.destinationX = x;
                    Client.destinationY = y;
                    var9 = ItemContainer.getPacketBufferNode(ClientPacket.field2981, Client.packetWriter.isaacCipher);
                    var9.packetBuffer.method7787(KeyHandler.KeyHandler_pressedKeys[82] ? 1 : 0);
                    var9.packetBuffer.writeShortLE(menuIndex);
                    Client.packetWriter.addNode(var9);
                }
            } else if (actionOpcode == 10) {
                var13 = Client.npcs[menuIndex];
                if (var13 != null) {
                    Client.mouseCrossX = mouseX;
                    Client.mouseCrossY = mouseY;
                    Client.mouseCrossColor = 2;
                    Client.mouseCrossState = 0;
                    Client.destinationX = x;
                    Client.destinationY = y;
                    var9 = ItemContainer.getPacketBufferNode(ClientPacket.field2925, Client.packetWriter.isaacCipher);
                    var9.packetBuffer.writeShort(menuIndex);
                    var9.packetBuffer.writeByte(KeyHandler.KeyHandler_pressedKeys[82] ? 1 : 0);
                    Client.packetWriter.addNode(var9);
                }
            } else if (actionOpcode == 11) {
                var13 = Client.npcs[menuIndex];
                if (var13 != null) {
                    Client.mouseCrossX = mouseX;
                    Client.mouseCrossY = mouseY;
                    Client.mouseCrossColor = 2;
                    Client.mouseCrossState = 0;
                    Client.destinationX = x;
                    Client.destinationY = y;
                    var9 = ItemContainer.getPacketBufferNode(ClientPacket.field2902, Client.packetWriter.isaacCipher);
                    var9.packetBuffer.writeShortLE(menuIndex);
                    var9.packetBuffer.method7786(KeyHandler.KeyHandler_pressedKeys[82] ? 1 : 0);
                    Client.packetWriter.addNode(var9);
                }
            } else if (actionOpcode == 12) {
                var13 = Client.npcs[menuIndex];
                if (var13 != null) {
                    Client.mouseCrossX = mouseX;
                    Client.mouseCrossY = mouseY;
                    Client.mouseCrossColor = 2;
                    Client.mouseCrossState = 0;
                    Client.destinationX = x;
                    Client.destinationY = y;
                    var9 = ItemContainer.getPacketBufferNode(ClientPacket.field2893, Client.packetWriter.isaacCipher);
                    var9.packetBuffer.method7787(KeyHandler.KeyHandler_pressedKeys[82] ? 1 : 0);
                    var9.packetBuffer.writeShortLE(menuIndex);
                    Client.packetWriter.addNode(var9);
                }
            } else if (actionOpcode == 13) {
                var13 = Client.npcs[menuIndex];
                if (var13 != null) {
                    Client.mouseCrossX = mouseX;
                    Client.mouseCrossY = mouseY;
                    Client.mouseCrossColor = 2;
                    Client.mouseCrossState = 0;
                    Client.destinationX = x;
                    Client.destinationY = y;
                    var9 = ItemContainer.getPacketBufferNode(ClientPacket.field2916, Client.packetWriter.isaacCipher);
                    var9.packetBuffer.writeShortLE(menuIndex);
                    var9.packetBuffer.method7787(KeyHandler.KeyHandler_pressedKeys[82] ? 1 : 0);
                    Client.packetWriter.addNode(var9);
                }
			} else {
                Player targetPlayer;
                if (actionOpcode == 14) {
                    targetPlayer = Client.players[menuIndex];
                    if (targetPlayer != null) {
                        Client.mouseCrossX = mouseX;
                        Client.mouseCrossY = mouseY;
                        Client.mouseCrossColor = 2;
                        Client.mouseCrossState = 0;
                        Client.destinationX = x;
                        Client.destinationY = y;
                        var9 = ItemContainer.getPacketBufferNode(ClientPacket.field2933, Client.packetWriter.isaacCipher);
                        var9.packetBuffer.writeShortLEADD(selectedItemId);
                        var9.packetBuffer.writeShort(menuIndex);
                        var9.packetBuffer.writeByte(KeyHandler.KeyHandler_pressedKeys[82] ? 1 : 0);
                        var9.packetBuffer.writeShortADD(class19.selectedItemSlot);
                        var9.packetBuffer.writeIntME(PendingSpawn.selectedItemWidget);
                        Client.packetWriter.addNode(var9);
                    }
                } else if (actionOpcode == 15) {
                    targetPlayer = Client.players[menuIndex];
                    if (targetPlayer != null) {
                        Client.mouseCrossX = mouseX;
                        Client.mouseCrossY = mouseY;
                        Client.mouseCrossColor = 2;
                        Client.mouseCrossState = 0;
                        Client.destinationX = x;
                        Client.destinationY = y;
                        var9 = ItemContainer.getPacketBufferNode(ClientPacket.field2917, Client.packetWriter.isaacCipher);
                        var9.packetBuffer.writeShortLE(Client.selectedSpellChildIndex);
                        var9.packetBuffer.writeShortADD(menuIndex);
                        var9.packetBuffer.method7787(KeyHandler.KeyHandler_pressedKeys[82] ? 1 : 0);
                        var9.packetBuffer.writeInt(class92.selectedSpellWidget);
                        var9.packetBuffer.writeShort(Client.field688);
                        Client.packetWriter.addNode(var9);
                    }
                } else if (actionOpcode == 16) {
                    Client.mouseCrossX = mouseX;
                    Client.mouseCrossY = mouseY;
                    Client.mouseCrossColor = 2;
                    Client.mouseCrossState = 0;
                    Client.destinationX = x;
                    Client.destinationY = y;
                    var8 = ItemContainer.getPacketBufferNode(ClientPacket.field2900, Client.packetWriter.isaacCipher);
                    var8.packetBuffer.writeByteSUB(KeyHandler.KeyHandler_pressedKeys[82] ? 1 : 0);
                    var8.packetBuffer.writeShortADD(class19.selectedItemSlot);
                    var8.packetBuffer.writeShortADD(menuIndex);
                    var8.packetBuffer.writeShortADD(x + ApproximateRouteStrategy.baseX);
                    var8.packetBuffer.writeShortLEADD(class250.baseY + y);
                    var8.packetBuffer.writeIntME(PendingSpawn.selectedItemWidget);
                    var8.packetBuffer.writeShort(selectedItemId);
                    Client.packetWriter.addNode(var8);
                } else if (actionOpcode == 17) {
                    Client.mouseCrossX = mouseX;
                    Client.mouseCrossY = mouseY;
                    Client.mouseCrossColor = 2;
                    Client.mouseCrossState = 0;
                    Client.destinationX = x;
                    Client.destinationY = y;
                    var8 = ItemContainer.getPacketBufferNode(ClientPacket.field2942, Client.packetWriter.isaacCipher);
                    var8.packetBuffer.writeShort(Client.field688);
                    var8.packetBuffer.writeByte(KeyHandler.KeyHandler_pressedKeys[82] ? 1 : 0);
                    var8.packetBuffer.writeShortLE(menuIndex);
                    var8.packetBuffer.writeInt(class92.selectedSpellWidget);
                    var8.packetBuffer.writeShort(class250.baseY + y);
                    var8.packetBuffer.writeShort(Client.selectedSpellChildIndex);
                    var8.packetBuffer.writeShortADD(x + ApproximateRouteStrategy.baseX);
                    Client.packetWriter.addNode(var8);
                } else if (actionOpcode == 18) {
                    Client.mouseCrossX = mouseX;
                    Client.mouseCrossY = mouseY;
                    Client.mouseCrossColor = 2;
                    Client.mouseCrossState = 0;
                    Client.destinationX = x;
                    Client.destinationY = y;
                    var8 = ItemContainer.getPacketBufferNode(ClientPacket.field2971, Client.packetWriter.isaacCipher);
                    var8.packetBuffer.writeByteSUB(KeyHandler.KeyHandler_pressedKeys[82] ? 1 : 0);
                    var8.packetBuffer.writeShortLEADD(menuIndex);
                    var8.packetBuffer.writeShort(class250.baseY + y);
                    var8.packetBuffer.writeShortLE(x + ApproximateRouteStrategy.baseX);
                    Client.packetWriter.addNode(var8);
                } else if (actionOpcode == 19) {
                    Client.mouseCrossX = mouseX;
                    Client.mouseCrossY = mouseY;
                    Client.mouseCrossColor = 2;
                    Client.mouseCrossState = 0;
                    Client.destinationX = x;
                    Client.destinationY = y;
                    var8 = ItemContainer.getPacketBufferNode(ClientPacket.field2915, Client.packetWriter.isaacCipher);
                    var8.packetBuffer.writeShort(class250.baseY + y);
                    var8.packetBuffer.method7787(KeyHandler.KeyHandler_pressedKeys[82] ? 1 : 0);
                    var8.packetBuffer.writeShort(menuIndex);
                    var8.packetBuffer.writeShortLE(x + ApproximateRouteStrategy.baseX);
                    Client.packetWriter.addNode(var8);
                } else if (actionOpcode == 20) {
                    Client.mouseCrossX = mouseX;
                    Client.mouseCrossY = mouseY;
                    Client.mouseCrossColor = 2;
                    Client.mouseCrossState = 0;
                    Client.destinationX = x;
                    Client.destinationY = y;
                    var8 = ItemContainer.getPacketBufferNode(ClientPacket.field2951, Client.packetWriter.isaacCipher);
                    var8.packetBuffer.writeShortLE(x + ApproximateRouteStrategy.baseX);
                    var8.packetBuffer.writeShort(menuIndex);
                    var8.packetBuffer.method7786(KeyHandler.KeyHandler_pressedKeys[82] ? 1 : 0);
                    var8.packetBuffer.writeShortADD(class250.baseY + y);
                    Client.packetWriter.addNode(var8);
                } else if (actionOpcode == 21) {
                    Client.mouseCrossX = mouseX;
                    Client.mouseCrossY = mouseY;
                    Client.mouseCrossColor = 2;
                    Client.mouseCrossState = 0;
                    Client.destinationX = x;
                    Client.destinationY = y;
                    var8 = ItemContainer.getPacketBufferNode(ClientPacket.field3000, Client.packetWriter.isaacCipher);
                    var8.packetBuffer.method7787(KeyHandler.KeyHandler_pressedKeys[82] ? 1 : 0);
                    var8.packetBuffer.writeShort(x + ApproximateRouteStrategy.baseX);
                    var8.packetBuffer.writeShortLEADD(class250.baseY + y);
                    var8.packetBuffer.writeShortLEADD(menuIndex);
                    Client.packetWriter.addNode(var8);
                } else if (actionOpcode == 22) {
                    Client.mouseCrossX = mouseX;
                    Client.mouseCrossY = mouseY;
                    Client.mouseCrossColor = 2;
                    Client.mouseCrossState = 0;
                    Client.destinationX = x;
                    Client.destinationY = y;
                    var8 = ItemContainer.getPacketBufferNode(ClientPacket.field2909, Client.packetWriter.isaacCipher);
                    var8.packetBuffer.method7786(KeyHandler.KeyHandler_pressedKeys[82] ? 1 : 0);
                    var8.packetBuffer.writeShortLEADD(x + ApproximateRouteStrategy.baseX);
                    var8.packetBuffer.writeShortLE(class250.baseY + y);
                    var8.packetBuffer.writeShort(menuIndex);
                    Client.packetWriter.addNode(var8);
                } else if (actionOpcode == 23) {
                    if (Client.isMenuOpen) {
                        FriendSystem.scene.setViewportWalking();
                    } else {
                        FriendSystem.scene.menuOpen(class160.Client_plane, x, y, true);
                    }
                } else {
                    PacketBufferNode var10;
                    Widget var16;
                    if (actionOpcode == 24) {
                        var16 = HorizontalAlignment.getWidget(y);
                        boolean var11 = true;
                        if (var16.contentType > 0) {
                            var11 = Frames.method4319(var16);
                        }

                        if (var11) {
                            var10 = ItemContainer.getPacketBufferNode(ClientPacket.field2937, Client.packetWriter.isaacCipher);
                            var10.packetBuffer.writeInt(y);
                            Client.packetWriter.addNode(var10);
                        }
					} else {
                        if (actionOpcode == 25) {
                            var16 = class143.getWidgetChild(y, x);
                            if (var16 != null) {
                                class120.Widget_runOnTargetLeave();
                                ApproximateRouteStrategy.selectSpell(y, x, WorldMapSection2.Widget_unpackTargetMask(WorldMapSection2.getWidgetFlags(var16)), var16.itemId);
                                Client.isItemSelected = 0;
                                Client.selectedSpellActionName = InvDefinition.Widget_getSpellActionName(var16);
                                if (Client.selectedSpellActionName == null) {
                                    Client.selectedSpellActionName = "null";
                                }

                                if (var16.isIf3) {
									Client.selectedSpellName = var16.dataText + ChatChannel.colorStartTag(16777215);
								} else {
									Client.selectedSpellName = ChatChannel.colorStartTag(65280) + var16.spellName + ChatChannel.colorStartTag(16777215);
								}
							}

							return;
						}

                        if (actionOpcode == 26) {
                            class9.method85();
                        } else {
                            int var12;
                            Widget var14;
                            if (actionOpcode == 28) {
                                var8 = ItemContainer.getPacketBufferNode(ClientPacket.field2937, Client.packetWriter.isaacCipher);
                                var8.packetBuffer.writeInt(y);
                                Client.packetWriter.addNode(var8);
                                var14 = HorizontalAlignment.getWidget(y);
                                if (var14.cs1Instructions != null && var14.cs1Instructions[0][0] == 5) {
                                    var12 = var14.cs1Instructions[0][1];
                                    Varps.Varps_main[var12] = 1 - Varps.Varps_main[var12];
                                    class78.changeGameOptions(var12);
                                }
                            } else if (actionOpcode == 29) {
                                var8 = ItemContainer.getPacketBufferNode(ClientPacket.field2937, Client.packetWriter.isaacCipher);
                                var8.packetBuffer.writeInt(y);
                                Client.packetWriter.addNode(var8);
                                var14 = HorizontalAlignment.getWidget(y);
                                if (var14.cs1Instructions != null && var14.cs1Instructions[0][0] == 5) {
                                    var12 = var14.cs1Instructions[0][1];
                                    if (Varps.Varps_main[var12] != var14.cs1ComparisonValues[0]) {
                                        Varps.Varps_main[var12] = var14.cs1ComparisonValues[0];
                                        class78.changeGameOptions(var12);
                                    }
                                }
                            } else if (actionOpcode == 30) {
                                if (Client.meslayerContinueWidget == null) {
                                    Messages.resumePauseWidget(y, x);
                                    Client.meslayerContinueWidget = class143.getWidgetChild(y, x);
                                    class290.invalidateWidget(Client.meslayerContinueWidget);
                                }
                            } else if (actionOpcode == 31) {
                                var8 = ItemContainer.getPacketBufferNode(ClientPacket.field2954, Client.packetWriter.isaacCipher);
                                var8.packetBuffer.writeIntME(PendingSpawn.selectedItemWidget);
                                var8.packetBuffer.writeShortLE(class19.selectedItemSlot);
                                var8.packetBuffer.method7806(y);
                                var8.packetBuffer.writeShort(menuIndex);
                                var8.packetBuffer.writeShort(x);
                                var8.packetBuffer.writeShortLE(selectedItemId);
                                Client.packetWriter.addNode(var8);
                                Client.field595 = 0;
                                class12.field64 = HorizontalAlignment.getWidget(y);
                                Client.field741 = x;
                            } else if (actionOpcode == 32) {
                                var8 = ItemContainer.getPacketBufferNode(ClientPacket.field2984, Client.packetWriter.isaacCipher);
                                var8.packetBuffer.writeShortADD(menuIndex);
                                var8.packetBuffer.writeIntME(class92.selectedSpellWidget);
                                var8.packetBuffer.writeShortADD(x);
                                var8.packetBuffer.writeShort(Client.selectedSpellChildIndex);
                                var8.packetBuffer.writeInt(y);
                                Client.packetWriter.addNode(var8);
                                Client.field595 = 0;
                                class12.field64 = HorizontalAlignment.getWidget(y);
                                Client.field741 = x;
                            } else if (actionOpcode == 33) {
                                var8 = ItemContainer.getPacketBufferNode(ClientPacket.field2997, Client.packetWriter.isaacCipher);
                                var8.packetBuffer.writeShort(menuIndex);
                                var8.packetBuffer.writeShort(x);
                                var8.packetBuffer.method7806(y);
                                Client.packetWriter.addNode(var8);
                                Client.field595 = 0;
                                class12.field64 = HorizontalAlignment.getWidget(y);
                                Client.field741 = x;
                            } else if (actionOpcode == 34) {
                                var8 = ItemContainer.getPacketBufferNode(ClientPacket.field2969, Client.packetWriter.isaacCipher);
                                var8.packetBuffer.method7806(y);
                                var8.packetBuffer.writeShort(x);
                                var8.packetBuffer.writeShort(menuIndex);
                                Client.packetWriter.addNode(var8);
                                Client.field595 = 0;
                                class12.field64 = HorizontalAlignment.getWidget(y);
                                Client.field741 = x;
                            } else if (actionOpcode == 35) {
                                var8 = ItemContainer.getPacketBufferNode(ClientPacket.field2918, Client.packetWriter.isaacCipher);
                                var8.packetBuffer.writeShort(menuIndex);
                                var8.packetBuffer.writeShort(x);
                                var8.packetBuffer.method7806(y);
                                Client.packetWriter.addNode(var8);
                                Client.field595 = 0;
                                class12.field64 = HorizontalAlignment.getWidget(y);
                                Client.field741 = x;
                            } else if (actionOpcode == 36) {
                                var8 = ItemContainer.getPacketBufferNode(ClientPacket.field2948, Client.packetWriter.isaacCipher);
                                var8.packetBuffer.writeShortLE(menuIndex);
                                var8.packetBuffer.method7806(y);
                                var8.packetBuffer.writeShortLEADD(x);
                                Client.packetWriter.addNode(var8);
                                Client.field595 = 0;
                                class12.field64 = HorizontalAlignment.getWidget(y);
                                Client.field741 = x;
                            } else if (actionOpcode == 37) {
                                var8 = ItemContainer.getPacketBufferNode(ClientPacket.field3002, Client.packetWriter.isaacCipher);
                                var8.packetBuffer.writeIntME(y);
                                var8.packetBuffer.writeShort(menuIndex);
                                var8.packetBuffer.writeShortLEADD(x);
                                Client.packetWriter.addNode(var8);
                                Client.field595 = 0;
                                class12.field64 = HorizontalAlignment.getWidget(y);
                                Client.field741 = x;
                            } else {
                                if (actionOpcode == 38) {
                                    class120.Widget_runOnTargetLeave();
                                    var16 = HorizontalAlignment.getWidget(y);
                                    Client.isItemSelected = 1;
                                    class19.selectedItemSlot = x;
                                    PendingSpawn.selectedItemWidget = y;
                                    selectedItemId = menuIndex;
                                    class290.invalidateWidget(var16);
                                    Client.selectedItemName = ChatChannel.colorStartTag(16748608) + class67.ItemDefinition_get(menuIndex).name + ChatChannel.colorStartTag(16777215);
                                    if (Client.selectedItemName == null) {
                                        Client.selectedItemName = "null";
                                    }

                                    return;
                                }

                                if (actionOpcode == 39) {
                                    var8 = ItemContainer.getPacketBufferNode(ClientPacket.field2974, Client.packetWriter.isaacCipher);
                                    var8.packetBuffer.writeInt(y);
                                    var8.packetBuffer.writeShortADD(x);
                                    var8.packetBuffer.writeShortLE(menuIndex);
                                    Client.packetWriter.addNode(var8);
                                    Client.field595 = 0;
                                    class12.field64 = HorizontalAlignment.getWidget(y);
                                    Client.field741 = x;
                                } else if (actionOpcode == 40) {
                                    var8 = ItemContainer.getPacketBufferNode(ClientPacket.field2931, Client.packetWriter.isaacCipher);
                                    var8.packetBuffer.writeIntME(y);
                                    var8.packetBuffer.writeShort(x);
                                    var8.packetBuffer.writeShortADD(menuIndex);
                                    Client.packetWriter.addNode(var8);
                                    Client.field595 = 0;
                                    class12.field64 = HorizontalAlignment.getWidget(y);
                                    Client.field741 = x;
                                } else if (actionOpcode == 41) {
                                    var8 = ItemContainer.getPacketBufferNode(ClientPacket.field2922, Client.packetWriter.isaacCipher);
                                    var8.packetBuffer.writeShortADD(menuIndex);
                                    var8.packetBuffer.method7806(y);
                                    var8.packetBuffer.writeShortLEADD(x);
                                    Client.packetWriter.addNode(var8);
                                    Client.field595 = 0;
                                    class12.field64 = HorizontalAlignment.getWidget(y);
                                    Client.field741 = x;
                                } else if (actionOpcode == 42) {
                                    var8 = ItemContainer.getPacketBufferNode(ClientPacket.field2993, Client.packetWriter.isaacCipher);
                                    var8.packetBuffer.writeShortLEADD(x);
                                    var8.packetBuffer.writeInt(y);
                                    var8.packetBuffer.writeShortLE(menuIndex);
                                    Client.packetWriter.addNode(var8);
                                    Client.field595 = 0;
                                    class12.field64 = HorizontalAlignment.getWidget(y);
                                    Client.field741 = x;
                                } else if (actionOpcode == 43) {
                                    var8 = ItemContainer.getPacketBufferNode(ClientPacket.field2895, Client.packetWriter.isaacCipher);
                                    var8.packetBuffer.writeShortLEADD(x);
                                    var8.packetBuffer.writeShortLEADD(menuIndex);
                                    var8.packetBuffer.writeInt(y);
                                    Client.packetWriter.addNode(var8);
                                    Client.field595 = 0;
                                    class12.field64 = HorizontalAlignment.getWidget(y);
                                    Client.field741 = x;
                                } else if (actionOpcode == 44) {
                                    targetPlayer = Client.players[menuIndex];
                                    if (targetPlayer != null) {
                                        Client.mouseCrossX = mouseX;
                                        Client.mouseCrossY = mouseY;
                                        Client.mouseCrossColor = 2;
                                        Client.mouseCrossState = 0;
                                        Client.destinationX = x;
                                        Client.destinationY = y;
                                        var9 = ItemContainer.getPacketBufferNode(ClientPacket.field2913, Client.packetWriter.isaacCipher);
                                        var9.packetBuffer.writeShortLE(menuIndex);
                                        var9.packetBuffer.method7786(KeyHandler.KeyHandler_pressedKeys[82] ? 1 : 0);
                                        Client.packetWriter.addNode(var9);
                                    }
                                } else if (actionOpcode == 45) {
                                    targetPlayer = Client.players[menuIndex];
                                    if (targetPlayer != null) {
                                        Client.mouseCrossX = mouseX;
                                        Client.mouseCrossY = mouseY;
                                        Client.mouseCrossColor = 2;
                                        Client.mouseCrossState = 0;
                                        Client.destinationX = x;
                                        Client.destinationY = y;
                                        var9 = ItemContainer.getPacketBufferNode(ClientPacket.field2957, Client.packetWriter.isaacCipher);
                                        var9.packetBuffer.writeShortLE(menuIndex);
                                        var9.packetBuffer.method7787(KeyHandler.KeyHandler_pressedKeys[82] ? 1 : 0);
                                        Client.packetWriter.addNode(var9);
                                    }
                                } else if (actionOpcode == 46) {
                                    targetPlayer = Client.players[menuIndex];
                                    if (targetPlayer != null) {
                                        Client.mouseCrossX = mouseX;
                                        Client.mouseCrossY = mouseY;
                                        Client.mouseCrossColor = 2;
                                        Client.mouseCrossState = 0;
                                        Client.destinationX = x;
                                        Client.destinationY = y;
                                        var9 = ItemContainer.getPacketBufferNode(ClientPacket.field2964, Client.packetWriter.isaacCipher);
                                        var9.packetBuffer.method7786(KeyHandler.KeyHandler_pressedKeys[82] ? 1 : 0);
                                        var9.packetBuffer.writeShortADD(menuIndex);
                                        Client.packetWriter.addNode(var9);
                                    }
                                } else if (actionOpcode == 47) {
                                    targetPlayer = Client.players[menuIndex];
                                    if (targetPlayer != null) {
                                        Client.mouseCrossX = mouseX;
                                        Client.mouseCrossY = mouseY;
                                        Client.mouseCrossColor = 2;
                                        Client.mouseCrossState = 0;
                                        Client.destinationX = x;
                                        Client.destinationY = y;
                                        var9 = ItemContainer.getPacketBufferNode(ClientPacket.field2904, Client.packetWriter.isaacCipher);
                                        var9.packetBuffer.method7787(KeyHandler.KeyHandler_pressedKeys[82] ? 1 : 0);
                                        var9.packetBuffer.writeShort(menuIndex);
                                        Client.packetWriter.addNode(var9);
                                    }
                                } else if (actionOpcode == 48) {
                                    targetPlayer = Client.players[menuIndex];
                                    if (targetPlayer != null) {
                                        Client.mouseCrossX = mouseX;
                                        Client.mouseCrossY = mouseY;
                                        Client.mouseCrossColor = 2;
                                        Client.mouseCrossState = 0;
                                        Client.destinationX = x;
                                        Client.destinationY = y;
                                        var9 = ItemContainer.getPacketBufferNode(ClientPacket.field2972, Client.packetWriter.isaacCipher);
                                        var9.packetBuffer.writeShort(menuIndex);
                                        var9.packetBuffer.method7786(KeyHandler.KeyHandler_pressedKeys[82] ? 1 : 0);
                                        Client.packetWriter.addNode(var9);
                                    }
                                } else if (actionOpcode == 49) {
                                    targetPlayer = Client.players[menuIndex];
                                    if (targetPlayer != null) {
                                        Client.mouseCrossX = mouseX;
                                        Client.mouseCrossY = mouseY;
                                        Client.mouseCrossColor = 2;
                                        Client.mouseCrossState = 0;
                                        Client.destinationX = x;
                                        Client.destinationY = y;
                                        var9 = ItemContainer.getPacketBufferNode(ClientPacket.field2903, Client.packetWriter.isaacCipher);
                                        var9.packetBuffer.writeShortLEADD(menuIndex);
                                        var9.packetBuffer.writeByte(KeyHandler.KeyHandler_pressedKeys[82] ? 1 : 0);
                                        Client.packetWriter.addNode(var9);
                                    }
                                } else if (actionOpcode == 50) {
                                    targetPlayer = Client.players[menuIndex];
                                    if (targetPlayer != null) {
                                        Client.mouseCrossX = mouseX;
                                        Client.mouseCrossY = mouseY;
                                        Client.mouseCrossColor = 2;
                                        Client.mouseCrossState = 0;
                                        Client.destinationX = x;
                                        Client.destinationY = y;
                                        var9 = ItemContainer.getPacketBufferNode(ClientPacket.field2898, Client.packetWriter.isaacCipher);
                                        var9.packetBuffer.writeShort(menuIndex);
                                        var9.packetBuffer.method7786(KeyHandler.KeyHandler_pressedKeys[82] ? 1 : 0);
                                        Client.packetWriter.addNode(var9);
                                    }
                                } else if (actionOpcode == 51) {
                                    targetPlayer = Client.players[menuIndex];
                                    if (targetPlayer != null) {
                                        Client.mouseCrossX = mouseX;
                                        Client.mouseCrossY = mouseY;
                                        Client.mouseCrossColor = 2;
                                        Client.mouseCrossState = 0;
                                        Client.destinationX = x;
                                        Client.destinationY = y;
                                        var9 = ItemContainer.getPacketBufferNode(ClientPacket.field2912, Client.packetWriter.isaacCipher);
                                        var9.packetBuffer.writeShortLEADD(menuIndex);
                                        var9.packetBuffer.method7787(KeyHandler.KeyHandler_pressedKeys[82] ? 1 : 0);
                                        Client.packetWriter.addNode(var9);
                                    }
								} else {
									label638:
                                    {
                                        if (actionOpcode != 57) {
                                            if (actionOpcode == 58) {
                                                var16 = class143.getWidgetChild(y, x);
                                                if (var16 != null) {
                                                    var9 = ItemContainer.getPacketBufferNode(ClientPacket.field2932, Client.packetWriter.isaacCipher);
                                                    var9.packetBuffer.writeShort(Client.field688);
                                                    var9.packetBuffer.writeIntME(class92.selectedSpellWidget);
                                                    var9.packetBuffer.writeShortADD(var16.itemId);
                                                    var9.packetBuffer.writeShortLEADD(Client.selectedSpellChildIndex);
                                                    var9.packetBuffer.writeInt(y);
                                                    var9.packetBuffer.writeShortADD(x);
                                                    Client.packetWriter.addNode(var9);
                                                }
												break label638;
											}

                                            if (actionOpcode == 1001) {
                                                Client.mouseCrossX = mouseX;
                                                Client.mouseCrossY = mouseY;
                                                Client.mouseCrossColor = 2;
                                                Client.mouseCrossState = 0;
                                                Client.destinationX = x;
                                                Client.destinationY = y;
                                                var8 = ItemContainer.getPacketBufferNode(ClientPacket.field2976, Client.packetWriter.isaacCipher);
                                                var8.packetBuffer.writeShortLE(menuIndex);
                                                var8.packetBuffer.writeShortADD(x + ApproximateRouteStrategy.baseX);
                                                var8.packetBuffer.method7787(KeyHandler.KeyHandler_pressedKeys[82] ? 1 : 0);
                                                var8.packetBuffer.writeShort(class250.baseY + y);
                                                Client.packetWriter.addNode(var8);
                                                break label638;
                                            }

                                            if (actionOpcode == 1002) {
                                                Client.mouseCrossX = mouseX;
                                                Client.mouseCrossY = mouseY;
                                                Client.mouseCrossColor = 2;
                                                Client.mouseCrossState = 0;
                                                var8 = ItemContainer.getPacketBufferNode(ClientPacket.field2949, Client.packetWriter.isaacCipher);
                                                var8.packetBuffer.writeShortLEADD(menuIndex);
                                                Client.packetWriter.addNode(var8);
                                                break label638;
                                            }

                                            if (actionOpcode == 1003) {
                                                Client.mouseCrossX = mouseX;
                                                Client.mouseCrossY = mouseY;
                                                Client.mouseCrossColor = 2;
                                                Client.mouseCrossState = 0;
                                                var13 = Client.npcs[menuIndex];
                                                if (var13 != null) {
                                                    NPCComposition var17 = var13.definition;
                                                    if (var17.transforms != null) {
                                                        var17 = var17.transform();
                                                    }

													if (var17 != null) {
                                                        var10 = ItemContainer.getPacketBufferNode(ClientPacket.field2958, Client.packetWriter.isaacCipher);
                                                        var10.packetBuffer.writeShortLE(var17.id);
                                                        Client.packetWriter.addNode(var10);
													}
												}
												break label638;
											}

                                            if (actionOpcode == 1004) {
                                                Client.mouseCrossX = mouseX;
                                                Client.mouseCrossY = mouseY;
                                                Client.mouseCrossColor = 2;
                                                Client.mouseCrossState = 0;
                                                var8 = ItemContainer.getPacketBufferNode(ClientPacket.field2988, Client.packetWriter.isaacCipher);
                                                var8.packetBuffer.writeShortADD(menuIndex);
                                                Client.packetWriter.addNode(var8);
                                                break label638;
                                            }

                                            if (actionOpcode == 1005) {
                                                var16 = HorizontalAlignment.getWidget(y);
                                                if (var16 != null && var16.itemQuantities[x] >= 100000) {
                                                    Login.addGameMessage(27, "", var16.itemQuantities[x] + " x " + class67.ItemDefinition_get(menuIndex).name);
                                                } else {
                                                    var9 = ItemContainer.getPacketBufferNode(ClientPacket.field2988, Client.packetWriter.isaacCipher);
                                                    var9.packetBuffer.writeShortADD(menuIndex);
                                                    Client.packetWriter.addNode(var9);
                                                }

                                                Client.field595 = 0;
                                                class12.field64 = HorizontalAlignment.getWidget(y);
                                                Client.field741 = x;
                                                break label638;
                                            }

                                            if (actionOpcode != 1007) {
                                                if (actionOpcode == 1009 || actionOpcode == 1008 || actionOpcode == 1010 || actionOpcode == 1011 || actionOpcode == 1012) {
                                                    ClanChannel.worldMap.worldMapMenuAction(actionOpcode, menuIndex, new Coord(x), new Coord(y));
                                                }
                                                break label638;
                                            }
                                        }

                                        var16 = class143.getWidgetChild(y, x);
										if (var16 != null) {
                                            Message.widgetDefaultMenuAction(menuIndex, y, x, var16.itemId, var5);
										}
									}
								}
							}
						}
					}
				}
			}
		}

		if (Client.isItemSelected != 0) {
			Client.isItemSelected = 0;
			class290.invalidateWidget(HorizontalAlignment.getWidget(PendingSpawn.selectedItemWidget));
		}

		if (Client.isSpellSelected) {
			class120.Widget_runOnTargetLeave();
		}

		if (class12.field64 != null && Client.field595 == 0) {
			class290.invalidateWidget(class12.field64);
		}

	}
}
