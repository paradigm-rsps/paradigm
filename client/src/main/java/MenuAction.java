public class MenuAction {
	int param0;
	int param1;
	int opcode;
	int identifier;
	String action;

	MenuAction() {
	}

	static char method1877(char var0) {
		if (var0 == 198) {
			return 'E';
		} else if (var0 == 230) {
			return 'e';
		} else if (var0 == 223) {
			return 's';
		} else if (var0 == 338) {
			return 'E';
		} else {
			return var0 == 339 ? 'e' : '\u0000';
		}
	}
}
