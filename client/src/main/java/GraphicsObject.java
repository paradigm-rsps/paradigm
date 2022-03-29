import java.util.Date;

public class GraphicsObject extends Renderable {
	int id;
	int cycleStart;
	int plane;
	int x;
	int y;
	int z;
	SequenceDefinition sequenceDefinition;
	int frame;
	int frameCycle;
	boolean isFinished;

	GraphicsObject(int var1, int var2, int var3, int var4, int var5, int var6, int var7) {
		this.frame = 0;
		this.frameCycle = 0;
		this.isFinished = false;
		this.id = var1;
		this.plane = var2;
		this.x = var3;
		this.y = var4;
		this.z = var5;
		this.cycleStart = var7 + var6;
		int var8 = class6.SpotAnimationDefinition_get(this.id).sequence;
		if (var8 != -1) {
			this.isFinished = false;
			this.sequenceDefinition = ItemContainer.SequenceDefinition_get(var8);
		} else {
			this.isFinished = true;
		}

	}

	final void advance(int var1) {
		if (!this.isFinished) {
			this.frameCycle += var1;
			if (!this.sequenceDefinition.isCachedModelIdSet()) {
				while (this.frameCycle > this.sequenceDefinition.frameLengths[this.frame]) {
					this.frameCycle -= this.sequenceDefinition.frameLengths[this.frame];
					++this.frame;
					if (this.frame >= this.sequenceDefinition.frameIds.length) {
						this.isFinished = true;
						break;
					}
				}
			} else {
				this.frame += var1;
				if (this.frame >= this.sequenceDefinition.method3827()) {
					this.isFinished = true;
				}
			}

		}
	}

	protected final Model getModel() {
		SpotAnimationDefinition var1 = class6.SpotAnimationDefinition_get(this.id);
		Model var2;
		if (!this.isFinished) {
			var2 = var1.getModel(this.frame);
		} else {
			var2 = var1.getModel(-1);
		}

		return var2;
	}

	static class83[] method1866() {
		return new class83[]{class83.field1075, class83.field1078, class83.field1077, class83.field1074, class83.field1081, class83.field1076};
	}

	static boolean method1864(Date var0) {
		Date var1 = class250.method5137();
		return var0.after(var1);
	}

	static int method1865(int var0) {
		return (int)((Math.log(var0) / Interpreter.field842 - 7.0D) * 256.0D);
	}

	static final void method1860(Player var0, int var1, int var2, class193 var3) {
		int var4 = var0.pathX[0];
		int var5 = var0.pathY[0];
		int var6 = var0.transformedSize();
		if (var4 >= var6 && var4 < 104 - var6 && var5 >= var6 && var5 < 104 - var6) {
			if (var1 >= var6 && var1 < 104 - var6 && var2 >= var6 && var2 < 104 - var6) {
				int var7 = ArchiveLoader.method2075(var4, var5, var0.transformedSize(), WorldMapSectionType.method4954(var1, var2), Client.collisionMaps[var0.plane], true, Client.field743, Client.field670);
				if (var7 >= 1) {
					for (int var8 = 0; var8 < var7 - 1; ++var8) {
						var0.method2149(Client.field743[var8], Client.field670[var8], var3);
					}

				}
			}
		}
	}

	public GraphicsObject() {
	}
}
