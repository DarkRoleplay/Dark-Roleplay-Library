package net.dark_roleplay.library.util;

import net.minecraft.launchwrapper.Launch;

public class InDevUtil {

	public static boolean isDevEnv() {
        return (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
	}
}
