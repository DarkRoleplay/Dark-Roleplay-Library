package net.dark_roleplay.library.experimental.blueprints.v2;

import net.dark_roleplay.library.experimental.blueprints.v2.exception.DataCorruptedException;
import net.dark_roleplay.library.experimental.blueprints.v2.exception.UnsupportedVersionException;
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
	
	public static Section readFromNBT(NBTTagCompound compound) throws UnsupportedVersionException, DataCorruptedException{
		return null;
	}

}
