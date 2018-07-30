package net.dark_roleplay.library;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@Mod(modid = References.MODID, version = References.VERSION, name = References.NAME, acceptedMinecraftVersions = References.ACCEPTEDVERSIONS, certificateFingerprint = "893c317856cf6819b3a8381c5664e4b06df7d1cc")
public class DRPLibrary {

	@EventHandler
	public void preInit(FMLPreInitializationEvent event){				
		References.init(event);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		if(event.getSide().isClient()) {
			ClientProxy.stuff();
		}
	}
	
	@SubscribeEvent
	public void registerItems(RegistryEvent.Register<Item> event) {
		for(Item item : items) {
			event.getRegistry().register(item);
		}
	}
	
	@SubscribeEvent
	public void registerBlocks(RegistryEvent.Register<Block> event) {
		registerBlocks(event.getRegistry(), CreativeTabs.BUILDING_BLOCKS,
				
			new Block(Material.IRON)
				.setRegistryName(References.MODID, "block name"),
				
			new Block(Material.IRON)
				.setRegistryName(References.MODID, "block2 name"),

			new Block(Material.IRON)
				.setRegistryName(References.MODID, "block3 name")
		);
	}
	
	//We create a list, in which we store all ItemBlocks
	private List<Item> items = new ArrayList<Item>();
	
	//A method that automaticly creates an Item for a Block
	private void registerBlocks(IForgeRegistry<Block> registry, CreativeTabs tab, Block... blocks) {
		for(Block block : blocks) {
			registry.register(block);
			
//			ItemBlock item = (ItemBlock) new ItemBlock(block).setRegistryName(block.getRegistryName()).setCreativeTab(tab);
		}
	}
}
