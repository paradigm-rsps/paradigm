import java.util.Iterator;

public final class WorldMapRectangle {
	int width;
	int height;
	int x;
	int y;
	// $FF: synthetic field
	final WorldMapManager this$0;

	WorldMapRectangle(WorldMapManager var1) {
		this.this$0 = var1;
	}

	static void method4987() {
		Iterator var0 = Messages.Messages_hashTable.iterator();

		while (var0.hasNext()) {
			Message var1 = (Message)var0.next();
			var1.clearIsFromIgnored();
		}

	}
}
