import java.io.IOException;
import java.util.Arrays;

public class PacketWriter {
	static MenuAction tempMenuAction;
	AbstractSocket socket;
	IterableNodeDeque packetBufferNodes;
	int bufferSize;
	Buffer buffer;
	public IsaacCipher isaacCipher;
	PacketBuffer packetBuffer;
	ServerPacket serverPacket;
	int serverPacketLength;
	boolean field1328;
	int field1326;
	int pendingWrites;
	ServerPacket field1320;
	ServerPacket field1329;
	ServerPacket field1331;

	PacketWriter() {
		this.packetBufferNodes = new IterableNodeDeque();
		this.bufferSize = 0;
		this.buffer = new Buffer(5000);
		this.packetBuffer = new PacketBuffer(40000);
		this.serverPacket = null;
		this.serverPacketLength = 0;
		this.field1328 = true;
		this.field1326 = 0;
		this.pendingWrites = 0;
	}

	final void clearBuffer() {
		this.packetBufferNodes.rsClear();
		this.bufferSize = 0;
	}

	final void flush() throws IOException {
		if (this.socket != null && this.bufferSize > 0) {
			this.buffer.offset = 0;

			while (true) {
				PacketBufferNode var1 = (PacketBufferNode)this.packetBufferNodes.last();
				if (var1 == null || var1.index > this.buffer.array.length - this.buffer.offset) {
					this.socket.write(this.buffer.array, 0, this.buffer.offset);
					this.pendingWrites = 0;
					break;
				}

				this.buffer.writeBytes(var1.packetBuffer.array, 0, var1.index);
				this.bufferSize -= var1.index;
				var1.remove();
				var1.packetBuffer.releaseArray();
				var1.release();
			}
		}

	}

	public final void addNode(PacketBufferNode var1) {
		this.packetBufferNodes.addFirst(var1);
		var1.index = var1.packetBuffer.offset;
		var1.packetBuffer.offset = 0;
		this.bufferSize += var1.index;
	}

	void setSocket(AbstractSocket var1) {
		this.socket = var1;
	}

	void close() {
		if (this.socket != null) {
			this.socket.close();
			this.socket = null;
		}

	}

	void removeSocket() {
		this.socket = null;
	}

	AbstractSocket getSocket() {
		return this.socket;
	}

	public static Object method2511(byte[] var0, boolean var1) {
		if (var0 == null) {
			return null;
		} else if (var0.length > 136) {
			DirectByteArrayCopier var2 = new DirectByteArrayCopier();
			var2.set(var0);
			return var2;
		} else {
			return var0;
		}
	}

	public static void method2485() {
		WorldMapElement.WorldMapElement_cachedSprites.clear();
	}

	static void reset() {
		Client.mouseLastLastPressedTimeMillis = -1L;
		BuddyRankComparator.mouseRecorder.index = 0;
		Varps.hasFocus = true;
		Client.hadFocus = true;
		Client.field718 = -1L;
		class33.reflectionChecks = new IterableNodeDeque();
		Client.packetWriter.clearBuffer();
		Client.packetWriter.packetBuffer.offset = 0;
		Client.packetWriter.serverPacket = null;
		Client.packetWriter.field1320 = null;
		Client.packetWriter.field1329 = null;
		Client.packetWriter.field1331 = null;
		Client.packetWriter.serverPacketLength = 0;
		Client.packetWriter.field1326 = 0;
		Client.rebootTimer = 0;
		Client.logoutTimer = 0;
		Client.hintArrowType = 0;
		class268.method5228();
		ItemComposition.method3764(0);
		NetCache.method5988();
		Client.isItemSelected = 0;
		Client.isSpellSelected = false;
		Client.soundEffectCount = 0;
		Client.camAngleY = 0;
		Client.oculusOrbState = 0;
		class340.field4109 = null;
		Client.minimapState = 0;
		Client.field721 = -1;
		Client.destinationX = 0;
		Client.destinationY = 0;
		Client.playerAttackOption = AttackOption.AttackOption_hidden;
		Client.npcAttackOption = AttackOption.AttackOption_hidden;
		Client.npcCount = 0;
        Players.gpiLocalPlayerCount = 0;

		int var0;
		for (var0 = 0; var0 < 2048; ++var0) {
			Players.cached_appearances[var0] = null;
            Players.movementTypes[var0] = class193.field2193;
		}

		for (var0 = 0; var0 < 2048; ++var0) {
            Client.gpiLocalPlayers[var0] = null;
		}

		for (var0 = 0; var0 < 32768; ++var0) {
			Client.npcs[var0] = null;
		}

		Client.combatTargetPlayerIndex = -1;
		Client.projectiles.clear();
		Client.graphicsObjects.clear();

		for (var0 = 0; var0 < 4; ++var0) {
			for (int var4 = 0; var4 < 104; ++var4) {
				for (int var2 = 0; var2 < 104; ++var2) {
					Client.groundItems[var0][var4][var2] = null;
				}
			}
		}

		Client.pendingSpawns = new NodeDeque();
		class155.friendSystem.clear();

		for (var0 = 0; var0 < VarpDefinition.VarpDefinition_fileCount; ++var0) {
			VarpDefinition var1 = SoundCache.VarpDefinition_get(var0);
			if (var1 != null) {
				Varps.Varps_temp[var0] = 0;
				Varps.Varps_main[var0] = 0;
			}
		}

		JagexCache.varcs.clearTransient();
		Client.followerIndex = -1;
		if (Client.rootInterface != -1) {
			WorldMapID.method5000(Client.rootInterface);
		}

		for (InterfaceParent var3 = (InterfaceParent)Client.interfaceParents.first(); var3 != null; var3 = (InterfaceParent)Client.interfaceParents.next()) {
			class20.closeInterface(var3, true);
		}

		Client.rootInterface = -1;
		Client.interfaceParents = new NodeHashTable(8);
		Client.meslayerContinueWidget = null;
		class268.method5228();
		Client.playerAppearance.update(null, new int[]{0, 0, 0, 0, 0}, false, -1);

		for (var0 = 0; var0 < 8; ++var0) {
			Client.playerMenuActions[var0] = null;
			Client.playerOptionsPriorities[var0] = false;
		}

		ItemContainer.itemContainers = new NodeHashTable(32);
		Client.isLoading = true;

		for (var0 = 0; var0 < 100; ++var0) {
			Client.field564[var0] = true;
		}

		class17.method228();
		Statics1.friendsChat = null;
		class134.guestClanSettings = null;
		Arrays.fill(Client.currentClanSettings, null);
		class83.guestClanChannel = null;
		Arrays.fill(Client.currentClanChannels, null);

		for (var0 = 0; var0 < 8; ++var0) {
			Client.grandExchangeOffers[var0] = new GrandExchangeOffer();
		}

		ReflectionCheck.grandExchangeEvents = null;
	}

	static final boolean runCs1(Widget var0) {
		if (var0.cs1Comparisons == null) {
			return false;
		} else {
			for (int var1 = 0; var1 < var0.cs1Comparisons.length; ++var1) {
				int var2 = WorldMapAreaData.method5086(var0, var1);
				int var3 = var0.cs1ComparisonValues[var1];
				if (var0.cs1Comparisons[var1] == 2) {
					if (var2 >= var3) {
						return false;
					}
				} else if (var0.cs1Comparisons[var1] == 3) {
					if (var2 <= var3) {
						return false;
					}
				} else if (var0.cs1Comparisons[var1] == 4) {
					if (var2 == var3) {
						return false;
					}
				} else if (var2 != var3) {
					return false;
				}
			}

			return true;
		}
	}
}
