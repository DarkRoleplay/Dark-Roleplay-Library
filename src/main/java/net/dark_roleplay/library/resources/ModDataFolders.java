package net.dark_roleplay.library.resources;

import java.io.File;

public class ModDataFolders {

	public static boolean doesGlobalModDataExist() {
		File globalModData = new File("./mods_data/");
		return globalModData.exists();
	}

	public static File getModDataFolder(String subFolder) {
		return new File("./mods_data/" + subFolder);
	}

}
