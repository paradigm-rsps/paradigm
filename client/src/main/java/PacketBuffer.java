public class PacketBuffer extends Buffer {
	static final int[] field4611;
	IsaacCipher isaacCipher;
	int bitIndex;

	static {
		field4611 = new int[]{0, 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767, 65535, 131071, 262143, 524287, 1048575, 2097151, 4194303, 8388607, 16777215, 33554431, 67108863, 134217727, 268435455, 536870911, 1073741823, Integer.MAX_VALUE, -1};
	}

	public PacketBuffer(int var1) {
		super(var1);
	}

	public void newIsaacCipher(int[] var1) {
		this.isaacCipher = new IsaacCipher(var1);
	}

	public void setIsaacCipher(IsaacCipher var1) {
		this.isaacCipher = var1;
	}

	public void writeByteIsaac(int var1) {
		super.array[++super.offset - 1] = (byte)(var1 + this.isaacCipher.nextInt());
	}

	public int readByteIsaac() {
		return super.array[++super.offset - 1] - this.isaacCipher.nextInt() & 255;
	}

	public boolean method7701() {
		int var1 = super.array[super.offset] - this.isaacCipher.method8330() & 255;
		return var1 >= 128;
	}

	public int readSmartByteShortIsaac() {
		int var1 = super.array[++super.offset - 1] - this.isaacCipher.nextInt() & 255;
		return var1 < 128 ? var1 : (var1 - 128 << 8) + (super.array[++super.offset - 1] - this.isaacCipher.nextInt() & 255);
	}

	public void method7708(byte[] var1, int var2, int var3) {
		for (int var4 = 0; var4 < var3; ++var4) {
			var1[var4 + var2] = (byte)(super.array[++super.offset - 1] - this.isaacCipher.nextInt());
		}

	}

	public void importIndex() {
		this.bitIndex = super.offset * 8;
	}

	public int readBits(int var1) {
		int var2 = this.bitIndex >> 3;
		int var3 = 8 - (this.bitIndex & 7);
		int var4 = 0;

		for (this.bitIndex += var1; var1 > var3; var3 = 8) {
			var4 += (super.array[var2++] & field4611[var3]) << var1 - var3;
			var1 -= var3;
		}

		if (var3 == var1) {
			var4 += super.array[var2] & field4611[var3];
		} else {
			var4 += super.array[var2] >> var3 - var1 & field4611[var1];
		}

		return var4;
	}

	public void exportIndex() {
		super.offset = (this.bitIndex + 7) / 8;
	}

	public int bitsRemaining(int var1) {
		return var1 * 8 - this.bitIndex;
	}
}
