import java.util.ArrayList;

public class Interpreter {
	static ClientPreferences clientPreferences;
	static String[] Interpreter_stringLocals;
	static int[] Interpreter_arrayLengths;
	static int[][] Interpreter_arrays;
	static int[] Interpreter_intStack;
	static String[] Interpreter_stringStack;
	static int Interpreter_frameDepth;
	static ScriptFrame[] Interpreter_frames;
	static java.util.Calendar Interpreter_calendar;
	static final String[] Interpreter_MONTHS;
	static boolean field837;
	static boolean field838;
	static ArrayList field839;
	static int field827;
	static final double field842;
	static IndexedSprite worldSelectLeftSprite;

	static {
		Interpreter_arrayLengths = new int[5];
		Interpreter_arrays = new int[5][5000];
		Interpreter_intStack = new int[1000];
		Interpreter_stringStack = new String[1000];
		Interpreter_frameDepth = 0;
		Interpreter_frames = new ScriptFrame[50];
		Interpreter_calendar = java.util.Calendar.getInstance();
		Interpreter_MONTHS = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
		field837 = false;
		field838 = false;
		field839 = new ArrayList();
		field827 = 0;
		field842 = Math.log(2.0D);
	}

	static void changeWorldSelectSorting(int var0, int var1) {
		int[] var2 = new int[4];
		int[] var3 = new int[4];
		var2[0] = var0;
		var3[0] = var1;
		int var4 = 1;

		for (int var5 = 0; var5 < 4; ++var5) {
			if (World.World_sortOption1[var5] != var0) {
				var2[var4] = World.World_sortOption1[var5];
				var3[var4] = World.World_sortOption2[var5];
				++var4;
			}
		}

		World.World_sortOption1 = var2;
		World.World_sortOption2 = var3;
		WorldMapDecorationType.sortWorlds(World.World_worlds, 0, World.World_worlds.length - 1, World.World_sortOption1, World.World_sortOption2);
	}
}
