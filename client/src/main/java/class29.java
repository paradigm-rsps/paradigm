import javax.imageio.ImageIO;
import java.applet.Applet;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class class29 {
	public static Applet field168;
	public static String field169;
	static IndexedSprite[] worldSelectStars;
	static long field165;

	static {
		field168 = null;
		field169 = "";
	}

	public static final SpritePixels method433(byte[] var0) {
		BufferedImage var1 = null;

		try {
			var1 = ImageIO.read(new ByteArrayInputStream(var0));
			int var2 = var1.getWidth();
			int var3 = var1.getHeight();
			int[] var4 = new int[var3 * var2];
			PixelGrabber var5 = new PixelGrabber(var1, 0, 0, var2, var3, var4, 0, var2);
			var5.grabPixels();
			return new SpritePixels(var4, var2, var3);
		} catch (IOException var7) {
		} catch (InterruptedException var8) {
		}

		return new SpritePixels(0, 0);
	}

	public static File method431(String var0, String var1, int var2) {
		String var3 = var2 == 0 ? "" : "" + var2;
		AccessFile.JagexCache_locationFile = new File(Statics1.userHomeDirectory, "jagex_cl_" + var0 + "_" + var1 + var3 + ".dat");
		String cacheDir = null;
		String var5 = null;
		boolean var6 = false;
		File var22;
		if (AccessFile.JagexCache_locationFile.exists()) {
			try {
				AccessFile var7 = new AccessFile(AccessFile.JagexCache_locationFile, "rw", 10000L);

				Buffer var8;
				int var9;
				for (var8 = new Buffer((int)var7.length()); var8.offset < var8.array.length; var8.offset += var9) {
					var9 = var7.read(var8.array, var8.offset, var8.array.length - var8.offset);
					if (var9 == -1) {
						throw new IOException();
					}
				}

				var8.offset = 0;
				var9 = var8.readUnsignedByte();
				if (var9 < 1 || var9 > 3) {
					throw new IOException("" + var9);
				}

				int var10 = 0;
				if (var9 > 1) {
					var10 = var8.readUnsignedByte();
				}

				if (var9 <= 2) {
					cacheDir = var8.readStringCp1252NullCircumfixed();
					if (var10 == 1) {
						var5 = var8.readStringCp1252NullCircumfixed();
					}
				} else {
					cacheDir = var8.readCESU8();
					if (var10 == 1) {
						var5 = var8.readCESU8();
					}
				}

				var7.close();
			} catch (IOException var20) {
				var20.printStackTrace();
			}

			if (cacheDir != null) {
				var22 = new File(cacheDir);
				if (!var22.exists()) {
					cacheDir = null;
				}
			}

			if (cacheDir != null) {
				var22 = new File(cacheDir, "test.dat");
				if (!SecureRandomCallable.openFile(var22, true)) {
					cacheDir = null;
				}
			}
		}

		if (cacheDir == null && var2 == 0) {
			label125:
			for (int i = 0; i < WorldMapSection1.cacheSubPaths.length; ++i) {
				for (int j = 0; j < PendingSpawn.cacheParentPaths.length; ++j) {
					File dir = new File(PendingSpawn.cacheParentPaths[j] + WorldMapSection1.cacheSubPaths[i] + File.separatorChar + var0 + File.separatorChar);
					if (dir.exists() && SecureRandomCallable.openFile(new File(dir, "test.dat"), true)) {
						cacheDir = dir.toString();
						var6 = true;
						break label125;
					}
				}
			}
		}

		if (cacheDir == null) {
			cacheDir = Statics1.userHomeDirectory + File.separatorChar + "jagexcache" + var3 + File.separatorChar + var0 + File.separatorChar + var1 + File.separatorChar;
            var6 = true;
		}

		if (var5 != null) {
			File var21 = new File(var5);
			var22 = new File(cacheDir);

			try {
				File[] var23 = var21.listFiles();

				for (int i = 0; i < var23.length; ++i) {
					File var12 = var23[i];
					File var13 = new File(var22, var12.getName());
					boolean var14 = var12.renameTo(var13);
					if (!var14) {
						throw new IOException();
					}
				}
			} catch (Exception var19) {
				var19.printStackTrace();
			}

			var6 = true;
		}

		if (var6) {
			ViewportMouse.method4493(new File(cacheDir), null);
		}

		return new File(cacheDir);
	}

	public static String method425(byte[] var0) {
		int var2 = var0.length;
		StringBuilder var3 = new StringBuilder();

		for (int var4 = 0; var4 < var2 + 0; var4 += 3) {
			int var5 = var0[var4] & 255;
			var3.append(class346.field4147[var5 >>> 2]);
			if (var4 < var2 - 1) {
				int var6 = var0[var4 + 1] & 255;
				var3.append(class346.field4147[(var5 & 3) << 4 | var6 >>> 4]);
				if (var4 < var2 - 2) {
					int var7 = var0[var4 + 2] & 255;
					var3.append(class346.field4147[(var6 & 15) << 2 | var7 >>> 6]).append(class346.field4147[var7 & 63]);
				} else {
					var3.append(class346.field4147[(var6 & 15) << 2]).append("=");
				}
			} else {
				var3.append(class346.field4147[(var5 & 3) << 4]).append("==");
			}
		}

		String var1 = var3.toString();
		return var1;
	}

	static void itemContainerSetItem(int var0, int var1, int var2, int var3) {
		ItemContainer var4 = (ItemContainer)ItemContainer.itemContainers.get(var0);
		if (var4 == null) {
			var4 = new ItemContainer();
			ItemContainer.itemContainers.put(var4, var0);
		}

		if (var4.ids.length <= var1) {
			int[] var5 = new int[var1 + 1];
			int[] var6 = new int[var1 + 1];

			int var7;
			for (var7 = 0; var7 < var4.ids.length; ++var7) {
				var5[var7] = var4.ids[var7];
				var6[var7] = var4.quantities[var7];
			}

			for (var7 = var4.ids.length; var7 < var1; ++var7) {
				var5[var7] = -1;
				var6[var7] = 0;
			}

			var4.ids = var5;
			var4.quantities = var6;
		}

		var4.ids[var1] = var2;
		var4.quantities[var1] = var3;
	}
}
