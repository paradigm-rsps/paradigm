import javax.imageio.ImageIO;

public class class28 {
	static {
		ImageIO.setUseCache(false);
	}

	static SpritePixels method423(int var0, int var1, int var2) {
		return (SpritePixels)WorldMapRegion.WorldMapRegion_cachedSprites.get(class65.method1867(var0, var1, var2));
	}

	static final void method424(int var0) {
		var0 = Math.min(Math.max(var0, 0), 127);
		Interpreter.clientPreferences.updateSoundEffectVolume(var0);
	}
}
