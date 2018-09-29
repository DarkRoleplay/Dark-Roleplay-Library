package net.dark_roleplay.library.experimental.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;

/**
 * Created: 31.05.2018
 * Last edit: 31.05.2018
 * Last edit by: JTK222
 * Version added: 0.1.0
 * State: Finished
 */
public class BlockSettings {

	private Material material;
	private MapColor mapColor;
	private SoundType soundType;
	private float hardness;
	private float blastResistance;
	private float lightLevel = 0F;
	private int lightOpacity = 0;
	private BlockRenderLayer renderLayer;
	private BlockFaceShape faceShape;
	private EnumBlockRenderType renderType;

	private boolean isFullCube = true;
	private boolean isOpaqueCube = true;

	/**
	 * Used as a shortcut to reusing settings for Blocks, and as a helper to not forget to apply any of them :3
	 * @param material
	 * @param soundType
	 * @param hardness
	 * @param blastResistance
	 */
	public BlockSettings(Material material, SoundType soundType, float hardness, float blastResistance) {
		this(material, material.getMaterialMapColor(), soundType, hardness, blastResistance);
	}

	/**
	 * Used as a shortcut to reusing settings for Blocks, and as a helper to not forget to apply any of them :3
	 * @param material
	 * @param mapColor
	 * @param soundType
	 * @param hardness
	 * @param blastResistance
	 */
	public BlockSettings(Material material, MapColor mapColor, SoundType soundType, float hardness, float blastResistance) {
		this.material = material;
		this.mapColor = mapColor;
		this.soundType = soundType;
		this.hardness = hardness;
		this.blastResistance = blastResistance;
		this.renderLayer = BlockRenderLayer.SOLID;
		this.faceShape = BlockFaceShape.SOLID;
	}



	public BlockSettings setBlockFaceShape(BlockFaceShape faceShape) {
		this.faceShape = faceShape;
		return this;
	}

	public BlockSettings setFullAndOpaque(boolean isFullCube, boolean isOpaqueCube) {
		this.isFullCube = isFullCube;
		this.isOpaqueCube = isOpaqueCube;
		return this;
	}

	public BlockSettings setBlockRenderLayer(BlockRenderLayer renderLayer) {
		this.renderLayer = renderLayer;
		return this;
	}

	public BlockSettings setBlockRenderType(EnumBlockRenderType renderType) {
		this.renderType = renderType;
		return this;
	}

	public BlockSettings setLightLevel(int lightLevel) {
		this.lightLevel = 0.06666666F * lightLevel;
		return this;
	}

	public BlockSettings setLightOpacity(int lightOpacity) {
		this.lightOpacity = lightOpacity;
		return this;
	}

	public Material getMaterial() {
		return this.material;
	}

	public MapColor getMapColor() {
		return this.mapColor;
	}

	public float getHardness() {
		return this.hardness;
	}

	public float getBlastResistance() {
		return this.blastResistance;
	}

	public float getLightLevel() {
		return this.lightLevel;
	}

	public int getLightOpacity() {
		return this.lightOpacity;
	}

	public SoundType getSoundType() {
		return this.soundType;
	}

	public BlockRenderLayer getBlockRenderLayer() {
		return this.renderLayer;
	}

	public boolean isFullCube() {
		return this.isFullCube;
	}

	public boolean isOpaqueCube() {
		return this.isOpaqueCube;
	}

	public BlockFaceShape getFaceShape() {
		return this.faceShape;
	}

	public EnumBlockRenderType getRenderType() {
		return this.renderType;
	}

	public BlockSettings copy() {
		BlockSettings copy = new BlockSettings(this.material, this.mapColor, this.soundType, this.hardness, this.blastResistance);
		copy.setBlockRenderLayer(this.renderLayer);
		copy.setFullAndOpaque(this.isFullCube, this.isOpaqueCube);
		return copy;
	}
}
