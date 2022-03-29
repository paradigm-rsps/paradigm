public class Friend extends Buddy {
	boolean field4256;
	boolean field4257;

	Friend() {
	}

	int compareToFriend(Friend var1) {
		if (super.world == Client.worldId && Client.worldId != var1.world) {
			return -1;
		} else if (Client.worldId == var1.world && super.world != Client.worldId) {
			return 1;
		} else if (super.world != 0 && var1.world == 0) {
			return -1;
		} else if (var1.world != 0 && super.world == 0) {
			return 1;
		} else if (this.field4256 && !var1.field4256) {
			return -1;
		} else if (!this.field4256 && var1.field4256) {
			return 1;
		} else if (this.field4257 && !var1.field4257) {
			return -1;
		} else if (!this.field4257 && var1.field4257) {
			return 1;
		} else {
			return super.world != 0 ? super.int2 - var1.int2 : var1.int2 - super.int2;
		}
	}

	public int compareTo_user(User var1) {
		return this.compareToFriend((Friend)var1);
	}

	public int compareTo(Object var1) {
		return this.compareToFriend((Friend)var1);
	}
}
