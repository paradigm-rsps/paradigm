import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.Iterator;
import java.util.zip.CRC32;

public class NetCache {
	public static AbstractSocket NetCache_socket;
	public static int NetCache_loadTime;
	public static long field3976;
	public static NodeHashTable NetCache_pendingPriorityWrites;
	public static int NetCache_pendingPriorityWritesCount;
	public static NodeHashTable NetCache_pendingPriorityResponses;
	public static int NetCache_pendingPriorityResponsesCount;
	public static DualNodeDeque NetCache_pendingWritesQueue;
	public static NodeHashTable NetCache_pendingWrites;
	public static int NetCache_pendingWritesCount;
	public static NodeHashTable NetCache_pendingResponses;
	public static int NetCache_pendingResponsesCount;
	public static NetFileRequest NetCache_currentResponse;
	public static Buffer NetCache_responseHeaderBuffer;
	public static int field3990;
	static CRC32 NetCache_crc;
	static Archive[] NetCache_archives;
	public static byte field3999;
	public static int NetCache_crcMismatches;
	public static int NetCache_ioExceptions;

	static {
		NetCache_loadTime = 0;
		NetCache_pendingPriorityWrites = new NodeHashTable(4096);
		NetCache_pendingPriorityWritesCount = 0;
		NetCache_pendingPriorityResponses = new NodeHashTable(32);
		NetCache_pendingPriorityResponsesCount = 0;
		NetCache_pendingWritesQueue = new DualNodeDeque();
		NetCache_pendingWrites = new NodeHashTable(4096);
		NetCache_pendingWritesCount = 0;
		NetCache_pendingResponses = new NodeHashTable(4096);
		NetCache_pendingResponsesCount = 0;
		NetCache_responseHeaderBuffer = new Buffer(8);
		field3990 = 0;
		NetCache_crc = new CRC32();
		NetCache_archives = new Archive[256];
		field3999 = 0;
		NetCache_crcMismatches = 0;
		NetCache_ioExceptions = 0;
	}

	static void method5988() {
		Messages.Messages_channels.clear();
		Messages.Messages_hashTable.clear();
		Messages.Messages_queue.clear();
		Messages.Messages_count = 0;
	}

	protected static int getGcDuration() {
		int var0 = 0;
		if (PendingSpawn.garbageCollector == null || !PendingSpawn.garbageCollector.isValid()) {
			try {
				Iterator var1 = ManagementFactory.getGarbageCollectorMXBeans().iterator();

				while (var1.hasNext()) {
					GarbageCollectorMXBean var2 = (GarbageCollectorMXBean)var1.next();
					if (var2.isValid()) {
						PendingSpawn.garbageCollector = var2;
						GameEngine.garbageCollectorLastCheckTimeMs = -1L;
						GameEngine.garbageCollectorLastCollectionTime = -1L;
					}
				}
			} catch (Throwable var11) {
			}
		}

		if (PendingSpawn.garbageCollector != null) {
			long var9 = WorldMapSprite.method4989();
			long var3 = PendingSpawn.garbageCollector.getCollectionTime();
			if (GameEngine.garbageCollectorLastCollectionTime != -1L) {
				long var5 = var3 - GameEngine.garbageCollectorLastCollectionTime;
				long var7 = var9 - GameEngine.garbageCollectorLastCheckTimeMs;
				if (var7 != 0L) {
					var0 = (int)(100L * var5 / var7);
				}
			}

			GameEngine.garbageCollectorLastCollectionTime = var3;
			GameEngine.garbageCollectorLastCheckTimeMs = var9;
		}

		return var0;
	}
}
