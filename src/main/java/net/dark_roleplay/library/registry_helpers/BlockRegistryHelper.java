package net.dark_roleplay.library.registry_helpers;

import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;

public class BlockRegistryHelper {

	private static IForgeRegistry<Block> REGISTRY;
	
	public static void setRegistry(IForgeRegistry<Block> registry) {
		REGISTRY = registry;
	}
	
	public static void register(Block block, String registryName) {
		BlockRegistryHelper.register(block, new ResourceLocation(registryName));
	}
	
	public static void register(Block block, ResourceLocation registryName) {
		REGISTRY.register(block.setRegistryName(registryName));
	}
	
}
