import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Widget extends Node {
	static int[][] field3495;
	static int[] BZip2Decompressor_block;
	public static EvictingDualNodeHashTable Widget_cachedSprites;
	public static EvictingDualNodeHashTable Widget_cachedModels;
	public static EvictingDualNodeHashTable Widget_cachedFonts;
	public static EvictingDualNodeHashTable Widget_cachedSpriteMasks;
	static class361 field3388;
	static class361 field3339;
	public static boolean field3368;
	public boolean isIf3;
	public int id;
	public int childIndex;
	public int type;
	public int buttonType;
	public int contentType;
	public int xAlignment;
	public int yAlignment;
	public int widthAlignment;
	public int heightAlignment;
	public int rawX;
	public int rawY;
	public int rawWidth;
	public int rawHeight;
	public int x;
	public int y;
	public int width;
	public int height;
	public int field3359;
	public int field3360;
	public int parentId;
	public boolean isHidden;
	public int scrollX;
	public int scrollY;
	public int scrollWidth;
	public int scrollHeight;
	public int color;
	public int color2;
	public int mouseOverColor;
	public int mouseOverColor2;
	public boolean fill;
	public FillMode fillMode;
	public int transparencyTop;
	public int transparencyBot;
	public int lineWid;
	public boolean field3491;
	public int spriteId2;
	public int spriteId;
	public String field3386;
	public int spriteAngle;
	public boolean spriteTiling;
	public int outline;
	public int spriteShadow;
	public boolean spriteFlipV;
	public boolean spriteFlipH;
	public int modelType;
	public int modelId;
	int modelType2;
	int modelId2;
	public int sequenceId;
	public int sequenceId2;
	public int modelOffsetX;
	public int modelOffsetY;
	public int modelAngleX;
	public int modelAngleY;
	public int modelAngleZ;
	public int modelZoom;
	public int field3398;
	public int field3399;
	public boolean modelOrthog;
	public boolean modelTransparency;
	public int itemQuantityMode;
	public int fontId;
	public String text;
	public String text2;
	public int textLineHeight;
	public int textXAlignment;
	public int textYAlignment;
	public boolean textShadowed;
	public int paddingX;
	public int paddingY;
	public int[] inventoryXOffsets;
	public int[] inventoryYOffsets;
	public int[] inventorySprites;
	public String[] itemActions;
	class155 field3476;
	int field3439;
	HashMap field3418;
	HashMap field3381;
	public int flags;
	public boolean field3432;
	public byte[][] field3422;
	public byte[][] field3423;
	public int[] field3424;
	public int[] field3425;
	public String dataText;
	public String[] actions;
	public Widget parent;
	public int dragZoneSize;
	public int dragThreshold;
	public boolean isScrollBar;
	public String spellActionName;
	public boolean hasListener;
	public Object[] onLoad;
	public Object[] onClick;
	public Object[] onClickRepeat;
	public Object[] onRelease;
	public Object[] onHold;
	public Object[] onMouseOver;
	public Object[] onMouseRepeat;
	public Object[] onMouseLeave;
	public Object[] onDrag;
	public Object[] onDragComplete;
	public Object[] onTargetEnter;
	public Object[] onTargetLeave;
	public Object[] onVarTransmit;
	public int[] varTransmitTriggers;
	public Object[] onInvTransmit;
	public int[] invTransmitTriggers;
	public Object[] onStatTransmit;
	public int[] statTransmitTriggers;
	public Object[] onTimer;
	public Object[] onOp;
	public Object[] onScroll;
	public Object[] onChatTransmit;
	public Object[] onKey;
	public Object[] field3457;
	public Object[] field3458;
	public Object[] onFriendTransmit;
	public Object[] onClanTransmit;
	public Object[] field3395;
	public Object[] field3461;
	public Object[] onMiscTransmit;
	public Object[] onDialogAbort;
	public Object[] onSubChange;
	public Object[] onResize;
	public Object[] onStockTransmit;
	public Object[] field3468;
	public Object[] field3408;
	public int[][] cs1Instructions;
	public int[] cs1Comparisons;
	public int[] cs1ComparisonValues;
	public int mouseOverRedirect;
	public String spellName;
	public String buttonText;
	public int[] itemIds;
	public int[] itemQuantities;
	public int itemId;
	public int itemQuantity;
	public int modelFrame;
	public int modelFrameCycle;
	public Widget[] children;
	public boolean containsMouse;
	public boolean isClicked;
	public int field3481;
	public int field3391;
	public int field3355;
	public int field3387;
	public int rootIndex;
	public int cycle;
	public int[] field3406;
	public boolean noClickThrough;
	public boolean noScrollThrough;
	public boolean prioritizeMenuEntry;

	static {
		Widget_cachedSprites = new EvictingDualNodeHashTable(200);
		Widget_cachedModels = new EvictingDualNodeHashTable(50);
		Widget_cachedFonts = new EvictingDualNodeHashTable(20);
		Widget_cachedSpriteMasks = new EvictingDualNodeHashTable(8);
		field3388 = new class361(10, class359.field4222);
		field3339 = new class361(10, class359.field4222);
		field3368 = false;
	}

	public Widget() {
		this.isIf3 = false;
		this.id = -1;
		this.childIndex = -1;
		this.buttonType = 0;
		this.contentType = 0;
		this.xAlignment = 0;
		this.yAlignment = 0;
		this.widthAlignment = 0;
		this.heightAlignment = 0;
		this.rawX = 0;
		this.rawY = 0;
		this.rawWidth = 0;
		this.rawHeight = 0;
		this.x = 0;
		this.y = 0;
		this.width = 0;
		this.height = 0;
		this.field3359 = 1;
		this.field3360 = 1;
		this.parentId = -1;
		this.isHidden = false;
		this.scrollX = 0;
		this.scrollY = 0;
		this.scrollWidth = 0;
		this.scrollHeight = 0;
		this.color = 0;
		this.color2 = 0;
		this.mouseOverColor = 0;
		this.mouseOverColor2 = 0;
		this.fill = false;
		this.fillMode = FillMode.SOLID;
		this.transparencyTop = 0;
		this.transparencyBot = 0;
		this.lineWid = 1;
		this.field3491 = false;
		this.spriteId2 = -1;
		this.spriteId = -1;
		this.spriteAngle = 0;
		this.spriteTiling = false;
		this.outline = 0;
		this.spriteShadow = 0;
		this.modelType = 1;
		this.modelId = -1;
		this.modelType2 = 1;
		this.modelId2 = -1;
		this.sequenceId = -1;
		this.sequenceId2 = -1;
		this.modelOffsetX = 0;
		this.modelOffsetY = 0;
		this.modelAngleX = 0;
		this.modelAngleY = 0;
		this.modelAngleZ = 0;
		this.modelZoom = 100;
		this.field3398 = 0;
		this.field3399 = 0;
		this.modelOrthog = false;
		this.modelTransparency = false;
		this.itemQuantityMode = 2;
		this.fontId = -1;
		this.text = "";
		this.text2 = "";
		this.textLineHeight = 0;
		this.textXAlignment = 0;
		this.textYAlignment = 0;
		this.textShadowed = false;
		this.paddingX = 0;
		this.paddingY = 0;
		this.field3439 = -1;
		this.flags = 0;
		this.field3432 = false;
		this.dataText = "";
		this.parent = null;
		this.dragZoneSize = 0;
		this.dragThreshold = 0;
		this.isScrollBar = false;
		this.spellActionName = "";
		this.hasListener = false;
		this.mouseOverRedirect = -1;
		this.spellName = "";
		this.buttonText = "Ok";
		this.itemId = -1;
		this.itemQuantity = 0;
		this.modelFrame = 0;
		this.modelFrameCycle = 0;
		this.containsMouse = false;
		this.isClicked = false;
		this.field3481 = -1;
		this.field3391 = 0;
		this.field3355 = 0;
		this.field3387 = 0;
		this.rootIndex = -1;
		this.cycle = -1;
		this.noClickThrough = false;
		this.noScrollThrough = false;
		this.prioritizeMenuEntry = false;
	}

	void decodeLegacy(Buffer var1) {
		this.isIf3 = false;
		this.type = var1.readUnsignedByte();
		this.buttonType = var1.readUnsignedByte();
		this.contentType = var1.readUnsignedShort();
		this.rawX = var1.readShort();
		this.rawY = var1.readShort();
		this.rawWidth = var1.readUnsignedShort();
		this.rawHeight = var1.readUnsignedShort();
		this.transparencyTop = var1.readUnsignedByte();
		this.parentId = var1.readUnsignedShort();
		if (this.parentId == 65535) {
			this.parentId = -1;
		} else {
			this.parentId += this.id & -65536;
		}

		this.mouseOverRedirect = var1.readUnsignedShort();
		if (this.mouseOverRedirect == 65535) {
			this.mouseOverRedirect = -1;
		}

		int var2 = var1.readUnsignedByte();
		int var3;
		if (var2 > 0) {
			this.cs1Comparisons = new int[var2];
			this.cs1ComparisonValues = new int[var2];

			for (var3 = 0; var3 < var2; ++var3) {
				this.cs1Comparisons[var3] = var1.readUnsignedByte();
				this.cs1ComparisonValues[var3] = var1.readUnsignedShort();
			}
		}

		var3 = var1.readUnsignedByte();
		int var4;
		int var5;
		int var6;
		if (var3 > 0) {
			this.cs1Instructions = new int[var3][];

			for (var4 = 0; var4 < var3; ++var4) {
				var5 = var1.readUnsignedShort();
				this.cs1Instructions[var4] = new int[var5];

				for (var6 = 0; var6 < var5; ++var6) {
					this.cs1Instructions[var4][var6] = var1.readUnsignedShort();
					if (this.cs1Instructions[var4][var6] == 65535) {
						this.cs1Instructions[var4][var6] = -1;
					}
				}
			}
		}

		if (this.type == 0) {
			this.scrollHeight = var1.readUnsignedShort();
			this.isHidden = var1.readUnsignedByte() == 1;
		}

		if (this.type == 1) {
			var1.readUnsignedShort();
			var1.readUnsignedByte();
		}

		if (this.type == 2) {
			this.itemIds = new int[this.rawWidth * this.rawHeight];
			this.itemQuantities = new int[this.rawHeight * this.rawWidth];
			var4 = var1.readUnsignedByte();
			if (var4 == 1) {
				this.flags |= 268435456;
			}

			var5 = var1.readUnsignedByte();
			if (var5 == 1) {
				this.flags |= 1073741824;
			}

			var6 = var1.readUnsignedByte();
			if (var6 == 1) {
				this.flags |= Integer.MIN_VALUE;
			}

			int var7 = var1.readUnsignedByte();
			if (var7 == 1) {
				this.flags |= 536870912;
			}

			this.paddingX = var1.readUnsignedByte();
			this.paddingY = var1.readUnsignedByte();
			this.inventoryXOffsets = new int[20];
			this.inventoryYOffsets = new int[20];
			this.inventorySprites = new int[20];

			int var8;
			for (var8 = 0; var8 < 20; ++var8) {
				int var11 = var1.readUnsignedByte();
				if (var11 == 1) {
					this.inventoryXOffsets[var8] = var1.readShort();
					this.inventoryYOffsets[var8] = var1.readShort();
					this.inventorySprites[var8] = var1.readInt();
				} else {
					this.inventorySprites[var8] = -1;
				}
			}

			this.itemActions = new String[5];

			for (var8 = 0; var8 < 5; ++var8) {
				String var9 = var1.readStringCp1252NullTerminated();
				if (var9.length() > 0) {
					this.itemActions[var8] = var9;
					this.flags |= 1 << var8 + 23;
				}
			}
		}

		if (this.type == 3) {
			this.fill = var1.readUnsignedByte() == 1;
		}

		if (this.type == 4 || this.type == 1) {
			this.textXAlignment = var1.readUnsignedByte();
			this.textYAlignment = var1.readUnsignedByte();
			this.textLineHeight = var1.readUnsignedByte();
			this.fontId = var1.readUnsignedShort();
			if (this.fontId == 65535) {
				this.fontId = -1;
			}

			this.textShadowed = var1.readUnsignedByte() == 1;
		}

		if (this.type == 4) {
			this.text = var1.readStringCp1252NullTerminated();
			this.text2 = var1.readStringCp1252NullTerminated();
		}

		if (this.type == 1 || this.type == 3 || this.type == 4) {
			this.color = var1.readInt();
		}

		if (this.type == 3 || this.type == 4) {
			this.color2 = var1.readInt();
			this.mouseOverColor = var1.readInt();
			this.mouseOverColor2 = var1.readInt();
		}

		if (this.type == 5) {
			this.spriteId2 = var1.readInt();
			this.spriteId = var1.readInt();
		}

		if (this.type == 6) {
			this.modelType = 1;
			this.modelId = var1.readUnsignedShort();
			if (this.modelId == 65535) {
				this.modelId = -1;
			}

			this.modelType2 = 1;
			this.modelId2 = var1.readUnsignedShort();
			if (this.modelId2 == 65535) {
				this.modelId2 = -1;
			}

			this.sequenceId = var1.readUnsignedShort();
			if (this.sequenceId == 65535) {
				this.sequenceId = -1;
			}

			this.sequenceId2 = var1.readUnsignedShort();
			if (this.sequenceId2 == 65535) {
				this.sequenceId2 = -1;
			}

			this.modelZoom = var1.readUnsignedShort();
			this.modelAngleX = var1.readUnsignedShort();
			this.modelAngleY = var1.readUnsignedShort();
		}

		if (this.type == 7) {
			this.itemIds = new int[this.rawHeight * this.rawWidth];
			this.itemQuantities = new int[this.rawHeight * this.rawWidth];
			this.textXAlignment = var1.readUnsignedByte();
			this.fontId = var1.readUnsignedShort();
			if (this.fontId == 65535) {
				this.fontId = -1;
			}

			this.textShadowed = var1.readUnsignedByte() == 1;
			this.color = var1.readInt();
			this.paddingX = var1.readShort();
			this.paddingY = var1.readShort();
			var4 = var1.readUnsignedByte();
			if (var4 == 1) {
				this.flags |= 1073741824;
			}

			this.itemActions = new String[5];

			for (var5 = 0; var5 < 5; ++var5) {
				String var10 = var1.readStringCp1252NullTerminated();
				if (var10.length() > 0) {
					this.itemActions[var5] = var10;
					this.flags |= 1 << var5 + 23;
				}
			}
		}

		if (this.type == 8) {
			this.text = var1.readStringCp1252NullTerminated();
		}

		if (this.buttonType == 2 || this.type == 2) {
			this.spellActionName = var1.readStringCp1252NullTerminated();
			this.spellName = var1.readStringCp1252NullTerminated();
			var4 = var1.readUnsignedShort() & 63;
			this.flags |= var4 << 11;
		}

		if (this.buttonType == 1 || this.buttonType == 4 || this.buttonType == 5 || this.buttonType == 6) {
			this.buttonText = var1.readStringCp1252NullTerminated();
			if (this.buttonText.length() == 0) {
				if (this.buttonType == 1) {
					this.buttonText = "Ok";
				}

				if (this.buttonType == 4) {
					this.buttonText = "Select";
				}

				if (this.buttonType == 5) {
					this.buttonText = "Select";
				}

				if (this.buttonType == 6) {
					this.buttonText = "Continue";
				}
			}
		}

		if (this.buttonType == 1 || this.buttonType == 4 || this.buttonType == 5) {
			this.flags |= 4194304;
		}

		if (this.buttonType == 6) {
			this.flags |= 1;
		}

	}

	void decode(Buffer var1) {
		var1.readUnsignedByte();
		this.isIf3 = true;
		this.type = var1.readUnsignedByte();
		this.contentType = var1.readUnsignedShort();
		this.rawX = var1.readShort();
		this.rawY = var1.readShort();
		this.rawWidth = var1.readUnsignedShort();
		if (this.type == 9) {
			this.rawHeight = var1.readShort();
		} else {
			this.rawHeight = var1.readUnsignedShort();
		}

		this.widthAlignment = var1.readByte();
		this.heightAlignment = var1.readByte();
		this.xAlignment = var1.readByte();
		this.yAlignment = var1.readByte();
		this.parentId = var1.readUnsignedShort();
		if (this.parentId == 65535) {
			this.parentId = -1;
		} else {
			this.parentId += this.id & -65536;
		}

		this.isHidden = var1.readUnsignedByte() == 1;
		if (this.type == 0) {
			this.scrollWidth = var1.readUnsignedShort();
			this.scrollHeight = var1.readUnsignedShort();
			this.noClickThrough = var1.readUnsignedByte() == 1;
		}

		if (this.type == 5) {
			this.spriteId2 = var1.readInt();
			this.spriteAngle = var1.readUnsignedShort();
			this.spriteTiling = var1.readUnsignedByte() == 1;
			this.transparencyTop = var1.readUnsignedByte();
			this.outline = var1.readUnsignedByte();
			this.spriteShadow = var1.readInt();
			this.spriteFlipV = var1.readUnsignedByte() == 1;
			this.spriteFlipH = var1.readUnsignedByte() == 1;
		}

		if (this.type == 6) {
			this.modelType = 1;
			this.modelId = var1.readUnsignedShort();
			if (this.modelId == 65535) {
				this.modelId = -1;
			}

			this.modelOffsetX = var1.readShort();
			this.modelOffsetY = var1.readShort();
			this.modelAngleX = var1.readUnsignedShort();
			this.modelAngleY = var1.readUnsignedShort();
			this.modelAngleZ = var1.readUnsignedShort();
			this.modelZoom = var1.readUnsignedShort();
			this.sequenceId = var1.readUnsignedShort();
			if (this.sequenceId == 65535) {
				this.sequenceId = -1;
			}

			this.modelOrthog = var1.readUnsignedByte() == 1;
			var1.readUnsignedShort();
			if (this.widthAlignment != 0) {
				this.field3398 = var1.readUnsignedShort();
			}

			if (this.heightAlignment != 0) {
				var1.readUnsignedShort();
			}
		}

		if (this.type == 4) {
			this.fontId = var1.readUnsignedShort();
			if (this.fontId == 65535) {
				this.fontId = -1;
			}

			this.text = var1.readStringCp1252NullTerminated();
			this.textLineHeight = var1.readUnsignedByte();
			this.textXAlignment = var1.readUnsignedByte();
			this.textYAlignment = var1.readUnsignedByte();
			this.textShadowed = var1.readUnsignedByte() == 1;
			this.color = var1.readInt();
		}

		if (this.type == 3) {
			this.color = var1.readInt();
			this.fill = var1.readUnsignedByte() == 1;
			this.transparencyTop = var1.readUnsignedByte();
		}

		if (this.type == 9) {
			this.lineWid = var1.readUnsignedByte();
			this.color = var1.readInt();
			this.field3491 = var1.readUnsignedByte() == 1;
		}

		this.flags = var1.readMedium();
		this.dataText = var1.readStringCp1252NullTerminated();
		int var2 = var1.readUnsignedByte();
		if (var2 > 0) {
			this.actions = new String[var2];

			for (int var3 = 0; var3 < var2; ++var3) {
				this.actions[var3] = var1.readStringCp1252NullTerminated();
			}
		}

		this.dragZoneSize = var1.readUnsignedByte();
		this.dragThreshold = var1.readUnsignedByte();
		this.isScrollBar = var1.readUnsignedByte() == 1;
		this.spellActionName = var1.readStringCp1252NullTerminated();
		this.onLoad = this.readListener(var1);
		this.onMouseOver = this.readListener(var1);
		this.onMouseLeave = this.readListener(var1);
		this.onTargetLeave = this.readListener(var1);
		this.onTargetEnter = this.readListener(var1);
		this.onVarTransmit = this.readListener(var1);
		this.onInvTransmit = this.readListener(var1);
		this.onStatTransmit = this.readListener(var1);
		this.onTimer = this.readListener(var1);
		this.onOp = this.readListener(var1);
		this.onMouseRepeat = this.readListener(var1);
		this.onClick = this.readListener(var1);
		this.onClickRepeat = this.readListener(var1);
		this.onRelease = this.readListener(var1);
		this.onHold = this.readListener(var1);
		this.onDrag = this.readListener(var1);
		this.onDragComplete = this.readListener(var1);
		this.onScroll = this.readListener(var1);
		this.varTransmitTriggers = this.readListenerTriggers(var1);
		this.invTransmitTriggers = this.readListenerTriggers(var1);
		this.statTransmitTriggers = this.readListenerTriggers(var1);
	}

	Object[] readListener(Buffer var1) {
		int var2 = var1.readUnsignedByte();
		if (var2 == 0) {
			return null;
		} else {
			Object[] var3 = new Object[var2];

			for (int var4 = 0; var4 < var2; ++var4) {
				int var5 = var1.readUnsignedByte();
				if (var5 == 0) {
					var3[var4] = new Integer(var1.readInt());
				} else if (var5 == 1) {
					var3[var4] = var1.readStringCp1252NullTerminated();
				}
			}

			this.hasListener = true;
			return var3;
		}
	}

	int[] readListenerTriggers(Buffer var1) {
		int var2 = var1.readUnsignedByte();
		if (var2 == 0) {
			return null;
		} else {
			int[] var3 = new int[var2];

			for (int var4 = 0; var4 < var2; ++var4) {
				var3[var4] = var1.readInt();
			}

			return var3;
		}
	}

	public void swapItems(int var1, int var2) {
		int var3 = this.itemIds[var2];
		this.itemIds[var2] = this.itemIds[var1];
		this.itemIds[var1] = var3;
		var3 = this.itemQuantities[var2];
		this.itemQuantities[var2] = this.itemQuantities[var1];
		this.itemQuantities[var1] = var3;
	}

	public SpritePixels getSprite(boolean var1, UrlRequester var2) {
		field3368 = false;
		if (this.field3386 != null) {
			SpritePixels var3 = this.method5628(var2);
			if (var3 != null) {
				return var3;
			}
		}

		int var7;
		if (var1) {
			var7 = this.spriteId;
		} else {
			var7 = this.spriteId2;
		}

		if (var7 == -1) {
			return null;
		} else {
			long var4 = ((this.spriteFlipV ? 1L : 0L) << 38) + (long)var7 + ((long)this.outline << 36) + ((this.spriteFlipH ? 1L : 0L) << 39) + ((long)this.spriteShadow << 40);
			SpritePixels var6 = (SpritePixels)Widget_cachedSprites.get(var4);
			if (var6 != null) {
				return var6;
			} else {
				var6 = InterfaceParent.SpriteBuffer_getSprite(WorldMapLabel.Widget_spritesArchive, var7, 0);
				if (var6 == null) {
					field3368 = true;
					return null;
				} else {
					this.method5684(var6);
					Widget_cachedSprites.put(var6, var4);
					return var6;
				}
			}
		}
	}

	SpritePixels method5628(UrlRequester var1) {
		if (!this.method5633()) {
			return this.method5621(var1);
		} else {
			String var2 = this.field3386 + (this.spriteFlipV ? 1 : 0) + (this.spriteFlipH ? 1 : 0) + this.outline + this.spriteShadow;
			SpritePixels var3 = (SpritePixels)field3339.method6639(var2);
			if (var3 == null) {
				SpritePixels var4 = this.method5621(var1);
				if (var4 != null) {
					var3 = var4.method8177();
					this.method5684(var3);
					field3339.method6640(var2, var3);
				}
			}

			return var3;
		}
	}

	SpritePixels method5621(UrlRequester var1) {
		if (this.field3386 != null && var1 != null) {
			class291 var2 = (class291)field3388.method6639(this.field3386);
			if (var2 == null) {
				var2 = new class291(this.field3386, var1);
				field3388.method6640(this.field3386, var2);
			}

			return var2.method5586();
		} else {
			return null;
		}
	}

	boolean method5633() {
		return this.spriteFlipV || this.spriteFlipH || this.outline != 0 || this.spriteShadow != 0;
	}

	void method5684(SpritePixels var1) {
		if (this.spriteFlipV) {
			var1.flipVertically();
		}

		if (this.spriteFlipH) {
			var1.flipHorizontally();
		}

		if (this.outline > 0) {
			var1.pad(this.outline);
		}

		if (this.outline >= 1) {
			var1.outline(1);
		}

		if (this.outline >= 2) {
			var1.outline(16777215);
		}

		if (this.spriteShadow != 0) {
			var1.shadow(this.spriteShadow);
		}

	}

	public Font getFont() {
		field3368 = false;
		if (this.fontId == -1) {
			return null;
		} else {
			Font var1 = (Font)Widget_cachedFonts.get(this.fontId);
			if (var1 != null) {
				return var1;
			} else {
				var1 = class19.method319(WorldMapLabel.Widget_spritesArchive, Client.Widget_fontsArchive, this.fontId, 0);
				if (var1 != null) {
					Widget_cachedFonts.put(var1, this.fontId);
				} else {
					field3368 = true;
				}

				return var1;
			}
		}
	}

	public SpritePixels getInventorySprite(int var1) {
		field3368 = false;
		if (var1 >= 0 && var1 < this.inventorySprites.length) {
			int var2 = this.inventorySprites[var1];
			if (var2 == -1) {
				return null;
			} else {
				SpritePixels var3 = (SpritePixels)Widget_cachedSprites.get(var2);
				if (var3 != null) {
					return var3;
				} else {
					var3 = InterfaceParent.SpriteBuffer_getSprite(WorldMapLabel.Widget_spritesArchive, var2, 0);
					if (var3 != null) {
						Widget_cachedSprites.put(var3, var2);
					} else {
						field3368 = true;
					}

					return var3;
				}
			}
		} else {
			return null;
		}
	}

	public Model getModel(SequenceDefinition var1, int var2, boolean var3, PlayerComposition var4) {
		field3368 = false;
		int var5;
		int var6;
		if (var3) {
			var5 = this.modelType2;
			var6 = this.modelId2;
		} else {
			var5 = this.modelType;
			var6 = this.modelId;
		}

		if (var5 == 0) {
			return null;
		} else if (var5 == 1 && var6 == -1) {
			return null;
		} else {
			Model var7 = (Model)Widget_cachedModels.get(var6 + (var5 << 16));
			if (var7 == null) {
				ModelData var8;
				if (var5 == 1) {
					var8 = ModelData.ModelData_get(BoundaryObject.Widget_modelsArchive, var6, 0);
					if (var8 == null) {
						field3368 = true;
						return null;
					}

					var7 = var8.toModel(64, 768, -50, -10, -50);
				}

				if (var5 == 2) {
					var8 = class9.getNpcDefinition(var6).getModelData();
					if (var8 == null) {
						field3368 = true;
						return null;
					}

					var7 = var8.toModel(64, 768, -50, -10, -50);
				}

				if (var5 == 3) {
					if (var4 == null) {
						return null;
					}

					var8 = var4.getModelData();
					if (var8 == null) {
						field3368 = true;
						return null;
					}

					var7 = var8.toModel(64, 768, -50, -10, -50);
				}

				if (var5 == 4) {
					ItemComposition var9 = class67.ItemDefinition_get(var6);
					var8 = var9.getModelData(10);
					if (var8 == null) {
						field3368 = true;
						return null;
					}

					var7 = var8.toModel(var9.ambient + 64, var9.contrast + 768, -50, -10, -50);
				}

				Widget_cachedModels.put(var7, var6 + (var5 << 16));
			}

			if (var1 != null) {
				var7 = var1.transformWidgetModel(var7, var2);
			}

			return var7;
		}
	}

	public SpriteMask getSpriteMask(boolean var1) {
		if (this.spriteId == -1) {
			var1 = false;
		}

		int var2 = var1 ? this.spriteId * -1599395709 * 1203004971 : this.spriteId2 * -234398187 * 1912504637;
		if (var2 == -1) {
			return null;
		} else {
			long var3 = ((long)this.spriteShadow << 40) + ((this.spriteFlipV ? 1L : 0L) << 38) + (long)var2 + ((long)this.outline << 36) + ((this.spriteFlipH ? 1L : 0L) << 39);
			SpriteMask var5 = (SpriteMask)Widget_cachedSpriteMasks.get(var3);
			if (var5 != null) {
				return var5;
			} else {
				SpritePixels var6 = this.getSprite(var1, null);
				if (var6 == null) {
					return null;
				} else {
					SpritePixels var7 = var6.copyNormalized();
					int[] var8 = new int[var7.subHeight];
					int[] var9 = new int[var7.subHeight];

					for (int var10 = 0; var10 < var7.subHeight; ++var10) {
						int var11 = 0;
						int var12 = var7.subWidth;

						int var13;
						for (var13 = 0; var13 < var7.subWidth; ++var13) {
							if (var7.pixels[var13 + var10 * var7.subWidth] == 0) {
								var11 = var13;
								break;
							}
						}

						for (var13 = var7.subWidth - 1; var13 >= var11; --var13) {
							if (var7.pixels[var13 + var10 * var7.subWidth] == 0) {
								var12 = var13 + 1;
								break;
							}
						}

						var8[var10] = var11;
						var9[var10] = var12 - var11;
					}

					var5 = new SpriteMask(var7.subWidth, var7.subHeight, var9, var8, var2);
					Widget_cachedSpriteMasks.put(var5, var3);
					return var5;
				}
			}
		}
	}

	public void setAction(int var1, String var2) {
		if (this.actions == null || this.actions.length <= var1) {
			String[] var3 = new String[var1 + 1];
			if (this.actions != null) {
				for (int var4 = 0; var4 < this.actions.length; ++var4) {
					var3[var4] = this.actions[var4];
				}
			}

			this.actions = var3;
		}

		this.actions[var1] = var2;
	}

	public boolean method5637() {
		return !this.isIf3 || this.type == 0 || this.type == 11 || this.hasListener || this.contentType == 1338;
	}

	public void method5720(String var1, UrlRequester var2, long var3) {
		if (this.type == 11 && var3 != -1L) {
			var1.replaceAll("%userid%", Long.toString(var3));
			this.field3476 = new class155();
			if (!this.field3476.method3131(var1, var2)) {
				this.field3476 = null;
			} else {
				this.field3418 = new HashMap();
				this.field3381 = new HashMap();
			}
		}
	}

	public void method5639(int var1, int var2) {
		if (this.type == 11 && this.field3418 != null) {
			this.field3418.put(var1, var2);
		}
	}

	public void method5682(String var1, int var2) {
		if (this.type == 11 && this.field3381 != null) {
			this.field3381.put(var1, var2);
		}
	}

	public boolean method5623(int var1, int var2) {
		if (this.type == 11 && this.field3476 != null && this.method5642()) {
			int var3 = (int)(this.field3476.method3146()[0] * (float)this.width);
			int var4 = (int)(this.field3476.method3146()[1] * (float)this.height);
			int var5 = var3 + (int)(this.field3476.method3146()[2] * (float)this.width);
			int var6 = var4 + (int)(this.field3476.method3146()[3] * (float)this.height);
			return var1 >= var3 && var2 >= var4 && var1 < var5 && var2 < var6;
		} else {
			return false;
		}
	}

	public boolean method5642() {
		return this.field3439 == 2;
	}

	public int method5663(String var1) {
		return this.type == 11 && this.field3476 != null && this.method5642() ? this.field3476.method3134(var1) : -1;
	}

	public String method5666(String var1) {
		return this.type == 11 && this.field3476 != null && this.method5642() ? this.field3476.method3144(var1) : null;
	}

	public int method5656() {
		return this.field3381 != null && this.field3381.size() > 0 ? 1 : 0;
	}

	public int method5699() {
		if (this.type == 11 && this.field3476 != null && this.field3381 != null && !this.field3381.isEmpty()) {
			String var1 = this.field3476.method3138();
			return var1 != null && this.field3381.containsKey(this.field3476.method3138()) ? (Integer)this.field3381.get(var1) : -1;
		} else {
			return -1;
		}
	}

	public String method5647() {
		if (this.type == 11 && this.field3476 != null) {
			String var1 = this.field3476.method3138();
			Iterator var2 = this.field3476.method3140().iterator();

			while (var2.hasNext()) {
				class166 var3 = (class166)var2.next();
				String var4 = String.format("%%%S%%", var3.method3295());
				if (var3.vmethod3297() == 0) {
					var1.replaceAll(var4, Integer.toString(var3.vmethod3299()));
				} else {
					var1.replaceAll(var4, var3.vmethod3296());
				}
			}

			return var1;
		} else {
			return null;
		}
	}

	public int[] method5686() {
		if (this.type == 11 && this.field3476 != null) {
			int[] var1 = new int[3];
			int var2 = 0;
			Iterator var3 = this.field3476.method3140().iterator();

			while (var3.hasNext()) {
				class166 var4 = (class166)var3.next();
				if (!var4.method3295().equals("user_id")) {
					if (var4.vmethod3297() != 0) {
						return null;
					}

					var1[var2++] = var4.vmethod3299();
					if (var2 > 3) {
						return null;
					}
				}
			}

			return var1;
		} else {
			return null;
		}
	}

	public boolean method5694(UrlRequester var1) {
		if (this.type == 11 && this.field3476 != null) {
			this.field3476.method3165(var1);
			if (this.field3476.method3141() != this.field3439) {
				this.field3439 = this.field3476.method3141();
				if (this.field3439 >= 100) {
					return false;
				}

				if (this.field3439 == 2) {
					this.method5640();
					return true;
				}
			}

			return false;
		} else {
			return false;
		}
	}

	void method5640() {
		this.noClickThrough = true;
		ArrayList var1 = this.field3476.method3130();
		ArrayList var2 = this.field3476.method3137();
		int var3 = var1.size() + var2.size();
		this.children = new Widget[var3];
		int var4 = 0;

		Iterator var5;
		Widget var7;
		for (var5 = var1.iterator(); var5.hasNext(); this.children[var4++] = var7) {
			class160 var6 = (class160)var5.next();
			var7 = class146.method3043(5, this, var4, 0, 0, 0, 0, var6.field1748);
			var7.field3386 = var6.field1749.method2546();
			class291 var8 = new class291(var6.field1749);
			field3388.method6640(var7.field3386, var8);
		}

		for (var5 = var2.iterator(); var5.hasNext(); this.children[var4++] = var7) {
			class162 var9 = (class162)var5.next();
			var7 = class146.method3043(4, this, var4, 0, 0, 0, 0, var9.field1769);
			var7.text = var9.field1771;
			var7.fontId = (Integer)this.field3418.get(var9.field1772);
			var7.textXAlignment = var9.field1770;
			var7.textYAlignment = var9.field1776;
		}

	}
}
