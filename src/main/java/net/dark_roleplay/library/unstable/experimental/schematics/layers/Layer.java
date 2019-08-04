package net.dark_roleplay.library.unstable.experimental.schematics.layers;

import net.dark_roleplay.library.unstable.experimental.schematics.palletes.IPallete;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.math.BlockPos;

public class Layer {

	private IPallete	pallete			= null;

	private BlockPos	size			= new BlockPos(5, 5, 5);
	private BlockPos	origin			= new BlockPos(0, 0, 0);

	private int[][][]	data			= new int[0][0][0];

	private ListNBT		entities		= new ListNBT();
	private ListNBT		tileEntities	= new ListNBT();

	private CompoundNBT	custom			= new CompoundNBT();

	public Layer(IPallete pallete, BlockPos size, BlockPos origin, int[][][] data) {
		this.pallete = pallete;
		this.size = size;
		this.origin = origin;
		this.data = data;
	}

	public void setEntitiesTag(ListNBT entities) { this.entities = entities; }

	public void setTileEntitesTag(ListNBT tileEntities) { this.tileEntities = tileEntities; }

	public void setCustom(CompoundNBT custom) { this.custom = custom; }

	public IPallete getPallete() { return this.pallete; }

	public BlockPos getSize() { return this.size; }

	public BlockPos getOrigin() { return this.origin; }

	public int[][][] getData() { return this.data; }

	public ListNBT getEntities() { return this.entities; }

	public ListNBT getTileEntities() { return this.tileEntities; }

	public CompoundNBT getCustom() { return this.custom; }
}
