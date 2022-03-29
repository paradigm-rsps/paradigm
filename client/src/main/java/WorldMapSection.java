public interface WorldMapSection {
	void expandBounds(WorldMapArea var1);

	boolean containsCoord(int var1, int var2, int var3);

	boolean containsPosition(int var1, int var2);

	int[] getBorderTileLengths(int var1, int var2, int var3);

	Coord coord(int var1, int var2);

	void read(Buffer var1);
}
