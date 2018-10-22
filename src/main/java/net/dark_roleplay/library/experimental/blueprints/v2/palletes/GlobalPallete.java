package net.dark_roleplay.library.experimental.blueprints.v2.palletes;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagList;

public class GlobalPallete implements IPallete{

	private BiMap<IBlockState, Integer> states = null;

	public GlobalPallete(NBTTagList stateList) {
		 this.states = HashBiMap.create(stateList.tagCount());
	}

	@Override
	public int getIDForState(IBlockState state) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IBlockState geStateFroID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte getRequiredBitsAmounts() {
		// TODO Auto-generated method stub
		return 0;
	}

}
