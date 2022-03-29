public class class120 implements class112 {
	static void Widget_runOnTargetLeave() {
		if (Client.isSpellSelected) {
			Widget var0 = class143.getWidgetChild(class92.selectedSpellWidget, Client.selectedSpellChildIndex);
			if (var0 != null && var0.onTargetLeave != null) {
				ScriptEvent var1 = new ScriptEvent();
				var1.widget = var0;
				var1.args = var0.onTargetLeave;
				class1.runScriptEvent(var1);
			}

			Client.field688 = -1;
			Client.isSpellSelected = false;
			class290.invalidateWidget(var0);
		}
	}

	static final void method2755(Widget var0) {
		int var1 = var0.contentType;
		if (var1 == 324) {
			if (Client.field759 == -1) {
				Client.field759 = var0.spriteId2;
				Client.field738 = var0.spriteId;
			}

			if (Client.playerAppearance.isFemale) {
				var0.spriteId2 = Client.field759;
			} else {
				var0.spriteId2 = Client.field738;
			}

		} else if (var1 == 325) {
			if (Client.field759 == -1) {
				Client.field759 = var0.spriteId2;
				Client.field738 = var0.spriteId;
			}

			if (Client.playerAppearance.isFemale) {
				var0.spriteId2 = Client.field738;
			} else {
				var0.spriteId2 = Client.field759;
			}

		} else if (var1 == 327) {
			var0.modelAngleX = 150;
			var0.modelAngleY = (int)(Math.sin((double)Client.cycle / 40.0D) * 256.0D) & 2047;
			var0.modelType = 5;
			var0.modelId = 0;
		} else if (var1 == 328) {
			var0.modelAngleX = 150;
			var0.modelAngleY = (int)(Math.sin((double)Client.cycle / 40.0D) * 256.0D) & 2047;
			var0.modelType = 5;
			var0.modelId = 1;
		}
	}
}
