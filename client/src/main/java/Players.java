public class Players {
    static byte[] gpiSkipFlags;
    static class193[] movementTypes;
    static Buffer[] cached_appearances;
    static int gpiLocalPlayerCount;
    static int[] gpiLocalPlayerIndexes;
    static int gpiExternalPlayerCount;
    static int[] gpiExternalPlayerIndexes;
    static int[] Players_regions;
    static int[] Players_orientations;
    static int[] Players_targetIndices;
    static int changedPlayerUpdatesCount;
    static int[] changedPlayerUpdates;
    static Buffer field1299;

    static {
        gpiSkipFlags = new byte[2048];
        movementTypes = new class193[2048];
        cached_appearances = new Buffer[2048];
        gpiLocalPlayerCount = 0;
        gpiLocalPlayerIndexes = new int[2048];
        gpiExternalPlayerCount = 0;
        gpiExternalPlayerIndexes = new int[2048];
        Players_regions = new int[2048];
        Players_orientations = new int[2048];
        Players_targetIndices = new int[2048];
        changedPlayerUpdatesCount = 0;
        changedPlayerUpdates = new int[2048];
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
