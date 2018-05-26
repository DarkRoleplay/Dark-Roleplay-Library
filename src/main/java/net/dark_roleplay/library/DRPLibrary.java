package net.dark_roleplay.library;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = References.MODID, version = References.VERSION, name = References.NAME, acceptedMinecraftVersions = References.ACCEPTEDVERSIONS)
public class DRPLibrary {

	@EventHandler
	public void preInit(FMLPreInitializationEvent event){				
		References.init(event);
	}
}
