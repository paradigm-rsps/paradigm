public final class Player extends Actor {
	static Widget mousedOverWidgetIf1;
	Username username;
	PlayerComposition appearance;
	int headIconPk;
	int headIconPrayer;
	String[] actions;
	int combatLevel;
	int skillLevel;
	int tileHeight;
	int animationCycleStart;
	int animationCycleEnd;
	int field1084;
	int tileHeight2;
	int field1095;
	Model model0;
	int minX;
	int minY;
	int maxX;
	int maxY;
	boolean isUnanimated;
	int team;
	boolean isHidden;
	int plane;
	int index;
	TriBool isFriendTriBool;
	TriBool isInFriendsChat;
	TriBool isInClanChat;
	boolean field1109;
	int tileX;
	int tileY;

	Player() {
		this.headIconPk = -1;
		this.headIconPrayer = -1;
		this.actions = new String[3];

		for (int var1 = 0; var1 < 3; ++var1) {
			this.actions[var1] = "";
		}

		this.combatLevel = 0;
		this.skillLevel = 0;
		this.animationCycleStart = 0;
		this.animationCycleEnd = 0;
		this.isUnanimated = false;
		this.team = 0;
		this.isHidden = false;
		this.isFriendTriBool = TriBool.TriBool_unknown;
		this.isInFriendsChat = TriBool.TriBool_unknown;
		this.isInClanChat = TriBool.TriBool_unknown;
		this.field1109 = false;
	}

	final void read(Buffer var1) {
		var1.offset = 0;
		int var2 = var1.readUnsignedByte();
		this.headIconPk = var1.readByte();
		this.headIconPrayer = var1.readByte();
		int var3 = -1;
		this.team = 0;
		int[] var4 = new int[12];

		int var6;
		int var7;
		int var8;
		for (int var5 = 0; var5 < 12; ++var5) {
			var6 = var1.readUnsignedByte();
			if (var6 == 0) {
				var4[var5] = 0;
			} else {
				var7 = var1.readUnsignedByte();
				var4[var5] = var7 + (var6 << 8);
				if (var5 == 0 && var4[0] == 65535) {
					var3 = var1.readUnsignedShort();
					break;
				}

				if (var4[var5] >= 512) {
					var8 = class67.ItemDefinition_get(var4[var5] - 512).team;
					if (var8 != 0) {
						this.team = var8;
					}
				}
			}
		}

		int[] var24 = new int[5];

		for (var6 = 0; var6 < 5; ++var6) {
			var7 = var1.readUnsignedByte();
			if (var7 < 0 || var7 >= class20.field102[var6].length) {
				var7 = 0;
			}

			var24[var6] = var7;
		}

		super.idleSequence = var1.readUnsignedShort();
		if (super.idleSequence == 65535) {
			super.idleSequence = -1;
		}

		super.turnLeftSequence = var1.readUnsignedShort();
		if (super.turnLeftSequence == 65535) {
			super.turnLeftSequence = -1;
		}

		super.turnRightSequence = super.turnLeftSequence;
		super.walkSequence = var1.readUnsignedShort();
		if (super.walkSequence == 65535) {
			super.walkSequence = -1;
		}

		super.walkBackSequence = var1.readUnsignedShort();
		if (super.walkBackSequence == 65535) {
			super.walkBackSequence = -1;
		}

		super.walkLeftSequence = var1.readUnsignedShort();
		if (super.walkLeftSequence == 65535) {
			super.walkLeftSequence = -1;
		}

		super.walkRightSequence = var1.readUnsignedShort();
		if (super.walkRightSequence == 65535) {
			super.walkRightSequence = -1;
		}

		super.runSequence = var1.readUnsignedShort();
		if (super.runSequence == 65535) {
			super.runSequence = -1;
		}

		this.username = new Username(var1.readStringCp1252NullTerminated(), class83.loginType);
		this.clearIsFriend();
		this.clearIsInFriendsChat();
		this.method2143();
		if (this == class19.localPlayer) {
			RunException.localPlayerName = this.username.getName();
		}

		this.combatLevel = var1.readUnsignedByte();
		this.skillLevel = var1.readUnsignedShort();
		this.isHidden = var1.readUnsignedByte() == 1;
		if (Client.gameBuild == 0 && Client.staffModLevel >= 2) {
			this.isHidden = false;
		}

		class168[] var25 = null;
		boolean var26 = false;
		if (var1.offset < var1.array.length) {
			var8 = var1.readUnsignedShort();
			var26 = (var8 >> 15 & 1) == 1;
			if (var8 > 0 && var8 != 32768) {
				var25 = new class168[12];

				for (int var9 = 0; var9 < 12; ++var9) {
					int var10 = var8 >> 12 - var9 & 1;
					if (var10 == 1) {
						int var14 = var4[var9] - 512;
						int var15 = var1.readUnsignedByte();
						boolean var16 = (var15 & 1) != 0;
						boolean var17 = (var15 & 2) != 0;
						class168 var18 = new class168(var14);
						int var19;
						int[] var20;
						boolean var21;
						int var22;
						short var23;
						if (var16) {
							var19 = var1.readUnsignedByte();
							var20 = new int[]{var19 & 15, var19 >> 4 & 15};
							var21 = var18.field1804 != null && var20.length == var18.field1804.length;

							for (var22 = 0; var22 < 2; ++var22) {
								if (var20[var22] != 15) {
									var23 = (short)var1.readUnsignedShort();
									if (var21) {
										var18.field1804[var20[var22]] = var23;
									}
								}
							}
						}

						if (var17) {
							var19 = var1.readUnsignedByte();
							var20 = new int[]{var19 & 15, var19 >> 4 & 15};
							var21 = var18.field1805 != null && var20.length == var18.field1805.length;

							for (var22 = 0; var22 < 2; ++var22) {
								if (var20[var22] != 15) {
									var23 = (short)var1.readUnsignedShort();
									if (var21) {
										var18.field1805[var20[var22]] = var23;
									}
								}
							}
						}

						var25[var9] = var18;
					}
				}
			}
		}

		if (this.appearance == null) {
			this.appearance = new PlayerComposition();
		}

		this.appearance.method5604(var4, var25, var26, var24, var2 == 1, var3);
	}

	protected final Model getModel() {
		if (this.appearance == null) {
			return null;
		} else {
			SequenceDefinition var1 = super.sequence != -1 && super.sequenceDelay == 0 ? ItemContainer.SequenceDefinition_get(super.sequence) : null;
			SequenceDefinition var2 = super.movementSequence == -1 || this.isUnanimated || super.movementSequence == super.idleSequence && var1 != null ? null : ItemContainer.SequenceDefinition_get(super.movementSequence);
			Model var3 = this.appearance.getModel(var1, super.sequenceFrame, var2, super.movementFrame);
			if (var3 == null) {
				return null;
			} else {
				var3.calculateBoundsCylinder();
				super.defaultHeight = var3.height;
				Model var4;
				Model[] var5;
				if (!this.isUnanimated && super.spotAnimation != -1 && super.spotAnimationFrame != -1) {
					var4 = class6.SpotAnimationDefinition_get(super.spotAnimation).getModel(super.spotAnimationFrame);
					if (var4 != null) {
						var4.offsetBy(0, -super.spotAnimationHeight, 0);
						var5 = new Model[]{var3, var4};
						var3 = new Model(var5, 2);
					}
				}

				if (!this.isUnanimated && this.model0 != null) {
					if (Client.cycle >= this.animationCycleEnd) {
						this.model0 = null;
					}

					if (Client.cycle >= this.animationCycleStart && Client.cycle < this.animationCycleEnd) {
						var4 = this.model0;
						var4.offsetBy(this.field1084 - super.x, this.tileHeight2 - this.tileHeight, this.field1095 - super.y);
						if (super.orientation == 512) {
							var4.rotateY90Ccw();
							var4.rotateY90Ccw();
							var4.rotateY90Ccw();
						} else if (super.orientation == 1024) {
							var4.rotateY90Ccw();
							var4.rotateY90Ccw();
						} else if (super.orientation == 1536) {
							var4.rotateY90Ccw();
						}

						var5 = new Model[]{var3, var4};
						var3 = new Model(var5, 2);
						if (super.orientation == 512) {
							var4.rotateY90Ccw();
						} else if (super.orientation == 1024) {
							var4.rotateY90Ccw();
							var4.rotateY90Ccw();
						} else if (super.orientation == 1536) {
							var4.rotateY90Ccw();
							var4.rotateY90Ccw();
							var4.rotateY90Ccw();
						}

						var4.offsetBy(super.x - this.field1084, this.tileHeight - this.tileHeight2, super.y - this.field1095);
					}
				}

				var3.isSingleTile = true;
				if (super.field1189 != 0 && Client.cycle >= super.field1133 && Client.cycle < super.field1185) {
					var3.overrideHue = super.field1146;
					var3.overrideSaturation = super.field1187;
					var3.overrideLuminance = super.field1188;
					var3.overrideAmount = super.field1189;
				} else {
					var3.overrideAmount = 0;
				}

				return var3;
			}
		}
	}

	boolean isFriend() {
		if (this.isFriendTriBool == TriBool.TriBool_unknown) {
			this.checkIsFriend();
		}

		return this.isFriendTriBool == TriBool.TriBool_true;
	}

	void clearIsFriend() {
		this.isFriendTriBool = TriBool.TriBool_unknown;
	}

	void checkIsFriend() {
		this.isFriendTriBool = class155.friendSystem.isFriendAndHasWorld(this.username) ? TriBool.TriBool_true : TriBool.TriBool_false;
	}

	boolean isFriendsChatMember() {
		if (this.isInFriendsChat == TriBool.TriBool_unknown) {
			this.updateIsInFriendsChat();
		}

		return this.isInFriendsChat == TriBool.TriBool_true;
	}

	void clearIsInFriendsChat() {
		this.isInFriendsChat = TriBool.TriBool_unknown;
	}

	void updateIsInFriendsChat() {
		this.isInFriendsChat = Statics1.friendsChat != null && Statics1.friendsChat.contains(this.username) ? TriBool.TriBool_true : TriBool.TriBool_false;
	}

	void updateIsInClanChat() {
		for (int var1 = 0; var1 < 3; ++var1) {
			if (Client.currentClanSettings[var1] != null && Client.currentClanSettings[var1].method2979(this.username.getName()) != -1 && var1 != 2) {
				this.isInClanChat = TriBool.TriBool_true;
				return;
			}
		}

		this.isInClanChat = TriBool.TriBool_false;
	}

	void method2143() {
		this.isInClanChat = TriBool.TriBool_unknown;
	}

	boolean isClanMember() {
		if (this.isInClanChat == TriBool.TriBool_unknown) {
			this.updateIsInClanChat();
		}

		return this.isInClanChat == TriBool.TriBool_true;
	}

	int transformedSize() {
		return this.appearance != null && this.appearance.npcTransformId != -1 ? class9.getNpcDefinition(this.appearance.npcTransformId).size : 1;
	}

	final void method2144(int var1, int var2, class193 var3) {
		if (super.sequence != -1 && ItemContainer.SequenceDefinition_get(super.sequence).field2171 == 1) {
			super.sequence = -1;
		}

		super.field1160 = -1;
		if (var1 >= 0 && var1 < 104 && var2 >= 0 && var2 < 104) {
			if (super.pathX[0] >= 0 && super.pathX[0] < 104 && super.pathY[0] >= 0 && super.pathY[0] < 104) {
				if (var3 == class193.field2191) {
					GraphicsObject.method1860(this, var1, var2, class193.field2191);
				}

				this.method2149(var1, var2, var3);
			} else {
				this.resetPath(var1, var2);
			}
		} else {
			this.resetPath(var1, var2);
		}

	}

	void resetPath(int var1, int var2) {
		super.pathLength = 0;
		super.field1134 = 0;
		super.field1197 = 0;
		super.pathX[0] = var1;
		super.pathY[0] = var2;
		int var3 = this.transformedSize();
		super.x = var3 * 64 + super.pathX[0] * 128;
		super.y = var3 * 64 + super.pathY[0] * 128;
	}

	final void method2149(int var1, int var2, class193 var3) {
		if (super.pathLength < 9) {
			++super.pathLength;
		}

		for (int var4 = super.pathLength; var4 > 0; --var4) {
			super.pathX[var4] = super.pathX[var4 - 1];
			super.pathY[var4] = super.pathY[var4 - 1];
			super.pathTraversed[var4] = super.pathTraversed[var4 - 1];
		}

		super.pathX[0] = var1;
		super.pathY[0] = var2;
		super.pathTraversed[0] = var3;
	}

	final boolean isVisible() {
		return this.appearance != null;
	}
}
