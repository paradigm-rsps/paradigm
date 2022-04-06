import com.jagex.oldscape.pub.OAuthApi;
import com.jagex.oldscape.pub.OtlTokenRequester;
import com.jagex.oldscape.pub.OtlTokenResponse;
import netscape.javascript.JSObject;
import rs.ScriptOpcodes;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.*;
import java.util.concurrent.Future;

public final class Client extends GameEngine implements Usernamed, OAuthApi {
	static ClanSettings[] currentClanSettings;
	public static int field764;
	static boolean field731;
	static int viewportOffsetX;
	static int viewportOffsetY;
	static int destinationX;
	static int destinationY;
	static final ApproximateRouteStrategy field477;
	static boolean isCameraLocked;
	static int[] field743;
	static boolean field739;
	static int[] field670;
	static int currentTrackGroupId;
	static int field759;
	static PlayerComposition playerAppearance;
	static boolean[] field740;
	static int field738;
	static int minimapState;
	static int[] field482;
	static ArrayList archiveLoaders;
	static int[] field549;
	static int archiveLoadersDone;
	static int[] field766;
	static int field721;
	static int[] field744;
	static long field718;
	static int soundEffectCount;
	static int[] queuedSoundEffectDelays;
	static int[] soundEffectIds;
	static int[] queuedSoundEffectLoops;
	static SoundEffect[] soundEffects;
	static long[] crossWorldMessageIds;
	static int[] soundLocations;
	static PlatformInfoProvider platformInfoProvider;
	static int mapIconCount;
	static int crossWorldMessageIdsIndex;
	static int[] mapIconXs;
	static int[] mapIconYs;
	static short field745;
	static short field706;
	static short field751;
	static SpritePixels[] mapIcons;
	static short field752;
	static short field750;
	static int viewportZoom;
	static short field562;
	static int viewportWidth;
	static int field713;
	static ClanChannel[] currentClanChannels;
	static int viewportHeight;
	static int[] field568;
	static short zoomWidth;
	static short zoomHeight;
	static int field767;
	static int[] field716;
	static int publicChatMode;
	static int tradeChatMode;
	static String field712;
	static GrandExchangeOffer[] grandExchangeOffers;
	static GrandExchangeOfferOwnWorldComparator GrandExchangeEvents_worldComparator;
	static CollisionMap[] collisionMaps;
	public static AbstractArchive Widget_fontsArchive;
	static boolean field536;
	public static int worldId;
	static int worldProperties;
	static GameBuild field509;
	static int gameBuild;
	public static boolean isMembersWorld;
	static boolean isLowDetail;
	static int field484;
	static int clientType;
	static int field486;
	static boolean onMobile;
	static int gameState;
	static IndexedSprite[] worldSelectArrows;
	static boolean isLoading;
	static int cycle;
	static long mouseLastLastPressedTimeMillis;
	static int field493;
	static int field494;
	static long field495;
	static boolean hadFocus;
	static int rebootTimer;
	static int hintArrowType;
	static int hintArrowNpcIndex;
	static int hintArrowPlayerIndex;
	static int hintArrowX;
	static int hintArrowY;
	static int hintArrowHeight;
	static int hintArrowSubX;
	static int hintArrowSubY;
	static AttackOption playerAttackOption;
	static AttackOption npcAttackOption;
	static int titleLoadingStage;
	static int js5ConnectState;
	static int field510;
	static int js5Errors;
	static int loginState;
	static int field513;
	static int field742;
	static int field490;
	static class124 field516;
	static class441 field517;
	static boolean Login_isUsernameRemembered;
	static SecureRandomFuture secureRandomFuture;
	static byte[] randomDatData;
	static NPC[] npcs;
	static int npcCount;
	static int[] npcIndices;
	static int field609;
	static int[] field533;
	public static final PacketWriter packetWriter;
	static int logoutTimer;
	static boolean hadNetworkError;
	static boolean useBufferedSocket;
	static boolean field753;
	static Timer timer;
	static HashMap fontsMap;
	static int field541;
	static int field558;
	static int field543;
	static int field679;
	static int field545;
	static boolean isInInstance;
	static int[][][] instanceChunkTemplates;
	static final int[] field711;
	static int field550;
	static SpritePixels[] crossSprites;
	static int field619;
	static int field684;
	static String selectedItemName;
	static int field554;
	static int field514;
	static boolean field556;
	static int alternativeScrollbarWidth;
	static int camAngleX;
	static int camAngleY;
	static int camAngleDY;
	static int camAngleDX;
	static int mouseCamClickedX;
	static int mouseCamClickedY;
	static int oculusOrbState;
	static int camFollowHeight;
	static int field566;
	static int field567;
	static int field696;
	static int oculusOrbNormalSpeed;
	static int oculusOrbSlowedSpeed;
	static int field571;
	static boolean field602;
	static int field542;
	static boolean field616;
	static int field756;
	static int overheadTextCount;
	static int overheadTextLimit;
	static int[] overheadTextXs;
	static int[] overheadTextYs;
	static int[] overheadTextAscents;
	static int[] overheadTextXOffsets;
	static int[] overheadTextColors;
	static int[] overheadTextEffects;
	static int[] overheadTextCyclesRemaining;
	static String[] overheadText;
	static int[][] tileLastDrawnActor;
	static int viewportDrawCount;
	static int viewportTempX;
	static int viewportTempY;
	static int mouseCrossX;
	static int mouseCrossY;
	static int mouseCrossState;
	static int mouseCrossColor;
	static boolean showMouseCross;
	static int field595;
	static int field741;
	static int dragItemSlotSource;
	static int draggedWidgetX;
	static int draggedWidgetY;
	static int dragItemSlotDestination;
	static boolean field601;
	static int itemDragDuration;
	static int field603;
	static boolean showLoadingMessages;
	static Player[] players;
	static int localPlayerIndex;
	static int field607;
	static long field608;
	static boolean renderSelf;
	static int drawPlayerNames;
	static int field634;
	static int[] field612;
	static final int[] playerMenuOpcodes;
	static String[] playerMenuActions;
	static boolean[] playerOptionsPriorities;
	static int[] defaultRotations;
	static int combatTargetPlayerIndex;
	static NodeDeque[][][] groundItems;
	static NodeDeque pendingSpawns;
	static NodeDeque projectiles;
	static NodeDeque graphicsObjects;
	static int[] currentLevels;
	static int[] levels;
	static int[] experience;
	static int leftClickOpensMenu;
	static boolean isMenuOpen;
	static int menuOptionsCount;
	static int[] menuArguments1;
	static int[] menuArguments2;
	static int[] menuOpcodes;
	static int[] menuIdentifiers;
	static String[] menuActions;
	static String[] menuTargets;
	static boolean[] menuShiftClick;
	static boolean followerOpsLowPriority;
	static boolean shiftClickDrop;
	static boolean tapToDrop;
	static boolean showMouseOverText;
	static int viewportX;
	static int viewportY;
	static int field641;
	static int field642;
	static int isItemSelected;
	static boolean isSpellSelected;
	static int selectedSpellChildIndex;
	static int field688;
	static String selectedSpellActionName;
	static String selectedSpellName;
	static int rootInterface;
	static NodeHashTable interfaceParents;
	static int field652;
	static int field653;
	static int chatEffects;
	static int field655;
	static Widget meslayerContinueWidget;
	static int runEnergy;
	static int weight;
	public static int staffModLevel;
	static int followerIndex;
	static boolean playerMod;
	static Widget viewportWidget;
	static Widget clickedWidget;
	static Widget clickedWidgetParent;
	static int widgetClickX;
	static int widgetClickY;
	static Widget draggedOnWidget;
	static boolean field668;
	static int field669;
	static int field628;
	static boolean field671;
	static int field596;
	static int field673;
	static boolean isDraggingWidget;
	static int cycleCntr;
	static int[] changedVarps;
	static int changedVarpCount;
	static int[] changedItemContainers;
	static int field746;
	static int[] changedSkills;
	static int changedSkillsCount;
	static int[] field682;
	static int field683;
	static int chatCycle;
	static int field685;
	static int field726;
	static int field687;
	static int field749;
	static int field689;
	static int field572;
	static int field762;
	static int mouseWheelRotation;
	static NodeDeque scriptEvents;
	static NodeDeque field694;
	static NodeDeque field695;
	static NodeHashTable widgetFlags;
	static int rootWidgetCount;
	static int field698;
	static boolean[] field564;
	static boolean[] field651;
	static boolean[] field576;
	static int[] rootWidgetXs;
	static int[] rootWidgetYs;
	static int[] rootWidgetWidths;
	static int[] rootWidgetHeights;
	static int gameDrawingMode;
	static long field529;
	static boolean isResizable;
	static int[] field709;
	String field518;
	class14 field519;
	class19 field618;
	OtlTokenRequester field544;
	Future field522;
	Buffer field525;
	class7 field526;
	long field658;

	static {
		field536 = true;
		worldId = 1;
		worldProperties = 0;
		gameBuild = 0;
		isMembersWorld = false;
		isLowDetail = false;
		field484 = -1;
		clientType = -1;
		field486 = -1;
		onMobile = false;
		gameState = 0;
		isLoading = true;
		cycle = 0;
		mouseLastLastPressedTimeMillis = -1L;
		field493 = -1;
		field494 = -1;
		field495 = -1L;
		hadFocus = true;
		rebootTimer = 0;
		hintArrowType = 0;
		hintArrowNpcIndex = 0;
		hintArrowPlayerIndex = 0;
		hintArrowX = 0;
		hintArrowY = 0;
		hintArrowHeight = 0;
		hintArrowSubX = 0;
		hintArrowSubY = 0;
		playerAttackOption = AttackOption.AttackOption_hidden;
		npcAttackOption = AttackOption.AttackOption_hidden;
		titleLoadingStage = 0;
		js5ConnectState = 0;
		field510 = 0;
		js5Errors = 0;
		loginState = 0;
		field513 = 0;
		field742 = 0;
		field490 = 0;
		field516 = class124.field1510;
		field517 = class441.field4672;
		Login_isUsernameRemembered = false;
		secureRandomFuture = new SecureRandomFuture();
		randomDatData = null;
		npcs = new NPC[32768];
		npcCount = 0;
		npcIndices = new int[32768];
		field609 = 0;
		field533 = new int[250];
		packetWriter = new PacketWriter();
		logoutTimer = 0;
		hadNetworkError = false;
		useBufferedSocket = true;
		field753 = false;
		timer = new Timer();
		fontsMap = new HashMap();
		field541 = 0;
		field558 = 1;
		field543 = 0;
		field679 = 1;
		field545 = 0;
		collisionMaps = new CollisionMap[4];
		isInInstance = false;
		instanceChunkTemplates = new int[4][13][13];
		field711 = new int[]{0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3};
		field550 = 0;
		field619 = 2301979;
		field684 = 5063219;
		field554 = 3353893;
		field514 = 7759444;
		field556 = false;
		alternativeScrollbarWidth = 0;
		camAngleX = 128;
		camAngleY = 0;
		camAngleDY = 0;
		camAngleDX = 0;
		mouseCamClickedX = 0;
		mouseCamClickedY = 0;
		oculusOrbState = 0;
		camFollowHeight = 50;
		field566 = 0;
		field567 = 0;
		field696 = 0;
		oculusOrbNormalSpeed = 12;
		oculusOrbSlowedSpeed = 6;
		field571 = 0;
		field602 = false;
		field542 = 0;
		field616 = false;
		field756 = 0;
		overheadTextCount = 0;
		overheadTextLimit = 50;
		overheadTextXs = new int[overheadTextLimit];
		overheadTextYs = new int[overheadTextLimit];
		overheadTextAscents = new int[overheadTextLimit];
		overheadTextXOffsets = new int[overheadTextLimit];
		overheadTextColors = new int[overheadTextLimit];
		overheadTextEffects = new int[overheadTextLimit];
		overheadTextCyclesRemaining = new int[overheadTextLimit];
		overheadText = new String[overheadTextLimit];
		tileLastDrawnActor = new int[104][104];
		viewportDrawCount = 0;
		viewportTempX = -1;
		viewportTempY = -1;
		mouseCrossX = 0;
		mouseCrossY = 0;
		mouseCrossState = 0;
		mouseCrossColor = 0;
		showMouseCross = true;
		field595 = 0;
		field741 = 0;
		dragItemSlotSource = 0;
		draggedWidgetX = 0;
		draggedWidgetY = 0;
		dragItemSlotDestination = 0;
		field601 = false;
		itemDragDuration = 0;
		field603 = 0;
		showLoadingMessages = true;
		players = new Player[2048];
		localPlayerIndex = -1;
		field607 = 0;
		field608 = -1L;
		renderSelf = true;
		drawPlayerNames = 0;
		field634 = 0;
		field612 = new int[1000];
		playerMenuOpcodes = new int[]{44, 45, 46, 47, 48, 49, 50, 51};
		playerMenuActions = new String[8];
		playerOptionsPriorities = new boolean[8];
		defaultRotations = new int[]{768, 1024, 1280, 512, 1536, 256, 0, 1792};
		combatTargetPlayerIndex = -1;
		groundItems = new NodeDeque[4][104][104];
		pendingSpawns = new NodeDeque();
		projectiles = new NodeDeque();
		graphicsObjects = new NodeDeque();
		currentLevels = new int[25];
		levels = new int[25];
		experience = new int[25];
		leftClickOpensMenu = 0;
		isMenuOpen = false;
		menuOptionsCount = 0;
		menuArguments1 = new int[500];
		menuArguments2 = new int[500];
		menuOpcodes = new int[500];
		menuIdentifiers = new int[500];
		menuActions = new String[500];
		menuTargets = new String[500];
		menuShiftClick = new boolean[500];
		followerOpsLowPriority = false;
		shiftClickDrop = false;
		tapToDrop = false;
		showMouseOverText = true;
		viewportX = -1;
		viewportY = -1;
		field641 = 0;
		field642 = 50;
		isItemSelected = 0;
		selectedItemName = null;
		isSpellSelected = false;
		selectedSpellChildIndex = -1;
		field688 = -1;
		selectedSpellActionName = null;
		selectedSpellName = null;
		rootInterface = -1;
		interfaceParents = new NodeHashTable(8);
		field652 = 0;
		field653 = -1;
		chatEffects = 0;
		field655 = 0;
		meslayerContinueWidget = null;
		runEnergy = 0;
		weight = 0;
		staffModLevel = 0;
		followerIndex = -1;
		playerMod = false;
		viewportWidget = null;
		clickedWidget = null;
		clickedWidgetParent = null;
		widgetClickX = 0;
		widgetClickY = 0;
		draggedOnWidget = null;
		field668 = false;
		field669 = -1;
		field628 = -1;
		field671 = false;
		field596 = -1;
		field673 = -1;
		isDraggingWidget = false;
		cycleCntr = 1;
		changedVarps = new int[32];
		changedVarpCount = 0;
		changedItemContainers = new int[32];
		field746 = 0;
		changedSkills = new int[32];
		changedSkillsCount = 0;
		field682 = new int[32];
		field683 = 0;
		chatCycle = 0;
		field685 = 0;
		field726 = 0;
		field687 = 0;
		field749 = 0;
		field689 = 0;
		field572 = 0;
		field762 = 0;
		mouseWheelRotation = 0;
		scriptEvents = new NodeDeque();
		field694 = new NodeDeque();
		field695 = new NodeDeque();
		widgetFlags = new NodeHashTable(512);
		rootWidgetCount = 0;
		field698 = -2;
		field564 = new boolean[100];
		field651 = new boolean[100];
		field576 = new boolean[100];
		rootWidgetXs = new int[100];
		rootWidgetYs = new int[100];
		rootWidgetWidths = new int[100];
		rootWidgetHeights = new int[100];
		gameDrawingMode = 0;
		field529 = 0L;
		isResizable = true;
		field709 = new int[]{16776960, 16711680, 65280, 65535, 16711935, 16777215};
		publicChatMode = 0;
		tradeChatMode = 0;
		field712 = "";
		crossWorldMessageIds = new long[100];
		crossWorldMessageIdsIndex = 0;
		field713 = 0;
		field716 = new int[128];
		field568 = new int[128];
		field718 = -1L;
		currentClanSettings = new ClanSettings[3];
		currentClanChannels = new ClanChannel[3];
		field721 = -1;
		mapIconCount = 0;
		mapIconXs = new int[1000];
		mapIconYs = new int[1000];
		mapIcons = new SpritePixels[1000];
		destinationX = 0;
		destinationY = 0;
		minimapState = 0;
		currentTrackGroupId = -1;
		field731 = false;
		soundEffectCount = 0;
		soundEffectIds = new int[50];
		queuedSoundEffectLoops = new int[50];
		queuedSoundEffectDelays = new int[50];
		soundLocations = new int[50];
		soundEffects = new SoundEffect[50];
		isCameraLocked = false;
		field739 = false;
		field740 = new boolean[5];
		field482 = new int[5];
		field549 = new int[5];
		field766 = new int[5];
		field744 = new int[5];
		field745 = 256;
		field706 = 205;
		zoomHeight = 256;
		zoomWidth = 320;
		field562 = 1;
		field750 = 32767;
		field751 = 1;
		field752 = 32767;
		viewportOffsetX = 0;
		viewportOffsetY = 0;
		viewportWidth = 0;
		viewportHeight = 0;
		viewportZoom = 0;
		playerAppearance = new PlayerComposition();
		field759 = -1;
		field738 = -1;
		platformInfoProvider = new DesktopPlatformInfoProvider();
		grandExchangeOffers = new GrandExchangeOffer[8];
		GrandExchangeEvents_worldComparator = new GrandExchangeOfferOwnWorldComparator();
		field764 = -1;
		archiveLoaders = new ArrayList(10);
		archiveLoadersDone = 0;
		field767 = 0;
		field477 = new ApproximateRouteStrategy();
		field743 = new int[50];
		field670 = new int[50];
	}

	public Client() {
		this.field658 = -1L;
	}

	protected final void resizeGame() {
		field529 = WorldMapSprite.cycleTimer() + 500L;
		this.resizeJS();
		if (rootInterface != -1) {
			this.resizeRoot(true);
		}

	}

	protected final void setUp() {
		int[] var1 = new int[]{20, 260, 10000};
		int[] var2 = new int[]{1000, 100, 500};
		if (var1 != null && var2 != null) {
			ByteArrayPool.ByteArrayPool_alternativeSizes = var1;
			GameObject.ByteArrayPool_altSizeArrayCounts = new int[var1.length];
			ScriptEvent.ByteArrayPool_arrays = new byte[var1.length][][];

			for (int var3 = 0; var3 < ByteArrayPool.ByteArrayPool_alternativeSizes.length; ++var3) {
				ScriptEvent.ByteArrayPool_arrays[var3] = new byte[var2[var3]][];
				ByteArrayPool.field4173.add(var1[var3]);
			}

			Collections.sort(ByteArrayPool.field4173);
		} else {
			ByteArrayPool.ByteArrayPool_alternativeSizes = null;
			GameObject.ByteArrayPool_altSizeArrayCounts = null;
			ScriptEvent.ByteArrayPool_arrays = null;
			ByteArrayPool.field4173.clear();
			ByteArrayPool.field4173.add(100);
			ByteArrayPool.field4173.add(5000);
			ByteArrayPool.field4173.add(10000);
			ByteArrayPool.field4173.add(30000);
		}

		class101.worldPort = gameBuild == 0 ? 43594 : worldId + 40000;
		BuddyRankComparator.js5Port = gameBuild == 0 ? 443 : worldId + 50000;
		ItemContainer.currentPort = class101.worldPort;
		TileItem.field1281 = class293.field3323;
		class20.field102 = class293.field3321;
		VerticalAlignment.field1950 = class293.field3320;
		class160.field1747 = class293.field3326;
		UserComparator9.urlRequester = new class101();
		this.setUpKeyboard();
		this.method534();
		class18.mouseWheel = this.mouseWheel();
		PcmPlayer.masterDisk = new ArchiveDisk(255, JagexCache.JagexCache_dat2File, JagexCache.JagexCache_idx255File, 500000);
		Interpreter.clientPreferences = class349.method6537();
		this.setUpClipboard();
		String var4 = ModeWhere.field4087;
		class29.field168 = this;
		if (var4 != null) {
			class29.field169 = var4;
		}

		UserComparator6.setWindowedMode(Interpreter.clientPreferences.method2317());
		class155.friendSystem = new FriendSystem(class83.loginType);
		this.field519 = new class14("tokenRequest", 1, 1);
	}

	protected final void doCycle() {
		++cycle;
		this.doCycleJs5();
		Canvas.method391();
		class126.method2819();
		MouseHandler.playPcmPlayers();
		synchronized(KeyHandler.KeyHandler_instance) {
			++KeyHandler.KeyHandler_idleCycles;
			KeyHandler.field135 = KeyHandler.field137;
			KeyHandler.field132 = 0;
			KeyHandler.field134 = 0;
			Arrays.fill(KeyHandler.field121, false);
			Arrays.fill(KeyHandler.field125, false);
			if (KeyHandler.field124 < 0) {
				Arrays.fill(KeyHandler.KeyHandler_pressedKeys, false);
				KeyHandler.field124 = KeyHandler.field138;
			} else {
				while (KeyHandler.field138 != KeyHandler.field124) {
					int var2 = KeyHandler.field129[KeyHandler.field138];
					KeyHandler.field138 = KeyHandler.field138 + 1 & 127;
					if (var2 < 0) {
						var2 = ~var2;
						if (KeyHandler.KeyHandler_pressedKeys[var2]) {
							KeyHandler.KeyHandler_pressedKeys[var2] = false;
							KeyHandler.field125[var2] = true;
							KeyHandler.field133[KeyHandler.field134] = var2;
							++KeyHandler.field134;
						}
					} else {
						if (!KeyHandler.KeyHandler_pressedKeys[var2] && KeyHandler.field132 < KeyHandler.field139.length - 1) {
							KeyHandler.field121[var2] = true;
							KeyHandler.field139[++KeyHandler.field132 - 1] = var2;
						}

						KeyHandler.KeyHandler_pressedKeys[var2] = true;
					}
				}
			}

			if (KeyHandler.field132 > 0) {
				KeyHandler.KeyHandler_idleCycles = 0;
			}

			KeyHandler.field137 = KeyHandler.field136;
		}

		synchronized(MouseHandler.MouseHandler_instance) {
			MouseHandler.MouseHandler_currentButton = MouseHandler.MouseHandler_currentButtonVolatile;
			MouseHandler.MouseHandler_x = MouseHandler.MouseHandler_xVolatile;
			MouseHandler.MouseHandler_y = MouseHandler.MouseHandler_yVolatile;
			MouseHandler.MouseHandler_millis = MouseHandler.MouseHandler_lastMovedVolatile;
			MouseHandler.MouseHandler_lastButton = MouseHandler.MouseHandler_lastButtonVolatile;
			MouseHandler.MouseHandler_lastPressedX = MouseHandler.MouseHandler_lastPressedXVolatile;
			MouseHandler.MouseHandler_lastPressedY = MouseHandler.MouseHandler_lastPressedYVolatile;
			MouseHandler.MouseHandler_lastPressedTimeMillis = MouseHandler.MouseHandler_lastPressedTimeMillisVolatile;
			MouseHandler.MouseHandler_lastButtonVolatile = 0;
		}

		if (class18.mouseWheel != null) {
			int var5 = class18.mouseWheel.useRotation();
			mouseWheelRotation = var5;
		}

		if (gameState == 0) {
			HorizontalAlignment.load();
			class78.method2098();
		} else if (gameState == 5) {
			class194.method3894(this, ViewportMouse.fontPlain12);
			HorizontalAlignment.load();
			class78.method2098();
		} else if (gameState != 10 && gameState != 11) {
			if (gameState == 20) {
				class194.method3894(this, ViewportMouse.fontPlain12);
				this.doCycleLoggedOut();
			} else if (gameState == 50) {
				class194.method3894(this, ViewportMouse.fontPlain12);
				this.doCycleLoggedOut();
			} else if (gameState == 25) {
				DirectByteArrayCopier.method5546();
			}
		} else {
			class194.method3894(this, ViewportMouse.fontPlain12);
		}

		if (gameState == 30) {
			this.doCycleLoggedIn();
		} else if (gameState == 40 || gameState == 45) {
			this.doCycleLoggedOut();
		}

	}

	protected final void draw(boolean var1) {
		boolean var2 = class356.method6621();
		if (var2 && field731 && class290.pcmPlayer0 != null) {
			class290.pcmPlayer0.tryDiscard();
		}

		if ((gameState == 10 || gameState == 20 || gameState == 30) && field529 != 0L && WorldMapSprite.cycleTimer() > field529) {
			UserComparator6.setWindowedMode(ReflectionCheck.getWindowedMode());
		}

		int var3;
		if (var1) {
			for (var3 = 0; var3 < 100; ++var3) {
				field564[var3] = true;
			}
		}

		if (gameState == 0) {
			this.drawInitial(Login.Login_loadingPercent, Login.Login_loadingText, var1);
		} else if (gameState == 5) {
			class132.drawTitle(FloorOverlayDefinition.fontBold12, TextureProvider.fontPlain11, ViewportMouse.fontPlain12);
		} else if (gameState != 10 && gameState != 11) {
			if (gameState == 20) {
				class132.drawTitle(FloorOverlayDefinition.fontBold12, TextureProvider.fontPlain11, ViewportMouse.fontPlain12);
			} else if (gameState == 50) {
				class132.drawTitle(FloorOverlayDefinition.fontBold12, TextureProvider.fontPlain11, ViewportMouse.fontPlain12);
			} else if (gameState == 25) {
				if (field545 == 1) {
					if (field541 > field558) {
						field558 = field541;
					}

					var3 = (field558 * 50 - field541 * 50) / field558;
					class119.drawLoadingMessage("Loading - please wait." + "<br>" + " (" + var3 + "%" + ")", false);
				} else if (field545 == 2) {
					if (field543 > field679) {
						field679 = field543;
					}

					var3 = (field679 * 50 - field543 * 50) / field679 + 50;
					class119.drawLoadingMessage("Loading - please wait." + "<br>" + " (" + var3 + "%" + ")", false);
				} else {
					class119.drawLoadingMessage("Loading - please wait.", false);
				}
			} else if (gameState == 30) {
				this.drawLoggedIn();
			} else if (gameState == 40) {
				class119.drawLoadingMessage("Connection lost" + "<br>" + "Please wait - attempting to reestablish", false);
			} else if (gameState == 45) {
				class119.drawLoadingMessage("Please wait...", false);
			}
		} else {
			class132.drawTitle(FloorOverlayDefinition.fontBold12, TextureProvider.fontPlain11, ViewportMouse.fontPlain12);
		}

		if (gameState == 30 && gameDrawingMode == 0 && !var1 && !isResizable) {
			for (var3 = 0; var3 < rootWidgetCount; ++var3) {
				if (field651[var3]) {
					SpotAnimationDefinition.rasterProvider.draw(rootWidgetXs[var3], rootWidgetYs[var3], rootWidgetWidths[var3], rootWidgetHeights[var3]);
					field651[var3] = false;
				}
			}
		} else if (gameState > 0) {
			SpotAnimationDefinition.rasterProvider.drawFull(0, 0);

			for (var3 = 0; var3 < rootWidgetCount; ++var3) {
				field651[var3] = false;
			}
		}

	}

	protected final void kill0() {
		if (JagexCache.varcs.hasUnwrittenChanges()) {
			JagexCache.varcs.write();
		}

		if (BuddyRankComparator.mouseRecorder != null) {
			BuddyRankComparator.mouseRecorder.isRunning = false;
		}

		BuddyRankComparator.mouseRecorder = null;
		packetWriter.close();
		if (KeyHandler.KeyHandler_instance != null) {
			synchronized(KeyHandler.KeyHandler_instance) {
				KeyHandler.KeyHandler_instance = null;
			}
		}

		class160.method3237();
		class18.mouseWheel = null;
		if (class290.pcmPlayer0 != null) {
			class290.pcmPlayer0.shutdown();
		}

		if (class182.pcmPlayer1 != null) {
			class182.pcmPlayer1.shutdown();
		}

		class309.method5804();
		synchronized(ArchiveDiskActionHandler.ArchiveDiskActionHandler_lock) {
			if (ArchiveDiskActionHandler.field3940 != 0) {
				ArchiveDiskActionHandler.field3940 = 1;

				try {
					ArchiveDiskActionHandler.ArchiveDiskActionHandler_lock.wait();
				} catch (InterruptedException var4) {
				}
			}
		}

		if (UserComparator9.urlRequester != null) {
			UserComparator9.urlRequester.close();
			UserComparator9.urlRequester = null;
		}

		World.method1655();
		this.field519.method178();
	}

	protected final void vmethod1135() {
	}

	boolean method1138() {
		return AbstractWorldMapData.field2796 != null && !AbstractWorldMapData.field2796.trim().isEmpty();
	}

	boolean method1139() {
		return this.field544 != null;
	}

	void method1146(String var1) throws IOException {
		URL var2 = new URL(class124.field1518 + "public/v1/games/YCfdbvr2pM1zUYMxJRexZY/play");
		OtlTokenRequester var3 = this.field544;
		if (var3 != null) {
			this.field522 = var3.request(var2);
		} else {
			class10 var4 = new class10(var2, class9.field44);
			var4.method87("Authorization", "Bearer " + var1);
			this.field618 = this.field519.method180(var4);
		}
	}

	void doCycleJs5() {
		if (gameState != 1000) {
			boolean var1 = class65.method1875();
			if (!var1) {
				this.doCycleJs5Connect();
			}

		}
	}

	void doCycleJs5Connect() {
		if (NetCache.NetCache_crcMismatches >= 4) {
			this.error("js5crc");
			InterfaceParent.updateGameState(1000);
		} else {
			if (NetCache.NetCache_ioExceptions >= 4) {
				if (gameState <= 5) {
					this.error("js5io");
					InterfaceParent.updateGameState(1000);
					return;
				}

				field510 = 3000;
				NetCache.NetCache_ioExceptions = 3;
			}

			if (--field510 + 1 <= 0) {
				try {
					if (js5ConnectState == 0) {
						class194.js5SocketTask = class434.taskHandler.newSocketTask(GrandExchangeOfferOwnWorldComparator.worldHost, ItemContainer.currentPort);
						++js5ConnectState;
					}

					if (js5ConnectState == 1) {
						if (class194.js5SocketTask.status == 2) {
							this.js5Error(-1);
							return;
						}

						if (class194.js5SocketTask.status == 1) {
							++js5ConnectState;
						}
					}

					if (js5ConnectState == 2) {
						if (useBufferedSocket) {
							Socket var2 = (Socket)class194.js5SocketTask.result;
							BufferedNetSocket var1 = new BufferedNetSocket(var2, 40000, 5000);
							ApproximateRouteStrategy.js5Socket = var1;
						} else {
							ApproximateRouteStrategy.js5Socket = new NetSocket((Socket)class194.js5SocketTask.result, class434.taskHandler, 5000);
						}

						Buffer handshakeBuf = new Buffer(5);
						handshakeBuf.writeByte(15);
						handshakeBuf.writeInt(204);
						ApproximateRouteStrategy.js5Socket.write(handshakeBuf.array, 0, 5);
						++js5ConnectState;
						class29.field165 = WorldMapSprite.cycleTimer();
					}

					if (js5ConnectState == 3) {
						if (ApproximateRouteStrategy.js5Socket.available() > 0 || !useBufferedSocket && gameState <= 5) {
							int var5 = ApproximateRouteStrategy.js5Socket.readUnsignedByte();
							if (var5 != 0) {
								this.js5Error(var5);
								return;
							}

							++js5ConnectState;
						} else if (WorldMapSprite.cycleTimer() - class29.field165 > 30000L) {
							this.js5Error(-2);
							return;
						}
					}

					if (js5ConnectState == 4) {
						AbstractSocket var12 = ApproximateRouteStrategy.js5Socket;
						boolean var6 = gameState > 20;
						if (NetCache.NetCache_socket != null) {
							try {
								NetCache.NetCache_socket.close();
							} catch (Exception var9) {
							}

							NetCache.NetCache_socket = null;
						}

						NetCache.NetCache_socket = var12;
						NPC.method2366(var6);
						NetCache.NetCache_responseHeaderBuffer.offset = 0;
						NetCache.NetCache_currentResponse = null;
						class291.NetCache_responseArchiveBuffer = null;
						NetCache.field3990 = 0;

						while (true) {
							NetFileRequest var3 = (NetFileRequest)NetCache.NetCache_pendingPriorityResponses.first();
							if (var3 == null) {
								while (true) {
									var3 = (NetFileRequest)NetCache.NetCache_pendingResponses.first();
									if (var3 == null) {
										if (NetCache.NetCache_xorValue != 0) {
											try {
												Buffer var13 = new Buffer(4);
												var13.writeByte(4);
												var13.writeByte(NetCache.NetCache_xorValue);
												var13.writeShort(0);
												NetCache.NetCache_socket.write(var13.array, 0, 4);
											} catch (IOException var8) {
												try {
													NetCache.NetCache_socket.close();
												} catch (Exception var7) {
												}

												++NetCache.NetCache_ioExceptions;
												NetCache.NetCache_socket = null;
											}
										}

										NetCache.NetCache_loadTime = 0;
										NetCache.idleNetTime = WorldMapSprite.cycleTimer();
										class194.js5SocketTask = null;
										ApproximateRouteStrategy.js5Socket = null;
										js5ConnectState = 0;
										js5Errors = 0;
										return;
									}

									NetCache.NetCache_pendingWritesQueue.addLast(var3);
									NetCache.NetCache_pendingWrites.put(var3, var3.key);
									++NetCache.NetCache_pendingWritesCount;
									--NetCache.NetCache_pendingResponsesCount;
								}
							}

							NetCache.NetCache_pendingPriorityWrites.put(var3, var3.key);
							++NetCache.NetCache_pendingPriorityWritesCount;
							--NetCache.NetCache_pendingPriorityResponsesCount;
						}
					}
				} catch (IOException var10) {
					this.js5Error(-3);
				}

			}
		}
	}

	void js5Error(int var1) {
		class194.js5SocketTask = null;
		ApproximateRouteStrategy.js5Socket = null;
		js5ConnectState = 0;
		if (ItemContainer.currentPort == class101.worldPort) {
			ItemContainer.currentPort = BuddyRankComparator.js5Port;
		} else {
			ItemContainer.currentPort = class101.worldPort;
		}

		++js5Errors;
		if (js5Errors >= 2 && (var1 == 7 || var1 == 9)) {
			if (gameState <= 5) {
				this.error("js5connect_full");
				InterfaceParent.updateGameState(1000);
			} else {
				field510 = 3000;
			}
		} else if (js5Errors >= 2 && var1 == 6) {
			this.error("js5connect_outofdate");
			InterfaceParent.updateGameState(1000);
		} else if (js5Errors >= 4) {
			if (gameState <= 5) {
				this.error("js5connect");
				InterfaceParent.updateGameState(1000);
			} else {
				field510 = 3000;
			}
		}

	}

	final void doCycleLoggedOut() {
		Object var1 = packetWriter.getSocket();
		PacketBuffer packetBuf = packetWriter.packetBuffer;

		try {
			if (loginState == 0) {
				if (class260.secureRandom == null && (secureRandomFuture.isDone() || field513 > 250)) {
					class260.secureRandom = secureRandomFuture.get();
					secureRandomFuture.shutdown();
					secureRandomFuture = null;
				}

				if (class260.secureRandom != null) {
					if (var1 != null) {
						((AbstractSocket)var1).close();
						var1 = null;
					}

					WorldMapEvent.socketTask = null;
					hadNetworkError = false;
					field513 = 0;
					if (field517.method8013()) {
						try {
							this.method1146(AbstractWorldMapData.field2796);
							UrlRequester.method2533(20);
						} catch (Throwable var23) {
							class301.RunException_sendStackTrace(null, var23);
							class150.getLoginError(65);
							return;
						}
					} else {
						UrlRequester.method2533(1);
					}
				}
			}

			if (loginState == 20) {
				if (this.field522 != null) {
					if (!this.field522.isDone()) {
						return;
					}

					if (this.field522.isCancelled()) {
						class150.getLoginError(65);
						this.field522 = null;
						return;
					}

					try {
						OtlTokenResponse var3 = (OtlTokenResponse)this.field522.get();
						if (!var3.isSuccess()) {
							class150.getLoginError(65);
							this.field522 = null;
							return;
						}

						this.field518 = var3.getToken();
						this.field522 = null;
					} catch (Exception var22) {
						class301.RunException_sendStackTrace(null, var22);
						class150.getLoginError(65);
						this.field522 = null;
						return;
					}
				} else {
					if (this.field618 == null) {
						class150.getLoginError(65);
						return;
					}

					if (!this.field618.method303()) {
						return;
					}

					if (this.field618.method316()) {
						class301.RunException_sendStackTrace(this.field618.method299(), null);
						class150.getLoginError(65);
						this.field618 = null;
						return;
					}

					class21 var25 = this.field618.method301();
					if (var25.method328() != 200) {
						class301.RunException_sendStackTrace("Response code: " + var25.method328() + "Response body: " + var25.method330(), null);
						class150.getLoginError(65);
						this.field618 = null;
						return;
					}

					this.field518 = var25.method330();
					this.field618 = null;
				}

				field513 = 0;
				UrlRequester.method2533(1);
			}

			if (loginState == 1) {
				if (WorldMapEvent.socketTask == null) {
					WorldMapEvent.socketTask = class434.taskHandler.newSocketTask(GrandExchangeOfferOwnWorldComparator.worldHost, ItemContainer.currentPort);
				}

				if (WorldMapEvent.socketTask.status == 2) {
					throw new IOException();
				}

				if (WorldMapEvent.socketTask.status == 1) {
					if (useBufferedSocket) {
						Socket var4 = (Socket)WorldMapEvent.socketTask.result;
						BufferedNetSocket var26 = new BufferedNetSocket(var4, 40000, 5000);
						var1 = var26;
					} else {
						var1 = new NetSocket((Socket)WorldMapEvent.socketTask.result, class434.taskHandler, 5000);
					}

					packetWriter.setSocket((AbstractSocket)var1);
					WorldMapEvent.socketTask = null;
					UrlRequester.method2533(2);
				}
			}

			PacketBufferNode var27;
			if (loginState == 2) {
				packetWriter.clearBuffer();
				var27 = FriendSystem.method1723();
				var27.packetBuffer.writeByte(LoginPacket.field3135.id);
				packetWriter.addNode(var27);
				packetWriter.flush();
				packetBuf.offset = 0;
				UrlRequester.method2533(3);
			}

			boolean var12;
			int var13;
			if (loginState == 3) {
				if (class290.pcmPlayer0 != null) {
					class290.pcmPlayer0.method722();
				}

				if (class182.pcmPlayer1 != null) {
					class182.pcmPlayer1.method722();
				}
				var12 = !useBufferedSocket || ((AbstractSocket) var1).isAvailable(1);

				if (var12) {
					var13 = ((AbstractSocket)var1).readUnsignedByte();
					if (class290.pcmPlayer0 != null) {
						class290.pcmPlayer0.method722();
					}

					if (class182.pcmPlayer1 != null) {
						class182.pcmPlayer1.method722();
					}

					if (var13 != 0) {
						class150.getLoginError(var13);
						return;
					}

					packetBuf.offset = 0;
					UrlRequester.method2533(4);
				}
			}

			int var40;
			if (loginState == 4) {
				if (packetBuf.offset < 8) {
					var40 = ((AbstractSocket)var1).available();
					if (var40 > 8 - packetBuf.offset) {
						var40 = 8 - packetBuf.offset;
					}

					if (var40 > 0) {
						((AbstractSocket)var1).read(packetBuf.array, packetBuf.offset, var40);
						packetBuf.offset += var40;
					}
				}

				if (packetBuf.offset == 8) {
					packetBuf.offset = 0;
					class121.field1479 = packetBuf.readLong();
					UrlRequester.method2533(5);
				}
			}

			int var7;
			int var9;
			int packetOpcode;
			if (loginState == 5) {
				packetWriter.packetBuffer.offset = 0;
				packetWriter.clearBuffer();
				PacketBuffer var28 = new PacketBuffer(500);
				int[] var29 = new int[]{class260.secureRandom.nextInt(), class260.secureRandom.nextInt(), class260.secureRandom.nextInt(), class260.secureRandom.nextInt()};
				var28.offset = 0;
				var28.writeByte(1);
				var28.writeInt(var29[0]);
				var28.writeInt(var29[1]);
				var28.writeInt(var29[2]);
				var28.writeInt(var29[3]);
				var28.writeLong(class121.field1479);
				int var11;
				if (gameState == 40) {
					var28.writeInt(NPCComposition.field1918[0]);
					var28.writeInt(NPCComposition.field1918[1]);
					var28.writeInt(NPCComposition.field1918[2]);
					var28.writeInt(NPCComposition.field1918[3]);
				} else {
					if (gameState == 50) {
						var28.writeByte(class124.field1512.rsOrdinal());
						var28.writeInt(class9.field35);
					} else {
						var28.writeByte(field516.rsOrdinal());
						switch(field516.field1513) {
						case 0:
						case 3:
							var28.writeMedium(UrlRequest.field1346);
							++var28.offset;
							break;
						case 1:
							LinkedHashMap var6 = Interpreter.clientPreferences.parameters;
							String var8 = Login.Login_username;
							var9 = var8.length();
							int var10 = 0;

							for (var11 = 0; var11 < var9; ++var11) {
								var10 = (var10 << 5) - var10 + var8.charAt(var11);
							}

							var28.writeInt((Integer)var6.get(var10));
							break;
						case 2:
							var28.offset += 4;
						}
					}

					if (field517.method8013()) {
						var28.writeByte(class441.field4667.rsOrdinal());
						var28.writeStringCp1252NullTerminated(this.field518);
					} else {
						var28.writeByte(class441.field4672.rsOrdinal());
						var28.writeStringCp1252NullTerminated(Login.Login_password);
					}
				}

				var28.encryptRsa(class67.field875, class67.field878);
				NPCComposition.field1918 = var29;
				PacketBufferNode var5 = FriendSystem.method1723();
				var5.packetBuffer.offset = 0;
				if (gameState == 40) {
					var5.packetBuffer.writeByte(LoginPacket.field3134.id);
				} else {
					var5.packetBuffer.writeByte(LoginPacket.field3133.id);
				}

				var5.packetBuffer.writeShort(0);
				packetOpcode = var5.packetBuffer.offset;
				var5.packetBuffer.writeInt(204);
				var5.packetBuffer.writeInt(1);
				var5.packetBuffer.writeByte(clientType);
				var5.packetBuffer.writeByte(field486);
				var5.packetBuffer.writeBytes(var28.array, 0, var28.offset);
				var7 = var5.packetBuffer.offset;
				var5.packetBuffer.writeStringCp1252NullTerminated(Login.Login_username);
				var5.packetBuffer.writeByte((isResizable ? 1 : 0) << 1 | (isLowDetail ? 1 : 0));
				var5.packetBuffer.writeShort(class4.canvasWidth);
				var5.packetBuffer.writeShort(class309.canvasHeight);
				PacketBuffer var38 = var5.packetBuffer;
				if (randomDatData != null) {
					var38.writeBytes(randomDatData, 0, randomDatData.length);
				} else {
					byte[] var15 = class67.method1878();
					var38.writeBytes(var15, 0, var15.length);
				}

				if (!class429.field4593.endsWith(";A")) {
					class429.field4593 = class429.field4593 + ";A";
				}

				var5.packetBuffer.writeStringCp1252NullTerminated(class429.field4593);
				var5.packetBuffer.writeInt(class7.field27);
				Buffer var42 = new Buffer(UserComparator2.platformInfo.size());
				UserComparator2.platformInfo.write(var42);
				var5.packetBuffer.writeBytes(var42.array, 0, var42.array.length);
				var5.packetBuffer.writeByte(clientType);
				var5.packetBuffer.writeInt(0);
				var5.packetBuffer.writeInt(class268.archive11.hash);
				var5.packetBuffer.method7806(ReflectionCheck.archive20.hash);
				var5.packetBuffer.writeIntME(DevicePcmPlayerProvider.archive14.hash);
				var5.packetBuffer.writeInt(WorldMapData_1.archive8.hash);
				var5.packetBuffer.writeIntME(FloorOverlayDefinition.archive2.hash);
				var5.packetBuffer.writeIntME(class202.archive19.hash);
				var5.packetBuffer.method7806(0);
				var5.packetBuffer.method7746(MouseHandler.archive4.hash);
				var5.packetBuffer.writeInt(class132.archive9.hash);
				var5.packetBuffer.writeIntME(World.archive10.hash);
				var5.packetBuffer.writeInt(class4.archive13.hash);
				var5.packetBuffer.method7806(JagexCache.archive18.hash);
				var5.packetBuffer.method7806(ViewportMouse.archive15.hash);
				var5.packetBuffer.method7746(class145.archive7.hash);
				var5.packetBuffer.method7746(ArchiveLoader.archive6.hash);
				var5.packetBuffer.writeInt(class302.archive5.hash);
				var5.packetBuffer.method7806(class121.archive3.hash);
				var5.packetBuffer.method7746(class321.archive1.hash);
				var5.packetBuffer.method7806(ArchiveLoader.archive17.hash);
				var5.packetBuffer.writeIntME(TileItem.archive0.hash);
				var5.packetBuffer.writeInt(class135.archive12.hash);
				var5.packetBuffer.xteaEncrypt(var29, var7, var5.packetBuffer.offset);
				var5.packetBuffer.writeLengthShort(var5.packetBuffer.offset - packetOpcode);
				packetWriter.addNode(var5);
				packetWriter.flush();
				packetWriter.isaacCipher = new IsaacCipher(var29);
				int[] var16 = new int[4];

				for (var11 = 0; var11 < 4; ++var11) {
					var16[var11] = var29[var11] + 50;
				}

				packetBuf.newIsaacCipher(var16);
				UrlRequester.method2533(6);
			}

			if (loginState == 6 && ((AbstractSocket)var1).available() > 0) {
				var40 = ((AbstractSocket)var1).readUnsignedByte();
				if (var40 == 61) {
					var13 = ((AbstractSocket)var1).available();
					class260.field2888 = var13 == 1 && ((AbstractSocket)var1).readUnsignedByte() == 1;
					UrlRequester.method2533(5);
				}

				if (var40 == 21 && gameState == 20) {
					UrlRequester.method2533(12);
				} else if (var40 == 2) {
					UrlRequester.method2533(14);
				} else if (var40 == 15 && gameState == 40) {
					packetWriter.serverPacketLength = -1;
					UrlRequester.method2533(19);
				} else if (var40 == 64) {
					UrlRequester.method2533(10);
				} else if (var40 == 23 && field742 < 1) {
					++field742;
					UrlRequester.method2533(0);
				} else if (var40 == 29) {
					UrlRequester.method2533(17);
				} else {
					if (var40 != 69) {
						class150.getLoginError(var40);
						return;
					}

					UrlRequester.method2533(7);
				}
			}

			if (loginState == 7 && ((AbstractSocket)var1).available() >= 2) {
				((AbstractSocket)var1).read(packetBuf.array, 0, 2);
				packetBuf.offset = 0;
				class116.field1418 = packetBuf.readUnsignedShort();
				UrlRequester.method2533(8);
			}

			if (loginState == 8 && ((AbstractSocket)var1).available() >= class116.field1418) {
				packetBuf.offset = 0;
				((AbstractSocket)var1).read(packetBuf.array, packetBuf.offset, class116.field1418);
				class6 var30 = JagexCache.method3224()[packetBuf.readUnsignedByte()];

				try {
					class3 var31 = class385.method7027(var30);
					this.field526 = new class7(packetBuf, var31);
					UrlRequester.method2533(9);
				} catch (Exception var21) {
					class150.getLoginError(22);
					return;
				}
			}

			if (loginState == 9 && this.field526.method42()) {
				this.field525 = this.field526.method44();
				this.field526.method43();
				this.field526 = null;
				if (this.field525 == null) {
					class150.getLoginError(22);
					return;
				}

				packetWriter.clearBuffer();
				var27 = FriendSystem.method1723();
				var27.packetBuffer.writeByte(LoginPacket.field3137.id);
				var27.packetBuffer.writeShort(this.field525.offset);
				var27.packetBuffer.method7818(this.field525);
				packetWriter.addNode(var27);
				packetWriter.flush();
				this.field525 = null;
				UrlRequester.method2533(6);
			}

			if (loginState == 10 && ((AbstractSocket)var1).available() > 0) {
				WorldMapSectionType.field2768 = ((AbstractSocket)var1).readUnsignedByte();
				UrlRequester.method2533(11);
			}

			if (loginState == 11 && ((AbstractSocket)var1).available() >= WorldMapSectionType.field2768) {
				((AbstractSocket)var1).read(packetBuf.array, 0, WorldMapSectionType.field2768);
				packetBuf.offset = 0;
				UrlRequester.method2533(6);
			}

			if (loginState == 12 && ((AbstractSocket)var1).available() > 0) {
				field490 = (((AbstractSocket)var1).readUnsignedByte() + 3) * 60;
				UrlRequester.method2533(13);
			}

			if (loginState == 13) {
				field513 = 0;
				GrandExchangeEvent.setLoginResponseString("You have only just left another world.", "Your profile will be transferred in:", field490 / 60 + " seconds.");
				if (--field490 <= 0) {
					UrlRequester.method2533(0);
				}

			} else {
				if (loginState == 14 && ((AbstractSocket)var1).available() >= 1) {
					class124.loginResponseLength = ((AbstractSocket)var1).readUnsignedByte();
					UrlRequester.method2533(15);
				}

				if (loginState == 15 && ((AbstractSocket)var1).available() >= class124.loginResponseLength) {
					var12 = ((AbstractSocket)var1).readUnsignedByte() == 1;
					((AbstractSocket)var1).read(packetBuf.array, 0, 4);
					packetBuf.offset = 0;
					boolean var41 = false;
					if (var12) {
						var13 = packetBuf.readByteIsaac() << 24;
						var13 |= packetBuf.readByteIsaac() << 16;
						var13 |= packetBuf.readByteIsaac() << 8;
						var13 |= packetBuf.readByteIsaac();
						String var37 = Login.Login_username;
						var7 = var37.length();
						int var17 = 0;
						var9 = 0;

						while (true) {
							if (var9 >= var7) {
								if (Interpreter.clientPreferences.parameters.size() >= 10 && !Interpreter.clientPreferences.parameters.containsKey(var17)) {
									Iterator var39 = Interpreter.clientPreferences.parameters.entrySet().iterator();
									var39.next();
									var39.remove();
								}

								Interpreter.clientPreferences.parameters.put(var17, var13);
								break;
							}

							var17 = (var17 << 5) - var17 + var37.charAt(var9);
							++var9;
						}
					}

					if (Login_isUsernameRemembered) {
						Interpreter.clientPreferences.method2316(Login.Login_username);
					} else {
						Interpreter.clientPreferences.method2316(null);
					}

					class307.savePreferences();
					staffModLevel = ((AbstractSocket)var1).readUnsignedByte();
					playerMod = ((AbstractSocket)var1).readUnsignedByte() == 1;
					localPlayerIndex = ((AbstractSocket)var1).readUnsignedByte();
					localPlayerIndex <<= 8;
					localPlayerIndex += ((AbstractSocket)var1).readUnsignedByte();
					field607 = ((AbstractSocket)var1).readUnsignedByte();

					((AbstractSocket)var1).read(packetBuf.array, 0, 8);
					packetBuf.offset = 0;
					this.field658 = packetBuf.readLong();
					if (class124.loginResponseLength >= 29) {
						((AbstractSocket)var1).read(packetBuf.array, 0, 8);
						packetBuf.offset = 0;
						field608 = packetBuf.readLong();
					}

					((AbstractSocket)var1).read(packetBuf.array, 0, 1);
					packetBuf.offset = 0;
					ServerPacket[] serverPackets = Statics1.ServerPacket_values();
					packetOpcode = packetBuf.readSmartByteShortIsaac();
					if (packetOpcode < 0 || packetOpcode >= serverPackets.length) {
						throw new IOException(packetOpcode + " " + packetBuf.offset);
					}

					packetWriter.serverPacket = serverPackets[packetOpcode];
					packetWriter.serverPacketLength = packetWriter.serverPacket.length;
					((AbstractSocket)var1).read(packetBuf.array, 0, 2);
					packetBuf.offset = 0;
					packetWriter.serverPacketLength = packetBuf.readUnsignedShort();

					try {
						Client var19 = UserComparator10.client;
						JSObject.getWindow(var19).call("zap", (Object[])null);
					} catch (Throwable var20) {
					}

					UrlRequester.method2533(16);
				}

				if (loginState == 16) {
					if (((AbstractSocket)var1).available() >= packetWriter.serverPacketLength) {
						packetBuf.offset = 0;
						((AbstractSocket)var1).read(packetBuf.array, 0, packetWriter.serverPacketLength);
						timer.updateCycleTimer();
						PacketWriter.reset();
						Strings.readPlayerGpi(packetBuf);
						class193.field2197 = -1;
						class146.rebuildRegion(false, packetBuf);
						packetWriter.serverPacket = null;
					}

				} else {
					if (loginState == 17 && ((AbstractSocket)var1).available() >= 2) {
						packetBuf.offset = 0;
						((AbstractSocket)var1).read(packetBuf.array, 0, 2);
						packetBuf.offset = 0;
						class142.field1634 = packetBuf.readUnsignedShort();
						UrlRequester.method2533(18);
					}

					if (loginState == 18 && ((AbstractSocket)var1).available() >= class142.field1634) {
						packetBuf.offset = 0;
						((AbstractSocket)var1).read(packetBuf.array, 0, class142.field1634);
						packetBuf.offset = 0;
						String var33 = packetBuf.readStringCp1252NullTerminated();
						String var32 = packetBuf.readStringCp1252NullTerminated();
						String var35 = packetBuf.readStringCp1252NullTerminated();
						GrandExchangeEvent.setLoginResponseString(var33, var32, var35);
						InterfaceParent.updateGameState(10);
					}

					if (loginState != 19) {
						++field513;
						if (field513 > 2000) {
							if (field742 < 1) {
								if (ItemContainer.currentPort == class101.worldPort) {
									ItemContainer.currentPort = BuddyRankComparator.js5Port;
								} else {
									ItemContainer.currentPort = class101.worldPort;
								}

								++field742;
								UrlRequester.method2533(0);
							} else {
								class150.getLoginError(-3);
							}
						}
					} else {
						if (packetWriter.serverPacketLength == -1) {
							if (((AbstractSocket)var1).available() < 2) {
								return;
							}

							((AbstractSocket)var1).read(packetBuf.array, 0, 2);
							packetBuf.offset = 0;
							packetWriter.serverPacketLength = packetBuf.readUnsignedShort();
						}

						if (((AbstractSocket)var1).available() >= packetWriter.serverPacketLength) {
							((AbstractSocket)var1).read(packetBuf.array, 0, packetWriter.serverPacketLength);
							packetBuf.offset = 0;
							var40 = packetWriter.serverPacketLength;
							timer.method6598();
							packetWriter.clearBuffer();
							packetWriter.packetBuffer.offset = 0;
							packetWriter.serverPacket = null;
							packetWriter.field1320 = null;
							packetWriter.field1329 = null;
							packetWriter.field1331 = null;
							packetWriter.serverPacketLength = 0;
							packetWriter.field1326 = 0;
							rebootTimer = 0;
							class268.method5228();
							minimapState = 0;
							destinationX = 0;

							for (var13 = 0; var13 < 2048; ++var13) {
								players[var13] = null;
							}

							class19.localPlayer = null;

							for (var13 = 0; var13 < npcs.length; ++var13) {
								NPC var36 = npcs[var13];
								if (var36 != null) {
									var36.targetIndex = -1;
									var36.false0 = false;
								}
							}

							ItemContainer.itemContainers = new NodeHashTable(32);
							InterfaceParent.updateGameState(30);

							for (var13 = 0; var13 < 100; ++var13) {
								field564[var13] = true;
							}

							class17.method228();
							Strings.readPlayerGpi(packetBuf);
							if (var40 != packetBuf.offset) {
								throw new RuntimeException();
							}
						}
					}
				}
			}
		} catch (IOException var24) {
			if (field742 < 1) {
				if (ItemContainer.currentPort == class101.worldPort) {
					ItemContainer.currentPort = BuddyRankComparator.js5Port;
				} else {
					ItemContainer.currentPort = class101.worldPort;
				}

				++field742;
				UrlRequester.method2533(0);
			} else {
				class150.getLoginError(-2);
			}
		}
	}

	final void doCycleLoggedIn() {
		if (rebootTimer > 1) {
			--rebootTimer;
		}

		if (logoutTimer > 0) {
			--logoutTimer;
		}

		if (hadNetworkError) {
			hadNetworkError = false;
			class9.method64();
		} else {
			if (!isMenuOpen) {
				VarpDefinition.addCancelMenuEntry();
			}

			int var1;
			for (var1 = 0; var1 < 100 && this.method1153(packetWriter); ++var1) {
			}

			if (gameState == 30) {
				while (true) {
					ReflectionCheck var2 = (ReflectionCheck)class33.reflectionChecks.last();
					boolean var30;
					var30 = var2 != null;

					int var3;
					PacketBufferNode var31;
					if (!var30) {
						PacketBufferNode var14;
						int var15;
						if (timer.field4182) {
							var14 = ItemContainer.getPacketBufferNode(ClientPacket.field2935, packetWriter.isaacCipher);
							var14.packetBuffer.writeByte(0);
							var15 = var14.packetBuffer.offset;
							timer.write(var14.packetBuffer);
							var14.packetBuffer.method7756(var14.packetBuffer.offset - var15);
							packetWriter.addNode(var14);
							timer.method6593();
						}

						int var4;
						int var5;
						int var6;
						int var7;
						int var8;
						int var9;
						int var10;
						int var11;
						int var12;
						PacketBuffer var10000;
						synchronized(BuddyRankComparator.mouseRecorder.lock) {
							if (!field536) {
								BuddyRankComparator.mouseRecorder.index = 0;
							} else if (MouseHandler.MouseHandler_lastButton != 0 || BuddyRankComparator.mouseRecorder.index >= 40) {
								var31 = null;
								var3 = 0;
								var4 = 0;
								var5 = 0;
								var6 = 0;

								for (var7 = 0; var7 < BuddyRankComparator.mouseRecorder.index && (var31 == null || var31.packetBuffer.offset - var3 < 246); ++var7) {
									var4 = var7;
									var8 = BuddyRankComparator.mouseRecorder.ys[var7];
									if (var8 < -1) {
										var8 = -1;
									} else if (var8 > 65534) {
										var8 = 65534;
									}

									var9 = BuddyRankComparator.mouseRecorder.xs[var7];
									if (var9 < -1) {
										var9 = -1;
									} else if (var9 > 65534) {
										var9 = 65534;
									}

									if (var9 != field493 || var8 != field494) {
										if (var31 == null) {
											var31 = ItemContainer.getPacketBufferNode(ClientPacket.field2965, packetWriter.isaacCipher);
											var31.packetBuffer.writeByte(0);
											var3 = var31.packetBuffer.offset;
											var10000 = var31.packetBuffer;
											var10000.offset += 2;
											var5 = 0;
											var6 = 0;
										}

										if (-1L != field495) {
											var10 = var9 - field493;
											var11 = var8 - field494;
											var12 = (int)((BuddyRankComparator.mouseRecorder.millis[var7] - field495) / 20L);
											var5 = (int)((long)var5 + (BuddyRankComparator.mouseRecorder.millis[var7] - field495) % 20L);
										} else {
											var10 = var9;
											var11 = var8;
											var12 = Integer.MAX_VALUE;
										}

										field493 = var9;
										field494 = var8;
										if (var12 < 8 && var10 >= -32 && var10 <= 31 && var11 >= -32 && var11 <= 31) {
											var10 += 32;
											var11 += 32;
											var31.packetBuffer.writeShort((var12 << 12) + var11 + (var10 << 6));
										} else if (var12 < 32 && var10 >= -128 && var10 <= 127 && var11 >= -128 && var11 <= 127) {
											var10 += 128;
											var11 += 128;
											var31.packetBuffer.writeByte(var12 + 128);
											var31.packetBuffer.writeShort(var11 + (var10 << 8));
										} else if (var12 < 32) {
											var31.packetBuffer.writeByte(var12 + 192);
											if (var9 != -1 && var8 != -1) {
												var31.packetBuffer.writeInt(var9 | var8 << 16);
											} else {
												var31.packetBuffer.writeInt(Integer.MIN_VALUE);
											}
										} else {
											var31.packetBuffer.writeShort((var12 & 8191) + 57344);
											if (var9 != -1 && var8 != -1) {
												var31.packetBuffer.writeInt(var9 | var8 << 16);
											} else {
												var31.packetBuffer.writeInt(Integer.MIN_VALUE);
											}
										}

										++var6;
										field495 = BuddyRankComparator.mouseRecorder.millis[var7];
									}
								}

								if (var31 != null) {
									var31.packetBuffer.method7756(var31.packetBuffer.offset - var3);
									var7 = var31.packetBuffer.offset;
									var31.packetBuffer.offset = var3;
									var31.packetBuffer.writeByte(var5 / var6);
									var31.packetBuffer.writeByte(var5 % var6);
									var31.packetBuffer.offset = var7;
									packetWriter.addNode(var31);
								}

								if (var4 >= BuddyRankComparator.mouseRecorder.index) {
									BuddyRankComparator.mouseRecorder.index = 0;
								} else {
									MouseRecorder var49 = BuddyRankComparator.mouseRecorder;
									var49.index -= var4;
									System.arraycopy(BuddyRankComparator.mouseRecorder.xs, var4, BuddyRankComparator.mouseRecorder.xs, 0, BuddyRankComparator.mouseRecorder.index);
									System.arraycopy(BuddyRankComparator.mouseRecorder.ys, var4, BuddyRankComparator.mouseRecorder.ys, 0, BuddyRankComparator.mouseRecorder.index);
									System.arraycopy(BuddyRankComparator.mouseRecorder.millis, var4, BuddyRankComparator.mouseRecorder.millis, 0, BuddyRankComparator.mouseRecorder.index);
								}
							}
						}

						PacketBufferNode var18;
						if (MouseHandler.MouseHandler_lastButton == 1 || !UserComparator5.mouseCam && MouseHandler.MouseHandler_lastButton == 4 || MouseHandler.MouseHandler_lastButton == 2) {
							long var16 = MouseHandler.MouseHandler_lastPressedTimeMillis - mouseLastLastPressedTimeMillis;
							if (var16 > 32767L) {
								var16 = 32767L;
							}

							mouseLastLastPressedTimeMillis = MouseHandler.MouseHandler_lastPressedTimeMillis;
							var3 = MouseHandler.MouseHandler_lastPressedY;
							if (var3 < 0) {
								var3 = 0;
							} else if (var3 > class309.canvasHeight) {
								var3 = class309.canvasHeight;
							}

							var4 = MouseHandler.MouseHandler_lastPressedX;
							if (var4 < 0) {
								var4 = 0;
							} else if (var4 > class4.canvasWidth) {
								var4 = class4.canvasWidth;
							}

							var5 = (int)var16;
							var18 = ItemContainer.getPacketBufferNode(ClientPacket.field2955, packetWriter.isaacCipher);
							var18.packetBuffer.writeShort((MouseHandler.MouseHandler_lastButton == 2 ? 1 : 0) + (var5 << 1));
							var18.packetBuffer.writeShort(var4);
							var18.packetBuffer.writeShort(var3);
							packetWriter.addNode(var18);
						}

						if (KeyHandler.field132 > 0) {
							var14 = ItemContainer.getPacketBufferNode(ClientPacket.field2899, packetWriter.isaacCipher);
							var14.packetBuffer.writeShort(0);
							var15 = var14.packetBuffer.offset;
							long var19 = WorldMapSprite.cycleTimer();

							for (var5 = 0; var5 < KeyHandler.field132; ++var5) {
								long var21 = var19 - field718;
								if (var21 > 16777215L) {
									var21 = 16777215L;
								}

								field718 = var19;
								var14.packetBuffer.writeByte(KeyHandler.field139[var5]);
								var14.packetBuffer.method7803((int)var21);
							}

							var14.packetBuffer.writeLengthShort(var14.packetBuffer.offset - var15);
							packetWriter.addNode(var14);
						}

						if (field542 > 0) {
							--field542;
						}

						if (KeyHandler.KeyHandler_pressedKeys[96] || KeyHandler.KeyHandler_pressedKeys[97] || KeyHandler.KeyHandler_pressedKeys[98] || KeyHandler.KeyHandler_pressedKeys[99]) {
							field616 = true;
						}

						if (field616 && field542 <= 0) {
							field542 = 20;
							field616 = false;
							var14 = ItemContainer.getPacketBufferNode(ClientPacket.field2975, packetWriter.isaacCipher);
							var14.packetBuffer.method7796(camAngleY);
							var14.packetBuffer.writeShort(camAngleX);
							packetWriter.addNode(var14);
						}

						if (Varps.hasFocus && !hadFocus) {
							hadFocus = true;
							var14 = ItemContainer.getPacketBufferNode(ClientPacket.field2897, packetWriter.isaacCipher);
							var14.packetBuffer.writeByte(1);
							packetWriter.addNode(var14);
						}

						if (!Varps.hasFocus && hadFocus) {
							hadFocus = false;
							var14 = ItemContainer.getPacketBufferNode(ClientPacket.field2897, packetWriter.isaacCipher);
							var14.packetBuffer.writeByte(0);
							packetWriter.addNode(var14);
						}

						if (ClanChannel.worldMap != null) {
							ClanChannel.worldMap.method7303();
						}

						UserComparator7.method2577();
						if (class82.field1070) {
							SecureRandomFuture.method1968();
							class82.field1070 = false;
						}

						WorldMapSectionType.method4945();
						if (gameState != 30) {
							return;
						}

						class286.method5549();

						int var10002;
						for (var1 = 0; var1 < soundEffectCount; ++var1) {
							var10002 = queuedSoundEffectDelays[var1]--;
							if (queuedSoundEffectDelays[var1] >= -10) {
								SoundEffect var32 = soundEffects[var1];
								if (var32 == null) {
									var10000 = null;
									var32 = SoundEffect.readSoundEffect(MouseHandler.archive4, soundEffectIds[var1], 0);
									if (var32 == null) {
										continue;
									}

									int[] var50 = queuedSoundEffectDelays;
									var50[var1] += var32.calculateDelay();
									soundEffects[var1] = var32;
								}

								if (queuedSoundEffectDelays[var1] < 0) {
									if (soundLocations[var1] != 0) {
										var4 = (soundLocations[var1] & 255) * 128;
										var5 = soundLocations[var1] >> 16 & 255;
										var6 = var5 * 128 + 64 - class19.localPlayer.x;
										if (var6 < 0) {
											var6 = -var6;
										}

										var7 = soundLocations[var1] >> 8 & 255;
										var8 = var7 * 128 + 64 - class19.localPlayer.y;
										if (var8 < 0) {
											var8 = -var8;
										}

										var9 = var8 + var6 - 128;
										if (var9 > var4) {
											queuedSoundEffectDelays[var1] = -100;
											continue;
										}

										if (var9 < 0) {
											var9 = 0;
										}

										var3 = (var4 - var9) * Interpreter.clientPreferences.method2286() / var4;
									} else {
										var3 = Interpreter.clientPreferences.method2269();
									}

									if (var3 > 0) {
										RawSound var23 = var32.toRawSound().resample(LoginScreenAnimation.decimator);
										RawPcmStream var24 = RawPcmStream.createRawPcmStream(var23, 100, var3);
										var24.setNumLoops(queuedSoundEffectLoops[var1] - 1);
										WorldMapIcon_1.pcmStreamMixer.addSubStream(var24);
									}

									queuedSoundEffectDelays[var1] = -100;
								}
							} else {
								--soundEffectCount;

								for (var15 = var1; var15 < soundEffectCount; ++var15) {
									soundEffectIds[var15] = soundEffectIds[var15 + 1];
									soundEffects[var15] = soundEffects[var15 + 1];
									queuedSoundEffectLoops[var15] = queuedSoundEffectLoops[var15 + 1];
									queuedSoundEffectDelays[var15] = queuedSoundEffectDelays[var15 + 1];
									soundLocations[var15] = soundLocations[var15 + 1];
								}

								--var1;
							}
						}

						if (field731 && !KeyHandler.method376()) {
							if (Interpreter.clientPreferences.method2288() != 0 && currentTrackGroupId != -1) {
								WorldMapSection2.method4561(ArchiveLoader.archive6, currentTrackGroupId, 0, Interpreter.clientPreferences.method2288(), false);
							}

							field731 = false;
						}

						++packetWriter.field1326;
						if (packetWriter.field1326 > 750) {
							class9.method64();
							return;
						}

						var1 = Players.Players_count;
						int[] var33 = Players.Players_indices;

						for (var3 = 0; var3 < var1; ++var3) {
							Player var42 = players[var33[var3]];
							if (var42 != null) {
								BuddyRankComparator.updateActorSequence(var42, 1);
							}
						}

						for (var1 = 0; var1 < npcCount; ++var1) {
							var15 = npcIndices[var1];
							NPC var25 = npcs[var15];
							if (var25 != null) {
								BuddyRankComparator.updateActorSequence(var25, var25.definition.size);
							}
						}

						VarpDefinition.method3345();
						++field550;
						if (mouseCrossColor != 0) {
							mouseCrossState += 20;
							if (mouseCrossState >= 400) {
								mouseCrossColor = 0;
							}
						}

						if (class12.field64 != null) {
							++field595;
							if (field595 >= 15) {
								class290.invalidateWidget(class12.field64);
								class12.field64 = null;
							}
						}

						Widget var39 = Player.mousedOverWidgetIf1;
						Widget var34 = class160.field1750;
						Player.mousedOverWidgetIf1 = null;
						class160.field1750 = null;
						draggedOnWidget = null;
						field671 = false;
						field668 = false;
						field713 = 0;

						while (class16.isKeyDown() && field713 < 128) {
							if (staffModLevel >= 2 && KeyHandler.KeyHandler_pressedKeys[82] && class241.field2833 == 66) {
								StringBuilder var43 = new StringBuilder();

								Message var40;
								for (Iterator var45 = Messages.Messages_hashTable.iterator(); var45.hasNext(); var43.append(var40.text).append('\n')) {
									var40 = (Message)var45.next();
									if (var40.sender != null && !var40.sender.isEmpty()) {
										var43.append(var40.sender).append(':');
									}
								}

								String var48 = var43.toString();
								UserComparator10.client.method535(var48);
							} else if (oculusOrbState != 1 || class1.field3 <= 0) {
								field568[field713] = class241.field2833;
								field716[field713] = class1.field3;
								++field713;
							}
						}

						boolean var35 = staffModLevel >= 2;
						if (var35 && KeyHandler.KeyHandler_pressedKeys[82] && KeyHandler.KeyHandler_pressedKeys[81] && mouseWheelRotation != 0) {
							var4 = class19.localPlayer.plane - mouseWheelRotation;
							if (var4 < 0) {
								var4 = 0;
							} else if (var4 > 3) {
								var4 = 3;
							}

							if (var4 != class19.localPlayer.plane) {
								class392.method7191(class19.localPlayer.pathX[0] + ApproximateRouteStrategy.baseX, class19.localPlayer.pathY[0] + class250.baseY, var4, false);
							}

							mouseWheelRotation = 0;
						}

						if (rootInterface != -1) {
							ItemComposition.updateRootInterface(rootInterface, 0, 0, class4.canvasWidth, class309.canvasHeight, 0, 0);
						}

						++cycleCntr;

						while (true) {
							Widget var41;
							ScriptEvent var44;
							Widget var46;
							do {
								var44 = (ScriptEvent)field694.removeLast();
								if (var44 == null) {
									while (true) {
										do {
											var44 = (ScriptEvent)field695.removeLast();
											if (var44 == null) {
												while (true) {
													do {
														var44 = (ScriptEvent)scriptEvents.removeLast();
														if (var44 == null) {
															this.menu();
															class154.method3127();
															if (clickedWidget != null) {
																this.method1159();
															}

															if (FloorDecoration.dragInventoryWidget != null) {
																class290.invalidateWidget(FloorDecoration.dragInventoryWidget);
																++itemDragDuration;
																if (MouseHandler.MouseHandler_currentButton == 0) {
																	if (field601) {
																		if (FloorDecoration.dragInventoryWidget == GrandExchangeEvent.hoveredItemContainer && dragItemSlotSource != dragItemSlotDestination) {
																			Widget var47 = FloorDecoration.dragInventoryWidget;
																			byte var36 = 0;
																			if (field655 == 1 && var47.contentType == 206) {
																				var36 = 1;
																			}

																			if (var47.itemIds[dragItemSlotDestination] <= 0) {
																				var36 = 0;
																			}

																			if (class239.method4998(WorldMapSection2.getWidgetFlags(var47))) {
																				var6 = dragItemSlotSource;
																				var7 = dragItemSlotDestination;
																				var47.itemIds[var7] = var47.itemIds[var6];
																				var47.itemQuantities[var7] = var47.itemQuantities[var6];
																				var47.itemIds[var6] = -1;
																				var47.itemQuantities[var6] = 0;
																			} else if (var36 == 1) {
																				var6 = dragItemSlotSource;
																				var7 = dragItemSlotDestination;

																				while (var7 != var6) {
																					if (var6 > var7) {
																						var47.swapItems(var6 - 1, var6);
																						--var6;
																					} else if (var6 < var7) {
																						var47.swapItems(var6 + 1, var6);
																						++var6;
																					}
																				}
																			} else {
																				var47.swapItems(dragItemSlotDestination, dragItemSlotSource);
																			}

																			var18 = ItemContainer.getPacketBufferNode(ClientPacket.field2996, packetWriter.isaacCipher);
																			var18.packetBuffer.method7791(dragItemSlotDestination);
																			var18.packetBuffer.method7795(dragItemSlotSource);
																			var18.packetBuffer.method7806(FloorDecoration.dragInventoryWidget.id);
																			var18.packetBuffer.method7787(var36);
																			packetWriter.addNode(var18);
																		}
																	} else if (this.shouldLeftClickOpenMenu()) {
																		this.openMenu(draggedWidgetX, draggedWidgetY);
																	} else if (menuOptionsCount > 0) {
																		UserComparator8.method2576(draggedWidgetX, draggedWidgetY);
																	}

																	field595 = 10;
																	MouseHandler.MouseHandler_lastButton = 0;
																	FloorDecoration.dragInventoryWidget = null;
																} else if (itemDragDuration >= 5 && (MouseHandler.MouseHandler_x > draggedWidgetX + 5 || MouseHandler.MouseHandler_x < draggedWidgetX - 5 || MouseHandler.MouseHandler_y > draggedWidgetY + 5 || MouseHandler.MouseHandler_y < draggedWidgetY - 5)) {
																	field601 = true;
																}
															}

															if (Scene.shouldSendWalk()) {
																var4 = Scene.Scene_selectedX;
																var5 = Scene.Scene_selectedY;
																var18 = ItemContainer.getPacketBufferNode(ClientPacket.field2911, packetWriter.isaacCipher);
																var18.packetBuffer.writeByte(5);
																var18.packetBuffer.method7795(var4 + ApproximateRouteStrategy.baseX);
																var18.packetBuffer.writeShort(var5 + class250.baseY);
																var18.packetBuffer.method7788(KeyHandler.KeyHandler_pressedKeys[82] ? (KeyHandler.KeyHandler_pressedKeys[81] ? 2 : 1) : 0);
																packetWriter.addNode(var18);
																Scene.method4167();
																mouseCrossX = MouseHandler.MouseHandler_lastPressedX;
																mouseCrossY = MouseHandler.MouseHandler_lastPressedY;
																mouseCrossColor = 1;
																mouseCrossState = 0;
																destinationX = var4;
																destinationY = var5;
															}

															if (var39 != Player.mousedOverWidgetIf1) {
																if (var39 != null) {
																	class290.invalidateWidget(var39);
																}

																if (Player.mousedOverWidgetIf1 != null) {
																	class290.invalidateWidget(Player.mousedOverWidgetIf1);
																}
															}

															if (var34 != class160.field1750 && field641 == field642) {
																if (var34 != null) {
																	class290.invalidateWidget(var34);
																}

																if (class160.field1750 != null) {
																	class290.invalidateWidget(class160.field1750);
																}
															}

															if (class160.field1750 != null) {
																if (field641 < field642) {
																	++field641;
																	if (field642 == field641) {
																		class290.invalidateWidget(class160.field1750);
																	}
																}
															} else if (field641 > 0) {
																--field641;
															}

															if (oculusOrbState == 0) {
																var4 = class19.localPlayer.x;
																var5 = class19.localPlayer.y;
																if (Messages.oculusOrbFocalPointX - var4 < -500 || Messages.oculusOrbFocalPointX - var4 > 500 || class115.oculusOrbFocalPointY - var5 < -500 || class115.oculusOrbFocalPointY - var5 > 500) {
																	Messages.oculusOrbFocalPointX = var4;
																	class115.oculusOrbFocalPointY = var5;
																}

																if (var4 != Messages.oculusOrbFocalPointX) {
																	Messages.oculusOrbFocalPointX += (var4 - Messages.oculusOrbFocalPointX) / 16;
																}

																if (var5 != class115.oculusOrbFocalPointY) {
																	class115.oculusOrbFocalPointY += (var5 - class115.oculusOrbFocalPointY) / 16;
																}

																var6 = Messages.oculusOrbFocalPointX >> 7;
																var7 = class115.oculusOrbFocalPointY >> 7;
																var8 = Archive.getTileHeight(Messages.oculusOrbFocalPointX, class115.oculusOrbFocalPointY, class160.Client_plane);
																var9 = 0;
																if (var6 > 3 && var7 > 3 && var6 < 100 && var7 < 100) {
																	for (var10 = var6 - 4; var10 <= var6 + 4; ++var10) {
																		for (var11 = var7 - 4; var11 <= var7 + 4; ++var11) {
																			var12 = class160.Client_plane;
																			if (var12 < 3 && (Tiles.Tiles_renderFlags[1][var10][var11] & 2) == 2) {
																				++var12;
																			}

																			int var26 = var8 - Tiles.Tiles_heights[var12][var10][var11];
																			if (var26 > var9) {
																				var9 = var26;
																			}
																		}
																	}
																}

																var10 = var9 * 192;
																if (var10 > 98048) {
																	var10 = 98048;
																}

																if (var10 < 32768) {
																	var10 = 32768;
																}

																if (var10 > field756) {
																	field756 += (var10 - field756) / 24;
																} else if (var10 < field756) {
																	field756 += (var10 - field756) / 80;
																}

																FloorOverlayDefinition.field2136 = Archive.getTileHeight(class19.localPlayer.x, class19.localPlayer.y, class160.Client_plane) - camFollowHeight;
															} else if (oculusOrbState == 1) {
																class391.method7169();
																short var37 = -1;
																if (KeyHandler.KeyHandler_pressedKeys[33]) {
																	var37 = 0;
																} else if (KeyHandler.KeyHandler_pressedKeys[49]) {
																	var37 = 1024;
																}

																if (KeyHandler.KeyHandler_pressedKeys[48]) {
																	if (var37 == 0) {
																		var37 = 1792;
																	} else if (var37 == 1024) {
																		var37 = 1280;
																	} else {
																		var37 = 1536;
																	}
																} else if (KeyHandler.KeyHandler_pressedKeys[50]) {
																	if (var37 == 0) {
																		var37 = 256;
																	} else if (var37 == 1024) {
																		var37 = 768;
																	} else {
																		var37 = 512;
																	}
																}

																byte var38 = 0;
																if (KeyHandler.KeyHandler_pressedKeys[35]) {
																	var38 = -1;
																} else if (KeyHandler.KeyHandler_pressedKeys[51]) {
																	var38 = 1;
																}

																var6 = 0;
																if (var37 >= 0 || var38 != 0) {
																	var6 = KeyHandler.KeyHandler_pressedKeys[81] ? oculusOrbSlowedSpeed * -881862903 * 881911609 : oculusOrbNormalSpeed * -1286782535 * -924814199;
																	var6 *= 16;
																	field567 = var37;
																	field696 = var38;
																}

																if (field566 < var6) {
																	field566 += var6 / 8;
																	if (field566 > var6) {
																		field566 = var6;
																	}
																} else if (field566 > var6) {
																	field566 = field566 * 9 / 10;
																}

																if (field566 > 0) {
																	var7 = field566 / 16;
																	if (field567 >= 0) {
																		var4 = field567 - MusicPatchNode2.cameraYaw & 2047;
																		var8 = Rasterizer3D.Rasterizer3D_sine[var4];
																		var9 = Rasterizer3D.Rasterizer3D_cosine[var4];
																		Messages.oculusOrbFocalPointX += var7 * var8 / 65536;
																		class115.oculusOrbFocalPointY += var7 * var9 / 65536;
																	}

																	if (field696 != 0) {
																		FloorOverlayDefinition.field2136 += var7 * field696;
																		if (FloorOverlayDefinition.field2136 > 0) {
																			FloorOverlayDefinition.field2136 = 0;
																		}
																	}
																} else {
																	field567 = -1;
																	field696 = -1;
																}

																if (KeyHandler.KeyHandler_pressedKeys[13]) {
																	class291.method5589();
																}
															}

															if (MouseHandler.MouseHandler_currentButton == 4 && UserComparator5.mouseCam) {
																var4 = MouseHandler.MouseHandler_y - mouseCamClickedY;
																camAngleDX = var4 * 2;
																mouseCamClickedY = var4 != -1 && var4 != 1 ? (mouseCamClickedY + MouseHandler.MouseHandler_y) / 2 : MouseHandler.MouseHandler_y * -501791789 * -601944997;
																var5 = mouseCamClickedX - MouseHandler.MouseHandler_x;
																camAngleDY = var5 * 2;
																mouseCamClickedX = var5 != -1 && var5 != 1 ? (mouseCamClickedX + MouseHandler.MouseHandler_x) / 2 : MouseHandler.MouseHandler_x * 1113383001 * -833329175;
															} else {
																if (KeyHandler.KeyHandler_pressedKeys[96]) {
																	camAngleDY += (-24 - camAngleDY) / 2;
																} else if (KeyHandler.KeyHandler_pressedKeys[97]) {
																	camAngleDY += (24 - camAngleDY) / 2;
																} else {
																	camAngleDY /= 2;
																}

																if (KeyHandler.KeyHandler_pressedKeys[98]) {
																	camAngleDX += (12 - camAngleDX) / 2;
																} else if (KeyHandler.KeyHandler_pressedKeys[99]) {
																	camAngleDX += (-12 - camAngleDX) / 2;
																} else {
																	camAngleDX /= 2;
																}

																mouseCamClickedY = MouseHandler.MouseHandler_y;
																mouseCamClickedX = MouseHandler.MouseHandler_x;
															}

															camAngleY = camAngleDY / 2 + camAngleY & 2047;
															camAngleX += camAngleDX / 2;
															if (camAngleX < 128) {
																camAngleX = 128;
															}

															if (camAngleX > 383) {
																camAngleX = 383;
															}

															if (field739) {
																class291.method5587();
															} else if (isCameraLocked) {
																class147.method3052();
															}

															for (var4 = 0; var4 < 5; ++var4) {
																var10002 = field744[var4]++;
															}

															JagexCache.varcs.tryWrite();
															var4 = ++MouseHandler.MouseHandler_idleCycles - 1;
															var6 = class18.method294();
															PacketBufferNode var27;
															if (var4 > 15000 && var6 > 15000) {
																logoutTimer = 250;
																ItemComposition.method3764(14500);
																var27 = ItemContainer.getPacketBufferNode(ClientPacket.field2979, packetWriter.isaacCipher);
																packetWriter.addNode(var27);
															}

															class155.friendSystem.processFriendUpdates();
															++packetWriter.pendingWrites;
															if (packetWriter.pendingWrites > 50) {
																var27 = ItemContainer.getPacketBufferNode(ClientPacket.field2950, packetWriter.isaacCipher);
																packetWriter.addNode(var27);
															}

															try {
																packetWriter.flush();
															} catch (IOException var28) {
																class9.method64();
															}

															return;
														}

														var46 = var44.widget;
														if (var46.childIndex < 0) {
															break;
														}

														var41 = HorizontalAlignment.getWidget(var46.parentId);
													} while(var41 == null || var41.children == null || var46.childIndex >= var41.children.length || var46 != var41.children[var46.childIndex]);

													class1.runScriptEvent(var44);
												}
											}

											var46 = var44.widget;
											if (var46.childIndex < 0) {
												break;
											}

											var41 = HorizontalAlignment.getWidget(var46.parentId);
										} while(var41 == null || var41.children == null || var46.childIndex >= var41.children.length || var46 != var41.children[var46.childIndex]);

										class1.runScriptEvent(var44);
									}
								}

								var46 = var44.widget;
								if (var46.childIndex < 0) {
									break;
								}

								var41 = HorizontalAlignment.getWidget(var46.parentId);
							} while(var41 == null || var41.children == null || var46.childIndex >= var41.children.length || var46 != var41.children[var46.childIndex]);

							class1.runScriptEvent(var44);
						}
					}

					var31 = ItemContainer.getPacketBufferNode(ClientPacket.field2914, packetWriter.isaacCipher);
					var31.packetBuffer.writeByte(0);
					var3 = var31.packetBuffer.offset;
					WorldMapAreaData.performReflectionCheck(var31.packetBuffer);
					var31.packetBuffer.method7756(var31.packetBuffer.offset - var3);
					packetWriter.addNode(var31);
				}
			}
		}
	}

	void resizeJS() {
		int var1 = class4.canvasWidth;
		int var2 = class309.canvasHeight;
		if (super.contentWidth < var1) {
			var1 = super.contentWidth;
		}

		if (super.contentHeight < var2) {
			var2 = super.contentHeight;
		}

		if (Interpreter.clientPreferences != null) {
			try {
				class27.method412(UserComparator10.client, "resize", new Object[]{ReflectionCheck.getWindowedMode()});
			} catch (Throwable var4) {
			}
		}

	}

	final void drawLoggedIn() {
		int var1;
		if (rootInterface != -1) {
			var1 = rootInterface;
			if (MusicPatchNode2.loadInterface(var1)) {
				ItemComposition.drawModelComponents(EnumComposition.Widget_interfaceComponents[var1], -1);
			}
		}

		for (var1 = 0; var1 < rootWidgetCount; ++var1) {
			if (field564[var1]) {
				field651[var1] = true;
			}

			field576[var1] = field564[var1];
			field564[var1] = false;
		}

		field698 = cycle;
		viewportX = -1;
		viewportY = -1;
		GrandExchangeEvent.hoveredItemContainer = null;
		if (rootInterface != -1) {
			rootWidgetCount = 0;
			class11.drawWidgets(rootInterface, 0, 0, class4.canvasWidth, class309.canvasHeight, 0, 0, -1);
		}

		Rasterizer2D.Rasterizer2D_resetClip();
		if (showMouseCross) {
			if (mouseCrossColor == 1) {
				crossSprites[mouseCrossState / 100].drawTransBgAt(mouseCrossX - 8, mouseCrossY - 8);
			}

			if (mouseCrossColor == 2) {
				crossSprites[mouseCrossState / 100 + 4].drawTransBgAt(mouseCrossX - 8, mouseCrossY - 8);
			}
		}

		int var2;
		int var3;
		if (!isMenuOpen) {
			if (viewportX != -1) {
				var1 = viewportX;
				var2 = viewportY;
				if ((menuOptionsCount >= 2 || isItemSelected != 0 || isSpellSelected) && showMouseOverText) {
					var3 = class168.method3324();
					String var11;
					if (isItemSelected == 1 && menuOptionsCount < 2) {
						var11 = "Use" + " " + selectedItemName + " " + "->";
					} else if (isSpellSelected && menuOptionsCount < 2) {
						var11 = selectedSpellActionName + " " + selectedSpellName + " " + "->";
					} else {
						var11 = ReflectionCheck.method637(var3);
					}

					if (menuOptionsCount > 2) {
						var11 = var11 + ChatChannel.colorStartTag(16777215) + " " + '/' + " " + (menuOptionsCount - 2) + " more options";
					}

					FloorOverlayDefinition.fontBold12.drawRandomAlphaAndSpacing(var11, var1 + 4, var2 + 15, 16777215, 0, cycle / 1000);
				}
			}
		} else {
			var1 = class307.menuX;
			var2 = ArchiveDiskActionHandler.menuY;
			var3 = class11.menuWidth;
			int var4 = UrlRequester.menuHeight;
			int var5 = 6116423;
			Rasterizer2D.Rasterizer2D_fillRectangle(var1, var2, var3, var4, var5);
			Rasterizer2D.Rasterizer2D_fillRectangle(var1 + 1, var2 + 1, var3 - 2, 16, 0);
			Rasterizer2D.Rasterizer2D_drawRectangle(var1 + 1, var2 + 18, var3 - 2, var4 - 19, 0);
			FloorOverlayDefinition.fontBold12.draw("Choose Option", var1 + 3, var2 + 14, var5, -1);
			int var6 = MouseHandler.MouseHandler_x;
			int var7 = MouseHandler.MouseHandler_y;

			for (int var8 = 0; var8 < menuOptionsCount; ++var8) {
				int var9 = var2 + (menuOptionsCount - 1 - var8) * 15 + 31;
				int var10 = 16777215;
				if (var6 > var1 && var6 < var1 + var3 && var7 > var9 - 13 && var7 < var9 + 3) {
					var10 = 16776960;
				}

				FloorOverlayDefinition.fontBold12.draw(ReflectionCheck.method637(var8), var1 + 3, var9, var10, 0);
			}

			LoginScreenAnimation.method2240(class307.menuX, ArchiveDiskActionHandler.menuY, class11.menuWidth, UrlRequester.menuHeight);
		}

		if (gameDrawingMode == 3) {
			for (var1 = 0; var1 < rootWidgetCount; ++var1) {
				if (field576[var1]) {
					Rasterizer2D.Rasterizer2D_fillRectangleAlpha(rootWidgetXs[var1], rootWidgetYs[var1], rootWidgetWidths[var1], rootWidgetHeights[var1], 16711935, 128);
				} else if (field651[var1]) {
					Rasterizer2D.Rasterizer2D_fillRectangleAlpha(rootWidgetXs[var1], rootWidgetYs[var1], rootWidgetWidths[var1], rootWidgetHeights[var1], 16711680, 128);
				}
			}
		}

		class268.method5232(class160.Client_plane, class19.localPlayer.x, class19.localPlayer.y, field550);
		field550 = 0;
	}

	final boolean method1153(PacketWriter packetWriter) {
		AbstractSocket var2 = packetWriter.getSocket();
		PacketBuffer packetBuf = packetWriter.packetBuffer;
		if (var2 == null) {
			return false;
		} else {
			String var21;
			int interfaceId;
			try {
				int component;
				if (packetWriter.serverPacket == null) {
					if (packetWriter.field1328) {
						if (!var2.isAvailable(1)) {
							return false;
						}

						var2.read(packetWriter.packetBuffer.array, 0, 1);
						packetWriter.field1326 = 0;
						packetWriter.field1328 = false;
					}

					packetBuf.offset = 0;
					if (packetBuf.method7701()) {
						if (!var2.isAvailable(1)) {
							return false;
						}

						var2.read(packetWriter.packetBuffer.array, 1, 1);
						packetWriter.field1326 = 0;
					}

					packetWriter.field1328 = true;
					ServerPacket[] var4 = Statics1.ServerPacket_values();
					component = packetBuf.readSmartByteShortIsaac();
					if (component < 0 || component >= var4.length) {
						throw new IOException(component + " " + packetBuf.offset);
					}

					packetWriter.serverPacket = var4[component];
					packetWriter.serverPacketLength = packetWriter.serverPacket.length;
				}

				if (packetWriter.serverPacketLength == -1) {
					if (!var2.isAvailable(1)) {
						return false;
					}

					packetWriter.getSocket().read(packetBuf.array, 0, 1);
					packetWriter.serverPacketLength = packetBuf.array[0] & 255;
				}

				if (packetWriter.serverPacketLength == -2) {
					if (!var2.isAvailable(2)) {
						return false;
					}

					packetWriter.getSocket().read(packetBuf.array, 0, 2);
					packetBuf.offset = 0;
					packetWriter.serverPacketLength = packetBuf.readUnsignedShort();
				}

				if (!var2.isAvailable(packetWriter.serverPacketLength)) {
					return false;
				}

				packetBuf.offset = 0;
				var2.read(packetBuf.array, 0, packetWriter.serverPacketLength);
				packetWriter.field1326 = 0;
				timer.method6594();
				packetWriter.field1331 = packetWriter.field1329;
				packetWriter.field1329 = packetWriter.field1320;
				packetWriter.field1320 = packetWriter.serverPacket;
				byte var70;
				if (ServerPacket.field3043 == packetWriter.serverPacket) {
					RouteStrategy.method3875();
					var70 = packetBuf.readByte();
					class131 var83 = new class131(packetBuf);
					ClanSettings var65;
					if (var70 >= 0) {
						var65 = currentClanSettings[var70];
					} else {
						var65 = class134.guestClanSettings;
					}

					var83.method2851(var65);
					packetWriter.serverPacket = null;
					return true;
				}

				int var7;
				int interfaceType;
				Widget var56;
				if (ServerPacket.field3052 == packetWriter.serverPacket) {
					interfaceType = packetBuf.readUnsignedShortLEAdd();
					component = packetBuf.readUnsignedShortAdd();
					interfaceId = packetBuf.readUnsignedShortAdd();
					var7 = packetBuf.method7797();
					var56 = HorizontalAlignment.getWidget(var7);
					if (interfaceId != var56.modelAngleX || component != var56.modelAngleY || interfaceType != var56.modelZoom) {
						var56.modelAngleX = interfaceId;
						var56.modelAngleY = component;
						var56.modelZoom = interfaceType;
						class290.invalidateWidget(var56);
					}

					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3061 == packetWriter.serverPacket) {
					class83.updatePlayers(packetBuf, packetWriter.serverPacketLength);
					class4.method19();
					packetWriter.serverPacket = null;
					return true;
				}

				Widget var6;
				boolean var71;
				if (ServerPacket.field3107 == packetWriter.serverPacket) {
					interfaceType = packetBuf.readIntME();
					var71 = packetBuf.readUnsignedByte() == 1;
					var6 = HorizontalAlignment.getWidget(interfaceType);
					if (var71 != var6.isHidden) {
						var6.isHidden = var71;
						class290.invalidateWidget(var6);
					}

					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3034 == packetWriter.serverPacket) {
					interfaceType = packetBuf.readUnsignedShort();
					byte var72 = packetBuf.method7792();
					Varps.Varps_temp[interfaceType] = var72;
					if (Varps.Varps_main[interfaceType] != var72) {
						Varps.Varps_main[interfaceType] = var72;
					}

					class78.changeGameOptions(interfaceType);
					changedVarps[++changedVarpCount - 1 & 31] = interfaceType;
					packetWriter.serverPacket = null;
					return true;
				}

				String var59;
				if (ServerPacket.field3032 == packetWriter.serverPacket) {
					interfaceType = packetBuf.readUShortSmart();
					var71 = packetBuf.readUnsignedByte() == 1;
					var59 = "";
					boolean var66 = false;
					if (var71) {
						var59 = packetBuf.readStringCp1252NullTerminated();
						if (class155.friendSystem.isIgnored(new Username(var59, class83.loginType))) {
							var66 = true;
						}
					}

					String var58 = packetBuf.readStringCp1252NullTerminated();
					if (!var66) {
						Login.addGameMessage(interfaceType, var59, var58);
					}

					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3058 == packetWriter.serverPacket) {
					interfaceType = packetBuf.readIntME();
					component = packetBuf.readUnsignedShort();
					Varps.Varps_temp[component] = interfaceType;
					if (Varps.Varps_main[component] != interfaceType) {
						Varps.Varps_main[component] = interfaceType;
					}

					class78.changeGameOptions(component);
					changedVarps[++changedVarpCount - 1 & 31] = component;
					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3038 == packetWriter.serverPacket) {
					interfaceType = packetBuf.readIntLE();
					var21 = packetBuf.readStringCp1252NullTerminated();
					var6 = HorizontalAlignment.getWidget(interfaceType);
					if (!var21.equals(var6.text)) {
						var6.text = var21;
						class290.invalidateWidget(var6);
					}

					packetWriter.serverPacket = null;
					return true;
				}

				int var9;
				int var10;
				int var23;
				if (ServerPacket.field3079 == packetWriter.serverPacket) {
					isCameraLocked = true;
					field739 = false;
					class12.field62 = packetBuf.readUnsignedByte();
					WorldMapSectionType.field2778 = packetBuf.readUnsignedByte();
					class351.field4157 = packetBuf.readUnsignedShort();
					ClanChannel.field1660 = packetBuf.readUnsignedByte();
					JagexCache.field1737 = packetBuf.readUnsignedByte();
					if (JagexCache.field1737 >= 100) {
						interfaceType = class12.field62 * 128 + 64;
						component = WorldMapSectionType.field2778 * 128 + 64;
						interfaceId = Archive.getTileHeight(interfaceType, component, class160.Client_plane) - class351.field4157;
						var7 = interfaceType - EnumComposition.cameraX;
						var23 = interfaceId - FriendSystem.cameraY;
						var9 = component - CollisionMap.cameraZ;
						var10 = (int)Math.sqrt(var9 * var9 + var7 * var7);
						Language.cameraPitch = (int)(Math.atan2(var23, var10) * 325.9490051269531D) & 2047;
						MusicPatchNode2.cameraYaw = (int)(Math.atan2(var7, var9) * -325.9490051269531D) & 2047;
						if (Language.cameraPitch < 128) {
							Language.cameraPitch = 128;
						}

						if (Language.cameraPitch > 383) {
							Language.cameraPitch = 383;
						}
					}

					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3103 == packetWriter.serverPacket) {
					interfaceType = packetBuf.readInt();
					component = packetBuf.readInt();
					interfaceId = NetCache.getGcDuration();
					PacketBufferNode var84 = ItemContainer.getPacketBufferNode(ClientPacket.field2983, Client.packetWriter.isaacCipher);
					var84.packetBuffer.method7746(interfaceType);
					var84.packetBuffer.method7806(component);
					var84.packetBuffer.method7788(GameEngine.fps);
					var84.packetBuffer.method7788(interfaceId);
					Client.packetWriter.addNode(var84);
					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3042 == packetWriter.serverPacket) {
					class9.field34 = packetBuf.method7789();
					FriendSystem.field803 = packetBuf.method7927();

					while (packetBuf.offset < packetWriter.serverPacketLength) {
						interfaceType = packetBuf.readUnsignedByte();
						class263 var81 = Language.method6134()[interfaceType];
						class221.method4531(var81);
					}

					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3095 == packetWriter.serverPacket) {
					GameEngine.field205 = null;
					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3040 == packetWriter.serverPacket) {
					class9.field34 = packetBuf.method7789();
					FriendSystem.field803 = packetBuf.readUnsignedByte();

					for (interfaceType = FriendSystem.field803; interfaceType < FriendSystem.field803 + 8; ++interfaceType) {
						for (component = class9.field34; component < class9.field34 + 8; ++component) {
							if (groundItems[class160.Client_plane][interfaceType][component] != null) {
								groundItems[class160.Client_plane][interfaceType][component] = null;
								class162.updateItemPile(interfaceType, component);
							}
						}
					}

					for (PendingSpawn var54 = (PendingSpawn)pendingSpawns.last(); var54 != null; var54 = (PendingSpawn)pendingSpawns.previous()) {
						if (var54.x >= FriendSystem.field803 && var54.x < FriendSystem.field803 + 8 && var54.y >= class9.field34 && var54.y < class9.field34 + 8 && var54.plane == class160.Client_plane) {
							var54.hitpoints = 0;
						}
					}

					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3114 == packetWriter.serverPacket) {
					class221.method4531(class263.field3012);
					packetWriter.serverPacket = null;
					return true;
				}

				long var25;
				long var27;
				String var32;
				String var50;
				int var68;
				if (ServerPacket.field3048 == packetWriter.serverPacket) {
					var50 = packetBuf.readStringCp1252NullTerminated();
					var25 = packetBuf.readUnsignedShort();
					var27 = packetBuf.readMedium();
					PlayerType var29 = (PlayerType)ChatChannel.findEnumerated(HitSplatDefinition.PlayerType_values(), packetBuf.readUnsignedByte());
					long var30 = var27 + (var25 << 32);
					boolean var62 = false;

					for (var68 = 0; var68 < 100; ++var68) {
						if (var30 == crossWorldMessageIds[var68]) {
							var62 = true;
							break;
						}
					}

					if (class155.friendSystem.isIgnored(new Username(var50, class83.loginType))) {
						var62 = true;
					}

					if (!var62 && field603 == 0) {
						crossWorldMessageIds[crossWorldMessageIdsIndex] = var30;
						crossWorldMessageIdsIndex = (crossWorldMessageIdsIndex + 1) % 100;
						var32 = AbstractFont.escapeBrackets(AbstractByteArrayCopier.method5528(class118.method2737(packetBuf)));
						byte var69;
						if (var29.isPrivileged) {
							var69 = 7;
						} else {
							var69 = 3;
						}

						if (var29.modIcon != -1) {
							Login.addGameMessage(var69, class351.method6579(var29.modIcon) + var50, var32);
						} else {
							Login.addGameMessage(var69, var50, var32);
						}
					}

					packetWriter.serverPacket = null;
					return true;
				}

				int var12;
				int var14;
				int var15;
				int var16;
				int var61;
				if (ServerPacket.field3090 == packetWriter.serverPacket) {
					int var18 = packetBuf.readUnsignedByte();
					var16 = packetBuf.readUnsignedShortLEAdd();
					interfaceId = packetBuf.method7805();
					interfaceType = interfaceId >> 16;
					component = interfaceId >> 8 & 255;
					var7 = interfaceType + (interfaceId >> 4 & 7);
					var23 = component + (interfaceId & 7);
					byte var63 = packetBuf.readByte();
					var12 = packetBuf.readUnsignedShortLEAdd();
					var68 = packetBuf.method7789() * 4;
					byte var64 = packetBuf.method7792();
					var15 = packetBuf.readUnsignedShortLE();
					int var17 = packetBuf.readUnsignedByte();
					var14 = packetBuf.method7927() * 4;
					var61 = packetBuf.method7769();
					var9 = var63 + var7;
					var10 = var64 + var23;
					if (var7 >= 0 && var23 >= 0 && var7 < 104 && var23 < 104 && var9 >= 0 && var10 >= 0 && var9 < 104 && var10 < 104 && var12 != 65535) {
						var7 = var7 * 128 + 64;
						var23 = var23 * 128 + 64;
						var9 = var9 * 128 + 64;
						var10 = var10 * 128 + 64;
						Projectile var19 = new Projectile(var12, class160.Client_plane, var7, var23, Archive.getTileHeight(var7, var23, class160.Client_plane) - var68, var15 + cycle, var16 + cycle, var17, var18, var61, var14);
						var19.setDestination(var9, var10, Archive.getTileHeight(var9, var10, class160.Client_plane) - var14, var15 + cycle);
						projectiles.addFirst(var19);
					}

					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3051 == packetWriter.serverPacket) {
					isCameraLocked = false;

					for (interfaceType = 0; interfaceType < 5; ++interfaceType) {
						field740[interfaceType] = false;
					}

					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3117 == packetWriter.serverPacket) {
					if (Statics1.friendsChat != null) {
						Statics1.friendsChat.method6718(packetBuf);
					}

					Strings.method5798();
					packetWriter.serverPacket = null;
					return true;
				}

				InterfaceParent var82;
				if (ServerPacket.IF_MOVE == packetWriter.serverPacket) {
					interfaceType = packetBuf.readIntLE();
					component = packetBuf.readIntME();

					/*int fromParent = (interfaceType >> 16) & 0xFFFF;
					int fromChild = interfaceType & 0xFFFF;
					int toParent = (component >> 16) & 0xFFFF;
					int toChild = component & 0xFFFF;
					System.out.println("[IF_MOVE] : [from: [parent: "+fromParent+", child: "+fromChild+"], to: [parent: "+toParent+", child: "+toChild+"]]");*/

					InterfaceParent var60 = (InterfaceParent)interfaceParents.get(interfaceType);
					var82 = (InterfaceParent)interfaceParents.get(component);
					if (var82 != null) {
						class20.closeInterface(var82, var60 == null || var82.group != var60.group);
					}

					if (var60 != null) {
						var60.remove();
						interfaceParents.put(var60, component);
					}

					var56 = HorizontalAlignment.getWidget(interfaceType);
					if (var56 != null) {
						class290.invalidateWidget(var56);
					}

					var56 = HorizontalAlignment.getWidget(component);
					if (var56 != null) {
						class290.invalidateWidget(var56);
						GrandExchangeEvents.revalidateWidgetScroll(EnumComposition.Widget_interfaceComponents[var56.id >>> 16], var56, true);
					}

					if (rootInterface != -1) {
						MouseHandler.runIntfCloseListeners(rootInterface, 1);
					}

					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3100 == packetWriter.serverPacket && isCameraLocked) {
					field739 = true;
					Tiles.field996 = packetBuf.readUnsignedByte();
					class33.field231 = packetBuf.readUnsignedByte();
					ClanChannel.field1660 = packetBuf.readUnsignedByte();
					JagexCache.field1737 = packetBuf.readUnsignedByte();

					for (interfaceType = 0; interfaceType < 5; ++interfaceType) {
						field740[interfaceType] = false;
					}

					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3030 == packetWriter.serverPacket) {
					DynamicObject.updateNpcs(false, packetBuf);
					packetWriter.serverPacket = null;
					return true;
				}

				boolean var87;
				if (ServerPacket.field3036 == packetWriter.serverPacket) {
					var87 = packetBuf.readUnsignedByte() == 1;
					if (var87) {
						Varps.field3287 = WorldMapSprite.cycleTimer() - packetBuf.readLong();
						ReflectionCheck.grandExchangeEvents = new GrandExchangeEvents(packetBuf, true);
					} else {
						ReflectionCheck.grandExchangeEvents = null;
					}

					field572 = cycleCntr;
					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3065 == packetWriter.serverPacket) {
					class221.method4531(class263.field3010);
					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3116 == packetWriter.serverPacket) {
					interfaceType = packetBuf.readUnsignedByte();
					component = packetBuf.readUnsignedByte();
					interfaceId = packetBuf.readUnsignedByte();
					var7 = packetBuf.readUnsignedByte();
					field740[interfaceType] = true;
					field482[interfaceType] = component;
					field549[interfaceType] = interfaceId;
					field766[interfaceType] = var7;
					field744[interfaceType] = 0;
					packetWriter.serverPacket = null;
					return true;
				}

				Widget var80;
				if (ServerPacket.field3072 == packetWriter.serverPacket) {
					interfaceType = packetBuf.readUnsignedShortAdd();
					component = packetBuf.readUnsignedShortLEAdd();
					interfaceId = packetBuf.readIntME();
					var80 = HorizontalAlignment.getWidget(interfaceId);
					var80.field3399 = component + (interfaceType << 16);
					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3110 == packetWriter.serverPacket) {
					World var53 = new World();
					var53.host = packetBuf.readStringCp1252NullTerminated();
					var53.id = packetBuf.readUnsignedShort();
					component = packetBuf.readInt();
					var53.properties = component;
					InterfaceParent.updateGameState(45);
					var2.close();
					var2 = null;
					ItemContainer.changeWorld(var53);
					packetWriter.serverPacket = null;
					return false;
				}

				if (ServerPacket.field3085 == packetWriter.serverPacket) {
					packetBuf.offset += 28;
					if (packetBuf.checkCrc()) {
						WorldMapRegion.method4798(packetBuf, packetBuf.offset - 28);
					}

					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3041 == packetWriter.serverPacket) {
					interfaceType = packetBuf.method7797();
					component = packetBuf.readShort();
					interfaceId = packetBuf.method7769();
					var80 = HorizontalAlignment.getWidget(interfaceType);
					if (component != var80.rawX || interfaceId != var80.rawY || var80.xAlignment != 0 || var80.yAlignment != 0) {
						var80.rawX = component;
						var80.rawY = interfaceId;
						var80.xAlignment = 0;
						var80.yAlignment = 0;
						class290.invalidateWidget(var80);
						this.alignWidget(var80);
						if (var80.type == 0) {
							GrandExchangeEvents.revalidateWidgetScroll(EnumComposition.Widget_interfaceComponents[interfaceType >> 16], var80, false);
						}
					}

					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3033 == packetWriter.serverPacket) {
					var87 = packetBuf.readBoolean();
					if (var87) {
						if (class340.field4109 == null) {
							class340.field4109 = new class326();
						}
					} else {
						class340.field4109 = null;
					}

					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.IF_OPEN_SUB == packetWriter.serverPacket) {
					interfaceType = packetBuf.readUnsignedByte();
					component = packetBuf.readInt();
					interfaceId = packetBuf.readUnsignedShortLEAdd();

					/*int parent = (component >> 16) & 0xFFFF;
					int child = component & 0xFFFF;
					System.out.println("[IF_OPEN_SUB] : [parent: " + parent + ", child: " + child + ", interfaceId: " + interfaceId);*/

					var82 = (InterfaceParent)interfaceParents.get(component);
					if (var82 != null) {
						class20.closeInterface(var82, interfaceId != var82.group);
					}

					ServerPacket.method5222(component, interfaceId, interfaceType);
					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3057 == packetWriter.serverPacket) {
					GameObject.logOut();
					packetWriter.serverPacket = null;
					return false;
				}

				if (ServerPacket.rebuildRegionOther == packetWriter.serverPacket) {
					class146.rebuildRegion(true, packetWriter.packetBuffer);
					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3027 == packetWriter.serverPacket) {
					class221.method4531(class263.field3006);
					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3060 == packetWriter.serverPacket) {
					Varps.privateChatMode = class83.method2124(packetBuf.readUnsignedByte());
					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3091 == packetWriter.serverPacket) {
					class221.method4531(class263.field3013);
					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3126 == packetWriter.serverPacket) {
					interfaceType = packetBuf.readUnsignedShortLE();
					component = packetBuf.readInt();
					interfaceId = interfaceType >> 10 & 31;
					var7 = interfaceType >> 5 & 31;
					var23 = interfaceType & 31;
					var9 = (var7 << 11) + (interfaceId << 19) + (var23 << 3);
					Widget var86 = HorizontalAlignment.getWidget(component);
					if (var9 != var86.color) {
						var86.color = var9;
						class290.invalidateWidget(var86);
					}

					packetWriter.serverPacket = null;
					return true;
				}

				long var34;
				if (ServerPacket.field3071 == packetWriter.serverPacket) {
					interfaceType = packetBuf.readIntME();
					component = packetBuf.readUnsignedShortLE();
					if (component == 65535) {
						component = -1;
					}

					interfaceId = packetBuf.readUnsignedShortLEAdd();
					if (interfaceId == 65535) {
						interfaceId = -1;
					}

					var7 = packetBuf.readInt();

					for (var23 = component; var23 <= interfaceId; ++var23) {
						var34 = (long)var23 + ((long)var7 << 32);
						Node var88 = widgetFlags.get(var34);
						if (var88 != null) {
							var88.remove();
						}

						widgetFlags.put(new IntegerNode(interfaceType), var34);
					}

					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3078 == packetWriter.serverPacket) {
					DynamicObject.updateNpcs(true, packetBuf);
					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3124 == packetWriter.serverPacket) {
					interfaceType = packetBuf.readInt();
					component = packetBuf.readUnsignedShort();
					if (interfaceType < -70000) {
						component += 32768;
					}

					if (interfaceType >= 0) {
						var6 = HorizontalAlignment.getWidget(interfaceType);
					} else {
						var6 = null;
					}

					if (var6 != null) {
						for (var7 = 0; var7 < var6.itemIds.length; ++var7) {
							var6.itemIds[var7] = 0;
							var6.itemQuantities[var7] = 0;
						}
					}

					TileItem.clearItemContainer(component);
					var7 = packetBuf.readUnsignedShort();

					for (var23 = 0; var23 < var7; ++var23) {
						var9 = packetBuf.readUnsignedShort();
						var10 = packetBuf.method7789();
						if (var10 == 255) {
							var10 = packetBuf.readInt();
						}

						if (var6 != null && var23 < var6.itemIds.length) {
							var6.itemIds[var23] = var9;
							var6.itemQuantities[var23] = var10;
						}

						class29.itemContainerSetItem(component, var23, var9 - 1, var10);
					}

					if (var6 != null) {
						class290.invalidateWidget(var6);
					}

					SceneTilePaint.method4499();
					changedItemContainers[++field746 - 1 & 31] = component & 32767;
					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3086 == packetWriter.serverPacket) {
					interfaceId = packetBuf.method7877();
					interfaceType = interfaceId >> 16;
					component = interfaceId >> 8 & 255;
					var7 = interfaceType + (interfaceId >> 4 & 7);
					var23 = component + (interfaceId & 7);
					var10 = packetBuf.readUnsignedByte();
					var61 = packetBuf.readUnsignedShort();
					var9 = packetBuf.readUnsignedShortLEAdd();
					if (var7 >= 0 && var23 >= 0 && var7 < 104 && var23 < 104) {
						var7 = var7 * 128 + 64;
						var23 = var23 * 128 + 64;
						GraphicsObject var89 = new GraphicsObject(var9, class160.Client_plane, var7, var23, Archive.getTileHeight(var7, var23, class160.Client_plane) - var10, var61, cycle);
						graphicsObjects.addFirst(var89);
					}

					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3129 == packetWriter.serverPacket) {
					interfaceType = packetBuf.readInt();
					if (interfaceType != field571) {
						field571 = interfaceType;
						class11.method115();
					}

					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3064 == packetWriter.serverPacket) {
					class221.method4531(class263.field3009);
					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3025 == packetWriter.serverPacket) {
					byte[] var52 = new byte[packetWriter.serverPacketLength];
					packetBuf.method7708(var52, 0, var52.length);
					Buffer var79 = new Buffer(var52);
					var59 = var79.readStringCp1252NullTerminated();
					class11.openURL(var59, true, false);
					packetWriter.serverPacket = null;
					return true;
				}

				Widget var74;
				if (ServerPacket.field3035 == packetWriter.serverPacket) {
					interfaceType = packetBuf.method7797();
					var74 = HorizontalAlignment.getWidget(interfaceType);
					var74.modelType = 3;
					var74.modelId = class19.localPlayer.appearance.getChatHeadId();
					class290.invalidateWidget(var74);
					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3099 == packetWriter.serverPacket) {
					interfaceType = packetBuf.readInt();
					InterfaceParent var78 = (InterfaceParent)interfaceParents.get(interfaceType);
					if (var78 != null) {
						class20.closeInterface(var78, true);
					}

					if (meslayerContinueWidget != null) {
						class290.invalidateWidget(meslayerContinueWidget);
						meslayerContinueWidget = null;
					}

					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3076 == packetWriter.serverPacket) {
					field749 = cycleCntr;
					var70 = packetBuf.readByte();
					class145 var76 = new class145(packetBuf);
					ClanChannel var57;
					if (var70 >= 0) {
						var57 = currentClanChannels[var70];
					} else {
						var57 = class83.guestClanChannel;
					}

					var76.method3030(var57);
					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3054 == packetWriter.serverPacket) {
					field749 = cycleCntr;
					var70 = packetBuf.readByte();
					if (packetWriter.serverPacketLength == 1) {
						if (var70 >= 0) {
							currentClanChannels[var70] = null;
						} else {
							class83.guestClanChannel = null;
						}

						packetWriter.serverPacket = null;
						return true;
					}

					if (var70 >= 0) {
						currentClanChannels[var70] = new ClanChannel(packetBuf);
					} else {
						class83.guestClanChannel = new ClanChannel(packetBuf);
					}

					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3096 == packetWriter.serverPacket) {
					for (interfaceType = 0; interfaceType < Varps.Varps_main.length; ++interfaceType) {
						if (Varps.Varps_temp[interfaceType] != Varps.Varps_main[interfaceType]) {
							Varps.Varps_main[interfaceType] = Varps.Varps_temp[interfaceType];
							class78.changeGameOptions(interfaceType);
							changedVarps[++changedVarpCount - 1 & 31] = interfaceType;
						}
					}

					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3045 == packetWriter.serverPacket) {
					RouteStrategy.method3875();
					var70 = packetBuf.readByte();
					if (packetWriter.serverPacketLength == 1) {
						if (var70 >= 0) {
							currentClanSettings[var70] = null;
						} else {
							class134.guestClanSettings = null;
						}

						packetWriter.serverPacket = null;
						return true;
					}

					if (var70 >= 0) {
						currentClanSettings[var70] = new ClanSettings(packetBuf);
					} else {
						class134.guestClanSettings = new ClanSettings(packetBuf);
					}

					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3028 == packetWriter.serverPacket) {
					rebootTimer = packetBuf.readUnsignedShort() * 30;
					field762 = cycleCntr;
					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3118 == packetWriter.serverPacket) {
					interfaceType = packetBuf.readUnsignedShortLEAdd();
					component = packetBuf.readIntME();
					var6 = HorizontalAlignment.getWidget(component);
					if (var6.modelType != 2 || interfaceType != var6.modelId) {
						var6.modelType = 2;
						var6.modelId = interfaceType;
						class290.invalidateWidget(var6);
					}

					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3121 == packetWriter.serverPacket) {
					interfaceType = packetBuf.method7797();
					component = packetBuf.readUnsignedShort();
					var6 = HorizontalAlignment.getWidget(interfaceType);
					if (var6.modelType != 1 || component != var6.modelId) {
						var6.modelType = 1;
						var6.modelId = component;
						class290.invalidateWidget(var6);
					}

					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3024 == packetWriter.serverPacket) {
					minimapState = packetBuf.readUnsignedByte();
					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3075 == packetWriter.serverPacket) {
					interfaceType = packetBuf.readUnsignedShort();
					Language.method6136(interfaceType);
					changedItemContainers[++field746 - 1 & 31] = interfaceType & 32767;
					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3101 == packetWriter.serverPacket) {
					interfaceType = packetBuf.readUnsignedShort();
					component = packetBuf.readUnsignedByte();
					interfaceId = packetBuf.readUnsignedShort();
					KitDefinition.queueSoundEffect(interfaceType, component, interfaceId);
					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3098 == packetWriter.serverPacket) {
					for (interfaceType = 0; interfaceType < players.length; ++interfaceType) {
						if (players[interfaceType] != null) {
							players[interfaceType].sequence = -1;
						}
					}

					for (interfaceType = 0; interfaceType < npcs.length; ++interfaceType) {
						if (npcs[interfaceType] != null) {
							npcs[interfaceType].sequence = -1;
						}
					}

					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.IF_OPEN_TOP == packetWriter.serverPacket) {
					interfaceType = packetBuf.readUnsignedShortAdd();
					rootInterface = interfaceType;
					this.resizeRoot(false);
					class127.Widget_resetModelFrames(interfaceType);
					class358.runWidgetOnLoadListener(rootInterface);

					for (component = 0; component < 100; ++component) {
						field564[component] = true;
					}

					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3119 == packetWriter.serverPacket) {
					SceneTilePaint.method4499();
					interfaceType = packetBuf.method7927();
					component = packetBuf.method7790();
					interfaceId = packetBuf.method7797();
					experience[component] = interfaceId;
					currentLevels[component] = interfaceType;
					levels[component] = 1;

					for (var7 = 0; var7 < 98; ++var7) {
						if (interfaceId >= Skills.Skills_experienceTable[var7]) {
							levels[component] = var7 + 2;
						}
					}

					changedSkills[++changedSkillsCount - 1 & 31] = component;
					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3104 == packetWriter.serverPacket) {
					publicChatMode = packetBuf.method7927();
					tradeChatMode = packetBuf.method7789();
					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3092 == packetWriter.serverPacket) {
					if (rootInterface != -1) {
						MouseHandler.runIntfCloseListeners(rootInterface, 0);
					}

					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3106 == packetWriter.serverPacket) {
					SceneTilePaint.method4499();
					weight = packetBuf.readShort();
					field762 = cycleCntr;
					packetWriter.serverPacket = null;
					return true;
				}

				long var38;
				if (ServerPacket.field3069 == packetWriter.serverPacket) {
					interfaceType = packetBuf.offset + packetWriter.serverPacketLength;
					component = packetBuf.readUnsignedShort();
					interfaceId = packetBuf.readUnsignedShort();
					if (component != rootInterface) {
						rootInterface = component;
						this.resizeRoot(false);
						class127.Widget_resetModelFrames(rootInterface);
						class358.runWidgetOnLoadListener(rootInterface);

						for (var7 = 0; var7 < 100; ++var7) {
							field564[var7] = true;
						}
					}

					InterfaceParent var85;
					for (; interfaceId-- > 0; var85.field1039 = true) {
						var7 = packetBuf.readInt();
						var23 = packetBuf.readUnsignedShort();
						var9 = packetBuf.readUnsignedByte();
						var85 = (InterfaceParent)interfaceParents.get(var7);
						if (var85 != null && var23 != var85.group) {
							class20.closeInterface(var85, true);
							var85 = null;
						}

						if (var85 == null) {
							var85 = ServerPacket.method5222(var7, var23, var9);
						}
					}

					for (var82 = (InterfaceParent)interfaceParents.first(); var82 != null; var82 = (InterfaceParent)interfaceParents.next()) {
						if (var82.field1039) {
							var82.field1039 = false;
						} else {
							class20.closeInterface(var82, true);
						}
					}

					widgetFlags = new NodeHashTable(512);

					while (packetBuf.offset < interfaceType) {
						var7 = packetBuf.readInt();
						var23 = packetBuf.readUnsignedShort();
						var9 = packetBuf.readUnsignedShort();
						var10 = packetBuf.readInt();

						for (var61 = var23; var61 <= var9; ++var61) {
							var38 = ((long)var7 << 32) + (long)var61;
							widgetFlags.put(new IntegerNode(var10), var38);
						}
					}

					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3031 == packetWriter.serverPacket) {
					var50 = packetBuf.readStringCp1252NullTerminated();
					var21 = AbstractFont.escapeBrackets(AbstractByteArrayCopier.method5528(class118.method2737(packetBuf)));
					Login.addGameMessage(6, var50, var21);
					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3050 == packetWriter.serverPacket) {
					if (GameEngine.field205 == null) {
						GameEngine.field205 = new class391(FontName.HitSplatDefinition_cached);
					}

					class445 var51 = FontName.HitSplatDefinition_cached.method7173(packetBuf);
					GameEngine.field205.field4376.vmethod7561(var51.field4680, var51.field4681);
					field682[++field683 - 1 & 31] = var51.field4680;
					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3112 == packetWriter.serverPacket) {
					class9.field34 = packetBuf.method7789();
					FriendSystem.field803 = packetBuf.method7927();
					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3074 == packetWriter.serverPacket) {
					for (interfaceType = 0; interfaceType < VarpDefinition.VarpDefinition_fileCount; ++interfaceType) {
						VarpDefinition var75 = SoundCache.VarpDefinition_get(interfaceType);
						if (var75 != null) {
							Varps.Varps_temp[interfaceType] = 0;
							Varps.Varps_main[interfaceType] = 0;
						}
					}

					SceneTilePaint.method4499();
					changedVarpCount += 32;
					packetWriter.serverPacket = null;
					return true;
				}

				String var40;
				if (ServerPacket.field3082 == packetWriter.serverPacket) {
					var50 = packetBuf.readStringCp1252NullTerminated();
					var25 = packetBuf.readLong();
					var27 = packetBuf.readUnsignedShort();
					var34 = packetBuf.readMedium();
					PlayerType var36 = (PlayerType)ChatChannel.findEnumerated(HitSplatDefinition.PlayerType_values(), packetBuf.readUnsignedByte());
					var38 = (var27 << 32) + var34;
					boolean var67 = false;

					for (var15 = 0; var15 < 100; ++var15) {
						if (crossWorldMessageIds[var15] == var38) {
							var67 = true;
							break;
						}
					}

					if (var36.isUser && class155.friendSystem.isIgnored(new Username(var50, class83.loginType))) {
						var67 = true;
					}

					if (!var67 && field603 == 0) {
						crossWorldMessageIds[crossWorldMessageIdsIndex] = var38;
						crossWorldMessageIdsIndex = (crossWorldMessageIdsIndex + 1) % 100;
						var40 = AbstractFont.escapeBrackets(AbstractByteArrayCopier.method5528(class118.method2737(packetBuf)));
						if (var36.modIcon != -1) {
							class6.addChatMessage(9, class351.method6579(var36.modIcon) + var50, var40, UserComparator7.base37DecodeLong(var25));
						} else {
							class6.addChatMessage(9, var50, var40, UserComparator7.base37DecodeLong(var25));
						}
					}

					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3047 == packetWriter.serverPacket) {
					class155.friendSystem.readUpdate(packetBuf, packetWriter.serverPacketLength);
					field685 = cycleCntr;
					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3122 == packetWriter.serverPacket) {
					SceneTilePaint.method4499();
					runEnergy = packetBuf.readUnsignedByte();
					field762 = cycleCntr;
					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3084 == packetWriter.serverPacket) {
					interfaceType = packetBuf.readUnsignedByte();
					var21 = packetBuf.readStringCp1252NullTerminated();
					interfaceId = packetBuf.method7789();
					if (interfaceType >= 1 && interfaceType <= 8) {
						if (var21.equalsIgnoreCase("null")) {
							var21 = null;
						}

						playerMenuActions[interfaceType - 1] = var21;
						playerOptionsPriorities[interfaceType - 1] = interfaceId == 0;
					}

					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3123 == packetWriter.serverPacket) {
					interfaceId = packetBuf.method7877();
					interfaceType = interfaceId >> 16;
					component = interfaceId >> 8 & 255;
					var7 = interfaceType + (interfaceId >> 4 & 7);
					var23 = component + (interfaceId & 7);
					var9 = packetBuf.method7789();
					var10 = var9 >> 2;
					var61 = var9 & 3;
					var12 = field711[var10];
					var68 = packetBuf.readUnsignedShort();
					if (var7 >= 0 && var23 >= 0 && var7 < 103 && var23 < 103) {
						if (var12 == 0) {
							BoundaryObject var93 = FriendSystem.scene.method4150(class160.Client_plane, var7, var23);
							if (var93 != null) {
								var15 = class121.Entity_unpackID(var93.tag);
								if (var10 == 2) {
									var93.renderable1 = new DynamicObject(var15, 2, var61 + 4, class160.Client_plane, var7, var23, var68, false, var93.renderable1);
									var93.renderable2 = new DynamicObject(var15, 2, var61 + 1 & 3, class160.Client_plane, var7, var23, var68, false, var93.renderable2);
								} else {
									var93.renderable1 = new DynamicObject(var15, var10, var61, class160.Client_plane, var7, var23, var68, false, var93.renderable1);
								}
							}
						} else if (var12 == 1) {
							WallDecoration var92 = FriendSystem.scene.method4224(class160.Client_plane, var7, var23);
							if (var92 != null) {
								var15 = class121.Entity_unpackID(var92.tag);
								if (var10 != 4 && var10 != 5) {
									if (var10 == 6) {
										var92.renderable1 = new DynamicObject(var15, 4, var61 + 4, class160.Client_plane, var7, var23, var68, false, var92.renderable1);
									} else if (var10 == 7) {
										var92.renderable1 = new DynamicObject(var15, 4, (var61 + 2 & 3) + 4, class160.Client_plane, var7, var23, var68, false, var92.renderable1);
									} else if (var10 == 8) {
										var92.renderable1 = new DynamicObject(var15, 4, var61 + 4, class160.Client_plane, var7, var23, var68, false, var92.renderable1);
										var92.renderable2 = new DynamicObject(var15, 4, (var61 + 2 & 3) + 4, class160.Client_plane, var7, var23, var68, false, var92.renderable2);
									}
								} else {
									var92.renderable1 = new DynamicObject(var15, 4, var61, class160.Client_plane, var7, var23, var68, false, var92.renderable1);
								}
							}
						} else if (var12 == 2) {
							GameObject var90 = FriendSystem.scene.getGameObject(class160.Client_plane, var7, var23);
							if (var10 == 11) {
								var10 = 10;
							}

							if (var90 != null) {
								var90.renderable = new DynamicObject(class121.Entity_unpackID(var90.tag), var10, var61, class160.Client_plane, var7, var23, var68, false, var90.renderable);
							}
						} else if (var12 == 3) {
							FloorDecoration var91 = FriendSystem.scene.getFloorDecoration(class160.Client_plane, var7, var23);
							if (var91 != null) {
								var91.renderable = new DynamicObject(class121.Entity_unpackID(var91.tag), 22, var61, class160.Client_plane, var7, var23, var68, false, var91.renderable);
							}
						}
					}

					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3046 == packetWriter.serverPacket) {
					interfaceType = packetBuf.method7797();
					var74 = HorizontalAlignment.getWidget(interfaceType);

					for (interfaceId = 0; interfaceId < var74.itemIds.length; ++interfaceId) {
						var74.itemIds[interfaceId] = -1;
						var74.itemIds[interfaceId] = 0;
					}

					class290.invalidateWidget(var74);
					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3068 == packetWriter.serverPacket) {
					var70 = packetBuf.readByte();
					var25 = packetBuf.readUnsignedShort();
					var27 = packetBuf.readMedium();
					var34 = (var25 << 32) + var27;
					boolean var11 = false;
					ClanChannel var37 = var70 >= 0 ? currentClanChannels[var70] : class83.guestClanChannel;
					if (var37 == null) {
						var11 = true;
					} else {
						for (var68 = 0; var68 < 100; ++var68) {
							if (crossWorldMessageIds[var68] == var34) {
								var11 = true;
								break;
							}
						}
					}

					if (!var11) {
						crossWorldMessageIds[crossWorldMessageIdsIndex] = var34;
						crossWorldMessageIdsIndex = (crossWorldMessageIdsIndex + 1) % 100;
						var32 = class118.method2737(packetBuf);
						var14 = var70 >= 0 ? 43 : 46;
						class6.addChatMessage(var14, "", var32, var37.name);
					}

					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3029 == packetWriter.serverPacket) {
					interfaceType = packetBuf.readUnsignedByte();
					class19.forceDisconnect(interfaceType);
					packetWriter.serverPacket = null;
					return false;
				}

				if (ServerPacket.field3081 == packetWriter.serverPacket) {
					GameEngine.field205 = new class391(FontName.HitSplatDefinition_cached);
					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3083 == packetWriter.serverPacket) {
					destinationX = packetBuf.readUnsignedByte();
					if (destinationX == 255) {
						destinationX = 0;
					}

					destinationY = packetBuf.readUnsignedByte();
					if (destinationY == 255) {
						destinationY = 0;
					}

					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3039 == packetWriter.serverPacket) {
					hintArrowType = packetBuf.readUnsignedByte();
					if (hintArrowType == 1) {
						hintArrowNpcIndex = packetBuf.readUnsignedShort();
					}

					if (hintArrowType >= 2 && hintArrowType <= 6) {
						if (hintArrowType == 2) {
							hintArrowSubX = 64;
							hintArrowSubY = 64;
						}

						if (hintArrowType == 3) {
							hintArrowSubX = 0;
							hintArrowSubY = 64;
						}

						if (hintArrowType == 4) {
							hintArrowSubX = 128;
							hintArrowSubY = 64;
						}

						if (hintArrowType == 5) {
							hintArrowSubX = 64;
							hintArrowSubY = 0;
						}

						if (hintArrowType == 6) {
							hintArrowSubX = 64;
							hintArrowSubY = 128;
						}

						hintArrowType = 2;
						hintArrowX = packetBuf.readUnsignedShort();
						hintArrowY = packetBuf.readUnsignedShort();
						hintArrowHeight = packetBuf.readUnsignedByte();
					}

					if (hintArrowType == 10) {
						hintArrowPlayerIndex = packetBuf.readUnsignedShort();
					}

					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3120 == packetWriter.serverPacket) {
					class268.readReflectionCheck(packetBuf, packetWriter.serverPacketLength);
					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3125 == packetWriter.serverPacket) {
					class162.field1768 = true;
					DynamicObject.updateNpcs(false, packetBuf);
					packetWriter.serverPacket = null;
					return true;
				}

				NPC var24;
				if (ServerPacket.field3063 == packetWriter.serverPacket) {
					component = packetBuf.readIntME();
					interfaceId = packetBuf.readUnsignedShortAdd();
					interfaceType = packetBuf.readUnsignedShortLE();
					var24 = npcs[interfaceType];
					if (var24 != null) {
						var24.spotAnimation = interfaceId;
						var24.spotAnimationHeight = component >> 16;
						var24.field1173 = (component & 65535) + cycle;
						var24.spotAnimationFrame = 0;
						var24.spotAnimationFrameCycle = 0;
						if (var24.field1173 > cycle) {
							var24.spotAnimationFrame = -1;
						}

						if (var24.spotAnimation == 65535) {
							var24.spotAnimation = -1;
						}
					}

					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3053 == packetWriter.serverPacket) {
					interfaceType = packetBuf.readInt();
					component = packetBuf.method7802();
					var6 = HorizontalAlignment.getWidget(interfaceType);
					if (component != var6.sequenceId || component == -1) {
						var6.sequenceId = component;
						var6.modelFrame = 0;
						var6.modelFrameCycle = 0;
						class290.invalidateWidget(var6);
					}

					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3094 == packetWriter.serverPacket) {
					class221.method4531(class263.field3005);
					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3067 == packetWriter.serverPacket) {
					class221.method4531(class263.field3008);
					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3062 == packetWriter.serverPacket) {
					interfaceType = packetBuf.readUnsignedShortLE();
					if (interfaceType == 65535) {
						interfaceType = -1;
					}

					component = packetBuf.method7797();
					interfaceId = packetBuf.readIntME();
					var80 = HorizontalAlignment.getWidget(interfaceId);
					ItemComposition var8;
					if (!var80.isIf3) {
						if (interfaceType == -1) {
							var80.modelType = 0;
							packetWriter.serverPacket = null;
							return true;
						}

						var8 = class67.ItemDefinition_get(interfaceType);
						var80.modelType = 4;
						var80.modelId = interfaceType;
						var80.modelAngleX = var8.xan2d;
						var80.modelAngleY = var8.yan2d;
						var80.modelZoom = var8.zoom2d * 100 / component;
						class290.invalidateWidget(var80);
					} else {
						var80.itemId = interfaceType;
						var80.itemQuantity = component;
						var8 = class67.ItemDefinition_get(interfaceType);
						var80.modelAngleX = var8.xan2d;
						var80.modelAngleY = var8.yan2d;
						var80.modelAngleZ = var8.zan2d;
						var80.modelOffsetX = var8.offsetX2d;
						var80.modelOffsetY = var8.offsetY2d;
						var80.modelZoom = var8.zoom2d;
						if (var8.isStackable == 1) {
							var80.itemQuantityMode = 1;
						} else {
							var80.itemQuantityMode = 2;
						}

						if (var80.field3398 > 0) {
							var80.modelZoom = var80.modelZoom * 32 / var80.field3398;
						} else if (var80.rawWidth > 0) {
							var80.modelZoom = var80.modelZoom * 32 / var80.rawWidth;
						}

						class290.invalidateWidget(var80);
					}

					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3111 == packetWriter.serverPacket) {
					interfaceType = packetBuf.method7797();
					component = packetBuf.readUnsignedShortAdd();
					var6 = HorizontalAlignment.getWidget(interfaceType);
					if (var6 != null && var6.type == 0) {
						if (component > var6.scrollHeight - var6.height) {
							component = var6.scrollHeight - var6.height;
						}

						if (component < 0) {
							component = 0;
						}

						if (component != var6.scrollY) {
							var6.scrollY = component;
							class290.invalidateWidget(var6);
						}
					}

					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3109 == packetWriter.serverPacket) {
					component = packetBuf.readUnsignedShort();
					interfaceId = packetBuf.readIntLE();
					interfaceType = packetBuf.readUnsignedShort();
					Player var77;
					if (interfaceType == localPlayerIndex) {
						var77 = class19.localPlayer;
					} else {
						var77 = players[interfaceType];
					}

					if (var77 != null) {
						var77.spotAnimation = component;
						var77.spotAnimationHeight = interfaceId >> 16;
						var77.field1173 = (interfaceId & 65535) + cycle;
						var77.spotAnimationFrame = 0;
						var77.spotAnimationFrameCycle = 0;
						if (var77.field1173 > cycle) {
							var77.spotAnimationFrame = -1;
						}

						if (var77.spotAnimation == 65535) {
							var77.spotAnimation = -1;
						}
					}

					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3089 == packetWriter.serverPacket) {
					var50 = packetBuf.readStringCp1252NullTerminated();
					Object[] var73 = new Object[var50.length() + 1];

					for (interfaceId = var50.length() - 1; interfaceId >= 0; --interfaceId) {
						if (var50.charAt(interfaceId) == 's') {
							var73[interfaceId + 1] = packetBuf.readStringCp1252NullTerminated();
						} else {
							var73[interfaceId + 1] = new Integer(packetBuf.readInt());
						}
					}

					var73[0] = new Integer(packetBuf.readInt());
					ScriptEvent var55 = new ScriptEvent();
					var55.args = var73;
					class1.runScriptEvent(var55);
					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3066 == packetWriter.serverPacket) {
					interfaceType = packetBuf.readUnsignedByte();
					class82.method2123(interfaceType);
					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3097 == packetWriter.serverPacket) {
					isCameraLocked = true;
					field739 = false;
					PcmPlayer.field302 = packetBuf.readUnsignedByte();
					DevicePcmPlayerProvider.field146 = packetBuf.readUnsignedByte();
					ClanSettings.field1608 = packetBuf.readUnsignedShort();
					Tiles.field996 = packetBuf.readUnsignedByte();
					class33.field231 = packetBuf.readUnsignedByte();
					if (class33.field231 >= 100) {
						EnumComposition.cameraX = PcmPlayer.field302 * 128 + 64;
						CollisionMap.cameraZ = DevicePcmPlayerProvider.field146 * 128 + 64;
						FriendSystem.cameraY = Archive.getTileHeight(EnumComposition.cameraX, CollisionMap.cameraZ, class160.Client_plane) - ClanSettings.field1608;
					}

					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3037 == packetWriter.serverPacket) {
					class221.method4531(class263.field3011);
					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3059 == packetWriter.serverPacket) {
					interfaceId = packetBuf.method7790();
					interfaceType = packetBuf.readUnsignedShortLEAdd();
					component = packetBuf.readUnsignedShortAdd();
					if (component == 65535) {
						component = -1;
					}

					var24 = npcs[interfaceType];
					if (var24 != null) {
						if (component == var24.sequence && component != -1) {
							var23 = ItemContainer.SequenceDefinition_get(component).field2172;
							if (var23 == 1) {
								var24.sequenceFrame = 0;
								var24.sequenceFrameCycle = 0;
								var24.sequenceDelay = interfaceId;
								var24.field1169 = 0;
							} else if (var23 == 2) {
								var24.field1169 = 0;
							}
						} else if (component == -1 || var24.sequence == -1 || ItemContainer.SequenceDefinition_get(component).field2166 >= ItemContainer.SequenceDefinition_get(var24.sequence).field2166) {
							var24.sequence = component;
							var24.sequenceFrame = 0;
							var24.sequenceFrameCycle = 0;
							var24.sequenceDelay = interfaceId;
							var24.field1169 = 0;
							var24.field1134 = var24.pathLength;
						}
					}

					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3070 == packetWriter.serverPacket) {
					class221.method4531(class263.field3007);
					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3080 == packetWriter.serverPacket) {
					interfaceType = packetBuf.readInt();
					component = packetBuf.readUnsignedShort();
					if (interfaceType < -70000) {
						component += 32768;
					}

					if (interfaceType >= 0) {
						var6 = HorizontalAlignment.getWidget(interfaceType);
					} else {
						var6 = null;
					}

					for (; packetBuf.offset < packetWriter.serverPacketLength; class29.itemContainerSetItem(component, var7, var23 - 1, var9)) {
						var7 = packetBuf.readUShortSmart();
						var23 = packetBuf.readUnsignedShort();
						var9 = 0;
						if (var23 != 0) {
							var9 = packetBuf.readUnsignedByte();
							if (var9 == 255) {
								var9 = packetBuf.readInt();
							}
						}

						if (var6 != null && var7 >= 0 && var7 < var6.itemIds.length) {
							var6.itemIds[var7] = var23;
							var6.itemQuantities[var7] = var9;
						}
					}

					if (var6 != null) {
						class290.invalidateWidget(var6);
					}

					SceneTilePaint.method4499();
					changedItemContainers[++field746 - 1 & 31] = component & 32767;
					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3049 == packetWriter.serverPacket) {
					interfaceType = packetBuf.readUnsignedShortLEAdd();
					if (interfaceType == 65535) {
						interfaceType = -1;
					}

					class401.playSong(interfaceType);
					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3093 == packetWriter.serverPacket) {
					interfaceType = packetBuf.method7805();
					component = packetBuf.readUnsignedShortLEAdd();
					if (component == 65535) {
						component = -1;
					}

					class145.method3032(component, interfaceType);
					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3113 == packetWriter.serverPacket) {
					FillMode.method8155(packetBuf.readStringCp1252NullTerminated());
					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3026 == packetWriter.serverPacket) {
					class221.method4531(class263.field3004);
					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.REBUILD_REGION_NORMAL == packetWriter.serverPacket) {
					class146.rebuildRegion(false, packetWriter.packetBuffer);
					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3055 == packetWriter.serverPacket) {
					class155.friendSystem.ignoreList.read(packetBuf, packetWriter.serverPacketLength);
					ObjectComposition.FriendSystem_invalidateIgnoreds();
					field685 = cycleCntr;
					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3087 == packetWriter.serverPacket) {
					class162.field1768 = true;
					DynamicObject.updateNpcs(true, packetBuf);
					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3073 == packetWriter.serverPacket) {
					class155.friendSystem.method1687();
					field685 = cycleCntr;
					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3108 == packetWriter.serverPacket) {
					if (packetWriter.serverPacketLength == 0) {
						Statics1.friendsChat = null;
					} else {
						if (Statics1.friendsChat == null) {
							Statics1.friendsChat = new FriendsChat(class83.loginType, UserComparator10.client);
						}

						Statics1.friendsChat.readUpdate(packetBuf);
					}

					Strings.method5798();
					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3115 == packetWriter.serverPacket) {
					var70 = packetBuf.readByte();
					var21 = packetBuf.readStringCp1252NullTerminated();
					long var42 = packetBuf.readUnsignedShort();
					long var44 = packetBuf.readMedium();
					PlayerType var33 = (PlayerType)ChatChannel.findEnumerated(HitSplatDefinition.PlayerType_values(), packetBuf.readUnsignedByte());
					long var46 = (var42 << 32) + var44;
					boolean var13 = false;
					ClanChannel var41 = null;
					var41 = var70 >= 0 ? currentClanChannels[var70] : class83.guestClanChannel;
					if (var41 == null) {
						var13 = true;
					} else {
						var15 = 0;

						while (true) {
							if (var15 >= 100) {
								if (var33.isUser && class155.friendSystem.isIgnored(new Username(var21, class83.loginType))) {
									var13 = true;
								}
								break;
							}

							if (crossWorldMessageIds[var15] == var46) {
								var13 = true;
								break;
							}

							++var15;
						}
					}

					if (!var13) {
						crossWorldMessageIds[crossWorldMessageIdsIndex] = var46;
						crossWorldMessageIdsIndex = (crossWorldMessageIdsIndex + 1) % 100;
						var40 = AbstractFont.escapeBrackets(class118.method2737(packetBuf));
						var16 = var70 >= 0 ? 41 : 44;
						if (var33.modIcon != -1) {
							class6.addChatMessage(var16, class351.method6579(var33.modIcon) + var21, var40, var41.name);
						} else {
							class6.addChatMessage(var16, var21, var40, var41.name);
						}
					}

					packetWriter.serverPacket = null;
					return true;
				}

				if (ServerPacket.field3077 == packetWriter.serverPacket) {
					interfaceType = packetBuf.readUnsignedByte();
					if (packetBuf.readUnsignedByte() == 0) {
						grandExchangeOffers[interfaceType] = new GrandExchangeOffer();
						packetBuf.offset += 18;
					} else {
						--packetBuf.offset;
						grandExchangeOffers[interfaceType] = new GrandExchangeOffer(packetBuf, false);
					}

					field689 = cycleCntr;
					packetWriter.serverPacket = null;
					return true;
				}

				class301.RunException_sendStackTrace("" + (packetWriter.serverPacket != null ? packetWriter.serverPacket.id * -805983233 * -998616065 : -1) + "," + (packetWriter.field1329 != null ? packetWriter.field1329.id * -805983233 * -998616065 : -1) + "," + (packetWriter.field1331 != null ? packetWriter.field1331.id * -805983233 * -998616065 : -1) + "," + packetWriter.serverPacketLength, null);
				GameObject.logOut();
			} catch (IOException var48) {
				class9.method64();
			} catch (Exception var49) {
				var21 = "" + (packetWriter.serverPacket != null ? packetWriter.serverPacket.id * -805983233 * -998616065 : -1) + "," + (packetWriter.field1329 != null ? packetWriter.field1329.id * -805983233 * -998616065 : -1) + "," + (packetWriter.field1331 != null ? packetWriter.field1331.id * -805983233 * -998616065 : -1) + "," + packetWriter.serverPacketLength + "," + (class19.localPlayer.pathX[0] + ApproximateRouteStrategy.baseX) + "," + (class19.localPlayer.pathY[0] + class250.baseY) + ",";

				for (interfaceId = 0; interfaceId < packetWriter.serverPacketLength && interfaceId < 50; ++interfaceId) {
					var21 = var21 + packetBuf.array[interfaceId] + ",";
				}

				class301.RunException_sendStackTrace(var21, var49);
				GameObject.logOut();
			}

			return true;
		}
	}

	final void menu() {
		class21.incrementMenuEntries();
		if (FloorDecoration.dragInventoryWidget == null) {
			if (clickedWidget == null) {
				int var2;
				int var4;
				int var5;
				label281: {
					int var1 = MouseHandler.MouseHandler_lastButton;
					int var3;
					int var8;
					int var14;
					if (isMenuOpen) {
						int var18;
						if (var1 != 1 && (UserComparator5.mouseCam || var1 != 4)) {
							var2 = MouseHandler.MouseHandler_x;
							var3 = MouseHandler.MouseHandler_y;
							if (var2 < class307.menuX - 10 || var2 > class307.menuX + class11.menuWidth + 10 || var3 < ArchiveDiskActionHandler.menuY - 10 || var3 > ArchiveDiskActionHandler.menuY + UrlRequester.menuHeight + 10) {
								isMenuOpen = false;
								var4 = class307.menuX;
								var5 = ArchiveDiskActionHandler.menuY;
								var14 = class11.menuWidth;
								var18 = UrlRequester.menuHeight;

								for (var8 = 0; var8 < rootWidgetCount; ++var8) {
									if (rootWidgetXs[var8] + rootWidgetWidths[var8] > var4 && rootWidgetXs[var8] < var14 + var4 && rootWidgetHeights[var8] + rootWidgetYs[var8] > var5 && rootWidgetYs[var8] < var18 + var5) {
										field564[var8] = true;
									}
								}
							}
						}

						if (var1 == 1 || !UserComparator5.mouseCam && var1 == 4) {
							var2 = class307.menuX;
							var3 = ArchiveDiskActionHandler.menuY;
							var4 = class11.menuWidth;
							var5 = MouseHandler.MouseHandler_lastPressedX;
							var14 = MouseHandler.MouseHandler_lastPressedY;
							var18 = -1;

							int var9;
							for (var8 = 0; var8 < menuOptionsCount; ++var8) {
								var9 = var3 + (menuOptionsCount - 1 - var8) * 15 + 31;
								if (var5 > var2 && var5 < var4 + var2 && var14 > var9 - 13 && var14 < var9 + 3) {
									var18 = var8;
								}
							}

							int var10;
							int var11;
							if (var18 != -1 && var18 >= 0) {
								var8 = menuArguments1[var18];
								var9 = menuArguments2[var18];
								var10 = menuOpcodes[var18];
								var11 = menuIdentifiers[var18];
								String var12 = menuActions[var18];
								String var13 = menuTargets[var18];
								SoundCache.menuAction(var8, var9, var10, var11, var12, var13, MouseHandler.MouseHandler_lastPressedX, MouseHandler.MouseHandler_lastPressedY);
							}

							isMenuOpen = false;
							var8 = class307.menuX;
							var9 = ArchiveDiskActionHandler.menuY;
							var10 = class11.menuWidth;
							var11 = UrlRequester.menuHeight;

							for (int var17 = 0; var17 < rootWidgetCount; ++var17) {
								if (rootWidgetXs[var17] + rootWidgetWidths[var17] > var8 && rootWidgetXs[var17] < var8 + var10 && rootWidgetYs[var17] + rootWidgetHeights[var17] > var9 && rootWidgetYs[var17] < var11 + var9) {
									field564[var17] = true;
								}
							}
						}
					} else {
						var2 = class168.method3324();
						if ((var1 == 1 || !UserComparator5.mouseCam && var1 == 4) && var2 >= 0) {
							var3 = menuOpcodes[var2];
							if (var3 == 39 || var3 == 40 || var3 == 41 || var3 == 42 || var3 == 43 || var3 == 33 || var3 == 34 || var3 == 35 || var3 == 36 || var3 == 37 || var3 == 38 || var3 == 1005) {
								var4 = menuArguments1[var2];
								var5 = menuArguments2[var2];
								Widget var6 = HorizontalAlignment.getWidget(var5);
								var8 = WorldMapSection2.getWidgetFlags(var6);
								boolean var7 = (var8 >> 28 & 1) != 0;
								if (var7) {
									break label281;
								}

								Object var10000 = null;
								if (class239.method4998(WorldMapSection2.getWidgetFlags(var6))) {
									break label281;
								}
							}
						}

						if ((var1 == 1 || !UserComparator5.mouseCam && var1 == 4) && this.shouldLeftClickOpenMenu()) {
							var1 = 2;
						}

						if ((var1 == 1 || !UserComparator5.mouseCam && var1 == 4) && menuOptionsCount > 0 && var2 >= 0) {
							var3 = menuArguments1[var2];
							var4 = menuArguments2[var2];
							var5 = menuOpcodes[var2];
							var14 = menuIdentifiers[var2];
							String var15 = menuActions[var2];
							String var16 = menuTargets[var2];
							SoundCache.menuAction(var3, var4, var5, var14, var15, var16, MouseHandler.MouseHandler_lastPressedX, MouseHandler.MouseHandler_lastPressedY);
						}

						if (var1 == 2 && menuOptionsCount > 0) {
							this.openMenu(MouseHandler.MouseHandler_lastPressedX, MouseHandler.MouseHandler_lastPressedY);
						}
					}

					return;
				}

				if (FloorDecoration.dragInventoryWidget != null && !field601 && menuOptionsCount > 0 && !this.shouldLeftClickOpenMenu()) {
					UserComparator8.method2576(draggedWidgetX, draggedWidgetY);
				}

				field601 = false;
				itemDragDuration = 0;
				if (FloorDecoration.dragInventoryWidget != null) {
					class290.invalidateWidget(FloorDecoration.dragInventoryWidget);
				}

				FloorDecoration.dragInventoryWidget = HorizontalAlignment.getWidget(var5);
				dragItemSlotSource = var4;
				draggedWidgetX = MouseHandler.MouseHandler_lastPressedX;
				draggedWidgetY = MouseHandler.MouseHandler_lastPressedY;
				if (var2 >= 0) {
					PacketWriter.tempMenuAction = new MenuAction();
					PacketWriter.tempMenuAction.param0 = menuArguments1[var2];
					PacketWriter.tempMenuAction.param1 = menuArguments2[var2];
					PacketWriter.tempMenuAction.opcode = menuOpcodes[var2];
					PacketWriter.tempMenuAction.identifier = menuIdentifiers[var2];
					PacketWriter.tempMenuAction.action = menuActions[var2];
				}

				class290.invalidateWidget(FloorDecoration.dragInventoryWidget);
			}
		}
	}

	final boolean shouldLeftClickOpenMenu() {
		int var1 = class168.method3324();
		return (leftClickOpensMenu == 1 && menuOptionsCount > 2 || ArchiveLoader.method2071(var1)) && !menuShiftClick[var1];
	}

	final void openMenu(int var1, int var2) {
		int var3 = FloorOverlayDefinition.fontBold12.stringWidth("Choose Option");

		int var4;
		int var5;
		for (var4 = 0; var4 < menuOptionsCount; ++var4) {
			var5 = FloorOverlayDefinition.fontBold12.stringWidth(ReflectionCheck.method637(var4));
			if (var5 > var3) {
				var3 = var5;
			}
		}

		var3 += 8;
		var4 = menuOptionsCount * 15 + 22;
		var5 = var1 - var3 / 2;
		if (var3 + var5 > class4.canvasWidth) {
			var5 = class4.canvasWidth - var3;
		}

		if (var5 < 0) {
			var5 = 0;
		}

		int var6 = var2;
		if (var4 + var2 > class309.canvasHeight) {
			var6 = class309.canvasHeight - var4;
		}

		if (var6 < 0) {
			var6 = 0;
		}

		class307.menuX = var5;
		ArchiveDiskActionHandler.menuY = var6;
		class11.menuWidth = var3;
		UrlRequester.menuHeight = menuOptionsCount * 15 + 22;
		var1 -= viewportOffsetX;
		var2 -= viewportOffsetY;
		FriendSystem.scene.menuOpen(class160.Client_plane, var1, var2, false);
		isMenuOpen = true;
	}

	final void resizeRoot(boolean var1) {
		DynamicObject.method1982(rootInterface, class4.canvasWidth, class309.canvasHeight, var1);
	}

	void alignWidget(Widget var1) {
		Widget var2 = var1.parentId == -1 ? null : HorizontalAlignment.getWidget(var1.parentId);
		int var3;
		int var4;
		if (var2 == null) {
			var3 = class4.canvasWidth;
			var4 = class309.canvasHeight;
		} else {
			var3 = var2.width;
			var4 = var2.height;
		}

		class116.alignWidgetSize(var1, var3, var4, false);
		class162.alignWidgetPosition(var1, var3, var4);
	}

	final void method1159() {
		class290.invalidateWidget(clickedWidget);
		++class136.widgetDragDuration;
		if (field671 && field668) {
			int var1 = MouseHandler.MouseHandler_x;
			int var2 = MouseHandler.MouseHandler_y;
			var1 -= widgetClickX;
			var2 -= widgetClickY;
			if (var1 < field669) {
				var1 = field669;
			}

			if (var1 + clickedWidget.width > field669 + clickedWidgetParent.width) {
				var1 = field669 + clickedWidgetParent.width - clickedWidget.width;
			}

			if (var2 < field628) {
				var2 = field628;
			}

			if (var2 + clickedWidget.height > field628 + clickedWidgetParent.height) {
				var2 = field628 + clickedWidgetParent.height - clickedWidget.height;
			}

			int var3 = var1 - field596;
			int var4 = var2 - field673;
			int var5 = clickedWidget.dragZoneSize;
			if (class136.widgetDragDuration > clickedWidget.dragThreshold && (var3 > var5 || var3 < -var5 || var4 > var5 || var4 < -var5)) {
				isDraggingWidget = true;
			}

			int var6 = var1 - field669 + clickedWidgetParent.scrollX;
			int var7 = var2 - field628 + clickedWidgetParent.scrollY;
			ScriptEvent var8;
			if (clickedWidget.onDrag != null && isDraggingWidget) {
				var8 = new ScriptEvent();
				var8.widget = clickedWidget;
				var8.mouseX = var6;
				var8.mouseY = var7;
				var8.args = clickedWidget.onDrag;
				class1.runScriptEvent(var8);
			}

			if (MouseHandler.MouseHandler_currentButton == 0) {
				if (isDraggingWidget) {
					if (clickedWidget.onDragComplete != null) {
						var8 = new ScriptEvent();
						var8.widget = clickedWidget;
						var8.mouseX = var6;
						var8.mouseY = var7;
						var8.dragTarget = draggedOnWidget;
						var8.args = clickedWidget.onDragComplete;
						class1.runScriptEvent(var8);
					}

					if (draggedOnWidget != null && UserComparator3.method2601(clickedWidget) != null) {
						PacketBufferNode var9 = ItemContainer.getPacketBufferNode(ClientPacket.field2919, packetWriter.isaacCipher);
						var9.packetBuffer.method7746(clickedWidget.id);
						var9.packetBuffer.writeShort(clickedWidget.childIndex);
						var9.packetBuffer.method7796(clickedWidget.itemId);
						var9.packetBuffer.writeShort(draggedOnWidget.childIndex);
						var9.packetBuffer.method7791(draggedOnWidget.itemId);
						var9.packetBuffer.method7746(draggedOnWidget.id);
						packetWriter.addNode(var9);
					}
				} else if (this.shouldLeftClickOpenMenu()) {
					this.openMenu(widgetClickX + field596, widgetClickY + field673);
				} else if (menuOptionsCount > 0) {
					UserComparator8.method2576(field596 + widgetClickX, widgetClickY + field673);
				}

				clickedWidget = null;
			}

		} else {
			if (class136.widgetDragDuration > 1) {
				clickedWidget = null;
			}

		}
	}

	public Username username() {
		return class19.localPlayer != null ? class19.localPlayer.username : null;
	}

	public void setOtlTokenRequester(OtlTokenRequester var1) {
		if (var1 != null) {
			this.field544 = var1;
			class150.method3089(10);
		}
	}

	public boolean isOnLoginScreen() {
		return gameState == 10;
	}

	public long getAccountHash() {
		return this.field658;
	}

	public final void init() {
		try {
			if (this.checkHost()) {
				int var4;
				for (int var1 = 0; var1 <= 27; ++var1) {
					String var2 = this.getParameter(Integer.toString(var1));
					if (var2 != null) {
						switch(var1) {
						case 1:
							useBufferedSocket = Integer.parseInt(var2) != 0;
						case 2:
						case 13:
						case 16:
						case 18:
						case 19:
						case 20:
						default:
							break;
						case 3:
							isMembersWorld = var2.equalsIgnoreCase("true");
							break;
						case 4:
							if (clientType == -1) {
								clientType = Integer.parseInt(var2);
							}
							break;
						case 5:
							worldProperties = Integer.parseInt(var2);
							break;
						case 6:
							var4 = Integer.parseInt(var2);
							Language var20;
							if (var4 >= 0 && var4 < Language.Language_valuesOrdered.length) {
								var20 = Language.Language_valuesOrdered[var4];
							} else {
								var20 = null;
							}

							FriendSystem.clientLanguage = var20;
							break;
						case 7:
							var4 = Integer.parseInt(var2);
							GameBuild[] var5 = new GameBuild[]{GameBuild.BUILDLIVE, GameBuild.RC, GameBuild.WIP, GameBuild.LIVE};
							GameBuild[] var6 = var5;
							int var7 = 0;

							GameBuild var3;
							while (true) {
								if (var7 >= var6.length) {
									var3 = null;
									break;
								}

								GameBuild var8 = var6[var7];
								if (var4 == var8.buildId) {
									var3 = var8;
									break;
								}

								++var7;
							}

							field509 = var3;
							break;
						case 8:
							if (var2.equalsIgnoreCase("true")) {
							}
							break;
						case 9:
							class429.field4593 = var2;
							break;
						case 10:
							Login.field917 = (StudioGame)ChatChannel.findEnumerated(ArchiveLoader.method2076(), Integer.parseInt(var2));
							if (StudioGame.oldscape == Login.field917) {
								class83.loginType = LoginType.oldscape;
							} else {
								class83.loginType = LoginType.field4559;
							}
							break;
						case 11:
							class124.field1518 = var2;
							break;
						case 12:
							worldId = Integer.parseInt(var2);
							break;
						case 14:
							class7.field27 = Integer.parseInt(var2);
							break;
						case 15:
							gameBuild = Integer.parseInt(var2);
							break;
						case 17:
							class152.field1686 = var2;
							break;
						case 21:
							field484 = Integer.parseInt(var2);
							break;
						case 22:
							field753 = Integer.parseInt(var2) != 0;
						}
					}
				}

				class299.method5767();
				GrandExchangeOfferOwnWorldComparator.worldHost = this.getCodeBase().getHost();
				String var9 = field509.name;
				byte var10 = 0;

				try {
					JagexCache.idxCount = 21;
					SecureRandomFuture.cacheGamebuild = var10;

					try {
						ReflectionCheck.operatingSystemName = System.getProperty("os.name");
					} catch (Exception var17) {
						ReflectionCheck.operatingSystemName = "Unknown";
					}

					Buffer.formattedOperatingSystemName = ReflectionCheck.operatingSystemName.toLowerCase();

					try {
						Statics1.userHomeDirectory = System.getProperty("user.home");
						if (Statics1.userHomeDirectory != null) {
							Statics1.userHomeDirectory = Statics1.userHomeDirectory + "/";
						}
					} catch (Exception var16) {
					}

					try {
						if (Buffer.formattedOperatingSystemName.startsWith("win")) {
							if (Statics1.userHomeDirectory == null) {
								Statics1.userHomeDirectory = System.getenv("USERPROFILE");
							}
						} else if (Statics1.userHomeDirectory == null) {
							Statics1.userHomeDirectory = System.getenv("HOME");
						}

						if (Statics1.userHomeDirectory != null) {
							Statics1.userHomeDirectory = Statics1.userHomeDirectory + "/";
						}
					} catch (Exception var15) {
					}

					if (Statics1.userHomeDirectory == null) {
						Statics1.userHomeDirectory = "~/";
					}

					PendingSpawn.cacheParentPaths = new String[]{"c:/rscache/", "/rscache/", "c:/windows/", "c:/winnt/", "c:/", Statics1.userHomeDirectory, "/tmp/", ""};
					WorldMapSection1.cacheSubPaths = new String[]{".jagex_cache_" + SecureRandomFuture.cacheGamebuild, ".file_store_" + SecureRandomFuture.cacheGamebuild};

					label148:
					for (int var11 = 0; var11 < 4; ++var11) {
						VertexNormal.cacheDir = class29.method431("oldschool", var9, var11);
						if (!VertexNormal.cacheDir.exists()) {
							VertexNormal.cacheDir.mkdirs();
						}

						File[] var12 = VertexNormal.cacheDir.listFiles();
						if (var12 == null) {
							break;
						}

						File[] var22 = var12;
						int var13 = 0;

						while (true) {
							if (var13 >= var22.length) {
								break label148;
							}

							File var14 = var22[var13];
							if (!SecureRandomCallable.openFile(var14, false)) {
								break;
							}

							++var13;
						}
					}

					File var21 = VertexNormal.cacheDir;
					class17.FileSystem_cacheDir = var21;
					if (!class17.FileSystem_cacheDir.exists()) {
						throw new RuntimeException("");
					}

					FileSystem.FileSystem_hasPermissions = true;
					ClanChannel.method3068();
					JagexCache.JagexCache_dat2File = new BufferedFile(new AccessFile(Varcs.getFile("main_file_cache.dat2"), "rw", 1048576000L), 5200, 0);
					JagexCache.JagexCache_idx255File = new BufferedFile(new AccessFile(Varcs.getFile("main_file_cache.idx255"), "rw", 1048576L), 6000, 0);
					class194.JagexCache_idxFiles = new BufferedFile[JagexCache.idxCount];

					for (var4 = 0; var4 < JagexCache.idxCount; ++var4) {
						class194.JagexCache_idxFiles[var4] = new BufferedFile(new AccessFile(Varcs.getFile("main_file_cache.idx" + var4), "rw", 1048576L), 6000, 0);
					}
				} catch (Exception var18) {
					class301.RunException_sendStackTrace(null, var18);
				}

				UserComparator10.client = this;
				RunException.clientType = clientType;
				if (field486 == -1) {
					field486 = 0;
				}

				AbstractWorldMapData.field2796 = System.getenv("JX_ACCESS_TOKEN");
				System.getenv("JX_REFRESH_TOKEN");
				this.startThread(765, 503, 204);
			}
		} catch (RuntimeException var19) {
			throw KitDefinition.newRunException(var19, "client.init(" + ')');
		}
	}

	static final boolean method1198() {
		return ViewportMouse.ViewportMouse_isInViewport;
	}

	static final void Widget_setKeyIgnoreHeld(Widget var0, int var1) {
		if (var0.field3422 == null) {
			throw new RuntimeException();
		} else {
			if (var0.field3406 == null) {
				var0.field3406 = new int[var0.field3422.length];
			}

			var0.field3406[var1] = Integer.MAX_VALUE;
		}
	}

	static int method1606(int var0, Script var1, boolean var2) {
		Widget var3 = HorizontalAlignment.getWidget(Interpreter.Interpreter_intStack[--class295.Interpreter_intStackSize]);
		if (var0 == ScriptOpcodes.IF_GETSCROLLX) {
			Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = var3.scrollX;
			return 1;
		} else if (var0 == ScriptOpcodes.IF_GETSCROLLY) {
			Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = var3.scrollY;
			return 1;
		} else if (var0 == ScriptOpcodes.IF_GETTEXT) {
			Interpreter.Interpreter_stringStack[++ChatChannel.Interpreter_stringStackSize - 1] = var3.text;
			return 1;
		} else if (var0 == ScriptOpcodes.IF_GETSCROLLWIDTH) {
			Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = var3.scrollWidth;
			return 1;
		} else if (var0 == ScriptOpcodes.IF_GETSCROLLHEIGHT) {
			Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = var3.scrollHeight;
			return 1;
		} else if (var0 == ScriptOpcodes.IF_GETMODELZOOM) {
			Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = var3.modelZoom;
			return 1;
		} else if (var0 == ScriptOpcodes.IF_GETMODELANGLE_X) {
			Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = var3.modelAngleX;
			return 1;
		} else if (var0 == ScriptOpcodes.IF_GETMODELANGLE_Z) {
			Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = var3.modelAngleZ;
			return 1;
		} else if (var0 == ScriptOpcodes.IF_GETMODELANGLE_Y) {
			Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = var3.modelAngleY;
			return 1;
		} else if (var0 == ScriptOpcodes.IF_GETTRANS) {
			Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = var3.transparencyTop;
			return 1;
		} else if (var0 == 2610) {
			Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = var3.transparencyBot;
			return 1;
		} else if (var0 == ScriptOpcodes.IF_GETCOLOUR) {
			Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = var3.color;
			return 1;
		} else if (var0 == ScriptOpcodes.IF_GETFILLCOLOUR) {
			Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = var3.color2;
			return 1;
		} else if (var0 == 2613) {
			Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = var3.fillMode.rsOrdinal();
			return 1;
		} else if (var0 == ScriptOpcodes.IF_GETMODELTRANSPARENT) {
			Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = var3.modelTransparency ? 1 : 0;
			return 1;
		} else if (var0 != 2615 && var0 != 2616) {
			return 2;
		} else {
			++class295.Interpreter_intStackSize;
			return 1;
		}
	}
}
