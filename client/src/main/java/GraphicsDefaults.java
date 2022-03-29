public class GraphicsDefaults {
	public int compass;
	public int field4315;
	public int mapScenes;
	public int headIconsPk;
	public int field4318;
	public int field4319;
	public int field4320;
	public int field4321;
	public int field4317;
	public int field4314;
	public int field4324;

	public GraphicsDefaults() {
		this.compass = -1;
		this.field4315 = -1;
		this.mapScenes = -1;
		this.headIconsPk = -1;
		this.field4318 = -1;
		this.field4319 = -1;
		this.field4320 = -1;
		this.field4321 = -1;
		this.field4317 = -1;
		this.field4314 = -1;
		this.field4324 = -1;
	}

	public void decode(AbstractArchive var1) {
		byte[] var2 = var1.takeFileFlat(DefaultsGroup.field4313.group);
		Buffer var3 = new Buffer(var2);

		while (true) {
			int var4 = var3.readUnsignedByte();
			if (var4 == 0) {
				return;
			}

			switch(var4) {
			case 1:
				var3.readMedium();
				break;
			case 2:
				this.compass = var3.method7742();
				this.field4315 = var3.method7742();
				this.mapScenes = var3.method7742();
				this.headIconsPk = var3.method7742();
				this.field4318 = var3.method7742();
				this.field4319 = var3.method7742();
				this.field4320 = var3.method7742();
				this.field4321 = var3.method7742();
				this.field4317 = var3.method7742();
				this.field4314 = var3.method7742();
				this.field4324 = var3.method7742();
			}
		}
	}
}
