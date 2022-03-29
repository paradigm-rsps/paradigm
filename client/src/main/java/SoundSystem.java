public class SoundSystem implements Runnable {
	volatile PcmPlayer[] players;

	SoundSystem() {
		this.players = new PcmPlayer[2];
	}

	public void run() {
		try {
			for (int var1 = 0; var1 < 2; ++var1) {
				PcmPlayer var2 = this.players[var1];
				if (var2 != null) {
					var2.run();
				}
			}
		} catch (Exception var4) {
			class301.RunException_sendStackTrace(null, var4);
		}

	}

	static AttackOption[] method789() {
		return new AttackOption[]{AttackOption.AttackOption_hidden, AttackOption.field1279, AttackOption.field1273, AttackOption.AttackOption_dependsOnCombatLevels, AttackOption.AttackOption_alwaysRightClick};
	}
}
