package net.dark_roleplay.library.experimental.connected_model;

import static net.dark_roleplay.library.experimental.connected_model.ConnectedModelBlockStates.CENTER_LEFT;
import static net.dark_roleplay.library.experimental.connected_model.ConnectedModelBlockStates.CENTER_RIGHT;
import static net.dark_roleplay.library.experimental.connected_model.ConnectedModelBlockStates.NORTH_CENTER;
import static net.dark_roleplay.library.experimental.connected_model.ConnectedModelBlockStates.NORTH_LEFT;
import static net.dark_roleplay.library.experimental.connected_model.ConnectedModelBlockStates.NORTH_RIGHT;
import static net.dark_roleplay.library.experimental.connected_model.ConnectedModelBlockStates.SOUTH_CENTER;
import static net.dark_roleplay.library.experimental.connected_model.ConnectedModelBlockStates.SOUTH_LEFT;
import static net.dark_roleplay.library.experimental.connected_model.ConnectedModelBlockStates.SOUTH_RIGHT;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import net.dark_roleplay.library.experimental.models.DelayedModel;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoaderRegistry;

public class ConnectedModelLoader implements ICustomModelLoader{

	private static Set<String> registryNames = new HashSet<String>();

	private static Map<String, DelayedModel> bakers = new HashMap<String, DelayedModel>();

	public static void registerConnectedModelBlock(Block block) {
		registryNames.add(block.getRegistryName().toString());
	}

	@Override
	public boolean accepts(ResourceLocation modelLocation) {
		if(!(modelLocation instanceof ModelResourceLocation)) return false;
		if(modelLocation.toString().contains("inventory")) return false;

		String name = modelLocation.getNamespace() + ":" + modelLocation.getPath();

		if(registryNames.contains(name)) {
			return true;
		}
		return false;
	}

	@Override
	public IModel loadModel(ResourceLocation modelLocation) throws Exception {
		if(!(modelLocation instanceof ModelResourceLocation)) throw new Exception("FUCKING ERROR!");

		if(this.isHorizontal(modelLocation)) {
			String name = modelLocation.getNamespace() + ":" + modelLocation.getPath();

			if(bakers.containsKey(name)) return bakers.get(name);

			JsonObject blockState = getBlockState(new ResourceLocation(modelLocation.getNamespace(), "blockstates/drp/connected_models/" + modelLocation.getPath() + ".json"));

			if(blockState == null) return null;

			JsonObject textures = blockState.get("textures").getAsJsonObject();
			JsonObject pack0 = blockState.get("pack0").getAsJsonObject();
			JsonObject pack1 = blockState.get("pack1").getAsJsonObject();
			JsonObject pack2 = blockState.get("pack2").getAsJsonObject();
			JsonObject pack3 = blockState.get("pack3").getAsJsonObject();
			JsonObject pack4 = blockState.get("pack4").getAsJsonObject();

			ImmutableMap.Builder<String, String> builder = ImmutableMap.<String, String>builder();

			for(Map.Entry<String, JsonElement> entry : textures.entrySet()) {
				builder.put(entry.getKey(), entry.getValue().getAsString());
			}

			ImmutableMap<String, String> textureRemap = builder.build();

			IModel center = getModel(pack0.get("center").getAsString()).retexture(textureRemap);

			DelayedModel baker = new HorizontalConnectedModelBaker(new ResourceLocation(textures.get("particle").getAsString()), center, new IModel[] {
					getModel(pack0.get("top_left").getAsString()).retexture(textureRemap),
					getModel(pack0.get("top_center").getAsString()).retexture(textureRemap),
					getModel(pack0.get("top_right").getAsString()).retexture(textureRemap),
					getModel(pack0.get("bottom_left").getAsString()).retexture(textureRemap),
					getModel(pack0.get("bottom_center").getAsString()).retexture(textureRemap),
					getModel(pack0.get("bottom_right").getAsString()).retexture(textureRemap),
					getModel(pack0.get("center_left").getAsString()).retexture(textureRemap),
					getModel(pack0.get("center_right").getAsString()).retexture(textureRemap)
			}, new IModel[] {
					getModel(pack1.get("top_left").getAsString()).retexture(textureRemap),
					getModel(pack1.get("top_center").getAsString()).retexture(textureRemap),
					getModel(pack1.get("top_right").getAsString()).retexture(textureRemap),
					getModel(pack1.get("bottom_left").getAsString()).retexture(textureRemap),
					getModel(pack1.get("bottom_center").getAsString()).retexture(textureRemap),
					getModel(pack1.get("bottom_right").getAsString()).retexture(textureRemap),
					getModel(pack1.get("center_left").getAsString()).retexture(textureRemap),
					getModel(pack1.get("center_right").getAsString()).retexture(textureRemap)
			}, new IModel[] {
					getModel(pack2.get("top_left").getAsString()).retexture(textureRemap),
					getModel(pack2.get("top_right").getAsString()).retexture(textureRemap),
					getModel(pack2.get("bottom_left").getAsString()).retexture(textureRemap),
					getModel(pack2.get("bottom_right").getAsString()).retexture(textureRemap)
			},new IModel[] {
					getModel(pack3.get("top_left").getAsString()).retexture(textureRemap),
					getModel(pack3.get("top_right").getAsString()).retexture(textureRemap),
					getModel(pack3.get("bottom_left").getAsString()).retexture(textureRemap),
					getModel(pack3.get("bottom_right").getAsString()).retexture(textureRemap)
			}, new IModel[] {
					getModel(pack4.get("top_left").getAsString()).retexture(textureRemap),
					getModel(pack4.get("top_right").getAsString()).retexture(textureRemap),
					getModel(pack4.get("bottom_left").getAsString()).retexture(textureRemap),
					getModel(pack4.get("bottom_right").getAsString()).retexture(textureRemap)
			});

			bakers.put(name, baker);

			return baker;
		}

		return null;
	}

	@Override
	public void onResourceManagerReload(IResourceManager resourceManager) {
		bakers = new HashMap<String, DelayedModel>();
	}

	private boolean isHorizontal(ResourceLocation modelLocation) {
		String variants = ((ModelResourceLocation)modelLocation).getVariant();

		return variants.contains(NORTH_LEFT.getName()) && variants.contains(NORTH_CENTER.getName()) && variants.contains(NORTH_RIGHT.getName()) &&
				 variants.contains(SOUTH_LEFT.getName()) && variants.contains(SOUTH_CENTER.getName()) && variants.contains(SOUTH_RIGHT.getName()) &&
				 variants.contains(CENTER_LEFT.getName()) && variants.contains(CENTER_RIGHT.getName());
	}

	protected static Gson gson = new GsonBuilder().setPrettyPrinting().create();

	protected static JsonObject getBlockState(ResourceLocation blockState) throws IOException {
		IResourceManager manager = Minecraft.getMinecraft().getResourceManager();
		IResource resource = manager.getResource(blockState);

		JsonReader reader = new JsonReader(new InputStreamReader(resource.getInputStream()));

		return gson.fromJson(reader, JsonObject.class);
	}

	protected static IModel getModel(String modelName) {
		return ModelLoaderRegistry.getModelOrLogError(
				new ResourceLocation(modelName),
				"A problem occured while trying to load: " + modelName);
	}
}
