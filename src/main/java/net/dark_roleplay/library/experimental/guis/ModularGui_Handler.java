package net.dark_roleplay.library.experimental.guis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.dark_roleplay.library.References;
import net.dark_roleplay.library.experimental.guis.modular.ModularGuiHandler;
import net.dark_roleplay.library.experimental.guis.modular.ModularGui_Template;
import net.dark_roleplay.library.experimental.guis.modular.Modular_Background;
import net.dark_roleplay.library.experimental.guis.modular.Modular_Buttons;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.util.ResourceLocation;

public class ModularGui_Handler implements IResourceManagerReloadListener {
	
	@Override
	public void onResourceManagerReload(IResourceManager manager) {	
		References.LOGGER.info("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
		References.LOGGER.info("Starting to load Dark Roleplay modular guis");
		References.LOGGER.info("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
		
//		ClientProxy.modularGuis.add();
		ModularGuiHandler.currentGui = loadModularGui(new ResourceLocation(References.MODID, "textures/guis/modular_gui/vanilla.json"));//ClientProxy.modularGuis.get(0);

		References.LOGGER.info("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
		References.LOGGER.info("Finished loading of Dark Roleplay modular guis");
		References.LOGGER.info("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
	}
	
	public static ModularGui_Template loadModularGui(ResourceLocation loc){
		InputStream in;
		try {
			Gson gson = new Gson();
			in = Minecraft.getMinecraft().getResourceManager().getResource(loc).getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			JsonElement je = gson.fromJson(reader, JsonElement.class);
			JsonObject json = je.getAsJsonObject();
			
			JsonElement version_json = json.get("version");
			JsonElement name_json = json.get("name");
			JsonObject background_json = json.getAsJsonObject("background");
			JsonObject buttons_json = json.getAsJsonObject("buttons");

			if(version_json != null && name_json != null){

				ModularGui_Template template = new ModularGui_Template(name_json.getAsString());
					
				boolean skip = false;
				
				if(background_json != null){
					skip = false;
					JsonElement path = background_json.get("path");
					JsonElement path_hollow = background_json.get("path_hollow");
					JsonElement left_json = background_json.get("left");
					JsonElement right_json = background_json.get("right");
					JsonElement top_json = background_json.get("top");
					JsonElement bottom_json = background_json.get("bottom");
					JsonElement width_json = background_json.get("width");
					JsonElement height_json = background_json.get("height");
					
					if(path == null){
						References.LOGGER.warn("Modular Backgrounds: Cannot find 'path' for 'background'");
						skip = true;
					}else if(path_hollow == null){
						References.LOGGER.warn("Modular Backgrounds: Cannot find 'path_hollow' for 'background'");
						skip = true;
					}else if(left_json == null){
						References.LOGGER.warn("Modular Backgrounds: Cannot find 'left' for 'background'");
						skip = true;
					}else if(right_json == null){
						References.LOGGER.warn("Modular Backgrounds: Cannot find 'right' for 'background'");
						skip = true;
					}else if(top_json == null){
						References.LOGGER.warn("Modular Backgrounds: Cannot find 'top' for 'background'");
						skip = true;
					}else if(bottom_json == null){
						References.LOGGER.warn("Modular Backgrounds: Cannot find 'bottom' for 'background'");
						skip = true;
					}else if(width_json == null){
						References.LOGGER.warn("Modular Backgrounds: Cannot find 'width' for 'background'");
						skip = true;
					}else if(height_json == null){
						References.LOGGER.warn("Modular Backgrounds: Cannot find 'height' for 'background'");
						skip = true;
					}
					
					if(!skip)
						template.setModularBG(new Modular_Background(new ResourceLocation(path.getAsString()), new ResourceLocation(path_hollow.getAsString()), left_json.getAsInt(), right_json.getAsInt(), top_json.getAsInt(), bottom_json.getAsInt(), width_json.getAsInt(), height_json.getAsInt()));
				}
					
				if(buttons_json != null){
					skip = false;
					JsonElement pathDisabled = buttons_json.get("path_disabled");
					JsonElement pathEnabled = buttons_json.get("path_enabled");
					JsonElement pathHovered = buttons_json.get("path_hovered");
					JsonElement left_json = buttons_json.get("left");
					JsonElement right_json = buttons_json.get("right");
					JsonElement top_json = buttons_json.get("top");
					JsonElement bottom_json = buttons_json.get("bottom");
					JsonElement width_json = buttons_json.get("width");
					JsonElement height_json = buttons_json.get("height");
					
					if(pathDisabled == null){
						References.LOGGER.warn("Modular Backgrounds: Cannot find 'path_disabled' for 'buttons'");
						skip = true;
					}else if(pathEnabled == null){
						References.LOGGER.warn("Modular Backgrounds: Cannot find 'path_enabled' for 'buttons'");
						skip = true;
					}else if(pathHovered == null){
						References.LOGGER.warn("Modular Backgrounds: Cannot find 'path_hovered' for 'buttons'");
						skip = true;
					}else if(left_json == null){
						References.LOGGER.warn("Modular Backgrounds: Cannot find 'left' for 'buttons'");
						skip = true;
					}else if(right_json == null){
						References.LOGGER.warn("Modular Backgrounds: Cannot find 'right' for 'buttons'");
						skip = true;
					}else if(top_json == null){
						References.LOGGER.warn("Modular Backgrounds: Cannot find 'top' for 'buttons'");
						skip = true;
					}else if(bottom_json == null){
						References.LOGGER.warn("Modular Backgrounds: Cannot find 'bottom' for 'buttons'");
						skip = true;
					}else if(width_json == null){
						References.LOGGER.warn("Modular Backgrounds: Cannot find 'width' for 'background'");
						skip = true;
					}else if(height_json == null){
						References.LOGGER.warn("Modular Backgrounds: Cannot find 'height' for 'background'");
						skip = true;
					}
					
					if(!skip)
						template.setModularButtons(new Modular_Buttons(new ResourceLocation(pathDisabled.getAsString()), new ResourceLocation(pathEnabled.getAsString()), new ResourceLocation(pathHovered.getAsString()), left_json.getAsInt(), right_json.getAsInt(), top_json.getAsInt(), bottom_json.getAsInt(), width_json.getAsInt(), height_json.getAsInt()));
				}
				
				return template;
			}
			
			
		} catch (IOException e) {
			References.LOGGER.catching(e);
		}
		return null;
	}
}
