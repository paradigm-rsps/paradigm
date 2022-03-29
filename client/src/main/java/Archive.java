import java.util.zip.CRC32;

public class Archive extends AbstractArchive {
	static CRC32 Archive_crc;
	ArchiveDisk archiveDisk;
	ArchiveDisk masterDisk;
	int index;
	volatile boolean field3953;
	boolean field3950;
	volatile boolean[] validGroups;
	int indexCrc;
	int indexVersion;
	int field3955;

	static {
		Archive_crc = new CRC32();
	}

	public Archive(ArchiveDisk var1, ArchiveDisk var2, int var3, boolean var4, boolean var5, boolean var6) {
		super(var4, var5);
		this.field3953 = false;
		this.field3950 = false;
		this.field3955 = -1;
		this.archiveDisk = var1;
		this.masterDisk = var2;
		this.index = var3;
		this.field3950 = var6;
		RouteStrategy.method3873(this, this.index);
	}

	public boolean method5819() {
		return this.field3953;
	}

	public int percentage() {
		if (this.field3953) {
			return 100;
		} else if (super.groups != null) {
			return 99;
		} else {
			int var1 = UserComparator4.method2563(255, this.index);
			if (var1 >= 100) {
				var1 = 99;
			}

			return var1;
		}
	}

	void loadRegionFromGroup(int var1) {
		int var2 = this.index;
		long var3 = (var2 << 16) + var1;
		NetFileRequest var5 = (NetFileRequest)NetCache.NetCache_pendingWrites.get(var3);
		if (var5 != null) {
			NetCache.NetCache_pendingWritesQueue.addLast(var5);
		}

	}

	void loadGroup(int var1) {
		if (this.archiveDisk != null && this.validGroups != null && this.validGroups[var1]) {
			ArchiveDisk var2 = this.archiveDisk;
			byte[] var4 = null;
			synchronized(ArchiveDiskActionHandler.ArchiveDiskActionHandler_requestQueue) {
				for (ArchiveDiskAction var6 = (ArchiveDiskAction)ArchiveDiskActionHandler.ArchiveDiskActionHandler_requestQueue.last(); var6 != null; var6 = (ArchiveDiskAction)ArchiveDiskActionHandler.ArchiveDiskActionHandler_requestQueue.previous()) {
					if (var6.key == (long)var1 && var2 == var6.archiveDisk && var6.type == 0) {
						var4 = var6.data;
						break;
					}
				}
			}

			if (var4 != null) {
				this.load(var2, var1, var4, true);
			} else {
				byte[] var5 = var2.read(var1);
				this.load(var2, var1, var5, true);
			}
		} else {
			TriBool.requestNetFile(this, this.index, var1, super.groupCrcs[var1], (byte)2, true);
		}

	}

	void loadIndex(int var1, int var2) {
		this.indexCrc = var1;
		this.indexVersion = var2;
		if (this.masterDisk != null) {
			int var3 = this.index;
			ArchiveDisk var4 = this.masterDisk;
			byte[] var6 = null;
			synchronized(ArchiveDiskActionHandler.ArchiveDiskActionHandler_requestQueue) {
				for (ArchiveDiskAction var8 = (ArchiveDiskAction)ArchiveDiskActionHandler.ArchiveDiskActionHandler_requestQueue.last(); var8 != null; var8 = (ArchiveDiskAction)ArchiveDiskActionHandler.ArchiveDiskActionHandler_requestQueue.previous()) {
					if (var8.key == (long)var3 && var4 == var8.archiveDisk && var8.type == 0) {
						var6 = var8.data;
						break;
					}
				}
			}

			if (var6 != null) {
				this.load(var4, var3, var6, true);
			} else {
				byte[] var7 = var4.read(var3);
				this.load(var4, var3, var7, true);
			}
		} else {
			TriBool.requestNetFile(this, 255, this.index, this.indexCrc, (byte)0, true);
		}

	}

	void write(int var1, byte[] var2, boolean var3, boolean var4) {
		if (var3) {
			if (this.field3953) {
				throw new RuntimeException();
			}

			if (this.masterDisk != null) {
				int var5 = this.index;
				ArchiveDisk var6 = this.masterDisk;
				ArchiveDiskAction var7 = new ArchiveDiskAction();
				var7.type = 0;
				var7.key = var5;
				var7.data = var2;
				var7.archiveDisk = var6;
				synchronized(ArchiveDiskActionHandler.ArchiveDiskActionHandler_requestQueue) {
					ArchiveDiskActionHandler.ArchiveDiskActionHandler_requestQueue.addFirst(var7);
				}

				synchronized(ArchiveDiskActionHandler.ArchiveDiskActionHandler_lock) {
					if (ArchiveDiskActionHandler.field3940 == 0) {
						ArchiveDiskActionHandler.ArchiveDiskActionHandler_thread = new Thread(new ArchiveDiskActionHandler());
						ArchiveDiskActionHandler.ArchiveDiskActionHandler_thread.setDaemon(true);
						ArchiveDiskActionHandler.ArchiveDiskActionHandler_thread.start();
						ArchiveDiskActionHandler.ArchiveDiskActionHandler_thread.setPriority(5);
					}

					ArchiveDiskActionHandler.field3940 = 600;
				}
			}

			this.decodeIndex(var2);
			this.loadAllLocal();
		} else {
			var2[var2.length - 2] = (byte)(super.groupVersions[var1] >> 8);
			var2[var2.length - 1] = (byte)super.groupVersions[var1];
			if (this.archiveDisk != null) {
				ArchiveDisk var13 = this.archiveDisk;
				ArchiveDiskAction var18 = new ArchiveDiskAction();
				var18.type = 0;
				var18.key = var1;
				var18.data = var2;
				var18.archiveDisk = var13;
				synchronized(ArchiveDiskActionHandler.ArchiveDiskActionHandler_requestQueue) {
					ArchiveDiskActionHandler.ArchiveDiskActionHandler_requestQueue.addFirst(var18);
				}

				synchronized(ArchiveDiskActionHandler.ArchiveDiskActionHandler_lock) {
					if (ArchiveDiskActionHandler.field3940 == 0) {
						ArchiveDiskActionHandler.ArchiveDiskActionHandler_thread = new Thread(new ArchiveDiskActionHandler());
						ArchiveDiskActionHandler.ArchiveDiskActionHandler_thread.setDaemon(true);
						ArchiveDiskActionHandler.ArchiveDiskActionHandler_thread.start();
						ArchiveDiskActionHandler.ArchiveDiskActionHandler_thread.setPriority(5);
					}

					ArchiveDiskActionHandler.field3940 = 600;
				}

				this.validGroups[var1] = true;
			}

			if (var4) {
				super.groups[var1] = PacketWriter.method2511(var2, false);
			}
		}

	}

	void load(ArchiveDisk var1, int var2, byte[] var3, boolean var4) {
		int var5;
		if (var1 == this.masterDisk) {
			if (this.field3953) {
				throw new RuntimeException();
			}

			if (var3 == null) {
				TriBool.requestNetFile(this, 255, this.index, this.indexCrc, (byte)0, true);
				return;
			}

			Archive_crc.reset();
			Archive_crc.update(var3, 0, var3.length);
			var5 = (int)Archive_crc.getValue();
			if (var5 != this.indexCrc) {
				TriBool.requestNetFile(this, 255, this.index, this.indexCrc, (byte)0, true);
				return;
			}

			Buffer var11 = new Buffer(class149.decompressBytes(var3));
			int var12 = var11.readUnsignedByte();
			if (var12 != 5 && var12 != 6) {
				throw new RuntimeException(var12 + "," + this.index + "," + var2);
			}

			int var8 = 0;
			if (var12 >= 6) {
				var8 = var11.readInt();
			}

			if (var8 != this.indexVersion) {
				TriBool.requestNetFile(this, 255, this.index, this.indexCrc, (byte)0, true);
				return;
			}

			this.decodeIndex(var3);
			this.loadAllLocal();
		} else {
			if (!var4 && var2 == this.field3955) {
				this.field3953 = true;
			}

			if (var3 == null || var3.length <= 2) {
				this.validGroups[var2] = false;
				if (this.field3950 || var4) {
					TriBool.requestNetFile(this, this.index, var2, super.groupCrcs[var2], (byte)2, var4);
				}

				return;
			}

			Archive_crc.reset();
			Archive_crc.update(var3, 0, var3.length - 2);
			var5 = (int)Archive_crc.getValue();
			int var6 = ((var3[var3.length - 2] & 255) << 8) + (var3[var3.length - 1] & 255);
			if (var5 != super.groupCrcs[var2] || var6 != super.groupVersions[var2]) {
				this.validGroups[var2] = false;
				if (this.field3950 || var4) {
					TriBool.requestNetFile(this, this.index, var2, super.groupCrcs[var2], (byte)2, var4);
				}

				return;
			}

			this.validGroups[var2] = true;
			if (var4) {
				Object[] var7 = super.groups;
				Object var9;
				if (var3 == null) {
					var9 = null;
				} else if (var3.length > 136) {
					DirectByteArrayCopier var10 = new DirectByteArrayCopier();
					var10.set(var3);
					var9 = var10;
				} else {
					var9 = var3;
				}

				var7[var2] = var9;
			}
		}

	}

	void loadAllLocal() {
		this.validGroups = new boolean[super.groups.length];

		int var1;
		for (var1 = 0; var1 < this.validGroups.length; ++var1) {
			this.validGroups[var1] = false;
		}

		if (this.archiveDisk == null) {
			this.field3953 = true;
		} else {
			this.field3955 = -1;

			for (var1 = 0; var1 < this.validGroups.length; ++var1) {
				if (super.fileCounts[var1] > 0) {
					ArchiveDisk var2 = this.archiveDisk;
					ArchiveDiskAction var4 = new ArchiveDiskAction();
					var4.type = 1;
					var4.key = var1;
					var4.archiveDisk = var2;
					var4.archive = this;
					synchronized(ArchiveDiskActionHandler.ArchiveDiskActionHandler_requestQueue) {
						ArchiveDiskActionHandler.ArchiveDiskActionHandler_requestQueue.addFirst(var4);
					}

					synchronized(ArchiveDiskActionHandler.ArchiveDiskActionHandler_lock) {
						if (ArchiveDiskActionHandler.field3940 == 0) {
							ArchiveDiskActionHandler.ArchiveDiskActionHandler_thread = new Thread(new ArchiveDiskActionHandler());
							ArchiveDiskActionHandler.ArchiveDiskActionHandler_thread.setDaemon(true);
							ArchiveDiskActionHandler.ArchiveDiskActionHandler_thread.start();
							ArchiveDiskActionHandler.ArchiveDiskActionHandler_thread.setPriority(5);
						}

						ArchiveDiskActionHandler.field3940 = 600;
					}

					this.field3955 = var1;
				}
			}

			if (this.field3955 == -1) {
				this.field3953 = true;
			}

		}
	}

	int groupLoadPercent(int var1) {
		if (super.groups[var1] != null) {
			return 100;
		} else {
			return this.validGroups[var1] ? 100 : UserComparator4.method2563(this.index, var1);
		}
	}

	public boolean method5828(int var1) {
		return this.validGroups[var1];
	}

	public boolean method5829(int var1) {
		return this.getGroupFileIds(var1) != null;
	}

	public int loadPercent() {
		int var1 = 0;
		int var2 = 0;

		int var3;
		for (var3 = 0; var3 < super.groups.length; ++var3) {
			if (super.fileCounts[var3] > 0) {
				var1 += 100;
				var2 += this.groupLoadPercent(var3);
			}
		}

		if (var1 == 0) {
			return 100;
		} else {
			var3 = var2 * 100 / var1;
			return var3;
		}
	}

	static final int getTileHeight(int var0, int var1, int var2) {
		int var3 = var0 >> 7;
		int var4 = var1 >> 7;
		if (var3 >= 0 && var4 >= 0 && var3 <= 103 && var4 <= 103) {
			int var5 = var2;
			if (var2 < 3 && (Tiles.Tiles_renderFlags[1][var3][var4] & 2) == 2) {
				var5 = var2 + 1;
			}

			int var6 = var0 & 127;
			int var7 = var1 & 127;
			int var8 = (128 - var6) * Tiles.Tiles_heights[var5][var3][var4] + var6 * Tiles.Tiles_heights[var5][var3 + 1][var4] >> 7;
			int var9 = Tiles.Tiles_heights[var5][var3][var4 + 1] * (128 - var6) + Tiles.Tiles_heights[var5][var3 + 1][var4 + 1] * var6 >> 7;
			return var8 * (128 - var7) + var9 * var7 >> 7;
		} else {
			return 0;
		}
	}
}
