import java.util.Hashtable;

public class FileSystem {
	public static boolean FileSystem_hasPermissions;
	static Hashtable FileSystem_cacheFiles;

	static {
		FileSystem_hasPermissions = false;
		FileSystem_cacheFiles = new Hashtable(16);
	}
}
