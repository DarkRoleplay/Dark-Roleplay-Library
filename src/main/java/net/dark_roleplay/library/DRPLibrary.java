package net.dark_roleplay.library;

import java.util.ArrayList;
import java.util.List;

import net.dark_roleplay.library.experimental.connected_model.ConnectedModelBlock;
import net.dark_roleplay.library.experimental.connected_model.ConnectedModelLoader;
import net.dark_roleplay.library.experimental.guis.ModularGui_Handler;
import net.dark_roleplay.library.sides.IProxy;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

@Mod(modid = References.MODID, version = References.VERSION, name = References.NAME, acceptedMinecraftVersions = References.ACCEPTEDVERSIONS, certificateFingerprint = "893c317856cf6819b3a8381c5664e4b06df7d1cc")
public class DRPLibrary {

	@SidedProxy(modId = References.MODID)
	public static IProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){				
		References.init(event);
		proxy.preInit(event);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
	}
	
//	@EventBusSubscriber(modid = References.MODID)
//	public static class RegistriesAndStuff{
//		@SubscribeEvent
//		public static void registerItems(RegistryEvent.Register<Item> event) {
//			for(Item item : items) {
//				event.getRegistry().register(item);
//			}
//		}
//		
//		@SubscribeEvent
//		public static void registerBlocks(RegistryEvent.Register<Block> event) {
////			Block block = new ConnectedModelBlock();
////			registerBlocks(event.getRegistry(), CreativeTabs.BUILDING_BLOCKS,
////					
////				new Block(Material.IRON)
////					.setRegistryName(References.MODID, "block name"),
////					
////				new Block(Material.IRON)
////					.setRegistryName(References.MODID, "block2 name"),
////
////				new Block(Material.IRON)
////					.setRegistryName(References.MODID, "block3 name"),
////				
////				block
////			);
//			
////			ConnectedModelLoader.registerConnectedModelBlock(block);
//		}
//		
//		//We create a list, in which we store all ItemBlocks
//		private static List<Item> items = new ArrayList<Item>();
//		
//		//A method that automaticly creates an Item for a Block
//		private static void registerBlocks(IForgeRegistry<Block> registry, CreativeTabs tab, Block... blocks) {
//			for(Block block : blocks) {
//				registry.register(block);
//				
//				ItemBlock item = (ItemBlock) new ItemBlock(block).setRegistryName(block.getRegistryName()).setCreativeTab(tab);
//				items.add(item);
//			}
//		}
//	}
	
	public static class ServerProxy implements IProxy{
		
	}
	
	@SideOnly(Side.CLIENT)
	public static class ClientProxy implements IProxy{
		
		@Override
		public void preInit(FMLPreInitializationEvent event) {
			ModelLoaderRegistry.registerLoader(new net.dark_roleplay.library.experimental.connected_model.ConnectedModelLoader());

		}

		
		@Override
		public void init(FMLInitializationEvent event) {
			IResourceManager manager = Minecraft.getMinecraft().getResourceManager();
			if(manager instanceof IReloadableResourceManager) {
				((IReloadableResourceManager)manager).registerReloadListener(new ModularGui_Handler());
			}
		}
	}
}
