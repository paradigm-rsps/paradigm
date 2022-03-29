public class GrandExchangeEvent {
	static Widget hoveredItemContainer;
	public final int world;
	public final long age;
	public final GrandExchangeOffer grandExchangeOffer;
	String offerName;
	String previousOfferName;

	GrandExchangeEvent(Buffer var1, byte var2, int var3) {
		this.offerName = var1.readStringCp1252NullTerminated();
		this.previousOfferName = var1.readStringCp1252NullTerminated();
		this.world = var1.readUnsignedShort();
		this.age = var1.readLong();
		int var4 = var1.readInt();
		int var5 = var1.readInt();
		this.grandExchangeOffer = new GrandExchangeOffer();
		this.grandExchangeOffer.method6050(2);
		this.grandExchangeOffer.method6053(var2);
		this.grandExchangeOffer.unitPrice = var4;
		this.grandExchangeOffer.totalQuantity = var5;
		this.grandExchangeOffer.currentQuantity = 0;
		this.grandExchangeOffer.currentPrice = 0;
		this.grandExchangeOffer.id = var3;
	}

	public String getOfferName() {
		return this.offerName;
	}

	public String getPreviousOfferName() {
		return this.previousOfferName;
	}

	static boolean method6045(AbstractArchive var0, int var1, int var2) {
		byte[] var3 = var0.takeFile(var1, var2);
		if (var3 == null) {
			return false;
		} else {
			ClientPreferences.SpriteBuffer_decode(var3);
			return true;
		}
	}

	static void setLoginResponseString(String var0, String var1, String var2) {
		Login.Login_response1 = var0;
		Login.Login_response2 = var1;
		Login.Login_response3 = var2;
	}

	static Archive newArchive(int var0, boolean var1, boolean var2, boolean var3) {
		ArchiveDisk var4 = null;
		if (JagexCache.JagexCache_dat2File != null) {
			var4 = new ArchiveDisk(var0, JagexCache.JagexCache_dat2File, class194.JagexCache_idxFiles[var0], 1000000);
		}

		return new Archive(var4, PcmPlayer.masterDisk, var0, var1, var2, var3);
	}
}
