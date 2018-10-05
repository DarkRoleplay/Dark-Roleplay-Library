package net.dark_roleplay.library.experimental.connected_model;

import static net.dark_roleplay.library.experimental.connected_model.ConnectedModelBlockStates.AXIS;
import static net.dark_roleplay.library.experimental.connected_model.ConnectedModelBlockStates.AXIS_HORIZONTAL;
import static net.dark_roleplay.library.experimental.connected_model.ConnectedModelBlockStates.CENTER_LEFT;
import static net.dark_roleplay.library.experimental.connected_model.ConnectedModelBlockStates.CENTER_RIGHT;
import static net.dark_roleplay.library.experimental.connected_model.ConnectedModelBlockStates.FACING;
import static net.dark_roleplay.library.experimental.connected_model.ConnectedModelBlockStates.FACING_HORIZONTAL;
import static net.dark_roleplay.library.experimental.connected_model.ConnectedModelBlockStates.NORTH_CENTER;
import static net.dark_roleplay.library.experimental.connected_model.ConnectedModelBlockStates.NORTH_LEFT;
import static net.dark_roleplay.library.experimental.connected_model.ConnectedModelBlockStates.NORTH_RIGHT;
import static net.dark_roleplay.library.experimental.connected_model.ConnectedModelBlockStates.SOUTH_CENTER;
import static net.dark_roleplay.library.experimental.connected_model.ConnectedModelBlockStates.SOUTH_LEFT;
import static net.dark_roleplay.library.experimental.connected_model.ConnectedModelBlockStates.SOUTH_RIGHT;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.dark_roleplay.library.experimental.models.DelayedModel;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;

public class HorizontalConnectedModelBaker extends DelayedModel{

	protected static final Map<String, List<BakedQuad>> CACHE = Maps.newHashMap();

	protected ImmutableList<ResourceLocation> textures;

	protected IModel center;
	protected IModel[] pack0;
	protected IModel[] pack1;
	protected IModel[] pack2;
	protected IModel[] pack3;
	protected IModel[] pack4;

	public HorizontalConnectedModelBaker(ImmutableList<ResourceLocation> textures, IModel center, IModel[] pack0, IModel[] pack1, IModel[] pack2, IModel[] pack3, IModel[] pack4) {
		this.center = center;
		this.pack0 = pack0;
		this.pack1 = pack1;
		this.pack2 = pack2;
		this.pack3 = pack3;
		this.pack4 = pack4;
		this.particle = textures.get(0).toString();

		this.textures = textures;

	}

	private static String convertExtendedToString(IExtendedBlockState state) {
		StringBuilder b = new StringBuilder();

		b.append(state.toString());

		b.append("[");

		ImmutableMap<IUnlistedProperty<?>, Optional<?>> properties = state.getUnlistedProperties();
		boolean setComma = false;
		for(IUnlistedProperty<?> prop : properties.keySet()) {
			Optional<?> value = properties.get(prop);
			if(value.isPresent()) {
				if(setComma) b.append(",");
				b.append(prop.getName());
				b.append("=");
				b.append(value.get());
			}
			setComma = true;
		}

		b.append("]");

		return b.toString();
	}

	@Override
	public List<BakedQuad> getQuads(IBlockState stateA, EnumFacing side, long rand) {
		side = null;

		List<BakedQuad> result = Lists.newArrayList();

		if(!(stateA instanceof IExtendedBlockState)) return result;
		IExtendedBlockState state = (IExtendedBlockState) stateA;

		//Improving performance
		//Magic trick suggested by Kanno, lets hope it works

		if (CACHE.containsKey(convertExtendedToString(state)))
			return CACHE.get(convertExtendedToString(state));

		int rotateX = 0;
		int rotateY = 0;

		EnumFacing facing = null;
		EnumFacing.Axis axis = null;

		if(stateA.getProperties().containsKey(FACING_HORIZONTAL)) {
			facing = (EnumFacing) stateA.getValue(FACING_HORIZONTAL);
		}else if(stateA.getProperties().containsKey(FACING)) {
			facing = (EnumFacing) stateA.getValue(FACING);
		}else if(stateA.getProperties().containsKey(AXIS_HORIZONTAL)) {
			axis = (EnumFacing.Axis) stateA.getValue(AXIS_HORIZONTAL);
		}else if(stateA.getProperties().containsKey(AXIS)) {
			axis = (EnumFacing.Axis) stateA.getValue(AXIS);
		}

		if(facing != null) {
			switch(facing) {
				case WEST:
					rotateY += 270;
					break;
				case SOUTH:
					rotateY += 180;
					break;
				case EAST:
					rotateY += 90;
					break;
				case NORTH:
					break;
				case DOWN:
					rotateX += 270;
					break;
				case UP:
					rotateX += 90;
					break;
				default:
					break;
			}
		}else if(axis != null) {
			switch(axis) {
				case X:
					rotateY += 90;
					break;
				case Y:
					rotateX += 90;
					break;
				case Z:
					break;
				default:
					break;
			}
		}

		this.addQuads(result, this.center, rotateY, rotateX, state, side, rand);

		boolean topLeft = state.getValue(NORTH_LEFT), topCenter = state.getValue(NORTH_CENTER), topRight = state.getValue(NORTH_RIGHT);
		boolean centerLeft = state.getValue(CENTER_LEFT), centerRight = state.getValue(CENTER_RIGHT);
		boolean bottomLeft = state.getValue(SOUTH_LEFT), bottomCenter = state.getValue(SOUTH_CENTER), bottomRight = state.getValue(SOUTH_RIGHT);

		if(!centerLeft && !topCenter) this.addQuads(result, this.pack0[0], rotateY, rotateX, state, side, rand);
		else if(centerLeft && topLeft && topCenter) this.addQuads(result, this.pack1[0], rotateY, rotateX, state, side, rand);
		else if(!centerLeft && topCenter) this.addQuads(result, this.pack2[0], rotateY, rotateX, state, side, rand);
		else if(centerLeft && !topCenter) this.addQuads(result, this.pack3[0], rotateY, rotateX, state, side, rand);
		else if(centerLeft && topCenter) this.addQuads(result, this.pack4[0], rotateY, rotateX, state, side, rand);

		if(!topCenter) this.addQuads(result, this.pack0[1], rotateY, rotateX, state, side, rand);
		else if(topCenter) this.addQuads(result, this.pack1[1], rotateY, rotateX, state, side, rand);

		if(!topCenter && !centerRight) this.addQuads(result, this.pack0[2], rotateY, rotateX, state, side, rand);
		else if(topCenter && topRight && centerRight) this.addQuads(result, this.pack1[2], rotateY, rotateX, state, side, rand);
		else if(topCenter && !centerRight) this.addQuads(result, this.pack2[1], rotateY, rotateX, state, side, rand);
		else if(!topCenter && centerRight) this.addQuads(result, this.pack3[1], rotateY, rotateX, state, side, rand);
		else if(topCenter && centerRight) this.addQuads(result, this.pack4[1], rotateY, rotateX, state, side, rand);

		if(!centerLeft && !bottomCenter) this.addQuads(result, this.pack0[3], rotateY, rotateX, state, side, rand);
		else if(centerLeft && bottomLeft && bottomCenter) this.addQuads(result, this.pack1[3], rotateY, rotateX, state, side, rand);
		else if(!centerLeft && bottomCenter) this.addQuads(result, this.pack2[2], rotateY, rotateX, state, side, rand);
		else if(centerLeft && !bottomCenter) this.addQuads(result, this.pack3[2], rotateY, rotateX, state, side, rand);
		else if(centerLeft && bottomCenter) this.addQuads(result, this.pack4[2], rotateY, rotateX, state, side, rand);

		if(!bottomCenter) this.addQuads(result, this.pack0[4], rotateY, rotateX, state, side, rand);
		else if(bottomCenter) this.addQuads(result, this.pack1[4], rotateY, rotateX, state, side, rand);

		if(!bottomCenter && !centerRight) this.addQuads(result, this.pack0[5], rotateY, rotateX, state, side, rand);
		else if(bottomCenter && bottomRight && centerRight) this.addQuads(result, this.pack1[5], rotateY, rotateX, state, side, rand);
		else if(bottomCenter && !centerRight) this.addQuads(result, this.pack2[3], rotateY, rotateX, state, side, rand);
		else if(!bottomCenter && centerRight) this.addQuads(result, this.pack3[3], rotateY, rotateX, state, side, rand);
		else if(bottomCenter && centerRight) this.addQuads(result, this.pack4[3], rotateY, rotateX, state, side, rand);

		if(!centerLeft) this.addQuads(result, this.pack0[6], rotateY, rotateX, state, side, rand);
		else if(centerLeft) this.addQuads(result, this.pack1[6], rotateY, rotateX, state, side, rand);

		if(!centerRight) this.addQuads(result, this.pack0[7], rotateY, rotateX, state, side, rand);
		else if(centerRight) this.addQuads(result, this.pack1[7], rotateY, rotateX, state, side, rand);

		CACHE.put(convertExtendedToString(state), result);
		return result;
	}

	@Override
	public Collection<ResourceLocation> getTextures() {
		return this.textures;
	}

	@Override
	public IBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
		this.bakeInfo(format, bakedTextureGetter, new ResourceLocation("drpmedieval", "blocks/clean_plank_spruce")); //this.particle));
		return this;
	}
}
