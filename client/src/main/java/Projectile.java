public final class Projectile extends Renderable {
	static Bounds field946;
	static int field944;
	int id;
	int plane;
	int sourceX;
	int sourceY;
	int sourceZ;
	int endHeight;
	int cycleStart;
	int cycleEnd;
	int slope;
	int startHeight;
	int targetIndex;
	boolean isMoving;
	double x;
	double y;
	double z;
	double speedX;
	double speedY;
	double speed;
	double speedZ;
	double accelerationZ;
	int yaw;
	int pitch;
	SequenceDefinition sequenceDefinition;
	int frame;
	int frameCycle;

	Projectile(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, int var11) {
		this.isMoving = false;
		this.frame = 0;
		this.frameCycle = 0;
		this.id = var1;
		this.plane = var2;
		this.sourceX = var3;
		this.sourceY = var4;
		this.sourceZ = var5;
		this.cycleStart = var6;
		this.cycleEnd = var7;
		this.slope = var8;
		this.startHeight = var9;
		this.targetIndex = var10;
		this.endHeight = var11;
		this.isMoving = false;
		int var12 = class6.SpotAnimationDefinition_get(this.id).sequence;
		if (var12 != -1) {
			this.sequenceDefinition = ItemContainer.SequenceDefinition_get(var12);
		} else {
			this.sequenceDefinition = null;
		}

	}

	final void setDestination(int var1, int var2, int var3, int var4) {
		double var5;
		if (!this.isMoving) {
			var5 = var1 - this.sourceX;
			double var7 = var2 - this.sourceY;
			double var9 = Math.sqrt(var5 * var5 + var7 * var7);
			this.x = var5 * (double)this.startHeight / var9 + (double)this.sourceX;
			this.y = (double)this.startHeight * var7 / var9 + (double)this.sourceY;
			this.z = this.sourceZ;
		}

		var5 = this.cycleEnd + 1 - var4;
		this.speedX = ((double)var1 - this.x) / var5;
		this.speedY = ((double)var2 - this.y) / var5;
		this.speed = Math.sqrt(this.speedX * this.speedX + this.speedY * this.speedY);
		if (!this.isMoving) {
			this.speedZ = -this.speed * Math.tan(0.02454369D * (double)this.slope);
		}

		this.accelerationZ = 2.0D * ((double)var3 - this.z - var5 * this.speedZ) / (var5 * var5);
	}

	protected final Model getModel() {
		SpotAnimationDefinition var1 = class6.SpotAnimationDefinition_get(this.id);
		Model var2 = var1.getModel(this.frame);
		if (var2 == null) {
			return null;
		} else {
			var2.rotateZ(this.pitch);
			return var2;
		}
	}

	final void advance(int var1) {
		this.isMoving = true;
		this.x += (double)var1 * this.speedX;
		this.y += (double)var1 * this.speedY;
		this.z += (double)var1 * this.speedZ + (double)var1 * (double)var1 * 0.5D * this.accelerationZ;
		this.speedZ += (double)var1 * this.accelerationZ;
		this.yaw = (int)(Math.atan2(this.speedX, this.speedY) * 325.949D) + 1024 & 2047;
		this.pitch = (int)(Math.atan2(this.speedZ, this.speed) * 325.949D) & 2047;
		if (this.sequenceDefinition != null) {
			if (!this.sequenceDefinition.isCachedModelIdSet()) {
				this.frameCycle += var1;

				while (true) {
					do {
						do {
							if (this.frameCycle <= this.sequenceDefinition.frameLengths[this.frame]) {
								return;
							}

							this.frameCycle -= this.sequenceDefinition.frameLengths[this.frame];
							++this.frame;
						} while(this.frame < this.sequenceDefinition.frameIds.length);

						this.frame -= this.sequenceDefinition.frameCount;
					} while(this.frame >= 0 && this.frame < this.sequenceDefinition.frameIds.length);

					this.frame = 0;
				}
			} else {
				this.frame += var1;
				int var2 = this.sequenceDefinition.method3827();
				if (this.frame >= var2) {
					this.frame = var2 - this.sequenceDefinition.frameCount;
				}
			}
		}

	}

	static final void method1957() {
		for (PendingSpawn var0 = (PendingSpawn)Client.pendingSpawns.last(); var0 != null; var0 = (PendingSpawn)Client.pendingSpawns.previous()) {
			if (var0.hitpoints == -1) {
				var0.delay = 0;
				WorldMapRegion.method4795(var0);
			} else {
				var0.remove();
			}
		}

	}
}
