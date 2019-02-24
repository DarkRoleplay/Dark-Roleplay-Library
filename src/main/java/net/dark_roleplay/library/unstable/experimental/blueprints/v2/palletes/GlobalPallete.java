package net.dark_roleplay.library.unstable.experimental.blueprints.v2.palletes;

import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import net.dark_roleplay.library.unstable.experimental.blueprints.v2.exception.NotContainedInPalleteException;
import net.dark_roleplay.library.unstable.experimental.data_compression.IntArrayCompression;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTUtil;
import net.minecraftforge.common.util.Constants.NBT;

/**
 * A General implementation of {@link IPallete}, this implementation stores the IBlockState and their corresponding ID
 * @version 1.0
 * @author JTK222
 */
public class GlobalPallete implements IPallete{

	private int palleteSize = 0;
	private BiMap<IBlockState, Integer> states = null;

	private int voidID = -1;
	private int solidID = -1;

	public GlobalPallete() {
		 this.states = HashBiMap.create();
	}

	public GlobalPallete(NBTTagCompound compound) {
		this();

		NBTTagList blockStates = compound.getList("BlockStates", NBT.TAG_COMPOUND);

		for(int i = 0; i < blockStates.size(); i++) {
			NBTTagCompound stateComp = blockStates.getCompound(i);
			this.states.put(NBTUtil.readBlockState(stateComp), i);
		}

		if(compound.hasKey("VoidID")) this.voidID = compound.getInt("VoidID");
		if(compound.hasKey("SolidID")) this.solidID = compound.getInt("SolidID");
	}

	@Override
	public int getIDForState(IBlockState state) throws NotContainedInPalleteException {
		if(this.states.containsKey(state)) {
			return this.states.get(state);
		}else {
			throw new NotContainedInPalleteException(String.format("Couldn't find the IBlockState %s, within the Pallete", state.toString()));
		}
	}

	@Override
	public int getOrAddIDForState(IBlockState state) {
		if(this.states.containsKey(state)) {
			return this.states.get(state);
		}else {
			this.states.put(state, this.palleteSize);
			this.palleteSize += 1;
			return this.palleteSize - 1;
		}
	}

	@Override
	public IBlockState getStateForID(int id) throws NotContainedInPalleteException {
		if(this.states.inverse().containsKey(id)) {
			return this.states.inverse().get(id);
		}else {
			throw new NotContainedInPalleteException(String.format("Couldn't find the ID %d, within the Pallete", id));
		}
	}

	@Override
	public byte getRequiredBitsAmounts() {
		return (byte) IntArrayCompression.getRequiredBitCount(this.palleteSize);
	}

	@Override
	public Set<String> getRequiredMods() {
		Set<String> mods = new HashSet<String>();

		for(IBlockState state : this.states.keySet()) {
			mods.add(state.getBlock().getRegistryName().getNamespace());
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

	public void setStructureVoidID(int id) {
		this.voidID = id;
	}

	public void setStructureSolidID(int id) {
		this.solidID = id;
	}
}
