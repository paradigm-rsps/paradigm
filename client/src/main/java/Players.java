public class Players {
    static byte[] skipFlags;
    static class193[] movementTypes;
    static Buffer[] cached_appearances;
    static int localPlayerCount;
    static int[] localPlayerIndexes;
    static int externalPlayerCount;
    static int[] externalPlayerIndexes;
    static int[] Players_regions;
    static int[] Players_orientations;
    static int[] Players_targetIndices;
    static int Players_pendingUpdateCount;
    static int[] Players_pendingUpdateIndices;
    static Buffer field1299;

    static {
        skipFlags = new byte[2048];
        movementTypes = new class193[2048];
        cached_appearances = new Buffer[2048];
        localPlayerCount = 0;
        localPlayerIndexes = new int[2048];
        externalPlayerCount = 0;
        externalPlayerIndexes = new int[2048];
        Players_regions = new int[2048];
        Players_orientations = new int[2048];
        Players_targetIndices = new int[2048];
        Players_pendingUpdateCount = 0;
        Players_pendingUpdateIndices = new int[2048];
        field1299 = new Buffer(new byte[5000]);
    }

	static void method2419() {
		for (ObjectSound var0 = (ObjectSound)ObjectSound.objectSounds.last(); var0 != null; var0 = (ObjectSound)ObjectSound.objectSounds.previous()) {
			if (var0.stream1 != null) {
				WorldMapIcon_1.pcmStreamMixer.removeSubStream(var0.stream1);
				var0.stream1 = null;
			}

			if (var0.stream2 != null) {
				WorldMapIcon_1.pcmStreamMixer.removeSubStream(var0.stream2);
				var0.stream2 = null;
			}
		}

		ObjectSound.objectSounds.clear();
	}
}
