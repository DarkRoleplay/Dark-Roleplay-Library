package net.dark_roleplay.library.models.delay_model_baking;

import java.util.List;
import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;

public class DelayedBakedModel implements IBakedModel{

	@Override
	public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, Random rand) {
		// XXX Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAmbientOcclusion() {
		// XXX Auto-generated method stub
		return false;
	}

	@Override
	public boolean isGui3d() {
		// XXX Auto-generated method stub
		return false;
	}

	@Override
	public boolean isBuiltInRenderer() {
		// XXX Auto-generated method stub
		return false;
	}

	@Override
	public TextureAtlasSprite getParticleTexture() {
		// XXX Auto-generated method stub
		return null;
	}

	@Override
	public ItemOverrideList getOverrides() {
		// XXX Auto-generated method stub
		return null;
	}

}
