import java.awt.event.*;

public class MouseHandler implements MouseListener, MouseMotionListener, FocusListener {
	public static MouseHandler MouseHandler_instance;
	public static volatile int MouseHandler_idleCycles;
	public static volatile int MouseHandler_currentButtonVolatile;
	public static volatile int MouseHandler_xVolatile;
	public static volatile int MouseHandler_yVolatile;
	public static volatile long MouseHandler_lastMovedVolatile;
	public static int MouseHandler_currentButton;
	public static int MouseHandler_x;
	public static int MouseHandler_y;
	public static long MouseHandler_millis;
	public static volatile int MouseHandler_lastButtonVolatile;
	public static volatile int MouseHandler_lastPressedXVolatile;
	public static volatile int MouseHandler_lastPressedYVolatile;
	public static volatile long MouseHandler_lastPressedTimeMillisVolatile;
	public static int MouseHandler_lastButton;
	public static int MouseHandler_lastPressedX;
	public static int MouseHandler_lastPressedY;
	public static long MouseHandler_lastPressedTimeMillis;
	static Archive archive4;

	static {
		MouseHandler_instance = new MouseHandler();
		MouseHandler_idleCycles = 0;
		MouseHandler_currentButtonVolatile = 0;
		MouseHandler_xVolatile = -1;
		MouseHandler_yVolatile = -1;
		MouseHandler_lastMovedVolatile = -1L;
		MouseHandler_currentButton = 0;
		MouseHandler_x = 0;
		MouseHandler_y = 0;
		MouseHandler_millis = 0L;
		MouseHandler_lastButtonVolatile = 0;
		MouseHandler_lastPressedXVolatile = 0;
		MouseHandler_lastPressedYVolatile = 0;
		MouseHandler_lastPressedTimeMillisVolatile = 0L;
		MouseHandler_lastButton = 0;
		MouseHandler_lastPressedX = 0;
		MouseHandler_lastPressedY = 0;
		MouseHandler_lastPressedTimeMillis = 0L;
	}

	MouseHandler() {
	}

	final int getButton(MouseEvent var1) {
		int var2 = var1.getButton();
		if (!var1.isAltDown() && var2 != 2) {
			return !var1.isMetaDown() && var2 != 3 ? 1 : 2;
		} else {
			return 4;
		}
	}

	public final synchronized void mouseMoved(MouseEvent var1) {
		if (MouseHandler_instance != null) {
			MouseHandler_idleCycles = 0;
			MouseHandler_xVolatile = var1.getX();
			MouseHandler_yVolatile = var1.getY();
			MouseHandler_lastMovedVolatile = var1.getWhen();
		}

	}

	public final synchronized void mousePressed(MouseEvent var1) {
		if (MouseHandler_instance != null) {
			MouseHandler_idleCycles = 0;
			MouseHandler_lastPressedXVolatile = var1.getX();
			MouseHandler_lastPressedYVolatile = var1.getY();
			MouseHandler_lastPressedTimeMillisVolatile = WorldMapSprite.method4989();
			MouseHandler_lastButtonVolatile = this.getButton(var1);
			if (MouseHandler_lastButtonVolatile != 0) {
				MouseHandler_currentButtonVolatile = MouseHandler_lastButtonVolatile;
			}
		}

		if (var1.isPopupTrigger()) {
			var1.consume();
		}

	}

	public final void mouseClicked(MouseEvent var1) {
		if (var1.isPopupTrigger()) {
			var1.consume();
		}

	}

	public final synchronized void mouseExited(MouseEvent var1) {
		if (MouseHandler_instance != null) {
			MouseHandler_idleCycles = 0;
			MouseHandler_xVolatile = -1;
			MouseHandler_yVolatile = -1;
			MouseHandler_lastMovedVolatile = var1.getWhen();
		}

	}

	public final synchronized void mouseEntered(MouseEvent var1) {
		this.mouseMoved(var1);
	}

	public final void focusGained(FocusEvent var1) {
	}

	public final synchronized void focusLost(FocusEvent var1) {
		if (MouseHandler_instance != null) {
			MouseHandler_currentButtonVolatile = 0;
		}

	}

	public final synchronized void mouseReleased(MouseEvent var1) {
		if (MouseHandler_instance != null) {
			MouseHandler_idleCycles = 0;
			MouseHandler_currentButtonVolatile = 0;
		}

		if (var1.isPopupTrigger()) {
			var1.consume();
		}

	}

	public final synchronized void mouseDragged(MouseEvent var1) {
		this.mouseMoved(var1);
	}

	static IndexedSprite method622(boolean var0, boolean var1) {
		return var0 ? (var1 ? class143.field1639 : Skeleton.options_buttons_2Sprite) : (var1 ? InvDefinition.field1814 : class160.options_buttons_0Sprite);
	}

	static void method621(GameEngine var0) {
		while (class16.isKeyDown()) {
			if (class241.field2833 == 13) {
				Login.worldSelectOpen = false;
				Login.leftTitleSprite.drawAt(Login.xPadding, 0);
				class162.rightTitleSprite.drawAt(Login.xPadding + 382, 0);
				HorizontalAlignment.logoSprite.drawAt(Login.xPadding + 382 - HorizontalAlignment.logoSprite.subWidth / 2, 18);
				return;
			}

			if (class241.field2833 == 96) {
				if (Login.worldSelectPage > 0 && Interpreter.worldSelectLeftSprite != null) {
					--Login.worldSelectPage;
				}
			} else if (class241.field2833 == 97 && Login.worldSelectPage < Login.worldSelectPagesCount && class124.worldSelectRightSprite != null) {
				++Login.worldSelectPage;
			}
		}

		if (MouseHandler_lastButton == 1 || !UserComparator5.mouseCam && MouseHandler_lastButton == 4) {
			int var1 = Login.xPadding + 280;
			if (MouseHandler_lastPressedX >= var1 && MouseHandler_lastPressedX <= var1 + 14 && MouseHandler_lastPressedY >= 4 && MouseHandler_lastPressedY <= 18) {
				Interpreter.changeWorldSelectSorting(0, 0);
				return;
			}

			if (MouseHandler_lastPressedX >= var1 + 15 && MouseHandler_lastPressedX <= var1 + 80 && MouseHandler_lastPressedY >= 4 && MouseHandler_lastPressedY <= 18) {
				Interpreter.changeWorldSelectSorting(0, 1);
				return;
			}

			int var2 = Login.xPadding + 390;
			if (MouseHandler_lastPressedX >= var2 && MouseHandler_lastPressedX <= var2 + 14 && MouseHandler_lastPressedY >= 4 && MouseHandler_lastPressedY <= 18) {
				Interpreter.changeWorldSelectSorting(1, 0);
				return;
			}

			if (MouseHandler_lastPressedX >= var2 + 15 && MouseHandler_lastPressedX <= var2 + 80 && MouseHandler_lastPressedY >= 4 && MouseHandler_lastPressedY <= 18) {
				Interpreter.changeWorldSelectSorting(1, 1);
				return;
			}

			int var3 = Login.xPadding + 500;
			if (MouseHandler_lastPressedX >= var3 && MouseHandler_lastPressedX <= var3 + 14 && MouseHandler_lastPressedY >= 4 && MouseHandler_lastPressedY <= 18) {
				Interpreter.changeWorldSelectSorting(2, 0);
				return;
			}

			if (MouseHandler_lastPressedX >= var3 + 15 && MouseHandler_lastPressedX <= var3 + 80 && MouseHandler_lastPressedY >= 4 && MouseHandler_lastPressedY <= 18) {
				Interpreter.changeWorldSelectSorting(2, 1);
				return;
			}

			int var4 = Login.xPadding + 610;
			if (MouseHandler_lastPressedX >= var4 && MouseHandler_lastPressedX <= var4 + 14 && MouseHandler_lastPressedY >= 4 && MouseHandler_lastPressedY <= 18) {
				Interpreter.changeWorldSelectSorting(3, 0);
				return;
			}

			if (MouseHandler_lastPressedX >= var4 + 15 && MouseHandler_lastPressedX <= var4 + 80 && MouseHandler_lastPressedY >= 4 && MouseHandler_lastPressedY <= 18) {
				Interpreter.changeWorldSelectSorting(3, 1);
				return;
			}

			if (MouseHandler_lastPressedX >= Login.xPadding + 708 && MouseHandler_lastPressedY >= 4 && MouseHandler_lastPressedX <= Login.xPadding + 708 + 50 && MouseHandler_lastPressedY <= 20) {
				Login.worldSelectOpen = false;
				Login.leftTitleSprite.drawAt(Login.xPadding, 0);
				class162.rightTitleSprite.drawAt(Login.xPadding + 382, 0);
				HorizontalAlignment.logoSprite.drawAt(Login.xPadding + 382 - HorizontalAlignment.logoSprite.subWidth / 2, 18);
				return;
			}

			if (Login.hoveredWorldIndex != -1) {
				World var5 = World.World_worlds[Login.hoveredWorldIndex];
				ItemContainer.changeWorld(var5);
				Login.worldSelectOpen = false;
				Login.leftTitleSprite.drawAt(Login.xPadding, 0);
				class162.rightTitleSprite.drawAt(Login.xPadding + 382, 0);
				HorizontalAlignment.logoSprite.drawAt(Login.xPadding + 382 - HorizontalAlignment.logoSprite.subWidth / 2, 18);
				return;
			}

			if (Login.worldSelectPage > 0 && Interpreter.worldSelectLeftSprite != null && MouseHandler_lastPressedX >= 0 && MouseHandler_lastPressedX <= Interpreter.worldSelectLeftSprite.subWidth && MouseHandler_lastPressedY >= class309.canvasHeight / 2 - 50 && MouseHandler_lastPressedY <= class309.canvasHeight / 2 + 50) {
				--Login.worldSelectPage;
			}

			if (Login.worldSelectPage < Login.worldSelectPagesCount && class124.worldSelectRightSprite != null && MouseHandler_lastPressedX >= class4.canvasWidth - class124.worldSelectRightSprite.subWidth - 5 && MouseHandler_lastPressedX <= class4.canvasWidth && MouseHandler_lastPressedY >= class309.canvasHeight / 2 - 50 && MouseHandler_lastPressedY <= class309.canvasHeight / 2 + 50) {
				++Login.worldSelectPage;
			}
		}

	}

	static final void method593() {
		FloorOverlayDefinition.FloorOverlayDefinition_cached.clear();
		FloorUnderlayDefinition.FloorUnderlayDefinition_cached.clear();
		KitDefinition.KitDefinition_cached.clear();
		ObjectComposition.ObjectDefinition_cached.clear();
		ObjectComposition.ObjectDefinition_cachedModelData.clear();
		ObjectComposition.ObjectDefinition_cachedEntities.clear();
		ObjectComposition.ObjectDefinition_cachedModels.clear();
		WorldMapDecoration.method4988();
		ItemComposition.ItemDefinition_cached.clear();
		ItemComposition.ItemDefinition_cachedModels.clear();
		ItemComposition.ItemDefinition_cachedSprites.clear();
		SequenceDefinition.SequenceDefinition_cached.clear();
		SequenceDefinition.SequenceDefinition_cachedFrames.clear();
		SequenceDefinition.SequenceDefinition_cachedModel.clear();
		SpotAnimationDefinition.SpotAnimationDefinition_cached.clear();
		SpotAnimationDefinition.SpotAnimationDefinition_cachedModels.clear();
		VarbitComposition.VarbitDefinition_cached.clear();
		FontName.method7509();
		ScriptEvent.HitSplatDefinition_cachedSprites.method7640();
		FontName.HitSplatDefinition_cached.method7640();
		class146.method3040();
		UserComparator1.method8029();
		class141.method2995();
		StructComposition.method3605();
		PacketWriter.method2485();
		PlayerComposition.PlayerAppearance_cachedModels.clear();
		Widget.Widget_cachedSprites.clear();
		Widget.Widget_cachedModels.clear();
		Widget.Widget_cachedFonts.clear();
		Widget.Widget_cachedSpriteMasks.clear();
		((TextureProvider)Rasterizer3D.Rasterizer3D_textureLoader).clear();
		Script.Script_cached.clear();
		TileItem.archive0.clearFiles();
		class321.archive1.clearFiles();
		class121.archive3.clearFiles();
		archive4.clearFiles();
		class302.archive5.clearFiles();
		ArchiveLoader.archive6.clearFiles();
		class145.archive7.clearFiles();
		WorldMapData_1.archive8.clearFiles();
		class132.archive9.clearFiles();
		World.archive10.clearFiles();
		class268.archive11.clearFiles();
		class135.archive12.clearFiles();
	}

	static final void playPcmPlayers() {
		if (class182.pcmPlayer1 != null) {
			class182.pcmPlayer1.run();
		}

		if (class290.pcmPlayer0 != null) {
			class290.pcmPlayer0.run();
		}

	}

	static final void runIntfCloseListeners(int var0, int var1) {
		if (MusicPatchNode2.loadInterface(var0)) {
			ClanSettings.runComponentCloseListeners(EnumComposition.Widget_interfaceComponents[var0], var1);
		}
	}
}
