public class User implements Comparable {
	Username username;
	Username previousUsername;

	User() {
	}

	public int compareTo_user(User var1) {
		return this.username.compareToTyped(var1.username);
	}

	public Username getUsername() {
		return this.username;
	}

	public String getName() {
		return this.username == null ? "" : this.username.getName();
	}

	public String getPreviousName() {
		return this.previousUsername == null ? "" : this.previousUsername.getName();
	}

	void set(Username var1, Username var2) {
		if (var1 == null) {
			throw new NullPointerException();
		} else {
			this.username = var1;
			this.previousUsername = var2;
		}
	}

	public int compareTo(Object var1) {
		return this.compareTo_user((User)var1);
	}
}
