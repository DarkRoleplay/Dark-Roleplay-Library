package net.dark_roleplay.library.sides;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public interface IProxy {

	public default void preInit(FMLPreInitializationEvent event) {}
	
	public default void init(FMLInitializationEvent event) {}

	public default void postInit(FMLPostInitializationEvent event) {}

	
}
