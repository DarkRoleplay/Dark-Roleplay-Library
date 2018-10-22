package net.dark_roleplay.library.experimental.blueprints.v2.palletes;

import net.minecraft.block.state.IBlockState;

public interface IPallete {

	public int getIDForState(IBlockState state);

	public IBlockState geStateFroID(int id);

	public byte getRequiredBitsAmounts();

}
