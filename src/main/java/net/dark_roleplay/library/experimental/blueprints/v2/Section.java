package net.dark_roleplay.library.experimental.blueprints.v2;

import net.dark_roleplay.library.experimental.blueprints.v2.exception.DataCorruptedException;
import net.dark_roleplay.library.experimental.blueprints.v2.exception.VersionNotSupportedException;
import net.minecraft.nbt.NBTTagCompound;

public class Section {

	private short sizeX, sizeY, sizeZ;
	
	private int[] localPallete;
	private int[][][] structure;
	private NBTTagCompound[] tileEntities;
	private NBTTagCompound[] entities;
	
	public NBTTagCompound writeToNBT() {
		return null;
	}
	
	public static Section readFromNBT(NBTTagCompound compound) throws VersionNotSupportedException, DataCorruptedException{
		return null;
	}

}
