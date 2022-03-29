public class ArchiveDiskActionHandler implements Runnable {
	static NodeDeque ArchiveDiskActionHandler_requestQueue;
	static NodeDeque ArchiveDiskActionHandler_responseQueue;
	public static int field3940;
	public static Object ArchiveDiskActionHandler_lock;
	static Thread ArchiveDiskActionHandler_thread;
	static int menuY;

	static {
		ArchiveDiskActionHandler_requestQueue = new NodeDeque();
		ArchiveDiskActionHandler_responseQueue = new NodeDeque();
		field3940 = 0;
		ArchiveDiskActionHandler_lock = new Object();
	}

	ArchiveDiskActionHandler() {
	}

	public void run() {
		try {
			while (true) {
				ArchiveDiskAction var1;
				synchronized(ArchiveDiskActionHandler_requestQueue) {
					var1 = (ArchiveDiskAction)ArchiveDiskActionHandler_requestQueue.last();
				}

				if (var1 != null) {
					if (var1.type == 0) {
						var1.archiveDisk.write((int)var1.key, var1.data, var1.data.length);
						synchronized(ArchiveDiskActionHandler_requestQueue) {
							var1.remove();
						}
					} else if (var1.type == 1) {
						var1.data = var1.archiveDisk.read((int)var1.key);
						synchronized(ArchiveDiskActionHandler_requestQueue) {
							ArchiveDiskActionHandler_responseQueue.addFirst(var1);
						}
					}

					synchronized(ArchiveDiskActionHandler_lock) {
						if (field3940 <= 1) {
							field3940 = 0;
							ArchiveDiskActionHandler_lock.notifyAll();
							return;
						}

						field3940 = 600;
					}
				} else {
					GrandExchangeOfferTotalQuantityComparator.method6007(100L);
					synchronized(ArchiveDiskActionHandler_lock) {
						if (field3940 <= 1) {
							field3940 = 0;
							ArchiveDiskActionHandler_lock.notifyAll();
							return;
						}

						--field3940;
					}
				}
			}
		} catch (Exception var13) {
			class301.RunException_sendStackTrace(null, var13);
		}
	}
}
