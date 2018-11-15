package net.dark_roleplay.library.experimental.blueprints.v2.palletes;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import net.dark_roleplay.library.experimental.blueprints.v2.exception.NotContainedInPalleteException;
import net.dark_roleplay.library.experimental.data_compression.IntArrayCompression;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;

public class LocalPallete implements IPallete{

	private int[] pallete = new int[0];

	IPallete parent = null;

	private int voidID = -1;
	private int solidID = -1;

	private int trueSize = 0;

	public LocalPallete(NBTTagCompound compound, IPallete parent) {
		this.parent = parent;

		this.pallete = compound.getIntArray("BlockStates");
		this.trueSize = this.pallete.length;

		if(compound.hasKey("VoidID")) this.voidID = compound.getInteger("VoidID");
		if(compound.hasKey("SolidID")) this.solidID = compound.getInteger("SolidID");
	}

	@Override
	public int getIDForState(IBlockState state) throws NotContainedInPalleteException {
		int parentID = this.parent.getIDForState(state);

		for(int i = 0; i < this.trueSize; i++) {
			if(this.pallete[i] == parentID)
				return i;
		}

		throw new NotContainedInPalleteException(String.format("Couldn't find the IBlockState %s, within the Pallete", state.toString()));
	}

	@Override
	public int getOrAddIDForState(IBlockState state) {
		int parentID = this.parent.getOrAddIDForState(state);

		for(int i = 0; i < this.trueSize; i++) {
			if(this.pallete[i] == parentID)
				return i;
		}

		if(this.trueSize < this.pallete.length) {
			this.pallete[this.trueSize] = parentID;
			this.trueSize += 1;
			return this.trueSize - 1;
		}else {
			int[] helper = Arrays.copyOf(this.pallete, this.pallete.length + 5);
			this.pallete[this.trueSize] = parentID;
			this.trueSize += 1;
			return this.trueSize - 1;
		}
	}

	@Override
	public IBlockState getStateForID(int id) throws NotContainedInPalleteException {
		if(id >= this.trueSize) throw new NotContainedInPalleteException(String.format("Couldn't find the ID %d, within the Pallete", id));

		return this.parent.getStateForID(this.pallete[id]);
	}

	@Override
	public byte getRequiredBitsAmounts() {
		return (byte) IntArrayCompression.getRequiredBitCount(this.trueSize);
	}

	@Override
	public Set<String> getRequiredMods() {
		Set<String> mods = new HashSet<String>();

		for(int i = 0; i < this.trueSize; i++) {
			try {
				IBlockState state = this.parent.getStateForID(this.pallete[i]);
				mods.add(state.getBlock().getRegistryName().getNamespace());
			} catch (NotContainedInPalleteException e) {}
		}

		return mods;
	}

	@Override
	public int getStructureVoidID() {
		return this.voidID;
	}

	@Override
	public int getStructureSolidID() {
		return this.solidID;
	}
}
