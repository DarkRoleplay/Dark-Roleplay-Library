package net.dark_roleplay.library;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.dark_roleplay.library.configs.prefabs.RGB;
import net.dark_roleplay.library.configs.prefabs.RGBA;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod(DRPLibrary.MODID)
public class DRPLibrary {

	public static final String	MODID	= "drplibrary";

	// Directly reference a log4j logger.
	public static final Logger	LOGGER	= LogManager.getLogger();

	public DRPLibrary() {
		// ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON,
		// DebugConfig.CONFIG_SPEC);
		// ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.CONFIGGUIFACTORY,
		// () -> {
		//
		// });
	}

	// modLoadingContext.registerExtensionPoint(ExtensionPoint.GUIFACTORY, () ->
	// GuiHandler.Client::getClientGuiElement);

	public static class DebugConfig {

		public static final DebugConfig			CONFIG;
		public static final ForgeConfigSpec		CONFIG_SPEC;

		public final RGB						rgb;
		public final ForgeConfigSpec.IntValue	rgba;

		static {
			Pair<DebugConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(DebugConfig::new);
			CONFIG_SPEC = specPair.getRight();
			CONFIG = specPair.getLeft();
		}

		public DebugConfig(ForgeConfigSpec.Builder builder) {
			builder.push("testing");
			rgb = new RGB("rgb", builder);
			rgba = builder.comment("Test 2").translation("drplibrary.config.test2").defineInRange("rgba", 0xFFFF0000, 0, 0xFFFFFFFF);
			builder.pop();
		}
	}
}
