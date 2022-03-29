public class PlatformInfo extends Node {
	int field4433;
	boolean field4431;
	int field4428;
	int field4448;
	int field4430;
	int field4443;
	int field4432;
	boolean field4442;
	int field4418;
	int field4435;
	int field4436;
	int field4437;
	String field4438;
	String field4439;
	String field4440;
	String field4441;
	int field4427;
	int field4434;
	int field4444;
	int field4445;
	String field4426;
	String field4447;
	int[] field4446;
	int field4449;
	String field4450;

	PlatformInfo(int var1, boolean var2, int var3, int var4, int var5, int var6, int var7, boolean var8, int var9, int var10, int var11, int var12, String var13, String var14, String var15, String var16, int var17, int var18, int var19, int var20, String var21, String var22, int[] var23, int var24, String var25) {
		this.field4446 = new int[3];
		this.field4433 = var1;
		this.field4431 = var2;
		this.field4428 = var3;
		this.field4448 = var4;
		this.field4430 = var5;
		this.field4443 = var6;
		this.field4432 = var7;
		this.field4442 = var8;
		this.field4418 = var9;
		this.field4435 = var10;
		this.field4436 = var11;
		this.field4437 = var12;
		this.field4438 = var13;
		this.field4439 = var14;
		this.field4440 = var15;
		this.field4441 = var16;
		this.field4427 = var17;
		this.field4434 = var18;
		this.field4444 = var19;
		this.field4445 = var20;
		this.field4426 = var21;
		this.field4447 = var22;
		this.field4446 = var23;
		this.field4449 = var24;
		this.field4450 = var25;
	}

	public void write(Buffer var1) {
		var1.writeByte(8);
		var1.writeByte(this.field4433);
		var1.writeByte(this.field4431 ? 1 : 0);
		var1.writeShort(this.field4428);
		var1.writeByte(this.field4448);
		var1.writeByte(this.field4430);
		var1.writeByte(this.field4443);
		var1.writeByte(this.field4432);
		var1.writeByte(this.field4442 ? 1 : 0);
		var1.writeShort(this.field4418);
		var1.writeByte(this.field4435);
		var1.writeMedium(this.field4436);
		var1.writeShort(this.field4437);
		var1.writeStringCp1252NullCircumfixed(this.field4438);
		var1.writeStringCp1252NullCircumfixed(this.field4439);
		var1.writeStringCp1252NullCircumfixed(this.field4440);
		var1.writeStringCp1252NullCircumfixed(this.field4441);
		var1.writeByte(this.field4434);
		var1.writeShort(this.field4427);
		var1.writeStringCp1252NullCircumfixed(this.field4426);
		var1.writeStringCp1252NullCircumfixed(this.field4447);
		var1.writeByte(this.field4444);
		var1.writeByte(this.field4445);

		for (int var2 = 0; var2 < this.field4446.length; ++var2) {
			var1.writeInt(this.field4446[var2]);
		}

		var1.writeInt(this.field4449);
		var1.writeStringCp1252NullCircumfixed(this.field4450);
	}

	public int size() {
		byte var1 = 39;
		String var4 = this.field4438;
		int var3 = var4.length() + 2;
		int var23 = var1 + var3;
		String var7 = this.field4439;
		int var6 = var7.length() + 2;
		var23 += var6;
		String var10 = this.field4440;
		int var9 = var10.length() + 2;
		var23 += var9;
		String var13 = this.field4441;
		int var12 = var13.length() + 2;
		var23 += var12;
		String var16 = this.field4426;
		int var15 = var16.length() + 2;
		var23 += var15;
		String var19 = this.field4447;
		int var18 = var19.length() + 2;
		var23 += var18;
		String var22 = this.field4450;
		int var21 = var22.length() + 2;
		var23 += var21;
		return var23;
	}
}
