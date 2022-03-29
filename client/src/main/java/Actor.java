public abstract class Actor extends Renderable {
	int x;
	int y;
	int rotation;
	boolean isWalking;
	int field1145;
	int playerCycle;
	int idleSequence;
	int turnLeftSequence;
	int turnRightSequence;
	int walkSequence;
	int walkBackSequence;
	int walkLeftSequence;
	int walkRightSequence;
	int runSequence;
	String overheadText;
	boolean isAutoChatting;
	boolean field1152;
	int overheadTextCyclesRemaining;
	int overheadTextColor;
	int overheadTextEffect;
	byte hitSplatCount;
	int[] hitSplatTypes;
	int[] hitSplatValues;
	int[] hitSplatCycles;
	int[] hitSplatTypes2;
	int[] hitSplatValues2;
	IterableNodeDeque healthBars;
	int targetIndex;
	boolean false0;
	int field1160;
	boolean field1183;
	int movementSequence;
	int movementFrame;
	int movementFrameCycle;
	int sequence;
	int sequenceFrame;
	int sequenceFrameCycle;
	int sequenceDelay;
	int field1169;
	int spotAnimation;
	int spotAnimationFrame;
	int spotAnimationFrameCycle;
	int field1173;
	int spotAnimationHeight;
	int field1175;
	int field1176;
	int field1177;
	int field1178;
	int field1179;
	int field1180;
	int field1181;
	int npcCycle;
	int defaultHeight;
	int field1133;
	int field1185;
	byte field1146;
	byte field1187;
	byte field1188;
	byte field1189;
	int orientation;
	int field1131;
	int field1192;
	int pathLength;
	int[] pathX;
	int[] pathY;
	class193[] pathTraversed;
	int field1197;
	int field1134;
	int field1161;

	Actor() {
		this.isWalking = false;
		this.field1145 = 1;
		this.idleSequence = -1;
		this.turnLeftSequence = -1;
		this.turnRightSequence = -1;
		this.walkSequence = -1;
		this.walkBackSequence = -1;
		this.walkLeftSequence = -1;
		this.walkRightSequence = -1;
		this.runSequence = -1;
		this.overheadText = null;
		this.field1152 = false;
		this.overheadTextCyclesRemaining = 100;
		this.overheadTextColor = 0;
		this.overheadTextEffect = 0;
		this.hitSplatCount = 0;
		this.hitSplatTypes = new int[4];
		this.hitSplatValues = new int[4];
		this.hitSplatCycles = new int[4];
		this.hitSplatTypes2 = new int[4];
		this.hitSplatValues2 = new int[4];
		this.healthBars = new IterableNodeDeque();
		this.targetIndex = -1;
		this.false0 = false;
		this.field1160 = -1;
		this.movementSequence = -1;
		this.movementFrame = 0;
		this.movementFrameCycle = 0;
		this.sequence = -1;
		this.sequenceFrame = 0;
		this.sequenceFrameCycle = 0;
		this.sequenceDelay = 0;
		this.field1169 = 0;
		this.spotAnimation = -1;
		this.spotAnimationFrame = 0;
		this.spotAnimationFrameCycle = 0;
		this.npcCycle = 0;
		this.defaultHeight = 200;
		this.field1133 = -1;
		this.field1185 = -1;
		this.field1131 = 0;
		this.field1192 = 32;
		this.pathLength = 0;
		this.pathX = new int[10];
		this.pathY = new int[10];
		this.pathTraversed = new class193[10];
		this.field1197 = 0;
		this.field1134 = 0;
		this.field1161 = -1;
	}

	boolean isVisible() {
		return false;
	}

	final void method2205() {
		this.pathLength = 0;
		this.field1134 = 0;
	}

	final void addHitSplat(int var1, int var2, int var3, int var4, int var5, int var6) {
		boolean var7 = true;
		boolean var8 = true;

		int var9;
		for (var9 = 0; var9 < 4; ++var9) {
			if (this.hitSplatCycles[var9] > var5) {
				var7 = false;
			} else {
				var8 = false;
			}
		}

		var9 = -1;
		int var10 = -1;
		int var11 = 0;
		if (var1 >= 0) {
			HitSplatDefinition var12 = GameEngine.method589(var1);
			var10 = var12.field2020;
			var11 = var12.field2025;
		}

		int var14;
		if (var8) {
			if (var10 == -1) {
				return;
			}

			var9 = 0;
			var14 = 0;
			if (var10 == 0) {
				var14 = this.hitSplatCycles[0];
			} else if (var10 == 1) {
				var14 = this.hitSplatValues[0];
			}

			for (int var13 = 1; var13 < 4; ++var13) {
				if (var10 == 0) {
					if (this.hitSplatCycles[var13] < var14) {
						var9 = var13;
						var14 = this.hitSplatCycles[var13];
					}
				} else if (var10 == 1 && this.hitSplatValues[var13] < var14) {
					var9 = var13;
					var14 = this.hitSplatValues[var13];
				}
			}

			if (var10 == 1 && var14 >= var2) {
				return;
			}
		} else {
			if (var7) {
				this.hitSplatCount = 0;
			}

			for (var14 = 0; var14 < 4; ++var14) {
				byte var15 = this.hitSplatCount;
				this.hitSplatCount = (byte)((this.hitSplatCount + 1) % 4);
				if (this.hitSplatCycles[var15] <= var5) {
					var9 = var15;
					break;
				}
			}
		}

		if (var9 >= 0) {
			this.hitSplatTypes[var9] = var1;
			this.hitSplatValues[var9] = var2;
			this.hitSplatTypes2[var9] = var3;
			this.hitSplatValues2[var9] = var4;
			this.hitSplatCycles[var9] = var5 + var11 + var6;
		}
	}

	final void addHealthBar(int var1, int var2, int var3, int var4, int var5, int var6) {
		HealthBarDefinition var7 = HitSplatDefinition.method3619(var1);
		HealthBar var8 = null;
		HealthBar var9 = null;
		int var10 = var7.int2;
		int var11 = 0;

		HealthBar var12;
		for (var12 = (HealthBar)this.healthBars.last(); var12 != null; var12 = (HealthBar)this.healthBars.previous()) {
			++var11;
			if (var12.definition.field1871 == var7.field1871) {
				var12.put(var2 + var4, var5, var6, var3);
				return;
			}

			if (var12.definition.int1 <= var7.int1) {
				var8 = var12;
			}

			if (var12.definition.int2 > var10) {
				var9 = var12;
				var10 = var12.definition.int2;
			}
		}

		if (var9 != null || var11 < 4) {
			var12 = new HealthBar(var7);
			if (var8 == null) {
				this.healthBars.addLast(var12);
			} else {
				IterableNodeDeque.IterableNodeDeque_addBefore(var12, var8);
			}

			var12.put(var2 + var4, var5, var6, var3);
			if (var11 >= 4) {
				var9.remove();
			}

		}
	}

	final void removeHealthBar(int var1) {
		HealthBarDefinition var2 = HitSplatDefinition.method3619(var1);

		for (HealthBar var3 = (HealthBar)this.healthBars.last(); var3 != null; var3 = (HealthBar)this.healthBars.previous()) {
			if (var2 == var3.definition) {
				var3.remove();
				return;
			}
		}

	}
}
