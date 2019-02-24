package net.dark_roleplay.library.models.delay_model_baking;

import java.util.Collection;
import java.util.Set;
import java.util.function.Function;

import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.IUnbakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.model.IModelState;

public class DelayedUnbakedModel implements IUnbakedModel{

	@Override
	public IBakedModel bake(Function<ResourceLocation, IUnbakedModel> modelGetter,
			Function<ResourceLocation, TextureAtlasSprite> spriteGetter, IModelState state, boolean uvlock,
			VertexFormat format) {
		// XXX Auto-generated method stub
		return null;
	}

	@Override
	public Collection<ResourceLocation> getOverrideLocations() {
		// XXX Auto-generated method stub
		return null;
	}

	@Override
	public Collection<ResourceLocation> getTextures(Function<ResourceLocation, IUnbakedModel> modelGetter,
			Set<String> missingTextureErrors) {
		// XXX Auto-generated method stub
		return null;
	}

}
