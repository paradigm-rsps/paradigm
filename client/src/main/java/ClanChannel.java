import rs.ScriptOpcodes;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class ClanChannel extends Node {
	static int field1660;
	static WorldMap worldMap;
	static boolean field1673;
	boolean field1665;
	boolean field1662;
	public List members;
	int[] sortedMembers;
	long field1664;
	public String name;
	public byte field1666;
	public byte field1669;

	static {
		new BitSet(65536);
	}

	public ClanChannel(Buffer var1) {
		this.field1662 = true;
		this.name = null;
		this.method3058(var1);
	}

	public int[] getSortedMembers() {
		if (this.sortedMembers == null) {
			String[] var1 = new String[this.members.size()];
			this.sortedMembers = new int[this.members.size()];

			for (int var2 = 0; var2 < this.members.size(); this.sortedMembers[var2] = var2++) {
				var1[var2] = ((ClanChannelMember)this.members.get(var2)).username.method8362();
			}

			int[] var3 = this.sortedMembers;
			WorldMapData_0.method4518(var1, var3, 0, var1.length - 1);
		}

		return this.sortedMembers;
	}

	void addMember(ClanChannelMember var1) {
		this.members.add(var1);
		this.sortedMembers = null;
	}

	void removeMember(int var1) {
		this.members.remove(var1);
		this.sortedMembers = null;
	}

	public int method3056() {
		return this.members.size();
	}

	public int method3057(String var1) {
		if (!this.field1662) {
			throw new RuntimeException("Displaynames not available");
		} else {
			for (int var2 = 0; var2 < this.members.size(); ++var2) {
				if (((ClanChannelMember)this.members.get(var2)).username.getName().equalsIgnoreCase(var1)) {
					return var2;
				}
			}

			return -1;
		}
	}

	void method3058(Buffer var1) {
		int var2 = var1.readUnsignedByte();
		if ((var2 & 1) != 0) {
			this.field1665 = true;
		}

		if ((var2 & 2) != 0) {
			this.field1662 = true;
		}

		int var3 = 2;
		if ((var2 & 4) != 0) {
			var3 = var1.readUnsignedByte();
		}

		super.key = var1.readLong();
		this.field1664 = var1.readLong();
		this.name = var1.readStringCp1252NullTerminated();
		var1.readBoolean();
		this.field1669 = var1.readByte();
		this.field1666 = var1.readByte();
		int var4 = var1.readUnsignedShort();
		if (var4 > 0) {
			this.members = new ArrayList(var4);

			for (int var5 = 0; var5 < var4; ++var5) {
				ClanChannelMember var6 = new ClanChannelMember();
				if (this.field1665) {
					var1.readLong();
				}

				if (this.field1662) {
					var6.username = new Username(var1.readStringCp1252NullTerminated());
				}

				var6.rank = var1.readByte();
				var6.world = var1.readUnsignedShort();
				if (var3 >= 3) {
					var1.readBoolean();
				}

				this.members.add(var5, var6);
			}
		}

	}

	public static void method3068() {
		try {
			File var0 = new File(Statics1.userHomeDirectory, "random.dat");
			int var2;
			if (var0.exists()) {
				JagexCache.JagexCache_randomDat = new BufferedFile(new AccessFile(var0, "rw", 25L), 24, 0);
			} else {
				label37:
				for (int var1 = 0; var1 < WorldMapSection1.cacheSubPaths.length; ++var1) {
					for (var2 = 0; var2 < PendingSpawn.cacheParentPaths.length; ++var2) {
						File var3 = new File(PendingSpawn.cacheParentPaths[var2] + WorldMapSection1.cacheSubPaths[var1] + File.separatorChar + "random.dat");
						if (var3.exists()) {
							JagexCache.JagexCache_randomDat = new BufferedFile(new AccessFile(var3, "rw", 25L), 24, 0);
							break label37;
						}
					}
				}
			}

			if (JagexCache.JagexCache_randomDat == null) {
				RandomAccessFile var4 = new RandomAccessFile(var0, "rw");
				var2 = var4.read();
				var4.seek(0L);
				var4.write(var2);
				var4.seek(0L);
				var4.close();
				JagexCache.JagexCache_randomDat = new BufferedFile(new AccessFile(var0, "rw", 25L), 24, 0);
			}
		} catch (IOException var5) {
		}

	}

	static int method3078(int var0, Script var1, boolean var2) {
		if (var0 == ScriptOpcodes.GETWINDOWMODE) {
			Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = ReflectionCheck.getWindowedMode();
			return 1;
		} else {
			int var3;
			if (var0 == ScriptOpcodes.SETWINDOWMODE) {
				var3 = Interpreter.Interpreter_intStack[--class295.Interpreter_intStackSize];
				if (var3 == 1 || var3 == 2) {
					UserComparator6.setWindowedMode(var3);
				}

				return 1;
			} else if (var0 == ScriptOpcodes.GETDEFAULTWINDOWMODE) {
				Interpreter.Interpreter_intStack[++class295.Interpreter_intStackSize - 1] = Interpreter.clientPreferences.method2317();
				return 1;
			} else if (var0 != ScriptOpcodes.SETDEFAULTWINDOWMODE) {
				if (var0 == 5310) {
					--class295.Interpreter_intStackSize;
					return 1;
				} else if (var0 == 5311) {
					class295.Interpreter_intStackSize -= 2;
					return 1;
				} else if (var0 == 5312) {
					--class295.Interpreter_intStackSize;
					return 1;
				} else if (var0 == 5350) {
					ChatChannel.Interpreter_stringStackSize -= 2;
					--class295.Interpreter_intStackSize;
					return 1;
				} else if (var0 == 5351) {
					--ChatChannel.Interpreter_stringStackSize;
					return 1;
				} else {
					return 2;
				}
			} else {
				var3 = Interpreter.Interpreter_intStack[--class295.Interpreter_intStackSize];
				if (var3 == 1 || var3 == 2) {
					Interpreter.clientPreferences.method2276(var3);
				}

				return 1;
			}
		}
	}
}
