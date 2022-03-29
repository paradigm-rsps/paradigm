import rs.ScriptOpcodes;

public class HitSplatDefinition extends DualNode {
	public static AbstractArchive HitSplatDefinition_archive;
	public static AbstractArchive HitSplatDefinition_fontsArchive;
	static EvictingDualNodeHashTable HitSplatDefinition_cached;
	static EvictingDualNodeHashTable HitSplatDefinition_cachedSprites;
	static EvictingDualNodeHashTable HitSplatDefinition_cachedFonts;
	int fontId;
	public int textColor;
	public int field2025;
	int field2018;
	int field2015;
	int field2014;
	int field2003;
	public int field2016;
	public int field2017;
	public int field2005;
	String field2019;
	public int field2020;
	public int field2021;
	public int[] transforms;
	int transformVarbit;
	int transformVarp;

	static {
		HitSplatDefinition_cached = new EvictingDualNodeHashTable(64);
		HitSplatDefinition_cachedSprites = new EvictingDualNodeHashTable(64);
		HitSplatDefinition_cachedFonts = new EvictingDualNodeHashTable(20);
	}

	HitSplatDefinition() {
		this.fontId = -1;
		this.textColor = 16777215;
		this.field2025 = 70;
		this.field2018 = -1;
		this.field2015 = -1;
		this.field2014 = -1;
		this.field2003 = -1;
		this.field2016 = 0;
		this.field2017 = 0;
		this.field2005 = -1;
		this.field2019 = "";
		this.field2020 = -1;
		this.field2021 = 0;
		this.transformVarbit = -1;
		this.transformVarp = -1;
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
			this.fontId = var1.method7742();
		} else if (var2 == 2) {
			this.textColor = var1.readMedium();
		} else if (var2 == 3) {
			this.field2018 = var1.method7742();
		} else if (var2 == 4) {
			this.field2014 = var1.method7742();
		} else if (var2 == 5) {
			this.field2015 = var1.method7742();
		} else if (var2 == 6) {
			this.field2003 = var1.method7742();
		} else if (var2 == 7) {
			this.field2016 = var1.readShort();
		} else if (var2 == 8) {
			this.field2019 = var1.readStringCp1252NullCircumfixed();
		} else if (var2 == 9) {
			this.field2025 = var1.readUnsignedShort();
		} else if (var2 == 10) {
			this.field2017 = var1.readShort();
		} else if (var2 == 11) {
			this.field2005 = 0;
		} else if (var2 == 12) {
			this.field2020 = var1.readUnsignedByte();
		} else if (var2 == 13) {
			this.field2021 = var1.readShort();
		} else if (var2 == 14) {
			this.field2005 = var1.readUnsignedShort();
		} else if (var2 == 17 || var2 == 18) {
			this.transformVarbit = var1.readUnsignedShort();
			if (this.transformVarbit == 65535) {
				this.transformVarbit = -1;
			}

			this.transformVarp = var1.readUnsignedShort();
			if (this.transformVarp == 65535) {
				this.transformVarp = -1;
			}

			int var3 = -1;
			if (var2 == 18) {
				var3 = var1.readUnsignedShort();
				if (var3 == 65535) {
					var3 = -1;
				}
			}

			int var4 = var1.readUnsignedByte();
			this.transforms = new int[var4 + 2];

			for (int var5 = 0; var5 <= var4; ++var5) {
				this.transforms[var5] = var1.readUnsignedShort();
				if (this.transforms[var5] == 65535) {
					this.transforms[var5] = -1;
				}
			}

			this.transforms[var4 + 1] = var3;
		}

	}

	public final HitSplatDefinition transform() {
		int var1 = -1;
		if (this.transformVarbit != -1) {
			var1 = WorldMapRegion.getVarbit(this.transformVarbit);
		} else if (this.transformVarp != -1) {
			var1 = Varps.Varps_main[this.transformVarp];
		}

		int var2;
		if (var1 >= 0 && var1 < this.transforms.length - 1) {
			var2 = this.transforms[var1];
		} else {
			var2 = this.transforms[this.transforms.length - 1];
		}

		return var2 != -1 ? GameEngine.method589(var2) : null;
	}

	public String getString(int var1) {
		String var2 = this.field2019;

		while (true) {
			int var3 = var2.indexOf("%1");
			if (var3 < 0) {
				return var2;
			}

			var2 = var2.substring(0, var3) + JagexCache.intToString(var1, false) + var2.substring(var3 + 2);
		}
	}

	public SpritePixels method3611() {
		if (this.field2018 < 0) {
			return null;
		} else {
			SpritePixels var1 = (SpritePixels)HitSplatDefinition_cachedSprites.get(this.field2018);
			if (var1 != null) {
				return var1;
			} else {
				var1 = InterfaceParent.SpriteBuffer_getSprite(class20.field101, this.field2018, 0);
				if (var1 != null) {
					HitSplatDefinition_cachedSprites.put(var1, this.field2018);
				}

				return var1;
			}
		}
	}

	public SpritePixels method3620() {
		if (this.field2015 < 0) {
			return null;
		} else {
			SpritePixels var1 = (SpritePixels)HitSplatDefinition_cachedSprites.get(this.field2015);
			if (var1 != null) {
				return var1;
			} else {
				var1 = InterfaceParent.SpriteBuffer_getSprite(class20.field101, this.field2015, 0);
				if (var1 != null) {
					HitSplatDefinition_cachedSprites.put(var1, this.field2015);
				}

				return var1;
			}
		}
	}

	public SpritePixels method3613() {
		if (this.field2014 < 0) {
			return null;
		} else {
			SpritePixels var1 = (SpritePixels)HitSplatDefinition_cachedSprites.get(this.field2014);
			if (var1 != null) {
				return var1;
			} else {
				var1 = InterfaceParent.SpriteBuffer_getSprite(class20.field101, this.field2014, 0);
				if (var1 != null) {
					HitSplatDefinition_cachedSprites.put(var1, this.field2014);
				}

				return var1;
			}
		}
	}

	public SpritePixels method3614() {
		if (this.field2003 < 0) {
			return null;
		} else {
			SpritePixels var1 = (SpritePixels)HitSplatDefinition_cachedSprites.get(this.field2003);
			if (var1 != null) {
				return var1;
			} else {
				var1 = InterfaceParent.SpriteBuffer_getSprite(class20.field101, this.field2003, 0);
				if (var1 != null) {
					HitSplatDefinition_cachedSprites.put(var1, this.field2003);
				}

				return var1;
			}
		}
	}

	public Font getFont() {
		if (this.fontId == -1) {
			return null;
		} else {
			Font var1 = (Font)HitSplatDefinition_cachedFonts.get(this.fontId);
			if (var1 != null) {
				return var1;
			} else {
				var1 = class19.method319(class20.field101, HitSplatDefinition_fontsArchive, this.fontId, 0);
				if (var1 != null) {
					HitSplatDefinition_cachedFonts.put(var1, this.fontId);
				}

				return var1;
			}
		}
	}

	public static HealthBarDefinition method3619(int var0) {
		HealthBarDefinition var1 = (HealthBarDefinition)HealthBarDefinition.HealthBarDefinition_cached.get(var0);
		if (var1 != null) {
			return var1;
		} else {
			byte[] var2 = HealthBarDefinition.HealthBarDefinition_archive.takeFile(33, var0);
			var1 = new HealthBarDefinition();
			if (var2 != null) {
				var1.decode(new Buffer(var2));
			}

			HealthBarDefinition.HealthBarDefinition_cached.put(var1, var0);
			return var1;
		}
	}

	public static PlayerType[] PlayerType_values() {
		return new PlayerType[]{PlayerType.PlayerType_hardcoreIronman, PlayerType.PlayerType_ironman, PlayerType.field3919, PlayerType.field3931, PlayerType.PlayerType_playerModerator, PlayerType.PlayerType_normal, PlayerType.field3926, PlayerType.PlayerType_jagexModerator, PlayerType.PlayerType_ultimateIronman};
	}

	static int method3642(int var0, Script var1, boolean var2) {
		if (var0 == ScriptOpcodes.WORLDLIST_FETCH) {
			Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = MusicPatch.loadWorlds() ? 1 : 0;
			return 1;
		} else {
			World var9;
			if (var0 == ScriptOpcodes.WORLDLIST_START) {
				var9 = FloorDecoration.worldListStart();
				if (var9 != null) {
					Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = var9.id;
					Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = var9.properties;
					Interpreter.Interpreter_stringStack[++ChatChannel.Interpreter_stringStackSize - 1] = var9.activity;
					Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = var9.location;
					Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = var9.population;
					Interpreter.Interpreter_stringStack[++ChatChannel.Interpreter_stringStackSize - 1] = var9.host;
				} else {
					Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = -1;
					Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = 0;
					Interpreter.Interpreter_stringStack[++ChatChannel.Interpreter_stringStackSize - 1] = "";
					Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = 0;
					Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = 0;
					Interpreter.Interpreter_stringStack[++ChatChannel.Interpreter_stringStackSize - 1] = "";
				}

				return 1;
			} else if (var0 == ScriptOpcodes.WORLDLIST_NEXT) {
				var9 = WallDecoration.getNextWorldListWorld();
				if (var9 != null) {
					Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = var9.id;
					Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = var9.properties;
					Interpreter.Interpreter_stringStack[++ChatChannel.Interpreter_stringStackSize - 1] = var9.activity;
					Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = var9.location;
					Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = var9.population;
					Interpreter.Interpreter_stringStack[++ChatChannel.Interpreter_stringStackSize - 1] = var9.host;
				} else {
					Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = -1;
					Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = 0;
					Interpreter.Interpreter_stringStack[++ChatChannel.Interpreter_stringStackSize - 1] = "";
					Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = 0;
					Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = 0;
					Interpreter.Interpreter_stringStack[++ChatChannel.Interpreter_stringStackSize - 1] = "";
				}

				return 1;
			} else {
				int var3;
				World var7;
				int var8;
				if (var0 == ScriptOpcodes.WORLDLIST_SPECIFIC) {
					var3 = Interpreter.Interpreter_intStack[--class295.Interpreter_intStackSize];
					var7 = null;

					for (var8 = 0; var8 < World.World_count; ++var8) {
						if (var3 == World.World_worlds[var8].id) {
							var7 = World.World_worlds[var8];
							break;
						}
					}

					if (var7 != null) {
						Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = var7.id;
						Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = var7.properties;
						Interpreter.Interpreter_stringStack[++ChatChannel.Interpreter_stringStackSize - 1] = var7.activity;
						Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = var7.location;
						Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = var7.population;
						Interpreter.Interpreter_stringStack[++ChatChannel.Interpreter_stringStackSize - 1] = var7.host;
					} else {
						Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = -1;
						Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = 0;
						Interpreter.Interpreter_stringStack[++ChatChannel.Interpreter_stringStackSize - 1] = "";
						Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = 0;
						Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = 0;
						Interpreter.Interpreter_stringStack[++ChatChannel.Interpreter_stringStackSize - 1] = "";
					}

					return 1;
				} else if (var0 == ScriptOpcodes.WORLDLIST_SORT) {
					class295.Interpreter_intStackSize -= 4;
					var3 = Interpreter.Interpreter_intStack[class295.Interpreter_intStackSize];
					boolean var10 = Interpreter.Interpreter_intStack[class295.Interpreter_intStackSize + 1] == 1;
					var8 = Interpreter.Interpreter_intStack[class295.Interpreter_intStackSize + 2];
					boolean var6 = Interpreter.Interpreter_intStack[class295.Interpreter_intStackSize + 3] == 1;
					FriendsList.sortWorldList(var3, var10, var8, var6);
					return 1;
				} else if (var0 != 6511) {
					if (var0 == ScriptOpcodes.SETFOLLOWEROPSLOWPRIORITY) {
						Client.followerOpsLowPriority = Interpreter.Interpreter_intStack[--class295.Interpreter_intStackSize] == 1;
						return 1;
					} else {
						int var4;
						ParamComposition var5;
						if (var0 == ScriptOpcodes.NC_PARAM) {
							class295.Interpreter_intStackSize -= 2;
							var3 = Interpreter.Interpreter_intStack[class295.Interpreter_intStackSize];
							var4 = Interpreter.Interpreter_intStack[class295.Interpreter_intStackSize + 1];
							var5 = class182.getParamDefinition(var4);
							if (var5.isString()) {
								Interpreter.Interpreter_stringStack[++ChatChannel.Interpreter_stringStackSize - 1] = class9.getNpcDefinition(var3).getStringParam(var4, var5.defaultStr);
							} else {
								Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = class9.getNpcDefinition(var3).getIntParam(var4, var5.defaultInt);
							}

							return 1;
						} else if (var0 == ScriptOpcodes.LC_PARAM) {
							class295.Interpreter_intStackSize -= 2;
							var3 = Interpreter.Interpreter_intStack[class295.Interpreter_intStackSize];
							var4 = Interpreter.Interpreter_intStack[class295.Interpreter_intStackSize + 1];
							var5 = class182.getParamDefinition(var4);
							if (var5.isString()) {
								Interpreter.Interpreter_stringStack[++ChatChannel.Interpreter_stringStackSize - 1] = class116.getObjectDefinition(var3).getStringParam(var4, var5.defaultStr);
							} else {
								Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = class116.getObjectDefinition(var3).getIntParam(var4, var5.defaultInt);
							}

							return 1;
						} else if (var0 == ScriptOpcodes.OC_PARAM) {
							class295.Interpreter_intStackSize -= 2;
							var3 = Interpreter.Interpreter_intStack[class295.Interpreter_intStackSize];
							var4 = Interpreter.Interpreter_intStack[class295.Interpreter_intStackSize + 1];
							var5 = class182.getParamDefinition(var4);
							if (var5.isString()) {
								Interpreter.Interpreter_stringStack[++ChatChannel.Interpreter_stringStackSize - 1] = class67.ItemDefinition_get(var3).getStringParam(var4, var5.defaultStr);
							} else {
								Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = class67.ItemDefinition_get(var3).getIntParam(var4, var5.defaultInt);
							}

							return 1;
						} else if (var0 == ScriptOpcodes.STRUCT_PARAM) {
							class295.Interpreter_intStackSize -= 2;
							var3 = Interpreter.Interpreter_intStack[class295.Interpreter_intStackSize];
							var4 = Interpreter.Interpreter_intStack[class295.Interpreter_intStackSize + 1];
							var5 = class182.getParamDefinition(var4);
							if (var5.isString()) {
								Interpreter.Interpreter_stringStack[++ChatChannel.Interpreter_stringStackSize - 1] = class114.StructDefinition_getStructDefinition(var3).getStringParam(var4, var5.defaultStr);
							} else {
								Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = class114.StructDefinition_getStructDefinition(var3).getIntParam(var4, var5.defaultInt);
							}

							return 1;
						} else if (var0 == ScriptOpcodes.ON_MOBILE) {
							Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = Client.onMobile ? 1 : 0;
							return 1;
						} else if (var0 == ScriptOpcodes.CLIENTTYPE) {
							Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = Client.clientType;
							return 1;
						} else if (var0 == 6520) {
							return 1;
						} else if (var0 == ScriptOpcodes.MOBILE_KEYBOARDHIDE) {
							return 1;
						} else if (var0 == 6522) {
							--ChatChannel.Interpreter_stringStackSize;
							--class295.Interpreter_intStackSize;
							return 1;
						} else if (var0 == 6523) {
							--ChatChannel.Interpreter_stringStackSize;
							--class295.Interpreter_intStackSize;
							return 1;
						} else if (var0 == ScriptOpcodes.MOBILE_BATTERYLEVEL) {
							Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = -1;
							return 1;
						} else if (var0 == ScriptOpcodes.MOBILE_BATTERYCHARGING) {
							Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = 1;
							return 1;
						} else if (var0 == ScriptOpcodes.MOBILE_WIFIAVAILABLE) {
							Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = 1;
							return 1;
						} else if (var0 == 6527) {
							Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = Client.field486;
							return 1;
						} else {
							return 2;
						}
					}
				} else {
					var3 = Interpreter.Interpreter_intStack[--class295.Interpreter_intStackSize];
					if (var3 >= 0 && var3 < World.World_count) {
						var7 = World.World_worlds[var3];
						Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = var7.id;
						Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = var7.properties;
						Interpreter.Interpreter_stringStack[++ChatChannel.Interpreter_stringStackSize - 1] = var7.activity;
						Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = var7.location;
						Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = var7.population;
						Interpreter.Interpreter_stringStack[++ChatChannel.Interpreter_stringStackSize - 1] = var7.host;
					} else {
						Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = -1;
						Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = 0;
						Interpreter.Interpreter_stringStack[++ChatChannel.Interpreter_stringStackSize - 1] = "";
						Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = 0;
						Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = 0;
						Interpreter.Interpreter_stringStack[++ChatChannel.Interpreter_stringStackSize - 1] = "";
					}

					return 1;
				}
			}
		}
	}
}
