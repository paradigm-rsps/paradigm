import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;

public class Login {
	static boolean clearLoginScreen;
	static int xPadding;
	static IndexedSprite field882;
	static IndexedSprite field913;
	static SpritePixels leftTitleSprite;
	static int[] Tiles_saturation;
	static LoginScreenAnimation loginScreenRunesAnimation;
	static int loginBoxX;
	static int Login_loadingPercent;
	static String Login_loadingText;
	static int field892;
	static int field897;
	static int loginIndex;
	static String Login_response0;
	static String Login_response1;
	static String Login_response2;
	static String Login_response3;
	static String Login_username;
	static String Login_password;
	static StudioGame field917;
	static int field899;
	static String[] field900;
	static boolean field901;
	static boolean field902;
	static boolean field903;
	static int currentLoginField;
	static boolean worldSelectOpen;
	static int hoveredWorldIndex;
	static int worldSelectPage;
	static int worldSelectPagesCount;
	static long field911;
	static long field912;
	static String[] field881;
	static String[] field914;

	static {
		xPadding = 0;
		loginBoxX = xPadding + 202;
		Login_loadingPercent = 10;
		Login_loadingText = "";
		field892 = -1;
		field897 = 1;
		loginIndex = 0;
		Login_response0 = "";
		Login_response1 = "";
		Login_response2 = "";
		Login_response3 = "";
		Login_username = "";
		Login_password = "";
		field899 = 0;
		field900 = new String[8];
		field901 = false;
		field902 = false;
		field903 = true;
		currentLoginField = 0;
		worldSelectOpen = false;
		hoveredWorldIndex = -1;
		worldSelectPage = 0;
		worldSelectPagesCount = 0;
		new DecimalFormat("##0.00");
		new class120();
		field911 = -1L;
		field912 = -1L;
		field881 = new String[]{"title.jpg"};
		field914 = new String[]{"logo", "logo_deadman_mode", "logo_seasonal_mode", "titlebox", "titlebutton", "titlebutton_large", "play_now_text", "titlebutton_wide42,1", "runes", "title_mute", "options_radio_buttons,0", "options_radio_buttons,2", "options_radio_buttons,4", "options_radio_buttons,6", "sl_back", "sl_flags", "sl_arrows", "sl_stars", "sl_button"};
	}

	static void addGameMessage(int var0, String var1, String var2) {
		class6.addChatMessage(var0, var1, var2, null);
	}

	public static void method1944(AbstractArchive var0, AbstractArchive var1, boolean var2, Font var3) {
		WorldMapID.ItemDefinition_archive = var0;
		GameEngine.ItemDefinition_modelArchive = var1;
		HealthBar.ItemDefinition_inMembersWorld = var2;
		ArchiveLoader.ItemDefinition_fileCount = WorldMapID.ItemDefinition_archive.getGroupFileCount(10);
		class239.ItemDefinition_fontPlain11 = var3;
	}

	static boolean method1945(String var0) {
		if (var0 == null) {
			return false;
		} else {
			try {
				new URL(var0);
				return true;
			} catch (MalformedURLException var2) {
				return false;
			}
		}
	}

	static int method1883(int var0, int var1) {
		ItemContainer var2 = (ItemContainer)ItemContainer.itemContainers.get(var0);
		if (var2 == null) {
			return 0;
		} else if (var1 == -1) {
			return 0;
		} else {
			int var3 = 0;

			for (int var4 = 0; var4 < var2.quantities.length; ++var4) {
				if (var2.ids[var4] == var1) {
					var3 += var2.quantities[var4];
				}
			}

			return var3;
		}
	}

	public static int method1894(int var0) {
		--var0;
		var0 |= var0 >>> 1;
		var0 |= var0 >>> 2;
		var0 |= var0 >>> 4;
		var0 |= var0 >>> 8;
		var0 |= var0 >>> 16;
		return var0 + 1;
	}
}
