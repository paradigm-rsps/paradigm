import java.lang.management.GarbageCollectorMXBean;

public final class PendingSpawn extends Node {
	static int foundItemIdCount;
	public static String[] cacheParentPaths;
	static GarbageCollectorMXBean garbageCollector;
	static GraphicsDefaults spriteIds;
	static int selectedItemWidget;
	int plane;
	int type;
	int x;
	int y;
	int objectId;
	int field1117;
	int field1119;
	int id;
	int orientation;
	int field1122;
	int delay;
	int hitpoints;

	PendingSpawn() {
		this.delay = 0;
		this.hitpoints = -1;
	}
}
