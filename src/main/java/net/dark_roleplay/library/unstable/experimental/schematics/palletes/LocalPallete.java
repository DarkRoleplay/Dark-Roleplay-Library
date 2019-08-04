package net.dark_roleplay.library.unstable.experimental.schematics.palletes;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import net.dark_roleplay.library.unstable.experimental.data_compression.IntArrayCompression;
import net.dark_roleplay.library.unstable.experimental.schematics.exception.NotContainedInPalleteException;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;

public class LocalPallete implements IPallete {

	private int[]	pallete		= new int[0];

	IPallete		parent		= null;

	private int		voidID		= -1;
	private int		solidID		= -1;

	private int		trueSize	= 0;

	public LocalPallete(CompoundNBT compound, IPallete parent) {
		this.parent = parent;

		this.pallete = compound.getIntArray("BlockStates");
		this.trueSize = this.pallete.length;

		if(compound.contains("VoidID")) this.voidID = compound.getInt("VoidID");
		if(compound.contains("SolidID")) this.solidID = compound.getInt("SolidID");
	}

	@Override
	public int getIDForState(BlockState state) throws NotContainedInPalleteException {
		int parentID = this.parent.getIDForState(state);

		for(int i = 0; i < this.trueSize; i++ ) {
			if(this.pallete[i] == parentID) return i;
		}

		throw new NotContainedInPalleteException(String.format("Couldn't find the BlockState %s, within the Pallete", state.toString()));
	}

	@Override
	public int getOrAddIDForState(BlockState state) {
		int parentID = this.parent.getOrAddIDForState(state);

		for(int i = 0; i < this.trueSize; i++ ) {
			if(this.pallete[i] == parentID) return i;
		}

		if(this.trueSize < this.pallete.length) {
			this.pallete[this.trueSize] = parentID;
			this.trueSize += 1;
			return this.trueSize - 1;
		} else {
			int[] helper = Arrays.copyOf(this.pallete, this.pallete.length + 5);
			this.pallete[this.trueSize] = parentID;
			this.trueSize += 1;
			return this.trueSize - 1;
		}
	}

	@Override
	public BlockState getStateForID(int id) throws NotContainedInPalleteException {
		if(id >= this.trueSize) throw new NotContainedInPalleteException(String.format("Couldn't find the ID %d, within the Pallete", id));

		return this.parent.getStateForID(this.pallete[id]);
	}

	@Override
	public byte getRequiredBitsAmounts() { return (byte) IntArrayCompression.getRequiredBitCount(this.trueSize); }

	@Override
	public Set<String> getRequiredMods() {
		Set<String> mods = new HashSet<String>();

		for(int i = 0; i < this.trueSize; i++ ) {
			try {
				BlockState state = this.parent.getStateForID(this.pallete[i]);
				mods.add(state.getBlock().getRegistryName().getNamespace());
			} catch(NotContainedInPalleteException e) {
			}
		}

		return mods;
	}

	@Override
	public int getStructureVoidID() { return this.voidID; }

	@Override
	public int getStructureSolidID() { return this.solidID; }
}
