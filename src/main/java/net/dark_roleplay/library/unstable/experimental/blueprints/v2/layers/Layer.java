package net.dark_roleplay.library.unstable.experimental.blueprints.v2.layers;

import net.dark_roleplay.library.unstable.experimental.blueprints.v2.palletes.IPallete;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.BlockPos;

public class Layer {

	private IPallete pallete = null;

	private BlockPos size = new BlockPos(5, 5, 5);
	private BlockPos origin = new BlockPos(0, 0, 0);

	private int[][][] data = new int[0][0][0];

	private NBTTagList entities = new NBTTagList();
	private NBTTagList tileEntities = new NBTTagList();

	private NBTTagCompound custom = new NBTTagCompound();

	public Layer(IPallete pallete, BlockPos size, BlockPos origin, int[][][] data) {
		this.pallete = pallete;
		this.size = size;
		this.origin = origin;
		this.data = data;
	}

	public void setEntitiesTag(NBTTagList entities) {
		this.entities = entities;
	}

	public void setTileEntitesTag(NBTTagList tileEntities) {
		this.tileEntities = tileEntities;
	}

	public void setCustom(NBTTagCompound custom) {
		this.custom = custom;
	}

	public IPallete getPallete() {
		return this.pallete;
	}

	public BlockPos getSize() {
		return this.size;
	}

	public BlockPos getOrigin() {
		return this.origin;
	}

	public int[][][] getData() {
		return this.data;
	}

	public NBTTagList getEntities() {
		return this.entities;
	}

	public NBTTagList getTileEntities() {
		return this.tileEntities;
	}

	public NBTTagCompound getCustom() {
		return this.custom;
	}
}
