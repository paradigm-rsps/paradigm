public final class FloorDecoration {
	static Widget dragInventoryWidget;
	int z;
	int x;
	int y;
	public Renderable renderable;
	public long tag;
	int flags;

	FloorDecoration() {
	}

	static World worldListStart() {
		World.World_listCount = 0;
		return WallDecoration.getNextWorldListWorld();
	}

	static final boolean method3980(Model var0, int var1, int var2, int var3) {
		if (!Client.method1198()) {
			return false;
		} else {
			int var4;
			int var5;
			int var6;
			int var7;
			int var10;
			int var11;
			int var12;
			int var15;
			int var16;
			if (!ViewportMouse.ViewportMouse_false0) {
				var4 = Scene.Scene_cameraPitchSine;
				var5 = Scene.Scene_cameraPitchCosine;
				var6 = Scene.Scene_cameraYawSine;
				var7 = Scene.Scene_cameraYawCosine;
				byte var8 = 50;
				short var9 = 3500;
				var10 = (ViewportMouse.ViewportMouse_x - Rasterizer3D.Rasterizer3D_clipMidX) * var8 / Rasterizer3D.Rasterizer3D_zoom;
				var11 = (ViewportMouse.ViewportMouse_y - Rasterizer3D.Rasterizer3D_clipMidY) * var8 / Rasterizer3D.Rasterizer3D_zoom;
				var12 = (ViewportMouse.ViewportMouse_x - Rasterizer3D.Rasterizer3D_clipMidX) * var9 / Rasterizer3D.Rasterizer3D_zoom;
				int var13 = (ViewportMouse.ViewportMouse_y - Rasterizer3D.Rasterizer3D_clipMidY) * var9 / Rasterizer3D.Rasterizer3D_zoom;
				int var14 = Rasterizer3D.method4019(var11, var8, var5, var4);
				var15 = Rasterizer3D.method4020(var11, var8, var5, var4);
				var11 = var14;
				var14 = Rasterizer3D.method4019(var13, var9, var5, var4);
				var16 = Rasterizer3D.method4020(var13, var9, var5, var4);
				var13 = var14;
				var14 = Rasterizer3D.method4017(var10, var15, var7, var6);
				var15 = Rasterizer3D.method4018(var10, var15, var7, var6);
				var10 = var14;
				var14 = Rasterizer3D.method4017(var12, var16, var7, var6);
				var16 = Rasterizer3D.method4018(var12, var16, var7, var6);
				UserComparator7.field1354 = (var14 + var10) / 2;
				class21.field108 = (var11 + var13) / 2;
				ViewportMouse.field2577 = (var15 + var16) / 2;
				ViewportMouse.field2581 = (var14 - var10) / 2;
				ViewportMouse.field2579 = (var13 - var11) / 2;
				class124.field1520 = (var16 - var15) / 2;
				HealthBar.field1251 = Math.abs(ViewportMouse.field2581);
				AbstractUserComparator.field4268 = Math.abs(ViewportMouse.field2579);
				TaskHandler.field1722 = Math.abs(class124.field1520);
			}

			var4 = var0.xMid + var1;
			var5 = var2 + var0.yMid;
			var6 = var3 + var0.zMid;
			var7 = var0.xMidOffset;
			var15 = var0.yMidOffset;
			var16 = var0.zMidOffset;
			var10 = UserComparator7.field1354 - var4;
			var11 = class21.field108 - var5;
			var12 = ViewportMouse.field2577 - var6;
			if (Math.abs(var10) > var7 + HealthBar.field1251) {
				return false;
			} else if (Math.abs(var11) > var15 + AbstractUserComparator.field4268) {
				return false;
			} else if (Math.abs(var12) > var16 + TaskHandler.field1722) {
				return false;
			} else if (Math.abs(var12 * ViewportMouse.field2579 - var11 * class124.field1520) > var16 * AbstractUserComparator.field4268 + var15 * TaskHandler.field1722) {
				return false;
			} else if (Math.abs(var10 * class124.field1520 - var12 * ViewportMouse.field2581) > var16 * HealthBar.field1251 + var7 * TaskHandler.field1722) {
				return false;
			} else {
				return Math.abs(var11 * ViewportMouse.field2581 - var10 * ViewportMouse.field2579) <= var15 * HealthBar.field1251 + var7 * AbstractUserComparator.field4268;
			}
		}
	}

	static boolean method3981() {
		return (Client.drawPlayerNames & 1) != 0;
	}
}
