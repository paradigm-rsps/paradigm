public class PcmStreamMixer extends PcmStream {
	NodeDeque subStreams;
	NodeDeque field254;
	int field255;
	int field256;

	public PcmStreamMixer() {
		this.subStreams = new NodeDeque();
		this.field254 = new NodeDeque();
		this.field255 = 0;
		this.field256 = -1;
	}

	public final synchronized void addSubStream(PcmStream var1) {
		this.subStreams.addLast(var1);
	}

	public final synchronized void removeSubStream(PcmStream var1) {
		var1.remove();
	}

	void method641() {
		if (this.field255 > 0) {
			for (PcmStreamMixerListener var1 = (PcmStreamMixerListener)this.field254.last(); var1 != null; var1 = (PcmStreamMixerListener)this.field254.previous()) {
				var1.field398 -= this.field255;
			}

			this.field256 -= this.field255;
			this.field255 = 0;
		}

	}

	void method642(Node var1, PcmStreamMixerListener var2) {
		while (this.field254.sentinel != var1 && ((PcmStreamMixerListener)var1).field398 <= var2.field398) {
			var1 = var1.previous;
		}

		NodeDeque.NodeDeque_addBefore(var2, var1);
		this.field256 = ((PcmStreamMixerListener)this.field254.sentinel.previous).field398;
	}

	void method643(PcmStreamMixerListener var1) {
		var1.remove();
		var1.remove2();
		Node var2 = this.field254.sentinel.previous;
		if (var2 == this.field254.sentinel) {
			this.field256 = -1;
		} else {
			this.field256 = ((PcmStreamMixerListener)var2).field398;
		}

	}

	protected PcmStream firstSubStream() {
		return (PcmStream)this.subStreams.last();
	}

	protected PcmStream nextSubStream() {
		return (PcmStream)this.subStreams.previous();
	}

	protected int vmethod5476() {
		return 0;
	}

	public final synchronized void fill(int[] var1, int var2, int var3) {
		do {
			if (this.field256 < 0) {
				this.updateSubStreams(var1, var2, var3);
				return;
			}

			if (var3 + this.field255 < this.field256) {
				this.field255 += var3;
				this.updateSubStreams(var1, var2, var3);
				return;
			}

			int var4 = this.field256 - this.field255;
			this.updateSubStreams(var1, var2, var4);
			var2 += var4;
			var3 -= var4;
			this.field255 += var4;
			this.method641();
			PcmStreamMixerListener var5 = (PcmStreamMixerListener)this.field254.last();
			synchronized(var5) {
				int var7 = var5.update();
				if (var7 < 0) {
					var5.field398 = 0;
					this.method643(var5);
				} else {
					var5.field398 = var7;
					this.method642(var5.previous, var5);
				}
			}
		} while(var3 != 0);

	}

	void updateSubStreams(int[] var1, int var2, int var3) {
		for (PcmStream var4 = (PcmStream)this.subStreams.last(); var4 != null; var4 = (PcmStream)this.subStreams.previous()) {
			var4.update(var1, var2, var3);
		}

	}

	public final synchronized void skip(int var1) {
		do {
			if (this.field256 < 0) {
				this.skipSubStreams(var1);
				return;
			}

			if (this.field255 + var1 < this.field256) {
				this.field255 += var1;
				this.skipSubStreams(var1);
				return;
			}

			int var2 = this.field256 - this.field255;
			this.skipSubStreams(var2);
			var1 -= var2;
			this.field255 += var2;
			this.method641();
			PcmStreamMixerListener var3 = (PcmStreamMixerListener)this.field254.last();
			synchronized(var3) {
				int var5 = var3.update();
				if (var5 < 0) {
					var3.field398 = 0;
					this.method643(var3);
				} else {
					var3.field398 = var5;
					this.method642(var3.previous, var3);
				}
			}
		} while(var1 != 0);

	}

	void skipSubStreams(int var1) {
		for (PcmStream var2 = (PcmStream)this.subStreams.last(); var2 != null; var2 = (PcmStream)this.subStreams.previous()) {
			var2.skip(var1);
		}

	}
}
