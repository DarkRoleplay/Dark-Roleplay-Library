package net.dark_roleplay.library;

import net.dark_roleplay.library.experimental.guis.ModularGui_Handler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy {

	public static void stuff(){
		IResourceManager manager = Minecraft.getMinecraft().getResourceManager();
		if(manager instanceof IReloadableResourceManager) {
		    ((IReloadableResourceManager)manager).registerReloadListener(new ModularGui_Handler());
		}
	}
	
}
