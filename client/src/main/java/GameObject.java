public final class GameObject {
	public static int[] ByteArrayPool_altSizeArrayCounts;
	int plane;
	int z;
	int centerX;
	int centerY;
	public Renderable renderable;
	int orientation;
	int startX;
	int endX;
	int startY;
	int endY;
	int field2639;
	int lastDrawn;
	public long tag;
	int flags;

	GameObject() {
		this.tag = 0L;
		this.flags = 0;
	}

	static final void logOut() {
		Client.packetWriter.close();
		MouseHandler.method593();
		FriendSystem.scene.clear();

		for (int var0 = 0; var0 < 4; ++var0) {
			Client.collisionMaps[var0].clear();
		}

		System.gc();
		class273.musicPlayerStatus = 1;
		class147.musicTrackArchive = null;
		ArchiveLoader.musicTrackGroupId = -1;
		class273.musicTrackFileId = -1;
		DevicePcmPlayerProvider.musicTrackVolume = 0;
		class260.musicTrackBoolean = false;
		class273.pcmSampleLength = 2;
		Client.currentTrackGroupId = -1;
		Client.field731 = false;
		Players.method2419();
		InterfaceParent.updateGameState(10);
	}
}
