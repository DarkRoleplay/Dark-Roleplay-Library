package net.dark_roleplay.library.unstable.experimental.connected_model;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.StateContainer;

public class ConnectedModelBlock extends Block {

	public ConnectedModelBlock(Block.Properties properties) {
		super(properties);
		// this.setRegistryName("table_test");
		this.setDefaultState(this.getDefaultState());
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		// builder.add(HORIZONTAL);
	}

	// PORT TO 1.13
	// @Override
	// public BlockState getActualState(BlockState state, IBlockAccess world,
	// BlockPos pos) {
	// if(!(state instanceof IExtendedBlockState)) return state;
	//
	// IExtendedBlockState stateCopy = (IExtendedBlockState) state;
	//
	// stateCopy = stateCopy.withProperty(NORTH_LEFT,
	// world.getBlockState(pos.north().west()).getBlock() == this);
	// stateCopy = stateCopy.withProperty(NORTH_CENTER,
	// world.getBlockState(pos.north()).getBlock() == this);
	// stateCopy = stateCopy.withProperty(NORTH_RIGHT,
	// world.getBlockState(pos.north().east()).getBlock() == this);
	// stateCopy = stateCopy.withProperty(SOUTH_LEFT,
	// world.getBlockState(pos.south().west()).getBlock() == this);
	// stateCopy = stateCopy.withProperty(SOUTH_CENTER,
	// world.getBlockState(pos.south()).getBlock() == this);
	// stateCopy = stateCopy.withProperty(SOUTH_RIGHT,
	// world.getBlockState(pos.south().east()).getBlock() == this);
	// stateCopy = stateCopy.withProperty(CENTER_LEFT,
	// world.getBlockState(pos.west()).getBlock() == this);
	// stateCopy = stateCopy.withProperty(CENTER_RIGHT,
	// world.getBlockState(pos.east()).getBlock() == this);
	//
	// return stateCopy;
	// }
}