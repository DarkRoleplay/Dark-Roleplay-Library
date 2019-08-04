package net.dark_roleplay.library.unstable.experimental.models;

import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;

import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.IUnbakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.ItemOverrideList;
import net.minecraft.client.renderer.model.ModelRotation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.common.model.TRSRTransformation;

// PORT TO 1.13
public abstract class DelayedModel implements IBakedModel, IUnbakedModel {

	protected VertexFormat										format;

	protected Function<ResourceLocation, TextureAtlasSprite>	textureGetter;

	protected String											particle;

	protected void bakeInfo(VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> textureGetter, ResourceLocation particle) {
		this.format = format;
		this.textureGetter = textureGetter;
		this.particle = particle.getNamespace() + ":" + particle.getPath();
	}

	protected void addQuads(List<BakedQuad> quads, IModel model, int yRotate, int xRotate, BlockState state, Direction side, long rand) {
		TRSRTransformation transform = TRSRTransformation.from(ModelRotation.getModelRotation(xRotate, yRotate));
		// IBakedModel baked = model.bake(transform, this.format,
		// this.textureGetter);
		// quads.addAll(baked.getQuads(state, side, new Random()));
	}

	protected void addQuads(List<BakedQuad> quads, IModel model, BlockState state, Direction side, long rand) {
		this.addQuads(quads, model, 0, 0, state, side, rand);
	}

	@Override
	public IModelState getDefaultState() { return TRSRTransformation.identity(); }

	@Override
	public boolean isAmbientOcclusion() { return false; }

	@Override
	public boolean isGui3d() { return true; }

	@Override
	public boolean isBuiltInRenderer() { return false; }

	@Override
	public TextureAtlasSprite getParticleTexture() { return Minecraft.getInstance().getTextureMap().getAtlasSprite(this.particle); }

	@Override
	public ItemCameraTransforms getItemCameraTransforms() { return ItemCameraTransforms.DEFAULT; }

	@Override
	public ItemOverrideList getOverrides() { return ItemOverrideList.EMPTY; }

	// @Override
	// public IBakedModel bake(Function<ResourceLocation, IUnbakedModel>
	// modelGetter, Function<ResourceLocation, TextureAtlasSprite> spriteGetter,
	// IModelState state,
	// boolean uvlock, VertexFormat format) {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// @Override
	// public Collection<ResourceLocation> getOverrideLocations() {
	// // TODO Auto-generated method stub
	// return null;
	// }

	@Override
	public Collection<ResourceLocation> getTextures(Function<ResourceLocation, IUnbakedModel> modelGetter, Set<String> missingTextureErrors) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BakedQuad> getQuads(BlockState state, Direction side, Random rand) {
		// TODO Auto-generated method stub
		return null;
	}

}
