package net.dark_roleplay.library.experimental.blueprints.v2.layers;

import net.dark_roleplay.library.experimental.blueprints.v2.palletes.IPallete;
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
}
