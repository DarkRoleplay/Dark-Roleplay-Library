package net.dark_roleplay.library.unstable.experimental.connected_model;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import net.dark_roleplay.library.unstable.experimental.models.DelayedModel;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.IUnbakedModel;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.resources.IResource;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.resource.IResourceType;
import net.minecraftforge.resource.VanillaResourceType;

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

		if(registryNames.contains(modelLocation.getNamespace() + ":" + modelLocation.getPath())) {
			return true;
		}
		return false;
	}
	
	@Override
	public IUnbakedModel loadModel(ResourceLocation modelLocation) throws Exception {
		if(!(modelLocation instanceof ModelResourceLocation)) throw new Exception("If you see this, pls kill me :sob:");

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

			ImmutableList.Builder<ResourceLocation> texBuilder = ImmutableList.builder();
			texBuilder.add(new ResourceLocation(textures.get("particle").getAsString()));

			for(Map.Entry<String, JsonElement> entry : textures.entrySet()) {
				builder.put(entry.getKey(), entry.getValue().getAsString());
				texBuilder.add(new ResourceLocation(entry.getValue().getAsString()));
			}

			ImmutableMap<String, String> textureRemap = builder.build();

			IModel center = getModel(pack0.get("center").getAsString()).retexture(textureRemap);

			ImmutableList<ResourceLocation> textures2 = texBuilder.build();

			//PORT TO 1.13
//			HorizontalConnectedModelBaker baker = new HorizontalConnectedModelBaker(textures2, center, new IModel[] {
//					getModel(pack0.get("top_left").getAsString()).retexture(textureRemap),
//					getModel(pack0.get("top_center").getAsString()).retexture(textureRemap),
//					getModel(pack0.get("top_right").getAsString()).retexture(textureRemap),
//					getModel(pack0.get("bottom_left").getAsString()).retexture(textureRemap),
//					getModel(pack0.get("bottom_center").getAsString()).retexture(textureRemap),
//					getModel(pack0.get("bottom_right").getAsString()).retexture(textureRemap),
//					getModel(pack0.get("center_left").getAsString()).retexture(textureRemap),
//					getModel(pack0.get("center_right").getAsString()).retexture(textureRemap)
//			}, new IModel[] {
//					getModel(pack1.get("top_left").getAsString()).retexture(textureRemap),
//					getModel(pack1.get("top_center").getAsString()).retexture(textureRemap),
//					getModel(pack1.get("top_right").getAsString()).retexture(textureRemap),
//					getModel(pack1.get("bottom_left").getAsString()).retexture(textureRemap),
//					getModel(pack1.get("bottom_center").getAsString()).retexture(textureRemap),
//					getModel(pack1.get("bottom_right").getAsString()).retexture(textureRemap),
//					getModel(pack1.get("center_left").getAsString()).retexture(textureRemap),
//					getModel(pack1.get("center_right").getAsString()).retexture(textureRemap)
//			}, new IModel[] {
//					getModel(pack2.get("top_left").getAsString()).retexture(textureRemap),
//					getModel(pack2.get("top_right").getAsString()).retexture(textureRemap),
//					getModel(pack2.get("bottom_left").getAsString()).retexture(textureRemap),
//					getModel(pack2.get("bottom_right").getAsString()).retexture(textureRemap)
//			},new IModel[] {
//					getModel(pack3.get("top_left").getAsString()).retexture(textureRemap),
//					getModel(pack3.get("top_right").getAsString()).retexture(textureRemap),
//					getModel(pack3.get("bottom_left").getAsString()).retexture(textureRemap),
//					getModel(pack3.get("bottom_right").getAsString()).retexture(textureRemap)
//			}, new IModel[] {
//					getModel(pack4.get("top_left").getAsString()).retexture(textureRemap),
//					getModel(pack4.get("top_right").getAsString()).retexture(textureRemap),
//					getModel(pack4.get("bottom_left").getAsString()).retexture(textureRemap),
//					getModel(pack4.get("bottom_right").getAsString()).retexture(textureRemap)
//			});

//			bakers.put(name, baker);
//
//			return baker;
		}


		System.out.println("NULLING SEARCH ME");
		return null;
	}

	@Override
	public void onResourceManagerReload(IResourceManager resourceManager) {
		bakers.clear();
	}

    @Override
    public void onResourceManagerReload(IResourceManager resourceManager, Predicate<IResourceType> resourcePredicate){
        if (resourcePredicate.test(VanillaResourceType.MODELS)) {
    		bakers.clear();
        }
    }

	private boolean isHorizontal(ResourceLocation modelLocation) {
//		String variants = ((ModelResourceLocation)modelLocation).getVariant();

		return true;

//		return variants.contains(NORTH_LEFT.getName()) && variants.contains(NORTH_CENTER.getName()) && variants.contains(NORTH_RIGHT.getName()) &&
//				 variants.contains(SOUTH_LEFT.getName()) && variants.contains(SOUTH_CENTER.getName()) && variants.contains(SOUTH_RIGHT.getName()) &&
//				 variants.contains(CENTER_LEFT.getName()) && variants.contains(CENTER_RIGHT.getName());
	}

	protected static Gson gson = new GsonBuilder().setPrettyPrinting().create();

	protected static JsonObject getBlockState(ResourceLocation blockState) throws IOException {
		IResourceManager manager = Minecraft.getInstance().getResourceManager();
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
